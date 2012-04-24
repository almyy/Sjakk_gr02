package Sjakk;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Rute implements Serializable{
    private final int x;
    private final int y;
    private boolean isBlocking = false;
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
        if(brikke !=null){
            return true;
        }
        return false;
    }
    public boolean getBlocking(){
        return isBlocking;
    }public void setBlocking(boolean b){
        isBlocking = b;
    }
    public String toString() {
        return "x: " + x + ", y: " + y;
    }
}
 