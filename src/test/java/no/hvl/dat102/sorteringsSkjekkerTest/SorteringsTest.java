package no.hvl.dat102.sorteringsSkjekkerTest;

import static org.junit.jupiter.api.Assertions.*;

import no.hvl.dat102.sammenligningSorteringsalgoritmer.SammenligningSorteringsalgoritmer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SorteringsTest {
    SammenligningSorteringsalgoritmer s = new SammenligningSorteringsalgoritmer();
    Integer[] usortert = new Integer[]{74, 12, 5, 44, 99, 17, 53, 2, 9, 89};
    Integer[] sortert = new Integer[]{2, 5, 9, 12, 17, 44, 53, 74, 89, 99};

    @Test
    public void testSorterVedInnsetting() {
        Integer[] a = Arrays.copyOf(usortert, usortert.length);
        assertTrue(testErSortert(s.sorterVedInnsetting(a), sortert));
    }

    @Test
    public void testUtvalgssorter() {
        Integer[] a = Arrays.copyOf(usortert, usortert.length);
        assertTrue(testErSortert(s.utvalgssorter(a), sortert));
    }

    @Test
    public void testKvikksorter() {
        Integer[] a = Arrays.copyOf(usortert, usortert.length);
        assertTrue(testErSortert(s.kvikksorter(a), sortert));
    }

    @Test
    public void testFlettesorter() {
        Integer[] a = Arrays.copyOf(usortert, usortert.length);
        assertTrue(testErSortert(s.flettesorter(a), sortert));
    }

    private boolean testErSortert(Integer[] a, Integer[] b) {
        if (a.length != b.length) return false;

        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}
