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
public class Svart extends Spiller{
    private ArrayList<Brikke> brikker;
    
    public Svart(){
        this.brikker = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            brikker.add(new Bonde(false));
        }
        brikker.add(new Taarn(false));
        brikker.add(new Taarn(false));
        brikker.add(new Loper(false));
        brikker.add(new Loper(false));
        brikker.add(new Springer(false));
        brikker.add(new Springer(false));
        brikker.add(new Dronning(false));
        brikker.add(new Konge(false));        
    }
    public boolean removePiece(Brikke b){
        for(int i = 0; i < brikker.size(); i++){
            if(brikker.get(i).equals(b)){
                brikker.remove(b);
                return true;
            }
        }
        return false;
    }
    public boolean promotePiece(Brikke b, Brikke p){
        for(int i = 0; i < brikker.size(); i++){
            if(brikker.get(i).equals(b)){
                brikker.remove(i);
                brikker.add(p);
                return true;
            }
        }
        return false;
    }
    public ArrayList<Brikke> getBrikker(){
        return brikker;
    }
}
