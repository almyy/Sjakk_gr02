package Sjakk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class Gui extends JFrame {

    private Brett brett = new Brett();
    private GuiRute squares[][] = new GuiRute[8][8];
    private ArrayList<Rute> lovligeTrekk;
    private String[] trekk = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private String move;
    private String move2;
    private final Color brown = new Color(160, 82, 45);
    private final Color lightBrown = new Color(244, 164, 96);
    private final Color highlighted = new Color(195, 205, 205);
    private final Color highlightedTrekk = new Color(191, 239, 255);
    private final Color highlightedSjakkSave = new Color(255, 255, 255);
    private boolean whiteTurn = true;
    private boolean isHighlighted = false;
    private boolean isSjakk = false;
    private boolean blockingCheck = false;
    private Rutenett rutenett;
    private GameInfo gameInfo;
    private Gui b;

    public Gui(String tittel) {
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        setLayout(new BorderLayout());
        rutenett = new Rutenett();
        add(rutenett, BorderLayout.CENTER);
        gameInfo = new GameInfo();
        add(gameInfo, BorderLayout.EAST);
        add(new SpillerNavn("Spiller2"), BorderLayout.NORTH);
        add(new TidTaker(true), BorderLayout.NORTH);
        add(new SpillerNavn("Spiller1"), BorderLayout.SOUTH);
        add(new TidTaker(false), BorderLayout.SOUTH);
        setJMenuBar(new MenyBar());
        pack();
    }

    private class TidTaker extends JPanel {

        private Tid tid;
        private JLabel tidLabel;

        public TidTaker(boolean isHvit) {
            tid = new Tid(isHvit);
            tidLabel = new JLabel();
            add(tidLabel);
            tid.start();
        }

        private class Tid extends Thread {

            private double tellerS = 300.0;
            private double tellerH = 300.0;
            private String timerS = "";
            private String timerH = "";
            private boolean isHvit;

            public Tid(boolean isHvit) {
                this.isHvit = isHvit;
            }

            @Override
            public void run() {
                while (true) {
                    while (whiteTurn && isHvit) {
                        timerH = "Hvit: " + (int) tellerH / 60 + " min, " + (int) tellerH % 60;
                        tidLabel.setText(timerH);
                        try {
                            tid.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tellerH = tellerH - 0.1;
                    }
                    while (!whiteTurn && !isHvit) {
                        timerS = "Svart: " + (int) tellerS / 60 + " min, " + (int) tellerS % 60;
                        tidLabel.setText(timerS);
                        try {
                            tid.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        tellerS = tellerS - 0.1;
                    }
                }
            }
        }
    }

    private class Homo implements Serializable {

        ObjectOutputStream oos;
        ObjectInputStream ois;

        public Homo() throws IOException {
            File file = new File("C:/Sjakk.dat");
            try {
                if (file.createNewFile()) {
                    System.out.println("File Created");
                } else {
                }
            } catch (Exception e) {
            }
        }

        public boolean lagre() throws IOException {
            oos = new ObjectOutputStream(new FileOutputStream("C:/Sjakk.dat"));
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    oos.writeObject(squares[i][u]);
                }
            }
            oos.writeObject(brett);
            return true;
        }

        public void laste() throws IOException, ClassNotFoundException {
            ois = new ObjectInputStream(new FileInputStream("C:/Sjakk.dat"));
            Object obj = null;
            while ((obj = ois.readObject()) != null) {
                if (ois.readObject() instanceof GuiRute) {
                    GuiRute r = (GuiRute) ois.readObject();
                    squares[r.getXen()][r.getYen()] = r;
                }
                //} else if (ois.readObject() instanceof Brett) {
                //  brett = (Brett) ois.readObject();
                //}
            }
        }
    }

    private class MenyBar extends JMenuBar {

        public MenyBar() {
            JMenu filMeny = new JMenu("File");
            filMeny.setMnemonic('F');
            JMenu optionMeny = new JMenu("Options");
            add(filMeny);
            add(optionMeny);
            MenuItem newGame = new MenuItem("New game");
            newGame.setMnemonic('N');
            MenuItem saveGame = new MenuItem("Save game");
            saveGame.setMnemonic('S');
            MenuItem loadGame = new MenuItem("Load game");
            loadGame.setMnemonic('L');
            MenuItem exit = new MenuItem("Exit");
            exit.setMnemonic('E');
            MenuItem preferences = new MenuItem("Preferences");
            filMeny.add(newGame);
            filMeny.add(saveGame);
            filMeny.add(loadGame);
            filMeny.add(exit);
            optionMeny.add(preferences);
            newGame.addActionListener(new MenyListener());
            saveGame.addActionListener(new MenyListener());
            loadGame.addActionListener(new MenyListener());
            exit.addActionListener(new MenyListener());
            preferences.addActionListener(new MenyListener());
        }
    }

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

    private class MenyListener implements ActionListener {

        private Homo jall;

        public MenyListener() {
            try {
                jall = new Homo();
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String valg = e.getSource().toString();
            String[] navn = {"New game", "Save game", "Load game", "Exit", "Preferences"};

            /*
             * try { b = new IO(); } catch (IOException ex) {
             * Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null,
             * ex); }
             */
            if (valg.equals(navn[0])) {

                dispose();
                Gui a = new Gui("Sjakk");
                a.setVisible(true);
            } else if (valg.equals(navn[1])) {

                try {
                    IO a = new IO();
                    a.IOwrite();
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (valg.equals(navn[2])) {
                try {
                    IO c = new IO();
                    ArrayList<Rutenett> help = c.IORead();
                    rutenett = help.get(0);
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else if (valg.equals(navn[3])) {
                System.exit(0);
            } else {
                throw new UnsupportedOperationException("Not implemented yet.");
            }
        }
    }

    private class SpillerNavn extends JPanel {

        public SpillerNavn(String navn) {
            setLayout(new FlowLayout());
            JLabel spiller = new JLabel(navn);
            spiller.setFont(new Font("Serif", Font.BOLD, 20));
            add(spiller);
        }
    }

    private class Rutenett extends JPanel {

        public Rutenett() {
            setLayout(new GridLayout(8, 8));
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

    private class GuiRute extends JPanel {

        private JLabel bilde;
        private int x;
        private int y;

        public GuiRute(JLabel bilde, int x, int y) {
            setPreferredSize(new Dimension(50, 50));
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

        public void setBilde(JLabel nyBilde) {
            bilde = nyBilde;
            if (bilde != null) {
                add(bilde);
            }
            this.repaint();
        }

        public JLabel getBilde() {
            return bilde;
        }

        public void removeBilde() {
            if (bilde != null) {
                this.remove(bilde);
            }
            this.setBilde(null);
        }

        public boolean hasLabel() {
            return bilde != null;
        }

        public int getXen() {
            return x;
        }

        public int getYen() {
            return y;
        }
    }

    private class GameInfo extends JPanel {

        private TextArea tekstFelt;

        public GameInfo() {
            tekstFelt = new TextArea();
            tekstFelt.setPreferredSize(new Dimension(150, 490));
            tekstFelt.setEditable(false);
            JScrollPane jsp = new JScrollPane(tekstFelt);
            add(jsp);
        }

        private void updateInfo(String move, String move2, boolean whiteTurn) {
            if (!whiteTurn) {
                tekstFelt.append("Hvitt trekk: " + move + " til " + move2 + "\n");
            } else {
                tekstFelt.append("Svart trekk: " + move + " til " + move2 + "\n");
            }
        }
    }

    private class IO {

        private File f;
        ObjectInputStream mick;

        public IO() throws IOException {

            f = new File("C:/Jobb/Sjakk.dat");

            f.createNewFile();

        }

        public ArrayList<Rutenett> IORead() {

            ArrayList<Rutenett> hjelp = new ArrayList();


            try {
                mick = new ObjectInputStream(new FileInputStream(f));

                Object obj = null;
                while ((obj = mick.readObject()) != null) {

                    if (obj instanceof Rutenett) {
                        hjelp.add(((Rutenett) obj));
                    }


                }


            } catch (EOFException ex) {
                System.out.println("Slutt på fil.");
            } catch (ClassNotFoundException ex) {
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } finally {

                try {
                    if (mick != null) {
                        mick.close();


                    }
                } catch (IOException ex) {
                }
            }
            return hjelp;
        }

        public void IOwrite() {


            ObjectOutputStream lol = null;
            ArrayList<Rutenett> help = new ArrayList();
            help.add(rutenett);


            try {

                lol = new ObjectOutputStream(new FileOutputStream(f));


                lol.writeObject(help);





            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } finally {
                try {
                    if (lol != null) {
                        lol.flush();
                        lol.close();
                    }
                } catch (IOException ex) {
                }

            }
        }
    }

    private class MuseLytter implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            GuiRute denne = (GuiRute) e.getSource();
            isSjakk = brett.isSjakk(whiteTurn);
            boolean isBlock = brett.getBlockingCheck();
            if (isSjakk) {
                System.out.println("Gjør et flytt som fjerner sjakken");
                if (!isHighlighted && denne.hasLabel()) {
                    move = trekk[denne.getYen()] + (denne.getXen() + 1);
                    if (whiteTurn) {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (sjekk.getBrikke().isHvit()) {
                            ArrayList<Rute> brikker = brett.whatPiecesBlockCheck(whiteTurn);
                            if (brikker != null) {
                                for (int i = 0; i < brikker.size(); i++) {
                                    if (brikker.get(i).getX() == denne.getYen() && brikker.get(i).getY() == denne.getXen()) {
                                        denne.setBackground(highlighted);
                                        isHighlighted = true;
                                        whiteTurn = false;
                                        brett.setBlockingCheck(true);
                                    }
                                }
                            }
                        }
                    } else {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (!sjekk.getBrikke().isHvit()) {
                            ArrayList<Rute> brikker = brett.whatPiecesBlockCheck(whiteTurn);
                            if (brikker != null) {
                                for (int i = 0; i < brikker.size(); i++) {
                                    if (brikker.get(i).getX() == denne.getYen() && brikker.get(i).getY() == denne.getXen()) {
                                        denne.setBackground(highlighted);
                                        isHighlighted = true;
                                        whiteTurn = true;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                if (!isHighlighted && denne.hasLabel()) {

                    move = trekk[denne.getYen()] + (denne.getXen() + 1);
                    if (whiteTurn) {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (sjekk.getBrikke().isHvit()) {
                            denne.setBackground(highlighted);
                            isHighlighted = true;
                            whiteTurn = false;
                        }
                    } else {
                        Rute sjekk = brett.getRute(denne.getYen(), denne.getXen());
                        if (!sjekk.getBrikke().isHvit()) {
                            denne.setBackground(highlighted);
                            isHighlighted = true;
                            whiteTurn = true;
                        }
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!whiteTurn) {
                        if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && !isSjakk && !isBlock) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                                squares[lY][lX].addMouseListener(new MuseLytter2());
                            }
                        } else if ((squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isBlock) || (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isSjakk)) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjakkTrekk(!whiteTurn, new Rute(x, y));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                                squares[lY][lX].addMouseListener(new MuseLytter2());
                            }
                        }
                    }else{
                        if (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && !isSjakk) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                                squares[lY][lX].addMouseListener(new MuseLytter2());
                            }
                        } else if ((squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isBlock) || (squares[i][j] != null && squares[i][j].getBackground().equals(highlighted) && isSjakk)) {
                            int x = j;
                            int y = i;
                            lovligeTrekk = brett.sjakkTrekk(!whiteTurn, new Rute(x, y));
                            for (int u = 0; u < lovligeTrekk.size(); u++) {
                                int lX = lovligeTrekk.get(u).getX();
                                int lY = lovligeTrekk.get(u).getY();
                                squares[lY][lX].setBackground(highlightedTrekk);
                                squares[lY][lX].addMouseListener(new MuseLytter2());
                            }
                        }
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class MuseLytter2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            GuiRute denne = (GuiRute) e.getSource();
            Rute startRute = new Rute(0, 0);
            JLabel oldPic = new JLabel();
            GuiRute startGuiRute = null;
            int x = denne.getXen();
            int y = denne.getYen();
            for (int i = 0; i < 8; i++) {
                for (int u = 0; u < 8; u++) {
                    if (squares[i][u].getBackground().equals(highlighted)) {
                        startGuiRute = squares[i][u];
                        startRute = new Rute(i, u);
                        oldPic = squares[i][u].getBilde();
                    }
                }
            }
            if (denne.getBackground().equals(highlightedTrekk)) {
                if (brett.getRute(y, x).isOccupied()) {
                    if (whiteTurn) {
                        if (brett.getRute(y, x).getBrikke().isHvit()) {
                            denne.removeBilde();
                        }
                    } else {
                        if (!brett.getRute(y, x).getBrikke().isHvit()) {
                            denne.removeBilde();
                        }
                    }
                }
                brett.flyttBrikke(new Rute(x, y), startRute, whiteTurn);
                move2 = trekk[y] + (x + 1);
                gameInfo.updateInfo(move, move2, whiteTurn);
                startGuiRute.removeBilde();
                denne.setBilde(oldPic);

                for (int i = 0; i < 8; i++) {
                    for (int u = 0; u < 8; u++) {
                        if (squares[i][u].getBackground().equals(highlighted) || squares[i][u].getBackground().equals(highlightedTrekk)) {
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



            if (brett.update("HV")) {
                JLabel pic = null;
                pic = squares[0][0].getBilde();
                GuiRute oldTaarn = null;
                oldTaarn = squares[0][0];
                oldTaarn.removeBilde();
                squares[0][3].setBilde(pic);
                repaint();

            }
            if (brett.update("HH")) {
                JLabel pic = null;
                pic = squares[0][7].getBilde();
                GuiRute oldTaarn = null;
                oldTaarn = squares[0][7];
                oldTaarn.removeBilde();
                squares[0][5].setBilde(pic);
                repaint();

            }
            if (brett.update("SH")) {
                JLabel pic = null;
                pic = squares[7][7].getBilde();
                GuiRute oldTaarn = null;
                oldTaarn = squares[7][7];
                oldTaarn.removeBilde();
                squares[7][5].setBilde(pic);
                repaint();


            }
            if (brett.update("SV")) {
                JLabel pic = null;
                pic = squares[7][0].getBilde();
                GuiRute oldTaarn = null;
                oldTaarn = squares[7][0];
                oldTaarn.removeBilde();
                squares[7][3].setBilde(pic);
                repaint();



            }
            validate();

        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args) {


        Gui b = new Gui("Sjakk");
        b.setVisible(true);


    }
}
