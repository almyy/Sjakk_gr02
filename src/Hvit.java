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
            brikker.add(new Bonde(new Rute(i,1),true));
        }
        brikker.add(new Taarn(new Rute(1,1),true));
        brikker.add(new Taarn(new Rute(8,1),true));
        brikker.add(new Loper(new Rute(2,1),true));
        brikker.add(new Loper(new Rute(7,1),true));
        brikker.add(new Springer(new Rute(3,1),true));
        brikker.add(new Springer(new Rute(6,1),true));
        brikker.add(new Dronning(new Rute(4,1),true));
        brikker.add(new Konge(new Rute(5,1),true));
        
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
