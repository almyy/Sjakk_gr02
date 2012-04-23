package Sjakk;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Rino
 */
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Brett implements Serializable {

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;
    private ArrayList<Brikke> bonderH;
    private ArrayList<Brikke> bonderS;
    private boolean rokadeKTH = false;
    private boolean rokadeKTS = false;
    private boolean rokadeHV = false;
    private boolean rokadeHH = false;
    private boolean rokadeSV = false;
    private boolean rokadeSH = false;
    private boolean blockingCheck = false;
    private int antRunderSpilt;
    private int KongeH = 0;
    private int KongeS = 0;
    private int TaarnHH = 0;
    private int TaarnHV = 0;
    private int TaarnSH = 0;
    private int TaarnSV = 0;
    

    public Brett() {
        this.ruter = new Rute[8][8];
        this.hvit = new Hvit();
        this.svart = new Svart();
        this.bonderH = hvit.getBrikker();
        this.bonderS = svart.getBrikker();
        for (int i = 7; i >= 0; i--) {
            for (int u = 0; u < 8; u++) {
                this.ruter[i][u] = new Rute(i, u);
            }
        }


        for (int i = 0; i < bonderS.size(); i++) {
            if (bonderS.get(i) instanceof Bonde) {
                for (int j = 0; j < 8; j++) {
                    this.ruter[j][6].setBrikke(bonderS.get(i));
                    i++;
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
                    this.ruter[j][1].setBrikke(bonderH.get(i));
                    i++;
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
        antRunderSpilt = 0;
    }

    public Rute[][] getRuter() {
        return ruter;
    }

    public void setBlockingCheck(boolean b) {
        blockingCheck = b;
    }

    public boolean getBlockingCheck() {
        return blockingCheck;
    }

    public Rute getRute(int x, int y) {
        return ruter[x][y];
    }

    public ImageIcon getIcon(int i, int j) {
        if (ruter[i][j].getBrikke().getIcon() != null) {
            return ruter[i][j].getBrikke().getIcon();
        }
        return null;
    }

    private boolean checkIfSecured(Rute r) {
        Rute sjekk = ruter[r.getX()][r.getY()];
        boolean whiteTurn = !sjekk.getBrikke().isHvit();
        ArrayList<Rute> trekk = new ArrayList<>();
        boolean res = false;
        boolean help = false;
        int hoyreT = 0;
        int venstreT = 0;
        int oppT = 0;
        int nedT = 0;
        int hoyreOppT = 0;
        int venstreOppT = 0;
        int hoyreNedT = 0;
        int venstreNedT = 0;
        if (whiteTurn) {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && !ruter[i][u].getBrikke().isHvit()) {
                        Brikke b = ruter[i][u].getBrikke();
                        if (b instanceof Bonde) {
                            Bonde bond = (Bonde) b;
                            trekk = bond.sjekkLovligeTrekk(ruter[i][u]);
                            for (int x = 0; x < trekk.size(); x++) {
                                if (trekk.get(x).getX() == ruter[i][u].getX()) {
                                    trekk.remove(x);
                                    x--;
                                }
                            }
                        } else if (b instanceof Springer) {
                            Springer s = (Springer) b;
                            trekk = s.sjekkLovligeTrekk(ruter[i][u]);
                        } else if (b instanceof Loper) {
                            Loper l = (Loper) b;
                            trekk = l.sjekkLovligeTrekk(ruter[i][u]);
                        } else if (b instanceof Dronning) {
                            Dronning d = (Dronning) b;
                            trekk = d.sjekkLovligeTrekk(ruter[i][u]);

                            Loper lo = new Loper(d.isHvit());
                            Taarn taa = new Taarn(d.isHvit());

                            ArrayList<Rute> rutene = lo.sjekkLovligeTrekk(ruter[i][u]);
                            ArrayList<Rute> ruteneTaarn = taa.sjekkLovligeTrekk(ruter[i][u]);

                            ArrayList<Rute> venstreOpp = new ArrayList<>();
                            ArrayList<Rute> hoyreOpp = new ArrayList<>();
                            ArrayList<Rute> venstreNed = new ArrayList<>();
                            ArrayList<Rute> hoyreNed = new ArrayList<>();

                            ArrayList<Rute> hoyre = new ArrayList<>();
                            ArrayList<Rute> venstre = new ArrayList<>();
                            ArrayList<Rute> opp = new ArrayList<>();
                            ArrayList<Rute> ned = new ArrayList<>();
                            int tellerL = rutene.size();
                            int tellerT = ruteneTaarn.size();
                            int x = i;
                            int y = u;

                            for (int a = 0; a < tellerL; a++) {
                                if (rutene.get(a).getX() < x && rutene.get(a).getY() > y) {
                                    venstreOpp.add(rutene.get(a));
                                } else if (rutene.get(a).getX() > x && rutene.get(a).getY() > y) {
                                    hoyreOpp.add(rutene.get(a));
                                } else if (rutene.get(a).getX() < x && rutene.get(a).getY() < y) {
                                    venstreNed.add(rutene.get(a));
                                } else {
                                    hoyreNed.add(rutene.get(a));
                                }
                            }
                            for (int s = 0; s < tellerT; s++) {
                                if (ruteneTaarn.get(s).getX() > x) {
                                    hoyre.add(ruteneTaarn.get(s));
                                } else if (ruteneTaarn.get(s).getX() < x) {
                                    venstre.add(ruteneTaarn.get(s));
                                } else if (ruteneTaarn.get(s).getY() > y) {
                                    opp.add(ruteneTaarn.get(s));
                                } else {
                                    ned.add(ruteneTaarn.get(s));
                                }
                            }
                            venstreT = venstre.size();
                            hoyreT = hoyre.size();
                            oppT = opp.size();
                            nedT = ned.size();
                            venstreNedT = venstreNed.size();
                            hoyreNedT = hoyreNed.size();
                            venstreOppT = venstreOpp.size();
                            hoyreOppT = hoyreOpp.size();
                        } else if (b instanceof Taarn) {
                            Taarn t = (Taarn) b;
                            trekk = t.sjekkLovligeTrekk(ruter[i][u]);
                        } else {
                            Konge k = (Konge) b;
                            trekk = k.sjekkLovligeTrekk(ruter[i][u]);
                        }
                        for (int y = 0; y < trekk.size(); y++) {
                            if (y == venstreT + hoyreOppT + venstreNedT + venstreOppT + hoyreNedT + hoyreT || y == hoyreT + hoyreOppT + venstreNedT + venstreOppT + hoyreNedT || y == oppT + hoyreOppT + venstreNedT + venstreOppT + hoyreNedT + venstreT + hoyreT || y == nedT + oppT + hoyreOppT + venstreNedT + venstreOppT + hoyreNedT + venstreT + hoyreT || y == venstreOppT + venstreNedT || y == hoyreOppT + venstreNedT + venstreOppT + hoyreNedT || y == venstreNedT || y == hoyreNedT + venstreNedT + venstreOppT) {
                                help = false;
                            }
                            Rute sjekker = ruter[trekk.get(y).getX()][trekk.get(y).getY()];
                            if (sjekker.isOccupied() && trekk.get(y).getX() != r.getX()) {
                                help = true;
                            }
                            if (trekk.get(y).getX() == r.getX() && trekk.get(y).getY() == r.getY() && !help) {
                                res = true;
                            }
                        }
                        help = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke().isHvit()) {
                        Brikke b = ruter[i][u].getBrikke();
                        if (b instanceof Bonde) {
                            Bonde bond = (Bonde) b;
                            trekk = bond.sjekkLovligeTrekk(ruter[i][u]);
                            for (int x = 0; x < trekk.size(); x++) {
                                if (trekk.get(x).getX() == ruter[i][u].getX()) {
                                    trekk.remove(x);
                                    x--;
                                }
                            }
                        } else if (b instanceof Springer) {
                            Springer s = (Springer) b;
                            trekk = s.sjekkLovligeTrekk(ruter[i][u]);
                        } else if (b instanceof Loper) {
                            Loper l = (Loper) b;
                            trekk = l.sjekkLovligeTrekk(ruter[i][u]);
                        } else if (b instanceof Dronning) {
                            Dronning d = (Dronning) b;
                            trekk = d.sjekkLovligeTrekk(ruter[i][u]);
                        } else if (b instanceof Taarn) {
                            Taarn t = (Taarn) b;
                            trekk = t.sjekkLovligeTrekk(ruter[i][u]);
                        } else {
                            Konge k = (Konge) b;
                            trekk = k.sjekkLovligeTrekk(ruter[i][u]);
                        }
                        for (int y = 0; y < trekk.size(); y++) {
                            if (trekk.get(y).isOccupied() && trekk.get(y).getX() != r.getX()) {
                                help = true;
                            }
                            if (trekk.get(y).getX() == r.getX() && trekk.get(y).getY() == r.getY() && !help) {
                                res = true;
                            }
                        }
                    }
                }
            }
        }
        return res;
    }

    public ArrayList<Rute> sjekkLovligeTrekk(Rute rute) {
        Brikke brikke = ruter[rute.getX()][rute.getY()].getBrikke();
        if (brikke instanceof Bonde) {
            Bonde bonde = (Bonde) brikke;
            int currentX = rute.getX();
            int currentY = rute.getY();
            ArrayList<Rute> lovligeTrekk = bonde.sjekkLovligeTrekk(rute);
            int teller = lovligeTrekk.size();
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].getBrikke() != null && bonde == ruter[i][u].getBrikke()) {
                    }
                    if (ruter[i][u].getBrikke() instanceof Bonde && ((Bonde) ruter[i][u].getBrikke()).getAntRunderSpilt() + 1 < antRunderSpilt && ((Bonde) ruter[i][u].getBrikke()).isUnPasant()) {
                        ((Bonde) ruter[i][u].getBrikke()).incUnPasant(false);
                    }
                }
            }
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (bonde.isHvit()) {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    } else if (currentX != x && ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (currentX != x && !ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (currentX - 1 >= 0 && currentX < 8 && currentY >= 0 && currentY < 8 && ruter[currentX - 1][currentY].isOccupied() && ruter[currentX - 1][currentY].getBrikke() instanceof Bonde) {
                        if (currentX - 1 == x && ruter[currentX - 1][currentY].isOccupied() && !ruter[currentX - 1][currentY].getBrikke().isHvit() && ((Bonde) ruter[currentX - 1][currentY].getBrikke()).isUnPasant()) {
                            lovligeTrekk.add(new Rute(x, y));
                        }
                    }
                    if (currentX + 1 >= 0 && currentX + 1 < 8 && currentY >= 0 && currentY < 8 && ruter[currentX + 1][currentY].isOccupied() && ruter[currentX + 1][currentY].getBrikke() instanceof Bonde) {
                        if (currentX + 1 == x && ruter[currentX + 1][currentY].isOccupied() && !ruter[currentX + 1][currentY].getBrikke().isHvit() && ((Bonde) ruter[currentX + 1][currentY].getBrikke()).isUnPasant()) {
                            lovligeTrekk.add(new Rute(x, y));
                        }
                    }
                } else {
                    if (currentX == x && ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    } else if (currentX != x && ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (currentX != x && !ruter[x][y].isOccupied()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (currentX - 1 >= 0 && currentX < 8 && currentY >= 0 && currentY < 8 && ruter[currentX - 1][currentY].isOccupied() && ruter[currentX - 1][currentY].getBrikke() instanceof Bonde) {
                        if (currentX - 1 == x && ruter[currentX - 1][currentY].isOccupied() && ruter[currentX - 1][currentY].getBrikke().isHvit() && ((Bonde) ruter[currentX - 1][currentY].getBrikke()).isUnPasant()) {
                            lovligeTrekk.add(new Rute(x, y));
                        }
                    }
                    if (currentX + 1 >= 0 && currentX + 1 < 8 && currentY >= 0 && currentY < 8 && ruter[currentX + 1][currentY].isOccupied() && ruter[currentX + 1][currentY].getBrikke() instanceof Bonde) {
                        if (currentX + 1 == x && ruter[currentX + 1][currentY].isOccupied() && ruter[currentX + 1][currentY].getBrikke().isHvit() && ((Bonde) ruter[currentX + 1][currentY].getBrikke()).isUnPasant()) {
                            lovligeTrekk.add(new Rute(x, y));
                        }
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
            int currenty = rute.getY();
            int currentx = rute.getX();

            ArrayList<Rute> lovligeTrekk = konge.sjekkLovligeTrekk(rute);
            int teller = lovligeTrekk.size();
            for (int i = 0; i < teller; i++) {
                int x = lovligeTrekk.get(i).getX();
                int y = lovligeTrekk.get(i).getY();
                if (konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit() && checkIfSecured(ruter[x][y])) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                } else if (!konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit() && checkIfSecured(ruter[x][y])) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                }
            }
            ArrayList<Rute> trekk = new ArrayList<>();
            boolean help = false;
            if (konge.isHvit()) {
                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (ruter[i][u].isOccupied() && !ruter[i][u].getBrikke().isHvit()) {
                            Brikke sjekken = ruter[i][u].getBrikke();
                            if (sjekken instanceof Bonde) {
                                Bonde b = (Bonde) ruter[i][u].getBrikke();
                                trekk = b.sjekkLovligeTrekk(ruter[i][u]);
                                for (int j = 0; j < trekk.size(); j++) {
                                    if (trekk.get(j).getX() == i) {
                                        trekk.remove(j);
                                        j--;
                                    }
                                }
                            } else if (!(sjekken instanceof Konge)) {
                                trekk = sjekkLovligeTrekk(ruter[i][u]);
                            }
                            for (int y = 0; y < lovligeTrekk.size(); y++) {
                                for (int w = 0; w < trekk.size(); w++) {
                                    if (y >= 0 && trekk.get(w).getX() == lovligeTrekk.get(y).getX() && trekk.get(w).getY() == lovligeTrekk.get(y).getY()) {
                                        lovligeTrekk.remove(y);
                                        y--;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke().isHvit()) {
                            if (ruter[i][u].getBrikke() instanceof Bonde) {
                                Bonde b = (Bonde) ruter[i][u].getBrikke();
                                trekk = b.sjekkLovligeTrekk(ruter[i][u]);
                                for (int j = 0; j < trekk.size(); j++) {
                                    if (trekk.get(j).getX() == i) {
                                        trekk.remove(j);
                                        j--;
                                    }
                                }
                            } else if (!(ruter[i][u].getBrikke() instanceof Konge)) {
                                trekk = sjekkLovligeTrekk(ruter[i][u]);
                            }
                            for (int y = 0; y < lovligeTrekk.size(); y++) {
                                for (int w = 0; w < trekk.size(); w++) {
                                    if (y >= 0 && trekk.get(w).getX() == lovligeTrekk.get(y).getX() && trekk.get(w).getY() == lovligeTrekk.get(y).getY()) {
                                        lovligeTrekk.remove(y);
                                        y--;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int hjelp = 0;
            if (konge.isHvit()) {
                if (ruter[4][0].getBrikke() instanceof Konge && ruter[(currentx + 3)][currenty].getBrikke() instanceof Taarn && ruter[(currentx + 3)][currenty].getBrikke().isHvit() && !rokadeKTH && (TaarnHH == 0) && (KongeH == 0)) {
                    while (!ruter[(currentx + 1)][currenty].isOccupied() && !ruter[(currentx + 2)][currenty].isOccupied() && ((currentx + 2) > 0) && (hjelp <= 0)) {
                        lovligeTrekk.add(new Rute((currentx + 2), currenty));
                        hjelp++;
                    }
                }
                hjelp = 0;
                if (ruter[4][0].getBrikke() instanceof Konge && ruter[currentx - 4][currenty].getBrikke() instanceof Taarn && ruter[currentx - 4][currenty].getBrikke().isHvit() && !rokadeKTH && (TaarnHV == 0) && (KongeH == 0)) {

                    while (!ruter[(currentx - 1)][currenty].isOccupied() && !ruter[(currentx - 2)][currenty].isOccupied() && !ruter[currentx - 3][currenty].isOccupied() && ((currentx - 2) > 0) && (hjelp <= 0)) {
                        lovligeTrekk.add(new Rute((currentx - 2), currenty));
                        hjelp++;
                    }
                }
                hjelp = 0;
            } else if (!konge.isHvit()) {
                if (ruter[4][7].getBrikke() instanceof Konge && ruter[currentx + 3][currenty].getBrikke() instanceof Taarn && !ruter[currentx + 3][currenty].getBrikke().isHvit() && !rokadeKTS && (TaarnSH == 0) && (KongeS == 0)) {
                    while (!ruter[currentx + 1][currenty].isOccupied() && !ruter[currentx + 2][currenty].isOccupied() && ((currentx + 2) > 0) && (hjelp <= 0)) {
                        lovligeTrekk.add(new Rute(currentx + 2, currenty));
                        hjelp++;
                    }
                }
                hjelp = 0;
                if (ruter[4][7].getBrikke() instanceof Konge && ruter[currentx - 4][currenty].getBrikke() instanceof Taarn && !ruter[currentx - 4][currenty].getBrikke().isHvit() && !rokadeKTS && (TaarnSV == 0) && (KongeS == 0)) {
                    if (!ruter[currentx - 1][currenty].isOccupied() && !ruter[currentx - 2][currenty].isOccupied() && !ruter[currentx - 3][currenty].isOccupied() && ((currentx - 2) > 0) && (hjelp <= 0)) {
                        lovligeTrekk.add(new Rute(currentx - 2, currenty));
                        hjelp++;
                    }
                }
                hjelp = 0;
            }
            return lovligeTrekk;
        } else if (brikke instanceof Loper) {
            Loper loper = (Loper) brikke;
            ArrayList<Rute> rutene = loper.sjekkLovligeTrekk(rute);
            //Deler opp rutene i arraylister, en arraylist for hver diagonal med lovlige trekk
            ArrayList<Rute> venstreOpp = new ArrayList<>();
            ArrayList<Rute> hoyreOpp = new ArrayList<>();
            ArrayList<Rute> venstreNed = new ArrayList<>();
            ArrayList<Rute> hoyreNed = new ArrayList<>();
            ArrayList<Rute> lovligeTrekk = new ArrayList<>();

            int teller = rutene.size();
            int x = rute.getX();
            int y = rute.getY();

            for (int i = 0; i < teller; i++) {
                if (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                    venstreOpp.add(rutene.get(i));
                } else if (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                    hoyreOpp.add(rutene.get(i));
                } else if (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                    venstreNed.add(rutene.get(i));
                } else {
                    hoyreNed.add(rutene.get(i));
                }
            }
            int venstreOppT = venstreOpp.size();
            int hoyreOppT = hoyreOpp.size();
            int venstreNedT = venstreNed.size();
            int hoyreNedT = hoyreNed.size();
            //sjekker om det står brikker på noen av arraylistene og fjerner henholdsvis de rutene som skal fjernes separat på hver diagonal
            for (int i = 0; i < venstreOppT; i++) {
                int currentX = venstreOpp.get(i).getX();
                int currentY = venstreOpp.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreOpp.remove(i);
                        venstreOppT--;
                        for (int u = i; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreOpp.remove(i);
                        venstreOppT--;
                        for (int u = i; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < hoyreOppT; i++) {
                int currentX = hoyreOpp.get(i).getX();
                int currentY = hoyreOpp.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreOpp.remove(i);
                        hoyreOppT--;
                        for (int u = i; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreOpp.remove(i);
                        hoyreOppT--;
                        for (int u = i; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < venstreNedT; i++) {
                int currentX = venstreNed.get(i).getX();
                int currentY = venstreNed.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreNed.remove(i);
                        venstreNedT--;
                        for (int u = i; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreNed.remove(i);
                        venstreNedT--;
                        for (int u = i; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < hoyreNedT; i++) {
                int currentX = hoyreNed.get(i).getX();
                int currentY = hoyreNed.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreNed.remove(i);
                        hoyreNedT--;
                        for (int u = i; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreNed.remove(i);
                        hoyreNedT--;
                        for (int u = i; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            //Legger sammen arraylistene til en arrayList som returneres
            for (int i = 0; i < venstreOppT; i++) {
                lovligeTrekk.add(venstreOpp.get(i));
            }
            for (int i = 0; i < hoyreOppT; i++) {
                lovligeTrekk.add(hoyreOpp.get(i));
            }
            for (int i = 0; i < venstreNedT; i++) {
                lovligeTrekk.add(venstreNed.get(i));
            }
            for (int i = 0; i < hoyreNedT; i++) {
                lovligeTrekk.add(hoyreNed.get(i));
            }
            return lovligeTrekk;
        } else if (brikke instanceof Dronning) {
            Dronning dronning = (Dronning) brikke;

            Loper lo = new Loper(dronning.isHvit());
            Taarn taa = new Taarn(dronning.isHvit());

            ArrayList<Rute> rutene = lo.sjekkLovligeTrekk(rute);
            ArrayList<Rute> ruteneTaarn = taa.sjekkLovligeTrekk(rute);

            ArrayList<Rute> venstreOpp = new ArrayList<>();
            ArrayList<Rute> hoyreOpp = new ArrayList<>();
            ArrayList<Rute> venstreNed = new ArrayList<>();
            ArrayList<Rute> hoyreNed = new ArrayList<>();

            ArrayList<Rute> hoyre = new ArrayList<>();
            ArrayList<Rute> venstre = new ArrayList<>();
            ArrayList<Rute> opp = new ArrayList<>();
            ArrayList<Rute> ned = new ArrayList<>();

            ArrayList<Rute> lovligeTrekk = new ArrayList<>();

            int tellerL = rutene.size();
            int tellerT = ruteneTaarn.size();
            int x = rute.getX();
            int y = rute.getY();

            for (int i = 0; i < tellerL; i++) {
                if (rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                    venstreOpp.add(rutene.get(i));
                } else if (rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                    hoyreOpp.add(rutene.get(i));
                } else if (rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                    venstreNed.add(rutene.get(i));
                } else {
                    hoyreNed.add(rutene.get(i));
                }
            }
            for (int i = 0; i < tellerT; i++) {
                if (ruteneTaarn.get(i).getX() > x) {
                    hoyre.add(ruteneTaarn.get(i));
                } else if (ruteneTaarn.get(i).getX() < x) {
                    venstre.add(ruteneTaarn.get(i));
                } else if (ruteneTaarn.get(i).getY() > y) {
                    opp.add(ruteneTaarn.get(i));
                } else {
                    ned.add(ruteneTaarn.get(i));
                }
            }
            int venstreOppT = venstreOpp.size();
            int hoyreOppT = hoyreOpp.size();
            int venstreNedT = venstreNed.size();
            int hoyreNedT = hoyreNed.size();
            //sjekker om det står brikker på noen av arraylistene og fjerner henholdsvis de rutene som skal fjernes separat på hver diagonal
            for (int i = 0; i < venstreOppT; i++) {
                int currentX = venstreOpp.get(i).getX();
                int currentY = venstreOpp.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreOpp.remove(i);
                        venstreOppT--;
                        for (int u = i; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreOpp.remove(i);
                        venstreOppT--;
                        for (int u = i; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreOppT; u++) {
                            venstreOpp.remove(u);
                            venstreOppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < hoyreOppT; i++) {
                int currentX = hoyreOpp.get(i).getX();
                int currentY = hoyreOpp.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreOpp.remove(i);
                        hoyreOppT--;
                        for (int u = i; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreOpp.remove(i);
                        hoyreOppT--;
                        for (int u = i; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreOppT; u++) {
                            hoyreOpp.remove(u);
                            hoyreOppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < venstreNedT; i++) {
                int currentX = venstreNed.get(i).getX();
                int currentY = venstreNed.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreNed.remove(i);
                        venstreNedT--;
                        for (int u = i; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstreNed.remove(i);
                        venstreNedT--;
                        for (int u = i; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreNedT; u++) {
                            venstreNed.remove(u);
                            venstreNedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < hoyreNedT; i++) {
                int currentX = hoyreNed.get(i).getX();
                int currentY = hoyreNed.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreNed.remove(i);
                        hoyreNedT--;
                        for (int u = i; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyreNed.remove(i);
                        hoyreNedT--;
                        for (int u = i; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreNedT; u++) {
                            hoyreNed.remove(u);
                            hoyreNedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            int hoyreT = hoyre.size();
            int venstreT = venstre.size();
            int oppT = opp.size();
            int nedT = ned.size();
            for (int i = 0; i < hoyreT; i++) {
                int currentX = hoyre.get(i).getX();
                int currentY = hoyre.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyre.remove(i);
                        hoyreT--;
                        for (int u = i; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyre.remove(i);
                        hoyreT--;
                        for (int u = i; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < venstreT; i++) {
                int currentX = venstre.get(i).getX();
                int currentY = venstre.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstre.remove(i);
                        venstreT--;
                        for (int u = i; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstre.remove(i);
                        venstreT--;
                        for (int u = i; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < oppT; i++) {
                int currentX = opp.get(i).getX();
                int currentY = opp.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        opp.remove(i);
                        oppT--;
                        for (int u = i; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        opp.remove(i);
                        oppT--;
                        for (int u = i; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < nedT; i++) {
                int currentX = ned.get(i).getX();
                int currentY = ned.get(i).getY();
                if (dronning.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        ned.remove(i);
                        nedT--;
                        for (int u = i; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        ned.remove(i);
                        nedT--;
                        for (int u = i; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < venstreOppT; i++) {
                lovligeTrekk.add(venstreOpp.get(i));
            }
            for (int i = 0; i < hoyreOppT; i++) {
                lovligeTrekk.add(hoyreOpp.get(i));
            }
            for (int i = 0; i < venstreNedT; i++) {
                lovligeTrekk.add(venstreNed.get(i));
            }
            for (int i = 0; i < hoyreNedT; i++) {
                lovligeTrekk.add(hoyreNed.get(i));
            }
            for (int i = 0; i < hoyre.size(); i++) {
                lovligeTrekk.add(hoyre.get(i));
            }
            for (int i = 0; i < venstre.size(); i++) {
                lovligeTrekk.add(venstre.get(i));
            }
            for (int i = 0; i < opp.size(); i++) {
                lovligeTrekk.add(opp.get(i));
            }
            for (int i = 0; i < ned.size(); i++) {
                lovligeTrekk.add(ned.get(i));
            }
            return lovligeTrekk;

        } else if (brikke instanceof Taarn) {
            Taarn taarn = (Taarn) brikke;
            ArrayList<Rute> rutene = taarn.sjekkLovligeTrekk(rute);
            ArrayList<Rute> hoyre = new ArrayList<>();
            ArrayList<Rute> venstre = new ArrayList<>();
            ArrayList<Rute> opp = new ArrayList<>();
            ArrayList<Rute> ned = new ArrayList<>();
            ArrayList<Rute> lovligeTrekk = new ArrayList<>();

            int y = rute.getY();
            int x = rute.getX();
            int teller = rutene.size();
            for (int i = 0; i < teller; i++) {
                if (rutene.get(i).getX() > x) {
                    hoyre.add(rutene.get(i));
                } else if (rutene.get(i).getX() < x) {
                    venstre.add(rutene.get(i));
                } else if (rutene.get(i).getY() > y) {
                    opp.add(rutene.get(i));
                } else {
                    ned.add(rutene.get(i));
                }
            }
            int hoyreT = hoyre.size();
            int venstreT = venstre.size();
            int oppT = opp.size();
            int nedT = ned.size();
            for (int i = 0; i < hoyreT; i++) {
                int currentX = hoyre.get(i).getX();
                int currentY = hoyre.get(i).getY();
                if (taarn.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyre.remove(i);
                        hoyreT--;
                        for (int u = i; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        hoyre.remove(i);
                        hoyreT--;
                        for (int u = i; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < hoyreT; u++) {
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < venstreT; i++) {
                int currentX = venstre.get(i).getX();
                int currentY = venstre.get(i).getY();
                if (taarn.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstre.remove(i);
                        venstreT--;
                        for (int u = i; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        venstre.remove(i);
                        venstreT--;
                        for (int u = i; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < venstreT; u++) {
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < oppT; i++) {
                int currentX = opp.get(i).getX();
                int currentY = opp.get(i).getY();
                if (taarn.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        opp.remove(i);
                        oppT--;
                        for (int u = i; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        opp.remove(i);
                        oppT--;
                        for (int u = i; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < oppT; u++) {
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < nedT; i++) {
                int currentX = ned.get(i).getX();
                int currentY = ned.get(i).getY();
                if (taarn.isHvit()) {
                    if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        ned.remove(i);
                        nedT--;
                        for (int u = i; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                } else {
                    if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()) {
                        ned.remove(i);
                        nedT--;
                        for (int u = i; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    } else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()) {
                        for (int u = i + 1; u < nedT; u++) {
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for (int i = 0; i < hoyre.size(); i++) {
                lovligeTrekk.add(hoyre.get(i));
            }
            for (int i = 0; i < venstre.size(); i++) {
                lovligeTrekk.add(venstre.get(i));
            }
            for (int i = 0; i < opp.size(); i++) {
                lovligeTrekk.add(opp.get(i));
            }
            for (int i = 0; i < ned.size(); i++) {
                lovligeTrekk.add(ned.get(i));
            }

            return lovligeTrekk;
        }
        return null;
    }

    private void removePiece(Rute r) {
        if (r.isOccupied() && r.getBrikke().isHvit()) {
            hvit.removePiece(r.getBrikke());
        } else if (r.isOccupied() && !r.getBrikke().isHvit()) {
            svart.removePiece(r.getBrikke());
        }
    }

    public void flyttBrikke(Rute flyttRute, Rute startRute, Boolean whiteTurn) {
        rokadeHH = false;
        rokadeHV = false;
        rokadeSV = false;
        rokadeSH = false;
        int fY = flyttRute.getX();
        int fX = flyttRute.getY();
        int sY = startRute.getX();
        int sX = startRute.getY();
        synchronized (this) {
        if (this.ruter[sX][sY].getBrikke() instanceof Konge && this.ruter[sX][sY].getBrikke().isHvit() && (rokadeKTH == false)) {
            if (((fX - sX) == 2) && (TaarnHH == 0) && (KongeH == 0)) {
                this.ruter[(sX + 1)][sY].setBrikke(ruter[(sX + 3)][sY].getBrikke());
                this.ruter[(sX + 3)][sY].setBrikke(null);
                this.removePiece(new Rute((sX + 3), sY));
                rokadeHH = true;
                rokadeKTH = true;


            } else if (((sX - fX) == 2) && (TaarnHV == 0) && (KongeH == 0)) {
                this.ruter[(sX - 1)][sY].setBrikke(ruter[(sX - 4)][sY].getBrikke());
                this.ruter[(sX - 4)][sY].setBrikke(null);
                this.removePiece(new Rute((sX - 4), sY));
                rokadeHV = true;
                rokadeKTH = true;
            }
        } else if (this.ruter[sX][sY].getBrikke() instanceof Konge && !this.ruter[sX][sY].getBrikke().isHvit() && (rokadeKTS == false)) {
            if (((fX - sX) == 2) && (TaarnSH == 0) && (KongeS == 0)) {
                this.ruter[(sX + 1)][sY].setBrikke(ruter[(sX + 3)][sY].getBrikke());
                this.ruter[(sX + 3)][sY].setBrikke(null);
                this.removePiece(new Rute((sX + 3), sY));
                rokadeSH = true;
                rokadeKTS = true;
            } else if (((sX - fX) == 2) && (TaarnSV == 0) && (KongeS == 0)) {
                this.ruter[(sX - 1)][sY].setBrikke(ruter[(sX - 4)][sY].getBrikke());
                this.ruter[(sX - 4)][sY].setBrikke(null);
                this.removePiece(new Rute((sX - 4), sY));
                rokadeSV = true;
                rokadeKTS = true;
            }
        }
        }
        Brikke brikken = this.ruter[sX][sY].getBrikke();
        if (this.ruter[fX][fY].isOccupied()) {
            this.removePiece(ruter[fX][fY]);
        } else if (brikken instanceof Bonde && sX != fX && this.ruter[fX][fY - 1].isOccupied() && this.ruter[fX][fY - 1].getBrikke() instanceof Bonde && ((Bonde) this.ruter[fX][fY - 1].getBrikke()).isUnPasant()) {
            this.removePiece(ruter[fX][fY - 1]);
            this.ruter[fX][fY - 1].setBrikke(null);
        } else if (brikken instanceof Bonde && (sX != fX && this.ruter[fX][fY + 1].isOccupied() && this.ruter[fX][fY + 1].getBrikke() instanceof Bonde && ((Bonde) this.ruter[fX][fY + 1].getBrikke()).isUnPasant())) {
            this.removePiece(ruter[fX][fY + 1]);
            this.ruter[fX][fY - 1].setBrikke(null);
        }
        if (brikken instanceof Bonde) {
            Bonde bonden = (Bonde) brikken;
            if (bonden.isHvit() && sY == 1 && fY == 3) {
                bonden.incUnPasant(true);
                bonden.setAntRunderSpilt(antRunderSpilt);
            } else if (!bonden.isHvit() && sY == 6 && fY == 4) {
                bonden.incUnPasant(true);
                bonden.setAntRunderSpilt(antRunderSpilt);
            }
            brikken = (Brikke) bonden;
        }
        this.ruter[fX][fY].setBrikke(brikken);
        this.ruter[sX][sY].setBrikke(null);
        antRunderSpilt++;
        
        if(brikken instanceof Konge){
            Konge Kongen = (Konge) brikken;
            if(Kongen.isHvit()){
                KongeH++;
            } else if (!Kongen.isHvit()){
                KongeS++;
            }
        }
        if(brikken instanceof Taarn){
            Taarn Taarnet = (Taarn) brikken;
            if(Taarnet.isHvit()){
                if(this.ruter[sX][sY].getBrikke() == this.ruter[7][0].getBrikke()){ //HH
                    TaarnHH++;
                } else if (this.ruter[sX][sY].getBrikke() == this.ruter[0][0].getBrikke()){ //HV
                    TaarnHV++;
                }
                
            } else if(!Taarnet.isHvit()){
                if(this.ruter[sX][sY].getBrikke() == this.ruter[7][7].getBrikke()){//SH
                    TaarnSH++;
                } else if (this.ruter[sX][sY].getBrikke() == this.ruter[0][7].getBrikke()){ // SV
                    TaarnSV++;
                }
            }
        }

    }

    public boolean update(String e) {
        if (e.equalsIgnoreCase("HV")) {
            return rokadeHV;
        }
        else if (e.equalsIgnoreCase("HH")) {
            return rokadeHH;
        }
        else if (e.equalsIgnoreCase("SV")) {
            return rokadeSV;
        }
        else if (e.equalsIgnoreCase("SH")) {
            return rokadeSH;
        }

        return false;
    }

    public ArrayList<Rute> whatPiecesBlockCheck(boolean isWhite) {
        ArrayList<Rute> res = new ArrayList<>();
        ArrayList<Rute> brikkene = new ArrayList<>();
        if (isWhite) {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke().isHvit()) {
                        brikkene.add(ruter[i][u]);
                    }
                }
            }
            for (int i = 0; i < brikkene.size(); i++) {
                ArrayList<Rute> sjekk = sjakkTrekk(isWhite, brikkene.get(i));
                if (sjekk.size() > 0) {
                    res.add(brikkene.get(i));
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && !ruter[i][u].getBrikke().isHvit()) {
                        brikkene.add(ruter[i][u]);
                    }
                }
            }
            for (int i = 0; i < brikkene.size(); i++) {
                ArrayList<Rute> sjekk = sjakkTrekk(!isWhite, brikkene.get(i));
                if (sjekk.size() > 0) {
                    res.add(brikkene.get(i));
                }
            }
        }
        return res;
    }

    public ArrayList<Rute> sjakkTrekk(boolean isWhite, Rute r) {
        Brikke b = ruter[r.getX()][r.getY()].getBrikke();
        isWhite = b.isHvit();
        Rute kongePos = null;
        ArrayList<Rute> trekk = new ArrayList<>();
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        ArrayList<Rute> trekkKonge = new ArrayList<>();
        ArrayList<Rute> discardedKonge = new ArrayList<>();
        if (isWhite) {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        trekkKonge = ruter[kI][kU].getBrikke().sjekkLovligeTrekk(ruter[kI][kU]);
                        kongePos = ruter[kI][kU];
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && !ruter[i][u].getBrikke().isHvit()) {
                        trekk = sjekkLovligeTrekk(ruter[i][u]);
                        for (int y = 0; y < trekkKonge.size(); y++) {
                            for (int w = 0; w < trekk.size(); w++) {
                                if (y >= 0 && trekk.get(w).getX() == trekkKonge.get(y).getX() && trekk.get(w).getY() == trekkKonge.get(y).getY()) {
                                    discardedKonge.add(trekkKonge.get(y));
                                    trekkKonge.remove(y);
                                    y--;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && !ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        trekkKonge = ruter[kI][kU].getBrikke().sjekkLovligeTrekk(ruter[kI][kU]);
                        kongePos = ruter[kI][kU];
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke().isHvit()) {
                        trekk = sjekkLovligeTrekk(ruter[i][u]);
                        for (int y = 0; y < trekkKonge.size(); y++) {
                            for (int w = 0; w < trekk.size(); w++) {
                                if (y >= 0 && trekk.get(w).getX() == trekkKonge.get(y).getX() && trekk.get(w).getY() == trekkKonge.get(y).getY()) {
                                    discardedKonge.add(trekkKonge.get(y));
                                    trekkKonge.remove(y);
                                    y--;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (b instanceof Konge) {
            for (int i = 0; i < trekkKonge.size(); i++) {
                lovligeTrekk.add(trekkKonge.get(i));
            }
            return lovligeTrekk;
        }
        boolean venstre = false;
        boolean hoyre = false;
        boolean opp = false;
        boolean ned = false;
        boolean oppVenstre = false;
        boolean oppHoyre = false;
        boolean nedVenstre = false;
        boolean nedHoyre = false;
        ArrayList<Rute> linjeAnalyse = new ArrayList<>();

        linjeAnalyse.add(kongePos);
        for (int i = 0; i < discardedKonge.size(); i++) {
            linjeAnalyse.add(discardedKonge.get(i));
        }
        for (int i = 0; i < linjeAnalyse.size(); i++) {
            if (linjeAnalyse.get(i).getX() < kongePos.getX() && linjeAnalyse.get(i).getY() == kongePos.getY()) {
                venstre = true;
            } else if (linjeAnalyse.get(i).getX() > kongePos.getX() && linjeAnalyse.get(i).getY() == kongePos.getY()) {
                hoyre = true;
            } else if (linjeAnalyse.get(i).getX() == kongePos.getX() && linjeAnalyse.get(i).getY() > kongePos.getY()) {
                opp = true;
            } else if (linjeAnalyse.get(i).getX() == kongePos.getX() && linjeAnalyse.get(i).getY() < kongePos.getY()) {
                ned = true;
            } else if (linjeAnalyse.get(i).getX() < kongePos.getX() && linjeAnalyse.get(i).getY() > kongePos.getY()) {
                oppVenstre = true;
            } else if (linjeAnalyse.get(i).getX() > kongePos.getX() && linjeAnalyse.get(i).getY() > kongePos.getY()) {
                oppHoyre = true;
            } else if (linjeAnalyse.get(i).getX() < kongePos.getX() && linjeAnalyse.get(i).getY() < kongePos.getY()) {
                nedVenstre = true;
            } else if (linjeAnalyse.get(i).getX() > kongePos.getX() && linjeAnalyse.get(i).getY() < kongePos.getY()) {
                nedHoyre = true;
            }
        }

        ArrayList<Rute> trekkBonde = new ArrayList<>();
        trekkBonde = sjekkLovligeTrekk(r);
        if (trekkBonde != null) {
            for (int i = 0; i < trekkBonde.size(); i++) {
                if (venstre) {
                    if (trekkBonde.get(i).getY() == kongePos.getY() && trekkBonde.get(i).getX() < kongePos.getX()) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (hoyre) {
                    if (trekkBonde.get(i).getY() == kongePos.getY() && trekkBonde.get(i).getX() > kongePos.getX()) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (opp) {
                    if (trekkBonde.get(i).getX() == kongePos.getX() && trekkBonde.get(i).getY() > kongePos.getY()) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (ned) {
                    if (trekkBonde.get(i).getX() == kongePos.getX() && trekkBonde.get(i).getY() < kongePos.getY()) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (oppVenstre) {
                    if ((kongePos.getX() - trekkBonde.get(i).getX()) == trekkBonde.get(i).getY() - kongePos.getY()) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (oppHoyre) {
                    if ((trekkBonde.get(i).getX() - kongePos.getX()) == (trekkBonde.get(i).getY() - kongePos.getY())) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (nedVenstre) {
                    if ((kongePos.getX() - trekkBonde.get(i).getX()) == (kongePos.getY() - trekkBonde.get(i).getY())) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                } else if (nedHoyre) {
                    if ((trekkBonde.get(i).getX() - kongePos.getX() == (kongePos.getY() - trekkBonde.get(i).getY()))) {
                        lovligeTrekk.add(trekkBonde.get(i));
                    }
                }
            }
        }
        return lovligeTrekk;
    }

    public boolean isSjakk(Boolean isWhite) {
        ArrayList<Rute> trekk = new ArrayList<>();
        Rute konge = null;

        if (isWhite) {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        konge = new Rute(kI, kU);
                    }
                }
            }
            for (int u = 0; u < 8; u++) {
                for (int v = 0; v < 8; v++) {
                    if (ruter[u][v].isOccupied() && !ruter[u][v].getBrikke().isHvit()) {
                        trekk = sjekkLovligeTrekk(ruter[u][v]);
                        for (int t = 0; t < trekk.size(); t++) {
                            if (trekk.get(t).getX() == konge.getX() && trekk.get(t).getY() == konge.getY()) {
                                return true;
                            }
                        }
                    }
                }
            }
        } else {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && !ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        konge = new Rute(kI, kU);
                    }
                }
            }
            for (int i = 0; i < bonderS.size(); i++) {
                for (int u = 0; u < 8; u++) {
                    for (int v = 0; v < 8; v++) {
                        if (ruter[u][v].isOccupied() && ruter[u][v].getBrikke().isHvit()) {
                            trekk = sjekkLovligeTrekk(ruter[u][v]);
                            for (int t = 0; t < trekk.size(); t++) {
                                if (trekk.get(t).getX() == konge.getX() && trekk.get(t).getY() == konge.getY()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Rute> blockingCheckMoves(boolean whiteTurn, Rute r) {
        ArrayList<Rute> lovligeTrekk = new ArrayList<>();
        ArrayList<Rute> sjekkTrekk = new ArrayList<>();
        Rute kongePos = null;
        Brikke blocker = ruter[r.getX()][r.getY()].getBrikke();

        if (whiteTurn) {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke().isHvit() && ruter[i][u].getBrikke() instanceof Konge) {
                        kongePos = ruter[i][u];
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (ruter[i][u].isOccupied() && !ruter[i][u].getBrikke().isHvit() && ruter[i][u].getBrikke() instanceof Konge) {
                        kongePos = ruter[i][u];
                    }
                }
            }
        }
        Dronning d = new Dronning(true);
        sjekkTrekk = d.sjekkLovligeTrekk(kongePos);
        ArrayList<Rute> venstreOpp = new ArrayList<>();
        ArrayList<Rute> hoyreOpp = new ArrayList<>();
        ArrayList<Rute> venstreNed = new ArrayList<>();
        ArrayList<Rute> hoyreNed = new ArrayList<>();

        ArrayList<Rute> hoyre = new ArrayList<>();
        ArrayList<Rute> venstre = new ArrayList<>();
        ArrayList<Rute> opp = new ArrayList<>();
        ArrayList<Rute> ned = new ArrayList<>();
        int teller = sjekkTrekk.size();
        int x = kongePos.getX();
        int y = kongePos.getY();
        for (int i = 0; i < teller; i++) {
            if (sjekkTrekk.get(i).getX() < x && sjekkTrekk.get(i).getY() > y) {
                venstreOpp.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getX() > x && sjekkTrekk.get(i).getY() > y) {
                hoyreOpp.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getX() < x && sjekkTrekk.get(i).getY() < y) {
                venstreNed.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getX() > x && sjekkTrekk.get(i).getY() < y) {
                hoyreNed.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getX() > x && sjekkTrekk.get(i).getY() == y) {
                hoyre.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getX() < x && sjekkTrekk.get(i).getY() == y) {
                venstre.add(sjekkTrekk.get(i));
            } else if (sjekkTrekk.get(i).getY() > y && sjekkTrekk.get(i).getX() == x) {
                opp.add(sjekkTrekk.get(i));
            } else {
                ned.add(sjekkTrekk.get(i));
            }
        }
        int tellerV = venstre.size();
        int tellerH = hoyre.size();
        int tellerO = opp.size();
        int tellerN = ned.size();
        int tellerVO = venstreOpp.size();
        int tellerHO = hoyreOpp.size();
        int tellerVN = venstreNed.size();
        int tellerHN = hoyreNed.size();
        Rute ru = null;
        if (whiteTurn) {
            for (int i = 0; i < tellerV; i++) {
                ru = ruter[venstre.get(i).getX()][venstre.get(i).getY()];
                if (ru != null && (ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn))) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(venstre.get(i));
                        int tx = r.getX();
                        while (ru.getX() - tx < 0) {
                            tx--;
                            lovligeTrekk.add(new Rute(tx, ru.getY()));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerH; i++) {
                ru = ruter[hoyre.get(i).getX()][hoyre.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(hoyre.get(i));
                        int tx = r.getX();
                        while (tx - ru.getX() < 0) {
                            tx++;
                            lovligeTrekk.add(new Rute(tx, ru.getY()));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerO; i++) {
                ru = ruter[opp.get(i).getX()][opp.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(hoyre.get(i));
                        int ty = r.getX();
                        while (ty - ru.getY() < 0) {
                            ty++;
                            lovligeTrekk.add(new Rute(ru.getX(), ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerN; i++) {
                ru = ruter[ned.get(i).getX()][ned.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(ned.get(i));
                        int ty = r.getY();
                        while (ru.getY() - ty < 0) {
                            ty--;
                            lovligeTrekk.add(new Rute(ru.getX(), ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerVO; i++) {
                ru = ruter[venstreOpp.get(i).getX()][venstreOpp.get(i).getY()];
                if ((ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper))) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(venstreOpp.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((ru.getX() - tx) < 0 && (ty - ru.getY()) < 0) {
                            tx--;
                            ty++;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                    if (blocker instanceof Bonde) {
                        if (ruter[r.getX() + 1][r.getY() + 1].isOccupied() && !ruter[r.getX() + 1][r.getY() + 1].getBrikke().isHvit()) {
                            lovligeTrekk.add(new Rute(r.getX() + 1, r.getY() + 1));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerHO; i++) {
                ru = ruter[hoyreOpp.get(i).getX()][hoyreOpp.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(hoyreOpp.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((tx - hoyreOpp.get(i).getX()) < 0 && (ty - hoyreOpp.get(i).getY()) < 0) {
                            tx++;
                            ty++;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    } else if (blocker instanceof Bonde) {
                        if (ruter[r.getX() + 1][r.getY() + 1].isOccupied() && !ruter[r.getX() + 1][r.getY() + 1].getBrikke().isHvit()) {
                            lovligeTrekk.add(new Rute(r.getX() + 1, r.getY() + 1));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerVN; i++) {
                ru = ruter[venstreNed.get(i).getX()][venstreNed.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(venstreNed.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while (tx - ru.getX() < 0 && ru.getY() - ty < 0) {
                            tx--;
                            ty--;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerHN; i++) {
                ru = ruter[hoyreNed.get(i).getX()][hoyreNed.get(i).getY()];
                if (ru != null && ru.isOccupied() && !ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(hoyreNed.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((tx - ru.getX()) < 0 && (ru.getY() - ty) < 0) {
                            tx++;
                            ty--;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < tellerV; i++) {
                ru = ruter[venstre.get(i).getX()][venstre.get(i).getY()];
                if (ru != null && (ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn))) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(venstre.get(i));
                        int tx = r.getX();
                        while (ru.getX() - tx < 0) {
                            tx--;
                            lovligeTrekk.add(new Rute(tx, ru.getY()));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerH; i++) {
                ru = ruter[hoyre.get(i).getX()][hoyre.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(hoyre.get(i));
                        int tx = r.getX();
                        while (tx - ru.getX() < 0) {
                            tx++;
                            lovligeTrekk.add(new Rute(tx, ru.getY()));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerO; i++) {
                ru = ruter[opp.get(i).getX()][opp.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(hoyre.get(i));
                        int ty = r.getX();
                        while (ty - ru.getY() < 0) {
                            ty++;
                            lovligeTrekk.add(new Rute(ru.getX(), ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerN; i++) {
                ru = ruter[ned.get(i).getX()][ned.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Taarn)) {
                    if (blocker instanceof Dronning || blocker instanceof Taarn) {
                        lovligeTrekk.add(ned.get(i));
                        int ty = r.getY();
                        while (ru.getY() - ty < 0) {
                            ty--;
                            lovligeTrekk.add(new Rute(ru.getX(), ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerVO; i++) {
                ru = ruter[venstreOpp.get(i).getX()][venstreOpp.get(i).getY()];
                if ((ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper))) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(venstreOpp.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((ru.getX() - tx) < 0 && (ty - ru.getY()) < 0) {
                            tx--;
                            ty++;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                    if (blocker instanceof Bonde) {
                        if (ruter[r.getX() + 1][r.getY() + 1].isOccupied() && !ruter[r.getX() + 1][r.getY() + 1].getBrikke().isHvit()) {
                            lovligeTrekk.add(new Rute(r.getX() + 1, r.getY() + 1));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerHO; i++) {
                ru = ruter[hoyreOpp.get(i).getX()][hoyreOpp.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(hoyreOpp.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((tx - hoyreOpp.get(i).getX()) < 0 && (ty - hoyreOpp.get(i).getY()) < 0) {
                            tx++;
                            ty++;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    } else if (blocker instanceof Bonde) {
                        if (ruter[r.getX() + 1][r.getY() + 1].isOccupied() && !ruter[r.getX() + 1][r.getY() + 1].getBrikke().isHvit()) {
                            lovligeTrekk.add(new Rute(r.getX() + 1, r.getY() + 1));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerVN; i++) {
                ru = ruter[venstreNed.get(i).getX()][venstreNed.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(venstreNed.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while (tx - ru.getX() < 0 && ru.getY() - ty < 0) {
                            tx--;
                            ty--;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                }
            }
            for (int i = 0; i < tellerHN; i++) {
                ru = ruter[hoyreNed.get(i).getX()][hoyreNed.get(i).getY()];
                if (ru != null && ru.isOccupied() && ru.getBrikke().isHvit() && (ru.getBrikke() instanceof Dronning || ru.getBrikke() instanceof Loper)) {
                    if (blocker instanceof Dronning || blocker instanceof Loper) {
                        lovligeTrekk.add(hoyreNed.get(i));
                        int tx = r.getX();
                        int ty = r.getY();
                        while ((tx - ru.getX()) < 0 && (ru.getY() - ty) < 0) {
                            tx++;
                            ty--;
                            lovligeTrekk.add(new Rute(tx, ty));
                        }
                    }
                }
            }
        }
        return lovligeTrekk;
    }

    public boolean checkIfBlockingCheck(boolean isWhite) {
        ArrayList<Rute> trekk = new ArrayList<>();
        boolean help = false;
        Rute konge = null;
        Brikke b = null;
        if (isWhite) {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        konge = new Rute(kI, kU);
                    }
                }
            }
            for (int u = 0; u < 8; u++) {
                for (int v = 0; v < 8; v++) {
                    if (ruter[u][v].isOccupied() && !ruter[u][v].getBrikke().isHvit()) {
                        b = ruter[u][v].getBrikke();
                        if (b instanceof Bonde) {
                            Bonde test = (Bonde) b;
                            trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                            for (int d = 0; d < trekk.size(); d++) {
                                if (trekk.get(d).getX() == u) {
                                    trekk.remove(d);
                                }
                            }
                        } else if (b instanceof Loper) {
                            Loper test = (Loper) b;
                            trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                        } else if (b instanceof Taarn) {
                            Taarn test = (Taarn) b;
                            trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                        } else if (b instanceof Dronning) {
                            Dronning test = (Dronning) b;
                            trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                        }
                        for (int t = 0; t < trekk.size(); t++) {
                            if (trekk.get(t).getX() == konge.getX() && trekk.get(t).getY() == konge.getY() && !help) {
                                return true;
                            }
                        }
                        help = false;
                    }
                }
            }
        } else {
            for (int kI = 0; kI < 8; kI++) {
                for (int kU = 0; kU < 8; kU++) {
                    if (ruter[kI][kU].isOccupied() && !ruter[kI][kU].getBrikke().isHvit() && ruter[kI][kU].getBrikke() instanceof Konge) {
                        konge = new Rute(kI, kU);
                    }
                }
            }
            for (int i = 0; i < bonderS.size(); i++) {
                for (int u = 0; u < 8; u++) {
                    for (int v = 0; v < 8; v++) {
                        if (ruter[u][v].isOccupied() && ruter[u][v].getBrikke().isHvit()) {
                            b = ruter[u][v].getBrikke();
                            if (b instanceof Bonde) {
                                Bonde test = (Bonde) b;
                                trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                                for (int d = 0; d < trekk.size(); d++) {
                                    if (trekk.get(d).getX() == u) {
                                        trekk.remove(d);
                                    }
                                }
                            } else if (b instanceof Loper) {
                                Loper test = (Loper) b;
                                trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                            } else if (b instanceof Taarn) {
                                Taarn test = (Taarn) b;
                                trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                            } else if (b instanceof Dronning) {
                                Dronning test = (Dronning) b;
                                trekk = test.sjekkLovligeTrekk(ruter[u][v]);
                            }
                            for (int t = 0; t < trekk.size(); t++) {
                                if (trekk.get(t).getX() == konge.getX() && trekk.get(t).getY() == konge.getY() && !help) {
                                    return true;
                                }
                            }
                            help = false;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isSjakkMatt(boolean whiteTurn,boolean isSjakk) {
        ArrayList<Rute> muligeMoves = whatPiecesBlockCheck(whiteTurn);
        ArrayList<Rute> legal = new ArrayList<>();
        if (muligeMoves.size()<=1&&isSjakk) {
            if (whiteTurn) {
                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke() instanceof Konge && ruter[i][u].getBrikke().isHvit()) {
                            legal = sjekkLovligeTrekk(ruter[i][u]);
                            if(legal.size()==0){
                                return true;
                            }
                        }
                    }
                }
            }else {
                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (ruter[i][u].isOccupied() && ruter[i][u].getBrikke() instanceof Konge && !ruter[i][u].getBrikke().isHvit()) {
                            legal = sjekkLovligeTrekk(ruter[i][u]);
                            if(legal.size()==0){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void promotePiece(Rute r, Brikke b) {
        ruter[r.getX()][r.getY()].setBrikke(b);
    }
}
