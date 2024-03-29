package Sjakk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import static javax.swing.JOptionPane.*;
import javax.swing.*;

/**
 *
 * @author Team 02, AITeL@HiST
 *
 * Klassen GUI håndterer all kommunikasjon med brukeren. Klassen kommuniserer
 * kun med klassen Brett, som igjen tar seg av det logiske i selve brettet.
 *
 */
class Gui extends JFrame {

    private final Brett brett = new Brett();
    private GuiRute squares[][] = new GuiRute[8][8];
    private ArrayList<Rute> lovligeTrekk;
    private final String[] trekk = {"a", "b", "c", "d", "e", "f", "g", "h"};
    private String move;
    private String move2;
    private final Color brown = new Color(160, 82, 45);
    private final Color lightBrown = new Color(244, 164, 96);
    private final Color highlighted = new Color(195, 205, 205);
    private final Color highlightedTrekk = new Color(191, 239, 255);
    private boolean isHighlighted = false;
    private boolean isSjakk = false;
    private Rutenett rutenett;
    private GameInfo gameInfo;
    private static Gui b;
    private boolean blackTurn = false;
    private boolean isStarted = false;
    private int teller1 = 0;
    private int teller2 = 0;
    private int teller3 = 0;
    private int teller4 = 0;
    private double tid;
    private boolean isDone = true;

    /**
     * Konstruktør med èn parameter. Her opprettes alt som skal ses av brukeren.
     * Det inkluderer selve rammen til spillet, i tillegg til hver enkelt del av
     * spillet, for eksempel rutenettet med de forskjellige bildene.
     *
     * @param tittel Tittelen på spillet.
     */
    public Gui(String tittel) {
        super(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        setLayout(new BorderLayout());
        rutenett = new Rutenett();
        add(rutenett, BorderLayout.CENTER);
        gameInfo = new GameInfo();
        add(gameInfo, BorderLayout.EAST);
        boolean input = true;
        while (input) {
            try {
                tid = Double.parseDouble(showInputDialog(null, "Hvor lang tid vil dere ha på dere? (Oppgis i sekunder, 0 = evig)"));
                input = false;
            } catch (NumberFormatException NFE) {
                showMessageDialog(null, "Input må være et tall!");
            } catch (NullPointerException e) {
                tid = 0;
                input = false;
            }

        }

        if (tid > 0) {
            add(new TidTaker(true), BorderLayout.SOUTH);
            add(new TidTaker(false), BorderLayout.NORTH);
        }

        setJMenuBar(new MenyBar());
        pack();
    }

    /**
     * Klassen som tar seg av tidtakingen.
     */
    private class TidTaker extends JPanel {

        private JLabel tidLabel;
        private double teller = tid;
        private String tidString = ""+ (int) teller / 3600 + ":" + (int) (teller % 3600) / 60 + ":" + (int) teller % 60;

        /**
         * Konstruktør som oppretter en ActionListener som utfører en viss
         * handling hvert sekund. I denne oppdaterer den tiden og legger den ut
         * i en JLabel hvert sekund.
         *
         * @param isHvit Bestemmer om det er en hvit eller svart sin tur.
         */
        public TidTaker(final boolean isHvit) {
            int delay = 1000;
            tidLabel = new JLabel(tidString);
            ActionListener taskPerformer = new ActionListener() {
                /**
                 * Hendelsen som skal skje hvert sekund. Her dekrementeres en teller i tillegg til at den nye tiden blir skrevet ut.
                 */
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (isStarted) {
                        int input;
                        String[] valg = {"New game", "Exit"};
                        if (isHvit && !blackTurn) {
                            if (teller <= 0) {
                                input = showOptionDialog(b, "Hvit har ikke mer tid, svart vinner!", "Svart vinner!", YES_NO_OPTION, PLAIN_MESSAGE, null, valg, valg[0]);
                                switch (input) {
                                    case 0:
                                        dispose();
                                        b = new Gui("Sjakk");
                                        b.setVisible(true);
                                        isStarted = false;
                                        break;
                                    case 1:
                                        System.exit(0);
                                }
                            }
                            teller--;
                            tidString = ""+ (int) teller / 3600 + ":" + (int) (teller % 3600) / 60 + ":" + (int) teller % 60;
                        } else if (!isHvit && blackTurn) {
                            if (teller <= 0) {
                                input = showOptionDialog(b, "Svart har ikke mer tid, hvit vinner!", "Hvit vinner!", YES_NO_OPTION, PLAIN_MESSAGE, null, valg, valg[0]);
                                switch (input) {
                                    case 0:
                                        dispose();
                                        b = new Gui("Sjakk");
                                        b.setVisible(true);
                                        isStarted = false;
                                        break;
                                    case 1:
                                        System.exit(0);
                                }
                            }
                            teller--;
                            tidString = ""+ (int) teller / 3600 + ":" + (int) (teller % 3600) / 60 + ":" + (int) teller % 60;
                        }
                        tidLabel.setText(tidString);
                    }
                }
            };
            new Timer(delay, taskPerformer).start();
            add(tidLabel);

        }
    }
    /**
     * Programmets meny. Her er det mulighet for å lage nytt spill eller å avslutte spillet.
     */
    private class MenyBar extends JMenuBar {

        public MenyBar() {
            JMenu filMeny = new JMenu("File");
            filMeny.setMnemonic('F');
            add(filMeny);
            MenuItem newGame = new MenuItem("New game");
            newGame.setMnemonic('N');
            MenuItem exit = new MenuItem("Exit");
            exit.setMnemonic('E');
            filMeny.add(newGame);
            filMeny.add(exit);
            newGame.addActionListener(new MenyListener());
            exit.addActionListener(new MenyListener());
        }
    }
    /**
     * Hver enkelt valg i menyen. Brukes i MenyBar.
     */
    private class MenuItem extends JMenuItem {

        private String navn;

        public MenuItem(String navn) {
            this.navn = navn;
            this.setText(navn);
        }

        @Override
        public String toString() {
            return navn;
        }
    }
    /**
     * Lytteren knyttet til hver enkelt valg i menyen.
     */
    private class MenyListener implements ActionListener {

        public MenyListener() {
        }
        /**
         * Beskriver hva som skal gjøres når et valg blir trykket. 
         * @param e 
         * MenuItem-objektet.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String valg = e.getSource().toString();
            String[] navn = {"New game", "Exit"};

            if (valg.equals(navn[0])) {
                dispose();
                b = new Gui("Sjakk");
                b.setVisible(true);
                isStarted = false;
            } else {
                System.exit(0);
            }
        }
    }
    /**
     * Selve sjakkbrettet. Her opprettes det 64 GuiRute-objekter med riktig bilde for de forskjellige brikkene på rett plass.
     * I tillegg legges det en muselytter på hvert eneste GuiRute-objekt.
     */
    private class Rutenett extends JPanel {

        public Rutenett() {
            setLayout(new GridLayout(8, 8));
            setResizable(false);
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {

                    if (i == 0 || i == 1 || i == 6 || i == 7) {
                        JLabel bilde = new JLabel(brett.getIcon(j, i));
                        squares[i][j] = new GuiRute(bilde, i, j);
                        add(squares[i][j]);
                    } else {
                        squares[i][j] = new GuiRute(i, j);
                        add(squares[i][j]);

                    }
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(brown);
                    } else {
                        squares[i][j].setBackground(lightBrown);
                    }

                    squares[i][j].addMouseListener(new MuseLytter());
                }
            }
        }
    }
    /**
     * Klassen som legger ut bildet på ruta den tilhører. Klassen har en x- og en y-koordinat som holder styr på hvilken
     * rute det er snakk om i rutenettet.
     */
    private class GuiRute extends JPanel {

        private JLabel bilde;
        private int x;
        private int y;

        public GuiRute(JLabel bilde, int x, int y) {
            setSize(new Dimension(50, 50));
            this.bilde = bilde;
            this.x = x;
            this.y = y;
            add(bilde);
        }

        public GuiRute(int x, int y) {
            setPreferredSize(new Dimension(50, 50));
            this.x = x;
            this.y = y;
        }
        /**
         * Endrer bildet på ruta.
         * @param nyBilde 
         * Det nye bildet som skal legges ut.
         */
        public void setBilde(JLabel nyBilde) {
            bilde = nyBilde;
            if (bilde != null) {
                add(bilde);
                repaint();
                pack();
            }
        }
        /**
         * Henter bildet på denne ruta.
         * @return 
         * En JLabel med et ImageIcon.
         */
        public JLabel getBilde() {
            return bilde;
        }
        /**
         * Fjerner bildet på denne ruta.
         */
        public void removeBilde() {
            if (bilde != null) {
                this.remove(bilde);
            }
            this.setBilde(null);
            repaint();
        }
        /**
         * Sjekker om det ligger et bilde på ruta.
         * @return 
         * True eller false, om det er et bilde her eller ikke.
         */
        public boolean hasLabel() {
            return bilde != null;
        }
        /**
         * Gir x-verdien til ruta
         * @return 
         * En integer fra 0-7 som representer x-koordinaten til ruta.
         */
        public int getXen() {
            return x;
        }
        /**
         * Gir y-verdien til ruta
         * @return 
         * En integer fra 0-7 som representer y-koordinaten til ruta.
         */
        public int getYen() {
            return y;
        }
    }
    /**
     * Klassen som skriver ut trekkene som er tatt i et TextArea.
     */
    private class GameInfo extends JPanel {
        private int trekkT = 0;
        private TextArea tekstFelt;

        public GameInfo() {
            tekstFelt = new TextArea();
            tekstFelt.setPreferredSize(new Dimension(150, 150));
            tekstFelt.setEditable(false);
            JScrollPane jsp = new JScrollPane(tekstFelt);
            add(jsp);
        }
        /**
         * Oppdaterer tekstområdet med det siste trekket.
         * @param move
         * En String som indikerer hvilken rute brikken sto på før trekket ble gjort.
         * @param move2
         * En String som indikerer hvilken rute brikken står på etter at trekket har blitt gjort.
         */
        private void updateInfo(String move, String move2) {
            trekkT++;
            tekstFelt.append( trekkT+". " + move + " " + move2 + "\n");
        }
    }
    /**
     * Klassen som lytter etter musetrykk på de forskjellige rutene i sjakkbrettet.
     */
    private class MuseLytter implements MouseListener {
        /**
         * Metoden som skal kjøres hver gang en rute er trykket. Her sjekkes det om programme skal finne trekk for spilleren,
         * om den skal flytte brikken eller om den skal fjerne opplyste trekk. Det er her all kommunikasjon med Brett ligger.
         * @param e 
         * Ruten som er trykket på.
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            GuiRute denne = (GuiRute) e.getSource();
            Rute R = brett.getRute(denne.getYen(), denne.getXen());
            isStarted = true;
            isSjakk = brett.isSjakk(!blackTurn);
            boolean isBlock = brett.getBlockingCheck();

            if (isSjakk) {
                if (!isHighlighted && denne.hasLabel()) {
                    move = trekk[denne.getYen()] + (denne.getXen() + 1);
                    if (!blackTurn) {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (sjekk.getBrikke().isHvit()) {
                            ArrayList<Rute> brikker = brett.whatPiecesBlockCheck(!blackTurn);
                            if (brikker != null) {
                                for (int i = 0; i < brikker.size(); i++) {
                                    if (brikker.get(i).getX() == denne.getYen() && brikker.get(i).getY() == denne.getXen()) {
                                        denne.setBackground(highlighted);
                                        isHighlighted = true;
                                    }
                                }
                            }
                        }
                    } else {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (!sjekk.getBrikke().isHvit()) {
                            ArrayList<Rute> brikker = brett.whatPiecesBlockCheck(!blackTurn);
                            if (brikker != null) {
                                for (int i = 0; i < brikker.size(); i++) {
                                    if (brikker.get(i).getX() == denne.getYen() && brikker.get(i).getY() == denne.getXen()) {
                                        denne.setBackground(highlighted);
                                        isHighlighted = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!isHighlighted && R.isOccupied() && denne.hasLabel()) {

                    move = trekk[denne.getYen()] + (denne.getXen() + 1);
                    if (!blackTurn) {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (sjekk.getBrikke().isHvit()) {
                            denne.setBackground(highlighted);
                            isHighlighted = true;
                        }
                    } else {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (!sjekk.getBrikke().isHvit()) {
                            denne.setBackground(highlighted);
                            isHighlighted = true;
                        }
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!blackTurn) {
                        if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && !isSjakk && !isBlock) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        } else if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isSjakk) {
                            int x = j;
                            int y = i;
                            if (brett.getRute(x, y).getBrikke() instanceof Konge) {
                                lovligeTrekk = brett.sjekkLovligeTrekk(brett.getRute(x, y));
                            } else {
                                lovligeTrekk = brett.sjakkTrekk(!blackTurn, new Rute(x, y));
                            }
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        } else if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isBlock) {
                            int x = j;
                            int y = i;
                            brett.checkIfIsBlocking(new Rute(x, y));
                            if (brett.getRute(x, y).getBrikke() instanceof Konge) {
                                lovligeTrekk = brett.sjekkLovligeTrekk(brett.getRute(x, y));
                            } else if (brett.getRute(x, y).getBlocking()) {
                                lovligeTrekk = brett.blockingCheckMoves(!blackTurn, new Rute(x, y));
                            } else {
                                lovligeTrekk = brett.sjekkLovligeTrekk(new Rute(x, y));
                            }
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        }
                    } else {
                        if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && !isSjakk && !isBlock) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        } else if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isSjakk) {
                            int x = j;
                            int y = i;
                            if (brett.getRute(x, y).getBrikke() instanceof Konge) {
                                lovligeTrekk = brett.sjekkLovligeTrekk(brett.getRute(x, y));
                            } else {
                                lovligeTrekk = brett.sjakkTrekk(blackTurn, new Rute(x, y));
                            }
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        } else if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isBlock) {
                            int x = j;
                            int y = i;
                            brett.checkIfIsBlocking(new Rute(x, y));
                            if (brett.getRute(x, y).getBrikke() instanceof Konge) {
                                lovligeTrekk = brett.sjekkLovligeTrekk(brett.getRute(x, y));
                            } else if (brett.getRute(x, y).getBlocking()) {
                                lovligeTrekk = brett.blockingCheckMoves(!blackTurn, new Rute(x, y));
                            } else {
                                lovligeTrekk = brett.sjekkLovligeTrekk(new Rute(x, y));
                            }
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                            }
                        }
                    }
                }
            }

            if (denne.getBackground().equals(highlightedTrekk)) {
                int x = denne.getXen();
                int y = denne.getYen();

                Rute startRute = new Rute(0, 0);
                JLabel oldPic = new JLabel();
                GuiRute startGuiRute = null;

                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (squares[i][u].getBackground().equals(highlighted)) {
                            startGuiRute = squares[i][u];
                            startRute = new Rute(i, u);
                            oldPic = squares[i][u].getBilde();
                        }
                    }
                }
                if (brett.getRute(y, x).isOccupied()) {
                    if (blackTurn) {
                        if (brett.getRute(y, x).getBrikke().isHvit()) {
                            denne.removeBilde();
                        }
                    } else {
                        if (!brett.getRute(y, x).getBrikke().isHvit()) {
                            denne.removeBilde();
                        }
                    }

                } else if (x > 0 && brett.getRute(startRute.getY(), startRute.getX()).getBrikke() instanceof Bonde && brett.getRute(y, x - 1).getBrikke() instanceof Bonde && y != startRute.getY() && x > 0 && brett.getRute(y, x - 1).isOccupied() && ((Bonde) brett.getRute(y, x - 1).getBrikke()).isUnPasant()) {
                    squares[x - 1][y].removeBilde();
                } else if (x < 7 && brett.getRute(startRute.getY(), startRute.getX()).getBrikke() instanceof Bonde && brett.getRute(y, x + 1).getBrikke() instanceof Bonde && y != startRute.getY() && x + 1 < 8 && brett.getRute(y, x + 1).isOccupied() && brett.getRute(y, x + 1).getBrikke() instanceof Bonde && ((Bonde) brett.getRute(y, x + 1).getBrikke()).isUnPasant()) {
                    squares[x + 1][y].removeBilde();

                }
                brett.flyttBrikke(new Rute(x, y), startRute, blackTurn);
                gameInfo.updateInfo(move, move2);
                if (blackTurn) {
                    blackTurn = false;
                } else {
                    blackTurn = true;
                }
                move2 = trekk[y] + (x + 1);
                startGuiRute.removeBilde();
                denne.setBilde(oldPic);

                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (squares[i][u].getBackground().equals(highlighted)) {
                            if ((i + u) % 2 == 0) {
                                squares[i][u].setBackground(brown);
                            } else {
                                squares[i][u].setBackground(lightBrown);
                            }
                        } else if (squares[i][u].getBackground().equals(highlightedTrekk)) {
                            if ((i + u) % 2 == 0) {
                                squares[i][u].setBackground(brown);
                            } else {
                                squares[i][u].setBackground(lightBrown);
                            }
                        }
                    }
                    isHighlighted = false;
                }
            }
            if (brett.update("HV") && teller1 == 0) {
                JLabel pic = squares[0][0].getBilde();
                GuiRute oldTaarn = squares[0][0];
                oldTaarn.removeBilde();
                squares[0][3].setBilde(pic);
                repaint();
                teller1++;
            }
            if (brett.update("HH") && teller2 == 0) {
                JLabel pic = squares[0][7].getBilde();
                GuiRute oldTaarn = squares[0][7];
                oldTaarn.removeBilde();
                squares[0][5].setBilde(pic);
                repaint();
                teller2++;
            }
            if (brett.update("SH") && teller3 == 0) {
                JLabel pic = squares[7][7].getBilde();
                GuiRute oldTaarn = squares[7][7];
                oldTaarn.removeBilde();
                squares[7][5].setBilde(pic);
                repaint();
                teller3++;
            }
            if (brett.update("SV") && teller4 == 0) {
                JLabel pic = squares[7][0].getBilde();
                GuiRute oldTaarn = squares[7][0];
                oldTaarn.removeBilde();
                squares[7][3].setBilde(pic);
                repaint();
                teller4++;
            }
            pack();
            validate();
            if (brett.getRute(denne.getYen(), denne.getXen()).getBrikke() instanceof Bonde && denne.getXen() == 7) {
                PromotePieceFrame ppf = new PromotePieceFrame(blackTurn, brett.getRute(denne.getYen(), denne.getXen()));
                ppf.setVisible(true);
                isDone = false;
            } else if (brett.getRute(denne.getYen(), denne.getXen()).getBrikke() instanceof Bonde && denne.getXen() == 0) {
                PromotePieceFrame ppf = new PromotePieceFrame(blackTurn, brett.getRute(denne.getYen(), denne.getXen()));
                ppf.setVisible(true);
                isDone = false;
            }

            if (isDone) {
                isBlock = brett.checkIfBlockingCheck(!blackTurn);
                brett.setBlockingCheck(isBlock);

                boolean isSjakkMatt = brett.isSjakkMatt(!blackTurn);
                if (isSjakkMatt) {
                    String[] valg = {"New game", "Exit"};
                    if (!blackTurn) {
                        int input = showOptionDialog(b, "Hvit er sjakkmatt, Svart vinner!", "Svart vinner!", YES_NO_OPTION, PLAIN_MESSAGE, null, valg, valg[0]);
                        switch (input) {
                            case 0:
                                dispose();
                                b = new Gui("Sjakk");
                                b.setVisible(true);
                                isStarted = false;
                                break;
                            case 1:
                                System.exit(0);
                        }
                    } else {
                        int input = showOptionDialog(b, "Svart er sjakkmatt, Hvit vinner!", "Hvit vinner!", YES_NO_OPTION, PLAIN_MESSAGE, null, valg, valg[0]);
                        switch (input) {
                            case 0:
                                dispose();
                                b = new Gui("Sjakk");
                                b.setVisible(true);
                                isStarted = false;
                                break;
                            case 1:
                                System.exit(0);
                        }
                    }
                }
                if (isHighlighted && !denne.getBackground().equals(highlighted) && !denne.getBackground().equals(highlightedTrekk)) {
                    if (!blackTurn) {
                        for (int i = 0; i < 8; i++) {
                            for (int u = 0; u < 8; u++) {
                                if (squares[i][u].getBackground().equals(highlighted)) {
                                    if ((i + u) % 2 == 0) {
                                        squares[i][u].setBackground(brown);
                                    } else {
                                        squares[i][u].setBackground(lightBrown);
                                    }
                                } else if (squares[i][u].getBackground().equals(highlightedTrekk)) {
                                    if ((i + u) % 2 == 0) {
                                        squares[i][u].setBackground(brown);
                                    } else {
                                        squares[i][u].setBackground(lightBrown);
                                    }
                                }
                            }
                            isHighlighted = false;
                        }

                    } else {
                        for (int i = 0; i < 8; i++) {
                            for (int u = 0; u < 8; u++) {
                                if (squares[i][u].getBackground().equals(highlighted)) {
                                    if ((i + u) % 2 == 0) {
                                        squares[i][u].setBackground(brown);
                                    } else {
                                        squares[i][u].setBackground(lightBrown);
                                    }
                                } else if (squares[i][u].getBackground().equals(highlightedTrekk)) {
                                    if ((i + u) % 2 == 0) {
                                        squares[i][u].setBackground(brown);
                                    } else {
                                        squares[i][u].setBackground(lightBrown);
                                    }
                                }
                            }
                            isHighlighted = false;
                        }
                    }
                }
            }
        }
        /**
         * Ikke i bruk.
         * @param e 
         */
        @Override
        public void mousePressed(MouseEvent e) {
        }
        /**
         * Ikke i bruk.
         * @param e 
         */
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        /**
         * Ikke i bruk.
         * @param e 
         */
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        /**
         * Ikke i bruk.
         * @param e 
         */
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    /**
     * Vinduet som kommer opp når du har flyttet en bonde til andre siden av brettet.
     */
    private class PromotePieceFrame extends JFrame {

        private Rute r;
        /**
         * Konstruktøren lager et vindu med fire knapper, svart eller hvit.
         * @param isHvit 
         * Bestemmer om knappene skal ha hvite eller svarte bilder.
         * @param r
         * Ruten som brikken sto på.
         */
        public PromotePieceFrame(boolean isHvit, Rute r) {
            setLayout(new GridLayout(2, 2));
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            if (isHvit) {
                add(new Knapp("hvitDronning", new ImageIcon(getClass().getResource("/Images/whiteQueen.gif"))));
                add(new Knapp("hvitTårn", new ImageIcon(getClass().getResource("/Images/whiteTaarn.gif"))));
                add(new Knapp("hvitLøper", new ImageIcon(getClass().getResource("/Images/whiteLoper.gif"))));
                add(new Knapp("hvitSpringer", new ImageIcon(getClass().getResource("/Images/whiteSpringer.gif"))));
            } else {
                add(new Knapp("svartDronning", new ImageIcon(getClass().getResource("/Images/blackQueen.gif"))));
                add(new Knapp("svartTårn", new ImageIcon(getClass().getResource("/Images/blackTaarn.gif"))));
                add(new Knapp("svartLøper", new ImageIcon(getClass().getResource("/Images/blackLoper.gif"))));
                add(new Knapp("svartSpringer", new ImageIcon(getClass().getResource("/Images/blackSpringer.gif"))));
            }
            this.r = r;
            pack();

        }
        /**
         * Selve knappen som dukker opp i forvandlingsvinduet
         */
        private class Knapp extends JButton {

            private ImageIcon bilde;
            /**
             * Konstruktøren legger et bilde og en knappelytter til knappen.
             * @param e
             * En string med info om hvilken brikke knappen representerer.
             * @param i 
             * Et ImageIcon som legges oppå knappen.
             */
            public Knapp(String e, ImageIcon i) {
                super(i);
                setActionCommand(e);
                addActionListener(new KnappeLytter());
            }
        }
        /**
         * Knappelytteren som brukes til å finne ut hvilken knapp som blir trykket på.
         */
        private class KnappeLytter implements ActionListener {
            /**
             * Det som skal skje når en knapp blir trykket på.
             * @param e 
             * Knappen som blir trykket på.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String valg = e.getActionCommand();
                switch (valg) {
                    case "hvitDronning":
                        brett.promotePiece(r, new Dronning(true));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/whiteQueen.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "hvitTårn":
                        brett.promotePiece(r, new Taarn(true));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/whiteTaarn.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "hvitLøper":
                        brett.promotePiece(r, new Loper(true));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/whiteLoper.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "hvitSpringer":
                        brett.promotePiece(r, new Springer(true));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/whiteSpringer.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "svartDronning":
                        brett.promotePiece(r, new Dronning(false));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/blackQueen.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "svartTårn":
                        brett.promotePiece(r, new Taarn(false));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/blackTaarn.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "svartLøper":
                        brett.promotePiece(r, new Loper(false));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/blackLoper.gif"))));
                        isDone = true;
                        dispose();
                        break;
                    case "svartSpringer":
                        brett.promotePiece(r, new Springer(false));
                        squares[r.getY()][r.getX()].removeBilde();
                        squares[r.getY()][r.getX()].setBilde(new JLabel(new ImageIcon(getClass().getResource("/Images/blackSpringer.gif"))));
                        isDone = true;
                        dispose();
                        break;
                }
            }
        }
    }
}
