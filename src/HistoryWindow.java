import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by mahmoud on 5/13/2016.
 */
public class HistoryWindow {
    private Stage window;
    private Scene scene;
    TableView<Memento> tableView;
    VBox mainLayout;
    Button moveToButton;
    Label currentHistoryPos;

    private HistoryHandler historyHandler;


    public HistoryWindow(HistoryHandler historyHandler)
    {
        this.historyHandler = historyHandler;

        window = new Stage();

        // set up the table view
        tableView = new TableView<>();

        TableColumn<Memento, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<> ("date"));
        dateColumn.setMinWidth(150);

        TableColumn<Memento, String> messageColumn = new TableColumn<>("Message");
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        messageColumn.setMinWidth(250);

        tableView.getColumns().addAll(dateColumn, messageColumn);
        refreshHistoryTableView();

        moveToButton = new Button("Move To");
        moveToButton.setOnAction(e -> moveButtonClicked());
        moveToButton.setAlignment(Pos.CENTER_RIGHT);

        currentHistoryPos = new Label("No Actions yet !");
        currentHistoryPos.setAlignment(Pos.CENTER_RIGHT);

        mainLayout = new VBox();
        mainLayout.setSpacing(10);
        mainLayout.setPadding(new Insets(10, 10, 10, 10));
        mainLayout.getChildren().addAll(tableView, moveToButton, currentHistoryPos);

        scene = new Scene(mainLayout, 400, 500);
        window.setAlwaysOnTop(true);
        window.initStyle(StageStyle.DECORATED);
        window.setScene(scene);
    }

    public void showHistoryWindow()
    {
        refreshHistoryTableView();
        window.show();
    }

    public void refreshHistoryTableView()
    {
        if (historyHandler.getCurrent().getMessage() != null)
            currentHistoryPos.setText("Current : " + historyHandler.getCurrent().getMessage());

        tableView.getItems().clear();
        tableView.getItems().addAll(historyHandler.returnObservableList());
    }

    private void moveButtonClicked()
    {
        Memento selected;
        selected = tableView.getSelectionModel().getSelectedItem();
        historyHandler.returnUntil(selected);
        refreshHistoryTableView();
    }

    public void closeHistoryWindow()
    {
        window.close();
    }
}
