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
		/*
		 * Skapar slumpmässiga konton från en lista med namn, för testsyften.
		 */
		CreateRandomAccs randomaccs = new CreateRandomAccs(10, "namn.txt");
		for (int i = 0; i < randomaccs.getAmount(); i++) {
			bank.addAccount(randomaccs.getNames().get(i), randomaccs.getYears().get(i));
		}

		BankApplication b = new BankApplication();
		/*
		 * Två booleans som kontrollerar applikationens menyloopen, och själva
		 * applikationen i sig. boolb är den som kontrollerar applikationen, bool
		 * menyloopen
		 */
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

	/**
	 * Huvudmetod som går igenom menyn, och gör vad användaren vill.
	 * 
	 */
	public void runApplication() {
		int nbr;
		int input = integerInput();

		scan.nextLine();
		System.out.println("Val: " + input);

		// Switch case för val av meny
		switch (input) {

		case 1: // sök konto utifrån innehavare (antar idNr?)
			System.out.println("Söker konton för personnummer: ");
			long longNbr = longInput();

			if (bank.findAccountsForHolder(longNbr).isEmpty()) {
				System.out.println("Inget konto existerar för personnumret " + longNbr);
			} else {
				System.out.println("Följande konton finns för kunden: ");
				for (BankAccount accs : (ArrayList<BankAccount>) bank.findAccountsForHolder(longNbr)) {
					System.out.println(accs.toString());
				}
			}
			break;

		case 2: // sök kontoinnehavare efter (del av) namn
			System.out.println("Söker konton för namn:");
			String namePart = stringInput();

			if (bank.findByPartofName(namePart) == null) {
				System.out.println("Inget konto kunde hittas");

			} else {

				System.out.println("Konton: ");
				for (Customer cust : (ArrayList<Customer>) bank.findByPartofName(namePart)) {
					System.out.println(cust.toString());
				}
			}
			break;

		case 3: // sätt in pengar

			System.out.println("Sätt in pengar till konto: ");
			nbr = integerInput();

			System.out.println("Belopp: ");
			double dep = doubleInput();

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
			nbr = integerInput();
			System.out.println("Belopp: ");
			double wit = doubleInput();
			while (wit <= 0) {
				System.out.println("Du måste ange ett positivt belopp! Ange nytt belopp: ");
				wit = doubleInput();
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
			double amount = 0;
			int nbr1 = 0;
			int nbr2 = 0;

			System.out.println("Från konto: ");
			nbr1 = integerInput();

			System.out.println("Till konto: ");
			nbr2 = integerInput();

			System.out.println("Belopp att överföra: ");
			amount = doubleInput();

			while (amount <= 0) {
				System.out.println("Beloppet är negativt. Skriv in ett positivt belopp!");
				amount = doubleInput();
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
			String name = stringInput();

			System.out.print("Personnummer: ");
			long idNr = longInput();

			System.out.println("Konto skapat. Kontonummer: " + bank.addAccount(name, idNr));
			break;

		case 7: // ta bort ett konto efter kundnummer
			System.out.println("Vilket kundnummer ska tas bort? ");
			nbr = integerInput();

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
		default: // Om man skriver in ett ogiltigt menyval
			System.out.println("Inget menyval med det numret. Testa något i listan istället!");
			break;
		}
		bool = false;

	}

	/**
	 * Hjälpmetod för att sanera användarinput till giltigt int
	 */
	private int integerInput() {
		while (!scan.hasNextInt()) {
			scan.nextLine();
			System.out.println("Skriv in ett giltigt nummer");
		}
		return scan.nextInt();
	}

	/**
	 * Hjälpmetod för att sanera användarinput till giltig long
	 */
	private long longInput() {
		while (!scan.hasNextLong()) {
			scan.next();
			System.out.println("Skriv in ett giltigt nummer");
		}
		return scan.nextLong();
	}

	/**
	 * Hjälpmetod för att sanera användarinput till giltig double
	 */
	private double doubleInput() {
		while (!scan.hasNextDouble()) {
			System.out.println("Skriv in ett giltigt nummer");
			scan.next();
		}
		return scan.nextDouble();
	}

	/**
	 * Hjälpmetod för att sanera användarinput till giltig sträng
	 */
	private String stringInput() {
		while (scan.hasNextInt() || scan.hasNextByte() || scan.hasNextLong() || scan.hasNextShort()) {
			System.out.println("Skriv in en giltig sträng");
			scan.nextLine();
		}
		return scan.nextLine();
	}

	/**
	 * Sorterar en ArrayList med BankAccount enligt bokstavsordning. Gör först en
	 * kopia av listan som skickas in. Sorterar sedan enligt principen för varje i
	 * kollar på den framför använder compareTo på getName, och om den första är
	 * större, dvs har ett senare namn så byter den plats på dem.
	 * 
	 * @return den sorterade listan.
	 * @param list Lista som skall sorteras.
	 */
	private ArrayList<BankAccount> sortList(ArrayList<BankAccount> list) {
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

	/**
	 * Kollar om ett bankkonto med ett visst nummer existerar.
	 * 
	 * @param nbr bankkontot som skall kollas
	 * @return true om det gör det, annars false.
	 */

	private boolean doesExist(int nbr) {
		return bank.findByNumber(nbr) != null ? true : false;
	}

	/**
	 * Skriver ut menyn
	 */
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

	/**
	 * Skapar en delay i applikationen genom thread.sleep
	 * 
	 * @param ms hur länge applikationen skall dröja, i millisekunder
	 */
	static void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
