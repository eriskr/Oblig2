package no.hvl.dat102.oving5.oppgave3;

import no.hvl.dat102.exceptions.EmptyCollectionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParentessjekkerUtvidet extends Parentessjekker {

    private KjedetStabel<Parentes> stabel;

    public ParentessjekkerUtvidet() {
        stabel = new KjedetStabel<>();
    }

    public void erBalanserUtvidet() {

        File fil = new File("src/no/hvl/dat102/oving5/oppgave3/Oblig 2 uke 7.txt");

        try (Scanner scanner = new Scanner(fil)) {
            int linjenummer = 1;

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();

                for (int posisjon = 0; posisjon < s.length(); posisjon++) {

                    char c = s.charAt(posisjon);

                    if (erVenstreparentes(c)) {
                        stabel.push(new Parentes(c, linjenummer, posisjon));
                    }
                    else if (erHogreparentes(c)) {
                        try {
                            if (!erPar(stabel.pop().getParentes(), c)) {
                                System.out.println("Lukkesymbol " + c + " på linje " + linjenummer +
                                        ", tegn nr " + posisjon + " har feil åpnesymbol");
                            }
                        }
                        catch (EmptyCollectionException e) { //Hvis stabel er tom
                            System.out.println("Lukkesymbol " + c + " på linje " + linjenummer +
                                    ", tegn nr " + posisjon + " mangler tilsvarende åpnesymbol");
                        }
                    }//for
                }
                linjenummer++;
            }//while
        }
        catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet!");
        }

        while (!stabel.erTom()) {
            Parentes p = stabel.pop();
            System.out.println("Åpnesymbol " + p.getParentes() + " på linje " + p.getLinjenummer() +
                    ", tegn nr " + p.getPosisjon() + " har ikke tilsvarende lukkesymbol");
        }

    }

}
