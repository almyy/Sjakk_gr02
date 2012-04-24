package Sjakk;


import java.util.ArrayList;

/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Bonde er brikken som representerer bonden Her håndteres logikken rundt selve brikken.
 */
public class Taarn extends Brikke {
    /**
     * Konstruktør med en parameter.
     * @param isHvit 
     * Bestemmer om tårn skal være svart eller hvit.
     */
    public Taarn(boolean isHvit) {
        super(5, isHvit,null);
        if(isHvit){
            super.setIcon("src/images/whiteTaarn.gif");
        }else{
            super.setIcon("src/images/blackTaarn.gif");
        }
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
        if(x<Brikke.OVRE_GRENSE){
            for(int i = x+1; i <= 7; i++) {
                lovligeTrekk.add(new Rute(i, y));
            }
        }
        if(x>Brikke.NEDRE_GRENSE){
            for(int i = x-1; i >= 0; i--) {
                lovligeTrekk.add(new Rute(i, y));
            }
        }
        if(y<Brikke.OVRE_GRENSE){
            for(int i = y+1; i <= 7; i++) {
                lovligeTrekk.add(new Rute(x, i));
            }
        }
        if(y>Brikke.NEDRE_GRENSE){
            for(int i = y-1; i >= 0; i--) {
                lovligeTrekk.add(new Rute(x, i));
            }
        }
        return lovligeTrekk;
    }
    
    
}
