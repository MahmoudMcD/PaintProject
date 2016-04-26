import javafx.scene.paint.Color;

/**
 * Created by mahmo on 4/26/2016.
 */
public abstract class Shape {

    private Color fillColor;
    private double rotationAngle;


    abstract public void drawShape();
    abstract public void resizeShape();
    abstract public void moveShape();
    abstract public void copy();

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
