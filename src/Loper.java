
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
public class Loper extends Brikke {
    
    public Loper(boolean isHvit){
        super(5, isHvit,null);  
        if(isHvit){
            super.setIcon("src/images/whiteLoper.gif");
        }else{
            super.setIcon("src/images/blackLoper.gif");
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
                    if(u>=0){
                        lovligeRuter.add(new Rute(i,u));
                        u--;                    
                    }
                }
            } 
            if(y<7){
                int u = y+1;
                for(int i = x -1; i >= 0; i--){
                    if(u<=7){
                        lovligeRuter.add(new Rute(i,u));
                        u++;
                    }
                }
            }
        }
        if(x<7){
            if(y>0){
                int u = y-1;
                for(int i = x +1; i <= 7; i++){
                    if(u>=0){
                        lovligeRuter.add(new Rute(i,u));
                        u--;                    
                    }
                }
            } 
            if(y<7){
                int u = y+1;
                for(int i = x+1; i <= 7; i++){
                    if(u<=7){
                        lovligeRuter.add(new Rute(i,u));
                        u++;
                    }
                }
            }
        }
        return lovligeRuter;
    }   
}
