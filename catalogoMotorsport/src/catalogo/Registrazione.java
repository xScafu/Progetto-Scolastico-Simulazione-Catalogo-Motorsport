package catalogo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Registrazione {

	protected static Map<String, String> admin = new HashMap<>();
	protected static Map<String, String> utenti = new HashMap<>();
	protected static Utente utente = new Utente(null, null, false);
	protected static Scanner input = new Scanner(System.in);

	public void letturaDatabase() {

		try {

			Reader databaseReader = new FileReader("database_utenti.txt");
			BufferedReader bufferedDR = new BufferedReader(databaseReader);

			String riga;
			while ((riga = bufferedDR.readLine()) != null) {

				String[] valori = riga.split(",");
				String checkAdmin = valori[2];
				utenti.put(valori[0], valori[1]);
				if (checkAdmin.equals("as")) {
					admin.put(valori[0], valori[2]);
					utente.setAdmin(true);
				} else {
					utente.setAdmin(false);
				}
			}

			bufferedDR.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void registrazioneUtente() {

		System.out.println("Registrazione in corso...");

		System.out.println("Impostare un nome utente:");
		String nomeUtente = input.nextLine();
		utente.setNomeUtente(nomeUtente);

		System.out.println("Impostare una password:");
		String password = input.nextLine();
		utente.setPassword(password);

		if (utenti.containsKey(nomeUtente)) {

			System.out.println("Errore, nome utente già registrato. Riprovare.\n");

		} else {
			utenti.put(nomeUtente, password);
			System.out.println("Registrazione avvenuta con successo!\n");
		}
	}

	public void impostaAdmin(boolean isAdmin) {

		if (isAdmin) {
			admin.put(utente.getNomeUtente(), "as");
		} else {
			admin.remove(utente.getNomeUtente());
		}
	}

	public void scritturaDatabase() {

		try {

			Writer databaseWriter = new FileWriter("database_utenti.txt");

			for (Map.Entry<String, String> entry : utenti.entrySet()) {

				String isAdmin = admin.containsKey(entry.getKey()) ? "as" : "an";
				databaseWriter.write(entry.getKey() + "," + entry.getValue() + "," + isAdmin + "\n");
			}
			databaseWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
