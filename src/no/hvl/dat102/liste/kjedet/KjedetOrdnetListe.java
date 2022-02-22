package no.hvl.dat102.liste.kjedet;

import no.hvl.dat102.LinearNode;
import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exception.EmptyCollectionException;

import java.util.IllformedLocaleException;
import java.util.Iterator;

/**
 * Sorterer fra minst til størst
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
		node.setNeste(null);
		antall--;
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
		LinearNode<T> ny = new LinearNode<>(element);
		if (erTom()) {
			foerste = ny;
			siste = ny;
		}
		else {
			if (foerste.getElement().compareTo(element) >= 0) {
				ny.setNeste(foerste);
				foerste = ny;
			}
			else {
				LinearNode<T> forrige = foerste;
				LinearNode<T> node = foerste.getNeste();

				while (node != null && node.getElement().compareTo(element) < 0) {
					forrige = node;
					node = node.getNeste();
				}
				forrige.setNeste(ny);
				if (node != null) {
					ny.setNeste(node);
				}
				else {
					siste = ny;
				}
			}
		}
		antall++;
	}

	@Override
	public T fjern(T element) {
//		T fjernet = null;
//		if (erTom()) {
//			return fjernet;
//		}
//
//		boolean found = false;
//		if (foerste.getElement().equals(element)) {
//			fjernet = foerste.getElement();
//			foerste = foerste.getNeste();
//			found = true;
//		}
//		else {
//			LinearNode<T> previous = foerste, node = previous.getNeste();
//			for (int i = 1; i < antall && !found && element.compareTo(previous.getElement()) >= 0; i++) { //Vi bruker compareTo for å stoppe hvis vi passerer elementet vi skal fjerne
//				if (node.getElement().equals(element)) {
//					fjernet = node.getElement();
//					found =  true;
//					previous.setNeste(node.getNeste());
//					if (previous.getNeste() == null) {
//						siste = previous;
//					}
//				}
//				else {
//					previous = previous.getNeste();
//					node = node.getNeste();
//				}
//			}
//		}
//
//		if (found) {
//			antall--;
//			if (erTom()) {
//				siste = null;
//			}
//		}
//		return fjernet;

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
