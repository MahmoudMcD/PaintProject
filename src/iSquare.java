

/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iSquare extends iPolygons {
    private double sideLength;

    public iSquare(double length){
        super(4);
        this.sideLength = length;
    }


    @Override
    public void resizeShape(double... newInfo)
    {
        sideLength = newInfo[0];
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

}
