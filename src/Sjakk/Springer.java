package Sjakk;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Springer extends Brikke {

    public Springer(boolean isHvit){
        super(1, isHvit,null);  
        if(isHvit){
            super.setIcon("src/images/whiteSpringer.gif");
        }else{
            super.setIcon("src/images/blackSpringer.gif");
        }
    }
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