package no.hvl.dat102.rekursiv;

public class RekursivMetode {

    // a
    public static int sumAvN(int n) { // 0 + 1 + 2 + ... + n
        if (n == 0) {
            return 0; //Basistilfellet
        }
        return n + sumAvN(n - 1);
    }

    // b a_n = 5a_n-1 - 6a_n-2 + 2 for n > 1 og startkrav a_0 = 2, a_1 = 5
    public static int ledd(int n) {
        int resultat;
        if (n == 0) {
            resultat = 2;
        } else if (n == 1) {
            resultat = 5;
        } else {
            resultat = 5 * ledd(n - 1) - 6 * ledd(n - 2) + 2;
        }
        return resultat;
    }

    public static int f(int n) {
        if (n < 2) {
            return n;
        }

        return f(n-1) + f(n-2);
    }

    public static void main(String[] args) {
      //  System.out.println(sumAvN(100));
        //System.out.println("a = " + ledd(2));
        System.out.println(f(20));
    }

}
