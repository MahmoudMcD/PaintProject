/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iPolygons extends iShape {
    private double[] xCoord,yCoord;
    private int noOfSides;
    private double sideLength;

    public iPolygons(int sides){
        this.noOfSides = sides;
        xCoord = new double[sides];
        yCoord = new double[sides];
    }

    @Override
    public void drawShape() {
        //TODO
    }

    //TODO Fix this -> howa ana 3adltha bs check tany w law s7 8yar b2a almove bnafs alconcept
    @Override
    public void resizeShape(double... newInfo) {
        if (newInfo.length < noOfSides*2)
             throw new RuntimeException("Wrong Coordinates number "); //TODO
        //in case of sending the last element in the array as a rotation angle copy the coords into a new array.
        if(newInfo.length %2 != 0){
            double[] coords = new double[newInfo.length-1];
            System.arraycopy(newInfo,0,coords,0,newInfo.length-2);
            this.setRotationAngle(newInfo[newInfo.length-1]);

            System.out.println(this.getRotationAngle());
            for(int i = 0; i < noOfSides; i+=2)
            {
                xCoord[i/2] = coords[i];
                yCoord[i/2] = coords[i+1];
            }
        }
        else{
            for(int i = 0; i < noOfSides; i+=2)
            {
                xCoord[i/2] = newInfo[i];
                yCoord[i/2] = newInfo[i+1];
            }
        }

        this.sideLength = MathHelper.calculateSideLength(xCoord,yCoord);
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

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

}
