package no.hvl.dat102.oving4.oppgave2;

import no.hvl.dat102.adt.MengdeADT;
import no.hvl.dat102.oving4.oppgave1.kjedet.KjedetMengde;
import no.hvl.dat102.oving4.oppgave1.tabell.TabellMengde;

import javax.swing.*;

public class Tekstgrensesnitt {
    //Hvis du vil lage meny, kan du også legge det inn i Tekstgrensesnitt
    // leser opplysningene om et medlem fra tastatur
    public static Medlem lesMedlem() {
        String navn = JOptionPane.showInputDialog("Navn");
        MengdeADT<Hobby> hobbyer = new KjedetMengde<>();
        String[] stringHobbyer = JOptionPane.showInputDialog("Hobbyer, skill med mellomrom").split("\\W+");

        for (String s : stringHobbyer) {
            Hobby hobby = new Hobby(s);
            hobbyer.leggTil(hobby);
        }
        return new Medlem(navn, hobbyer);
    }

    // Skriver ut hobbylisten for et medlem
    public static void skrivHobbyListe(Medlem medlem) {
        System.out.println("Alle hobbyene ");
        System.out.println(medlem.getHobbyer());
    }

    /**
     * skriver ut på skjermen en oversikt over medlemmer som er koblet
     * til hverandre i medlemstabellen til enhver tid.
     * Et slikt par skal kun vises én gang på utskriftlisten. Metoden
     * skriver også ut antall par som er funnet.
     * Eksempel på utskrift:
     * PARNAVN             HOBBYER
     * Erna og Jonas       <ski, musikk, politikk>
     * Eva og Adam         <epleplukking, paradishopping>
     * --------------------------------------------------
     * Antall par funnet: 12
     */
    public static void skrivParListe(Datakontakt arkiv) {
        System.out.printf("%s %20s %n", "PARNAVN", "HOBBYER");
        TabellMengde<Medlem> medlemmer = arkiv.getMedlemmer();

        int i = 0, antallPar = 0;
        //Markerer posisjonen til de medlemmene som er skrevet ut
        boolean[] skrevetUt = new boolean[arkiv.getMedlemmer().antall()];

        for (Medlem m : medlemmer) {
            int m2 = m.getStatusIndeks();

            if (m2 != -1 && !skrevetUt[i]) {
                Medlem medlem2 = medlemmer.getElement(m2);
                System.out.printf("%s %18s %n", m.getNavn() + " og " + medlem2.getNavn(), m.getHobbyer().snitt(medlem2.getHobbyer()));
                skrevetUt[m2] = true;
                antallPar++;
            }
            i++;
        }
        System.out.println("----------------------------------------------\n" +
                "Antall par funnet: " + antallPar);
    }
}
