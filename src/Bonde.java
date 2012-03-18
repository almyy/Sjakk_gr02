
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
public class Bonde extends Brikke {
    
    public Bonde(Rute start, boolean isHvit){
        super(start, 1, isHvit,new ImageIcon("src/images/icon.gif"));
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        if(isHvit() && y < 8) {
            lovligeTrekk.add(new Rute(x, y+1));
            if (x < 8) {
                lovligeTrekk.add(new Rute(x + 1, y + 1));
            }
            else if(x > 1) {
                lovligeTrekk.add(new Rute(x - 1, y + 1));
            }
            return lovligeTrekk;
        }
        else if(!isHvit() && y > 0) {
            lovligeTrekk.add(new Rute(x, y-1));
            if(x < 8) {
                lovligeTrekk.add(new Rute(x-1, y+1));
            }
            else if(x > 1) {
                lovligeTrekk.add(new Rute(x+1, y +1));
            }
            return lovligeTrekk;
        }
        else return null;
    }
}
