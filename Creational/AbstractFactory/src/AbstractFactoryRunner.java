enum Nation {
    ROMAN,
    VIKING
}
enum WarriorType {
    INFANTRY,
    ARCHER,
    CAVALRY
}

abstract class Warrior {
    WarriorType type;
    Nation nation;
    String name;
    Warrior(WarriorType type, Nation nation, String name) {
        this.type = type;
        this.nation = nation;
        this.name = name;
    }
    abstract void create();
}

class InfantryWarrior extends Warrior {
    InfantryWarrior(Nation nation, String name) {
        super(WarriorType.INFANTRY, nation, name);
        create();
    }
    @Override
    void create() {
        System.out.println("Создан " + name + " (" + nation + " " + type + ")");
    }
}

class CavalryWarrior extends Warrior {
    CavalryWarrior(Nation nation, String name) {
        super(WarriorType.CAVALRY, nation, name);
        create();
    }
    @Override
    void create() {
        System.out.println("Создан " + name + " (" + nation + " " + type + ")");
    }
}

interface NationWarriorFactory {
    InfantryWarrior makeInfantryWarrior();
    CavalryWarrior makeCavalryWarrior();
}

class RomanWarriorFactory implements NationWarriorFactory {
    @Override
    public InfantryWarrior makeInfantryWarrior() {
        return new InfantryWarrior(Nation.ROMAN, "Римский пехотинец");
    }
    @Override
    public CavalryWarrior makeCavalryWarrior() {
        return new CavalryWarrior(Nation.ROMAN, "Римский кавалерист");
    }
    Warrior createWarrior(WarriorType type) {
        switch (type) {
            case INFANTRY:
                return makeInfantryWarrior();
            case CAVALRY:
                return makeCavalryWarrior();
        }
        return null;
    }
}

class VikingWarriorFactory implements NationWarriorFactory {
    @Override
    public InfantryWarrior makeInfantryWarrior() {
        return new InfantryWarrior(Nation.VIKING, "Пеший викинг");
    }
    @Override
    public CavalryWarrior makeCavalryWarrior() {
        return new CavalryWarrior(Nation.VIKING, "Конный викинг");
    }
    Warrior createWarrior(WarriorType type) {
        switch (type) {
            case INFANTRY:
                return makeInfantryWarrior();
            case CAVALRY:
                return makeCavalryWarrior();
        }
        return null;
    }
}

class WarriorAbstractFactory {
    Warrior warrior;
    Warrior createWarrior(Nation nation, WarriorType type) {
        switch (nation) {
            case ROMAN:
                warrior = new RomanWarriorFactory().createWarrior(type);
                break;
            case VIKING:
                warrior = new VikingWarriorFactory().createWarrior(type);
                break;
        }
        return warrior;
    }
}

public class AbstractFactoryRunner {
    public static void main(String[] args) {
        new WarriorAbstractFactory().createWarrior(Nation.ROMAN, WarriorType.CAVALRY);
        new WarriorAbstractFactory().createWarrior(Nation.VIKING, WarriorType.INFANTRY);
    }
}
