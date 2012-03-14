/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Dronning extends Brikke {
    
    public Dronning(Rute start) {
            super(start,6);
    
} 
    public ArrayList<Rute> sjekkLovligeTrekk() {
    ArrayList<Rute> lovligeRuter = new ArrayList<>();
        Rute current = super.getCurrent();
        int x = current.getX();
        int y = current.getY();
        
        for(int i = x+1; i < 9; i++){
                lovligeRuter.add(new Rute(i,current.getY()));
            }       
        for(int i = y +1; i < 9; i--){
              lovligeRuter.add(new Rute(current.getX(),i));
           
        }
        for(int i = x -1; i > 0; i--){
               lovligeRuter.add(new Rute(i,current.getY()));
            
        }
        for(int u = y - 1; u > 0; u--){
                lovligeRuter.add(new Rute(current.getX(),u));
            }
        for(int i = x -1; i > 0; i--){
            for(int u = y - 1; i>0; i--){
                lovligeRuter.add(new Rute(i,u));
            }//lol
        }
        for(int i = x -1; i > 0; i--){
            for(int u = y + 1; i < 8; i++){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        for(int i = x + 1; i < 8; i++){
            for(int u = y - 1; u > 0; u--){
                lovligeRuter.add(new Rute(i,u));
            }
        }
        
        return lovligeRuter;
    }
}