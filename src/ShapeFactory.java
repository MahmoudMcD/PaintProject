/**
 * Created by mahmoud on 4/27/2016.
 */
public class ShapeFactory implements SimpleShapeFactory {

    public Shape makeShape(String shapeType, double... shapeInfo)
    {
        switch (shapeType)
        {
            case "circle":
                if (shapeInfo.length < 3)
                    throw new RuntimeException("UnSufficient information");
                return makeCircle(shapeInfo[0], shapeInfo[1], shapeInfo[3]);
            case "ellipse":
                if (shapeInfo.length < 4)
                    throw  new RuntimeException("UnSufficient information");
                return makeEllipse(shapeInfo);
            case "line":
                if (shapeInfo.length < 4)
                    throw  new RuntimeException("UnSufficient information");
                return makeLine(shapeInfo);
            case "square":
                if (shapeInfo.length < 1)
                    throw new RuntimeException("UnSufficient information");
                return makeSquare(shapeInfo);
            case "rectangle":
                if (shapeInfo.length < 2)
                    throw new RuntimeException("UnSufficient information");
                return makeRectangle(shapeInfo);
        }

        return null;
    }

    @Override
    public Shape makeShape(String shapeType, double[] xCoordinates, double[] yCoordinates, double... otherInfo)
    {
        switch (shapeType)
        {
            case "polygon":
                return makePolygon(xCoordinates, yCoordinates, otherInfo);
            case "triangle":
                return makeTriangle(xCoordinates, yCoordinates, otherInfo);
        }
        return null;
    }

    private Circle makeCircle(double centerX, double centerY, double radius)
    {
        return new Circle(new Double[]{centerX, centerY}, radius);
    }

    private Ellipse makeEllipse(double... ellipseInfo)
    {
        Ellipse newEllipse = new Ellipse(new Double[]{ellipseInfo[0], ellipseInfo[1]}, ellipseInfo[2],
                ellipseInfo[3]);
        if (ellipseInfo.length == 5)
            newEllipse.setRotationAngle(ellipseInfo[4]);
        return newEllipse;
    }

    private Line makeLine(double... lineInfo)
    {
        Line newLine = new Line(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3]);
        if (lineInfo.length == 5)
            newLine.setRotationAngle(lineInfo[4]);
        return newLine;
    }

    private Square makeSquare(double... squareInfo)
    {
        Square newSquare = new Square(squareInfo[0]);
        if (squareInfo.length == 2)
            newSquare.setRotationAngle(squareInfo[1]);
        return newSquare;
    }

    private Rectangle makeRectangle(double... rectangleInfo)
    {
        Rectangle newRectangle = new Rectangle(rectangleInfo[0], rectangleInfo[1]);
        if (rectangleInfo.length == 3)
            newRectangle.setRotationAngle(rectangleInfo[2]);
        return newRectangle;
    }

    private Polygons makePolygon(double[] xCoordinates, double[] yCoordinates, double... otherInfo)
    {
        Polygons newPolygon =  new Polygons(xCoordinates.length);
        newPolygon.setxCoord(xCoordinates);
        newPolygon.setyCoord(yCoordinates);
        if(otherInfo.length == 1)
            newPolygon.setRotationAngle(otherInfo[0]);
        return newPolygon;
    }

    private Triangle makeTriangle(double[] xCoordinates, double[] yCoordinates, double... otherInfo)
    {
        Triangle newTriangle = new Triangle();
        newTriangle.setxCoord(xCoordinates);
        newTriangle.setyCoord(yCoordinates);
        if  (otherInfo.length == 1)
            newTriangle.setRotationAngle(otherInfo[0]);
        return newTriangle;
    }
}
