package no.hvl.dat102.liste.listeklient;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.liste.tabell.TabellOrdnetListe;

public class KlientPerson {

	public static void main(String[] args) {
		
		OrdnetListeADT<Person> kjedetListe = new KjedetOrdnetListe<>();
		OrdnetListeADT<Person> tabelListe = new TabellOrdnetListe<>();
		
		kjedetListe.leggTil(lesInnPerson());
		kjedetListe.leggTil(lesInnPerson());
		kjedetListe.leggTil(lesInnPerson());

		tabelListe.leggTil(lesInnPerson());
		tabelListe.leggTil(lesInnPerson());
		tabelListe.leggTil(lesInnPerson());
		
		while(!kjedetListe.erTom()) {
			System.out.println(kjedetListe.fjernSiste());
		}
		

	}

}
