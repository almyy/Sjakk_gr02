
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Konge extends Brikke{
    
    public Konge(Rute start, boolean isHvit){
        super(start,0, isHvit);
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        Rute current = super.getCurrent();
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = current.getX();
        int y = current.getY();
        
        lovligeTrekk.add(new Rute(x+1,y));
        lovligeTrekk.add(new Rute(x+1,y+1));
        lovligeTrekk.add(new Rute(x+1,y-1));
        lovligeTrekk.add(new Rute(x-1,y));
        lovligeTrekk.add(new Rute(x-1,y+1));
        lovligeTrekk.add(new Rute(x-1,y-1));
        lovligeTrekk.add(new Rute(x,y+1));
        lovligeTrekk.add(new Rute(x,y-1));
        
        return lovligeTrekk;
        
    }
    
}
