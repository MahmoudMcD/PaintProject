/**
 * Created by mahmoud on 4/26/2016.
 */
public class Circle extends EllipticalShapes {

    public Circle(Double[] center, double radius)
    {
        super(center, radius, radius);
    }

    @Override
    public void drawShape()
    {
        //TODO
    }

    @Override
    public void resizeShape(double... newInfo)
    {
        super.resizeShape(newInfo[0], newInfo[0]);
    }
}
