package Sjakk;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Rute {
    private final int x;
    private final int y;
    private Brikke brikke;
    
    public Rute(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Brikke getBrikke() {
        return brikke;
    }
    public void setBrikke(Brikke b){
        brikke = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean isOccupied(){
        if(getBrikke()!=null){
            return true;
        }
        return false;
    }
    public String toString() {
        return "X: " + x + ", y: " + y;
    }
}
