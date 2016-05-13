import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Stack;

/**
 * Created by mahmoud on 5/2/2016.
 */
public class GUIMain extends Application{

    Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

    double windowWidth = bounds.getWidth();
    double windowHeight = bounds.getHeight();

    // GUI Front-end elements
    BorderPane mainLayout;
    VBox menusOuterLayout;
    MenuBar menuBar;
    HBox shapesMenu;
    HBox settingsLayout;
    Pane shapesRoot;
    GUIHelpers guiHelpers;
    Rectangle clip;

    DrawApplication drawApplication;

    // settings for the context menu
    private ContextMenu contextMenu;
    private MenuItem editMenuItem, copyMenuItem, deleteMenuItem;

    // settings for the main context menu
    private ContextMenu mainContextMenu;
    private MenuItem pasteMenuItem;
    private MenuItem undoMenuItem;
    private MenuItem redoMenuItem;

    private CopyHandler copyHandler;

    /*
        shapes Numbers:
        1- Circle
        2- Ellipse
        3- Rectangle
        4- Square
        5- Triangle
        6- Polygon
        7- Line
     */


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage)
    {

        //init drawApp 'singleton'
        drawApplication = drawApplication.getInstance();


        mainLayout = new BorderPane();


        // setting the gui helper
        guiHelpers = new GUIHelpers();

        // setting up the copy handler
        copyHandler = new CopyHandler(drawApplication, guiHelpers);


        // Setting the menu bar
        menuBar = guiHelpers.getMenuBar();

        // setting the shape menu
        shapesMenu = guiHelpers.getShapesMenu();

        // setting the settings box
        settingsLayout = guiHelpers.getSettingsLayout();

        // setting the shape root from the drawApp

        shapesRoot = drawApplication.getRoot();
        shapesRoot.setStyle("-fx-background-color: #ffffff;");
        clip = new Rectangle(windowWidth, windowHeight);
        shapesRoot.setClip(clip);
        shapesRoot.setPadding(new Insets(100, 0, 0, 0));
        BorderPane.setAlignment(shapesRoot, Pos.CENTER);


        // setting up the context menu
        contextMenu = new ContextMenu();
        editMenuItem = new MenuItem("Edit");
        editMenuItem.setOnAction(e -> {

            EditWindow w = new EditWindow(contextMenu.getOwnerNode(),drawApplication);
            w.makeWindow();

        });

        copyMenuItem = new MenuItem("Copy");
        //copyMenuItem.setOnAction(new CopyHandler(contextMenu.getOwnerNode(), drawApplication));
        copyMenuItem.setOnAction(e ->
            copyHandler.handleCopy(contextMenu.getOwnerNode())
        );

        deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(e -> {
            // Deleting the shape
            drawApplication.deleteShape((Shape) contextMenu.getOwnerNode());
        });
        contextMenu.getItems().addAll(editMenuItem, copyMenuItem, deleteMenuItem);


        // setting up the main context menu
        mainContextMenu = new ContextMenu();
        pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setOnAction(e -> {
            Shape newShape = copyHandler.handlePaste();
            setUpNewShape(newShape);
        });

        undoMenuItem = new MenuItem("Undo");
        undoMenuItem.setOnAction(e -> drawApplication.getHistoryHandler().undo());

        redoMenuItem = new MenuItem("Redo");
        redoMenuItem.setOnAction(e -> drawApplication.getHistoryHandler().redo());

        mainContextMenu.getItems().addAll(pasteMenuItem, undoMenuItem, redoMenuItem);


        //for handling all possible mouse events for free hand sketching
        EventHandler<MouseEvent> freehand = new FreeHand(drawApplication,guiHelpers);
        shapesRoot.setOnMouseClicked(freehand);
        shapesRoot.setOnMouseDragged(freehand);
        shapesRoot.setOnMouseEntered(freehand);
        shapesRoot.setOnMouseExited(freehand);
        shapesRoot.setOnMouseMoved(freehand);
        shapesRoot.setOnMousePressed(freehand);
        shapesRoot.setOnMouseReleased(freehand);


        /* when the Pane is clicked on guiHelpers is called to determine whether
         * the guiHelper is listening for points for a previous shape draw or not
         */
        shapesRoot.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                mainContextMenu.hide();
                if (guiHelpers.getListeningForPoints() == 1 && (guiHelpers.getStatus() == 5 || guiHelpers.getStatus() == 7)
                    /*TODO add the rest */) {
                /* if the guiHelper is listening for points the new point is added
                 * then we check if guiHelper received all the points it need to draw the shape
                 * if so a new polygon is created with the points recorded in the guiHelper
                 */
                    System.out.println("You are listening");
                    System.out.println(e.getX() + " " + e.getY());
                    System.out.println(guiHelpers.getStatus());
                    int temp = guiHelpers.getStatus();
                    if (guiHelpers.pointClicked(e.getX(), e.getY())) {
                        Shape shape;
                        Stack<Double> points = guiHelpers.getPoints();
                        String[] settings = guiHelpers.getSettingsHelper().getSettings(temp);
                        switch (temp) {
                            case 5:
                                shape = new Polygon();
                                ((Polygon) shape).getPoints().addAll(points);
                                shape.setFill(Paint.valueOf(settings[0]));
                                drawApplication.addShape(shape);
                                setUpNewShape(shape);
                                break;
                            case 7:
                                shape = new Line();
                                shape.setStrokeWidth(Double.valueOf(settings[1]));
                                ((Line) shape).setEndY(points.pop());
                                ((Line) shape).setEndX(points.pop());
                                ((Line) shape).setStartY(points.pop());
                                ((Line) shape).setStartX(points.pop());
                                shape.setStroke(Paint.valueOf(settings[0]));
                                shape.setFill(Paint.valueOf(settings[0]));
                                drawApplication.addShape(shape);
                                setUpNewShape(shape);
                                break;
                        }

                    /*
                    shapesRoot.getChildren().add(shape);
                    shape.setOnMouseClicked(event -> {
                        guiHelpers.getContextMenu().show(shape, event.getX(), event.getY());
                    });
                    */
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
                } else {
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
                            drawApplication.addShape(circle);
                            setUpNewShape(circle);
                            break;
                        case 2:
                            settings = guiHelpers.getSettingsHelper().getSettings(2);
                            Ellipse ellipse = new Ellipse(Double.valueOf(settings[0]), Double.valueOf(settings[1]));
                            ellipse.setFill(Paint.valueOf(settings[3]));
                            ellipse.setCenterX(e.getX());
                            ellipse.setCenterY(e.getY());
                            ellipse.setRotate(Double.valueOf(settings[2]));
                            //ellipse.getTransforms().add(new Rotate(Double.valueOf(settings[2]),ellipse.getCenterX(),ellipse.getCenterY()));
                            drawApplication.addShape(ellipse);
                            setUpNewShape(ellipse);
                            break;
                        case 3:
                            settings = guiHelpers.getSettingsHelper().getSettings(3);
                            Rectangle rectangle = new Rectangle(Double.valueOf(settings[0]), Double.valueOf(settings[1]));
                            rectangle.setFill(Paint.valueOf(settings[3]));
                            rectangle.setX(e.getX());
                            rectangle.setY(e.getY());
                            rectangle.setRotate(Double.valueOf(settings[2]));
                            //rectangle.getTransforms().add(new Rotate(Double.valueOf(settings[2]),e.getX(),e.getY()));
                            drawApplication.addShape(rectangle);
                            setUpNewShape(rectangle);

                            break;
                        case 4:
                            settings = guiHelpers.getSettingsHelper().getSettings(4);
                            Rectangle square = new Rectangle(Double.valueOf(settings[0]), Double.valueOf(settings[0]));
                            square.setFill(Paint.valueOf(settings[2]));
                            square.setX(e.getX());
                            square.setY(e.getY());
                            square.setRotate(Double.valueOf(settings[1]));
                            //square.getTransforms().add(new Rotate(Double.valueOf(settings[1]),e.getX(),e.getY()));
                            drawApplication.addShape(square);
                            setUpNewShape(square);
                            break;
                        case 6:
                            /*TODO: fix this -> shoof MathHelper 3shan lma b7seb alsideLength bl fn aly ana 3amlha bytla3 nos aly bt7sbo blzabt
                             * grab trsem wa7ed 3ady b side length b3den dos edit hytla3lak aly ana b7sbo
                             */
                            settings = guiHelpers.getSettingsHelper().getSettings(6);
                            Polygon polygon = new Polygon();
                            polygon.getPoints().addAll(MathHelper.calculatePolygonVertices(e.getX(), e.getY(),
                                    Double.valueOf(settings[1]), Integer.valueOf(settings[0])));
                            polygon.setRotate(Double.valueOf(settings[2]));
                            //polygon.getTransforms().add(new Rotate(Double.valueOf(settings[2]),MathHelper.centerFromVertices(polygon.getPoints())[0],MathHelper.centerFromVertices(polygon.getPoints())[1]));
                            polygon.setFill(Paint.valueOf(settings[3]));
                            drawApplication.addShape(polygon);
                            setUpNewShape(polygon);
                    }
                }
            }
            // new for the right click handling
            else if (e.getButton() == MouseButton.SECONDARY)
            {
                mainContextMenu.show(drawApplication.getRoot() , e.getScreenX(), e.getScreenY());
                if (!copyHandler.getShapeInHand())
                    pasteMenuItem.setDisable(true);
                else
                {
                    copyHandler.setMouseEvent(e);
                    pasteMenuItem.setDisable(false);
                }

            }
            e.consume();
        });

        // showing the history window
        drawApplication.showHistoryWindow();

        // Setting the scene
        menusOuterLayout = new VBox();
        menusOuterLayout.getChildren().addAll(menuBar, shapesMenu, settingsLayout);
        mainLayout.setTop(menusOuterLayout);
        mainLayout.setCenter(shapesRoot);
        Scene scene = new Scene(mainLayout, windowWidth, windowHeight);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }


    /* Setting up the new Shape by adding it to the shapes root and setting up the
     * Event listeners for clicks and dragging
     */
    public void setUpNewShape(Shape shape)
    {
        shapesRoot.getChildren().add(shape);

        shape.setOnMouseClicked(e -> {
            // if the user right-clicked the shape show the context menu
            if (e.getButton() == MouseButton.SECONDARY)
                getContextMenu().show(shape, e.getX(), e.getY());

        });

        ShapeLink shapeLink = drawApplication.searchFor(shape);
        drawApplication.getHistoryHandler().addMemento("A "+shape.getClass().toString()+" Added",
                shapeLink, 0);
        // handling the drag to move the shape
        DragEventHandler newDragEventHandler = new DragEventHandler(shapeLink.getType(),
                shape, shapeLink.getShape(), guiHelpers);

        shape.setOnMousePressed(e -> {

            System.out.println("In");
            newDragEventHandler.setInitialPosition(shape);
            newDragEventHandler.setInitialClickPositionX(e.getX());
            newDragEventHandler.setInitialClickPositionY(e.getY());
            //newDragEventHandler.setInitialPositionX(String.valueOf(e.getX()));
            //newDragEventHandler.setInitialPositionY(String.valueOf(e.getY()));
            e.consume();
        });

        shape.setOnMouseDragged(newDragEventHandler);

        shape.setOnMouseReleased(e -> {
            if (e.getX() != newDragEventHandler.getInitialClickPositionX() || e.getY() != newDragEventHandler.getInitialClickPositionY())
            {
                System.out.println("Out");
                newDragEventHandler.registerMovement(String.valueOf(e.getX()), String.valueOf(e.getY()),
                        drawApplication.getHistoryHandler(), shapeLink);
            }
            e.consume();
        });
        //shape.setOnMouseDragged(new DragEventHandler(shapeLink.getType(), shape, shapeLink.getShape(), guiHelpers));
    }


    public ContextMenu getContextMenu() {
        return contextMenu;
    }

}
