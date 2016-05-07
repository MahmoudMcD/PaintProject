import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * Created by mahmoud on 5/2/2016.
 */
public class GUIMain extends Application{

    // GUI Front-end elements
    double windowWidth = 1000;
    double windowHeight = 700;

    BorderPane mainLayout;
    VBox menusOuterLayout;
    MenuBar menuBar;
    HBox shapesMenu;
    HBox settingsLayout;
    Pane shapesRoot;
    GUIHelpers guiHelpers;
    Rectangle clip;




    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)
    {
        mainLayout = new BorderPane();


        // setting the gui helper
        guiHelpers = new GUIHelpers();

        // Setting the menu bar
        menuBar = guiHelpers.getMenuBar();

        // setting the shape menu
        shapesMenu = guiHelpers.getShapesMenu();

        // setting the settings box
        settingsLayout = guiHelpers.getSettingsLayout();

        // setting the shape root
        shapesRoot = new Pane();
        shapesRoot.setStyle("-fx-background-color: #ffffff;");
        clip = new Rectangle(windowWidth, windowHeight);
        shapesRoot.setClip(clip);
        shapesRoot.setPadding(new Insets(100, 0, 0, 0));
        BorderPane.setAlignment(shapesRoot, Pos.CENTER);


        /* when the Pane is clicked on guiHelpers is called to determine whether
         * the guiHelper is listening for points for a previous shape draw or not
         */
        // TODO use draw application
        shapesRoot.setOnMouseClicked(e -> {
            if (guiHelpers.getListeningForPoints() == 1 && (guiHelpers.getStatus() == 5 || guiHelpers.getStatus() == 7)
                    /*TODO add the rest */)
            {
                /* if the guiHelper is listening for points the new point is added
                 * then we check if guiHelper received all the points it need to draw the shape
                 * if so a new polygon is created with the points recorded in the guiHelper
                 */
                System.out.println("You are listening");
                System.out.println(e.getX() +" "+ e.getY());
                System.out.println(guiHelpers.getStatus());
                int temp = guiHelpers.getStatus();
                if (guiHelpers.pointClicked(e.getX(), e.getY()))
                {
                    Shape shape = null;
                    Stack<Double> points = guiHelpers.getPoints();
                    String[] settings = guiHelpers.getSettingsHelper().getSettings(temp);
                    switch (temp)
                    {
                        case 5:
                            shape = new Polygon();
                            ((Polygon) shape).getPoints().addAll(points);
                            shape.setFill(Paint.valueOf(settings[0]));
                            break;
                        case 7:
                            shape = new Line();
                            shape.setStrokeWidth(Double.valueOf(settings[1]));
                            ((Line) shape).setEndY(points.pop());
                            ((Line) shape).setEndX(points.pop());
                            ((Line) shape).setStartY(points.pop());
                            ((Line) shape).setStartX(points.pop());
                            shape.setFill(Paint.valueOf(settings[0]));
                            break;
                    }

                    shapesRoot.getChildren().add(shape);

                    /*
                    Polygon polygon = new Polygon();
                    polygon.getPoints().addAll(guiHelpers.getPoints());

                    String[] settings;

                    switch (temp)
                    {
                        case 5:
                            settings = guiHelpers.getSettingsHelper().getSettings(5);
                            polygon.setFill(Paint.valueOf(settings[0]));
                    }
                    shapesRoot.getChildren().add(polygon);*/
                }
            }
            else
            {
                /* if the guiHelper is not listening for any points we check whether it's waiting for
                 * a position for a shape draw request of not and handle the request by ordering the shape
                 * draw settings from the settingsHelper in the guiHelper
                 */
                String[] settings;
                switch (guiHelpers.getStatus()) {
                    case 1:
                        settings = guiHelpers.getSettingsHelper().getSettings(1);
                        Circle circle = new Circle(Double.valueOf(settings[0]));
                        circle.setFill(Paint.valueOf(settings[1]));
                        circle.setCenterX(e.getX());
                        circle.setCenterY(e.getY());
                        shapesRoot.getChildren().add(circle);
                        break;
                    case 2:
                        settings = guiHelpers.getSettingsHelper().getSettings(2);
                        Ellipse ellipse = new Ellipse(Double.valueOf(settings[0]), Double.valueOf(settings[1]));
                        ellipse.setFill(Paint.valueOf(settings[2]));
                        ellipse.setCenterX(e.getX());
                        ellipse.setCenterY(e.getY());
                        shapesRoot.getChildren().add(ellipse);
                        break;
                    // TODO
                }
            }
            e.consume();
        });

        // Setting the scene
        menusOuterLayout = new VBox();
        menusOuterLayout.getChildren().addAll(menuBar, shapesMenu, settingsLayout);
        mainLayout.setTop(menusOuterLayout);
        mainLayout.setCenter(shapesRoot);
        Scene scene = new Scene(mainLayout, windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
