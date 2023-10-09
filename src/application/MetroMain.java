package application;
	
import controller.*;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MetroFacade;
import view.AdminMainPane;
import view.AdminView;
import view.MetroStationView;
import view.MetroTicketView;
import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

import java.io.*;

public class MetroMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {

		MetroFacade facade = new MetroFacade();

		MetroCardOverviewPane metroCardOverviewPane = new MetroCardOverviewPane();
		ControlCenterPane controlCenterPane = new ControlCenterPane();
		SetupPane setupPane = new SetupPane();
		BorderPane borderPane = new AdminMainPane(metroCardOverviewPane, controlCenterPane, setupPane);
		MetroTicketView metroTicketView = new MetroTicketView();
		AdminView adminView = new AdminView(borderPane);
		MetroStationView metroStationView = new MetroStationView();

		MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController(facade, metroCardOverviewPane);
		ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController(facade, controlCenterPane);
		SetupPaneController setupPaneController = new SetupPaneController(facade, setupPane);
		AdminViewController adminViewController = new AdminViewController(facade, adminView);
		MetroTicketViewController metroTicketViewController = new MetroTicketViewController(facade, metroTicketView);
		MetroStationViewController metroStationViewController = new MetroStationViewController(facade, metroStationView);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
