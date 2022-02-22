package no.hvl.dat102.liste;

import no.hvl.dat102.adt.ListeADTTest;
import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.liste.tabell.TabellOrdnetListe;

public class TabellListeTest extends ListeADTTest {
    @Override
    protected OrdnetListeADT<Integer> reset() {
        return new TabellOrdnetListe<>();
    }
}
