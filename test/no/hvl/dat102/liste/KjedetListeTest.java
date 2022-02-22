package no.hvl.dat102.liste;

import no.hvl.dat102.adt.ListeADT;
import no.hvl.dat102.adt.ListeADTTest;
import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.liste.kjedet.KjedetOrdnetListe;

public class KjedetListeTest extends ListeADTTest {
    @Override
    protected OrdnetListeADT<Integer> reset() {
        return new KjedetOrdnetListe<>();
    }

}
