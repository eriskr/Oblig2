package no.hvl.dat102.oving4.oppgave1.kjedet;

//********************************************************************
// Kjedet implementasjon av en mengde. 
//********************************************************************
import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.LinearNode;
import no.hvl.dat102.exceptions.EmptyCollectionException;
import no.hvl.dat102.adt.MengdeADT;

public class KjedetMengde<T> implements MengdeADT<T>, Iterable<T> {

	private static Random rand = new Random();
	private int antall; // antall elementer i mengden
	private LinearNode<T> start;

	/**
	 * Oppretter en tom mengde.
	 */
	public KjedetMengde() {
		antall = 0;
		start = null;
	}//

	@Override
	public void leggTil(T element) {
		if (!(inneholder(element))) {
			LinearNode<T> node = new LinearNode<T>(element);
			node.setNeste(start);
			start = node;
			antall++;
		}
	}

	@Override
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			leggTil(teller.next());
		}
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		LinearNode<T> forgjenger, aktuell;
		T resultat;

		int valg = rand.nextInt(antall) + 1;
		if (valg == 1) {
			resultat = start.getElement();
			start = start.getNeste();
		} else {
			forgjenger = start;
			for (int nr = 2; nr < valg; nr++) {
				forgjenger = forgjenger.getNeste();
			}
			aktuell = forgjenger.getNeste();
			resultat = aktuell.getElement();
			forgjenger.setNeste(aktuell.getNeste());
		}
		antall--;

		return resultat;

	}//

	@Override
	public T fjern(T element) {

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		LinearNode<T> forgjenger, aktuell;
		T resultat = null;
		if (start.getElement().equals(element)) {// Sletter foran
			resultat = start.getElement();
			start = start.getNeste();
			antall--;
		} else {// Gjennomg�r den kjedete strukturen
			forgjenger = start;
			aktuell = start.getNeste();
			for (int sok = 2; sok <= antall && !funnet; sok++) {
				if (aktuell.getElement().equals(element))
					funnet = true;
				else {
					forgjenger = forgjenger.getNeste();
					aktuell = aktuell.getNeste();
				}
			}
			if (funnet) {
				resultat = aktuell.getElement();
				forgjenger.setNeste(aktuell.getNeste()); // Slette midt inni/bak
				antall--;
			}
		}
		return resultat;

	}//

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		LinearNode<T> aktuell = start;
		for (int soek = 0; soek < antall && !funnet; soek++) {
			if (aktuell.getElement().equals(element)) {
				funnet = true;
			} else {
				aktuell = aktuell.getNeste();
			}
		}
		return funnet;
	}
	/*
	 * N�r vi overkjører (ovverride) equals- meteoden er det anbefalt at vi ogs�
	 * overkj�rer hashcode-metoden da en del biblioteker bruker hascode sammen med
	 * equals. Vi kommer tilbake til forklaring og bruk av hashcode senere i faget.
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antall;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object ny) {

		if (this == ny) {
			return true;
		}
		if (ny == null) {
			return false;
		}
		if (getClass() != ny.getClass()) {
			return false;
		} 
			boolean likeMengder = true;
			MengdeADT<T> m2 = (KjedetMengde<T>) ny;
			if (this.antall != m2.antall()) {
				likeMengder = false;
			} else {
				likeMengder = true;
				Iterator<T> teller = m2.oppramser();
				while (teller.hasNext() && likeMengder) {
					T element = teller.next();
					if (!this.inneholder(element)) {
						likeMengder = false;
					}
				}
				return likeMengder;
			}
		
		return false;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> m2) {
		MengdeADT<T> begge = new KjedetMengde<>();
		LinearNode<T> aktuell = start;
		T element;

		while (aktuell != null) {// ubetinget innsetting
			((KjedetMengde<T>) begge).settInn(aktuell.getElement());
			aktuell = aktuell.getNeste();
		}

		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext()) {
			element = teller.next();
			if (!this.inneholder(element)) {// tester mot "konstant" mengde
				((KjedetMengde<T>) begge).settInn(element);
			}
		}

		return begge;
	}//

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new KjedetMengde<>();

		for (T el1 : this) {
			for (T el2 : (KjedetMengde<T>) m2) {
				if (el1.equals(el2)) {
					((KjedetMengde<T>) snittM).settInn(el1);
				}
			}
		}

		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {
		MengdeADT<T> differensM = new KjedetMengde<>();

		for (T el : this) {
			if (!m2.inneholder(el)) {
				((KjedetMengde<T>) differensM).settInn(el);
			}
		}

		return differensM;
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {
		for (T el : (KjedetMengde<T>) m2) {
			if (!inneholder(el)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<T> oppramser() {
		return iterator();
	}

	private void settInn(T element) {
		LinearNode<T> nyNode = new LinearNode<>(element);
		nyNode.setNeste(start);
		start = nyNode;
		antall++;
	}

	@Override
	public Iterator<T> iterator() {
		return new KjedetIterator<>(start);
	}


	public String toString(){

		String resultat = "<";

		LinearNode<T> aktuell = start;
		while(aktuell != null) {
			resultat += aktuell.getElement().toString();
			if (aktuell.getNeste() != null) {
				resultat += ", ";
			}
			aktuell = aktuell.getNeste();
		}
		resultat += ">";
		return resultat;
	}
}// class
