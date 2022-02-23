package no.hvl.dat102;

import no.hvl.dat102.adt.ParentessjekkerADT;
import no.hvl.dat102.exception.EmptyCollectionException;

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
        if (venstre == '(' && hogre == ')' ||
                venstre == '[' && hogre == ']' ||
                venstre == '{' && hogre == '}') {
            return true;
        }
        return false;
    }

    @Override
    public boolean erBalansert(String s) {

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (erParentes(c)) {
                if (erVenstreparentes(c)) {
                    stabel.push(c);
                }
                else { // erHoyreparentes
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
        }
        return stabel.erTom(); //true hvis tom, false ellers
    }

    public static void main(String[] args) {
        Parentessjekker sjekker = new Parentessjekker();
        System.out.println(sjekker.erParentes(')'));
        System.out.println(sjekker.erParentes('K'));

        System.out.println(sjekker.erBalansert("[(Hei)]"));
        System.out.println(sjekker.erBalansert("{()]("));

        System.out.println(sjekker.erBalansert("Dette er en tekst (som ikke har riktige paranteser(."));
    }
}
