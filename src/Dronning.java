/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Dronning extends Brikke {
    
    public Dronning(Rute start, boolean isHvit) {
            super(start,6, isHvit,new ImageIcon("src/images/icon.gif"));
    
} 
    @Override
    public ArrayList<Rute> sjekkLovligeTrekk() {
    ArrayList<Rute> lovligeRuter = new ArrayList<>();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        
        for(int i = x+1; i < 8; i++){
                lovligeRuter.add(new Rute(i,y));
        }       
        for(int i = y +1; i < 8; i--){
              lovligeRuter.add(new Rute(x,i));
           
        }
        for(int i = x -1; i >= 0; i--){
               lovligeRuter.add(new Rute(i,y));
            
        }
        for(int u = y - 1; u >= 0; u--){
                lovligeRuter.add(new Rute(x,u));
            }
        for(int i = x -1; i >= 0; i--){
            for(int u = y - 1; i>= 0; i--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = x -1; i >= 0; i--){
            for(int u = y + 1; i < 8; i++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = x + 1; i < 8; i++){
            for(int u = y - 1; u >= 0; u--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        
        return lovligeRuter;
    }
}
