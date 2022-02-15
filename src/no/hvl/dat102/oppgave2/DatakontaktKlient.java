package no.hvl.dat102.oppgave2;
import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;
import no.hvl.dat102.oppgave2.Datakontakt;

import javax.xml.crypto.Data;

public class DatakontaktKlient {
    public static void main(String[] args) {
        //TODO test metodene i datakontakt og medlem
        Datakontakt dk = new Datakontakt(5);
        Medlem Lars = new Medlem("Lars");
        Medlem Kari = new Medlem("Kari");
        Hobby fotball = new Hobby("Fotball");
        MengdeADT<Hobby> hobbyer = new KjedetMengde<>();
        hobbyer.leggTil(fotball);

        dk.leggTilMedlem(Lars);
        dk.leggTilMedlem(Kari);
        Lars.setHobbyer(hobbyer);
        Kari.setHobbyer(hobbyer);


        System.out.println(dk.finnPartnerFor(Lars));
        System.out.println(dk);
        Tekstgrensesnitt.skrivParListe(dk);
        //dk.tilbakestillStatusIndeks("Lars");
        System.out.println(dk);

    }
}
