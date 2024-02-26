import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Handler {
    Handler setNext(Handler handler); // Метод для построения цепочки обработчиков
    void handle(String request); // Метод для выполнения запроса
    boolean isLocationExists(String destination);
}

class AbstractHandler implements Handler {
    protected List<String> possibleLocations = new ArrayList<>();
    private Handler nextHandler;
    public AbstractHandler() {
        this.nextHandler = null;
    }
    public Handler setNext(Handler handler) {
        this.nextHandler = handler;
        return handler;
    }
    public void handle(String request) {
        if (this.nextHandler != null)
            this.nextHandler.handle(request);
    }
    public boolean isLocationExists(String destination) {
        return possibleLocations.contains(destination);
    }
}

/**
 * Конкретные обработчики либо обрабатывают запрос,
 * либо передают его следующему обработчику в цепочке
 * (если требуемый пункт назначения не найден в списке).
 */
class Underground extends AbstractHandler {
    public Underground() {
        possibleLocations = new ArrayList<>(Arrays.asList("Озерки", "Парнас", "Автово", "Беговая", "Ладожская"));
    }
    public void handle(String request) {
        if (isLocationExists(request))
            System.out.println("До места " + request + " ходит метро");
        else
            super.handle(request);
    }
}

class Car extends AbstractHandler {
    public Car() {
        possibleLocations = new ArrayList<>(Arrays.asList("Пушкин", "Павловск", "Выборг", "Кронштадт", "Зеленогорск"));
    }
    public void handle(String request) {
        if (isLocationExists(request))
            System.out.println("До места " + request + " нужно ехать на автомобиле");
        else
            super.handle(request);
    }
}

class Train extends AbstractHandler {
    public Train() {
        possibleLocations = new ArrayList<>(Arrays.asList("Пенза", "Псков", "Новгород", "Саратов", "Москва", "Ростов"));
    }
    public void handle(String request) {
        if (isLocationExists(request))
            System.out.println("До места " + request + " можно доехать на поезде");
        else
            super.handle(request);
    }
}

class Plane extends AbstractHandler {
    public Plane() {
        possibleLocations = new ArrayList<>(Arrays.asList("Стамбул", "Париж", "Лиссабон", "Каир", "Нью-Йорк", "Дубай"));
    }
    public void handle(String request) {
        if (isLocationExists(request))
            System.out.println("До места " + request + " можно добраться только на самолёте");
        else
            super.handle(request);
    }
}

public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler metro = new Underground();
        Handler car = new Car();
        Handler train = new Train();
        Handler plane = new Plane();
        metro.setNext(car).setNext(train).setNext(plane);
        car.handle("Ростов");
        metro.handle("Нью-Йорк");
    }
}