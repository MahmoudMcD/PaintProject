import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
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

    SettingsHelper settingsHelper;

    public EditWindow(Node s,DrawApplication d){
        shape = s;
        drawApplication = d;
        shapeLink = d.getShapeLink(shape);
    }

    public void makeWindow(){

        settingsHelper = new SettingsHelper();
        Node[] settings;

        if(shape instanceof Circle)
            settings = settingsHelper.getSettingsLayout(1);

        else if(shape instanceof Ellipse)
            settings = settingsHelper.getSettingsLayout(2);

        else if(shape instanceof Rectangle){
            if(((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight())
                settings = settingsHelper.getSettingsLayout(4);
            else
                settings = settingsHelper.getSettingsLayout(3);
        }

        else if(shape instanceof Polygon){
            if(((Polygon) shape).getPoints().toArray().length == 6)
                settings = settingsHelper.getSettingsLayout(5);

            else
                settings = settingsHelper.getSettingsLayout(6);
        }

        else if(shape instanceof Line)
            settings = settingsHelper.getSettingsLayout(7);

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

        mainLayout.setTop(settingsLayout);
        mainLayout.setCenter(editButton);

        scene = new Scene(mainLayout,650,100);


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setScene(scene);
        window.showAndWait();
    }

    public void resize(){
        String[] ar;

        if(shape instanceof Circle){
            ar = settingsHelper.getSettings(1);
        }
        else if(shape instanceof Ellipse){
            ar = settingsHelper.getSettings(2);
        }
        else if(shape instanceof Rectangle){
            if(((Rectangle) shape).getWidth() == ((Rectangle) shape).getHeight())
                ar = settingsHelper.getSettings(4);
            else
                ar = settingsHelper.getSettings(3);
        }

        else if(shape instanceof Polygon){
            if(((Polygon) shape).getPoints().toArray().length == 6)
                ar = settingsHelper.getSettings(5);

            else
                ar = settingsHelper.getSettings(6);
        }

        else if(shape instanceof Line)
            ar = settingsHelper.getSettings(7);

        else
            throw new RuntimeException(" Shape Not Found ! ");

        double[] values = new double[ar.length-1];
        for(int i=0;i<ar.length-1;i++){
            values[i] = Double.valueOf(ar[i]);

        }

        drawApplication.resizeShape(shape,values);
        //fill color
    }

}
