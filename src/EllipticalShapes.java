/**
 * Created by mahmoud on 4/26/2016.
 */
public class EllipticalShapes extends iShape {
    private Double[] center;
    private double xRadius;
    private double yRadius;

    public EllipticalShapes(Double[] center, double xRadius, double yRadius)
    {
        this.center = center;
        this.xRadius = xRadius;
        this.yRadius = yRadius;
    }


    @Override
    public void drawShape() {
        //TODO
    }

    @Override
    public void resizeShape(double... newInfo) {
        xRadius = newInfo[0];
        yRadius = newInfo[1];
        if(newInfo.length == 3)
            this.setRotationAngle(newInfo[2]);
    }

    @Override
    public void moveShape(double... newCoordinates) {
        center[0] = newCoordinates[0];
        center[1] = newCoordinates[1];
    }

    public Double[] getCenter() {
        return center;
    }

    public void setCenter(Double[] center) {
        this.center = center;
    }

    public double getxRadius() {
        return xRadius;
    }

    public void setxRadius(double xRadius) {
        this.xRadius = xRadius;
    }

    public double getyRadius() {
        return yRadius;
    }

    public void setyRadius(double yRadius) {
        this.yRadius = yRadius;
    }
}
