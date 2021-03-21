package appli;

/**
 * Application pour les programmeurs
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
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientProg {

	public static int PORT_PROG = 1000;		//Port des programmeurs
	public static String HOST ="localhost";	//adresse a laquelle le programmeur se connecte
	
	public static void main(String[] args) throws UnknownHostException, IOException {
	
		@SuppressWarnings("resource")
		Socket socket = new Socket(HOST, PORT_PROG);		
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
		PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
		Scanner clavier = new Scanner(System.in);
		String val="init";
		//Boucle ayant pour objectif d'assurer la comunication
		while (true){
			val=sin.readLine();
			if(val==null) 
				break;
			System.out.println(val);
			sout.println(clavier.next());
			}
		//On referme la socket
		socket.close();
		clavier.close();
	}
}
