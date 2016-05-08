import javafx.scene.paint.Color;

/**
 * Created by mahmoud on 4/26/2016.
 */
public abstract class iShape implements Cloneable{

    private Color fillColor;
    private double rotationAngle;


    abstract public void drawShape();

    /* info for resize is sent in the order valid for each shape
     * iCircle: resizeShape(radius)
     * iEllipse: resizeShape(xRadius, yRadius)
     * iLine: resizeShape(newLength)
     * iTriangle: resizeShape(firstSideLength, secondSideLength, thirdSideLength)
     * iRectangle: resizeShape(width, height)
     * iSquare: resizeShape(sideLength)
     */
    abstract public void resizeShape(double... newInfo);
    abstract public void moveShape(double... newCoordinates);

    public iShape copy(){
         iShape newShape = null;
         try{
             newShape = (iShape)super.clone();
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
