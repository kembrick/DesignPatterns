interface InterfaceWarrior {
    void doOperation();
}

class Warrior implements InterfaceWarrior {
    @Override
    public void doOperation() {
        System.out.print("атакует... ");
    }
}

abstract class Decorator implements InterfaceWarrior {
    protected InterfaceWarrior warrior;
    public Decorator (InterfaceWarrior w) {
        warrior = w;
    }
    @Override
    public void doOperation() {
        warrior.doOperation();
    }
    public void newOperation() {}
}

class DecoratorInfantry extends Decorator {
    public DecoratorInfantry(InterfaceWarrior w) {
        super(w);
    }
    @Override
    public void doOperation() {
        System.out.print("обнажает меч... ");
        super.doOperation();
    }
    @Override
    public void newOperation() {
        System.out.println("Оружие к бою");
    }
}

class DecoratorRoman extends Decorator {
    public DecoratorRoman(InterfaceWarrior w) {
        super(w);
    }
    @Override
    public void doOperation() {
        System.out.print("ожидает команды командира... ");
        super.doOperation();
    }
    @Override
    public void newOperation() {
        System.out.println("Ждет команду");
    }
}

class Main {
    public static void main(String[] args) {
        Decorator c = new DecoratorRoman(new DecoratorInfantry(new Warrior()));
        c.newOperation(); // Выводит: "Ждет команду"
        c.doOperation();  // Выводит: "ожидает команды командира... обнажает меч... атакует..."
    }
}