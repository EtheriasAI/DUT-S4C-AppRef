package appli;

import java.io.IOException;

import bri.Serveur;

public class AppliServeur {
	
	private static int PORT_AMA=3000;
	private static int PORT_PROG=1000;
		
	public static void main(String[] args) {
		try {
			new Thread(new Serveur(PORT_AMA)).start();
			new Thread(new Serveur(PORT_PROG)).start();
			} catch (IOException e) {e.printStackTrace();
		}
	}
	
	//public List<IUtilisateur> init pro()
	
}