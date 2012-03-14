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
    
    public Hvit(ArrayList<Brikke> brikker){
        this.brikker = new ArrayList<>();
        for(int i = 1; i < 9; i++){
            brikker.add(new Bonde(new Rute(i,2),true));
        }
        brikker.add(new Taarn(new Rute(1,1)));
        brikker.add(new Taarn(new Rute(1,8)));
        brikker.add(new Loper(new Rute(1,2)));
        brikker.add(new Loper(new Rute(1,7)));
        brikker.add(new Springer(new Rute(1,3)));
        brikker.add(new Springer(new Rute(1,6)));
        brikker.add(new Dronning(new Rute(1,4)));
        brikker.add(new Konge(new Rute(1,5)));
        
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
}
