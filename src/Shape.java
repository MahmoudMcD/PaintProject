import javafx.scene.paint.Color;

/**
 * Created by mahmoud on 4/26/2016.
 */
public abstract class Shape implements Cloneable{

    private Color fillColor;
    private double rotationAngle;


    abstract public void drawShape();

    /* info for resize is sent in the order valid for each shape
     * Circle: resizeShape(radius)
     * Ellipse: resizeShape(xRadius, yRadius)
     * Line: resizeShape(newLength)
     * Triangle: resizeShape(firstSideLength, secondSideLength, thirdSideLength)
     * Rectangle: resizeShape(width, height)
     * Square: resizeShape(sideLength)
     */
    abstract public void resizeShape(double... newInfo);
    abstract public void moveShape(double... newCoordinates);

    public Shape copy(){
         Shape newShape = null;
         try{
             newShape = (Shape)super.clone();
         }
         catch (CloneNotSupportedException e){
             e.printStackTrace();
         }
         return newShape;
    }

    public Color getFillColor() { return fillColor; }
    public double getRotationAngle() {return rotationAngle;}

    public void setFillColor(Color color)
    {
        this.fillColor = color;
    }

    public void setRotationAngle(double rotationAngle)
    {
        this.rotationAngle = rotationAngle;
    }

}
