import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateRandomAccs {
	ArrayList<String> firstName, lastName, id, names;
	ArrayList<Customer> customers;
	ArrayList<BankAccount> accounts;

	public CreateRandomAccs(int amount) {
		names = new ArrayList<String>();
		id = new ArrayList<String>();

	}

	public ArrayList<Customer> createCustomers() {
		return customers;
	}
	public ArrayList<BankAccount> createAccounts() {
		return accounts;
	}

	private void readFile(String fileName) {
			Scanner scan;
			// Provar att få in filen som input, annars ErrorException
			try {
				scan = new Scanner(new File(fileName), "utf-8");
			} catch (FileNotFoundException e) {
				System.err.println("File not found" + fileName);
				e.printStackTrace();
			}

			int i = 0;
			// While sålänge det finns ett nästa input, annars null
			while (scan.hasNext()) {
				String name = scan.next() + " " + scan.next();
				i = i + 1;
			}

	}

}
