package no.hvl.dat102.oving5.oppgave1.listeklient;

public class Person implements Comparable<Person> {

	private String fornavn;
	private String etternavn;
	private int foedselsaar;

	// Konstruktører

	public Person() {
		this("", "", 0);

	}

	public Person(String fornavn, String etternavn, int foedselsaar) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.foedselsaar = foedselsaar;

	}

	public void setFoedselsaar(int foedselsaar) {
		this.foedselsaar = foedselsaar;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	int getFoedselsaar() {
		return foedselsaar;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public String toString() {
		return (foedselsaar + "\t" + etternavn + ", " + fornavn);
	}

	// Oppgave a

	/**
	 * Se Klassen Person i prosjektet ListeOrdnetU. Ferdigstill metoden compareTo. Metoden
	 * skal sammenligne to Person-objekter med hensyn på fødselsår. Vi ønsker at den eldste
	 * kommer først (compareTo returnerer et tall < 0). Ved sammenligning av like fødselsår skal
	 * du sammenligne etternavn og deretter eventuelt fornavn (ønsker ordnet i alfabetisk
	 * rekkefølge). Tips: Se gjerne compareTo i klassen Lag, i prosjektet ListeOrdnetU.
	 * @param denAndrePersonen Personen metoden sammenligner mot
	 * @return Differansen
	 */
	public int compareTo(Person denAndrePersonen) {
		int resultat = 0;

		//Sammenligner fødselsår
		if (foedselsaar < denAndrePersonen.foedselsaar) {
			resultat = -1;
		}
		else if (foedselsaar > denAndrePersonen.foedselsaar) {
			resultat = 1;
		}
		//Sammenligner etternavn
		if (resultat == 0) {
			resultat = etternavn.compareTo(denAndrePersonen.etternavn);
		}
		//Sammenligner fornavn
		if (resultat == 0) {
			resultat = fornavn.compareTo(denAndrePersonen.fornavn);
		}
		return resultat;
	}

}// class