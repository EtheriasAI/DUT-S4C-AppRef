package serveur;


import java.io.*;
import java.net.*;


public class Serveur implements Runnable {
	private ServerSocket listen_socket;
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public Serveur(int port) throws IOException {
		listen_socket = new ServerSocket(port);
	}

	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	public void run() {
		try {
			/*Si c'est 3000 c'est pour les amateurs
			 * Sinon c'est un programeur
			 */
			while(true) {
				if(listen_socket.getLocalPort()==3000)
					new ServiceAma(listen_socket.accept()).start();
				else
					new ServiceProg(listen_socket.accept()).start();
			}
		}
		catch (IOException e) { 
			try {this.listen_socket.close();} catch (IOException e1) {}
			System.err.println("Pb sur le port d'�coute :"+e);
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}

	// lancement du serveur
	public void lancer() {
		(new Thread(this)).start();		
	}
}
