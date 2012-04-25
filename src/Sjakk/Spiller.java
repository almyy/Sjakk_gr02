package Sjakk;

import java.util.ArrayList;

abstract class Spiller {

    private ArrayList<Brikke> brikker;

    public Spiller(boolean isWhite) {
        this.brikker = new ArrayList<>();
        if (!isWhite) {
            for (int i = 0; i < 8; i++) {
                brikker.add(new Bonde(false));
            }
            brikker.add(new Taarn(false));
            brikker.add(new Taarn(false));
            brikker.add(new Loper(false));
            brikker.add(new Loper(false));
            brikker.add(new Springer(false));
            brikker.add(new Springer(false));
            brikker.add(new Dronning(false));
            brikker.add(new Konge(false));
        } else {
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
    }

    /**
     * Fjerner en brikke fra ArrayListen
     *
     * @param b Brikken som skal fjernes
     * @return True eller false, om det gikk bra eller ikke.
     */
    public boolean removePiece(Brikke b) {
        for (int i = 0; i < brikker.size(); i++) {
            if (brikker.get(i).equals(b)) {
                brikker.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Bytter ut en brikke med en annen hvis en bonde kommer seg på andre siden
     * av brettet.
     *
     * @param b Bonden som skal fjernes.
     * @param p Brikken som skal ta over for bonden.
     */
    public Brikke promotePiece(Brikke b) {
        return b;
    }

    /**
     * Gir alle brikkene som er opprettet.
     *
     * @return ArrayList med alle brikkene.
     */
    public ArrayList<Brikke> getBrikker() {
        return brikker;
    }
}