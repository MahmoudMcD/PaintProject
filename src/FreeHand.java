import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * Created by Tarek Alqaddy on 5/12/2016.
 */
public class FreeHand implements EventHandler<MouseEvent>{
    private Path path;
    private DrawApplication drawApplication;
    private GUIHelpers guiHelpers;


    public FreeHand(DrawApplication d,GUIHelpers g){
        this.drawApplication = d;
        this.guiHelpers = g;

        path = new Path();
        drawApplication.getRoot().getChildren().add(path);

        path.getElements().add(new MoveTo(0, 0));
    }

    @Override
    public void handle(MouseEvent event) {
        if(guiHelpers.getStatus() == 8) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                path.setStrokeWidth(Double.valueOf(guiHelpers.getSettingsHelper().getSettings(8)[0]));
                path.setStroke(Paint.valueOf(guiHelpers.getSettingsHelper().getSettings(8)[1]));
                path.getElements().add( new MoveTo(event.getX(), event.getY()));
            }
            else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {

                path.getElements()
                        .add(new LineTo(event.getX(), event.getY()));
            }
        }
    }
}
