import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CreateRandomAccs {
	ArrayList<String> firstName, lastName, names;
	ArrayList<Integer> id;
	ArrayList<BankAccount> accounts;
	Random rand;
	int amount;

	/*
	 * Skapar slumpmässiga konton med slumpmässiga namn Accounts hänvisar till
	 * listan som överskrivs med de nya kontona.
	 */
	public CreateRandomAccs(int amount, String filename) {
		names = new ArrayList<String>();
		id = new ArrayList<Integer>();
		rand = new Random();
		createAccounts(filename, amount);
		
	}

	ArrayList<String> getNames() {
		return names;
	}
	ArrayList<Integer> getYears() {
		return id;
	}
	
	public void createAccounts(String filename, int amount) {
		this.amount = amount; 
		readFile(filename);
		for (int i = 0; i < amount; i++) {
			String name = names.get(rand.nextInt(names.size()));
			names.add(name);
			id.add(generateBirthDay());
		}
	}
	
	/*
	 * public void createAccounts(String filename, int amount) { this.amount =
	 * amount; readFile(filename); for (int i = 0; i < amount; i++) { String name =
	 * names.get(rand.nextInt(names.size())); BankAccount acc = new
	 * BankAccount(name, generateBirthDay()); accounts.add(acc); } }
	 */
	
	private int generateBirthDay() {
		String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
						 "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
						 "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		int year = rand.nextInt(100) + 1900;
		String month = months[rand.nextInt(months.length)];
		String day = days[rand.nextInt(days.length)];
		String dateOfBirth = "" + year + month + day;
		return Integer.parseInt(dateOfBirth);
	}

	private void readFile(String fileName) {
			Scanner scan = new Scanner(System.in);
			firstName = new ArrayList<String>();
			lastName = new ArrayList<String>();
			// Provar att få in filen som input, annars ErrorException
			try {
				scan = new Scanner(new File(fileName), "utf-8");
			} catch (FileNotFoundException e) {
				System.err.println("File not found" + fileName);
				e.printStackTrace();
			}

			
			// While sålänge det finns ett nästa input, annars null
			while (scan.hasNext()) {
				String fName = scan.next();
				String lName = scan.next();
				firstName.add(fName);
				lastName.add(lName);				
			}
			for (int i = 0; i < amount; i++) {
				names.add(firstName.get(rand.nextInt(firstName.size())) + " " + lastName.get(rand.nextInt(lastName.size())));
			}
			

	}

}
