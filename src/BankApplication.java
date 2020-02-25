import java.util.Scanner;

public class BankApplication {
	static Scanner scan = new Scanner(System.in);
	static Bank bank = new Bank();

	public static void main(String[] args) {
	runApplication();

	}

	private static void runApplication() {
		writeMenu();
		int input = scan.nextInt();
		System.out.println("Val: " + input);
		
		switch (input) {			
		case 1:
		case 2:
		case 3:
			System.out.println("Från konto: ");
			int nbr = scan.nextInt();
			System.out.println("Belopp: ");
			double deposit = scan.nextDouble(); //inte klart :)
			
			
		case 4:
		case 5:
		case 6: 
			//System.out.println("Skriv in namnet för kontot:");
			System.out.println("namn: ");
			String name = scan.nextLine();
			System.out.println("personnummer: ");			
			long idNr = scan.nextLong();
			//int ddd bank.addAccount(name, idNr);
			System.out.println("Nytt bankkonto med id: " + " skapat.");
		case 7:
		case 8: 
		case 9:
		default:
			System.out.println("Inget menyval med det numret. Testa något i listan istället!");
			
		}
	
		

	}

	private static void writeMenu() {

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
