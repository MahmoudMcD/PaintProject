import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;

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
            setSettings(5);
        });

        polygonIcon = new ImageView(new Image("images/polygon.png", 24, 24, true, true));
        polygonIcon.setOnMouseClicked(e -> {
            setSettings(6);
        });

        lineIcon = new ImageView(new Image("images/line.png", 24, 24, true, true));
        lineIcon.setOnMouseClicked(e -> {
            setSettings(7);
        });

        shapesMenu.getChildren().addAll(circleIcon, ellipseIcon, squareIcon, rectangleIcon,
                triangleIcon, polygonIcon, lineIcon);


    }

    public MenuBar getMenuBar() {return menuBar;}

    public HBox getSettingsLayout() { return settingsLayout; }

    public HBox getShapesMenu() { return shapesMenu; }

    public void changeLabel()
    {
        System.out.println("Hello");
        welcomeLabel.setText("Hello there");
    }

    public int getStatus() {return status; }

    public SettingsHelper getSettingsHelper() {return settingsHelper;}


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

}
