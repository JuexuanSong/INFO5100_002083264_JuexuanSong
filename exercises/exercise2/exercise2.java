abstract class Shape {
    static String className = "Shape";  

    abstract double calculateArea();
    abstract double calculatePerimeter();

    public void displayInfo() {
        System.out.println("This is a " + className);
    }
}

class Circle extends Shape {
    static String className = "Circle";  
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    static String className = "Rectangle";  
    private double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea() {
        return length * width;
    }

    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

class Square extends Rectangle {
    static String className = "Square";  

    public Square(double side) {
        super(side, side);  
    }
}

class Triangle extends Shape {
    static String className = "Triangle";  
    private double base, height, side1, side2, side3;

    public Triangle(double base, double height, double side1, double side2, double side3) {
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    double calculatePerimeter() {
        return side1 + side2 + side3;
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape square = new Square(4);
        Shape triangle = new Triangle(3, 4, 3, 4, 5);

        Shape[] shapes = {circle, rectangle, square, triangle};

        for (Shape shape : shapes) {
            shape.displayInfo();
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
            System.out.println();
        }
    }
}
