package view;

import controller.MetroTicketViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/* Authors: Arno Robeys*/
public class MetroTicketView extends GridPane {

	MetroTicketViewController theController;
	private Stage stage = new Stage();
	ChoiceBox<String> metroCardIdChoiceBox = new ChoiceBox<>();
	Label totalPriceLabel = new Label("Total price: 0 €");
	Label textPriceLabel = new Label();
	Button buyMetroCardButton = new Button("Buy Metro Card");
	TextField numberOfRidesTextField = new TextField();
	Button addRidesButton = new Button("Add extra rides to metro card");
	Button confirmRequestButton = new Button("Confirm request");
	Button cancelRequestButton = new Button("Cancel request");
	CheckBox isStudentCheckBox = new CheckBox("Higher education student?");
	ToggleGroup ageToggleGroup = new ToggleGroup();
	RadioButton ageRadioButton1 = new RadioButton("Younger than 26 years");
	RadioButton ageRadioButton2 = new RadioButton("Older than 64 years");
	RadioButton ageRadioButton3 = new RadioButton("Between 26 and 64 years");

	public MetroTicketView(){
		stage.setTitle("METROTICKET VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(5);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 350);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();


		//Metrocard Buy
		VBox metroCardBuyVBox = new VBox(10);
		VBox buyVBox = new VBox(8);
		buyVBox.getChildren().addAll(buyMetroCardButton, new Label("Metro card price is 15 € - 2 free rides included"));
		buyVBox.setPadding(new Insets(10));
		buyVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));


		//Metrocard settings
		VBox metroCardIdVBox = new VBox(8);

		//ChoiceBox Metrocard ID
		HBox metroCardIdHBox = new HBox(8);
		metroCardIdHBox.getChildren().addAll(new Label("Select metro card"), metroCardIdChoiceBox);

		//Number of Rides
		HBox metroCardIdHBox2 = new HBox(8);
		numberOfRidesTextField.setEditable(false);
		metroCardIdHBox2.getChildren().addAll(new Label("Number of rides"), numberOfRidesTextField);

		//Checkbox is Student?
		HBox metroCardIdHBox3 = new HBox(8);
		metroCardIdHBox3.getChildren().addAll(isStudentCheckBox);

		//Radio Buttons for age
		ageRadioButton1.setToggleGroup(ageToggleGroup);
		ageRadioButton2.setToggleGroup(ageToggleGroup);
		ageRadioButton3.setToggleGroup(ageToggleGroup);

		HBox metroCardIdHBox4 = new HBox(8);
		metroCardIdHBox4.getChildren().addAll(ageRadioButton1, ageRadioButton2, ageRadioButton3);

		VBox metroCardIdVBox2 = new VBox(8);

		HBox metroCardIdHBox5 = new HBox(8);
		metroCardIdHBox5.getChildren().addAll(confirmRequestButton, cancelRequestButton);
		metroCardIdVBox2.getChildren().addAll(addRidesButton, totalPriceLabel, textPriceLabel, metroCardIdHBox5);

		metroCardIdVBox.getChildren().addAll(metroCardIdHBox, metroCardIdHBox2, metroCardIdHBox3, metroCardIdHBox4, metroCardIdVBox2);

		//Buttons actions
		buyMetroCardButton.setOnAction(e -> theController.createNewMetroCard());
		addRidesButton.setOnAction(e -> {
			theController.calculatePrice(metroCardIdChoiceBox.getValue(), numberOfRidesTextField.getText(), isStudentCheckBox.isSelected(), ageRadioButton2.isSelected(), ageRadioButton1.isSelected());
		});
		cancelRequestButton.setOnAction(e -> {
			resetAll();
		});
		confirmRequestButton.setOnAction(e -> {
			theController.buyRides(metroCardIdChoiceBox.getValue(), numberOfRidesTextField.getText());
			resetAll();
		});
		metroCardIdVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));

		metroCardIdVBox.setPrefWidth(stage.getWidth()*0.9);
		metroCardIdVBox.setPadding(new Insets(10));
		metroCardBuyVBox.getChildren().addAll(buyVBox, metroCardIdVBox);
		this.add(metroCardBuyVBox, 0, 2);

		//Disable buttons
		resetView();


		metroCardIdChoiceBox.setOnAction(e -> numberOfRidesTextField.setEditable(true));
		numberOfRidesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.matches("\\d*" ) && !newValue.isEmpty() && metroCardIdChoiceBox.getValue() != null) {
				addRidesButton.setDisable(false);
			} else {
				resetView();
			}
		});

		//Set Margin
		double mW = stage.getWidth()*0.05;
		double mH = stage.getHeight()*0.025;
		setMargin(metroCardBuyVBox, new Insets(mH, mW, 0, mW));
		stage.widthProperty().addListener((observable, oldValue, newValue) -> {
			metroCardIdVBox.setPrefWidth(newValue.doubleValue()*0.9);
			setMargin(metroCardBuyVBox, new Insets(mH, newValue.doubleValue()*0.05, 0, newValue.doubleValue()*0.05));
		});

		root.getChildren().add(this);

	}


	//Methodes
	public void setTheController(MetroTicketViewController theController) {
		this.theController = theController;
	}

	public void updateMetrocardIDList(ArrayList<Integer> IDS) {
		metroCardIdChoiceBox.getItems().clear();
		for (int i : IDS) {
			metroCardIdChoiceBox.getItems().add(Integer.toString(i));
		}
	}

	public void updateTotalPriceLabel(String price, String priceText, boolean valid) {
		if(valid) {
			confirmRequestButton.setDisable(false);
			cancelRequestButton.setDisable(false);
		}
		totalPriceLabel.setText("Total price: " + price + " €");
		textPriceLabel.setText(priceText);
	}

	public void resetView() {
		updateTotalPriceLabel("0", "", true);
		confirmRequestButton.setDisable(true);
		cancelRequestButton.setDisable(true);
		addRidesButton.setDisable(true);
	}

	private void resetAll() {
		resetView();
		numberOfRidesTextField.setText("0");
		metroCardIdChoiceBox.setValue(null);
		isStudentCheckBox.setSelected(false);
		if(ageToggleGroup.getSelectedToggle() != null) {
			ageToggleGroup.getSelectedToggle().setSelected(false);
		}
	}

}


