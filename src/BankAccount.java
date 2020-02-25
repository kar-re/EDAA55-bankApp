import java.util.Random;

public class BankAccount {
	private int accountNumber;
	private double balance;
	private Customer holder;
	private Random rand;
	
	/**
	* Skapar ett nytt bankkonto åt en innehavare med namn ’holderName’ och
	* id ’holderId’. Kontot tilldelas ett unikt kontonummer och innehåller
	* inledningsvis 0 kr.
	*/
	public BankAccount(String holderName, long holderId) {
		holder = new Customer(holderName, holderId);
		rand = new Random();
		accountNumber = rand.nextInt(99999999);
		balance = 0;
	}
	/**
	* Skapar ett nytt bankkonto med innehavare ’holder’. Kontot tilldelas
	* ett unikt kontonummer och innehåller inledningsvis 0 kr.
	*/
	public BankAccount(Customer holder) {
		this.holder = holder;
		rand = new Random();
		balance = 0;
		accountNumber = rand.nextInt(99999999);
	}
	/** Tar reda på kontots innehavare. */
	public Customer getHolder() {
		return holder;
	}
	/** Tar reda på det kontonummer som identifierar detta konto. */
	public int getAccountNumber() {
		return accountNumber;
	}
	/** Tar reda på hur mycket pengar som finns på kontot. */
	public double getAmount() {
		return balance;
	}
	/** Sätter in beloppet ’amount’ på kontot. */
	public void deposit(double amount) {
		balance =+ amount;
	}
	/**
	* Tar ut beloppet ’amount’ från kontot. Om kontot saknar täckning
	* blir saldot negativt.
	*/
	public void withdraw(double amount) {
		balance =- amount;
	}
	/** Returnerar en strängrepresentation av bankkontot. */
	public String toString() {
		String output = holder.toString() + " : " + balance;
		return output;
	}

}
