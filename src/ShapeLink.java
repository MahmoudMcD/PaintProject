/**
 * Created by Tarek Alqaddy on 4/29/2016.
 */
import javafx.scene.Node;

public class ShapeLink {
    private iShape shape;
    private javafx.scene.Node shapeFX;
    private String type;

    public iShape getShape() {
        return shape;
    }

    public void setShape(iShape shape) {
        this.shape = shape;
    }

    public Node getShapeFX() {
        return shapeFX;
    }

    public void setShapeFX(Node shapeFX) {
        this.shapeFX = shapeFX;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}