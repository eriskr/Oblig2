package no.hvl.dat102.oving5.oppgave3;

import no.hvl.dat102.adt.ParentessjekkerADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Parentessjekker implements ParentessjekkerADT {

    private final KjedetStabel<Character> stabel;

    public Parentessjekker() {
        stabel = new KjedetStabel<>();
    }

    @Override
    public boolean erVenstreparentes(char p) {
        return (p == '(' || p == '[' || p == '{');
    }

    @Override
    public boolean erHogreparentes(char p) {
        return (p == ')' || p == ']' || p == '}');
    }

    @Override
    public boolean erParentes(char p) {
        return erHogreparentes(p) || erVenstreparentes(p);
    }

    @Override
    public boolean erPar(char venstre, char hogre) {
        return venstre == '(' && hogre == ')' ||
                venstre == '[' && hogre == ']' ||
                venstre == '{' && hogre == '}';
    }

    @Override
    public boolean erBalansert(String s) {

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (erVenstreparentes(c)) {
                stabel.push(c);
            }
            else if (erHogreparentes(c)){
                try {
                    if (!erPar(stabel.pop(), c)) {
                        return false;
                    }
                }
                catch (EmptyCollectionException e) { //Hvis stabel er tom
                    return false;
                }
            }
        }
        return stabel.erTom(); //true hvis tom, false ellers
    }

    public static void main(String[] args) {
        Parentessjekker sjekker = new Parentessjekker();
        System.out.println(sjekker.erParentes(')'));
        System.out.println(sjekker.erParentes('K'));

//        System.out.println(sjekker.erBalansert("[(Hei)]"));
//        System.out.println(sjekker.erBalansert("{()]("));

//        System.out.println(sjekker.erBalansert("Dette er en tekst (som ikke har riktige paranteser(."));
        ParentessjekkerUtvidet sjekkerUtvidet = new ParentessjekkerUtvidet();
        sjekkerUtvidet.erBalanserUtvidet();
    }
}
