package catalogo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Abbonamento extends Registrazione {

	private Set<String> listaAbbonamenti = new TreeSet<>();
	private Map<String, Set<String>> utentiAbbonati = new HashMap<>();
	private boolean letturaUtente = false;
	private static final String[] cataloghi = { "Formula 1", "MotoGP", "SBK", "WEC" };

	public void letturaDatabaseAbbonamento() {
		try {
			Reader dbReader = new FileReader("database_abbonamenti.txt");
			BufferedReader bufferedDBR = new BufferedReader(dbReader);

			String riga;

			while ((riga = bufferedDBR.readLine()) != null) {
				String[] abbonamenti = riga.split(",");
				String nomeUtente = abbonamenti[0];

				if (!utentiAbbonati.containsKey(nomeUtente)) {
					utentiAbbonati.put(nomeUtente, new TreeSet<>());
				}
				Set<String> abbonamentiUtente = utentiAbbonati.get(nomeUtente);
				abbonamentiUtente.addAll(Arrays.asList(abbonamenti).subList(1, abbonamenti.length));
			}
			setLetturaUtente(true);
			listaAbbonamenti.addAll(utentiAbbonati.get(utente.getNomeUtente()));
			bufferedDBR.close();
		} catch (Exception e) {
			System.out.println("Nessun abbonamento sottoiscritto!");
		}
	}

	public void scritturaDatabaseAbbonamento() {
		String nomeUtente = utente.getNomeUtente();
		utentiAbbonati.put(nomeUtente, listaAbbonamenti);

		try {
			Writer databaseWriter = new FileWriter("database_abbonamenti.txt");

			for (Entry<String, Set<String>> entry : utentiAbbonati.entrySet()) {
				StringBuilder sbDatabase = new StringBuilder();
				for (String abbonamento : entry.getValue()) {
					sbDatabase.append(abbonamento).append(",");
				}
				if (sbDatabase.length() > 0) {
					sbDatabase.deleteCharAt(sbDatabase.length() - 1);
				}

				String abbonamenti = sbDatabase.toString();

				databaseWriter.write(entry.getKey() + "," + abbonamenti + "\n");
			}
			databaseWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void iscrizioneAbbonamento() {
		boolean fineIscrizione = false;

		while (!fineIscrizione) {
			System.out.println("Seleziona uno dei seguenti cataloghi: ");
			System.out.println("1 - Formula 1\n2 - MotoGP\n3 - SBK\n4 - WEC");

			int scelta = input.nextInt();

			if (scelta >= 1 && scelta <= 4) {
				String abbonamentoScelto = cataloghi[scelta - 1];
				listaAbbonamenti.add(abbonamentoScelto);

				System.out.println("Aggiungere altro?\n1 - Sì\n2 - No");
				int sceltaSiNo = input.nextInt();
				if (sceltaSiNo == 2) {
					fineIscrizione = true;
				}
			} else {
				System.out.println("Scelta non valida. Riprova.");
			}
		}
	}

	public void rimozioneAbbonamento() {
		boolean fineRimozione = false;

		while (!fineRimozione) {
			System.out.println("Seleziona uno dei seguenti cataloghi da rimuovere: ");
			System.out.println("1 - Formula 1\n2 - MotoGP\n3 - SBK\n4 - WEC");

			int scelta = input.nextInt();

			if (scelta >= 1 && scelta <= 4) {
				String abbonamentoScelto = cataloghi[scelta - 1];

				if (listaAbbonamenti.contains(abbonamentoScelto)) {
					listaAbbonamenti.remove(abbonamentoScelto);
					System.out.println("Abbonamento rimosso con successo.");
				} else {
					System.out.println("L'utente non è abbonato a questo servizio.");
				}

				System.out.println("Rimuovere altro?\n1 - Sì\n2 - No");
				int sceltaSiNo = input.nextInt();
				if (sceltaSiNo == 2) {
					fineRimozione = true;
				}
			} else {
				System.out.println("Scelta non valida. Riprova.");
			}
		}
		aggiornaDatabase();
	}

	private void aggiornaDatabase() {
		String nomeUtente = utente.getNomeUtente();
		utentiAbbonati.put(nomeUtente, listaAbbonamenti);

		try {
			Writer databaseWriter = new FileWriter("database_abbonamenti.txt");
			for (Entry<String, Set<String>> entry : utentiAbbonati.entrySet()) {
				StringBuilder sbDatabase = new StringBuilder();
				for (String abbonamento : entry.getValue()) {
					sbDatabase.append(abbonamento).append(",");
				}
				if (sbDatabase.length() > 0) {
					sbDatabase.deleteCharAt(sbDatabase.length() - 1);
				}

				String abbonamenti = sbDatabase.toString();
				databaseWriter.write(entry.getKey() + "," + abbonamenti + "\n");
			}
			databaseWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stampaAbbonamenti() {
		String nomeUtente = utente.getNomeUtente();

		if (utentiAbbonati.containsKey(nomeUtente)) {
			Set<String> abbonamentiUtente = utentiAbbonati.get(nomeUtente);
			String stampaAbbonamenti = abbonamentiUtente.toString();
			System.out.println("I tuoi abbonamenti sono: " + stampaAbbonamenti);
		} else {
			System.out.println("Nessun abbonamento trovato per l'utente: " + nomeUtente);
		}
	}

	public String getLetturaAbbonamenti(String lettura) {
		if (listaAbbonamenti.isEmpty()) {
			return "Lista abbonamenti vuota!";
		}
		for (String elemento : listaAbbonamenti) {
			switch (lettura) {
			case "Formula 1": {
				if ("Formula 1".equals(elemento))
					return "Formula 1";
			}
			case "MotoGP": {
				if ("MotoGP".equals(elemento))
					return "MotoGP";
			}
			case "SBK": {
				if ("SBK".equals(elemento))
					return "SBK";
			}
			case "WEC": {
				if ("WEC".equals(elemento))
					return "WEC";
			}
			}
		}
		return null;
	}

	public boolean getLetturaUtente() {
		return letturaUtente;
	}

	public void setLetturaUtente(boolean letturaUtente) {
		this.letturaUtente = letturaUtente;
	}
}
