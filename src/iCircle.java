/**
 * Created by mahmoud on 4/26/2016.
 */
public class iCircle extends EllipticalShapes {

    public iCircle(Double[] center, double radius)
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

    @Override
    public String toString(){
        return "radius: " + super.getxRadius() + " Center: " + super.getCenter()[0] + ", " +super.getCenter()[1];
    }
}
