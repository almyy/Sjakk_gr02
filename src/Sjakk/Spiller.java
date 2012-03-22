package Sjakk;

import java.util.ArrayList;
abstract class Spiller {
    private ArrayList<Brikke> brikker;
    private ArrayList<String> moves;
    
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
    public ArrayList<Brikke> getBrikker(){
        return brikker;
    }
}