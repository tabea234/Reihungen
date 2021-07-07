
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;

/**
 * Klasse XYDiagramm.
 * XY-Diagramm für Schalldruckpegel, Zahlen werden aus CSV-Datei gelesen.
 *
 * @author T. Schaller, S. Gebert 
 * @version 05.02.2020
 */
public class XYDiagramm extends PApplet
{      
    // Liste mit allen Werten
    double[]    x_werte;    
    double[]    y_werte;

    // Hilfsvariablen für die Suche
    int akt_maximum=-1;    // aktuell groesstes Element
    int akt=-1;            // aktuell untersuchtes Element
    int verzoegerung=0;    // Geschwindigkeit der Ausführung

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
        ladeTabelle("data/Amplitudes.csv");
        stroke(250,250,200);
        //zeichneBalken();
        zeichneDiagramm();
    }

    //<>//
    public void ladeTabelle(String name) {
        // Tabelle aus CSV-Datei laden //<>//
        Table csv = loadTable(name, "header,csv");

        if (csv != null  && csv.getColumnCount()==2) {

            // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
            x_werte = new double[csv.getRowCount()];
            y_werte = new double[csv.getRowCount()];

            // Fülle die Arrays mit Werten aus der Tabelle
            for (int i = 0; i < x_werte.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "x_werte" bzw. "y_werte"
                x_werte[i] = csv.getDouble(i, "Time (s)");
                y_werte[i] = csv.getDouble(i, "Sound pressure level (dB)");
            }
        }
    }

    public void zeichneBalken() {

        // Überschrift
        fill(255,255,255);
        textFont(grosseSchrift);
        text("x_werte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        if (x_werte != null) {
            for (int i = 0; i<= 40; i++) {

                fill(20,25,165);
                // aktuelle Elemente farblich hervorheben
                if (i == akt) {
                    fill(140,230,20);
                } 
                if (i == akt_maximum) {
                    fill(230,60,140);
                } 

                // Balkendiagramm zeichnen
                if (x_werte[i]>=0) rect(120, 25+i*15, (int) y_werte[i]+ 1 , 13);

                // Beschriftung
                fill(255,255,255);
                //text(""+x_werte[i], 70, 38+i*15);
                text(""+y_werte[i], 2, 38+i*15);
                
            }
        }
    }
    
    public void zeichneDiagramm (){
        
         // Überschrift
        fill(255,255,255);
        textFont(grosseSchrift);
        text("x_werte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        if (x_werte != null) {
            for (int i = 0; i<= 40; i++) {

                fill(20,25,165);
                // aktuelle Elemente farblich hervorheben
                if (i == akt) {
                    fill(140,230,20);
                } 
                if (i == akt_maximum) {
                    fill(230,60,140);
                } 

                // XY-Diagramm zeichnen
                if (x_werte[i]>=0) point ((float)x_werte[i],(float)y_werte[i]);

                // Beschriftung
                //fill(255,255,255);
                //text(""+x_werte[i], 70, 38+i*15);
                //text(""+y_werte[i], 2, 38+i*15);
                
            }
        }
    }
        
        
        


    public int sucheMaximum(int[] zahlen) {
        // Sind überhaupt Daten da?
        if (zahlen.length==0 ) {
            return -1;
        }

        // Startwerte setzen
        akt_maximum = 0;
        akt = 1;

        // Alle Arrayelemente untersuchen
        while (akt < zahlen.length) {

            // Neues größtes Element??
            if (zahlen[akt]> zahlen[akt_maximum]) {
                // Dann merke dir das neue 
                akt_maximum = akt;

            }
            akt = akt + 1;
        }
        // Gib Position des größten Elements zurück
        return akt_maximum;
    } //<>//

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(new String[] {XYDiagramm.class.getName() });
    }

}
