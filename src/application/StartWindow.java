package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StartWindow extends Application {

	public void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
		primaryStage.setTitle(null);
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setHeight(400);
		primaryStage.setWidth(600);
		primaryStage.show();
	}
	
	@FXML
	private javafx.scene.control.Button ChatBtn;
	@FXML
	private javafx.scene.control.Button ExitBtn;
	
	@FXML
	public void ChatBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
		System.exit(0);
	}
	
	@FXML
	public void ExitBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
		System.exit(0);
	}
}
