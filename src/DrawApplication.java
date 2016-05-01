/**
 * Created by Tarek Alqaddy on 4/29/2016.
 */

import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.Node;
import java.util.ArrayList;

public class DrawApplication {
    static DrawApplication instance = null;

    ShapeFactory shapeFactory = new ShapeFactory();
    int status;
    ArrayList<ShapeLink> shapes = new ArrayList<>();
    Group root = new Group();

    static DrawApplication  getInstance(){
        if(instance == null){
            return instance = new DrawApplication();
        }
        return instance;
    }

    void addShape(Node shape){
        ShapeLink sl = new ShapeLink();
        sl.setShapeFX(shape);
        switch (shape.getClass().getName()) {
            case "iCircle":
                sl.setType("iCircle");
                sl.setShape(shapeFactory.makeShape("circle", ((Circle) shape).getCenterX(), ((Circle) shape).getCenterY(),
                        ((Circle) shape).getRadius()));
                break;

            case "iEllipse":
                sl.setType("iEllipse");
                sl.setShape(shapeFactory.makeShape("ellipse", ((Ellipse) shape).getCenterX(), ((Ellipse) shape).getCenterY(),
                        ((Ellipse) shape).getRadiusX(), ((Ellipse) shape).getCenterY()));
                break;

            case "iRectangle":
                sl.setType("iRectangle");
                if (((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight()) {
                    sl.setShape(shapeFactory.makeShape("square", ((Rectangle) shape).getWidth(), ((Rectangle) shape).getHeight()));
                } else
                    sl.setShape(shapeFactory.makeShape("rectangle", ((Rectangle) shape).getWidth(), ((Rectangle) shape).getHeight()));
                break;

            case "Polygon":
                if (((Polygon) shape).getPoints().toArray().length == 6) {
                    sl.setType("iTriangle");
                    double[] xCoord = new double[3];
                    double[] yCoord = new double[3];
                    for (int i = 0; i < 6; i += 2) {
                        xCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i];
                        yCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i + 1];
                    }
                    sl.setShape(shapeFactory.makeShape("triangle", xCoord, yCoord));
                } else {
                    sl.setType("Polygon");
                    int length = ((Polygon) shape).getPoints().toArray().length;
                    double[] xCoord = new double[3];
                    double[] yCoord = new double[3];
                    for (int i = 0; i < length; i += 2) {
                        xCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i];
                        yCoord[i / 2] = (double) ((Polygon) shape).getPoints().toArray()[i + 1];
                    }
                    sl.setShape(shapeFactory.makeShape("polygon", xCoord, yCoord));
                }
                break;
            case "iLine":
                sl.setType("iLine");
                sl.setShape(shapeFactory.makeShape("line", ((Line) shape).getStartX(), ((Line) shape).getStartY(),
                        ((Line) shape).getEndX(), ((Line) shape).getEndY()));
                break;
        }

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

        switch (shape.getClass().getName()){
            case "iCircle":
                ((Circle) temp.getShapeFX()).setRadius(moreInfo[0]);
                temp.getShape().resizeShape(moreInfo);
                break;
            case "iEllipse":
                ((Ellipse) temp.getShapeFX()).setRadiusX(moreInfo[0]);
                ((Ellipse) temp.getShapeFX()).setRadiusY(moreInfo[1]);
                temp.getShape().resizeShape(moreInfo);
                break;
            case "iLine":
                temp.getShape().resizeShape(moreInfo);
                ((javafx.scene.shape.Line) temp.getShapeFX()).setEndX(((Line) temp.getShape()).getEndX());
                ((javafx.scene.shape.Line) temp.getShapeFX()).setEndX(((Line) temp.getShape()).getEndY());
                break;

            case "Polygon":
                if(((Polygon) temp.getShapeFX()).getPoints().toArray().length == 6){
                     //TODO for triangle
                }
                else{
                    ArrayList<Double> ar = new ArrayList<>();
                    for (int i=0;i<moreInfo.length;i++){
                        ar.add(moreInfo[i]);
                    }
                    ((Polygon) temp.getShapeFX()).getPoints().clear();
                    ((Polygon) temp.getShapeFX()).getPoints().addAll(ar);
                    temp.getShape().resizeShape(moreInfo);
                }
                break;

            case "iRectangle":
                if(((Rectangle) temp.getShapeFX()).getWidth() == ((Rectangle) temp.getShapeFX()).getHeight()) {
                    ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                    ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[0]);
                    temp.getShape().resizeShape(moreInfo);
                }
                else{
                    ((Rectangle) temp.getShapeFX()).setWidth(moreInfo[0]);
                    ((Rectangle) temp.getShapeFX()).setHeight(moreInfo[1]);
                    temp.getShape().resizeShape(moreInfo);
                }
                break;
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

        switch (shape.getClass().getName()) {

            case "iCircle":
                ((Circle) temp.getShapeFX()).setCenterX(moreInfo[0]);
                ((Circle) temp.getShapeFX()).setCenterY(moreInfo[1]);
                temp.getShape().moveShape(moreInfo);
                break;

            case "iEllipse":
                ((Ellipse) temp.getShapeFX()).setCenterX(moreInfo[0]);
                ((Ellipse) temp.getShapeFX()).setCenterY(moreInfo[1]);
                temp.getShape().moveShape(moreInfo);
                break;

            case "iLine":
                ((Line) temp.getShapeFX()).setStartX(moreInfo[0]);
                ((Line) temp.getShapeFX()).setStartY(moreInfo[1]);
                ((Line) temp.getShapeFX()).setEndX(moreInfo[2]);
                ((Line) temp.getShapeFX()).setEndY(moreInfo[3]);
                temp.getShape().moveShape(moreInfo);
                break;
            case "Polygon":
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
                break;

            case "iRectangle":
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

}
