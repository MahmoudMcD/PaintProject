/**
 * Created by mahmoud on 4/26/2016.
 */
public class iLine extends iShape {

    private double xStart, xEnd;
    private double yStart, yEnd;
    // Added
    private double strokeWidth;

    public iLine(double xStart, double yStart, double xEnd, double yEnd)
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
        double lineLength = newInfo[0];
        xEnd = xStart + lineLength*Math.cos(getRotationAngle());
        yEnd = yStart + lineLength*Math.sin(getRotationAngle());
    }

    @Override
    public void moveShape(double... newInfo) {
        xStart = newInfo[0];
        yStart = newInfo[1];
        xEnd = newInfo[2];
        yEnd = newInfo[3];
    }


    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public double getxStart() {
        return xStart;
    }

    public void setxStart(double xStart) {
        this.xStart = xStart;
    }

    public double getxEnd() {
        return xEnd;
    }

    public void setxEnd(double xEnd) {
        this.xEnd = xEnd;
    }

    public double getyStart() {
        return yStart;
    }

    public void setyStart(double yStart) {
        this.yStart = yStart;
    }

    public double getyEnd() {
        return yEnd;
    }

    public void setyEnd(double yEnd) {
        this.yEnd = yEnd;
    }
}
