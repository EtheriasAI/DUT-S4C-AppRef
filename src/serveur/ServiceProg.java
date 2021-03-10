package serveur;


import java.io.*;
import java.net.*;
import java.util.List;

import appli.AppliServeur;
import utilisateur.Utilisateur;


class ServiceProg implements Runnable {
	
	private Socket client;
	private List<Utilisateur> prog;
	
	
	ServiceProg(Socket socket) {
		client = socket;
		getList();
	}

	private void getList() {
		prog = AppliServeur.initPro();		
	}

	public void run() {
		synchronized(this) {	
			// URLClassLoader sur ftp
			
			URLClassLoader urlcl = null;
			PrintWriter sout = null;
			BufferedReader sin =null;
			try {
			
			sout = new PrintWriter (client.getOutputStream ( ), true);		
			sin = new BufferedReader(new InputStreamReader(client.getInputStream()));	
			
			sout.println("Entrer votre login");
			String l=sin.readLine();
			sout.println("entrer votre mot de passe");
			String m=sin.readLine();
			Utilisateur prog = connect(l,m);
			String fileNameURL = "ftp://"+prog.getURL();
			
			urlcl = URLClassLoader.newInstance(new URL[] {new URL(fileNameURL)});
			
			boolean appli = true;
			String a;
			
			while(appli) {
				sout.println("0 pour afficher les services "
					+"1 pour ajouter un service "
					+"2 pour mettre a jour un service "
					+"3 pour declarer changement d'adresse serveur ftp");
				
				switch(sin.readLine()) {
					case"0":

					sout.println(ServiceRegistry.toStringue()
							+"continuer? (O/N)");

					a =sin.readLine();
					if(a.equals("N"))
						appli=false;
					break;
					case"1":
					
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
						a =sin.readLine();
						if(a.equals("N"))
							appli=false;
					break;
					case"2":
						sout.println("Saisir numero du service a mettre a jour");
						String nomc=sin.readLine();
						String ans;
						if(ServiceRegistry.doesExist(nomc)) {
							urlcl.loadClass(prog.getLogin()+"."+nomc).asSubclass(Service.class);
							ans="maj effectuee";
						}else
							ans="maj non effectuee car n'existait pas";
						sout.println(ans+ " continuer? (O/N)");
						a =sin.readLine();
						if(a.equals("N"))
								appli=false;
					break;
					case"3":
						sout.println("Entrer votre nouvelle adresse ftp");
						String b=sin.readLine();
						prog.setURL(b);		
						fileNameURL = "ftp://"+prog.getURL();
						urlcl = URLClassLoader.newInstance(new URL[] {new URL(fileNameURL)});
					sout.println("continuer? (O/N)");
					a =sin.readLine();
					if(a.equals("N"))
							appli=false;
					break;
					}	
				}//fin while(appli)
			
			} catch (IOException | UserNotFoundException | ClassNotFoundException e) {e.printStackTrace();}
	
			try {client.close();} catch (IOException e2) {}
		}
	}
		
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