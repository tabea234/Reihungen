
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.net.URI;

// import processing.core.PApplet;
import processing.data.Table;

/**
 * Die Test-Klasse MaximumsucheTest.
 *
 * @author  S.Gebert
 * @version 06.02.2020
 */
public class MaximumsucheTest
{
    private Maximumsuche s;

    /**
     * Führt zeitintensive Aktionen aus.
     * 
     * Wird einmalig vor allen Testfall-Methoden aufgerufen
     */
    @BeforeClass
    public static void init()
    {              
        // PApplet.main(new String[] {MaximumsucheTest.class.getName() });
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @Before
    public void setUp()
    {
        s = new Maximumsuche();
        s.verzoegerung=0;
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testFall1()
    {
        ladeTabelle("data/testfall1.csv");
        assertEquals("Maximum in testfall.csv nicht gefunden.",12, s.maximumsuche());

    }

    // Testfall 2: negative Zahlen
    @Test
    public void testFall2()
    {
        ladeTabelle("data/testfall2.csv");
        assertEquals("Maximum in testfall2.csv nicht gefunden.",3, s.maximumsuche());
    }

    // Testfall 3: Nur 1 Element
    @Test
    public void testFall3()
    {
        ladeTabelle("data/testfall3.csv");
        assertEquals("Maximum in testfall3.csv nicht gefunden.", 0, s.maximumsuche());
    }

    // Testfall 4: Leere Liste
    @Test
    public void testFall4()
    {
        ladeTabelle("data/testfall4.csv");
        assertEquals("Maximum in testfall4.csv nicht gefunden.", -1, s.maximumsuche());
    }

    /*
     * Ersetzt ladeTabelle() aus Maximumsuche, da diese nur funktioniert wenn setup() bereits ausgeführt wurde und
     * daher nicht mit JUnit kompatibel ist.
     */
    private void ladeTabelle(String name) {

        try{
            // Tabelle aus CSV-Datei laden
            Table csv = new Table(new File(name), "header,csv");

            if (csv != null  && csv.getColumnCount()==2) {
                // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
                s.zahlen = new int[csv.getRowCount()];
                s.namen = new String[csv.getRowCount()];

                // Fülle die Arrays mit Werten aus der Tabelle
                for (int i = 0; i < s.zahlen.length; i++) {
                    // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                    s.zahlen[i] = csv.getInt(i, "Punkte");
                    s.namen[i] = csv.getString(i, "Name");
                }
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }

    }
}

