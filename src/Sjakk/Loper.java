package Sjakk;


import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Loper er brikken som representerer løperen. Her håndteres logikken rundt selve brikken.
 */
public class Loper extends Brikke {
    /**
     * Konstruktør med en parameter.
     * @param isHvit 
     * Bestemmer om løperen skal være svart eller hvit.
     */
    public Loper(boolean isHvit){
        super(5, isHvit,null);  
        if(isHvit){
            super.setIcon(new ImageIcon(getClass().getResource("/Images/whiteLoper.gif")));
        }else{
            super.setIcon(new ImageIcon(getClass().getResource("/Images/blackLoper.gif")));
        }
    }
    /**
     * Regner ut hvilke ruter løperen kan flytte til. Tar ikke hensyn til andre brikker i det hele tatt.
     * @param r
     * Ruten som løperen står på.
     * @return 
     * Et ArrayList med ruter som bestemmer hvilke flytt løperen kan ta.
     */
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
        }
        return lovligeRuter;
    }   
}
