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
    public void resizeShape() {
        //TODO
    }

    @Override
    public void moveShape() {
        //TODO
    }

}
