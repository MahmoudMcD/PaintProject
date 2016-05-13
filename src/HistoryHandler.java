import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;


import java.util.ArrayList;

/**
 * Created by mahmoud on 5/9/2016.
 */
public class HistoryHandler {

    MementoLinkedList mementos;
    Memento prev, current, next;
    ArrayList<ShapeLink> recycleBin;
    DrawApplication drawApplication;

    public HistoryHandler(DrawApplication drawApplication)
    {

        this.drawApplication = drawApplication;
        mementos = new MementoLinkedList();
        recycleBin = new ArrayList<>();
        prev = mementos.getSentinel();
        current = prev;
        next = prev;
    }

    public void addMemento(String message, ShapeLink actionShapeLink,
                           int type, String... moreInfo)
    {
        if (next != mementos.getSentinel())
            mementos.deleteUntil(current);

        next = mementos.getSentinel();

        prev = current;
        current = mementos.addMemento(message, actionShapeLink,  type,
                moreInfo);
        System.out.println(current.getMoreInfo().toString());
        drawApplication.getHistoryWindow().refreshHistoryTableView();
    }


    public void addMemento(String type, ShapeLink actionShapeLink, String[] newValues, String[] oldValues)
    {
        String[] mementoValue = new String[(newValues.length * 2) + 1];
        mementoValue[0] = type;
        for (int i = 0, m = 1; i < newValues.length; i++, m+=2)
        {
            mementoValue[m] = oldValues[i];
            mementoValue[m + 1] = newValues[i];
        }
        addMemento("A "+type+" was edited", actionShapeLink, 2, mementoValue);
    }


    public void undo()
    {
        /* if current is sentinel that means that there's no more actions
         * to undo
         * else set the next to the current and set the current to the
         * current.getNext() to move back in the chain of actions a step
         * if current is now the sentinel set prev also to the sentinel
         * else set the prev to current.getNext() to move prev a step behind
         * the current action
         */

        if (current != mementos.getSentinel())
        {
            next = current;
            current = current.getNext();

            if (current == mementos.getSentinel())
                prev = mementos.getSentinel();
            else
                prev = current.getNext();

            undoAction(next);
            System.out.println("---------");
            mementos.printList();
            drawApplication.getHistoryWindow().refreshHistoryTableView();
        }

    }

    public void redo()
    {
        /* if next is sentinel that means that there's no more actions to redo
         * else set the current to the next and the next to next.getPrev() to move
         * back in the chain of actions then set the prev to current.getNext()
         * redo current to redo the action
         */
        if (next != mementos.getSentinel())
        {
            current = next;
            next = next.getPrev();
            prev = current.getNext();

            redoAction(current);
            System.out.println(" - . - . - .");
            mementos.printList();
            drawApplication.getHistoryWindow().refreshHistoryTableView();
        }
    }

    private void undoAction(Memento action)
    {
        switch (action.getType())
        {
            case 0:
                drawApplication.deleteWithoutMemento((Shape) action.getActionShapeLink().getShapeFX());
                break;
            case 1:
                drawApplication.drawShape(action.getActionShapeLink());
                break;
            // This case means that the action was edit action
            case 2:
                applyChanges(1, action);
                break;
            case 3:
                applyMovement(1, action);
        }
    }

    private void redoAction(Memento action)
    {
        switch (action.getType())
        {
            case 0:
                drawApplication.drawShape(action.getActionShapeLink());
                break;
            case 1:
                drawApplication.deleteWithoutMemento((Shape) action.getActionShapeLink().getShapeFX());
                break;
            case 2:
                applyChanges(2, action);
                break;
            case 3:
                applyMovement(2, action);
        }
    }

    private void applyChanges(int start, Memento action)
    {
        Shape shape = (Shape) action.getActionShapeLink().getShapeFX();
        iShape ishape = action.getActionShapeLink().getShape();
        String[] info = action.getMoreInfo();
        switch (info[0].toLowerCase())
        {
            case "circle":
                if (info.length != 5)
                    throw new RuntimeException("Invalid No. of values");
                ((Circle) shape).setRadius(Double.valueOf(info[start]));
                shape.setFill(Paint.valueOf(info[start+2]));
                (ishape).resizeShape(Double.valueOf(info[start]));
                ishape.setFillColor(Color.valueOf(info[start + 2]));
                break;
            case "ellipse":
                if (info.length != 9)
                    throw new RuntimeException("Invalid No. of values");
                ((Ellipse) shape).setRadiusX(Double.valueOf(info[start]));
                ((Ellipse) shape).setRadiusY(Double.valueOf(info[start + 2]));
                shape.setRotate(Double.valueOf(info[start + 4]));
                shape.setFill(Paint.valueOf(info[start + 6]));

                ishape.resizeShape(Double.valueOf(info[start]), Double.valueOf(info[start + 2]));
                ishape.setRotationAngle(Double.valueOf(info[start + 4]));
                ishape.setFillColor(Color.valueOf(info[start + 6]));
                break;
            case "rectangle":
                if (info.length != 9)
                    throw new RuntimeException("Invalid No. of values");
                ((Rectangle) shape).setWidth(Double.valueOf(info[start]));
                ((Rectangle) shape).setHeight(Double.valueOf(info[start+2]));
                shape.setRotate(Double.valueOf(info[start + 4]));
                shape.setFill(Paint.valueOf(info[start + 6]));

                ishape.resizeShape(Double.valueOf(info[start]), Double.valueOf(info[start+2]));
                ishape.setRotationAngle(Double.valueOf(info[start + 4]));
                ishape.setFillColor(Color.valueOf(info[start + 6]));
                break;
            case "square":
                if (info.length != 7)
                    throw new RuntimeException("Invalid No. of values");
                ((Rectangle) shape).setHeight(Double.valueOf(info[start]));
                ((Rectangle) shape).setWidth(Double.valueOf(info[start]));

                shape.setRotate(Double.valueOf(info[start + 2]));
                shape.setFill(Paint.valueOf(info[start + 4]));

                ishape.resizeShape(Double.valueOf(info[start]), Double.valueOf(info[start]));
                ishape.setRotationAngle(Double.valueOf(info[start + 2]));
                ishape.setFillColor(Color.valueOf(info[start + 4]));
                break;
            case "triangle":
                if (info.length != 3)
                    throw new RuntimeException("Invalud No. of Values");
                shape.setFill(Paint.valueOf(info[start]));
                ishape.setFillColor(Color.valueOf(info[start]));
                break;
            case "line":
                if (info.length != 5)
                    throw new RuntimeException("Invalid No. of values");
                shape.setStrokeWidth(Double.valueOf(info[start]));
                shape.setFill(Paint.valueOf(info[start + 2]));
                ishape.resizeShape(Double.valueOf(info[start]));
                ishape.setFillColor(Color.valueOf(info[start + 2]));
                break;
            case "polygon":
                if (info.length != 9)
                    throw new RuntimeException("InValid No. of values");
                double point1X = ((Polygon) shape).getPoints().get(0);
                double point1Y = ((Polygon) shape).getPoints().get(1);
                double noSidesDouble = Double.parseDouble(info[start]);
                int noOfSide = (int) noSidesDouble;
                ((Polygon) shape).getPoints()
                        .addAll(MathHelper.calculatePolygonVertices(point1X, point1Y,
                                Double.valueOf(info[start + 2]), noOfSide));
                shape.setRotate(Double.valueOf(info[start + 4]));
                shape.setFill(Paint.valueOf(info[start + 6]));

                ((iPolygons) ishape).setSideLength(Double.valueOf(info[start + 2]));
                ishape.setRotationAngle(Double.valueOf(info[start + 4]));
                ishape.setFillColor(Color.valueOf(info[start + 6]));
                break;
        }
    }


    public String[] setOldValuesArray(String type, String[] newValues, Shape shape)
    {
        // TODO return when the rotation angle is here
        String[] oldValues = new String[newValues.length + 1];
        //String[] oldValues = new String[newValues.length];
        switch (type)
        {
            case "circle":
                double circleRadius = ((Circle) shape).getRadius();
                Paint circleColor = ((Circle) shape).getFill();
                oldValues[0] = String.valueOf(circleRadius);
                oldValues[1] = String.valueOf(circleColor);
                break;
            case "ellipse":
                double ellipseXRadius = ((Ellipse) shape).getRadiusX();
                double ellipseYRadius = ((Ellipse) shape).getRadiusY();
                double ellipseRotationAngle = shape.getRotate();
                Paint ellipseColor = shape.getFill();

                oldValues[0] = String.valueOf(ellipseXRadius);
                oldValues[1] = String.valueOf(ellipseYRadius);
                oldValues[2] = String.valueOf(ellipseRotationAngle);
                oldValues[3] = String.valueOf(ellipseColor);
                break;
            case "square":
                double squareSideLength = ((Rectangle) shape).getWidth();
                double squareRotationAngle = shape.getRotate();
                Paint squareColor = shape.getFill();
                oldValues[0] = String.valueOf(squareSideLength);
                oldValues[1] = String.valueOf(squareRotationAngle);
                oldValues[2] = String.valueOf(squareColor);
                break;
            case "rectangle":
                double rectangleWidth = ((Rectangle) shape).getWidth();
                double rectangleHeight = ((Rectangle) shape).getHeight();
                double rectangleRotationAngle = shape.getRotate();
                Paint rectangleColor = shape.getFill();
                oldValues[0] = String.valueOf(rectangleWidth);
                oldValues[1] = String.valueOf(rectangleHeight);
                oldValues[2] = String.valueOf(rectangleRotationAngle);
                oldValues[3] = String.valueOf(rectangleColor);
                break;
            case "triangle":
                Paint triangleColor = shape.getFill();
                oldValues[0] = String.valueOf(triangleColor);
                break;
            case "polygon":
                double polyNoOfSides = ((Polygon) shape).getPoints().size();
                double point1X, point2X, point1Y, point2Y;
                point1X = ((Polygon) shape).getPoints().get(0);
                point1Y = ((Polygon) shape).getPoints().get(1);
                point2X = ((Polygon) shape).getPoints().get(2);
                point2Y = ((Polygon) shape).getPoints().get(3);
                double sideLength = Math.sqrt(Math.pow((point2X - point1X), 2) +
                    Math.pow((point2Y - point1Y), 2));
                double rotationAngle = shape.getRotate();
                Paint polyColor = shape.getFill();
                oldValues[0] = String.valueOf(polyNoOfSides);
                oldValues[1] = String.valueOf(sideLength);
                oldValues[2] = String.valueOf(rotationAngle);
                oldValues[3] = String.valueOf(polyColor);

                break;
            case "line":
                double lineStrokeWidth = ((Line) shape).getStrokeWidth();
                Paint lineColor = shape.getFill();
                oldValues[0] = String.valueOf(lineStrokeWidth);
                oldValues[1] = String.valueOf(lineColor);
        }

        return oldValues;
    }

    private void applyMovement(int start, Memento action)
    {
        Shape shape = (Shape) action.getActionShapeLink().getShapeFX();
        iShape ishape = action.getActionShapeLink().getShape();
        String[] info = action.getMoreInfo();

        if (info.length != 5)
            throw new RuntimeException("Invalid No. of Values");

        switch (info[0].toLowerCase())
        {
            case "circle":

                ((Circle) shape).setCenterX(Double.valueOf(info[start]));
                ((Circle) shape).setCenterY(Double.valueOf(info[start + 2]));
                ((iCircle) ishape).setCenter(new Double[]{
                        Double.valueOf(info[start]),
                        Double.valueOf(info[start + 2])
                });
                break;
            case "ellipse":

                ((Ellipse) shape).setCenterX(Double.valueOf(info[start]));
                ((Ellipse) shape).setCenterY(Double.valueOf(info[start + 2]));
                ((iEllipse) ishape).setCenter(new Double[]{
                        Double.valueOf(info[start]),
                        Double.valueOf(info[start + 2])
                });
                break;

            case "square":
            case "rectangle":
                ((Rectangle) shape).setX(Double.valueOf(info[start]));
                ((Rectangle) shape).setY(Double.valueOf(info[start + 2]));
                //TODO
                break;

            case "line":
                double xDiff, yDiff;
                if (start == 1)
                {
                    xDiff = Double.valueOf(info[1]) - Double.valueOf(info[2]);
                    yDiff = Double.valueOf(info[3]) - Double.valueOf(info[4]);
                }
                else
                {
                    xDiff = Double.valueOf(info[2]) - Double.valueOf(info[1]);
                    yDiff = Double.valueOf(info[4]) - Double.valueOf(info[3]);
                }

                Line tempLine = (Line) action.getActionShapeLink().getShapeFX();
                tempLine.setStartX(tempLine.getStartX() + xDiff);
                tempLine.setEndX(tempLine.getEndX() + xDiff);
                tempLine.setStartY(tempLine.getStartY() + yDiff);
                tempLine.setEndY(tempLine.getEndY() + yDiff);

                iLine tempILine = (iLine) action.getActionShapeLink().getShape();
                tempILine.setxStart(tempILine.getxStart() + xDiff);
                tempILine.setxEnd(tempILine.getxEnd() + xDiff);
                tempILine.setyStart(tempILine.getyStart() + yDiff);
                tempILine.setyEnd(tempILine.getyEnd() + yDiff);
                break;

            case "polygon":
                double sideLength = ((iPolygons) ishape).getSideLength();
                double[] center = MathHelper.centerFromVertices(((Polygon) shape).getPoints());

                double yDiffPolygon, xDiffPolygon;

                if (start == 1)
                {
                    xDiffPolygon = Double.valueOf(info[1]) - Double.valueOf(info[2]);
                    yDiffPolygon = Double.valueOf(info[3]) - Double.valueOf(info[4]);
                }
                else
                {
                    xDiffPolygon = Double.valueOf(info[2]) - Double.valueOf(info[1]);
                    yDiffPolygon = Double.valueOf(info[4]) - Double.valueOf(info[3]);
                }


                center[0] += xDiffPolygon;
                center[1] += yDiffPolygon;

                int noOfSides = ((Polygon) shape).getPoints().size() / 2;
                ((Polygon) shape).getPoints().clear();
                ((Polygon) shape).getPoints().addAll(MathHelper.calculatePolygonVertices(center[0], center[1],
                        sideLength, noOfSides));

                //TODO

                break;
        }
    }

    public ObservableList<Memento> returnObservableList()
    {
        return mementos.returnObservableList();
    }

    public Memento getCurrent()
    {
        return  current;
    }

    public void returnUntil(Memento toBeCurrent)
    {
        while (next != mementos.getSentinel())
            redo();

        while (current != toBeCurrent)
            undo();
    }

}
