/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class Triangle extends Polygons{
    double[] sideLength;
    public Triangle(){
        super(3);
    }

    public double[] getSideLength() {
        return sideLength;
    }

    public void setSideLength(double[] sideLength) {
        this.sideLength = sideLength;
    }
}
