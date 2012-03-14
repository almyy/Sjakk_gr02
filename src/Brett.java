/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
public class Brett {

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;

    public Brett() {
        for (int i = 1; i < 9; i++) {
            for(int u = 1; u < 9; u++) {
                this.ruter[i][u] = new Rute(i, u);
            }
        }
        this.hvit = new Hvit();
        this.svart = new Svart();
    }
}
