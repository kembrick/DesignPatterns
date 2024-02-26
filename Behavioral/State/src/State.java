interface PackageState {
    void next(Package pkg);
    void printStatus();
}

class OrderedState implements PackageState {
    @Override
    public void next(Package pkg) {
        pkg.setState(new DeliveredState());
    }
    @Override
    public void printStatus() {
        System.out.println("Посылка собрана, готова к отправке.");
    }
}

class DeliveredState implements PackageState {
    @Override
    public void next(Package pkg) {
        pkg.setState(new ReceivedState());
    }
    @Override
    public void printStatus() {
        System.out.println("Посылка передана в службу доставки.");
    }
}

class ReceivedState implements PackageState {
    @Override
    public void next(Package pkg) {
        System.out.println("Посылка уже получена клиентом.");
    }
    @Override
    public void printStatus() {
        System.out.println("Посылка доставлена клиенту.");
    }
}

class Package {
    private PackageState state = new OrderedState();
    public void setState(PackageState state) {
        this.state = state;
    }
    public void nextState() {
        state.next(this);
    }
    public void printStatus() {
        state.printStatus();
    }
}

public class State {
    public static void main(String[] args) {
        Package pkg = new Package();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();
    }
}