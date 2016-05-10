import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

/**
 * Created by mahmoud on 5/8/2016.
 */
public class CopyHandler /*implements EventHandler<ActionEvent>*/{

    private boolean shapeInHand;
    private ShapeLink toBeCopyed;
    private DrawApplication drawApplication;
    private MouseEvent mouseEvent;
    private GUIHelpers guiHelpers;

    /* CopyHandler is created when the application starts
     * it's job is to save the shape that's a copy
     * and paste it where the user clicks paste
     */
    public CopyHandler(DrawApplication drawApplication, GUIHelpers guiHelpers)
    {
        shapeInHand = false;
        toBeCopyed = null;
        this.drawApplication = drawApplication;
        this.guiHelpers = guiHelpers;
    }

    /* handleCopy creates a copy of the shape given  and save it
     * in the toBeCopyed and set shapeInHand = true
     */
    public void handleCopy(Node shape)
    {
        ShapeLink shapeLink = drawApplication.searchFor((Shape) shape);
        iShape newiShape = shapeLink.getShape().copy();

        Shape newShape = copyShape((Shape) shape);

        shapeInHand = true;
        toBeCopyed = new ShapeLink();

        toBeCopyed.setType(shapeLink.getType());
        toBeCopyed.setShape(newiShape);
        toBeCopyed.setShapeFX(newShape);

    }

    // Copy methods
    private Shape copyShape(Shape shape)
    {
        if (shape instanceof Circle)
            return copyCircle((Circle) shape);

        else if (shape instanceof Ellipse)
            return copyEllipse((Ellipse) shape);

        else if (shape instanceof Rectangle)
            return copyRectangle((Rectangle) shape);

        else if (shape instanceof Polygon)
            return copyPolygon((Polygon) shape);

        else if (shape instanceof Line)
            return copyLine((Line) shape);

        return null;
    }

    private Circle copyCircle(Circle shape)
    {
        Circle newCircle = new Circle(shape.getRadius());
        newCircle.setFill(shape.getFill());
       return newCircle;
    }

    private Ellipse copyEllipse(Ellipse shape)
    {
        Ellipse newEllipse = new Ellipse(shape.getRadiusX(), shape.getRadiusY());
        newEllipse.setFill(shape.getFill());
        return newEllipse;
    }

    private Rectangle copyRectangle(Rectangle shape)
    {
        Rectangle newRectangle = new Rectangle(shape.getWidth(), shape.getHeight());
        newRectangle.setFill(shape.getFill());
        return newRectangle;
    }

    private Polygon copyPolygon(Polygon shape)
    {
        Polygon newPolygon = new Polygon();
        newPolygon.setFill(shape.getFill());
        newPolygon.getPoints().addAll(shape.getPoints());
        return newPolygon;
    }

    private Line copyLine (Line shape)
    {
        Line newLine = new Line(shape.getStartX(), shape.getStartY(),
                shape.getEndX(), shape.getEndY());
        newLine.setFill(shape.getFill());
        newLine.setStrokeWidth(shape.getStrokeWidth());
        return newLine;
    }

    public boolean getShapeInHand() {
        return shapeInHand;
    }

    public ShapeLink getToBeCopyed() {
        return toBeCopyed;
    }

    /* when handlePaste is called a dragEventHandler is created to move
     * the copy to the place where it's meant to be placed then return
     * the new shape set with the new position
     */
    public Shape handlePaste()
    {

        DragEventHandler dragEventHandler = new DragEventHandler(toBeCopyed.getType(),
                (Shape) toBeCopyed.getShapeFX(), toBeCopyed.getShape(), guiHelpers);

        dragEventHandler.handle(mouseEvent);
        drawApplication.addShapeLink(toBeCopyed);
        //drawApplication.getRoot().getChildren().add(toBeCopyed.getShapeFX());
        ShapeLink temp = toBeCopyed;
        toBeCopyed = null;
        shapeInHand = false;
        mouseEvent = null;
        return (Shape) temp.getShapeFX();
    }

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }

    public void setMouseEvent(MouseEvent mouseEvent) {
        this.mouseEvent = mouseEvent;
    }
}
