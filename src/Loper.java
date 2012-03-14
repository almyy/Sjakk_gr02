
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
        for(int i = current.getX()+1; i < 8; i++){
            for(int u = current.getY()+1; u < 8; u++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = current.getX() -1; i > 0; i--){
            for(int u = current.getY() - 1; i>0; i--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = current.getX() -1; i > 0; i--){
            for(int u = current.getY() + 1; i < 8; i++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = current.getX() + 1; i < 8; i++){
            for(int u = current.getY() - 1; u > 0; u--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
    }   
}
