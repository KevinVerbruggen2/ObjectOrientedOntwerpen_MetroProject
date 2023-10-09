package view.panels;


import controller.MetroCardOverviewPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import jxl.read.biff.BiffException;
import model.MetroCard;
import model.database.MetroCardDatabase;
import model.database.strategy.LoadSaveStrategy;
import model.database.strategy.LoadSaveStrategyFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* Authors:  Arno Robeys */
public class MetroCardOverviewPane extends GridPane{

	private MetroCardOverviewPaneController controller;
	private ObservableList<MetroCard> metroCards;
	private TableView<MetroCard> metroCardTable ;

	public MetroCardOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);        
		this.add(new Label("List of Metro cards:"), 0, 0, 1, 1);
		this.add(createMetroCardTable(), 0, 2, 1, 1);
	}

	private TableView<MetroCard> createMetroCardTable(){
		metroCardTable = new TableView<>();

		//Resize table
		metroCardTable.prefWidthProperty().bind(this.widthProperty());

		//Create columns
		TableColumn<MetroCard,Integer> metroCardId = new TableColumn<>("MetroCard ID");
		TableColumn<MetroCard,String> monthYear = new TableColumn<>("Month#Year");
		TableColumn<MetroCard,Integer> availableTickets = new TableColumn<>("Available Tickets");
		TableColumn<MetroCard,Integer> spentTickets = new TableColumn<>("Spent Tickets");

		metroCardId.setCellValueFactory(new PropertyValueFactory<>("id"));
		monthYear.setCellValueFactory(new PropertyValueFactory<>("monthYear"));
		availableTickets.setCellValueFactory(new PropertyValueFactory<>("availableRides"));
		spentTickets.setCellValueFactory(new PropertyValueFactory<>("usedRides"));

		//Add columns to table
		metroCardTable.getColumns().addAll(new ArrayList<>(Arrays.asList(metroCardId, monthYear, availableTickets, spentTickets)));

		//Resize columns
		metroCardTable.getColumns().forEach(column -> column.prefWidthProperty().bind(metroCardTable.widthProperty().divide(4)));

		return metroCardTable;
	}

	public void refresh(ArrayList<MetroCard> metroCardList) {
		metroCards = FXCollections.observableArrayList(metroCardList);
		metroCardTable.setItems(metroCards);
		metroCardTable.refresh();
	}

	public void updateMetroCardList(ArrayList<MetroCard> metroCardList) {
		refresh(metroCardList);
	}

	public void setController(MetroCardOverviewPaneController controller) {
		this.controller = controller;
	}
}
