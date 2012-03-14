/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
abstract class Brikke {
    private final int verdi;
    private Rute start;
    private Rute current;
    
    public Brikke(Rute start, int verdi){
        this.start = start;
        this.current = start;
        this.verdi = verdi;
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
}