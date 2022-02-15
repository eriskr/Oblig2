package no.hvl.dat102.mengde.tabell;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import no.hvl.dat102.exception.EmptyCollectionException;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class TabellMengde<T> implements MengdeADT<T>, Iterable<T> {
	// ADT-en Mengde implementert som tabell
	//
	private final static Random tilf = new Random();
	private final static int STDK = 100;
	private int antall;
	private T[] tab;

	public TabellMengde() {
		this(STDK);
	}

	public TabellMengde(int start) {
		antall = 0;
		tab = (T[]) (new Object[start]);
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public boolean erTom() {
		return (antall == 0);
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tab.length) {
				utvidKapasitet();
			}
			tab[antall] = element;
			antall++;
		}
	}

	private void utvidKapasitet() {
		T[] hjelpetabell = (T[]) (new Object[2 * tab.length]);
		for (int i = 0; i < tab.length; i++) {
			hjelpetabell[i] = tab[i];
		}
		tab = hjelpetabell;
	}

	@Override
	public T fjernTilfeldig() {
		if (erTom())
			throw new EmptyCollectionException("mengde");

		T svar = null;
		int indeks = tilf.nextInt(antall);
		svar = tab[indeks];
		tab[indeks] = tab[antall - 1];
		tab[antall-1]= null;
		antall--;

		return svar;
	}

	@Override
	public T fjern(T element) {
		//TODO
		// S�ker etter og fjerner element. Returnerer null-ref ved ikke-funn

		if (erTom())
			throw new EmptyCollectionException("mengde");

		boolean funnet = false;
		T svar = null;
		for(int i = 0; (i < antall && !funnet);i++) {
			if(tab[i].equals(element)) {
				svar = tab[i];
				tab[i] = tab[antall-1];
				//tab[antall-1] = null;
				antall--;
				funnet = true;
				
			}
		}
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		boolean funnet = false;
		for (int i = 0; (i < antall) && (!funnet); i++) {
			if (tab[i].equals(element)) {
				funnet = true;
			}
		}
		return (funnet);
	}
	
	/*
	 * Når vi overkjører (override) equals- meteoden er det anbefalt at vi også
	 * overkjører hascode-metoden da en del biblioterker burker hascode sammen med
	 * equals. Vi kommer tilbake til forklaring og bruk av hascode senere i faget.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + antall;
		result = prime * result + Arrays.deepHashCode(tab);
		return result;
	}

	@Override
	public boolean equals(Object m2) {

		if (this == m2){
			return true;
		}

		if (m2 == null){
			return false;
		}

		if (getClass() != m2.getClass()) {
			return false;
		}
		boolean likeMengder = true;


		MengdeADT<T> tab1= (KjedetMengde<T>) m2;
		if (this.antall != tab1.antall()) {
			likeMengder = false;
		} else {
			likeMengder = true;
			Iterator<T> teller = tab1.oppramser();
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
	public void leggTilAlle(MengdeADT<T> m2) {
		Iterator<T> teller = m2.oppramser();
		while (teller.hasNext())
			leggTil(teller.next());
	}

	/*
	 * Denne versjonen av unionen er lite effektiv
	 * 
	 * @Override public MengdeADT<T> union(MengdeADT<T> m2) { TabellMengde<T> begge
	 * = new TabellMengde<T>(); for (int i = 0; i < antall; i++) {
	 * begge.leggTil(tab[i]); } Iterator<T> teller = m2.oppramser();
	 * 
	 * while (teller.hasNext()) { begge.leggTil(teller.next()); } return
	 * (MengdeADT<T>)begge; }
	 */
	@Override

	public MengdeADT<T> union(MengdeADT<T> m2) {

		MengdeADT<T> begge = new TabellMengde<>();

		for (T i : this) {
			((TabellMengde<T>) begge).settInn(i);
		}

		for (T i : (TabellMengde<T>) m2){
			if (!inneholder(i)){
				((TabellMengde<T>)begge).settInn(i);
			}

		}
		return begge;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> m2) {
		MengdeADT<T> snittM = new TabellMengde<>();

		for (T i : this){
			for (T j : (TabellMengde<T>) m2){
				if (i.equals(j)){
					((TabellMengde<T>)snittM).settInn(i);
				}

			}
		}
		return snittM;
	}

	@Override
	public MengdeADT<T> differens(MengdeADT<T> m2) {

		MengdeADT<T> differensM = new TabellMengde<>();

		for (T i : this){

				if (!m2.inneholder(i)){
					((TabellMengde<T>) differensM).settInn(i);
				}

			}

		return differensM;

		/*
		Iterator<T> m2teller = m2.oppramser();

		while (m2teller.hasNext()){
			T element = m2teller.next();
			if (!inneholder(element)) {
				differensM.settInn(element);
			}
		}

		return differensM;
		 */
	}

	@Override
	public boolean undermengde(MengdeADT<T> m2) {

		Iterator<T> m2teller = m2.oppramser();

		while (m2teller.hasNext()) {
			if (!inneholder(m2teller.next())) {
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
		if (antall == tab.length) {
			utvidKapasitet();
		}
		tab[antall] = element;
		antall++;
	}

	@Override
	public Iterator<T> iterator() {
		return new TabellIterator<>(tab, antall);
	}

	public T[] getTab () {
		return tab;
	}

	public T getElement(int i) {
		return tab[i];
	}

	@Override
	public String toString(){

		String resultat = "<";

		for (int i = 0; i < antall; i++) {
			resultat += tab[i].toString();
			if (i < antall-1) {
				resultat += ", ";
			}
		}
		resultat += ">";
		return resultat;
	}
}// class
