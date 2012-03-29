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

public class Brett implements Serializable{

    private Rute[][] ruter;
    private Hvit hvit;
    private Svart svart;
    private ArrayList<Brikke> bonderH;
    private ArrayList<Brikke> bonderS;

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
                } else if (!bonde.isHvit()) {
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
                        i--;
                    }
                } else if (!konge.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        lovligeTrekk.remove(i);
                        teller--;
                        i--;
                    }
                }
            }
            return lovligeTrekk;
        } else if (brikke instanceof Loper) {
            Loper loper = (Loper) brikke;
            ArrayList<Rute> rutene = loper.sjekkLovligeTrekk(rute);
            int teller = rutene.size();
            int currentX = rute.getX();
            int currentY = rute.getY();
            for (int i = 0; i < teller; i++) {
                int x = rutene.get(i).getX();
                int y = rutene.get(i).getY();
                if (loper.isHvit()) {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                        if (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            while (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                                System.out.println("Venstre/opp");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            while (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                                System.out.println("venste/ned");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            while (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                                System.out.println("Høyre/opp");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            while (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                                System.out.println("Høyre/ned");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                    } else if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                        rutene.remove(i);
                        teller--;
                        if (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                            while (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() > y) {
                                System.out.println("Venstre/opp");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                            while (teller > 0 && rutene.get(i).getX() < x && rutene.get(i).getY() < y) {
                                System.out.println("venste/ned");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                            while (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() > y) {
                                System.out.println("Høyre/opp");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        if (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                            while (teller > 0 && rutene.get(i).getX() > x && rutene.get(i).getY() < y) {
                                System.out.println("Høyre/ned");
                                rutene.remove(i);
                                teller--;
                            }
                        }
                        i--;
                    }
                    System.out.println("I: " + i);
                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
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
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                    }
                } else {
                    if (ruter[x][y].isOccupied() && !ruter[x][y].getBrikke().isHvit()) {
                    }
                    if (ruter[x][y].isOccupied() && ruter[x][y].getBrikke().isHvit()) {
                    }
                }
                return rutene;
            }

        }else if (brikke instanceof Taarn) {
            Taarn taarn = (Taarn) brikke;
            ArrayList<Rute> rutene = taarn.sjekkLovligeTrekk(rute);
            ArrayList<Rute> hoyre = new ArrayList<>();
            ArrayList<Rute> venstre = new ArrayList<>();
            ArrayList<Rute> opp = new ArrayList<>();
            ArrayList<Rute> ned = new ArrayList<>();
            ArrayList<Rute> lovligeTrekk = new ArrayList<>();
            
            int y=rute.getY();
            int x = rute.getX();
            int teller = rutene.size();
            for (int i = 0; i < teller; i++) {
                if(rutene.get(i).getX()>x){
                    hoyre.add(rutene.get(i));
                }else if(rutene.get(i).getX() < x){
                    venstre.add(rutene.get(i));
                }else if(rutene.get(i).getY()> y){
                    opp.add(rutene.get(i));
                }else {
                    ned.add(rutene.get(i));
                }
            }
            int hoyreT = hoyre.size();
            int venstreT = venstre.size();
            int oppT = opp.size();
            int nedT = ned.size();
            for(int i = 0; i < hoyreT; i++){
                int currentX = hoyre.get(i).getX();
                int currentY = hoyre.get(i).getY();
                if(taarn.isHvit()){
                    if(ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        hoyre.remove(i);
                        hoyreT--;
                        for(int u = i; u<hoyreT; u++){
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < hoyreT; u++){
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                }else{
                    if(ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        hoyre.remove(i);
                        hoyreT--;
                        for(int u = i; u<hoyreT; u++){
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < hoyreT; u++){
                            hoyre.remove(u);
                            hoyreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }for(int i = 0; i < venstreT; i++){
                int currentX = venstre.get(i).getX();
                int currentY = venstre.get(i).getY();
                if(taarn.isHvit()){
                    if(ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        venstre.remove(i);
                        venstreT--;
                        for(int u = i; u<venstreT; u++){
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < venstreT; u++){
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                }else{
                    if(ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        venstre.remove(i);
                        venstreT--;
                        for(int u = i; u<venstreT; u++){
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < venstreT; u++){
                            venstre.remove(u);
                            venstreT--;
                            u--;
                            i++;
                        }
                    }
                }
            }for(int i = 0; i < oppT; i++){
                int currentX = opp.get(i).getX();
                int currentY = opp.get(i).getY();
                if(taarn.isHvit()){
                    if(ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        opp.remove(i);
                        oppT--;
                        for(int u = i; u<oppT; u++){
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < oppT; u++){
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                }else{
                    if(ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        opp.remove(i);
                        oppT--;
                        for(int u = i; u<oppT; u++){
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < oppT; u++){
                            opp.remove(u);
                            oppT--;
                            u--;
                            i++;
                        }
                    }
                }
            }for(int i = 0; i < nedT; i++){
                int currentX = ned.get(i).getX();
                int currentY = ned.get(i).getY();
                if(taarn.isHvit()){
                    if(ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        ned.remove(i);
                        nedT--;
                        for(int u = i; u<nedT; u++){
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                    else if (ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < nedT; u++){
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                }else{
                    if(ruter[currentX][currentY].isOccupied() && !ruter[currentX][currentY].getBrikke().isHvit()){
                        ned.remove(i);
                        nedT--;
                        for(int u = i; u<nedT; u++){
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }else if (ruter[currentX][currentY].isOccupied() && ruter[currentX][currentY].getBrikke().isHvit()){
                        for(int u = i+1; u < nedT; u++){
                            ned.remove(u);
                            nedT--;
                            u--;
                            i++;
                        }
                    }
                }
            }
            for(int i = 0; i < hoyre.size();i++){
                lovligeTrekk.add(hoyre.get(i));
            }
            for(int i = 0; i < venstre.size();i++){
                lovligeTrekk.add(venstre.get(i));
            }for(int i = 0; i < opp.size();i++ ){
                lovligeTrekk.add(opp.get(i));
            }for(int i = 0; i < ned.size(); i++){
                lovligeTrekk.add(ned.get(i));
            }
            
            return lovligeTrekk;
        }
        return null;
    }

    private void removePiece(Rute r) {
        if (r.isOccupied()) {
            if (hvit.removePiece(r.getBrikke())) {
                System.out.println(" lol");
            }
        }
    }

    public void flyttBrikke(Rute flyttRute, Rute startRute) {
        int fY = flyttRute.getX();
        int fX = flyttRute.getY();
        int sY = startRute.getX();
        int sX = startRute.getY();
        if (this.ruter[fX][fY].isOccupied()) {
            removePiece(this.ruter[fY][fX]);
        }
        this.ruter[fX][fY].setBrikke(ruter[sX][sY].getBrikke());
        this.ruter[sX][sY].setBrikke(null);
    }

    public void registrateMove(String move, String move2, boolean s) {

        if (s) {
            String completeMoveS = ("Svart spiller: " + move + " til " + move2);
            svart.setMoves(completeMoveS);
        } else {
            String completeMoveH = ("Hvit spiller: " + move + " til " + move2);
            //hvit.setMoves(completeMoveH);
        }
    }

    public boolean isSjakk(Boolean isWhite) {
        ArrayList<Rute> trekk = new ArrayList<>();
        Rute current = null;
        Rute konge = null;
        if (isWhite) {
            for (int i = 0; i < bonderS.size(); i++) {
                Brikke b = bonderS.get(i);
                for (int u = 0; u < 8; u++) {
                    for (int v = 0; v < 8; v++) {
                        if (ruter[u][v].getBrikke() != null && ruter[u][v].getBrikke().equals(b)) {
                            current = new Rute(u, v);
                            System.out.println("X: " + u + " Y: " + v);
                        }
                        if (ruter[u][v].getBrikke() != null && ruter[u][v].getBrikke().isHvit() && ruter[u][v].getBrikke() instanceof Konge) {
                            konge = new Rute(u, v);
                            System.out.println("fant Kongen X: " + u + " Y: " + v);
                        }
                    }
                }
                trekk = bonderS.get(i).sjekkLovligeTrekk(current);
                for (int t = 0; t < trekk.size(); t++) {
                    if (trekk.get(t).getX() == konge.getX() && trekk.get(t).getY() == konge.getY()) {
                        System.out.println("Sjakk!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
