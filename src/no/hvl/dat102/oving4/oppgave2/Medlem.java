package no.hvl.dat102.oving4.oppgave2;

import no.hvl.dat102.adt.MengdeADT;
import no.hvl.dat102.oving4.oppgave1.kjedet.KjedetMengde;

import java.util.Iterator;

public class Medlem {

    private String navn;
    private MengdeADT<Hobby> hobbyer;
    private int statusIndeks; //Peker på partner om man har det, ellers -1

    public Medlem (String navn, MengdeADT<Hobby> hobbyer) {
        this.navn = navn;
        this.hobbyer = hobbyer;
        statusIndeks = -1;
    }

    public Medlem (String navn) {
        this.navn = navn;
        hobbyer = null;
        statusIndeks = -1;
    }

    /**
     * som avgjør om to medlemmer passer til hverandre
     * og altså kan danne et par. To medlemmer passer til hverandre dersom de har nøyaktig
     * samme hobbyer (tips: like mengder).
     * Hvis en av medlemmene har partner, returner false
     * @param medlem2 Et medlem
     * @return true dersom de passer, false ellers
     */
    public boolean passerTil(Medlem medlem2) {

        if (hobbyer.antall() != medlem2.hobbyer.antall() ||
                (statusIndeks != -1 && medlem2.statusIndeks != -1)) {
            return false;
        }

        Iterator<Hobby> m1 = hobbyer.oppramser(), m2 = medlem2.hobbyer.oppramser();
        while (m1.hasNext()) {
            while (m2.hasNext()) {
                if (!m1.next().equals(m2.next()) ) {
                    return false;
                }
            }
        }
        return true;
    }

    public void leggTilHobby(Hobby h) {
        if (hobbyer == null) {
            hobbyer = new KjedetMengde<>();
        }
        hobbyer.leggTil(h);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setStatusIndeks (int indeks) {
        statusIndeks = indeks;
    }

    public int getStatusIndeks () {
        return statusIndeks;
    }

    public void setHobbyer(MengdeADT<Hobby> hobbyer) {
        this.hobbyer = hobbyer;
    }

    public MengdeADT<Hobby> getHobbyer() {
        return hobbyer;
    }

    @Override
    public String toString () {
        return "[Navn=" + navn + ", hobbyer=" + hobbyer + ", statusIndeks=" + statusIndeks + "]";
    }
}