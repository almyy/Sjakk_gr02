package Sjakk;
/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Rute representerer hver rute i rutenettet i Brett-klassen. Her finner man brikken som står på denne ruten,
 * og da også hvilken x- og y-koordinat brikken har.
 */
class Rute {
    private final int x;
    private final int y;
    private Brikke brikke;
    /**
     * Konstruktør med to parametre. Setter x- og y-koordinatene.
     * @param x
     * x-koordinaten til ruta.
     * @param y 
     * y-koordinaten til ruta.
     */
    public Rute(int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * Henter ut brikken som står på denne ruta.
     * @return Brikkeobjekt på denne ruta.
     */
    public Brikke getBrikke() {
        return brikke;
    }
    /**
     * Endrer hvilken brikke som står på denne ruta.
     * @param b 
     * Brikken som skal stå på ruta.
     */
    public void setBrikke(Brikke b){
        brikke = b;
    }
    /**
     * Henter ut x-koordinaten til ruta
     * @return x-koordinaten.
     */
    public int getX() {
        return x;
    }
    /**
     * Henter ut y-koordinaten til ruta.
     * @return y-koordinaten.
     */
    public int getY() {
        return y;
    }
    /**
     * Sjekker om det står en brikke på denne ruta.
     * @return 
     * True eller false, om det står en brikke her eller ikke.
     */
    public boolean isOccupied(){
        if(brikke !=null){
            return true;
        }
        return false;
    }
    /**
     * Standard toString-metode
     * @return x- og y-koordinaten til ruta i form av tekst.
     */
    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
 