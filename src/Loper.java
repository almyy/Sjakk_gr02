
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
        ArrayList<Rute> lovligeRuter = new ArrayList<>();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        for(int i = x+1; i < 9; i++){
            for(int u = y+1; u < 9; u++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = x -1; i > 0; i--){
            for(int u = y - 1; i>0; i--){
                lovligeRuter.add(new Rute(i,u));
            }//lol
        }
        for(int i = x -1; i > 0; i--){
            for(int u = y + 1; i < 9; i++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = x + 1; i < 9; i++){
            for(int u = y - 1; u > 0; u--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        return lovligeRuter;
    }   
}
