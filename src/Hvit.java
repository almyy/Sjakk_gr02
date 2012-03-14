/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
public class Hvit extends Spiller {
    private ArrayList<Brikke> brikker;
    
    public Hvit(ArrayList<Brikke> brikker){
        this.brikker = new ArrayList<Brikke>();
        for(int i = 1; i < 9; i++){
            brikker.add(new Bonde(new Rute(i,2),true));
        }
        brikker.add(new Taarn(true,new Rute(1,1)));
        brikker.add(new Taarn(true,new Rute(1,8)));
        brikker.add(new Loper())
        
    }
}
