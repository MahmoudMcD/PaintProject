/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iTriangle extends iPolygons {

    double[] sideLength;

    public iTriangle(double[] sides){
        super(3);
        sideLength = new double[3];
        sideLength[0] = sides[0];
        sideLength[1] = sides[1];
        sideLength[2] = sides[2];
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

    @Override
    public String toString(){
        return "sides: "+ sideLength[0] + "\n" + sideLength[1] + "\n" + sideLength[2] + "\n";
    }
}
