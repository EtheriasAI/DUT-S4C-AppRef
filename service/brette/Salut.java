package brette;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import bri.Service;

public class Salut implements Service {

	private final Socket client;
	
	public Salut(Socket socket) {
		client=socket;
	}
	
	@Override
	public void run() {
			try {
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
	
			out.println("Salut");
		
			client.close();
		}
		catch (IOException e) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	public static String toStringue() {
		return "Salut";
	}
}
