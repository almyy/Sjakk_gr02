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
public class Taarn extends Brikke {
    
    public Taarn(boolean isHvit) {
        super(5, isHvit,null);
        if(isHvit){
            super.setIcon("src/images/whiteTaarn.gif");
        }else{
            super.setIcon("src/images/blackTaarn.gif");
        }
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        int x = r.getX();
        int y = r.getY();
        if(x<Brikke.OVRE_GRENSE){
            for(int i = x+1; i <= 7; i++) {
                lovligeTrekk.add(new Rute(i, y));
            }
        }
        if(x>Brikke.NEDRE_GRENSE){
            for(int i = x-1; i >= 0; i--) {
                lovligeTrekk.add(new Rute(i, y));
            }
        }
        if(y<Brikke.OVRE_GRENSE){
            for(int i = y+1; i <= 7; i++) {
                lovligeTrekk.add(new Rute(x, i));
            }
        }
        if(y>Brikke.NEDRE_GRENSE){
            for(int i = y-1; i >= 0; i--) {
                lovligeTrekk.add(new Rute(x, i));
            }
        }
        return lovligeTrekk;
    }
    
    
}
