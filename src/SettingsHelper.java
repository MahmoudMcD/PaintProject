import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    }


    public Node[] getSettingsLayout(int type)
    {
        Node[] newElements;
        switch (type)
        {
            case 1:
                newElements = new Node[4];
                newElements[0] = radiusLabel;
                newElements[1] = radiusField;
                newElements[2] = colorCodeLabel;
                newElements[3] = colorCodeField;
                return newElements;
            case 2:
                newElements = new Node[6];
                newElements[0] = xRadiusLabel;
                newElements[1] = xRadiusField;
                newElements[2] = yRadiusLabel;
                newElements[3] = yRadiusField;
                newElements[4] = ellipseColorCodeLabel;
                newElements[5] = ellipsseColorCodeField;
                return newElements;
            case 3:
                newElements = new Node[6];
                newElements[0] = rectangleWidthLabel;
                newElements[1] = rectangleWidthField;
                newElements[2] = rectangleHeightLabel;
                newElements[3] = rectangleHeightField;
                newElements[4] = rectangleColorCodeLabel;
                newElements[5] = rectangleColorCodeField;
                return newElements;
            case 4:
                newElements = new Node[4];
                newElements[0] = squareSideLengthLabel;
                newElements[1] = squareSideLengthField;
                newElements[2] = squareColorLabel;
                newElements[3] = squareColorField;
                return newElements;
            case 5:
                newElements = new Node[2];
                newElements[0] = triangleGuideLabel;
                newElements[1] = triangleColorCodeField;
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
            case 5:
                settings = new String[1];
                settings[0] = triangleColorCodeField.getText();
                return settings;
        }
        return null;
    }
}
