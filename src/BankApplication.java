import java.util.Scanner;
import java.util.ArrayList;

public class BankApplication {
	/*
	 * Scanner för input, Statisk instansiering för bank, för att kunna nå den
	 * överallt Två booleans, en för menyloopen, en för programloopen
	 */
	static Scanner scan = new Scanner(System.in);
	private static Bank bank = new Bank();
	private static boolean bool = true, boolb = true;

	/*
	 * Mainmetod som anropar meny-loopen.
	 */
	public static void main(String[] args) {
		CreateRandomAccs randomaccs = new CreateRandomAccs(10, "namn.txt");
		for (int i = 0; i < randomaccs.getYears().size(); i++) {
			bank.addAccount(randomaccs.getNames().get(i), randomaccs.getYears().get(i));
		}

		BankApplication b = new BankApplication();
		while (boolb) {
			bool = true;
			while (bool) {
				b.writeMenu();
				b.runApplication();
			}
//			Delay så att användaren kan se vad som har hänt
			delay(2000);
		}

	}

	/*
	 * Huvudmetod som går igenom menyn, och gör vad användaren vill. Bröt inte ut
	 * några metoder pga
	 */
	public void runApplication() {
		int nbr;
		int input = 0;

	
		boolean b = false;
		while (!b) {
			if (scan.hasNextInt()) {
				input = scan.nextInt();
				b = true;
			} else {
				scan.nextLine();
				System.out.println("Skriv in en giltigt siffra"); //felmeddelande skrivs ut dubbelt ibland
			}
		}

		scan.nextLine();
		System.out.println("Val: " + input);

		switch (input) {

		case 1: // sök konto utifrån innehavare (antar idNr?)
			System.out.println("Söker konton för personnummer: ");
			b = false;
			nbr = 0;
			while (!b) {
				if (scan.hasNextInt()) {
					nbr = scan.nextInt();
					b = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt personnummer");
				}
			}

			if (bank.findAccountsForHolder((long) nbr).isEmpty()) {
				System.out.println("Inget konto existerar för personnumret " + nbr);
			} else {
				System.out.println("Följande konton finns för kunden: " + bank.findAccountsForHolder((long) nbr));
//				Object foundAccounts = ;
//				for (Object t : foundAccounts) {
//					t.toString();
//				}
			}

			break;

		case 2: // sök kontoinnehavare efter (del av) namn
			System.out.println("Söker konton för namn:");
			String namePart = "";
			b = false;
			while (!b) {
				if (scan.hasNextInt()) {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt namn");
				} else {
					namePart = scan.nextLine();
					b = true;
				}
			}

			if (bank.findByPartofName(namePart) == null) {
				System.out.println("Inget konto kunde hittas");

			} else {

				System.out.println("Konton: " + bank.findByPartofName(namePart));
			}
			break;

		case 3: // sätt in pengar

			System.out.println("Sätt in pengar till konto: ");
			b = false;
			nbr = 0;
			while (!b) {
				if (scan.hasNextInt()) {
					nbr = scan.nextInt();
					b = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt kontonummer");
				}
			}
			System.out.println("Belopp: ");
			double dep = scan.nextDouble();

			while (dep <= 0) {
				System.out.println("Du måste ange ett positivt belopp! Ange nytt belopp: ");
				dep = scan.nextDouble();
			}
			if (doesExist(nbr)) {
				bank.findByNumber(nbr).deposit(dep);
				System.out.println("Saldo för konto " + nbr + ": " + bank.findByNumber(nbr).getAmount() + " kr");
			} else {
				System.out.println("Kontot existerar inte. Använd menyval 8 för att se vilka konton som finns.");
			}

			break;

		case 4: // ta ut pengar

			System.out.println("Ta ut pengar från konto: ");
			b = false;
			nbr = 0;
			while (!b) {
				if (scan.hasNextInt()) {
					nbr = scan.nextInt();
					b = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt kontonummer");
				}
			}
			System.out.println("Belopp: ");
			double wit = scan.nextDouble();
			while (wit <= 0) {
				System.out.println("Du måste ange ett positivt belopp! Ange nytt belopp: ");
				wit = scan.nextDouble();
			}

			if (doesExist(nbr)) {

				if (bank.findByNumber(nbr).getAmount() < wit) {
					System.out.println(
							"Uttaget misslyckades. Endast " + bank.findByNumber(nbr).getAmount() + " kr på kontot!");

				} else {
					bank.findByNumber(nbr).withdraw(wit);
					System.out.println(
							"Saldo för konto " + nbr + " efter uttag: " + bank.findByNumber(nbr).getAmount() + " kr");
				}
			} else {
				System.out.println("Kontot existerar inte. Använd menyval 8 för att se vilka konton som finns.");
			}

			break;

		case 5: // överföring
			b=false;
			double amount=0;
			boolean b1 = false;
			boolean b2 = false;
			int nbr1 = 0;
			int nbr2 = 0;
			System.out.println("Från konto: ");
			while (!b1) {
				if (scan.hasNextInt()) {
					nbr1 = scan.nextInt();
					b1 = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt kontonummer");
				}
			}
			System.out.println("Till konto: ");
			while (!b2) {
				if (scan.hasNextInt()) {
					nbr2 = scan.nextInt();
					b2 = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt kontonummer"); //felmeddelandet skrivs ut två gånger. 
				}
			}
			System.out.println("Belopp att överföra: ");
			
			while(!b) {
				if(scan.hasNextDouble()) {
					amount = scan.nextDouble();
					b=true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt belopp");
				}
			} 

			if (doesExist(nbr1) && doesExist(nbr2)) {
				if (bank.findByNumber(nbr1).getAmount() < amount) {
					System.out.println("Överföringen misslyckades. Endast " + bank.findByNumber(nbr1).getAmount()
							+ " kr på kontot!");

				} else {
					bank.findByNumber(nbr1).withdraw(amount);
					bank.findByNumber(nbr2).deposit(amount);
					System.out.println("Överföring lyckad. Saldo för konto " + nbr1 + ": "
							+ bank.findByNumber(nbr1).getAmount() + "kr , saldo för konto " + nbr2 + ": "
							+ bank.findByNumber(nbr2).getAmount() + " kr");
				}
			} else {
				System.out.println(
						"Någon av kontona existerar inte. Använd menyval 8 för att se vilka konton som finns.");
			}
			break;
		case 6: // skapa nytt konto
			System.out.print("Namn på kontoinnehavare: ");
			String name = "";
			b = false;
//			while (!b) {
//				if (scan.hasNextInt()) {
//					scan.nextLine();
//					System.out.println("Skriv in ett giltigt namn");
//				} else {
//					namePart = scan.next();
//					b = true;
//				}
//			}
			while (scan.hasNextInt()) {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt namn");
			}
			name = scan.next();
			System.out.print("Personnummer: ");
			long idNr = 0;
				while (!scan.hasNextLong()) {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt personnummer");
				}
			idNr = scan.nextLong();
			System.out.println("Konto skapat. Kontonummer: " + bank.addAccount(name, idNr));
			break;

		case 7: // ta bort ett konto efter kundnummer
			System.out.println("Vilket kundnummer ska tas bort? ");
			b=false;
			nbr=0;
			while (!b) {
				if (scan.hasNextInt()) {
					nbr = scan.nextInt();
					b = true;
				} else {
					scan.nextLine();
					System.out.println("Skriv in ett giltigt kontonummer"); //felmeddelandet skrivs ut två gånger. 
				}
			}
			
			if (bank.removeAccount(nbr)) {
				System.out.println("Kontot har tagits bort.");
			} else {
				System.out.println("Inget konto för kundnumret kunde hittas.");
			}
			break;

		case 8: // skriv ut alla konton
			ArrayList<BankAccount> accounts;
			accounts = sortList(bank.getAllAccounts());
			if (accounts.size() == 0) {
				System.out.println("Det finns inga konton ännu");
			} else {
				for (BankAccount account : accounts) {
					System.out.println(account.toString());

				}

			}

			break;

		case 9: // avsluta
			boolb = false;
			System.out.println("Session avslutas...");
			break;
		default:
			System.out.println("Inget menyval med det numret. Testa något i listan istället!");
			break;
		}
		bool = false;

	}

	public ArrayList<BankAccount> sortList(ArrayList<BankAccount> list) {
		ArrayList<BankAccount> sortedAcc = new ArrayList<BankAccount>(list);
		BankAccount tempAcc;

		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (sortedAcc.get(i).getHolder().getName().compareTo(sortedAcc.get(j).getHolder().getName()) > 0) {
					tempAcc = sortedAcc.get(i);
					sortedAcc.set(i, sortedAcc.get(j));
					sortedAcc.set(j, tempAcc);

				}
			}
		}
		return sortedAcc;
	}

	public boolean doesExist(int nbr) {
		boolean b = true;

		if (bank.findByNumber(nbr) == null) {
			b = false;
		}
		return b;
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
