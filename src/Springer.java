/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Springer extends Brikke {
    
    public Springer(Rute start){
        super(start, 1);  
    }
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        lovligeTrekk.add(new Rute(x +1, y + 2));
        lovligeTrekk.add(new Rute(x +1, y -2));
        lovligeTrekk.add(new Rute(x -1, y + 2));
        lovligeTrekk.add(new Rute(x -1, y -2));
        lovligeTrekk.add(new Rute(y +1, x + 2));
        lovligeTrekk.add(new Rute(y +1, x -2));
        lovligeTrekk.add(new Rute(y -1, x + 2));
        lovligeTrekk.add(new Rute(y -1, x -2));
        return lovligeTrekk;
    }
}
