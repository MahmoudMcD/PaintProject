/**
 * Created by Tarek Alqaddy on 4/27/2016.
 */
public class iRectangle extends iPolygons {
    double width;
    double height;
    public iRectangle(double width, double height){
        super(4);
        this.width = width;
        this.height = height;
    }

    @Override
    public void resizeShape(double... newInfo)
    {
        width = newInfo[0];
        height = newInfo[1];
        if(newInfo.length == 3)
            this.setRotationAngle(newInfo[2]);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
