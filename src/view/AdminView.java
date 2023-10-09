package view;

import controller.AdminViewController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.ControlCenterPane;

/* Authors:  */
public class AdminView {

	private AdminViewController theController;
	private BorderPane borderPane;
	private static Stage stage = new Stage();

		
	public AdminView(BorderPane borderPane){
		this.borderPane = borderPane;
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(660);
		stage.setY(5);
		Group root = new Group();
		Scene scene = new Scene(root, 690, 680);

		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
	}

	public void setTheController(AdminViewController theController) {
		this.theController = theController;
	}

}
