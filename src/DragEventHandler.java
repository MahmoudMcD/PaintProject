import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;

/**
 * Created by mahmoud on 5/8/2016.
 */
public class DragEventHandler implements EventHandler<MouseEvent>{

    Shape shape;
    iShape ishape;
    String type;
    GUIHelpers guiHelpers;

    public DragEventHandler(String type, Shape shape, iShape ishape, GUIHelpers guiHelpers)
    {
        this.shape = shape;
        this.ishape = ishape;
        this.type = type;
        this.guiHelpers = guiHelpers;
    }

    public void handle(MouseEvent e)
    {
        guiHelpers.setStatus(0);
        switch (type.toLowerCase())
        {
            case "circle":
                ((Circle) shape).setCenterX(e.getX());
                ((Circle) shape).setCenterY(e.getY());
                ishape.moveShape(e.getX(), e.getY());
                break;
            case "ellipse":
                ((Ellipse) shape).setCenterX(e.getX());
                ((Ellipse) shape).setCenterY(e.getY());
                ishape.moveShape(e.getX(), e.getY());
                break;
            case "rectangle":
                ((Rectangle) shape).setX(e.getX());
                ((Rectangle) shape).setY(e.getY());
                iRectangle temp = (iRectangle) ishape;
                temp.resizeShape(e.getX(), e.getY(),
                        e.getX() + temp.getWidth(), e.getY(),
                        e.getX(), e.getY() + temp.getHeight(),
                        e.getX() + temp.getWidth(), e.getY() + temp.getHeight());

                break;
            case "square":
                ((Rectangle) shape).setX(e.getX());
                ((Rectangle) shape).setY(e.getY());
                iSquare tempSquare = (iSquare) ishape;
                tempSquare.resizeShape(e.getX(), e.getY(),
                        e.getX() + tempSquare.getSideLength(), e.getY(),
                        e.getX(), e.getY()+ tempSquare.getSideLength(),
                        e.getX() + tempSquare.getSideLength(), e.getY()+ tempSquare.getSideLength());

                break;
            case "regularPolygon":
                iPolygons tempRPoly = (iPolygons) ishape;
                ((Polygon) shape).getPoints().clear();
                Double[] newPoints = MathHelper.calculatePolygonVertices(e.getX(), e.getY(),
                        tempRPoly.getSideLength() ,tempRPoly.getNoOfSides());
                ((Polygon) shape).getPoints().addAll(newPoints);
                // TODO
        }
        e.consume();
    }
}
