import java.util.ArrayList;
import java.util.List;

class Infantryman {
    public void info() {
        System.out.println("Пехотинец");
    }
}

class Archer {
    public void info() {
        System.out.println("Лучник");
    }
}

class Horseman {
    public void info() {
        System.out.println("Кавалерист");
    }
}

class Catapult {
    public void info() {
        System.out.println("Катапульта");
    }
}

class Elephant {
    public void info() {
        System.out.println("Слон");
    }
}

class Army {
    List<Infantryman> vi = new ArrayList<>();
    List<Archer> va = new ArrayList<>();
    List<Horseman> vh = new ArrayList<>();
    List<Catapult> vc = new ArrayList<>();
    List<Elephant> ve = new ArrayList<>();

    public void info() {
        for (Infantryman infantryman : vi)
            infantryman.info();
        for (Archer archer : va)
            archer.info();
        for (Horseman horseman : vh)
            horseman.info();
        for (Catapult catapult : vc)
            catapult.info();
        for (Elephant elephant : ve)
            elephant.info();
    }
}

class ArmyBuilder {
    protected Army p;

    public ArmyBuilder() {
        p = null;
    }

    public void createArmy() {}

    public void buildInfantryman() {}

    public void buildArcher() {}

    public void buildHorseman() {}

    public void buildCatapult() {}

    public void buildElephant() {}

    public Army getArmy() {
        return p;
    }
}

class RomanArmyBuilder extends ArmyBuilder {
    public void createArmy() {
        p = new Army();
    }

    public void buildInfantryman() {
        p.vi.add(new Infantryman());
    }

    public void buildArcher() {
        p.va.add(new Archer());
    }

    public void buildHorseman() {
        p.vh.add(new Horseman());
    }

    public void buildCatapult() {
        p.vc.add(new Catapult());
    }
}

class CarthaginianArmyBuilder extends ArmyBuilder {
    public void createArmy() {
        p = new Army();
    }

    public void buildInfantryman() {
        p.vi.add(new Infantryman());
    }

    public void buildArcher() {
        p.va.add(new Archer());
    }

    public void buildHorseman() {
        p.vh.add(new Horseman());
    }

    public void buildElephant() {
        p.ve.add(new Elephant());
    }
}

class Director {
    public Army createArmy(ArmyBuilder builder) {
        builder.createArmy();
        builder.buildInfantryman();
        builder.buildArcher();
        builder.buildHorseman();
        builder.buildCatapult();
        builder.buildElephant();
        return builder.getArmy();
    }
}

public class Main {
    public static void main(String[] args) {
        Director dir = new Director();
        RomanArmyBuilder ra_builder = new RomanArmyBuilder();
        CarthaginianArmyBuilder ca_builder = new CarthaginianArmyBuilder();
        Army ra = dir.createArmy(ra_builder);
        Army ca = dir.createArmy(ca_builder);
        System.out.println("Римская армия:");
        ra.info();
        System.out.println("\nАрмия Карфагена:");
        ca.info();
    }
}