/**
 * Contient les attributs et les méthodes d'un programmeur et implémente Utilisateur
 * 
 * @author GATIMEL CHARLES
 * @author BARTHELME JUSTINE
 * 
 * @version 1.0
 * 
 */

package utilisateur;

public class Programmeur implements Utilisateur {
	
	//attributs de connexion
	private String login;
	private String mdp;
	private String url;
	
	//constructeur basique
	public Programmeur(String login, String mdp, String URL){
		this.login=login;
		this.mdp=mdp;
		url=URL;
	}
	
	//Permet de renvoyer le login
	@Override
	public String getLogin() {
		return login;
	}
	
	
	//Permet de renvoyer le mdp
	@Override
	public String getMDP() {
		return mdp;
	}
	
	//Permet de renvoyer l'url de connexion
	@Override
	public String getURL() {
		return url;
	}
	
	//Permet de modifier l'url d'un programmeur
	@Override
	public void setURL(String URL) {
		this.url=URL;
	}

}
