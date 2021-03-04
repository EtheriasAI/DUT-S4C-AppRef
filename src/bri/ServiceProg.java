package bri;


import java.io.*;
import java.net.*;


class ServiceProg implements Runnable {
	
	private Socket client;
	
	ServiceProg(Socket socket) {
		client = socket;
	}

	public void run() {
		synchronized(this) {	
			// URLClassLoader sur ftp
			String fileNameURL = "ftp://localhost:2121/";  // 
			URLClassLoader urlcl = null;
			PrintWriter sout = null;
			BufferedReader sin =null;
			try {
			urlcl = URLClassLoader.newInstance(new URL[] {new URL(fileNameURL)});		
			sout = new PrintWriter (client.getOutputStream ( ), true);		
			sin = new BufferedReader(new InputStreamReader(client.getInputStream()));	
					
			boolean appli = true;
			String a;
			while(appli) {
				sout.println("0 pour afficher les services 1 pour ajouter une activite");
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
						String classeName = sin.readLine();
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
					}	
				}//fin while(appli)
			
			} catch (IOException e) {e.printStackTrace();}
	
			try {client.close();} catch (IOException e2) {}
		}
	}
		
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}
