enum WarriorType {
    INFANTRY,
    CAVALRY
}

abstract class Warrior {
    WarriorType type;
    String name;
    Warrior(WarriorType type, String name) {
        this.type = type;
        this.name = name;
    }
    abstract void create();
}

class InfantryWarrior extends Warrior {
    InfantryWarrior() {
        super(WarriorType.INFANTRY, "Пехотинец");
        create();
    }
    @Override
    void create() {
        System.out.println("Создан " + name + " (" + type + ")");
    }
}

class CavalryWarrior extends Warrior {
    CavalryWarrior() {
        super(WarriorType.CAVALRY, "Кавалерист");
        create();
    }
    @Override
    void create() {
        System.out.println("Создан " + name + " (" + type + ")");
    }
}

class WarriorFactory {
    Warrior warrior = null;
    Warrior createWarrior(WarriorType type) {
        switch (type) {
            case INFANTRY:
                warrior = new InfantryWarrior();
                break;
            case CAVALRY:
                warrior = new CavalryWarrior();
                break;
        }
        return warrior;
    }
}

public class FactoryRunner {
    public static void main(String[] args) {
        Warrior warrior1 = new WarriorFactory().createWarrior(WarriorType.CAVALRY);
        Warrior warrior2 = new WarriorFactory().createWarrior(WarriorType.INFANTRY);
    }
}
