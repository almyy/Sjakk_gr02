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
        if (x < 7) {
            if (y < 6) {
                lovligeTrekk.add(new Rute(x + 1, y + 2));
            }
            if (y > 1) {
                lovligeTrekk.add(new Rute(x + 1, y - 2));
            }
        }
        if (x > 0) {
            if (y < 6) {
                lovligeTrekk.add(new Rute(x - 1, y + 2));
            } if(y > 1) {
                lovligeTrekk.add(new Rute(x - 1, y - 2));
            }
        }
        if (x < 6) {
            if (y < 7) {
                lovligeTrekk.add(new Rute(x + 2, y + 1));
            } if(y > 0) {
                lovligeTrekk.add(new Rute(x + 2, y - 1));
            }
        }
        if (x > 1) {
            if (y < 7) {
                lovligeTrekk.add(new Rute(x - 2, y + 1));
            } if(y > 0) {
                lovligeTrekk.add(new Rute(x - 2, y - 1));
            }
        }
        return lovligeTrekk;
    }
}