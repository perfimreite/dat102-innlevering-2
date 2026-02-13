package no.hvl.dat102.sorteringVedInnsetting;

import java.util.Arrays;
import java.util.Random;

public class SorteringVedInnsetting {
    public static void main(String[] args) {
        Random r = new Random(0);
        int n = 512 * 256;
        Integer[] original = new Integer[n];
        for (int i = 0; i < n; i++) {
            original[i] = r.nextInt();
        }

        long start, finish;
        double timeElapsed;
        Integer[] a;

        a = Arrays.copyOf(original, original.length);
        start = System.currentTimeMillis();
        sorteringMedMinstFoerst(a);
        assert(erSortert(a));
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start) / 1000.0;
        System.out.println("Sortering uten modifikasjon: " + timeElapsed + "s");

        a = Arrays.copyOf(original, original.length);
        start = System.currentTimeMillis();
        sorteringMedMinstFoerst(a);
        assert(erSortert(a));
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start) / 1000.0;
        System.out.println("Sortering med minste først: " + timeElapsed + "s");

        a = Arrays.copyOf(original, original.length);
        start = System.currentTimeMillis();
        sorteringParvis(a);
        assert(erSortert(a));
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start) / 1000.0;
        System.out.println("Sortering parvis: " + timeElapsed + "s");

        a = Arrays.copyOf(original, original.length);
        start = System.currentTimeMillis();
        sorteringParvisMedMinsteFoerst(a);
        assert(erSortert(a));
        finish = System.currentTimeMillis();
        timeElapsed = (finish - start) / 1000.0;
        System.out.println("Sortering parvis med minste først: " + timeElapsed + "s");
    }

    public static void sorteringUtenModifikasjon(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            Integer temp = a[i];
            while (temp.compareTo(a[j]) < 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[++j] = temp;
        }
    }

    public static void sorteringMedMinstFoerst(Integer[] a) {
        {
            int minIdx = 0;
            Integer min = a[minIdx];
            for (int i = 1; i < a.length; i++) {
                if (a[i].compareTo(min) <= 0) {
                    min = a[i];
                    minIdx = i;
                }
            }

            Integer temp = a[minIdx];
            a[minIdx] = a[0];
            a[0] = temp;
        }

        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            Integer temp = a[i];
            while (temp.compareTo(a[j]) < 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[++j] = temp;
        }
    }

    public static void sorteringParvis(Integer[] a) {
        int n = a.length;
        if (n <= 1) return;

        for (int i = 1; i < n; i += 2) {
            int min = a[i];
            int max = (i + 1 < n) ? a[i + 1] : min;
            if (min > max) {
                int tmp = min;
                min = max;
                max = tmp;
            }

            int j = i - 1;
            for (;j >= 0 && max < a[j]; j--) {
                if (j + 2 < n) {
                    a[j + 2] = a[j];
                }
            }
            int maxPos = j + 2;
            if (maxPos < n) {
                a[maxPos] = max;
            }

            for (;j >= 0 && min < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = min;
        }
    }

    public static void sorteringParvisMedMinsteFoerst(Integer[] a) {
        {
            int minIdx = 0;
            Integer min = a[minIdx];
            for (int i = 1; i < a.length; i++) {
                if (a[i].compareTo(min) <= 0) {
                    min = a[i];
                    minIdx = i;
                }
            }

            Integer temp = a[minIdx];
            a[minIdx] = a[0];
            a[0] = temp;
        }

        int n = a.length;
        if (n <= 1) return;

        for (int i = 1; i < n; i += 2) {
            int min = a[i];
            int max = (i + 1 < n) ? a[i + 1] : min;
            if (min > max) {
                int tmp = min;
                min = max;
                max = tmp;
            }

            int j = i - 1;
            for (;j >= 0 && max < a[j]; j--) {
                if (j + 2 < n) {
                    a[j + 2] = a[j];
                }
            }

            int maxPos = j + 2;
            if (maxPos < n) {
                a[maxPos] = max;
            }

            for (;j >= 0 && min < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = min;
        }
    }

    private static boolean erSortert(Integer[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }
}
