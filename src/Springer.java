/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Springer {
    private final boolean isHvit;
    
    public Springer(Rute start, boolean isHvit){
        super(start, 1);  
    }
    
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(){
        ArrayList<Rute> lovligeTrekk = new ArrayList();
        Rute current = super.getCurrent();
    }
}
