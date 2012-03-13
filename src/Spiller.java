/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
abstract class Spiller {
    private ArrayList<Brikke> brikker;
    private ArrayList<String> moves;
    
    public Spiller(ArrayList<Brikke> brikker){
        this.brikker = new ArrayList<>();    
    }
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
    public void setMoves(String s){
        moves.add(s);
    }
    public ArrayList<String> getMoves(){
        return moves;
    }
}