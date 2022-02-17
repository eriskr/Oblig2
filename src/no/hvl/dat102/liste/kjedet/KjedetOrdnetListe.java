package no.hvl.dat102.liste.kjedet;

import no.hvl.dat102.LinearNode;
import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exception.EmptyCollectionException;

import java.util.IllformedLocaleException;
import java.util.Iterator;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
	}

	@Override
	public T fjernFoerste() {
		if (erTom()) throw new EmptyCollectionException("ordnet liste");


		T resultat = foerste.getElement();
		foerste = foerste.getNeste();
		antall--;
		return resultat;
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		if (siste.equals(foerste)){
			fjernFoerste();
			return resultat;
		}
			LinearNode<T> node = foerste;
		for (int i = 0; i < antall-2; i++) {
			node = node.getNeste();
		}
		siste = node;

		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T svar = foerste.getElement();

		return svar;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		return resultat;
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
	public void leggTil(T element) {

		LinearNode<T> forrige = foerste;
		LinearNode<T> node = foerste.getNeste();
		LinearNode<T> ny = new LinearNode<>(element);

		for (int i = 0; i < antall; i++) {
			if (node.getElement().compareTo(element) <= 0) {
				forrige.setNeste(ny);
				ny.setNeste(node);
				antall++;
			}

		}



	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

}// class
