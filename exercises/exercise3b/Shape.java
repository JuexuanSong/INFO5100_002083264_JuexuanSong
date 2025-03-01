
import java.io.Serializable;

public abstract class Shape implements Serializable {

    private static final long serialVersionUID = 1L;

    static String className = "Shape";

    abstract double calculateArea();

    abstract double calculatePerimeter();

    public void displayInfo() {
        System.out.println("This is a " + className);
    }
}
