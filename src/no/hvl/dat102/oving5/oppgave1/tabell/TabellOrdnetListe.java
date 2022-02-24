package no.hvl.dat102.oving5.oppgave1.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * Sorterer fra minst til størst
 * @param <T> Elementtypen
 */
public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int antall;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		antall = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste();
		antall--;
		liste[antall] = null;
		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = foerste();
		antall--;
		for (int i = 0; i < antall; i++) {
			liste[i] = liste[i + 1];
		}
		liste[antall] = null;
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		return liste[0];
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		return liste[antall - 1];
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public void leggTil(T element) {
		if (antall == liste.length) {
			utvid();
		}
		if (erTom()) {
			liste[0] = element;
		}
		else {
			boolean funnet = false;
			for (int i = 0; i < antall && !funnet; i++) {
				if (element.compareTo(liste[i]) < 0) {
					//Flytter alle elementer fra indeks i, ett hakk til høyre
					for (int j = antall - 1; j >= i; j--) {
						liste[j + 1] = liste[j];
					}
					liste[i] = element;
					funnet = true;
				}
			}
			if (!funnet) {
				liste[antall] = element;
			}
		}
		antall++;
	}

	@Override
	public boolean inneholder(T element) {

		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T el) {
		int i = finn(el);

		if (i != -1) {
			for (int j = i; j < antall-1; j++) {
				liste[j] = liste[j + 1];
			}
			antall--;
			liste[antall] = null;
			return el;
		}
		return null;

	}

	private int finn(T el) {
		if (erTom()) {
			throw new EmptyCollectionException("Ordnet liste");
		}
		for (int i = 0; i < antall; i++) {
			if (liste[i].equals(el)) {
				return i;
			}
		}
		return IKKE_FUNNET;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < antall; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
