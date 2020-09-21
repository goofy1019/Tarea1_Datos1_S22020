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

	/**
	 * Variable para llamar al archivo que contiene la lista de puertos ya usados
	 */
	String PortList = "PortList.txt";

	/**
	 * Metodo start propio de JavaFX
	 */
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
	private javafx.scene.control.Button ChatBtn;// coloca el boton de chat

	/**
	 * @param event Button click.
	 * @throws Exception Crea una nueva instancia de SocketServer por medio de un
	 *                   thread y le asigna ese socket a la ventana. Tambien,
	 *                   realiza los cambios necesarios para que se despliegue la
	 *                   ventana de chat.
	 */
	@FXML
	public void ChatBtnAction(ActionEvent event) throws Exception {
		Parent nA = FXMLLoader.load(getClass().getResource("ChatWindow.fxml"));
		Stage NewApp = new Stage();
		NewApp.setScene(new Scene(nA));
		int replace = this.puerto();
		Thread thread = new Thread() {
			public void run() {
				SocketServer.NewServer(replace);
			}
		};
		System.out.println(replace);
		Ports.SavePort(Integer.toString(replace), PortList);
		NewApp.setResizable(false);
		NewApp.setHeight(569);
		NewApp.setWidth(966);
		NewApp.initStyle(StageStyle.UTILITY);
		ChatWindow.MyPortNum = replace;
		thread.start();
		NewApp.show();
	}

	@FXML
	private javafx.scene.control.Button ExitBtn;// coloca el boton de exit

	/**
	 * @param event Button click. Cierra la ventana, limpia el archivo de Ports y
	 *              termina los threads
	 */
	@FXML
	public void ExitBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
		Ports.EndPorts(PortList);
		System.exit(0);
	}

	/**
	 * @return PortNumber Devuelve un puerto random del 0 al 65536 (limite de PC) Se
	 *         realiza una verificacion de que no este repetido por medio del metodo
	 *         PortReader de la clase Ports.java
	 */
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