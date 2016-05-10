import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.*;

/**
 * Created by Tarek Alqaddy on 5/9/2016.
 */
public class ScrollHandle implements EventHandler<ScrollEvent>{
    ShapeLink shapeLink;
    Shape shapeFX;
    iShape shape;
    String type;
    DrawApplication drawApplication;

    public ScrollHandle(ShapeLink s,DrawApplication d){
        shapeLink = s;
        drawApplication = d;
        type = s.getType();
        shape = s.getShape();
        shapeFX = (Shape)s.getShapeFX();
    }

    @Override
    public void handle(ScrollEvent event) {
        switch (type){
            case "Circle":
                double newRadius = ((Circle) shapeFX).getRadius()+(event.getDeltaY())/4;
                drawApplication.resizeShape(shapeFX,newRadius);
                break;
            case "Ellipse":
                double radiusX= ((Ellipse) shapeFX).getRadiusX();
                double radiusY = ((Ellipse) shapeFX).getRadiusY();
                drawApplication.resizeShape(shapeFX,radiusX+event.getDeltaY()*(radiusX/radiusY),radiusY+event.getDeltaY());
                break;
            case "Rectangle":
                double width = ((Rectangle) shapeFX).getWidth();
                double height= ((Rectangle) shapeFX).getHeight();
                drawApplication.resizeShape(shapeFX,width+((event.getDeltaY())/4)*(width/height),height+(event.getDeltaY())/4);
                break;
            case "Square":
                double length = ((Rectangle) shapeFX).getWidth()+(event.getDeltaY())/4;
                drawApplication.resizeShape(shapeFX,length);
                break;
            case "Polygon":
                //TODO: problem
                ObservableList<Double> points = ((Polygon) shapeFX).getPoints();
                double centerX = MathHelper.centerFromVertices(points)[0];
                double centerY= MathHelper.centerFromVertices(points)[1];
                Double[] newPoints = MathHelper.calculatePolygonVertices(centerX,centerY,((iPolygons) shape).getSideLength()+(event.getTotalDeltaY()/5),((iPolygons) shape).getNoOfSides());

                double[] newPoints1 = new double[newPoints.length];
                for(int i=0;i<newPoints.length;i++)
                    newPoints1[i] = newPoints[i];
                //System.out.println(newPoints1[0]);
                System.out.println(((iPolygons) shape).getSideLength());
                System.out.println(((iPolygons) shape).getNoOfSides());
                drawApplication.resizeShape(shapeFX,newPoints1);
                ((Polygon) shapeFX).getPoints().setAll(newPoints);

                break;
            case "Line":
                shapeFX.setStrokeWidth(shapeFX.getStrokeWidth()+(event.getDeltaY())/10);
                ((iLine) shape).setStrokeWidth(shapeFX.getStrokeWidth()+(event.getDeltaY())/10);
                break;
        }
    }
}
