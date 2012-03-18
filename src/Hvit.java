/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
public class Hvit extends Spiller {
    private ArrayList<Brikke> brikker;
    
    public Hvit(){
        this.brikker = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            brikker.add(new Bonde(new Rute(i,6),true));
        }
        brikker.add(new Taarn(new Rute(0,7),true));
        brikker.add(new Taarn(new Rute(7,7),true));
        brikker.add(new Loper(new Rute(1,7),true));
        brikker.add(new Loper(new Rute(6,7),true));
        brikker.add(new Springer(new Rute(2,7),true));
        brikker.add(new Springer(new Rute(5,7),true));
        brikker.add(new Dronning(new Rute(4,7),true));
        brikker.add(new Konge(new Rute(3,7),true));
        
    }
    @Override
    public boolean removePiece(Brikke b){
        for(int i= 0; i< brikker.size(); i++){
            if(brikker.get(i).equals(b)){
                brikker.remove(i);
                return true;
            }
        }
        return false;
    }
    public void promotePiece(Brikke b,Brikke p){
        brikker.remove(b);
        brikker.add(p);
    }
    public ArrayList<Brikke> getBrikker(){
        return brikker;
    }
}
