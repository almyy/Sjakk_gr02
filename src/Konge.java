
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
public class Konge extends Brikke{
    
    public Konge(boolean isHvit){
        super(0, isHvit,new ImageIcon("src/images/icon.gif"));
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        
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
