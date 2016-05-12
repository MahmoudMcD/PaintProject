/**
 * Created by Tarek Alqaddy on 4/29/2016.
 */

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.Node;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class DrawApplication {
    private static DrawApplication instance = null;

    ShapeFactory shapeFactory = new ShapeFactory();
    int status;
    ArrayList<ShapeLink> shapes = new ArrayList<>();
    Pane root = new Pane();

    public static DrawApplication  getInstance(){
        if(instance == null){
            return instance = new DrawApplication();
        }
        return instance;
    }

    void addShape(Node shape){
        ShapeLink sl = new ShapeLink();
        sl.setShapeFX(shape);

        if(shape instanceof Circle) {
            sl.setType("Circle");
            sl.setShape(shapeFactory.makeShape("circle", ((Circle) shape).getCenterX(), ((Circle) shape).getCenterY(),
                    ((Circle) shape).getRadius()));
        }
        else if(shape instanceof Ellipse) {
            sl.setType("Ellipse");
            double rotationAngle = ((Rotate) shape.getTransforms().get(0)).getAngle();
            sl.setShape(shapeFactory.makeShape("ellipse", ((Ellipse) shape).getCenterX(), ((Ellipse) shape).getCenterY(),
                    ((Ellipse) shape).getRadiusX(), ((Ellipse) shape).getCenterY(),rotationAngle));
        }
        else if(shape instanceof Rectangle) {

            if (((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight()) {
                sl.setType("Square");
                double rotationAngle = ((Rotate) shape.getTransforms().get(0)).getAngle();
                sl.setShape(shapeFactory.makeShape("square", ((Rectangle) shape).getWidth(),rotationAngle));
            } else{
                sl.setType("Rectangle");
                double rotationAngle = ((Rotate) shape.getTransforms().get(0)).getAngle();
                sl.setShape(shapeFactory.makeShape("rectangle", ((Rectangle) shape).getWidth(), ((Rectangle) shape).getHeight(),rotationAngle));
            }
        }
        else if(shape instanceof Polygon) {
            if (((Polygon) shape).getPoints().toArray().length == 6) {
                System.out.println(" entered triangle ");
                sl.setType("Triangle");
                double[] xCoord = new double[3];
                double[] yCoord = new double[3];
                for (int i = 0; i < 6; i += 2) {
                    xCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i];
                    yCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i + 1];
                }
                sl.setShape(shapeFactory.makeShape("triangle", xCoord, yCoord));
                ((iPolygons) sl.getShape()).setNoOfSides(3);
            } else {
                sl.setType("Polygon");
                int length = ((Polygon) shape).getPoints().toArray().length;
                double[] xCoord = new double[length/2+1];
                double[] yCoord = new double[length/2+1];
                for (int i = 0; i < length; i += 2) {
                    xCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i];
                    yCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i + 1];
                }
                double rotationAngle = ((Rotate) shape.getTransforms().get(0)).getAngle();
                sl.setShape(shapeFactory.makeShape("polygon", xCoord, yCoord,rotationAngle));
                ((iPolygons) sl.getShape()).setNoOfSides(length/2);
                double sideLength = MathHelper.calculateSideLength(((Polygon) sl.getShapeFX()).getPoints());
                ((iPolygons) sl.getShape()).setSideLength(sideLength);
            }
        }
        else if(shape instanceof Line) {
            sl.setType("Line");
            sl.setShape(shapeFactory.makeShape("line", ((Line) shape).getStartX(), ((Line) shape).getStartY(),
                    ((Line) shape).getEndX(), ((Line) shape).getEndY()));
        }
        else{
            throw new RuntimeException("shape not found !");
        }

        sl.getShape().setFillColor(Color.valueOf(((Shape) shape).getFill().toString()));
        shapes.add(sl);
    }


    void removeShape(Node shape){
        for(ShapeLink sl : shapes){
            if(shape.equals(sl.getShapeFX())) {
                shapes.remove(sl);
                break;
            }
        }
    }


    /*
        In case of resizing call resize with the following order :
        "iCircle": (Node shape,double newRadius)
        "iEllipse": (Node shape,double newXRadius,double newYRadius)
        "iLine": (Node shape,double newLineLength)
        "iPolygons": (Node shape,double newCoord) => to be confirmed
        "iSquare": (Node shape,double newLength)
        "iRectangle": (Node shape,double newWidth,double newHeight)
        "iTriangle": (Node shape,double[] xCoord,double[] yCoord)
        and the method changes the new dimensions of 2 nodes (javafx & our node)
     */

    void resizeShape(Node shape,double...moreInfo){

        ShapeLink temp = null;
        for(ShapeLink sl : shapes){
            if(shape.equals(sl.getShapeFX())){
                temp = sl;
                break;
            }
        }

        if(shape instanceof Circle) {
            ((Circle) temp.getShapeFX()).setRadius(moreInfo[0]);
            temp.getShape().resizeShape(moreInfo);
        }
           else if(shape instanceof Ellipse) {
            ((Ellipse) temp.getShapeFX()).setRadiusX(moreInfo[0]);
            ((Ellipse) temp.getShapeFX()).setRadiusY(moreInfo[1]);
            if(moreInfo.length == 3)
                temp.getShapeFX().getTransforms().set(0,new Rotate(moreInfo[2],((Ellipse) temp.getShapeFX()).getCenterX(),((Ellipse) temp.getShapeFX()).getCenterY()));
            temp.getShape().resizeShape(moreInfo);
        }
           else if(shape instanceof Line) {
            temp.getShape().resizeShape(moreInfo);
            ((Line) temp.getShapeFX()).setStrokeWidth(moreInfo[0]);
        }
        else if(shape instanceof Polygon) {
            if (((Polygon) temp.getShapeFX()).getPoints().toArray().length == 6) {
                //TODO for triangle
            } else {
                ArrayList<Double> ar = new ArrayList<>();
                if(moreInfo.length %2 != 0)
                    for (int i = 0; i < moreInfo.length-1; i++)
                        ar.add(moreInfo[i]);

                else
                    for (int i = 0; i < moreInfo.length; i++)
                        ar.add(moreInfo[i]);

                ((Polygon) temp.getShapeFX()).getPoints().setAll(ar);
                ObservableList<Double> li = ((Polygon) temp.getShapeFX()).getPoints();
                temp.getShapeFX().getTransforms().set(0,new Rotate(moreInfo[moreInfo.length-1],MathHelper.centerFromVertices(li)[0],MathHelper.centerFromVertices(li)[1]));
                ((iPolygons) temp.getShape()).setNoOfSides(moreInfo.length/2);
                temp.getShape().resizeShape(moreInfo);
            }
        }
        else if(shape instanceof Rectangle) {
            if (((Rectangle) temp.getShapeFX()).getWidth() == ((Rectangle) temp.getShapeFX()).getHeight()) {
                ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[0]);
                if(moreInfo.length == 2)
                    temp.getShapeFX().getTransforms().set(0,new Rotate(moreInfo[1],MathHelper.centerOfRectangle((Rectangle) temp.getShapeFX())[0],MathHelper.centerOfRectangle((Rectangle) temp.getShapeFX())[1]));
                temp.getShape().resizeShape(moreInfo);
            } else {
                ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[1]);
                if(moreInfo.length == 3)
                     temp.getShapeFX().getTransforms().set(0,new Rotate(moreInfo[2],MathHelper.centerOfRectangle((Rectangle) temp.getShapeFX())[0],MathHelper.centerOfRectangle((Rectangle) temp.getShapeFX())[1]));

                temp.getShape().resizeShape(moreInfo);
            }
        }

    }



    void moveShape(Node shape,double...moreInfo){
        ShapeLink temp = null;
        for(ShapeLink sl : shapes){
            if(shape.equals(sl.getShapeFX())){
                temp = sl;
                break;
            }
        }

        if(shape instanceof Circle) {
            ((Circle) temp.getShapeFX()).setCenterX(moreInfo[0]);
            ((Circle) temp.getShapeFX()).setCenterY(moreInfo[1]);
            temp.getShape().moveShape(moreInfo);
        }

         else if(shape instanceof Ellipse) {
            ((Ellipse) temp.getShapeFX()).setCenterX(moreInfo[0]);
            ((Ellipse) temp.getShapeFX()).setCenterY(moreInfo[1]);
            temp.getShape().moveShape(moreInfo);
        }
        else if(shape instanceof Line) {
            ((Line) temp.getShapeFX()).setStartX(moreInfo[0]);
            ((Line) temp.getShapeFX()).setStartY(moreInfo[1]);
            ((Line) temp.getShapeFX()).setEndX(moreInfo[2]);
            ((Line) temp.getShapeFX()).setEndY(moreInfo[3]);
            temp.getShape().moveShape(moreInfo);
        }
        else if(shape instanceof Polygon) {
            if (((Polygon) temp.getShapeFX()).getPoints().toArray().length == 6) {
                //TODO for iTriangle
            } else {
                ArrayList<Double> ar = new ArrayList<>();
                for (int i = 0; i < moreInfo.length; i++) {
                    ar.add(moreInfo[i]);
                }
                ((Polygon) temp.getShapeFX()).getPoints().clear();
                ((Polygon) temp.getShapeFX()).getPoints().addAll(ar);
                temp.getShape().moveShape(moreInfo);
            }
        }
        else if(shape instanceof Rectangle) {
            if (((Rectangle) temp.getShapeFX()).getHeight() == ((Rectangle) temp.getShapeFX()).getWidth()) {
                ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[0]);
                temp.getShape().moveShape(moreInfo);
            } else {
                ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[1]);
                temp.getShape().moveShape(moreInfo);
            }
        }


    }


    public ShapeLink getShapeLink (iShape shape){
        ShapeLink s = null;

        for(ShapeLink s1 : shapes){
            if(s1.getShape().equals(shape)){
                s = s1;
                break;
            }
        }
        return s;
    }

    public ShapeLink getShapeLink(Node shape){
        ShapeLink s = null;

        for(ShapeLink s1 : shapes){
            if(s1.getShapeFX().equals(shape)){
                s = s1;
                break;
            }
        }
        return s;
    }
    public Pane getRoot() {
        return root;
    }
    public void setRoot(Pane root) {
        this.root = root;
    }

    public ShapeLink searchFor(Shape shape)
    {
        for (ShapeLink shapeLink: shapes)
        {
            if (shapeLink.getShapeFX() == shape)
                return shapeLink;
        }
        return null;
    }

    public void addShapeLink(ShapeLink shapeLink)
    {
        shapes.add(shapeLink);
    }
}
