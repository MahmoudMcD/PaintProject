/**
 * Created by mahmoud on 4/26/2016.
 */
public class Line extends Shape {

    double xStart, xEnd;
    double yStart, yEnd;

    public Line(double xStart, double yStart, double xEnd, double yEnd)
    {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    @Override
    public void drawShape() {

    }

    @Override
    public void resizeShape(double... newInfo) {
        xStart = newInfo[0];
        yStart = newInfo[1];
        xEnd = newInfo[2];
        yEnd = newInfo[3];
    }

    @Override
    public void moveShape() {

    }

}
