import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

/**
 * Created by mahmoud on 5/8/2016.
 */
public class MathHelper {

    public static double calculateRadius(double sideLength, int numberOfSides)
    {
        return (sideLength / Math.sqrt(2 - 2 * Math.cos(360 / numberOfSides)));
    }

    public static Double[] calculateStarVertices(double centerX, double centerY, double sideLength, int numberOfSides)
    {
        double radius = calculateRadius(sideLength, numberOfSides);

        Double[] vertices = new Double[numberOfSides * 2];
        for (int i = 0; i < numberOfSides * 2; i += 2 )
        {
            vertices[i] = centerX + ( radius * Math.cos(2 * i * (Math.PI / numberOfSides)));
            vertices[i + 1] = centerY + ( radius * Math.sin(2 * i * (Math.PI / numberOfSides)));
        }

        return vertices;
    }

    public static Double[] calculatePolygonVertices(double centerX,double centerY, double sideLength, int numberOfSides)
    {
        double radius = calculateRadius(sideLength, numberOfSides);

        Double[] vertices = new Double[numberOfSides * 2];
        for (int i = 0, m = 0; i < numberOfSides; i++ )
        {
            vertices[m] = centerX + ( radius * Math.cos(2 * i * (Math.PI / numberOfSides)));
            vertices[m + 1] = centerY + ( radius * Math.sin(2 * i * (Math.PI / numberOfSides)));
            m += 2;
        }

        return vertices;
    }


    public static double[] centerFromVertices(ObservableList<Double>  vertices){
        double sumX =0,sumY=0;
        for(int i=0;i<vertices.size();i+=2){
            sumX+= vertices.get(i);
            sumY+= vertices.get(i+1);
        }
        return new double[]{sumX/(vertices.size()/2),sumY/(vertices.size()/2)};
    }
    public static double[] centerOfRectangle(Rectangle shape) {
        return new double[]{shape.getX()+(shape.getWidth()/2),shape.getY()+(shape.getHeight()/2)};
    }

    public static double calculateSideLength(ObservableList<Double> vertices){
        return Math.sqrt(Math.pow(vertices.get(2)-vertices.get(0),2)+Math.pow(vertices.get(3)-vertices.get(1),2));
    }

    public static double calculateSideLength(double[] vertices){
        return Math.sqrt(Math.pow(vertices[2]-vertices[0],2)+Math.pow(vertices[3]-vertices[1],2));
    }

    public static double calculateSideLength(double[] xCoord,double[] yCoord){
        return Math.sqrt(Math.pow(xCoord[1]-xCoord[0],2)+Math.pow(yCoord[1]-yCoord[0],2));
    }


}
