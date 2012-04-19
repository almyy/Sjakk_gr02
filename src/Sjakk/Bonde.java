package Sjakk;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Bonde extends Brikke {
    private boolean unPasant;
    private int antRunderSpilt;
    public Bonde(boolean isHvit){
        super(1, isHvit,null);
        if(isHvit){
            super.setIcon("src/images/whitepawn.gif");
        }else{
            super.setIcon("src/images/blackpawn.gif");
        }
        unPasant = false;
        antRunderSpilt = 0;
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        if(isHvit() && y < Brikke.OVRE_GRENSE) {
            lovligeTrekk.add(new Rute(x, y+1));
            if(x<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y+1));
            if(x>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y+1));
            if(y==Brikke.NEDRE_GRENSE_TO){
                lovligeTrekk.add(new Rute(x,y+2));
            }
            return lovligeTrekk;
        }
        else if(!isHvit() && y > Brikke.NEDRE_GRENSE) {
            lovligeTrekk.add(new Rute(x, y-1));
            if(x<Brikke.OVRE_GRENSE)lovligeTrekk.add(new Rute(x+1,y-1));
            if(x>Brikke.NEDRE_GRENSE)lovligeTrekk.add(new Rute(x-1,y-1));
            if(y==Brikke.OVRE_GRENSE_TO){
                lovligeTrekk.add(new Rute(x,y-2));
            }
            return lovligeTrekk;
        }
        else return null;
    }
    public void incUnPasant(boolean b) {
        unPasant = b;
    }
    public boolean isUnPasant() {
        return unPasant;
    }
    public void setAntRunderSpilt(int i) {
        antRunderSpilt = i;
    }
    public int getAntRunderSpilt() {
        return antRunderSpilt;
    }
}
