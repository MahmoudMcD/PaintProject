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
                //TODO: fix this -> bydrab lma t drag w by2ol mynf3sh t cast iSquare l iRectangle hna blazabt
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
            case "triangle":
                iTriangle tempTriangle = ((iTriangle) ishape);
                double[] xCoordinates = tempTriangle.getxCoord();
                double[] yCoordinates = tempTriangle.getyCoord();

                double xDiff = e.getX() - xCoordinates[0];
                double yDiff = e.getY() - yCoordinates[0];

                ((Polygon) shape).getPoints().clear();
                for (int i = 0; i < 3; i++)
                {
                    yCoordinates[i] += yDiff;
                    xCoordinates[i] += xDiff;
                    ((Polygon) shape).getPoints().addAll(xCoordinates[i], yCoordinates[i]);
                }
                break;
            case "line":
                iLine tempLine = ((iLine) ishape);
                double xStart = tempLine.getxStart();
                double yStart = tempLine.getyStart();
                double xEnd = tempLine.getxEnd();
                double yEnd = tempLine.getyEnd();

                double xDiffLine = e.getX() - xStart;
                double yDiffLine = e.getY() - yStart;

                tempLine.setxStart(xStart += xDiffLine);
                tempLine.setyStart(yStart += yDiffLine);
                tempLine.setxEnd(xEnd += xDiffLine);
                tempLine.setyEnd(yEnd += yDiffLine);

                ((Line) shape).setStartX(xStart += xDiffLine);
                ((Line) shape).setStartY(yStart += yDiffLine);

                ((Line) shape).setEndX(xEnd += xDiffLine);
                ((Line) shape).setEndY(yEnd += yDiffLine);
                break;
            case "regularPolygon":
                iPolygons tempRPoly = (iPolygons) ishape;
                ((Polygon) shape).getPoints().clear();
                Double[] newPoints = MathHelper.calculatePolygonVertices(e.getX(), e.getY(),
                        tempRPoly.getSideLength() ,tempRPoly.getNoOfSides());
                ((Polygon) shape).getPoints().addAll(newPoints);
                // TODO
                break;
        }
        e.consume();
    }
}
