package Sjakk;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Hvit extends Spiller {

    private ArrayList<Brikke> brikker;

    public Hvit() {
        this.brikker = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            brikker.add(new Bonde(true));
        }
        brikker.add(new Taarn(true));
        brikker.add(new Taarn(true));
        brikker.add(new Loper(true));
        brikker.add(new Loper(true));
        brikker.add(new Springer(true));
        brikker.add(new Springer(true));
        brikker.add(new Dronning(true));
        brikker.add(new Konge(true));

    }

    @Override
    public boolean removePiece(Brikke b) {
        for (int i = 0; i < brikker.size(); i++) {
            if (brikker.get(i).equals(b)) {
                brikker.remove(i);
                return true;
            }
        }
        return false;
    }

    public void promotePiece(Brikke b, Brikke p) {
        brikker.remove(b);
        brikker.add(p);
    }

    public ArrayList<Brikke> getBrikker() {
        return brikker;
    }
}
