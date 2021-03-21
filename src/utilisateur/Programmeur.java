package utilisateur;

public class Programmeur implements Utilisateur {

	private String login;
	private String mdp;
	private String url;
	
	public Programmeur(String login, String mdp, String URL){
		this.login=login;
		this.mdp=mdp;
		url=URL;
	}
	
	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getMDP() {
		return mdp;
	}

	@Override
	public String getURL() {
		return url;
	}

	@Override
	public void setURL(String URL) {
		this.url=URL;
	}

}
