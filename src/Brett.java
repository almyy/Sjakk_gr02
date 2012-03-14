/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Brett {

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;

    public Brett() {
        for (int i = 1; i < 9; i++) {
            for(int u = 1; u < 9; u++) {
                this.ruter[i][u] = new Rute(i, u);
            }
        }
        this.hvit = new Hvit();
        this.svart = new Svart();
    }
    public ArrayList<Rute> sjekkLovligeTrekk(Rute rute) {
        Brikke brikke = rute.getBrikke();
        if(brikke instanceof Bonde) {
            Bonde bonde = (Bonde) brikke;
            int currentX = bonde.getCurrent().getX();
            int currentY = bonde.getCurrent().getY();
            ArrayList<Rute> lovligeTrekk = bonde.sjekkLovligeTrekk();
            for(int i = 0; i < lovligeTrekk.size(); i++) {
                if(lovligeTrekk.get(i).getX() == currentX && ruter[currentX][currentY+1].isOccupied()) {
                    lovligeTrekk.remove(i);
                }
                else if(lovligeTrekk.get(i).getX() == currentX+1 && lovligeTrekk.get(i).getY() == currentY+1) {
                    boolean isHvit = bonde.isHvit();
                    if(isHvit && ruter[currentX+1][currentY+1].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                    else if(!isHvit && !ruter[currentX+1][currentY+1].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                }
            }
        }
        if(brikke instanceof Springer) {
            
        }
    }
}
