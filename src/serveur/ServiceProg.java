package serveur;

/**
 * Service des programmeurs
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

import java.io.*;
import java.net.*;
import java.util.List;

import appli.AppliServeur;
import utilisateur.Utilisateur;

class ServiceProg implements Runnable {
	
	private Socket client;
	//Liste des programmeurs
	private List<Utilisateur> prog;
	
	
	ServiceProg(Socket socket) {
		client = socket;
		getList();
	}
	//initialise la liste
	private void getList() {
		prog = AppliServeur.initPro();		
	}

	public void run() {
		synchronized(this) {
			URLClassLoader urlcl = null;
			PrintWriter sout = null;
			BufferedReader sin =null;
			try {
			sout = new PrintWriter (client.getOutputStream ( ), true);		
			sin = new BufferedReader(new InputStreamReader(client.getInputStream()));	
			//Assure la connection
			sout.println("Entrer votre login");
			String l=sin.readLine();
			sout.println("entrer votre mot de passe");
			String m=sin.readLine();
			//On recupere l'utilisateur associe aux identifiants
			Utilisateur prog = connect(l,m);
			//On se connecte au serveur ftp du programmeur
			String fileNameURL = "ftp://"+prog.getURL();
			urlcl = URLClassLoader.newInstance(new URL[] {new URL(fileNameURL)});
			//Etat de la connection
			boolean appli = true;
			String a;
			//Tant que l'utilisateur ne se deconnecte
			while(appli) {
				//Envoie la liste des services
				sout.println("0 pour afficher les services "
					+"1 pour ajouter un service "
					+"2 pour mettre a jour un service "
					+"3 pour declarer changement d'adresse serveur ftp");
				//Choisi le service
				switch(sin.readLine()) {
					case"0":
					sout.println(ServiceRegistry.toStringue()
							+"continuer? (O/N)");
					//fermer le service
					a =sin.readLine();
					if(a.equals("N"))
						appli=false;
					break;
					case"1":
					//Ajouter un service
					sout.println("entrer le nom du service");
					try {
						String classeName = l+"."+sin.readLine();
						ServiceRegistry.addService(urlcl.loadClass(classeName).asSubclass(Service.class));
						} catch (ClassCastException e) {
							System.out.println("La classe doit implémenter bri.Service");
						}catch (ValidationException e) {
							System.out.println(e.getMessage());
						} catch (ClassNotFoundException e) {
							System.out.println("La classe n'est pas sur le serveur ftp dans home"); 
						}
						sout.println("continuer? (O/N)");
						//ferme le service
						a =sin.readLine();
						if(a.equals("N"))
							appli=false;
					break;
					case"2":
						//Mettre a jour le service
						sout.println("Saisir le nom du service a mettre a jour");
						String nomc=sin.readLine();
						String ans;
						if(ServiceRegistry.doesExist(nomc)) {
							urlcl.loadClass(prog.getLogin()+"."+nomc).asSubclass(Service.class);
							ans="maj effectuee";
						}else
							ans="maj non effectuee car n'existait pas";
						sout.println(ans+ " continuer? (O/N)");
						//ferme le service
						a =sin.readLine();
						if(a.equals("N"))
								appli=false;
					break;
					case"3":
						//Changer adresse ftp
						sout.println("Entrer votre nouvelle adresse ftp");
						String b=sin.readLine();
						prog.setURL(b);		
						fileNameURL = "ftp://"+prog.getURL();
						urlcl = URLClassLoader.newInstance(new URL[] {new URL(fileNameURL)});
					sout.println("continuer? (O/N)");
					a =sin.readLine();
					//ferme le service
					if(a.equals("N"))
							appli=false;
					break;
					}	
				}//fin while(appli)
			
			} catch (IOException | UserNotFoundException | ClassNotFoundException e) {e.printStackTrace();}
	
			try {client.close();} catch (IOException e2) {}
		}
	}
	/*
	 * @param in : String l login
	 * @parim in : String m mot de passe
	 * @return l'utilisateur associe aux identifiants
	 * @throw UserNotFoundException si il n'y a pas d'utilisateur associe	
	 */
	private Utilisateur connect(String l, String m) throws UserNotFoundException {
		boolean a=false;
		for(Utilisateur u : prog)
			if(l.equals(u.getLogin()))
				return u;
		if(!a)
			throw new UserNotFoundException("Le programmeur ou le mot de passe n'est pas bon");
		return null;
	}

	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}