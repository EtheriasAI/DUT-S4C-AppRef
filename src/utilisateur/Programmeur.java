package utilisateur;

public class Programmeur implements Utilisateur {

	private String login;
	private String mdp;
	private boolean p;
	
	public Programmeur(String login, String mdp, boolean p){
		this.login=login;
		this.mdp=mdp;
		this.p=p;
	}
	
	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getMDP() {
		return mdp;
	}

}
