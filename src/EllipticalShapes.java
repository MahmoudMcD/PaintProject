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
        center[0] = newInfo[0];
        center[1] = newInfo[1];
        xRadius = newInfo[2];
        yRadius = newInfo[3];
    }

    @Override
    public void moveShape() {
        //TODO
    }

}
