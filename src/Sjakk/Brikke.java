package Sjakk;

import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author Team 02, AITeL@HiST
 * 
 * Klasen Brikke er superklassen til alle de forskjellige brikkene.
 */

abstract class Brikke {
    protected final static int OVRE_GRENSE = 7;
    protected final static int OVRE_GRENSE_TO = 6;
    protected final static int NEDRE_GRENSE = 0;
    protected final static int NEDRE_GRENSE_TO = 1;
    private final int verdi;
    private boolean isHvit;
    private ImageIcon icon;
    
    /**
     * Konstruktør som tar i mot parametre fra subklassene.
     * @param verdi
     * Verdien til de forskjellige brikkene.
     * @param isHvit
     * Bestemmer om brikken er hvit eller svart.
     * @param icon 
     * Bildet som skal assosieres med brikken.
     */
    public Brikke(int verdi, boolean isHvit, ImageIcon icon){
        this.verdi = verdi;
        this.isHvit = isHvit;
        this.icon=icon;
    }
    /**
     * Henter ut bildet til brikken.
     * @return 
     * Bildet til brikken som et ImageIcon
     */
    public ImageIcon getIcon(){
        return icon;
    }
    /**
     * Setter pathen til bildet inne i et ImageIcon
     * @param i 
     * En String som beskriver pathen til bildet.
     */
    public void setIcon(String i){
        this.icon = new ImageIcon(i);
    }
    /**
     * Finner de lovlige trekkene til de forskjellige brikkene. Metodekroppen er satt i de forskjellige brikkene.
     * @param r
     * Ruten brikken står på.
     * @return 
     * Et ArrayList med rutene som brikken kan flytte til.
     */
    public abstract ArrayList<Rute> sjekkLovligeTrekk(Rute r);
    /**
     * Henter ut verdien til brikken.
     * @return 
     * Verdien til brikken.
     */
    public int getVerdi() {
        return verdi;
    }
    /**
     * Sjekker om brikken er hvit eller svart.
     * @return 
     * True eller false, om brikken er hvit eller svart.
     */
    public boolean isHvit() {
        return isHvit;
    }
 }