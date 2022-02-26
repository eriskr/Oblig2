package no.hvl.dat102.oving4.oppgave2;

import no.hvl.dat102.adt.MengdeADT;
import no.hvl.dat102.oving4.oppgave1.tabell.TabellMengde;

public class Datakontakt {

    private final TabellMengde<Medlem> medlemmer;

    public Datakontakt(int antall) {
        medlemmer = new TabellMengde<>(antall);
    }

    public Datakontakt() {
        medlemmer = new TabellMengde<>();
    }

    /**
     * Legger til et medlem i tabellen
     */
    public void leggTilMedlem(Medlem person) {
        medlemmer.leggTil(person);
    }

    /**
     * Finner indeksen til et medlem, hvis medlem ikke blir funnet returner -1
     * @param medlemsnavn Et medlem
     * @return Indeksen til medlemmet, eller -1 hvis det ikke blir funnet
     */
    public int finnMedlemsIndeks(String medlemsnavn) {
        int i = 0;
        for (Medlem m : medlemmer) {
            if (m.getNavn().contains(medlemsnavn)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Finner ut om et medlem (medlemsnavn) passer med et annet medlem dersom det finnes.
     * @param medlem Navn på medlemmet som skal finne partner.
     * @return Indeksen til partneren eller -1 hvis ikke funnet.
     */
    public int finnPartnerFor(Medlem medlem) {

        int medlemIndeks, indeksM = -1;

        for (Medlem m : medlemmer) {
            if (m.passerTil(medlem)) {
                indeksM = (finnMedlemsIndeks(m.getNavn()));
                medlemIndeks = (finnMedlemsIndeks(medlem.getNavn()));

                setStatusIndeks(m, medlem, indeksM, medlemIndeks);
            }
        }
        return indeksM;
    }

    public int finnBestePartnerFor(Medlem m) {

        int besteMatch = -1, teller = 0;
        double besteVerdi = 0;

        for (Medlem medlem : medlemmer) {
            if (!medlem.equals(m)) {

                MengdeADT<Hobby> snitt = medlem.getHobbyer().snitt(m.getHobbyer());

                double antallEkstraHobbyerMedlem = medlem.getHobbyer().antall() - snitt.antall(),

                        antallEkstraHobbyerM = m.getHobbyer().antall() - snitt.antall(),

                        gjeldende = snitt.antall() - (antallEkstraHobbyerMedlem * antallEkstraHobbyerM) /
                                (snitt.antall() + antallEkstraHobbyerMedlem + antallEkstraHobbyerM);

                if (snitt.antall() != 0 && gjeldende > besteVerdi) {
                    besteVerdi = gjeldende;
                    besteMatch = teller;
                }
            }
            teller++;
        }
        if (besteMatch != -1) {
            setStatusIndeks(m, medlemmer.getElement(besteMatch), getPosisjon(m), besteMatch);
        }
        return besteMatch;
    }

    private void setStatusIndeks(Medlem m1, Medlem m2, int i1, int i2) {
        m1.setStatusIndeks(i2);
        m2.setStatusIndeks(i1);
    }

    private int getPosisjon(Medlem m) {
        for (int i = 0; i < medlemmer.antall(); i++) {
            if (m.equals(medlemmer.getElement(i))) {
                return i;
            }
        }
        return -1;
    }

    public void tilbakestillStatusIndeks(String medlemsnavn) {
        int m1 = finnMedlemsIndeks(medlemsnavn);
        Medlem medlem1 = medlemmer.getElement(m1);

        if (medlem1.getStatusIndeks() != -1) {
            int m2 = medlemmer.getElement(m1).getStatusIndeks();
            setStatusIndeks(medlem1, medlemmer.getElement(m2), -1, -1);
        }
    }

    public TabellMengde<Medlem> getMedlemmer() {
        return medlemmer;
    }

    @Override
    public String toString() {
        return medlemmer.toString();
    }
}

