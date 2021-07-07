
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;

/**
 * Klasse Highscoretabelle.
 * Balkendiagramm für int-Array, Zahlen werden aus CSV-Datei gelesen, Umwandlung in sortierte Tabelle.
 * Hinweis zur Benutzung: 
 * Klicke in das Zeichenfenster
 * Start des Algorithmus "wastutes" mit Taste "a"
 *
 * @author T. Schaller, S. Gebert
 * @version 05.02.2020
 */
public class Highscoretabelle extends PApplet
{       
    // Liste mit allen Werten //<>//
    int[]    zahlen;    
    String[] namen;

    // Hilfsvariablen für die Suche
    int akt_maximum=-1;    // aktuell groesstes Element
    int akt=-2;            // aktuell untersuchtes Element
    int verzoegerung=1000;  // Geschwindigkeit der Ausführung

    // Schriften
    PFont kleineSchrift;  
    PFont grosseSchrift;

    /**
     * settings() Methode 
     * Fenstergröße size(int width, int height) und smooth(int level) muss hier eingestellt werden.
     */
    @Override
    public void settings()
    {
        size(1000,700);
    }        

    /**
     * Die setup() Methode wird einmal aufgerufen, wenn das Programm startet.
     * Hier werden Einstellungen wie die Hintergrundfarbe vorgenommen
     * und Medien wie Bilder und Schriftarten geladen.
     */
    @Override
    public void setup()
    {
        background(0);
        // Schriften laden
        kleineSchrift = createFont("fonts/NotoSans-Medium.ttf", 12); //12 / 26
        grosseSchrift = createFont("fonts/NotoSansDisplay-Medium.ttf",20); //20 /48

        // CSV-Datei laden und anzeigen
        ladeTabelle("data/punkte.csv");
        zeichneBalken();
    }

    /**
     * Die draw() Methode wird nach der setup() Methode aufgerufen
     * und führt den Code innerhalb ihres Blocks fortlaufend aus,
     * bis das Programm gestoppt oder noLoop() aufgerufen wird.
     */
    @Override
    public void draw()
    {
        zeichneBalken();
    }

    @Override
    public void keyPressed() {
        // Animierte Suche mit Taste "a"
        if (key=='a') {
            thread("wastutes");
        }
    }

    public void ladeTabelle(String name) {
        // Tabelle aus CSV-Datei laden
        Table csv = loadTable(name, "header,csv");

        if (csv != null  && csv.getColumnCount()==2) {

            // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
            zahlen = new int[csv.getRowCount()];
            namen = new String[csv.getRowCount()];

            // Fülle die Arrays mit Werten aus der Tabelle
            for (int i = 0; i < zahlen.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                zahlen[i] = csv.getInt(i, "Punkte");
                namen[i] = csv.getString(i, "Name");
            }
        }
    }

    public void zeichneBalken() {

        clear();

        // Überschrift
        fill(255, 255, 255);
        textFont(grosseSchrift);
        text("Punkte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        if (zahlen != null) {
            for (int i = 0; i< zahlen.length; i++) {

                fill(20, 25, 165);
                // aktuelle Elemente farblich hervorheben
                if (i == akt || i == akt+1 ) {
                    fill(140, 230, 20);
                } 

                // Balkendiagramm zeichnen
                if (zahlen[i]>=0) rect(120, 25+i*15, zahlen[i]+1, 13);

                // Beschriftung
                fill(255, 255, 255);
                text(namen[i], 2, 35+i*15);
                text(zahlen[i], 70, 35+i*15);
            }
        }
    }

    public void wastutes() {
        // Sind überhaupt Daten da?
        if (zahlen.length==0 ) {
            return;
        }

        //
        for (akt=0; akt+1 < zahlen.length; akt++) {
            //  
            redraw();
            delay(verzoegerung);
            // 
            if (zahlen[akt+1]> zahlen[akt]) {
                // 
                int dummy = zahlen[akt];
                zahlen[akt] = zahlen[akt+1];
                zahlen[akt+1] = dummy;

                // 
                redraw();
                delay(verzoegerung);
            }
        }

        // 
        akt = -2;
        redraw();
        delay(verzoegerung);

    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(new String[] {Highscoretabelle.class.getName() });
    }

}
