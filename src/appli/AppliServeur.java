/**
 * Permet de lancer les threads des serveurs amateurs et programmeurs
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

package appli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import serveur.Serveur;
import utilisateur.Programmeur;
import utilisateur.Utilisateur;

public class AppliServeur {
	
	private static int PORT_AMA=3000;//Port du serveur amateur
	private static int PORT_PROG=1000;//Port du serveur programmeur
	
	/*Crée les threads des clients amateurs et des clients programmeurs
	à travers un try and catch et en utilisant le port correspondant	
	*/
	public static void main(String[] args) {
		try {
			new Thread(new Serveur(PORT_AMA)).start();
			new Thread(new Serveur(PORT_PROG)).start();
			} catch (IOException e) {e.printStackTrace();
		}
	}
	
	//Liste qui recense les programmeurs avec leurs attributs (identifiants, mdp et adresse ftp)
	public static List<Utilisateur> initPro(){
		List<Utilisateur> prog = new ArrayList<Utilisateur>();
		prog.add(new Programmeur("brette","brette","localhost:2020"));
		prog.add(new Programmeur("stage","stage","localhost:2323"));
		prog.add(new Programmeur("ecole","ecole","localhost:2222"));
		return prog;
	}
	
}
