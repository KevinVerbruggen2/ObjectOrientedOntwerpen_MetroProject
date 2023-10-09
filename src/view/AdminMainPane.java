package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.ControlCenterPane;
import view.panels.MetroCardOverviewPane;
import view.panels.SetupPane;

/* Authors:  */
public class AdminMainPane extends BorderPane {

    MetroCardOverviewPane metroCardOverviewPane;
    ControlCenterPane controlCenterPane;
    SetupPane setupPane;

	public AdminMainPane(MetroCardOverviewPane metroCardOverviewPane, ControlCenterPane controlCenterPane, SetupPane setupPane) {
        this.metroCardOverviewPane = metroCardOverviewPane;
        this.controlCenterPane = controlCenterPane;
        this.setupPane = setupPane;
        TabPane tabPane = new TabPane();

        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);
        metroCardOverviewTab.setClosable(false);
        Tab controlCenterTab = new Tab("Control Center", controlCenterPane);
        controlCenterTab.setClosable(false);
        Tab setupTab = new Tab("Setup", setupPane);
        setupTab.setClosable(false);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(setupTab);
        this.setCenter(tabPane);
	}
}
