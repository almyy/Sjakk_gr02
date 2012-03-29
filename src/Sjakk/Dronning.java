package Sjakk;

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
public class Dronning extends Brikke {
    
    public Dronning(boolean isHvit) {
            super(6, isHvit,null);
            if(isHvit){
                super.setIcon("src/images/whiteQueen.gif");
            }else{
                super.setIcon("src/images/blackQueen.gif");
            }
} 
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeRuter = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        if(x>Brikke.NEDRE_GRENSE){
            if(y>Brikke.NEDRE_GRENSE){
                int u = y-1;
                for(int i = x -1; i >= 0; i--){
                    if(u>=Brikke.NEDRE_GRENSE){
                        lovligeRuter.add(new Rute(i,u));
                        u--;                    
                    }
                }
            } 
            if(y<Brikke.OVRE_GRENSE){
                int u = y+1;
                for(int i = x -1; i >= 0; i--){
                    if(u<=Brikke.OVRE_GRENSE){
                        lovligeRuter.add(new Rute(i,u));
                        u++;
                    }
                }
            }
        }
        if(x<Brikke.OVRE_GRENSE){
            if(y>Brikke.NEDRE_GRENSE){
                int u = y-1;
                for(int i = x +1; i <= 7; i++){
                    if(u>=Brikke.NEDRE_GRENSE){
                        lovligeRuter.add(new Rute(i,u));
                        u--;                    
                    }
                }
            } 
            if(y<Brikke.OVRE_GRENSE){
                int u = y+1;
                for(int i = x+1; i <= 7; i++){
                    if(u<=Brikke.OVRE_GRENSE){
                        lovligeRuter.add(new Rute(i,u));
                        u++;
                    }
                }
            }
        }if(x<Brikke.OVRE_GRENSE){
            for(int i = x+1; i <= 7; i++) {
                lovligeRuter.add(new Rute(i, y));
            }
        }
        if(x>Brikke.NEDRE_GRENSE){
            for(int i = x-1; i >= 0; i--) {
                lovligeRuter.add(new Rute(i, y));
            }
        }
        if(y<Brikke.OVRE_GRENSE){
            for(int i = y+1; i <= 7; i++) {
                lovligeRuter.add(new Rute(x, i));
            }
        }
        if(y>Brikke.NEDRE_GRENSE){
            for(int i = y-1; i >= 0; i--) {
                lovligeRuter.add(new Rute(x, i));
            }
        }
        return lovligeRuter;
    }
}
