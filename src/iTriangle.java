/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iTriangle extends iPolygons {

    double[] sideLength;
    public iTriangle(){
        super(3);
    }

    public void resizeShape(double... newInfo)
    {
        sideLength[0] = newInfo[0];
        sideLength[1] = newInfo[1];
        sideLength[2] = newInfo[2];
    }

    public double[] getSideLength() {
        return sideLength;
    }

    public void setSideLength(double[] sideLength) {
        this.sideLength = sideLength;
    }
}
