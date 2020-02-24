import java.util.Scanner;

public class BankApplication {
	Scanner scan = new Scanner(System.in);
	Bank bank = new Bank();

	public static void main(String[] args) {
		BankApplication b = new BankApplication();
		b.writeMenu();
		b.runApplication();

	}

	public void runApplication() {
		int nbr;
		int input;
		boolean b = true;

		while (b) {
			input = scan.nextInt();
			System.out.println("Val: " + input);

			switch (input) {
			case 1: // sök konto utifrån innehavare (antar idNr?)
				System.out.println("Söker konton för nummer: ");
				nbr = scan.nextInt();
				if (bank.findAccountsForHolder(nbr) == null) {
					System.out.println("Inget konto med det numret existerar.");

				} else {
					System.out.println("Följande konton finns för kunden: " + bank.findAccountsForHolder(nbr));
				}
			case 2: // sök kontoinnehavare efter (del av) namn
				System.out.println("Söker konton för namn:");
				String namePart = scan.nextLine();
				if (bank.findByPartofName(namePart) == null) {
					System.out.println("Personen har inget konto.");

				} else {
					System.out.println("Konton: " + bank.findByPartofName(namePart));
				}

			case 3: // sätt in pengar

				System.out.println("Från konto: ");
				nbr = scan.nextInt();
				System.out.println("Belopp: ");
				double dep = scan.nextDouble();
				bank.findByNumber(nbr).deposit(dep);

			case 4: // ta ut pengar

				System.out.println("Från konto: ");
				nbr = scan.nextInt();
				System.out.println("Belopp: ");
				double wit = scan.nextDouble();
				if (bank.findByNumber(nbr).getAmount() < wit) {
					System.out.println(
							"Uttaget misslyckades. Endast " + bank.findByNumber(nbr).getAmount() + " på kontot!");

				} else {
					bank.findByNumber(nbr).withdraw(wit);
				}

			case 5: // överföring
				System.out.println("Från konto: ");
				int nbr1 = scan.nextInt();
				System.out.println("Till konto: ");
				int nbr2 = scan.nextInt();
				System.out.println("Belopp att överföra: ");
				double amount = scan.nextDouble();
				if (bank.findByNumber(nbr1).getAmount() < amount) {
					System.out.println(
							"Överföringen misslyckades. Endast " + bank.findByNumber(nbr1).getAmount() + " på kontot!");

				} else {
					bank.findByNumber(nbr1).withdraw(amount);
					bank.findByNumber(nbr2).deposit(amount);
				}

			case 6: // skapa nytt konto
				System.out.println("Namn på kontoinnehavare: ");
				String name = scan.nextLine();
				System.out.println("Personnummer: ");
				long idNr = scan.nextLong();
				System.out.println("Konto skapat. Kundnummer: " + bank.addAccount(name, idNr)); // ska det finnas några
																								// conditions här?

			case 7: // ta bort ett konto efter kundnummer
				System.out.println("Vilket kundnummer ska tas bort? : ");
				nbr = scan.nextInt();
				if (bank.removeAccount(nbr)) {
					System.out.println("Kontot har tagits bort.");
				} else {
					System.out.println("Inget konto för kundnumret kunde hittas.");
				}

			case 8: // skriv ut alla konton

				System.out.println(bank.getAllAccounts());

			case 9: // avsluta
				b = false;

			default:
				System.out.println("Inget menyval med det numret. Testa något i listan istället!"); // default verkar
																									// inte funka.

			}
		}

	}

	public void writeMenu() {

		System.out.println("1. Hitta konto utifrån innehavare");
		System.out.println("2. Sök kontoinnehavare utifrån (del av) namn");
		System.out.println("3. Sätt in");
		System.out.println("4. Ta ut");
		System.out.println("5. Överföring");
		System.out.println("6. Skapa konto");
		System.out.println("7. Ta bort konto");
		System.out.println("8. Skriv ut konton");
		System.out.println("9. Avsluta");

	}
}
