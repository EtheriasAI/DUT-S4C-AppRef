package appli;

/**
 * Application pour les amateurs
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientAma {
	
	public static int PORT_AMA = 3000;		//Port des amateurs
	private static String HOST="localhost";	//adresse a laquelle l'amateur se connecte

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Socket socket = new Socket(HOST, PORT_AMA);
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
		PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
		BufferedReader clavierClient = new BufferedReader(new InputStreamReader(System.in));	
		String val="init";
		//Boucle ayant pour objectif d'assurer la comunication
		while(true) {	
			val=sin.readLine();
			if(val==null) 
				break;
			System.out.println(val);
			String message = clavierClient.readLine();
			sout.println(message);
		}
		//On referme la socket
		socket.close();
		clavierClient.close();
	}
	
}