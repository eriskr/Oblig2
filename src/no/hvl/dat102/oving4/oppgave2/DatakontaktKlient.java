package no.hvl.dat102.oving4.oppgave2;

public class DatakontaktKlient {
    public static void main(String[] args) {
        Datakontakt dk = new Datakontakt(5);
        Medlem lars = new Medlem("Lars"),
                kari = new Medlem("Kari"),
                knut = new Medlem("Knut"),
                jennifer = new Medlem("Jennifer"),
                petter = new Medlem("Petter"),
                lest = Tekstgrensesnitt.lesMedlem();

        dk.leggTilMedlem(lest);
        dk.leggTilMedlem(lars);
        dk.leggTilMedlem(kari);
        dk.leggTilMedlem(knut);
        dk.leggTilMedlem(jennifer);
        dk.leggTilMedlem(petter);

        Hobby fotball = new Hobby("Fotball"),
                golf = new Hobby("Golf"),
                ski = new Hobby("Ski"),
                tur = new Hobby("Tur"),
                spill = new Hobby("Spill");

        lars.leggTilHobby(fotball);
        lars.leggTilHobby(ski);
        lars.leggTilHobby(tur);

        kari.leggTilHobby(fotball);
        kari.leggTilHobby(ski);
        kari.leggTilHobby(golf);
        kari.leggTilHobby(spill);

        knut.leggTilHobby(fotball);
        knut.leggTilHobby(golf);

        jennifer.leggTilHobby(golf);
        jennifer.leggTilHobby(spill);
        jennifer.leggTilHobby(ski);

        petter.leggTilHobby(fotball);
        petter.leggTilHobby(ski);

        System.out.println(dk.finnBestePartnerFor(lars));
        System.out.println(dk.finnBestePartnerFor(knut));
        dk.finnBestePartnerFor(lest);

        System.out.println(dk);
        Tekstgrensesnitt.skrivParListe(dk);
        dk.tilbakestillStatusIndeks("Lars");
        System.out.println(dk);

    }
}
