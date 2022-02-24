package no.hvl.dat102.rekursiv;

public class RekursivMetode {

    // a
    public static int sumAvN(int n) { // 0 + 1 + 2 + ... + n
        if (n == 0) {
            return 0; //Basistilfellet
        }
        return n + sumAvN(n - 1);
    }

    // b
    public static int a(int n) {
        int resultat;
        if (n == 0) {
            resultat = 2;
        }
        else if (n == 1) {
            resultat = 5;
        }
        else {
            resultat = 5 * a(n - 1) - 6 * a(n - 2) + 2;
        }
        return resultat;
    }

    // c
    public static int f(int n) {
        if (n < 2) {
            return n;
        }

        return f(n-1) + f(n-2);
    }

    // d
    public static int fi(int n) {
        int sum2 = 0, sum = 1;

        for (int i = 2; i <= n; i++) {
            sum += sum2;
            sum2 = sum - sum2;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Sum: " + sumAvN(100));
        System.out.println("a = " + a(2));
        System.out.println("f rekursjon = " + f(20));
        System.out.println("f = " + fi(20));
    }

}
