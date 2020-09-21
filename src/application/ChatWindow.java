package application;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChatWindow extends StartWindow {
	/**
	 * Numero de puerto de la ventana.
	 */
	protected static int MyPortNum = 0;

	/**
	 * Archivo donde se encuentran los puertos usados.
	 */
	String PortList = "PortList.txt";

	/**
	 * Almacena mensaje entrante.
	 */
	public static String InMessage = "";

	/**
	 * Almacena puerto que envio mensaje.
	 */
	public static String InPort = "";

	@FXML
	private javafx.scene.control.Button ExitBtn;// coloca el boton de exit

	/**
	 * Cierra la ventana de chat.
	 */
	@FXML
	public void ExitBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
	}

	@FXML
	private javafx.scene.control.MenuItem MyPortBtn;// coloca el boton de menu para encontrar el numero de puerto

	/**
	 * @param event Button click. Despliega port medio de un Alert el numero de
	 *              puerto que pertenece a la ventana.
	 */
	@FXML
	public void MyPortBtnAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("My Port Number");
		alert.setHeaderText(null);
		String MyPortString = Integer.toString(ChatWindow.MyPortNum);
		alert.setContentText(MyPortString);
		alert.showAndWait();

	}

	@FXML
	private javafx.scene.control.MenuItem MyIPBtn;// coloca el boton de menu para encontrar la direccion IP

	/**
	 * @param event Button click. Despliega por medio de un alert el IP propio de la
	 *              ventana.
	 */
	@FXML
	public void MyIPBtnAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("My I.P. Number");
		alert.setHeaderText(null);
		InetAddress Iaddress = null;
		try {
			Iaddress = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String address = Iaddress.getHostAddress();
		alert.setContentText(address);
		alert.showAndWait();

	}

	@FXML
	private javafx.scene.control.TextArea TextAreaSent;// Coloca el text area de mensajes enviados

	@FXML
	private javafx.scene.control.TextArea TextAreaIn;// Coloca el text area de mensajes recibidos

	@FXML
	private javafx.scene.control.TextField NewMessageTF;// Coloca el text field para ingresar mensaje

	@FXML
	private javafx.scene.control.TextField PortTF;// Coloca el text field para ingresar puerto

	@FXML
	private javafx.scene.control.TextField IPTF;// Coloca el text field para ingresar IP

	@FXML
	private javafx.scene.control.Button SendBtn;// Coloca boton de enviar

	/**
	 * @param event Button click. Toma los valores que se ingresaron en el text
	 *              field, realiza una verificacion de lo ingresado para revisar que
	 *              este valido (si puerto o IP son invalidos lo avisa por medio de
	 *              un alert) y si es valido realiza un nuevo Socket Client llamando
	 *              un metodo de la clase SocketClient.java. Luego, imprime lo
	 *              ingresado en el TextArea de enviados y remueve el texto de los
	 *              TextFields
	 * 
	 */
	@FXML
	public void SendBtnAction(ActionEvent event) {
		String port = PortTF.getText();
		String message = NewMessageTF.getText();
		String ip = IPTF.getText();
		InetAddress Iaddress = null;
		try {
			Iaddress = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String address = Iaddress.getHostAddress();
		if (Ports.PortReader(port, PortList) && message != "" && address.equals(ip)) {
			try {
				SocketClient.NewClient(Integer.parseInt(port), ip, message);
				Platform.runLater(() -> {
					TextAreaSent.appendText(
							"I.P.: " + ip + "   |   " + "To: " + port + "   |   " + "Message: " + message + "\n");
				});
				String mes = InMessage;
				System.out.println(mes);
				PortTF.clear();
				NewMessageTF.clear();
				IPTF.clear();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Please verify the I.P. Address and Port Number you entered are valid");
			alert.showAndWait();
		}
	}

	@FXML
	private javafx.scene.control.Button ReBtn;// Coloca el boton para refrescar lo recibido

	/**
	 * @param event Button click. Despliega el mensaje mas reciente que ha recibido
	 *              el puerto de la ventana.
	 */
	@FXML
	public void ReBtnAction(ActionEvent event) {
		String message = ChatWindow.InMessage;
		String port = ChatWindow.InPort;
		try {
			Platform.runLater(() -> {
				TextAreaIn.appendText("From: " + port + " | " + "Message: " + message + "\n");
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}