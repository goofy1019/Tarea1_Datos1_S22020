package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ports {
	private static Scanner x;

	/**
	 * @param port Puerto a agregar.
	 * @param path Archivo en el que se quiere buscar. Guarda el nuevo port que va a
	 *             ser utilizado en un archivo.
	 */
	final static void SavePort(String port, String path) {
		try {
			FileWriter fw = new FileWriter(path, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.print(port + ",");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * @param term Puerto que se quiere buscar.
	 * @param path Archivo en el que se quiere buscar.
	 * @return boolean Lee que el puerto del parametro se encuentre en el archivo
	 *         indicado.
	 */
	final static boolean PortReader(String term, String path) {
		boolean found = false;
		boolean result = false;
		String port = "";
		try {
			x = new Scanner(new File(path));
			x.useDelimiter("[,]");
			while (x.hasNext() && !found) {
				port = x.next();
				if (port.equals(term)) {
					found = true;
				}
			}
			if (found) {
				result = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param path Archivo que se quiere limpiar. Metodo para eliminar los
	 *             contenidos del archivo especificado.
	 */
	final static void EndPorts(String path) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.close();
		return;
	}
}
