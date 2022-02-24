package no.hvl.dat102.ordnetListe;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.adt.OrdnetListeADTTest;
import no.hvl.dat102.oving5.oppgave1.kjedet.KjedetOrdnetListe;

public class KjedetOrdnetListeTest extends OrdnetListeADTTest{
    @Override
    protected OrdnetListeADT<Integer> reset() {
        return new KjedetOrdnetListe<>();
    }
}