import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

/**
 * Created by mahmo on 5/8/2016.
 */
public class CopyHandler /*implements EventHandler<ActionEvent>*/{

    private boolean shapeInHand;
    private ShapeLink toBeCopyed;
    DrawApplication drawApplication;
    private MouseEvent mouseEvent;
    private GUIHelpers guiHelpers;


    public CopyHandler(DrawApplication drawApplication, GUIHelpers guiHelpers)
    {
        shapeInHand = false;
        toBeCopyed = null;
        this.drawApplication = drawApplication;
        this.guiHelpers = guiHelpers;
    }

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
        System.out.println(toBeCopyed);
    }

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
       return new Circle(shape.getRadius());
    }

    private Ellipse copyEllipse(Ellipse shape)
    {
        return new Ellipse(shape.getRadiusX(), shape.getRadiusY());
    }

    private Rectangle copyRectangle(Rectangle shape)
    {
        return new Rectangle(shape.getWidth(), shape.getHeight());
    }

    private Polygon copyPolygon(Polygon shape)
    {
        Polygon newPolygon = new Polygon();
        newPolygon.getPoints().addAll(shape.getPoints());
        return newPolygon;
    }

    private Line copyLine (Line shape)
    {
        return new Line(shape.getStartX(), shape.getStartY(),
                shape.getEndX(), shape.getEndY());
    }

    public boolean getShapeInHand() {
        return shapeInHand;
    }

    public ShapeLink getToBeCopyed() {
        return toBeCopyed;
    }

    public Shape handlePaste()
    {
        System.out.println(toBeCopyed);
        System.out.println(toBeCopyed.getShapeFX());
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
