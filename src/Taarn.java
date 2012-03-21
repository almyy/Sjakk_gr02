
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Taarn extends Brikke {
    
    public Taarn(boolean isHvit) {
        super(5, isHvit,new ImageIcon("src/images/icon.gif"));
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        for(int i = x; i < 8; i++) {
            lovligeTrekk.add(new Rute(i, y));
        }
        for(int i = x; i < 0; i--) {
            lovligeTrekk.add(new Rute(i, y));
        }
        for(int i = y; i < 8; i++) {
            lovligeTrekk.add(new Rute(x, i));
        }
        for(int i = y; i < 0; i--) {
            lovligeTrekk.add(new Rute(x, i));
        }
        return lovligeTrekk;
    }
    
    
}
