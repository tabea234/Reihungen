
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;

/**
 * Klasse Balkendiagramm_CSV.
 * Balkendiagramm für int-Array, Zahlen werden aus CSV-Datei gelesen. 
 *
 * @author Thomas Schaller, Simon Gebert 
 * @version 05.02.2020
 */
public class Balkendiagramm_CSV extends PApplet
{ 
    // Liste mit allen Werten 
    // Deklariere ein Array zahlen für die Punkte und ein Array namen für die Spielernamen
    int[]    zahlen;    
    String[] namen;
    //------------
    // TODO
    //------------

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

    public void ladeTabelle(String name) {
        // Tabelle aus CSV-Datei laden
        Table csv = loadTable(name,"header,csv");

        // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
        // Die Anzahl der gespeicherten Zeilen bekommt man mit csv.getRowCount()
        //------------
        zahlen = new int[csv.getRowCount()];
        namen = new String[csv.getRowCount()];

        // Fülle die Arrays mit Werten aus der Tabelle
        // Fülle die Arrays mit Werten aus der Tabelle
        // Mit csv.getInt(zeilennummer, "Name der Spalte") oder csv.getInt(zeilennummer, spaltennummer)
        // bekommt man die Werte der Tabelleneinträge als Integer-Wert
        // Informiere dich unter https://processing.org/reference/Table.html, welche Methode geeignet ist,
        // um die Namen der Spieler als String zu bekommen. getInt hilft hier nicht weiter.
        //------------
        for (int i = 0; i < zahlen.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                zahlen[i] = csv.getInt(i, "Punkte");
                namen[i] = csv.getString(i, "Name");
        }
        //------------  
    }

    public void zeichneBalken() {

        clear();

        // Überschrift
        fill(255,255,255);
        textFont(grosseSchrift);
        text("Punkte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        // lasse alle Ergebnisse der Spieler in der Form
        // SpielerXY  234 XXXXXXXXXXXXXXXX
        // SpielerZY  12  XX
        // usw.
        // darstellen. Wandle dazu dein Programm, um die Werte eines Arrays darzustelle ab.
        //------------
        for(int i=0; i < zahlen.length; i++) {
            // Balkendiagramm zeichnen
            fill(255,255,255);
            rect(120, 15*i+25, zahlen[i], 13); //2*zahlen[i]

            // Beschriftung
            fill(255,255,255);
            textFont(kleineSchrift);  
            text("i="+i, 2, 38+i*15);
            text("zahlen["+i+"]="+zahlen[i], 30, 38+i*15);
        }
        //------------

    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(Balkendiagramm_CSV.class, _args);
    }

}
