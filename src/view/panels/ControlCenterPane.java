package view.panels;

import controller.ControlCenterPaneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.MetroGate;

import java.util.ArrayList;

/* Authors: Kevin Verbruggen, Nino Verstraeten, Arno Robeys */
public class ControlCenterPane extends GridPane {

    private TextField soldTickets = new TextField();
    private TextField totalRevenue = new TextField();

    private VBox[] gateVBoxes = new VBox[3];
    private Button[] activateGateButtons = new Button[3];
    private Button[] deactivateGateButtons = new Button[3];
    private TextField[] ticketsScanned = new TextField[3];
    private TextArea logArea = new TextArea();
    private ArrayList<String> alerts = new ArrayList<>();

    private final MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
    private ControlCenterPaneController theController;
    private Button openMetroStationButton = new Button("Open metrostation");
    private Button closeMetroStationButton = new Button("Close metrostation");


    public ControlCenterPane() {}

    public void initialize() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Control Center Pane!"), 0, 0, 1, 1);

        this.add(openMetroStationButton, 0, 1, 1, 1);
        this.add(closeMetroStationButton, 0, 2, 1, 1);

        openMetroStationButton.setOnAction(e -> {
            theController.openMetroStation();
            setOpenStation();
        });

        closeMetroStationButton.setOnAction(e -> {
            theController.closeMetroStation();
            setCloseStation();
        });

        closeMetroStationButton.setDisable(true);

        VBox vBox = new VBox(25);
        VBox vBox1 = new VBox(8);
        vBox1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        HBox hBox1 = new HBox(10);
        soldTickets.setEditable(false);
        hBox1.getChildren().addAll(new Label("Number of sold tickets: "), soldTickets);

        HBox hBox2 = new HBox(10);
        totalRevenue.setEditable(false);
        hBox2.getChildren().addAll(new Label("Total € amount of sold tickets: "), totalRevenue);
        vBox1.getChildren().addAll(hBox1, hBox2);

        VBox vBox2 = new VBox(10);
        vBox2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        HBox hBox3 = new HBox(10);

        //For each gate, add a button to active, deactivate the gate
        for (MetroGate gate : theController.getFacade().getMetroGateList()) {
            int indexID = gate.getId() - 1;
            Label gateLabel = new Label("Gate " + gate.getId() + " / " + gate.getState().toString().toUpperCase());
            gateVBoxes[indexID] = new VBox(5);
            activateGateButtons[indexID] = new Button("Activate");
            deactivateGateButtons[indexID] = new Button("Deactivate");
            Label ticketsScannedLabel = new Label("Tickets scanned: ");
            ticketsScanned[indexID] = new TextField();
            ticketsScanned[indexID].setEditable(false);
            gateVBoxes[indexID].getChildren().addAll(gateLabel, activateGateButtons[indexID], deactivateGateButtons[indexID], ticketsScannedLabel, ticketsScanned[indexID]);

            activateGateButtons[indexID].setDisable(true);
            deactivateGateButtons[indexID].setDisable(true);
            activateGateButtons[indexID].setOnAction(e -> {theController.activateMetroGate(gate.getId());
                System.out.println("Activate gate " + gate.getId());
                    activateGateButtons[indexID].setDisable(true);
                    deactivateGateButtons[indexID].setDisable(false);});
            deactivateGateButtons[indexID].setOnAction(e -> {theController.deactivateMetroGate(gate.getId());
                    activateGateButtons[indexID].setDisable(false);
                    deactivateGateButtons[indexID].setDisable(true);});
        }
        for (VBox gateVBox : gateVBoxes) {
            hBox3.getChildren().add(gateVBox);
        }
        vBox2.getChildren().addAll(hBox3);

        //Alerts messages
        VBox vBox4 = new VBox(10);
        logArea.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        logArea.setEditable(false);
        vBox4.getChildren().addAll(new Label("ALERTS"), logArea);

        vBox1.setPadding(new Insets(10, 10, 10, 10));
        vBox2.setPadding(new Insets(10, 10, 10, 10));
        vBox.setPrefWidth(700);
        this.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        vBox.getChildren().addAll(vBox1, vBox2, vBox4);
        this.add(vBox, 0, 3, 1, 1);
    }

    private void setOpenStation() {
        openMetroStationButton.setDisable(true);
        closeMetroStationButton.setDisable(false);
        for (Button activateGateButton : activateGateButtons) {
            activateGateButton.setDisable(false);
        }
    }
    private void setCloseStation() {
        closeMetroStationButton.setDisable(true);
        openMetroStationButton.setDisable(false);
        for (Button deactivateGateButton : deactivateGateButtons) {
            deactivateGateButton.fire();
        }
        for (Button activateGateButton : activateGateButtons) {
            activateGateButton.setDisable(true);
        }
    }

    public void updateGateLabel(MetroGate gate, String state) {
        Label newLabel = (Label) gateVBoxes[gate.getId() - 1].getChildren().get(0);
        newLabel.setText("Gate " + gate.getId() + " / " + state.toUpperCase());
    }

    public void addAlert(String alert) {
        alerts.add(0,alert);
        logArea.setText(String.join("\n", alerts));
    }

    public void setSoldTickets(int soldTickets) {
        this.soldTickets.setText(String.valueOf(soldTickets));
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue.setText(String.format("%.2f", totalRevenue));
    }

    public void setTicketsScanned(int gateID, int ticketsScanned) {
        this.ticketsScanned[gateID - 1].setText(String.valueOf(ticketsScanned));
    }

    public void setTheController(ControlCenterPaneController theController) {
        this.theController = theController;
    }
}
