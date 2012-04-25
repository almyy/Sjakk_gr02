package Sjakk;



import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klassen Konge er brikken som representerer kongen. Her håndteres logikken rundt selve brikken.
 */
public class Konge extends Brikke{
    
    /**
     * Konstruktør med èn parameter.
     * @param isHvit 
     * Bestemmer om kongen skal være svart eller hvit.
     */
    public Konge(boolean isHvit){
        super(0, isHvit,null);
        if(isHvit){
            super.setIcon(new ImageIcon("Images/whiteKing.png"));
        }else{
            super.setIcon(new ImageIcon("Images/blackKing.png"));
        }
    }
    /**
     * Regner ut hvilke ruter kongen kan flytte til. Tar ikke hensyn til andre brikker i det hele tatt.
     * @param r
     * Ruten som kongen står på.
     * @return 
     * Et ArrayList med ruter som bestemmer hvilke flytt kongen kan ta.
     */
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        
        if(x<Brikke.OVRE_GRENSE){
            lovligeTrekk.add(new Rute(x+1,y));
            if(y<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y+1));
            if(y>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y-1));
        }
        if(x>Brikke.NEDRE_GRENSE){
            lovligeTrekk.add(new Rute(x-1,y));
            if(y<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y+1));
            if(y>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y-1));
        }        
        if(y<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x,y+1));
        if(y>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x,y-1));
        
        return lovligeTrekk;
        
    }
    /**
     * Besetmmer om kongen har noen lovlige trekk å ta.
     * @param r
     * Ruteobjekt som bestemmer hvor kongen står.
     * @return 
     * True eller false, om kongen har noen lovlige trekk eller ikke.
     */
    public boolean hasMoves(Rute r){
        if(sjekkLovligeTrekk(r).isEmpty()){
            return false;
        }
        return true;
    }
    
}
