package appli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientprog {

	public static int PORT_PROG = 1000;
	public static String HOST ="localhost";
	
	public static void main(String[] args) throws UnknownHostException, IOException {
	
		@SuppressWarnings("resource")
		Socket socket = new Socket(HOST, PORT_PROG);
		
		BufferedReader sin = new BufferedReader (new InputStreamReader(socket.getInputStream ( )));
		PrintWriter sout = new PrintWriter (socket.getOutputStream ( ), true);
		Scanner clavier = new Scanner(System.in);
		
		while (!socket.isClosed()){
			System.out.println(sin.readLine());
			sout.println(clavier.next());
			System.out.println("prog");
			}
		socket.close();
		clavier.close();
	}
}
