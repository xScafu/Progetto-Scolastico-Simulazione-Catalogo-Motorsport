package wec;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CampionatoWEC {

	private List<Scuderia> scuderie = new ArrayList<>();
	private List<Scuderia> scuderiaAttiva = new ArrayList<>();

	public void letturaDatabase() {
		try {
			scuderie.clear();
			Reader databaseReader = new FileReader("database_campionatoWEC.txt");
			BufferedReader bufferedReader = new BufferedReader(databaseReader);

			String riga;
			while ((riga = bufferedReader.readLine()) != null) {
				String[] valori = riga.split(",");
				Scuderia scuderia = new Scuderia(valori[0], valori[1], valori[2], Integer.parseInt(valori[3]));

				Pilota pilota1 = new Pilota(valori[4], valori[5], valori[6], Integer.parseInt(valori[7]),
						Integer.parseInt(valori[8]));
				scuderia.setPiloti(pilota1);

				Pilota pilota2 = new Pilota(valori[9], valori[10], valori[11], Integer.parseInt(valori[12]),
						Integer.parseInt(valori[13]));
				scuderia.setPiloti(pilota2);

				scuderie.add(scuderia);
				
			}
			
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scritturaDatabase() {
		try {
			Writer databaseWriter = new FileWriter("database_campionatoWEC.txt");

			for (Scuderia scuderia : scuderie) {
				StringBuilder rigaScuderia = new StringBuilder();
				rigaScuderia.append(scuderia.getNome()).append(",").append(scuderia.getMotore()).append(",")
						.append(scuderia.getSede()).append(",").append(scuderia.getCostruttoriVinti()).append(",")
						.append(scuderia.getPiloti().toString().substring(1,
								scuderia.getPiloti().toString().length() - 1));

				databaseWriter.write(rigaScuderia.toString() + "\n");
			}

			databaseWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inizializzaScuderia() {
		Scanner input = new Scanner(System.in);

		Scuderia nuovaScuderia = new Scuderia(null, null, null, 0);

		nuovaScuderia = new Scuderia(null, null, null, 0);

		System.out.println("Inserire il nome della scuderia:");
		nuovaScuderia.setNome(input.nextLine());

		System.out.println("Inserire la motorizzazione della scuderia:");
		nuovaScuderia.setMotore(input.nextLine());

		System.out.println("Inserire la sede della scuderia");
		nuovaScuderia.setSede(input.nextLine());

		System.out.println("Inserire il numero di costruttori vinti:");
		nuovaScuderia.setCostruttoriVinti(input.nextInt());

		nuovaScuderia.inizializzaPilota();
		System.out.println(nuovaScuderia.getPiloti().toString());
		nuovaScuderia.aggiungiPilota();
		System.out.println(nuovaScuderia.getPiloti().toString());

		scuderie.add(nuovaScuderia);
	}

	public void stampaScuderia(String nomeScuderia) {
		for (Scuderia scuderia : scuderiaAttiva) {
			if (scuderia.getNome().equals(nomeScuderia)) {
				System.out.println("");
				System.out.println("SCUDERIA:");
				System.out.println("Nome: " + scuderia.getNome());
				System.out.println("Motore: " + scuderia.getMotore());
				System.out.println("Sede: " + scuderia.getSede());
				System.out.println("Costruttori Vinti: " + scuderia.getCostruttoriVinti());
				System.out.println("");
				System.out.println("PILOTI DELLA SCUDERIA:");
				for (Pilota pilota : scuderia.getPiloti()) {
					System.out.println("Nome Pilota: " + pilota.getNome());
					System.out.println("Cognome Pilota: " + pilota.getCognome());
					System.out.println("Nazionalità: " + pilota.getNazionalita());
					System.out.println("Gare Vinte: " + pilota.getGareVinte());
					System.out.println("Campionati Vinti: " + pilota.getCampionatiVinti());
					System.out.println("-------------------");
				}
			} else {
				System.out.println("Scuderia non trovata con il nome: " + nomeScuderia);
			}
		}

	}

	public boolean verificaCataloghi() {

		if (scuderie.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void verificaScuderia() {
		Scanner input = new Scanner(System.in);
		String nomeScuderia = input.nextLine();
		boolean scuderiaTrovata = false;
		scuderiaAttiva.clear();
		
		for (Scuderia scuderia : scuderie) {
			if (scuderia.getNome().equals(nomeScuderia)) {
				scuderiaAttiva.add(scuderia);
				scuderiaTrovata = true;
			}
		}
		if (scuderiaTrovata) {
			stampaScuderia(nomeScuderia);
		} else {
			System.out.println("Nessuna scuderia trovata con il nome " + nomeScuderia + ".");
		}
	}

}
