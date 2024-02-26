import java.util.ArrayList;
import java.util.List;

/**
 Пример использования шаблона проектирования Visitor.
 Имеется набор различных фигур, таких как круги, квадраты и треугольники.
 Нам требуется вычислить площадь каждой фигуры,
 но при этом классы самих фигур не будут подвергаться изменениям.
 */

interface Shape {
    void accept(ShapeVisitor visitor);
}

class Circle implements Shape {
    private final double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class Square implements Shape {
    private final double side;
    public Square(double side) {
        this.side = side;
    }
    public double getSide() {
        return side;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

class Triangle implements Shape {
    private final double base;
    private final double height;
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    public double getBase() {
        return base;
    }
    public double getHeight() {
        return height;
    }
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

/**
 В интерфейсе ShapeVisitor определяются методы для вычисления площади каждого типа фигуры.
 */
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Square square);
    void visit(Triangle triangle);
}

/**
 Конкретная реализация Visitor'а.
 */
class AreaCalculator implements ShapeVisitor {
    private double totalArea = 0;
    @Override
    public void visit(Circle circle) {
        totalArea += Math.PI * circle.getRadius() * circle.getRadius();
    }
    @Override
    public void visit(Square square) {
        totalArea += square.getSide() * square.getSide();
    }
    @Override
    public void visit(Triangle triangle) {
        totalArea += 0.5 * triangle.getBase() * triangle.getHeight();
    }
    public double getTotalArea() {
        return totalArea;
    }
}

/**
 Класс для операций над группой фигур, содержит набор фигур в виде списка.
 Метод calculateArea вычисляет общую площадь всех фигур,
 вызывая в цикле метод accept для каждой из них,
 передавая AreaCalculator в Visitor.
 */
class ObjectStructure {
    private final List<Shape> shapes = new ArrayList<>();
    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public void calculateArea(AreaCalculator areaCalculator) {
        for (Shape shape : shapes)
            shape.accept(areaCalculator);
    }
}

public class Client {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.addShape(new Circle(5));
        objectStructure.addShape(new Square(4));
        objectStructure.addShape(new Triangle(3, 6));
        AreaCalculator areaCalculator = new AreaCalculator();
        objectStructure.calculateArea(areaCalculator);
        System.out.println("Общая площадь фигур: " + areaCalculator.getTotalArea());
    }
}