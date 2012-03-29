package Sjakk;

import java.io.Serializable;
import java.util.ArrayList;
abstract class Spiller implements Serializable{
    private ArrayList<Brikke> brikker;
    
    public boolean removePiece(Brikke b){
        for(int i = 0; i < brikker.size(); i++){
            if(brikker.get(i).equals(b)){
                brikker.remove(i);
                return true;
            }
        }
        return false;
    }
    public Brikke promotePiece(Brikke b){
        return b;
    }
    public ArrayList<Brikke> getBrikker(){
        return brikker;
    }
}