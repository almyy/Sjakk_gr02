package Sjakk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

class Gui extends JFrame{

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
    private boolean whiteTurn = true;
    private boolean isHighlighted = false;
    private Rutenett rutenett;

    public Gui(String tittel) {
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());
        rutenett = new Rutenett();
        add(rutenett, BorderLayout.CENTER);
        add(new GameInfo(), BorderLayout.EAST);
        add(new SpillerNavn("Spiller2"), BorderLayout.NORTH);
        add(new SpillerNavn("Spiller1"), BorderLayout.SOUTH);
        setJMenuBar(new MenyBar());
        pack();        
    }

    private class MenyBar extends JMenuBar {

        public MenyBar() {
            JMenu filMeny = new JMenu("File");
            JMenu optionMeny = new JMenu("Options");
            add(filMeny);
            add(optionMeny);
            MenuItem newGame = new MenuItem("New game");
            MenuItem saveGame = new MenuItem("Save game");
            MenuItem loadGame = new MenuItem("Load game");
            MenuItem exit = new MenuItem("Exit");
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

    private class MenyListener implements ActionListener  {
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String valg = e.getSource().toString();
            String[] navn = {"New game", "Save game", "Load game", "Exit", "Preferences"};
            if (valg.equals(navn[0])) {
                brett = new Brett();
            } else if (valg.equals(navn[1])) {
                throw new UnsupportedOperationException("Not implemented yet.");
            } else if (valg.equals(navn[2])) {
                throw new UnsupportedOperationException("Not implemented yet.");
            } else if (valg.equals(navn[3])) {
                throw new UnsupportedOperationException("Not implemented yet.");
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

        ArrayList<String> hvitTrekk;
        ArrayList<String> svartTrekk;

        public GameInfo() {
            setLayout(new FlowLayout());
            hvitTrekk = brett.getHvitMoves();
            svartTrekk = brett.getSvartMoves();
            setPreferredSize(new Dimension(150, 500));
            TextArea tekstFelt = new TextArea("Siste trekk:");
            tekstFelt.setPreferredSize(new Dimension(150, 700));
            add(tekstFelt);
        }
    }

    private class MuseLytter implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            GuiRute denne = (GuiRute) e.getSource();
            if (!isHighlighted && denne.hasLabel()) {
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
            move = trekk[denne.getXen()] + denne.getYen();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (squares[i][j].getBackground().equals(highlighted) && squares[i][j] != null) {
                        int x = j;
                        int y = i;
                        lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
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
                System.out.println("Works");
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
                brett.flyttBrikke(new Rute(x, y), startRute);
                move2 = trekk[x] + y;
                //brett.registrateMove(move,move2);
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