/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rino
 */
import java.util.ArrayList;

public class Brett {

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;

    public Brett() {
        for (int i = 1; i < 9; i++) {
            for (int u = 1; u < 9; u++) {
                this.ruter[i][u] = new Rute(i, u);
            }
        }
        this.hvit = new Hvit();
        this.svart = new Svart();
    }

    public ArrayList<Rute> sjekkLovligeTrekk(Rute rute) {
        Brikke brikke = rute.getBrikke();
        if (brikke instanceof Bonde) {
            Bonde bonde = (Bonde) brikke;
            int currentX = bonde.getCurrent().getX();
            int currentY = bonde.getCurrent().getY();
            ArrayList<Rute> lovligeTrekk = bonde.sjekkLovligeTrekk();
            int teller = lovligeTrekk.size();
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (bonde.isHvit()) {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                    }
                    else if(currentX != x && ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                }
                else if(!bonde.isHvit()) {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                    }
                    else if(currentX != x && ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                }
            }
            return lovligeTrekk;            
        }
        else if (brikke instanceof Springer) {
            Springer springer = (Springer) brikke;
            ArrayList<Rute> lovligeTrekk = springer.sjekkLovligeTrekk();
            for (int i = 0; i < lovligeTrekk.size(); i++) {
                if (ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].getBrikke().isHvit()) {
                    if (ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].isOccupied() && ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                } else if (!ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].getBrikke().isHvit()) {
                    if (ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].isOccupied() && !ruter[lovligeTrekk.get(i).getX()][lovligeTrekk.get(i).getY()].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Konge) {
            Konge konge = (Konge) brikke;
            ArrayList<Rute> lovligeTrekk = konge.sjekkLovligeTrekk();
            for (int i = 0; i < lovligeTrekk.size(); i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                } else if (!konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Loper) {
        } else if (brikke instanceof Dronning) {
        } else if (brikke instanceof Taarn) {
            Taarn taarn = (Taarn) brikke;
            ArrayList<Rute> lovligeTrekk = taarn.sjekkLovligeTrekk();
            int teller = lovligeTrekk.size();
            for(int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if(taarn.isHvit()) {
                    if(ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while(lovligeTrekk.get(i).getX() > x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() < x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() > y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() < y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI;
                    }
                    else if(ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while(lovligeTrekk.get(i).getX() >= x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() <= x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() >= y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() <= y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI;
                    }
                }
                else if(!taarn.isHvit()) {
                    if(ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while(lovligeTrekk.get(i).getX() >= x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() <= x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() >= y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() <= y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI;
                    }
                    else if(ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        int helpI = i;
                        i++;
                        while(lovligeTrekk.get(i).getX() > x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() < x && lovligeTrekk.get(i).getY() == y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() > y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI+1;
                        while(lovligeTrekk.get(i).getX() == x && lovligeTrekk.get(i).getY() < y) {
                            lovligeTrekk.remove(i);
                            i++;
                        }
                        i = helpI;
                    }
                }
            }
        }
    }
}
