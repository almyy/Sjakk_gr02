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
        lovligeTrekk.add(new Rute(current.getX() +1, current.getY() + 2));
        lovligeTrekk.add(new Rute(current.getX() +1, current.getY() -2));
        lovligeTrekk.add(new Rute(current.getX() -1, current.getY() + 2));
        lovligeTrekk.add(new Rute(current.getX() -1, current.getY() -2));
        lovligeTrekk.add(new Rute(current.getY() +1, current.getX() + 2));
        lovligeTrekk.add(new Rute(current.getY() +1, current.getX() -2));
        lovligeTrekk.add(new Rute(current.getY() -1, current.getX() + 2));
        lovligeTrekk.add(new Rute(current.getY() -1, current.getX() -2));
        return lovligeTrekk;
    }
}
