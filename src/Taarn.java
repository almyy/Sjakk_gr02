
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
    
    public Taarn(Rute start, boolean isHvit) {
        super(start, 5, isHvit,new ImageIcon("src/images/icon.gif"));
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        Rute rute = super.getCurrent();
        int x = rute.getX();
        int y = rute.getY();
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
