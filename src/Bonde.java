
import java.util.ArrayList;

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
        super(start, 1, isHvit);
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        Rute rute = super.getCurrent();
        int x = rute.getX();
        int y = rute.getY();
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
