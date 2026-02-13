package no.hvl.dat102.sammenligningSorteringsalgoritmer;

import java.util.Arrays;

public class SammenlihninhSorteringsalgoritmer {
    public static void main(String[] args) {
    }

    // sortering ved innsetting
    public static void sorteringVedInnsetting(Integer[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = i - 1;
            Integer temp = a[i];

            while (j >= 0 && temp.compareTo(a[j]) > 0) {
                a[j + 1] = a[j];
                j--;
            }

            a[++j] = temp;
        }
    }

    // utvalgssortering
    public static void utvalgssortering(Integer[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int m = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[m]) {
                    m = j;
                }
            }

            bytt(a, i, m);
        }
    }

    // kvikksortering
    public static void kvikksorter(Integer[] a) {
        kvikksorter(a, 0,a.length);
    }

    private static void kvikksorter(Integer[] a, int start, int slutt) {
        if (slutt - start <= 1) {
            return;
        }
        int pivot = start;
        int midt = partisjoner(a, start, slutt, pivot);
        kvikksorter(a, start, midt);
        kvikksorter(a, midt + 1, slutt);
        return;
    }

    private static int partisjoner(Integer[] a, int start, int slutt, int pivot) {
        int i = start;
        int j = slutt - 2;
        Integer piv = a[pivot];
        bytt(a, pivot, slutt - 1);

        while (i <= j) {
            while (i <= j && a[i].compareTo(piv) < 0 ) {
                i++;
            }
            while (i <= j && a[j].compareTo(piv) > 0) {
                j--;
            }
            if (i <= j) {
                bytt(a, i, j);
                i++;
                j--;
            }

        }

        bytt(a, i, slutt - 1);
        return i;
    }

    // flettesortering
    public static Integer[] flettesortering(Integer[] a) {
        if (a.length < 2) {
            return a;
        }

        int midt = a.length / 2;
        Integer[] venstre = Arrays.copyOfRange(a,0, midt);
        Integer[] hoyre = Arrays.copyOfRange(a, midt, a.length);

        venstre = flettesortering(venstre);
        hoyre = flettesortering(hoyre);

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