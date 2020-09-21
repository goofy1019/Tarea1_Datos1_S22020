package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	/**
	 * Variable que indica el estado del serverSocket
	 */
	static boolean active = true;

	/**
	 * @param Port Puerto al que se quiere asignar el server. Metodo utilizado para
	 *             crear un nuevo serverSocket y realizar el manejo adecuado de
	 *             recibir mensajes.
	 */
	public static void NewServer(int Port) {
		try {
			ServerSocket serverSocket = new ServerSocket(Port, 0, InetAddress.getByName(null));
			while (active) {
				System.out.println("Listening in port " + Integer.toString(Port) + " ... ");
				Socket entrante = serverSocket.accept();
				BufferedReader lector = new BufferedReader(new InputStreamReader(entrante.getInputStream()));
				String mensaje = lector.readLine();
				String[] MensPo = mensaje.split("%");
				String Mens = MensPo[0];
				String Po = MensPo[1];
				ChatWindow.InMessage = Mens;
				ChatWindow.InPort = Po;
				System.out.println("Mensaje recibido: " + Mens + " " + "De: " + Po);
				entrante.close();

			}
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
