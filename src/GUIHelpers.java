import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

import java.util.Stack;

/**
 * Created by mahmoud on 5/2/2016.
 */
public class GUIHelpers {

    // Helper
    SettingsHelper settingsHelper;

    // Elements for the menu bar
    private MenuBar menuBar;
    private Menu fileMenu;
    private MenuItem closeItem;
    private Menu aboutMenu;
    private MenuItem helpItem, aboutItem;

    // elements for the shape menu
    private HBox shapesMenu;
    private ImageView circleIcon, ellipseIcon, squareIcon, rectangleIcon, triangleIcon, polygonIcon, lineIcon;

    // Elements for the setting box main layout
    private HBox settingsLayout;
    private Label welcomeLabel;

    private int status;

    // in case of listening for points
    private int listeningForPoints = 0;
    private int numberOfPoints = 0;
    private int desiredNumberOfPoints = 0;
    private Stack<Double> points;

    // for copying and pasting shapes


    public GUIHelpers()
    {

        // Setting up the settings box layouts
        settingsLayout = new HBox();
        settingsLayout.setSpacing(10);
        settingsLayout.setPadding(new Insets(5, 5, 5, 5));
        settingsLayout.setStyle("-fx-background-color: #F5F5DC;");
        settingsLayout.setMinHeight(60);
        welcomeLabel = new Label("Welcome to Paint .. choose a shape to start");
        welcomeLabel.setTextFill(Paint.valueOf("Black"));
        settingsLayout.getChildren().add(welcomeLabel);

        settingsHelper = new SettingsHelper();

        // Setting up the menu bar
        menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #303E57");
        fileMenu = new Menu("File");
        closeItem = new MenuItem("Close");

        fileMenu.getItems().addAll(closeItem);

        aboutMenu = new Menu("About");
        helpItem = new MenuItem("Help");
        aboutItem = new MenuItem("About Us");

        aboutMenu.getItems().addAll(helpItem, aboutItem);

        menuBar.getMenus().addAll(fileMenu, aboutMenu);
        menuBar.setStyle("-fx-color: white;");

        // setting up the shape menu
        shapesMenu = new HBox();
        shapesMenu.setSpacing(10);
        shapesMenu.setPadding(new Insets(5, 5, 5, 5));
        circleIcon = new ImageView(new Image("images/circle.png", 24, 24, true, true));

        circleIcon.setOnMouseClicked(e -> {
            setSettings(1);
        });

        ellipseIcon = new ImageView(new Image("images/ellipse.png", 24, 24, true, true));
        ellipseIcon.setOnMouseClicked(e -> {
            setSettings(2);
        });


        rectangleIcon = new ImageView(new Image("images/rectangle.png", 24, 24, true, true));
        rectangleIcon.setOnMouseClicked(e -> {
            setSettings(3);
        });

        squareIcon = new ImageView(new Image("images/square.png", 24, 24, true, true));
        squareIcon.setOnMouseClicked(e -> {
            setSettings(4);
        });

        triangleIcon = new ImageView(new Image("images/triangle.png", 24, 24, true, true));
        triangleIcon.setOnMouseClicked(e -> {
            setSettings(5, 3);
        });

        polygonIcon = new ImageView(new Image("images/polygon.png", 24, 24, true, true));
        polygonIcon.setOnMouseClicked(e -> {
            setSettings(6);
        });

        lineIcon = new ImageView(new Image("images/line.png", 24, 24, true, true));
        lineIcon.setOnMouseClicked(e -> {
            setSettings(7, 2);
        });

        shapesMenu.getChildren().addAll(circleIcon, ellipseIcon, squareIcon, rectangleIcon,
                triangleIcon, polygonIcon, lineIcon);




    }

    // set the gui helper to listen for x clicks and make new point stack
    public void listenFor(int numberOfPoints)
    {
        this.desiredNumberOfPoints = numberOfPoints;
        //points = new Double[numberOfPoints];
        points = new Stack<>();
    }

    /* when this function is called we add the new point to the points stack
     * and check whether the guiHelper received all the points it needs or not
     */
    public Boolean pointClicked(double xCoordinate, double yCoordinate)
    {

        if (listeningForPoints == 1 && numberOfPoints != desiredNumberOfPoints)
        {
            //points[numberOfPoints] = xCoordinate;
            //points[numberOfPoints + 1] = yCoordinate;
            //System.out.println(points[numberOfPoints] + " " + points[numberOfPoints + 1]);
            points.push(xCoordinate);
            points.push(yCoordinate);
            numberOfPoints++;
        }

        if (numberOfPoints  == desiredNumberOfPoints)
        {
            setSettings(status);
            listeningForPoints = 0;
            numberOfPoints = 0;
            desiredNumberOfPoints = 0;
            return true;
        }

        return false;
    }

    public MenuBar getMenuBar() {return menuBar;}

    public HBox getSettingsLayout() { return settingsLayout; }

    public HBox getShapesMenu() { return shapesMenu; }

    public int getStatus() {return status; }

    public void setStatus(int status)
    {
        this.status = status;
        setSettings(status);
    }

    public SettingsHelper getSettingsHelper() { return settingsHelper;}

    public int getDesiredNumberOfPoints() {
        return desiredNumberOfPoints;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public int getListeningForPoints() {
        return listeningForPoints;
    }

    public /*Double[]*/ Stack<Double> getPoints() {
        return points;
    }

    /* when this function is called we check if status is the same as the number of the
     * icon clicked is so we cancel the draw request and reset the settings layout to the welcome
     * label.
     * if not we set the request and the settings layout
     */
    private void setSettings(int toBeSet)
    {
        if (status == toBeSet)
        {
            status = 0;
            settingsLayout.getChildren().clear();
            settingsLayout.getChildren().addAll(welcomeLabel);
        }
        else
        {
            status = toBeSet;
            settingsLayout.getChildren().clear();
            settingsLayout.getChildren().addAll(settingsHelper.getSettingsLayout(toBeSet));
        }
    }

    /* when this function is called we set the guiHelper to direct the next x clicks to
     * the points stack to draw a shape and if the status is toBeSet handle like the previous
     * function.
     */
    private void setSettings(int toBeSet, int numberOfPoints)
    {
        if (status == toBeSet)
        {
            status = 0;
            settingsLayout.getChildren().clear();
            settingsLayout.getChildren().addAll(welcomeLabel);
            listeningForPoints = 0;
            this.numberOfPoints = 0;
            desiredNumberOfPoints = 0;
            points = null;
        }
        else
        {
            System.out.println("You are now listening");
            status = toBeSet;
            settingsLayout.getChildren().clear();
            settingsLayout.getChildren().addAll(settingsHelper.getSettingsLayout(toBeSet));
            listeningForPoints = 1;
            this.numberOfPoints = 0;
            desiredNumberOfPoints = numberOfPoints;
            //points = new Double[numberOfPoints * 2];
            points = new Stack<>();
        }
    }


}
