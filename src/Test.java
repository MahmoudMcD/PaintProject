import javafx.scene.Node;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;

/**
 * Created by mahmo on 4/29/2016.
 */

public class Test {

    public static void main(String[] args) {
        testClass(new Circle(10));
        testThisClass(new javafx.scene.shape.Circle(10));

    }

    public static void testClass(Node javaFXClass)
    {
        ((Circle) javaFXClass).setCenterX(10);
        System.out.println(javaFXClass instanceof javafx.scene.shape.Circle);
    }

    public static void testThisClass(Shape shape)
    {
        System.out.println("This is our shape");
    }

    public static void testThisClass(javafx.scene.shape.Circle shape)
    {
        System.out.println("This is JavaFX Class");
    }
}
