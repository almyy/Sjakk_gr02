package Sjakk;
import java.util.ArrayList;
/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Bonde er brikken som representerer bonden Her håndteres logikken rundt selve brikken.
 */
public class Bonde extends Brikke {
    private boolean unPasant;
    private int antRunderSpilt;
    /**
     * Konstruktør med en parameter.
     * @param isHvit 
     * Bestemmer om bonden skal være svart eller hvit.
     */
    public Bonde(boolean isHvit){
        super(1, isHvit,null);
        if(isHvit){
            super.setIcon("src/images/whitepawn.gif");
        }else{
            super.setIcon("src/images/blackpawn.gif");
        }
        unPasant = false;
        antRunderSpilt = 0;
    }
    /**
     * Regner ut hvilke ruter bonden kan flytte til. Tar ikke hensyn til andre brikker i det hele tatt.
     * @param r
     * Ruten som bonden står på.
     * @return 
     * Et ArrayList med ruter som bestemmer hvilke flytt bonden kan ta.
     */
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        if(isHvit() && y < Brikke.OVRE_GRENSE) {
            lovligeTrekk.add(new Rute(x, y+1));
            if(x<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y+1));
            if(x>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y+1));
            if(y==Brikke.NEDRE_GRENSE_TO){
                lovligeTrekk.add(new Rute(x,y+2));
            }
            return lovligeTrekk;
        }
        else if(!isHvit() && y > Brikke.NEDRE_GRENSE) {
            lovligeTrekk.add(new Rute(x, y-1));
            if(x<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y-1));
            if(x>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y-1));
            if(y==Brikke.OVRE_GRENSE_TO){
                lovligeTrekk.add(new Rute(x,y-2));
            }
            return lovligeTrekk;
        }
        else return null;
    }
    /**
     * Setter variablen unPasant til parameteren.
     * @param b 
     * Bestemmer om bonden kan bli utført unPasant på.
     */
    public void incUnPasant(boolean b) {
        unPasant = b;
    }
    /**
     * Bestemmer om bonden kan la seg bli utført En Passant på.
     * @return
     * True eller false, bestemt av variablen unPasant.
     */
    public boolean isUnPasant() {
        return unPasant;
    }
    /**
     * Setter antall runder spilt i denne bonden. Dette holder styr på hvor mange turer det har gått siden bonden ble flyttet sist.
     * @param i 
     * Antall runder spilt.
     */
    public void setAntRunderSpilt(int i) {
        antRunderSpilt = i;
    }
    /**
     * Henter hvilken runde denne bonden ble brukt i sist.
     * @return 
     * Hvilken runde bonden ble brukt i sist.
     */
    public int getAntRunderSpilt() {
        return antRunderSpilt;
    }
}
