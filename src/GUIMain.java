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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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

        // TODO use draw application
        shapesRoot.setOnMouseClicked(e -> {
            String[] settings;
            switch (guiHelpers.getStatus())
            {
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
            }
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
