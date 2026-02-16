package no.hvl.dat102.parantesSkjekkerTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class parantesSkjekkerTest {
    public ParantesSkjekker p = new ParantesSkjekker();

    @Test
    public void testParantesSkjekker() {
        assertEquals(true, ParantesSkjekker.skjekkParanteser("{ [ ( ) ] }"));
        assertEquals(false, ParantesSkjekker.skjekkParanteser("[ ( ) ] }"));
        assertEquals(false, ParantesSkjekker.skjekkParanteser("{ [ ( ) }"));
        assertEquals(false, ParantesSkjekker.skjekkParanteser("{ [ ( ] ) }"));
        assertEquals(true, ParantesSkjekker.skjekkParanteser("""
class HelloWorld {
public static void main(String[] args) {
System.out.println("Hello World!");
}
}
"""));
    }
}
