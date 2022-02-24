package no.hvl.dat102.oving5.oppgave1.listeklient;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.oving5.oppgave1.kjedet.KjedetOrdnetListe;
import no.hvl.dat102.oving5.oppgave1.tabell.TabellOrdnetListe;

import javax.swing.*;

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
		System.out.println();

		while(!tabelListe.erTom()) {
			System.out.println(tabelListe.fjernSiste());
		}
	}

	private static Person lesInnPerson() {

		String fornavn = JOptionPane.showInputDialog("Fornavn:");
		String etternavn = JOptionPane.showInputDialog("Etternavn:");
		int fodselsaar = 0;

		boolean ok;
		do {
			ok = true;
			try {
				fodselsaar = Integer.parseInt(JOptionPane.showInputDialog("F�dsels�r:"));
			}
			catch (NumberFormatException e) {
				ok = false;
			}
		} while (!ok);
		return new Person(fornavn, etternavn, fodselsaar);
	}

}
