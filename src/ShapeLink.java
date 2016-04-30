/**
 * Created by Tarek Alqaddy on 4/29/2016.
 */
import javafx.scene.Node;

public class ShapeLink {
    private Shape shape;
    private javafx.scene.Node shapeFX;
    private String type;

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
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