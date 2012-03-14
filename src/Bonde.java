
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
    
    public Bonde(Rute start, boolean isHvit){
        super(start, 1);
        this.isHvit = isHvit;
    }

    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        Rute rute = super.getCurrent();
        int x = rute.getX();
        int y = rute.getY();
    }
}
