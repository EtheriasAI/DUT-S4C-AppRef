/**
 * interface qui sera implémenté par Programmeur et contient les méthodes nécessaires au programmeur
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */
package utilisateur;

public interface Utilisateur {

	String getLogin();//Renvoyer le login
	String getMDP();//Renvoyer le mdp
	String getURL();//Renvoyer l'url
	void setURL(String url);//Modifier l'url
	
}
