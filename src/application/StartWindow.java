package application;

import java.util.Random;

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

	String PortList = "PortList.txt";

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
	public void ChatBtnAction(ActionEvent event) throws Exception {
		Parent nA = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
		Stage NewApp = new Stage();
		NewApp.setScene(new Scene(nA));
		int replace = this.puerto();
		System.out.println(replace);
		Ports.SavePort(Integer.toString(replace), PortList);
		NewApp.setResizable(false);
		NewApp.setHeight(569);
		NewApp.setWidth(966);
		NewApp.initStyle(StageStyle.UTILITY);
		NewApp.show();
	}

	@FXML
	public void ExitBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
		Ports.EndPorts(PortList);
		System.exit(0);
	}

	public int puerto() {
		Random rand = new Random();
		int upperbound = 65536;
		int randInt = Math.abs(rand.nextInt(upperbound));

		if (Ports.PortReader(Integer.toString(randInt), PortList) == true || randInt == 0) {

			puerto();

		}
		return randInt;
	}
}
