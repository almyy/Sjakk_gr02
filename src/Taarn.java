
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Taarn extends Brikke {
    
    public Taarn(Rute start) {
        super(start, 5);
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        Rute rute = super.getCurrent();
        int x = rute.getX();
        int y = rute.getY();
        for(int i = x; i < 9; i++) {
            lovligeTrekk.add(new Rute(i, y));
        }
        for(int i = x; i < 0; i--) {
            lovligeTrekk.add(new Rute(i, y));
        }
        for(int i = y; i < 9; i++) {
            lovligeTrekk.add(new Rute(x, i));
        }
        for(int i = y; i < 0; i--) {
            lovligeTrekk.add(new Rute(x, i));
        }
        return lovligeTrekk;
    }
    
    
}
