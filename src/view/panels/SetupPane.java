package view.panels;

import controller.SetupPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.database.strategy.LoadSaveStrategyEnum;
import model.ticketpricedecorator.TicketPriceDiscountEnum;

import java.util.ArrayList;

/* Authors: Kevin Verbruggen, Arno Robeys */
public class SetupPane extends GridPane {

    SetupPaneController theController;

    public SetupPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Setup Pane!"), 0, 0, 1, 1);

    }

    public void initialize() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        for (LoadSaveStrategyEnum s : LoadSaveStrategyEnum.values())
            choiceBox.getItems().add(s.toString());

        Button saveButton = new Button("Save load-save strategy");
        this.add(choiceBox, 0, 1, 1, 1);
        this.add(saveButton, 0, 2, 1, 1);

        for(TicketPriceDiscountEnum t : TicketPriceDiscountEnum.values()) {
            CheckBox checkBox = new CheckBox(t.toString());
            for(String s : theController.getDiscounts()) {
                if(s.equals(t.toString()))
                    checkBox.setSelected(true);
            }
            this.add(checkBox, 0, 3 + t.ordinal(), 1, 1);
        }

        Button confirmButton = new Button("Save Discounts");
        this.add(confirmButton, 0, 4 + TicketPriceDiscountEnum.values().length, 1, 1);

        confirmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<String> selectedDiscounts = new ArrayList<>();
                for(TicketPriceDiscountEnum t : TicketPriceDiscountEnum.values())
                    if(((CheckBox)SetupPane.this.getChildren().get(3 + t.ordinal())).isSelected())
                        selectedDiscounts.add(t.toString());
                theController.saveDiscounts(selectedDiscounts);
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Bij een klik op deze knop worden de gekozen instellingen opgeslagen in een properties bestand met als naam “settings” dat opgeslagen wordt in de bestanden map (folder binnen je project waar ook de txt en xls bestandjes staan)
                String choice = choiceBox.getValue();
                if(choice != null)
                    theController.saveStrategy(choice);
            }
        });
    }
    public void setTheController(SetupPaneController theController) {
        this.theController = theController;
    }
}
