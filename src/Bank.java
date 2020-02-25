import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> accounts;
	private ArrayList<Customer> customers;
	static private int accNbr, custNbr;
	/** Skapar en ny bank utan konton. */
	Bank() {
		accounts = new ArrayList<BankAccount>();
		customers = new ArrayList<Customer>();
		accNbr = 1000;
		custNbr = 100;
	}
	/**
	* Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare
	* med de givna uppgifterna ska inte en ny Customer skapas, utan istället
	* den befintliga användas. Det nya kontonumret returneras.
	*/
	public int addAccount(String holderName, long idNr) {
		BankAccount newAccount;
		boolean alreadyExists = false;
		int index = 0;
		
		for (Customer cust : customers) {
			if (cust.getName() == holderName) 
				alreadyExists = true;
				index = customers.indexOf(cust);
		}
		
		if (alreadyExists) {
			newAccount = new BankAccount(customers.get(index));
			accounts.add(newAccount);
		} else {
			Customer newCustomer = new Customer(holderName, idNr);
			newAccount = new BankAccount(newCustomer);
			accounts.add(newAccount);
			customers.add(newCustomer);
			custNbr += 1;
			
		}
		accNbr += 1;
		return accNbr;
		
	}
	/**
	* Returnerar den kontoinnehavaren som har det givna id-numret,
	* eller null om ingen sådan finns.
	*/
	public Customer findHolder(long idNr) {
		for (Customer t : customers) {
			return hasSameId(t, idNr) ? t : null;
		} return null;
		
	}
	private boolean hasSameId(Customer customer, long idNr) {
		return (customer.getIdNr() == idNr);
	}
	public static int getNextAccountNbr() {
		return accNbr + 1;
	}
	public static int getNextCustNbr() {
		return custNbr + 1;
	}
	/**
	* Tar bort konto med nummer ’number’ från banken. Returnerar true om
	* kontot fanns (och kunde tas bort), annars false.
	*/
	public boolean removeAccount(int number) {
		for (BankAccount account : accounts) {
			if(account.getAccountNumber() == number) {
				accounts.remove(account);
				return true;
			} 
		}
		return false;
	}
	/**
	* Returnerar en lista innehållande samtliga bankkonton i banken.
	* Listan är sorterad på kontoinnehavarnas namn.
	*/
	public ArrayList<BankAccount> getAllAccounts() {
		return accounts;
	}
	/**
	* Söker upp och returnerar bankkontot med kontonummer ’accountNumber’.
	* Returnerar null om inget sådant konto finns.
	*/
	public BankAccount findByNumber(int accountNumber) {
		for (BankAccount account : accounts) {
			if(account.getAccountNumber() == accountNumber) {
				return account;
			}
		} return null;
	}
	/**
	* Söker upp alla bankkonton som innehas av kunden med id-nummer ’idNr’.
	* Kontona returneras i en lista. Kunderna antas ha unika id-nummer.
	*/
	public ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> holderAccounts = new ArrayList<BankAccount>();
		for (BankAccount account : accounts) {
			if (account.getHolder().getIdNr() == idNr) {
				holderAccounts.add(account);
			}
		}
		return holderAccounts;
	}
	/**
	* Söker upp kunder utifrån en sökning på namn eller del av namn. Alla
	* personer vars namn innehåller strängen ’namePart’ inkluderas i
	* resultatet, som returneras som en lista. Samma person kan förekomma
	* flera gånger i resultatet. Sökningen är "case insensitive", det vill
	* säga gör ingen skillnad på stora och små bokstäver.
	*/
	public ArrayList<Customer> findByPartofName(String namePart){
		ArrayList<Customer> customersNameEquals = new ArrayList<Customer>();
		for (Customer customer : customers) {
			if (customer.getName().toLowerCase().contains(namePart.toLowerCase())){
				customersNameEquals.add(customer);
			}
		}
		return customersNameEquals;
	}
}
