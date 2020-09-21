package application;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {
	/**
	 * @param Port    Puerto al que se quiere mandar mensaje.
	 * @param IP      IP al que se quiere mandar mensaje.
	 * @param message Mensaje que se quiere mandar
	 * @throws IOException Crea un nuevo SocketClient y envia un mensaje al server.
	 */
	public static void NewClient(int Port, String IP, String message) throws IOException {
		Socket client = new Socket(IP, Port);
		OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
		writer.write(message + "%" + Integer.toString(ChatWindow.MyPortNum));
		writer.flush();
		client.close();
	}
}
