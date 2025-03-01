
import java.io.*;

public class ShapeDemo {

    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape square = new Square(4);
        Shape triangle = new Triangle(3, 4, 3, 4, 5);

        Shape[] shapes = {circle, rectangle, square, triangle};
        String filePath = "shapes.ser";
        serializeShapes(shapes, filePath);

        Shape[] deserializedShapes = deserializeShapes(filePath);

        if (deserializedShapes != null) {
            for (Shape shape : deserializedShapes) {
                shape.displayInfo();
                System.out.println("Area: " + shape.calculateArea());
                System.out.println("Perimeter: " + shape.calculatePerimeter());
                System.out.println();
            }
        }
    }

    private static void serializeShapes(Shape[] shapes, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
            System.out.println("Shapes have been serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Shape[] deserializeShapes(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Shape[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
