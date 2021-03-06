import javafx.beans.binding.StringBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by Tarek Alqaddy on 5/8/2016.
 */
public class EditWindow{
    private Node shape;
    private ShapeLink shapeLink;
    private DrawApplication drawApplication;

    BorderPane mainLayout;
    Stage window;
    HBox settingsLayout;
    Scene scene;
    Button editButton;
    HBox bottomLayout;
    Rectangle rect;

    SettingsHelper settingsHelper;

    public EditWindow(Node s,DrawApplication d){
        shape = s;
        drawApplication = d;
        shapeLink = d.getShapeLink(shape);
    }

    public void makeWindow(){

        settingsHelper = new SettingsHelper();
        Node[] settings;

        if(shape instanceof Circle) {
            settings = settingsHelper.getSettingsLayout(1);
            ((TextField) settings[1]).setText(String.valueOf(((Circle) shape).getRadius()));
            ((TextField) settings[3]).setText(((Circle) shape).getFill().toString());
        }

        else if(shape instanceof Ellipse) {
            settings = settingsHelper.getSettingsLayout(2);
            ((TextField) settings[1]).setText(String.valueOf(((Ellipse) shape).getRadiusX()));
            ((TextField) settings[3]).setText(String.valueOf(((Ellipse) shape).getRadiusY()));
            ((TextField) settings[5]).setText(String.valueOf(shape.getRotate()/*(Rotate) shape.getTransforms().get(0)).getAngle()*/));
            ((TextField) settings[7]).setText(((Ellipse) shape).getFill().toString());
        }

        else if(shape instanceof Rectangle){
            if(((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight()) {
                settings = settingsHelper.getSettingsLayout(4);
                ((TextField) settings[1]).setText(String.valueOf(((Rectangle) shape).getWidth()));
                ((TextField) settings[3]).setText(String.valueOf(shape.getRotate()));
                //((TextField) settings[3]).setText(String.valueOf(((Rotate) shape.getTransforms().get(0)).getAngle()));
                ((TextField) settings[5]).setText(((Rectangle) shape).getFill().toString());
            }
            else {
                settings = settingsHelper.getSettingsLayout(3);
                ((TextField) settings[1]).setText(String.valueOf(((Rectangle) shape).getWidth()));
                ((TextField) settings[3]).setText(String.valueOf(((Rectangle) shape).getHeight()));
                ((TextField) settings[5]).setText(String.valueOf(shape.getRotate()));
                //((TextField) settings[5]).setText(String.valueOf(((Rotate) shape.getTransforms().get(0)).getAngle()));
                ((TextField) settings[7]).setText(((Rectangle) shape).getFill().toString());
            }
        }

        else if(shape instanceof Polygon){
            if(((Polygon) shape).getPoints().toArray().length == 6) {
                settings = settingsHelper.getSettingsLayout(5);
                ((TextField) settings[1]).setText(((Polygon) shape).getFill().toString());
            }
            else {
                settings = settingsHelper.getSettingsLayout(6);
                ((TextField) settings[0]).setText(String.valueOf(((Polygon) shape).getPoints().toArray().length/2));
                ((TextField) settings[2]).setText(String.valueOf(MathHelper.calculateSideLength(((Polygon) shape).getPoints())));
                ((TextField) settings[4]).setText(String.valueOf(shape.getRotate()));
                //((TextField) settings[4]).setText(String.valueOf(((Rotate) shape.getTransforms().get(0)).getAngle()));
                ((TextField) settings[6]).setText(((Polygon) shape).getFill().toString());
            }
        }
        else if(shape instanceof Line) {
            settings = settingsHelper.getSettingsLayout(7);
            ((TextField) settings[3]).setText(String.valueOf(((Line) shape).getStrokeWidth()));
            ((TextField) settings[1]).setText(((Line) shape).getFill().toString());
        }
        else
            throw new RuntimeException(" Shape Not Found ! ");

        mainLayout = new BorderPane();

        settingsLayout = new HBox(20);
        settingsLayout.setPadding(new Insets(10));
        settingsLayout.getChildren().addAll(settings);
        for(Node n:settingsLayout.getChildren()){
            if(n instanceof TextField)
                ((TextField) n).setMaxWidth(70);
        }

        editButton = new Button("Edit");
        editButton.setOnAction(event -> resize());

        rect = new Rectangle(50,50,Color.GREEN);

        bottomLayout = new HBox(20);
        bottomLayout.setPadding(new Insets(30,10,0,190));
        bottomLayout.getChildren().addAll(editButton,new Label("or scroll here"),rect);
        bottomLayout.setOnScroll(new ScrollHandle(shapeLink,drawApplication));

        mainLayout.setTop(settingsLayout);
        mainLayout.setCenter(bottomLayout);

        scene = new Scene(mainLayout,650,160);


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(scene);
        window.show();
    }

    public void resize(){
        String[] ar = null,polyString = null;
        int flagPoly=0;
        Double[] vertices=null;

        String type;
        String[] oldValues; // for the history memento

        if(shape instanceof Circle){
            ar = settingsHelper.getSettings(1);
            type = "circle";
            oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, (Shape) shape);

        }
        else if(shape instanceof Ellipse){
            ar = settingsHelper.getSettings(2);
            type = "ellipse";
            oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, (Shape) shape);

        }
        else if(shape instanceof Rectangle){
            if(((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight()) {
                ar = settingsHelper.getSettings(4);
                type = "square";
                oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, (Shape) shape);
            }
            else {
                ar = settingsHelper.getSettings(3);
                type = "rectangle";
                oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, (Shape) shape);
            }

        }

        else if(shape instanceof Polygon){
            if(((Polygon) shape).getPoints().toArray().length == 6)
            {
                ar = settingsHelper.getSettings(5);
                type = "triangle";
                oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, (Shape) shape);
            }
            else {
                flagPoly =1;
                polyString = settingsHelper.getSettings(6);
                int No = Integer.parseInt(polyString[0]);
                double sideLength = Double.valueOf(polyString[1]);

                double centerX =MathHelper.centerFromVertices(((Polygon) shape).getPoints())[0];
                double centerY =MathHelper.centerFromVertices(((Polygon) shape).getPoints())[1];
                Double[] ard ;
                ard = MathHelper.calculatePolygonVertices(centerX,centerY,sideLength,No);

                vertices = new Double[2*No+1];

                //System.out.println(ard.length);
                //System.out.println(2*No+1);

                System.arraycopy(ard,0,vertices,0,ard.length);

                vertices[vertices.length-1] = Double.valueOf(polyString[2]);
                type = "polygon";
                oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, polyString, ((Shape) shape));

            }
        }
        else if(shape instanceof Line) {
            String[] ar1 = settingsHelper.getSettings(7);
            ar = new String[2];
            ar[0] = ar1[1];
            ar[1] = ar1[0];
            ((Line) shapeLink.getShapeFX()).setStroke(Paint.valueOf(ar[ar.length - 1]));
            type = "line";
            oldValues = drawApplication.getHistoryHandler().setOldValuesArray(type, ar, ((Shape) shape));

        }
        else
            throw new RuntimeException(" Shape Not Found ! ");

        if(flagPoly == 0) {
            double[] values = new double[ar.length - 1];
            for (int i = 0; i < ar.length - 1; i++)
                values[i] = Double.valueOf(ar[i]);

            drawApplication.resizeShape(shape, values);
            ((Shape) shapeLink.getShapeFX()).setFill(Paint.valueOf(ar[ar.length - 1]));
            shapeLink.getShape().setFillColor(Color.valueOf(ar[ar.length - 1]));
            drawApplication.getHistoryHandler().addMemento(type, shapeLink, ar, oldValues);

        }
        else {
            flagPoly =0;
            double[] verticesDouble = new double[vertices.length];
            for(int i=0;i<verticesDouble.length;i++){
                verticesDouble[i] = vertices[i];
            }
            drawApplication.resizeShape(shape,verticesDouble);
            ((Shape) shapeLink.getShapeFX()).setFill(Paint.valueOf(polyString[3]));
            shapeLink.getShape().setFillColor(Color.valueOf(polyString[3]));
            drawApplication.getHistoryHandler().addMemento(type, shapeLink, polyString, oldValues);

        }

    }

}