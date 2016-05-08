/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iTriangle extends iPolygons {

    double[] sideLengths;

    public iTriangle(double[] sides){
        super(3);
        sideLengths = new double[3];
        sideLengths[0] = sides[0];
        sideLengths[1] = sides[1];
        sideLengths[2] = sides[2];
    }

    public void resizeShape(double... newInfo)
    {
        sideLengths[0] = newInfo[0];
        sideLengths[1] = newInfo[1];
        sideLengths[2] = newInfo[2];
    }

    public double[] getSideLengths() {
        return sideLengths;
    }

    public void setSideLength(double[] sideLength) {
        this.sideLengths = sideLength;
    }

    @Override
    public String toString(){
        return "sides: "+ sideLengths[0] + "\n" + sideLengths[1] + "\n" + sideLengths[2] + "\n";
    }
}
