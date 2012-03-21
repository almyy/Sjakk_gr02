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
abstract class Brikke {
    private final int verdi;
    private boolean isHvit;
    private ImageIcon icon;
    
    public Brikke(int verdi, boolean isHvit, ImageIcon icon){
        this.verdi = verdi;
        this.isHvit = isHvit;
        this.icon=icon;
    }
    public ImageIcon getIcon(){
        return icon;
    }
    public abstract ArrayList<Rute> sjekkLovligeTrekk(Rute r);
    
    public int getVerdi() {
        return verdi;
    }
    public boolean isHvit() {
        return isHvit;
    }
}