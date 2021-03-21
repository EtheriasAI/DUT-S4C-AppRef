/**
 * Exception utilisé dans le cas où un utilisateur n'existe pas ou n'a pas été trouvé
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */
package serveur;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	//Message renvoyé dans l'exception lorsqu'il n'y a pas cet utilisateur
	public UserNotFoundException(String message) {
		super(message);
	}
}
