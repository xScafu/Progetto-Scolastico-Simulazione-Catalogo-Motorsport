package motogp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Scuderia {

	private String nome;
	private String motore;
	private String sede;
	private int costruttoriVinti;
	private List<Pilota> piloti = new ArrayList<>();

	public Scuderia(String nome, String motore, String sede, int costruttoriVinti) {
		this.nome = nome;
		this.motore = motore;
		this.sede = sede;
		this.costruttoriVinti = costruttoriVinti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMotore() {
		return motore;
	}

	public void setMotore(String motore) {
		this.motore = motore;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public int getCostruttoriVinti() {
		return costruttoriVinti;
	}

	public void setCostruttoriVinti(int costruttoriVinti) {
		this.costruttoriVinti = costruttoriVinti;
	}
	
	public void inizializzaPilota() {
		Scanner input = new Scanner(System.in);

		Pilota nuovoPilota = new Pilota(null, null, null, 0, 0);

		System.out.println("Inserire il nome del pilota:");
		nuovoPilota.setNome(input.nextLine());

		System.out.println("Inserire il cognome del pilota:");
		nuovoPilota.setCognome(input.nextLine());

		System.out.println("Inserire la nazionalità:");
		nuovoPilota.setNazionalita(input.nextLine());

		System.out.println("Inserire il numero di gare vinte:");
		nuovoPilota.setGareVinte(input.nextInt());

		System.out.println("Inserire il numero di campionati vinti:");
		nuovoPilota.setCampionatiVinti(input.nextInt());

		piloti.add(nuovoPilota);
	}

	public List<Pilota> getPiloti() {
		return piloti;
	}

	public void setPiloti(Pilota pilota) {
		piloti.add(pilota);
	}

	public void aggiungiPilota() {
	    if (piloti.size() < 2) {
	        inizializzaPilota();
	    } else {
	        System.out.println("Numero massimo di piloti raggiunto per questa scuderia.");
	    }
	}
	
	@Override
	public String toString() {
		return "Scuderia [nome=" + nome + ", motore=" + motore + ", sede=" + sede + ", costruttoriVinti="
				+ costruttoriVinti + ", piloti=" + piloti + "]";
	}
	
}
