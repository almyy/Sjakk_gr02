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
abstract class Brikke {
    private final int verdi;
    private Rute start;
    private Rute current;
    private boolean isHvit;
    private final Icon icon;
    
    public Brikke(Rute start, int verdi, boolean isHvit, Icon icon){
        this.start = start;
        this.current = start;
        this.verdi = verdi;
        this.isHvit = isHvit;
        this.icon=icon;
    }
    
    public abstract ArrayList<Rute> sjekkLovligeTrekk();
    
    public int getVerdi() {
        return verdi;
    }
    public Rute getCurrent(){
        return current;
    }
    public void setCurrent(Rute r){
        current = r;
    }
    public boolean isHvit() {
        return isHvit;
    }
}