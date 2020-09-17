package application;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChatWindow extends StartWindow {

	protected static int MyPortNum = 0;

	String PortList = "PortList.txt";

	public static String InMessage = "";

	public static String InPort = "";

	@FXML
	private javafx.scene.control.Button ExitBtn;

	@FXML
	public void ExitBtnAction(ActionEvent event) {
		Stage window1 = (Stage) ExitBtn.getScene().getWindow();
		window1.close();
	}

	@FXML
	private javafx.scene.control.MenuItem MyPortBtn;

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
	private javafx.scene.control.MenuItem MyIPBtn;

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
	private javafx.scene.control.TextArea TextAreaSent;

	@FXML
	private javafx.scene.control.TextArea TextAreaIn;

	@FXML
	private javafx.scene.control.TextField NewMessageTF;

	@FXML
	private javafx.scene.control.TextField PortTF;

	@FXML
	private javafx.scene.control.TextField IPTF;

	@FXML
	private javafx.scene.control.Button SendBtn;

	@FXML
	public void SendBtnAction(ActionEvent event) {
		return;
	}

	@FXML
	private javafx.scene.control.Button ReBtn;

	@FXML
	public void ReBtnAction(ActionEvent event) {
		return;
	}

}
