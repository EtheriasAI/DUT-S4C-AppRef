package ecole;

import java.io.*;
import java.net.*;

import serveur.Service;

public class ServiceRepetition implements Service {
    
    private final Socket client;
    
    public ServiceRepetition(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
            PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
    
            out.println("je suis chiant donc je vais repeter tout ce que tu dis");
            
            while(true) {
                String line = in.readLine();
                if(line.equals("stop"))client.close();
                out.println(line);
            }
        }
        catch (IOException e) {
            //Fin du service de répétition
            System.out.println("Probleme Probleme");
        }
    }

	protected void finalize() throws Throwable {
		 client.close(); 
	}

	public static String toStringue() {
		return "Repetition de texte";
	}

}