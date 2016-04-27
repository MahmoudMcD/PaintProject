/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class Polygons extends Shape{
    private double[] xCoord,yCoord;
    private int noOfSides;

    public Polygons(int sides){
        this.noOfSides = sides;
        xCoord = new double[sides];
        yCoord = new double[sides];
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


    public double[] getxCoord() {
        return xCoord;
    }

    public void setxCoord(double[] xCoord) {
        this.xCoord = xCoord;
    }

    public double[] getyCoord() {
        return yCoord;
    }

    public void setyCoord(double[] yCoord) {
        this.yCoord = yCoord;
    }

    public int getNoOfSides() {
        return noOfSides;
    }

    public void setNoOfSides(int noOfSides) {
        this.noOfSides = noOfSides;
    }
}
