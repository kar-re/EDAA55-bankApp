import java.util.Scanner;
import java.util.ArrayList;

public class BankApplication {
	static Scanner scan = new Scanner(System.in);
	private static Bank bank = new Bank();
	private static boolean bool = true;
	private static boolean boolb = true;

	public static void main(String[] args) {
		CreateRandomAccs randomaccs = new CreateRandomAccs(bank.getAllAccounts());
		randomaccs.createAccounts("namn.txt", 10);
		BankApplication b = new BankApplication();
		while (boolb) {
			bool = true;
			while (bool) {
				b.writeMenu();
				b.runApplication();							
			}
			delay(2000);
		}

	}

	public void runApplication() {
		int nbr;
		int input;

			input = scan.nextInt();
			scan.nextLine();
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
			break;
			case 2: // sök kontoinnehavare efter (del av) namn
				System.out.println("Söker konton för namn:");
				String namePart = scan.nextLine();
				if (bank.findByPartofName(namePart) == null) {
					System.out.println("Personen har inget konto.");

				} else {
					System.out.println("Konton: " + bank.findByPartofName(namePart));
				}
				break;
			case 3: // sätt in pengar

				System.out.println("Till konto: ");
				nbr = scan.nextInt();
				System.out.println("Belopp: ");
				double dep = scan.nextDouble();
				BankAccount acc = bank.findByNumber(nbr);
				if (acc != null) {
					acc.deposit(dep);				
				}
				break;
			case 4: // ta ut pengar

				System.out.println("Från konto: ");
				nbr = scan.nextInt();
				System.out.println("Belopp: ");
				double wit = scan.nextDouble();
				if (bank.findByNumber(nbr).getAmount() < wit) {
					System.out.println(
							"Uttaget misslyckades. Endast " + bank.findByNumber(nbr).getAmount() + " kr på kontot!");

				} else {
					bank.findByNumber(nbr).withdraw(wit);
				}
				break;
			case 5: // överföring
				System.out.println("Från konto: ");
				int nbr1 = scan.nextInt();
				System.out.println("Till konto: ");
				int nbr2 = scan.nextInt();
				System.out.println("Belopp att överföra: ");
				double amount = scan.nextDouble();
				if (bank.findByNumber(nbr1).getAmount() < amount) {
					System.out.println(
							"Överföringen misslyckades. Endast " + bank.findByNumber(nbr1).getAmount() + " kr på kontot!");

				} else {
					bank.findByNumber(nbr1).withdraw(amount);
					bank.findByNumber(nbr2).deposit(amount);
				}
				break;
			case 6: // skapa nytt konto
				System.out.print("Namn på kontoinnehavare: ");
				String name = scan.nextLine();
				System.out.print("Personnummer: ");
				long idNr = scan.nextLong();
				System.out.println("Konto skapat. Kundnummer: " + bank.addAccount(name, idNr)); // ska det finnas några				
				break;
			case 7: // ta bort ett konto efter kundnummer
				System.out.println("Vilket kundnummer ska tas bort? : ");
				nbr = scan.nextInt();
				if (bank.removeAccount(nbr)) {
					System.out.println("Kontot har tagits bort.");
				} else {
					System.out.println("Inget konto för kundnumret kunde hittas.");
				}
				break;
			case 8: // skriv ut alla konton
				ArrayList<BankAccount> accounts, sortedAccounts;
				accounts = bank.getAllAccounts();
				nbr = 1000;
				for (BankAccount account : accounts) {
					
					System.out.println("Kontonr: " + (1000+accounts.indexOf(account)) + " " + account.toString());
					nbr += 1;
				}
				break;
			case 9: // avsluta
				boolb = false;
				break;
			default:
				System.out.println("Inget menyval med det numret. Testa något i listan istället!"); 
				break;																				
		}
		bool = false;

	}

	private void writeMenu() {
		
		System.out.println(" ");
		System.out.println("1. Hitta konto utifrån innehavare");
		System.out.println("2. Sök kontoinnehavare utifrån (del av) namn");
		System.out.println("3. Sätt in");
		System.out.println("4. Ta ut");
		System.out.println("5. Överföring");
		System.out.println("6. Skapa konto");
		System.out.println("7. Ta bort konto");
		System.out.println("8. Skriv ut konton");
		System.out.println("9. Avsluta");
		System.out.println(" ");

	}
	
	static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
