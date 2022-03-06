package no.hvl.dat102.oving5.oppgave3;

public class Parentes {

    private char parentes;
    private int linjenummer, posisjon;

    public Parentes(char parentes, int linjenummer, int posisjon) {
        this.parentes = parentes;
        this.linjenummer = linjenummer;
        this.posisjon = posisjon;
    }

    public char getParentes() {
        return parentes;
    }

    public void setParentes(char parentes) {
        this.parentes = parentes;
    }

    public int getLinjenummer() {
        return linjenummer;
    }

    public void setLinjenummer(int linjenummer) {
        this.linjenummer = linjenummer;
    }

    public int getPosisjon() {
        return posisjon;
    }

    public void setPosisjon(int posisjon) {
        this.posisjon = posisjon;
    }

}
