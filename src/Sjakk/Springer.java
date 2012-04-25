package Sjakk;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Springer er brikken som representerer springeren. Her håndteres logikken rundt selve brikken.
 */
public class Springer extends Brikke {
    /**
     * Konstruktør med en parameter.
     * @param isHvit 
     * Bestemmer om springeren skal være svart eller hvit.
     */
    public Springer(boolean isHvit){
        super(1, isHvit,null);  
        if(isHvit){
            super.setIcon(new ImageIcon(getClass().getResource("/images/whiteSpringer.gif")));
        }else{
            super.setIcon(new ImageIcon(getClass().getResource("/images/blackSpringer.gif")));
        }
    }
    /**
     * Regner ut hvilke ruter springeren kan flytte til. Tar ikke hensyn til andre brikker i det hele tatt.
     * @param r
     * Ruten som springeren står på.
     * @return 
     * Et ArrayList med ruter som bestemmer hvilke flytt springeren kan ta.
     */
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList();
        int x = r.getX();
        int y = r.getY();
        if (x < Brikke.OVRE_GRENSE) {
            if (y < Brikke.OVRE_GRENSE_TO) {
                lovligeTrekk.add(new Rute(x + 1, y + 2));
            }
            if (y > Brikke.NEDRE_GRENSE_TO) {
                lovligeTrekk.add(new Rute(x + 1, y - 2));
            }
        }
        if (x > Brikke.NEDRE_GRENSE) {
            if (y < Brikke.OVRE_GRENSE_TO) {
                lovligeTrekk.add(new Rute(x - 1, y + 2));
            } if(y > Brikke.NEDRE_GRENSE_TO) {
                lovligeTrekk.add(new Rute(x - 1, y - 2));
            }
        }
        if (x < Brikke.OVRE_GRENSE_TO) {
            if (y < Brikke.OVRE_GRENSE) {
                lovligeTrekk.add(new Rute(x + 2, y + 1));
            } if(y > Brikke.NEDRE_GRENSE) {
                lovligeTrekk.add(new Rute(x + 2, y - 1));
            }
        }
        if (x > Brikke.NEDRE_GRENSE_TO) {
            if (y < Brikke.OVRE_GRENSE) {
                lovligeTrekk.add(new Rute(x - 2, y + 1));
            } if(y > Brikke.NEDRE_GRENSE) {
                lovligeTrekk.add(new Rute(x - 2, y - 1));
            }
        }
        return lovligeTrekk;
    }
}