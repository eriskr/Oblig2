package no.hvl.dat102.oving4.oppgave2;

public class Hobby {

    private String hobbyNavn;

    public Hobby(String hobby) {
        hobbyNavn = hobby;
    }

    public String toString() {
        return hobbyNavn;
    }

    @Override
    public boolean equals(Object hobby2) {
        Hobby hobbyDenAndre = (Hobby) hobby2;
        return (hobbyNavn.equals(hobbyDenAndre.getHobbyNavn()));
    }

    public String getHobbyNavn() {
        return hobbyNavn;
    }

    public void setHobbyNavn(String hobbyNavn) {
        this.hobbyNavn = hobbyNavn;
    }
}