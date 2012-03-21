
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
    
    public Bonde(boolean isHvit){
        super(1, isHvit,null);
        if(isHvit){
            super.setIcon("src/images/whitepawn.gif");
        }else{
            super.setIcon("src/images/blackpawn.gif");
        }
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        if(isHvit() && y < 8) {
            lovligeTrekk.add(new Rute(x, y+1));
            if(y==1){
                lovligeTrekk.add(new Rute(x,y+2));
            }
            return lovligeTrekk;
        }
        else if(!isHvit() && y > 0) {
            lovligeTrekk.add(new Rute(x, y-1));
            if(y==6){
                lovligeTrekk.add(new Rute(x,y-2));
            }
            return lovligeTrekk;
        }
        else return null;
    }
}
