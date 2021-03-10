package serveur;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;


class ServiceAma implements Runnable {
	
	private Socket client;
	
	ServiceAma(Socket socket) {
		client = socket;
	}

	public void run() {
		try {BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println(ServiceRegistry.toStringue()+"Tapez le num�ro de service d�sir� :");
			int choix = Integer.parseInt(in.readLine());
			Class<? extends Service> classe = ServiceRegistry.getServiceClass(choix);
			
			try {
				Constructor<? extends Service> niou = classe.getConstructor(java.net.Socket.class);
				Service service = niou.newInstance(this.client);
				service.run();
				/*Method runne = classe.getMethod("run");
				runne.invoke(service);*/
			} catch (SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException | NoSuchMethodException e) {
				System.out.println(e);
			}
			}
		catch (IOException e) {
			//Fin du service
		}

		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}