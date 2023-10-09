package view;

import controller.MetroStationViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.metrogatestates.Inactive;

import java.util.ArrayList;

/* Authors: Arno Robeys, Nino Verstraeten, Kevin Verbruggen */
public class MetroStationView extends GridPane {

	MetroStationViewController theController;
	private Stage stage = new Stage();

	private VBox[] gateVBoxes = new VBox[3];
	private ChoiceBox[] IDBoxes = new ChoiceBox[3];
	private Button[] scanButtons = new Button[3];
	private Button[] walkThroughButtons = new Button[3];

	public MetroStationView(){}

	public void initialize() {
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(390);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 300);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();

		HBox screen1HBox = new HBox(8);
		screen1HBox.setPadding(new Insets(10));

		for(int i = 1; i <= 3; i++) {
			screen1HBox.getChildren().add(createMetroStationGate(i, root));
		}

		root.getChildren().add(screen1HBox);
	}

	public void setTheController(MetroStationViewController theController) {
		this.theController = theController;
	}

	public void updateMetrocardIDList(ArrayList<Integer> IDS) {
		for(ChoiceBox choiceBox : IDBoxes) {
			choiceBox.getItems().clear();
			for (int i : IDS) {
				choiceBox.getItems().add(i);
			}
		}
	}

	public VBox createMetroStationGate(int gateID, Group root){
		int indexId = gateID - 1;
		Label alert = new Label();
		alert.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1; -fx-padding: 10 10 10 10;");
		alert.setWrapText(true);

		gateVBoxes[indexId] = new VBox(5);
		gateVBoxes[indexId].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
		gateVBoxes[indexId].setPadding(new Insets(10));
		gateVBoxes[indexId].setPrefWidth(stage.getWidth()/3 - 20);
		gateVBoxes[indexId].setMaxWidth(250);

		IDBoxes[indexId] = new ChoiceBox<>();
		scanButtons[indexId] = new Button("Scan Metrocard");
		walkThroughButtons[indexId] = new Button("Walk Through Gate");

		double prefWidth = gateVBoxes[indexId].getPrefWidth();
		IDBoxes[indexId].setPrefWidth(prefWidth);
		scanButtons[indexId].setPrefWidth(prefWidth);
		walkThroughButtons[indexId].setPrefWidth(prefWidth);
		alert.setPrefWidth(prefWidth);

		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			double width = (double) newVal / 3 - 20;
			gateVBoxes[indexId].setPrefWidth(width);
			IDBoxes[indexId].setPrefWidth(width);
			scanButtons[indexId].setPrefWidth(width);
			walkThroughButtons[indexId].setPrefWidth(width);
			alert.setPrefWidth(width);
		});

		scanButtons[indexId].setOnAction(e -> { theController.scanMetroCard(gateID, (Integer) IDBoxes[gateID-1].getValue(),alert); });
		walkThroughButtons[indexId].setOnAction(e -> { theController.walkThroughGate(gateID, (Integer) IDBoxes[gateID-1].getValue(),alert); });

		if (theController.getMetroGateList().get(gateID-1).getState() instanceof Inactive) {
			IDBoxes[gateID-1].setDisable(true);
			scanButtons[indexId].setDisable(true);
			walkThroughButtons[indexId].setDisable(true);
			gateVBoxes[indexId].setStyle("-fx-background-color: orange;");
		} else {
			gateVBoxes[indexId].setStyle("-fx-background-color: white;");
		}

		gateVBoxes[indexId].getChildren().addAll(new Label("GATE " + gateID), new Label("MetrocardID"),IDBoxes[gateID-1], scanButtons[indexId], walkThroughButtons[indexId],alert);
		return gateVBoxes[indexId];
	}

	public void activateGate(int gateID) {
		IDBoxes[gateID-1].setDisable(false);
		scanButtons[gateID-1].setDisable(false);
		walkThroughButtons[gateID-1].setDisable(false);
		gateVBoxes[gateID-1].setStyle("-fx-background-color: white;");
	}

	public void deactivateGate(int gateID) {
		IDBoxes[gateID-1].setDisable(true);
		scanButtons[gateID-1].setDisable(true);
		walkThroughButtons[gateID-1].setDisable(true);
		gateVBoxes[gateID-1].setStyle("-fx-background-color: orange;");
	}
}
