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
        if(x>0){
            if(y>0){
                int u = y-1;
                for(int i = x -1; i >= 0; i--){
                    lovligeRuter.add(new Rute(i,u));
                    if(u>0){
                        u--;                    
                    }
                }
            } 
            if(y<7){
                int u = y+1;
                for(int i = x -1; i >= 0; i--){
                    lovligeRuter.add(new Rute(i,u));
                    if(u<7){
                        u++;
                    }
                }
            }
        }
        if(x<7){
            if(y>0){
                int u = y-1;
                for(int i = x +1; i <= 7; i++){
                    lovligeRuter.add(new Rute(i,u));
                    if(u>0){
                        u--;                    
                    }
                }
            } 
            if(y<7){
                int u = y+1;
                for(int i = x+1; i <= 7; i++){
                    lovligeRuter.add(new Rute(i,u));
                    if(u<7){
                        u++;
                    }
                }
            }
        }
        
        return lovligeRuter;
    }
}
