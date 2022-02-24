package no.hvl.dat102.adt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Lag to testmetoder for hver av de 3 mengdeoperasjonene der:
 *
 * m1 og m2 har felles elementer
 * de ikke har felles elementer (mengdene er disjunkte).
 */
public abstract class MengdeADTTest {

    // Oppgave 1d

    protected abstract MengdeADT<Integer> reset();

    protected MengdeADT<Integer> mengde1;
    protected MengdeADT<Integer> mengde2;
    private Integer i0, i1, i2, i3, i4, i5;

    @BeforeEach
    void setup() {
        mengde1 = reset();
        mengde2 = reset();
        i0 = 0;
        i1 = 1;
        i2 = 2;
        i3 = 3;
        i4 = 4;
        i5 = 5;
    }

    void settInnFelles() {
        mengde1.leggTil(i0);
        mengde1.leggTil(i1);
        mengde1.leggTil(i2);

        mengde2.leggTil(i0);
        mengde2.leggTil(i1);
        mengde2.leggTil(i2);
    }

    void settInnUlik() {
        mengde1.leggTil(i0);
        mengde1.leggTil(i1);
        mengde1.leggTil(i2);
        mengde2.leggTil(i3);
        mengde2.leggTil(i4);
        mengde2.leggTil(i5);
    }

    @Test
    void unionFellesTest() {
        settInnFelles();
        MengdeADT<Integer> union = mengde1.union(mengde2);

        Assertions.assertEquals(i0, union.fjern(i0));
        Assertions.assertEquals(i1, union.fjern(i1));
        Assertions.assertEquals(i2, union.fjern(i2));

        Assertions.assertTrue(union.erTom());
    }

    @Test
    void unionUlikTest() {
        settInnUlik();
        MengdeADT<Integer> union = mengde1.union(mengde2);
        Assertions.assertEquals(i0, union.fjern(i0));
        Assertions.assertEquals(i1, union.fjern(i1));
        Assertions.assertEquals(i2, union.fjern(i2));
        Assertions.assertEquals(i3, union.fjern(i3));
        Assertions.assertEquals(i4, union.fjern(i4));
        Assertions.assertEquals(i5, union.fjern(i5));

        Assertions.assertTrue(union.erTom());
    }

    @Test
    void snittFellesTest() {
        settInnFelles();
        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);
        Assertions.assertEquals(i0, snitt.fjern(i0));
        Assertions.assertEquals(i1, snitt.fjern(i1));
        Assertions.assertEquals(i2, snitt.fjern(i2));
        Assertions.assertTrue(snitt.erTom());
    }

    @Test
    void snittUlikTest() {
        settInnUlik();
        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);

        Assertions.assertTrue(snitt.erTom());
    }

    @Test
    void differenseFellesTest() {
        settInnFelles();
        MengdeADT<Integer> differanse = mengde1.differens(mengde2);
        Assertions.assertTrue(differanse.erTom());
    }

    @Test
    void differenseUlikTest() {
        settInnUlik();
        MengdeADT<Integer> differanse = mengde1.differens(mengde2);
        Assertions.assertEquals(i0, differanse.fjern(i0));
        Assertions.assertEquals(i1, differanse.fjern(i1));
        Assertions.assertEquals(i2, differanse.fjern(i2));
        Assertions.assertTrue(differanse.erTom());
    }

}
