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

        Hobby fotball = new Hobby("Fotball");
        Hobby golf = new Hobby("Golf");

        lars.leggTilHobby(fotball);
        kari.leggTilHobby(fotball);
        knut.leggTilHobby(fotball);
        knut.leggTilHobby(golf);
        jennifer.leggTilHobby(golf);
        petter.leggTilHobby(golf);

        System.out.println(dk.finnPartnerFor(lars));
        System.out.println(dk.finnPartnerFor(knut));
        dk.finnPartnerFor(lest);

        System.out.println(dk);
        Tekstgrensesnitt.skrivParListe(dk);
        dk.tilbakestillStatusIndeks("Lars");
        System.out.println(dk);

    }
}
