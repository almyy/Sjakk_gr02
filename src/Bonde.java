
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Bonde extends Brikke {
    private final boolean isHvit;
    
    Bonde(Rute start, boolean isHvit){
        super(start, 1);
        this.isHvit = isHvit;
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getVerdi() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rute getCurrent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
