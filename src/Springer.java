/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Springer extends Brikke {
    
    public Springer(Rute start){
        super(start, 1);  
    }
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk(){
        ArrayList<Rute> lovligeTrekk = new ArrayList();
        Rute current = super.getCurrent();
        return lovligeTrekk;
    }
}
