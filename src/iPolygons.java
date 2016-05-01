/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iPolygons extends iShape {
    private double[] xCoord,yCoord;
    private int noOfSides;

    public iPolygons(int sides){
        this.noOfSides = sides;
        xCoord = new double[sides];
        yCoord = new double[sides];
    }

    @Override
    public void drawShape() {
        //TODO
    }

    //TODO Fix this
    @Override
    public void resizeShape(double... newInfo) {
        if (newInfo.length != noOfSides*2)
            throw new RuntimeException("Wrong Coordinates number "); //TODO

        for(int i = 0; i < noOfSides; i++)
        {
            xCoord[i] = newInfo[i];
            yCoord[i] = newInfo[i+1];
        }
    }

    @Override
    public void moveShape(double... newInfo) {
        if (newInfo.length != noOfSides*2)
            throw new RuntimeException("Wrong Coordinates number "); //TODO

        for(int i = 0; i < noOfSides; i++)
        {
            xCoord[i] = newInfo[i];
            yCoord[i] = newInfo[i+1];
        }
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
