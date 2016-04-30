/**
 * Created by mahmoud on 4/27/2016.
 */
public interface SimpleShapeFactory {

    /* Shape info varies from one shape type to another.
     * Parameter for each shape: (angle marked with * is optional and it's the rotation
     * angle with the positive x axis)
     * circle: makeShape("circle", centerX, centerY, radius)
     * Ellipse: makeShape("ellipse",centerX, centerY, radiusX, radiusY, angle*)
     * Line: makeShape("line", startX, startY, endX, endY, angle*)
     * Polygons: makeShape("polygon", xCoordinates[], yCoordinates[], angle*)
     * Square: makeShape("square", sideLength, angle*)
     * Rectangle: makeShape("rectangle", width, height, angle*)
     * Triangle: makeShape("triangle", xCoordinates[], yCoordinates[], angle*);
     */
    Shape makeShape(String shapeType, double... shapeInfo);
    Shape makeShape(String shapeType, double[] xCoordinates, double[] yCoordinates, double... otherInfo);
}
