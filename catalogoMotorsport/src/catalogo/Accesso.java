package catalogo;

public class Accesso extends Registrazione {

	public void accessoCredenziali() {

		boolean stop = false;

		while (stop == false) {
			letturaDatabase();
			System.out.println("Inserire nome utente:");
			String nomeUtente = input.nextLine();
			utente.setNomeUtente(nomeUtente);
			if (utenti.containsKey(nomeUtente)) {
				System.out.println("Inserire una password:");
				String password = input.nextLine();
				utente.setPassword(password);

				if (utenti.containsValue(password)) {
					utente.setPassword(password);
					System.out.println("Accesso effettuato!");
					System.out.println("Benvenuto " + utente.getNomeUtente());

					stop = true;
				} else {
					System.out.println("Password errata, riprovare");
				}
			} else {
				System.out.println("Nome utente errato, riprovare");
			}

		}

	}

	public boolean accessoAdmin() {

		String nomeUtente = utente.getNomeUtente();

		if ((admin.containsKey(nomeUtente) == utenti.containsKey(nomeUtente)) && admin.containsValue("as")) {
			utente.setAdmin(true);
			return true;

		} else {
			return false;
		}

	}
}
