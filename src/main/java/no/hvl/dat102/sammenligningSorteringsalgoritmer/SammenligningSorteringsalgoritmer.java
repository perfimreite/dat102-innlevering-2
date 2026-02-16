package no.hvl.dat102.sammenligningSorteringsalgoritmer;

import java.util.Arrays;
import java.util.Random;

public class SammenligningSorteringsalgoritmer {
    public static void main(String[] args) {

        int[] ns = {32000, 64000, 128000};
        int antallMaalinger = 3;

        printResultat("sorterVedInnsetting", ns, antallMaalinger);
        printResultat("utvalgssorter", ns, antallMaalinger);
        printResultat("kvikksorter", ns, antallMaalinger);
        printResultat("flettesorter", ns, antallMaalinger);
    }

    public static void printResultat(String metode, int[] ns, int antallMaalinger) {
        System.out.println("Resultat " + metode);
        System.out.println("     N Målinger  Tid(s) Teoretisk tid(s)");

        double c = 0;

        for (int n : ns) {

            long totalTid = 0;

            for (int i = 0; i < antallMaalinger; i++) {

                Integer[] d = genererArray(n);   // nytt array hver gang
                totalTid += maalTid(d, metode);
            }

            double gjennomsnitt = totalTid / (double) antallMaalinger;

            double f_n;

            if (metode.equals("sorterVedInnsetting") || metode.equals("utvalgssorter")) {
                f_n = n * (double) n;
            } else {
                f_n = n * (Math.log(n) / Math.log(2));
            }

            if (c == 0) {
                c = gjennomsnitt / f_n;
            }

            double teoretiskTid = c * f_n;

            System.out.printf("%6d %8d %7.3f %16.3f\n", n, antallMaalinger, gjennomsnitt / 1e9, teoretiskTid / 1e9);
        }

        System.out.println("---------------------------------------------");
    }

    public static Integer[] genererArray(int n) {
        Random tilfeldig = new Random();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = tilfeldig.nextInt();
        }
        return arr;
    }

    public static long maalTid(Integer[] a, String metode) {
        long start = System.nanoTime();   // mer presis

        switch (metode) {
            case "sorterVedInnsetting":
                a = sorterVedInnsetting(a);
                break;
            case "utvalgssorter":
                a = utvalgssorter(a);
                break;
            case "kvikksorter":
                a = kvikksorter(a);
                break;
            case "flettesorter":
                a = flettesorter(a);
                break;
        }

        long end = System.nanoTime();
        return (end - start);
    }

    public static Integer[] sorterVedInnsetting(Integer[] a) {
        for (int i = 1; i < a.length; i++) {

            int j = i - 1;
            Integer temp = a[i];

            while (j >= 0 && temp.compareTo(a[j]) < 0) {
                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = temp;
        }

        return a;
    }

    public static Integer[] utvalgssorter(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {

            int m = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[m]) < 0) {   // FIX: compareTo
                    m = j;
                }
            }

            bytt(a, i, m);
        }

        return a;
    }

    public static Integer[] kvikksorter(Integer[] a) {
        kvikksorter(a, 0, a.length - 1);   // FIX: riktig sluttindeks
        return a;
    }

    private static void kvikksorter(Integer[] a, int start, int slutt) {
        if (start < slutt) {

            int pivotIndex = partisjoner(a, start, slutt);

            kvikksorter(a, start, pivotIndex - 1);
            kvikksorter(a, pivotIndex + 1, slutt);
        }
    }

    private static int partisjoner(Integer[] a, int start, int slutt) {
        Integer pivot = a[slutt];
        int i = start - 1;

        for (int j = start; j < slutt; j++) {

            if (a[j].compareTo(pivot) <= 0) {
                i++;
                bytt(a, i, j);
            }
        }

        bytt(a, i + 1, slutt);
        return i + 1;
    }

    public static Integer[] flettesorter(Integer[] a) {
        if (a.length < 2) {
            return a;
        }

        int midt = a.length / 2;

        Integer[] venstre = Arrays.copyOfRange(a, 0, midt);
        Integer[] hoyre = Arrays.copyOfRange(a, midt, a.length);

        venstre = flettesorter(venstre);
        hoyre = flettesorter(hoyre);

        return flett(venstre, hoyre);
    }

    public static Integer[] flett(Integer[] a, Integer[] b) {
        Integer[] ut = new Integer[a.length + b.length];
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {

            if (a[i].compareTo(b[j]) <= 0) {
                ut[i + j] = a[i];
                i++;
            } else {
                ut[i + j] = b[j];
                j++;
            }
        }

        while (i < a.length) {
            ut[i + j] = a[i];
            i++;
        }

        while (j < b.length) {
            ut[i + j] = b[j];
            j++;
        }

        return ut;
    }

    private static void bytt(Integer[] a, int i, int j) {
        Integer temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}