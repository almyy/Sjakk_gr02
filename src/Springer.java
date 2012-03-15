/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Springer extends Brikke {
    
    public Springer(Rute start, boolean isHvit){
        super(start, 1, isHvit,new ImageIcon("images/icon.gif"));  
    }
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        lovligeTrekk.add(new Rute(x +1, y +2));
        lovligeTrekk.add(new Rute(x +1, y -2));
        lovligeTrekk.add(new Rute(x -1, y +2));
        lovligeTrekk.add(new Rute(x -1, y -2));
        lovligeTrekk.add(new Rute(x +2, y +1));
        lovligeTrekk.add(new Rute(x +2, y -1));
        lovligeTrekk.add(new Rute(x -2, y +1));
        lovligeTrekk.add(new Rute(x -2, y -1));
        return lovligeTrekk;
    }
}