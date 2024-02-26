interface Strategy {
    public void attack();
}

class UseBow implements Strategy {
    @Override
    public void attack() {
        System.out.println("Выстрел из лука");
    }
}

class UseSpear implements Strategy {
    @Override
    public void attack() {
        System.out.println("Удар копьем");
    }
}

class UseAxe implements Strategy {
    @Override
    public void attack() {
        System.out.println("Удар топором");
    }
}

abstract class Warrior {
    Strategy strategy;
}

class Archer extends Warrior {
    public Archer() {
        strategy = new UseBow();
    }
}

class Infantry extends Warrior {
    public Infantry() {
        strategy = new UseAxe();
    }
}

public class Client {
    public static void main(String[] args) {
        Warrior archer = new Archer();
        archer.strategy.attack();
        Warrior infantry = new Infantry();
        infantry.strategy.attack();
    }
}