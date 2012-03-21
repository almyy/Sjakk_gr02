/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Brett {

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;

    public Brett() {
        this.ruter = new Rute[8][8];
        this.hvit = new Hvit();
        this.svart = new Svart();
        for (int i = 7; i >= 0; i--) {
            for (int u = 0; u < 8; u++) {
                this.ruter[i][u] = new Rute(i, u);
            }
        }
        ArrayList<Brikke> bonderH = hvit.getBrikker();
        ArrayList<Brikke> bonderS = svart.getBrikker();

        for (int i = 0; i < bonderS.size(); i++) {
            if (bonderS.get(i) instanceof Bonde) {
                for (int j = 0; j < 8; j++) {
                   // this.ruter[j][6].setBrikke(bonderS.get(i));
                }
                 
            } else if (bonderS.get(i) instanceof Taarn) {
                this.ruter[0][7].setBrikke(bonderS.get(i));
                this.ruter[7][7].setBrikke(bonderS.get(i));
            } else if (bonderS.get(i) instanceof Springer) {
                this.ruter[1][7].setBrikke(bonderS.get(i));
                this.ruter[6][7].setBrikke(bonderS.get(i));
            } else if (bonderS.get(i) instanceof Loper) {
                this.ruter[2][7].setBrikke(bonderS.get(i));
                this.ruter[5][7].setBrikke(bonderS.get(i));
            } else if (bonderS.get(i) instanceof Dronning) {
                this.ruter[3][7].setBrikke(bonderS.get(i));
            } else if (bonderS.get(i) instanceof Konge) {
                this.ruter[4][7].setBrikke(bonderS.get(i));
            }
        }
        for (int i = 0; i < bonderH.size(); i++) {
            if (bonderH.get(i) instanceof Bonde) {
                for (int j = 0; j < 8; j++) {
                   // this.ruter[j][1].setBrikke(bonderH.get(i));
                }
                 
            } else if (bonderH.get(i) instanceof Taarn) {
                this.ruter[0][0].setBrikke(bonderH.get(i));
                this.ruter[7][0].setBrikke(bonderH.get(i));
            } else if (bonderH.get(i) instanceof Springer) {
                this.ruter[1][0].setBrikke(bonderH.get(i));
                this.ruter[6][0].setBrikke(bonderH.get(i));
            } else if (bonderH.get(i) instanceof Loper) {
                this.ruter[2][0].setBrikke(bonderH.get(i));
                this.ruter[5][0].setBrikke(bonderH.get(i));
            } else if (bonderH.get(i) instanceof Dronning) {
                this.ruter[3][0].setBrikke(bonderH.get(i));
            } else if (bonderH.get(i) instanceof Konge) {
                this.ruter[4][0].setBrikke(bonderH.get(i));
            }
        }
    }

    public Rute[][] getRuter() {
        return ruter;
    }

    public Rute getRute(int x, int y) {
        return ruter[x][y];
    }

    public ArrayList<String> getSvartMoves() {
        return svart.getMoves();
    }

    public ArrayList<String> getHvitMoves() {
        return hvit.getMoves();
    }

    public ImageIcon getIcon(int i, int j) {
        if (ruter[i][j].getBrikke().getIcon() != null) {
            return ruter[i][j].getBrikke().getIcon();
        }
        return null;
    }

    public ArrayList<Rute> sjekkLovligeTrekk(Rute rute) {
        Brikke brikke = rute.getBrikke();
        if (brikke instanceof Bonde) {
            Bonde bonde = (Bonde) brikke;
            int currentX = rute.getX();
            ArrayList<Rute> lovligeTrekk = bonde.sjekkLovligeTrekk(rute);
            int teller = lovligeTrekk.size();
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (bonde.isHvit()) {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    } else if (currentX != x && ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                } else if (!bonde.isHvit()) {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    } else if (currentX != x && ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Springer) {
            Springer springer = (Springer) brikke;
            ArrayList<Rute> lovligeTrekk = springer.sjekkLovligeTrekk(rute);
            int teller = lovligeTrekk.size();
            int cX = rute.getX();
            int cY = rute.getY();
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (ruter[cX][cY].getBrikke().isHvit()) {
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Konge) {
            Konge konge = (Konge) brikke;
            ArrayList<Rute> lovligeTrekk = konge.sjekkLovligeTrekk(rute);
            int teller = lovligeTrekk.size();
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                } else if (!konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Loper) {
            Loper loper = (Loper) brikke;
            ArrayList<Rute> rutene = loper.sjekkLovligeTrekk(rute);
            int teller = rutene.size();
            for (int i = 0; i < teller; i++) {
                int x = rutene.get(i).getX();
                int y = rutene.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()&&x<7&&y<7&&x>0&&y>0) {
                        int helpI = i;
                        i++;
                        
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()&&x<7&&x>0&&y<7&&y>0) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y&&i<rutene.size()) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }

                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                }
            }
            return rutene;
        } else if (brikke instanceof Dronning) {
            Dronning dronning = (Dronning) brikke;
            ArrayList<Rute> rutene = dronning.sjekkLovligeTrekk(rute);
            int teller = rutene.size();
            for (int i = 0; i < teller; i++) {
                int x = rutene.get(i).getX();
                int y = rutene.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }

                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                }
            }
            return rutene;

        } else if (brikke instanceof Taarn) {
            Taarn taarn = (Taarn) brikke;
            ArrayList<Rute> rutene = taarn.sjekkLovligeTrekk(rute);
            int teller = rutene.size();
            for (int i = 0; i < teller; i++) {
                int x = rutene.get(i).getX();
                int y = rutene.get(i).getY();
                if (taarn.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        if (x < 7) {
                            i++;
                            while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                                rutene.remove(i);
                                teller--;
                                i++;
                            }

                            i = helpI + 1;
                            while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                                rutene.remove(i);
                                teller--;
                                i++;
                            }
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }

                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() < y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() == x && rutene.get(i).getY() > y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() < x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI + 1;
                        while (rutene.get(i).getX() > x && rutene.get(i).getY() == y) {
                            rutene.remove(i);
                            teller--;
                            i++;
                        }
                        i = helpI;
                    }
                }
            }
            return rutene;
        }
        return null;
    }

    public void flyttBrikke(Rute startRute, ArrayList<Rute> lovligeTrekk, Rute flyttRute) {
        int fY = startRute.getX();
        int fX = startRute.getY();
        int sY = flyttRute.getX();
        int sX = flyttRute.getY();
        System.out.println("x " + fX + " y " + fY);
        this.ruter[fX][fY].setBrikke(ruter[sX][sY].getBrikke());
        this.ruter[sX][sY].setBrikke(null);
    }
}