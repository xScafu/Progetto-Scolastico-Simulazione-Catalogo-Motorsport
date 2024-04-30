package catalogo;

public class Utente {

	private String nomeUtente;
	private String password;
	private boolean admin;

	public Utente(String nomeUtente, String password, boolean admin) {

		this.nomeUtente = nomeUtente;
		this.password = password;
		this.setAdmin(admin);
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
