import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Test extends Application
{
    Group drowRoot;

    public static void main(String[] args) {
        launch(args);
    }

    public class DragListener implements EventHandler<MouseEvent>
    {
        Circle shape;

        public DragListener(Circle shape)
        {
            this.shape = shape;
        }

        public void handle(MouseEvent e)
        {
            shape.setCenterX(e.getX());
            shape.setCenterY(e.getY());
            e.consume();
        }
    }

    public void start(Stage primaryStage)
    {
        Pane root = new Pane();

        VBox layout = new VBox();
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        MenuItem save = new MenuItem("Save");
        file.getItems().add(save);
        menuBar.getMenus().add(file);

        Rectangle clip = new Rectangle(1000, 700);

        System.out.println(Integer.toHexString(System.identityHashCode(clip)));

        clip.setLayoutX(0);
        clip.setLayoutY(0);
        clip.setFill(Paint.valueOf("Grey"));

        layout.getChildren().addAll(menuBar, root);
        root.setClip(clip);

        Circle shape = new Circle(10, 10, 10);
        shape.setOnMouseDragged(new DragListener(shape));

        Polygon poly = new Polygon();
        Double[] ar = new Double[]{493.0, 272.0, 462.0, 315.0, 411.0, 298.0, 411.0, 245.0, 462.0, 228.0};
        poly.getPoints().addAll(ar);
        poly.getPoints().clear();

        ArrayList<Double> li = new ArrayList<Double>();
        for(int i=0;i<ar.length;i++){
            li.add(ar[i]+70);
        }
        poly.getPoints().addAll(li);
        root.getChildren().addAll(shape,poly);
        primaryStage.setScene(new Scene(layout, 1000, 700));
        primaryStage.show();
    }
}