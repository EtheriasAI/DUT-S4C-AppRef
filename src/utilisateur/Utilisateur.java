package utilisateur;

public class Utilisateur implements IUtilisateur {

	private String login;
	private String mdp;
	private boolean p;
	
	public Utilisateur(String login, String mdp, boolean p){
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

	@Override
	public Boolean isProgammeur() {
		return p;
	}

}
