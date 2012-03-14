
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Svart {
    private ArrayList<Brikke> brikker;
    
    public Svart(ArrayList<Brikke> brikker){
        this.brikker = new ArrayList<>();
        for(int i = 1; i < 9; i++){
            brikker.add(new Bonde(new Rute(i,7),false));
        }
        brikker.add(new Taarn(new Rute(1,8),false));
        brikker.add(new Taarn(new Rute(8,8),false));
        brikker.add(new Loper(new Rute(2,8),false));
        brikker.add(new Loper(new Rute(7,8),false));
        brikker.add(new Springer(new Rute(3,8),false));
        brikker.add(new Springer(new Rute(6,8),false));
        brikker.add(new Dronning(new Rute(4,8),false));
        brikker.add(new Konge(new Rute(5,8),false));        
    }
}
