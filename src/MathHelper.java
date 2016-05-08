/**
 * Created by mahmoud on 5/8/2016.
 */
public class MathHelper {

    public static double calculateRadius(double sideLength, int numberOfSides)
    {
        return (sideLength / Math.sqrt(2 - 2 * Math.cos(360 / numberOfSides)));
    }

    public static Double[] calculateStarVertices(double centerX, double centerY, double sideLength, int numberOfSides)
    {
        double radius = calculateRadius(sideLength, numberOfSides);

        Double[] vertices = new Double[numberOfSides * 2];
        for (int i = 0; i < numberOfSides * 2; i += 2 )
        {
            vertices[i] = centerX + ( radius * Math.cos(2 * i * (Math.PI / numberOfSides)));
            vertices[i + 1] = centerY + ( radius * Math.sin(2 * i * (Math.PI / numberOfSides)));
        }

        return vertices;
    }

    public static Double[] calculatePolygonVertices(double centerX,double centerY, double sideLength, int numberOfSides)
    {
        double radius = calculateRadius(sideLength, numberOfSides);

        Double[] vertices = new Double[numberOfSides * 2];
        for (int i = 0, m = 0; i < numberOfSides; i++ )
        {
            vertices[m] = centerX + ( radius * Math.cos(2 * i * (Math.PI / numberOfSides)));
            vertices[m + 1] = centerY + ( radius * Math.sin(2 * i * (Math.PI / numberOfSides)));
            m += 2;
        }

        return vertices;
    }

}
