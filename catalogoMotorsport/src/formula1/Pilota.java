package formula1;

public class Pilota{

	private String nome;
	private String cognome;
	private String nazionalita;
	private int gareVinte;
	private int campionatiVinti;

	public Pilota(String nome, String cognome, String nazionalita, int gareVinte, int campionatiVinti) {

		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.gareVinte = gareVinte;
		this.campionatiVinti = campionatiVinti;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public int getGareVinte() {
		return gareVinte;
	}

	public void setGareVinte(int gareVinte) {
		this.gareVinte = gareVinte;
	}

	public int getCampionatiVinti() {
		return campionatiVinti;
	}

	public void setCampionatiVinti(int campionatiVinti) {
		this.campionatiVinti = campionatiVinti;
	}

    @Override
    public String toString() {
        return  nome + "," + cognome + "," + nazionalita + "," + gareVinte + "," + campionatiVinti;
    }
	
}
