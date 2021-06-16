import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * Die Test-Klasse Balkendiagramm_ZufallTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Balkendiagramm_ZufallTest
{
    private Balkendiagramm_Zufall s;
    /**
     * Konstruktor fuer die Test-Klasse Balkendiagramm_ZufallTest
     */
    public Balkendiagramm_ZufallTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
        s = new Balkendiagramm_Zufall();
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    @DisplayName("Teste korrekte Arraylänge")
    public void testFall1()
    {
        try {
            int l = s.getZufallszahl(1, 999);
            s.erzeugeZufallsarray(l);
            assertEquals(l, s.zahlen.length, "Länge des Zufallsarrays falsch.");
        }
        catch (Exception e){
            fail("getZufallszahl erzeugt kein Array. Es kam zu folgendem Fehler: " + e.toString());
        }
    }

    @Test
    @DisplayName("Teste korrekte Arraylänge bei Länge 0")
    public void testFall2()
    {
        try {
            s.erzeugeZufallsarray(0);
            assertEquals(0, s.zahlen.length, "Länge des Zufallsarrays nicht 0");
        }
        catch (Exception e){
            fail("getZufallszahl erzeugt kein Array. Es kam zu folgendem Fehler: " + e.toString());
        }
    }

    @Test
    @DisplayName("Teste ob negative Parameter zu Fehlern führen.")
    public void testFall3()
    {
        int l = s.getZufallszahl(1, 999);
        assertDoesNotThrow(()->s.erzeugeZufallsarray(-l),"Fehlerhafte Eingaben (negative Längen) nicht abgefangen");
    }

}
