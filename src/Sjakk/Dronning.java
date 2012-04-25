package Sjakk;

/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Dronning er brikken som representerer dronningen. Her håndteres logikken rundt selve brikken.
 */
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Dronning extends Brikke {
    /**
     * Konstruktør med en parameter.
     * @param isHvit 
     * Bestemmer om dronningen skal være svart eller hvit.
     */
    public Dronning(boolean isHvit) {
            super(6, isHvit,null);
            if(isHvit){
                super.setIcon(new ImageIcon(getClass().getResource("/images/whiteQueen.gif")));
            }else{
                super.setIcon(new ImageIcon(getClass().getResource("/images/blackQueen.gif")));
            }
    }
    /**
     * Regner ut hvilke ruter dronningen kan flytte til. Tar ikke hensyn til andre brikker i det hele tatt.
     * @param r
     * Ruten som dronningen står på.
     * @return 
     * Et ArrayList med ruter som bestemmer hvilke flytt dronningen kan ta.
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
