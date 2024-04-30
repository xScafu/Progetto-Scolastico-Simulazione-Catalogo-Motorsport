package catalogo;

import java.util.Scanner;

import formula1.CampionatoFormula1;
import motogp.CampionatoMotoGP;
import sbk.CampionatoSBK;
import wec.CampionatoWEC;

public class Principale {

	public static void benvenuto() {

		System.out.println("Benvenuto Utente, scegli l'azione che vuoi effettuare: \n1 - Registrazione\n2 - Login");

	}

	public static void utenteCredenziali() {

		System.out.println(
				"Scegli l'azione che vuoi effettuare: \n1 - Sfoglia catalogo\n2 - Modifica abbonamento\n3 - Logout");

	}

	public static void utenteAdmin() {

		System.out.println("Hai le credenziali da Admin");
		System.out.println(
				"Scegli l'azione che vuoi effettuare: \n1 - Sfoglia catalogo\n2 - Modifica abbonamento\n3 - Modifica catalogo\n4 - Logout");

	}

	public static void utenteAbbonamento() {

		System.out.println("Quali modifiche vuoi fare?:");
		System.out.println(
				"1 - Aggiungi iscrizione\n2 - Rimuovi iscrizione\n3 - Controlla iscrizioni\n4 - Torna indietro");
	}

	public static void sfogliaCatalogo() {

		System.out.println("Quale catalogo si vuole sfogliare?:");
		System.out.println("1 - Formula 1\n2 - MotoGP\n3 - SBK\n4 - WEC");

	}

	public static void sfogliaScuderie() {

		System.out.println("Quale Scuderia si vuole visionare? Scrivere il nome come riportato di seguito: ");
		System.out.println(
				"- Ferrari\n- Mercedes AMG\n- Red Bull\n- McLaren\n- Alfa Romeo\n- Aston Martin\n- Haas\n- Williams\n- Alpine\n- Alpha Tauri");

	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		Registrazione registrazione = new Registrazione();
		Accesso accesso = new Accesso();
		Abbonamento abbonamento = new Abbonamento();
		CampionatoFormula1 campionatoFormula1 = new CampionatoFormula1();
		CampionatoMotoGP campionatoMotoGP = new CampionatoMotoGP();
		CampionatoSBK campionatoSBK = new CampionatoSBK();
		CampionatoWEC campionatoWEC = new CampionatoWEC();

		boolean run = true;
		boolean benvenuto = true;

		while (run) {
			while (benvenuto) {

				benvenuto();

				int scelta = input.nextInt();

				if (scelta == 1) {
					registrazione.letturaDatabase();
					registrazione.registrazioneUtente();
					registrazione.scritturaDatabase();
				}

				if (scelta == 2) {
					accesso.accessoCredenziali();
					benvenuto = false;
				}
			}

			while (!benvenuto) {
				if (accesso.accessoAdmin() == true) {
					utenteAdmin();
				} else {
					utenteCredenziali();
				}

				int scelta = input.nextInt();

				if (scelta == 1) {

					try {
						abbonamento.letturaDatabaseAbbonamento();
						campionatoFormula1.letturaDatabase();

						if (campionatoFormula1.verificaCataloghi() == true) {
							sfogliaCatalogo();

							int sceltaCatalogo = input.nextInt();

							if (sceltaCatalogo == 1
									&& abbonamento.getLetturaAbbonamenti("Formula 1").equals("Formula 1")) {

								sfogliaScuderie();

								campionatoFormula1.verificaScuderia();
							}
							if (sceltaCatalogo == 2 && abbonamento.getLetturaAbbonamenti("MotoGP").equals("MotoGP")) {
								System.out.println("Catalogo MotoGP ancora da compilare.");
							}
							if (sceltaCatalogo == 3 && abbonamento.getLetturaAbbonamenti("SBK").equals("SBK")) {
								System.out.println("Catalogo SBK ancora da compilare.");
							}
							if (sceltaCatalogo == 4 && abbonamento.getLetturaAbbonamenti("WEC").equals("WEC")) {
								System.out.println("Catalogo WEC ancora da compilare.");
							}
						} else {
							System.out.println("Database cataloghi vuoto!");
						}
					} catch (Exception e) {
						System.out.println("Catalogo non compreso nel tuo abbonamento");

					}
				}
				if (scelta == 2) {

					abbonamento.letturaDatabaseAbbonamento();

					boolean runAbbonamenti = true;

					while (runAbbonamenti) {

						utenteAbbonamento();

						int sceltaAbbonamenti = input.nextInt();

						if (sceltaAbbonamenti == 1) {
							abbonamento.iscrizioneAbbonamento();
							abbonamento.scritturaDatabaseAbbonamento();
						}
						if (sceltaAbbonamenti == 2) {
							abbonamento.rimozioneAbbonamento();
							abbonamento.scritturaDatabaseAbbonamento();
						}
						if (sceltaAbbonamenti == 3) {
							abbonamento.letturaDatabaseAbbonamento();
							abbonamento.stampaAbbonamenti();

						}
						if (sceltaAbbonamenti == 4)
							runAbbonamenti = false;
					}

				}

				if (scelta == 3) {
					benvenuto = true;
				}
				if (scelta == 3 && accesso.accessoAdmin()) {
					System.out.println("Quale catalogo si vuole modificare?:");
					System.out.println("1 - Formula 1\n2 - MotoGP\n3 - SBK\n4 - WEC");

					int sceltaCatalogo1 = input.nextInt();
					input.nextLine();

					switch (sceltaCatalogo1) {

						case 1:
							campionatoFormula1.letturaDatabase();
							boolean continuaModifica = true;
							while (continuaModifica) {
								System.out.println("1 - Aggiungi scuderia\n2 - Esci");

								int sceltaModifica = input.nextInt();
								input.nextLine();

								if (sceltaModifica == 1) {
									campionatoFormula1.inizializzaScuderia();
									campionatoFormula1.scritturaDatabase();
									System.out.println("Modifica completata.");
								} else {
									continuaModifica = false;
								}
							}
							break;

						case 2:
							campionatoMotoGP.letturaDatabase();
							boolean continuaModifica1 = true;
							while (continuaModifica1) {
								System.out.println("1 - Aggiungi scuderia\n2 - Esci");

								int sceltaModifica = input.nextInt();
								input.nextLine();

								if (sceltaModifica == 1) {
									campionatoMotoGP.inizializzaScuderia();
									campionatoMotoGP.scritturaDatabase();
									System.out.println("Modifica completata.");
								} else {
									continuaModifica1 = false;
								}
							}
							break;

						case 3:
							campionatoSBK.letturaDatabase();
							boolean continuaModifica2 = true;
							while (continuaModifica2) {
								System.out.println("1 - Aggiungi scuderia\n2 - Esci");

								int sceltaModifica = input.nextInt();
								input.nextLine();

								if (sceltaModifica == 1) {
									campionatoSBK.inizializzaScuderia();
									campionatoSBK.scritturaDatabase();
									System.out.println("Modifica completata.");
								} else {
									continuaModifica2 = false;
								}
							}
							break;

						case 4:
							campionatoWEC.letturaDatabase();
							boolean continuaModifica3 = true;
							while (continuaModifica3) {
								System.out.println("1 - Aggiungi scuderia\n2 - Esci");

								int sceltaModifica = input.nextInt();
								input.nextLine();

								if (sceltaModifica == 1) {
									campionatoWEC.inizializzaScuderia();
									campionatoWEC.scritturaDatabase();
									System.out.println("Modifica completata.");
								} else {
									continuaModifica3 = false;
								}
							}
							break;

						default:
							System.out.println("Scelta del catalogo non valida.");
							break;
					}

					benvenuto = false;

					if (scelta == 4 && accesso.accessoAdmin() == true) {
						benvenuto = true;
					}
				}
			}
		}
	}
}
