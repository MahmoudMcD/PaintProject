/**
 * Created by mahmoud on 4/26/2016.
 */
public class EllipticalShapes extends Shape {
    Double[] center;
    double xRadius;
    double yRadius;

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
    }

    @Override
    public void moveShape(double... newCoordinates) {
        center[0] = newCoordinates[0];
        center[1] = newCoordinates[1];
    }

}
