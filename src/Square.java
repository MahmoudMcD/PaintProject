

/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class Square extends Polygons{
    private double sideLength;

    public Square(double length){
        super(4);
        this.sideLength = length;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }
}
