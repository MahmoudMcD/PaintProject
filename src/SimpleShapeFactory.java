/**
 * Created by mahmoud on 4/27/2016.
 */
public interface SimpleShapeFactory {

    /* iShape info varies from one shape type to another.
     * Parameter for each shape: (angle marked with * is optional and it's the rotation
     * angle with the positive x axis)
     * circle: makeShape("circle", centerX, centerY, radius)
     * iEllipse: makeShape("ellipse",centerX, centerY, radiusX, radiusY, angle*)
     * iLine: makeShape("line", startX, startY, endX, endY, angle*)
     * iPolygons: makeShape("polygon", xCoordinates[], yCoordinates[], angle*)
     * iSquare: makeShape("square", sideLength, angle*)
     * iRectangle: makeShape("rectangle", width, height, angle*)
     * iTriangle: makeShape("triangle", xCoordinates[], yCoordinates[], angle*);
     */
    iShape makeShape(String shapeType, double... shapeInfo);
    iShape makeShape(String shapeType, double[] xCoordinates, double[] yCoordinates, double... otherInfo);
}
