package no.hvl.dat102.liste;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.adt.OrdnetListeADTTest;
import no.hvl.dat102.liste.kjedet.KjedetOrdnetListe;
import no.hvl.dat102.liste.tabell.TabellOrdnetListe;

public class TabellOrdnetListeTest extends OrdnetListeADTTest {

    @Override
    protected OrdnetListeADT<Integer> reset() {
        return new TabellOrdnetListe<Integer>();
    }
}