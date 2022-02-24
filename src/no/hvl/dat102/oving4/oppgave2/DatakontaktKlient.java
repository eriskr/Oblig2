package no.hvl.dat102.oving4.oppgave2;
import no.hvl.dat102.adt.MengdeADT;
import no.hvl.dat102.oving4.oppgave1.kjedet.KjedetMengde;

public class DatakontaktKlient {
    public static void main(String[] args) {
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
