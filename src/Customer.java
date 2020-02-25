
public class Customer {
	private String name;
	private long idNr;
	private int customerNr;

	/**
	 * Skapar en kund (kontoinnehavare) med namnet ’name’ och id-nummer ’idNr’.
	 * Kunden tilldelas också ett unikt kundnummer.
	 */
	Customer(String name, long idNr) {
		this.name = name;
		this.idNr = idNr;
		customerNr = Bank.getNextCustNbr();
	}

	/** Tar reda på kundens namn. */
	String getName() {
		return name;
	}

	/** Tar reda på kundens personnummer. */
	long getIdNr() {
		return idNr;
	}

	/** Tar reda på kundens kundnummer. */
	int getCustomerNr() {
		return customerNr;
	}

	/** Returnerar en strängbeskrivning av kunden. */
	public String toString() {
		String output = name + "," + " personnummer: " + idNr + "," + " kundnr: " + customerNr;
		return output;
	}
}
