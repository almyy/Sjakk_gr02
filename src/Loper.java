
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Loper extends Brikke {
    
    public Loper(Rute start){
        super(start,5);  
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        Rute current = getCurrent();
        
    }

    @Override
    public Rute getCurrent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rute setCurrent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
