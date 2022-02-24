package no.hvl.dat102.mengde;

import no.hvl.dat102.adt.MengdeADTTest;
import no.hvl.dat102.adt.MengdeADT;
import no.hvl.dat102.oving4.oppgave1.tabell.TabellMengde;

public class TabellMengdeTest extends MengdeADTTest {

    @Override
    protected MengdeADT<Integer> reset() {
        return new TabellMengde<>();
    }
}


