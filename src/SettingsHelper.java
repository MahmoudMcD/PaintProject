import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

/**
 * Created by mahmoud on 5/2/2016.
 */
public class SettingsHelper {

    // elements for new circle draw
    private Label radiusLabel;
    private Label colorCodeLabel;
    private TextField radiusField;
    private TextField colorCodeField;
    private TextField circleLineStrokeField;


    // elements for new ellipse draw
    private Label yRadiusLabel, xRadiusLabel, ellipseColorCodeLabel;
    private TextField yRadiusField, xRadiusField,ellipsseColorCodeField;

    // elements for the new rectangle
    private Label rectangleWidthLabel, rectangleHeightLabel, rectangleColorCodeLabel;
    private TextField rectangleWidthField, rectangleHeightField, rectangleColorCodeField;

    // elements for the new square
    private Label squareSideLengthLabel, squareColorLabel;
    private TextField squareSideLengthField, squareColorField;

    // elements for the new triangle
    private Label triangleGuideLabel;
    private TextField triangleColorCodeField;

    // elements for the new line
    private Label lineStrokeWidthLabel;
    private TextField lineColorCodeField, lineStrokeWidthField;

    // elements for new polygon
    private ChoiceBox<Integer> noOfSidesChoiceBox;
    private TextField polygonColorCodeField;

    public SettingsHelper()
    {
        // setting up the new circle settings
        radiusLabel = new Label("Radius: ");
        colorCodeLabel = new Label("Color Code: ");
        radiusField = new TextField("10");
        colorCodeField = new TextField("#000000");


        // setting up the new ellipse settings
        yRadiusLabel = new Label("Y-Radius: ");
        xRadiusLabel = new Label("X-Radius: ");
        ellipseColorCodeLabel = new Label("Color/Color Code: ");

        yRadiusField = new TextField("20");
        xRadiusField = new TextField("10");
        ellipsseColorCodeField = new TextField("Black");


        // setting up the new rectangle settings
        rectangleWidthLabel = new Label("Width: ");
        rectangleHeightLabel = new Label("Height: ");
        rectangleColorCodeLabel = new Label("Color/Color Code: ");


        rectangleWidthField = new TextField("20");
        rectangleHeightField = new TextField("10");
        rectangleColorCodeField = new TextField("Black");

        // setting up the new square settings
        squareSideLengthLabel = new Label("Side Length: ");
        squareColorLabel = new Label("Color/Color Code: ");

        squareSideLengthField = new TextField("20");
        squareColorField = new TextField("Black");

        // setting up the new triangle settings
        triangleGuideLabel = new Label("Enter The color/color code then click on the three vertices  of the triangle");
        triangleColorCodeField = new TextField("Black");

        // setting up the new line settings
        lineStrokeWidthLabel = new Label("Line Width : ");
        lineColorCodeField = new TextField("Black");
        lineStrokeWidthField = new TextField("3");

        // setting up the new polygon settings
        noOfSidesChoiceBox = new ChoiceBox<>();
        noOfSidesChoiceBox.setTooltip(new Tooltip("Choose the number of sides then click on the start point"));
        noOfSidesChoiceBox.getItems().addAll(5, 6, 7);
        noOfSidesChoiceBox.getSelectionModel().selectFirst();
        polygonColorCodeField = new TextField("Black");
    }


    public Node[] getSettingsLayout(int type)
    {
        Node[] newElements;
        switch (type)
        {
            case 1:
                //for circle
                newElements = new Node[4];
                newElements[0] = radiusLabel;
                newElements[1] = radiusField;
                newElements[2] = colorCodeLabel;
                newElements[3] = colorCodeField;
                return newElements;
            case 2:
                //for ellipse
                newElements = new Node[6];
                newElements[0] = xRadiusLabel;
                newElements[1] = xRadiusField;
                newElements[2] = yRadiusLabel;
                newElements[3] = yRadiusField;
                newElements[4] = ellipseColorCodeLabel;
                newElements[5] = ellipsseColorCodeField;
                return newElements;
            case 3:
                //for rectangle
                newElements = new Node[6];
                newElements[0] = rectangleWidthLabel;
                newElements[1] = rectangleWidthField;
                newElements[2] = rectangleHeightLabel;
                newElements[3] = rectangleHeightField;
                newElements[4] = rectangleColorCodeLabel;
                newElements[5] = rectangleColorCodeField;
                return newElements;
            case 4:
                //for square
                newElements = new Node[4];
                newElements[0] = squareSideLengthLabel;
                newElements[1] = squareSideLengthField;
                newElements[2] = squareColorLabel;
                newElements[3] = squareColorField;
                return newElements;
            case 5:
                //for triangle
                newElements = new Node[2];
                newElements[0] = triangleGuideLabel;
                newElements[1] = triangleColorCodeField;
                return newElements;
            case 6:
                //for polygon
                newElements = new Node[3];
                newElements[0] = noOfSidesChoiceBox;
                newElements[1] = squareColorLabel;
                newElements[2] = polygonColorCodeField;
                return newElements;
            case 7:
                //for line
                newElements = new Node[4];
                newElements[0] = squareColorLabel;
                newElements[1] = lineColorCodeField;
                newElements[2] = lineStrokeWidthLabel;
                newElements[3] = lineStrokeWidthField;
                return newElements;
        }
        return null;
    }


    public String[] getSettings(int type)
    {
        String[] settings;
        switch (type)
        {
            case 1:
                settings = new String[2];
                settings[0] = radiusField.getText();
                settings[1] = colorCodeField.getText();
                return settings;
            case 2:
                settings = new String[3];
                settings[0] = xRadiusField.getText();
                settings[1] = yRadiusField.getText();
                settings[2] = ellipsseColorCodeField.getText();
                return settings;
            case 3:
                settings = new String[3];
                settings[0] = rectangleWidthField.getText();
                settings[1] = rectangleHeightField.getText();
                settings[2] = rectangleColorCodeField.getText();
                return settings;
            case 4:
                settings = new String[2];
                settings[0] = squareSideLengthField.getText();
                settings[1] = squareColorField.getText();
                return settings;
            case 5:
                settings = new String[1];
                settings[0] = triangleColorCodeField.getText();
                return settings;
            case 6:
                settings = new String[2];
                settings[0] = String.valueOf(noOfSidesChoiceBox.getSelectionModel().getSelectedItem());
                settings[1] = polygonColorCodeField.getText();
                return settings;
            case 7:
                settings = new String[2];
                settings[0] = lineColorCodeField.getText();
                settings[1] = lineStrokeWidthField.getText();
                return settings;
        }
        return null;
    }
}
