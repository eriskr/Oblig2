package no.hvl.dat102.oppgave2;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

import javax.swing.*;
import java.util.Iterator;

public class Tekstgrensesnitt {
    //Hvis du vil lage meny, kan du også legge det inn i Tekstgrensesnitt
    // leser opplysningene om et medlem fra tastatur
    public static Medlem lesMedlem() {
        String navn = JOptionPane.showInputDialog("Navn");
        MengdeADT<Hobby> hobbyer = new KjedetMengde<>();
        String[] stringHobbyer = JOptionPane.showInputDialog("Hobbyer, skill med mellomrom").split("\\w");//TODO sjekk

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

    public static void skrivParListe (Datakontakt arkiv) {
        /*
        skriver ut på skjermen en oversikt over medlemmer som er koblet
        til hverandre i medlemstabellen til enhver tid.
        Et slikt par skal kun vises én gang på utskriftlisten. Metoden
        skriver også ut antall par som er funnet.
        Eksempel på utskrift:
        PARNAVN             HOBBYER
        Erna og Jonas       <ski, musikk, politikk>
        Eva og Adam         <epleplukking, paradishopping>
        --------------------------------------------------
        Antall par funnet: 12
        */
        System.out.println("PARNAVN\t\t\tHOBBYER");

        for (Medlem m : arkiv.getMedlemmer()) {
            if (m.getStatusIndeks() != -1) {
                System.out.print(m.getNavn() + " og ");
                System.out.println(arkiv.getMedlemmer().getElement(m.getStatusIndeks()).getNavn());
            }
        }

        System.out.println("----------------------------------------------");
    }
}
