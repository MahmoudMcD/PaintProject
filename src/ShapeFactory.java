/**
 * Created by mahmoud on 4/27/2016.
 */
public class ShapeFactory implements SimpleShapeFactory {

    public iShape makeShape(String shapeType, double... shapeInfo)
    {
        switch (shapeType)
        {
            case "circle":
                if (shapeInfo.length < 3)
                    throw new RuntimeException("UnSufficient information");
                System.out.println(shapeInfo.length);
                return makeCircle(shapeInfo[0], shapeInfo[1], shapeInfo[2]);
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
    public iShape makeShape(String shapeType, double[] xCoordinates, double[] yCoordinates, double... otherInfo)
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

    private iCircle makeCircle(double centerX, double centerY, double radius)
    {
        return new iCircle(new Double[]{centerX, centerY}, radius);
    }

    private iEllipse makeEllipse(double... ellipseInfo)
    {
        iEllipse newEllipse = new iEllipse(new Double[]{ellipseInfo[0], ellipseInfo[1]}, ellipseInfo[2],
                ellipseInfo[3]);
        if (ellipseInfo.length == 5)
            newEllipse.setRotationAngle(ellipseInfo[4]);
        return newEllipse;
    }

    private iLine makeLine(double... lineInfo)
    {
        iLine newLine = new iLine(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3]);
        if (lineInfo.length == 5)
            newLine.setRotationAngle(lineInfo[4]);
        return newLine;
    }

    private iSquare makeSquare(double... squareInfo)
    {
        iSquare newSquare = new iSquare(squareInfo[0]);
        if (squareInfo.length == 2)
            newSquare.setRotationAngle(squareInfo[1]);
        return newSquare;
    }

    private iRectangle makeRectangle(double... rectangleInfo)
    {
        iRectangle newRectangle = new iRectangle(rectangleInfo[0], rectangleInfo[1]);
        if (rectangleInfo.length == 3)
            newRectangle.setRotationAngle(rectangleInfo[2]);
        return newRectangle;
    }

    private iPolygons makePolygon(double[] xCoordinates, double[] yCoordinates, double... otherInfo)
    {
        iPolygons newPolygon =  new iPolygons(xCoordinates.length);
        newPolygon.setxCoord(xCoordinates);
        newPolygon.setyCoord(yCoordinates);
        if(otherInfo.length == 1)
            newPolygon.setRotationAngle(otherInfo[0]);
        return newPolygon;
    }

    private iTriangle makeTriangle(double[] xCoordinates, double[] yCoordinates, double... otherInfo)
    {
        double sides[] = new double[3];
        sides[0] = Math.sqrt(Math.pow(xCoordinates[0]-xCoordinates[1],2)+ Math.pow(yCoordinates[0]-yCoordinates[1],2));
        sides[1] = Math.sqrt(Math.pow(xCoordinates[1]-xCoordinates[2],2)+ Math.pow(yCoordinates[1]-yCoordinates[2],2));
        sides[2] = Math.sqrt(Math.pow(xCoordinates[2]-xCoordinates[0],2)+ Math.pow(yCoordinates[2]-yCoordinates[0],2));
        System.out.println(sides[0]);
        iTriangle newTriangle = new iTriangle(sides);
        newTriangle.setxCoord(xCoordinates);
        newTriangle.setyCoord(yCoordinates);

        if  (otherInfo.length == 1)
            newTriangle.setRotationAngle(otherInfo[0]);
        return newTriangle;
    }
}
