package Sjakk;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
abstract class Brikke implements Serializable {
    protected final static int OVRE_GRENSE = 7;
    protected final static int OVRE_GRENSE_TO = 6;
    protected final static int NEDRE_GRENSE = 0;
    protected final static int NEDRE_GRENSE_TO = 1;
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
    public void setIcon(String i){
        this.icon = new ImageIcon(i);
    }
    public abstract ArrayList<Rute> sjekkLovligeTrekk(Rute r);
    
    public int getVerdi() {
        return verdi;
    }
    public boolean isHvit() {
        return isHvit;
    }
}