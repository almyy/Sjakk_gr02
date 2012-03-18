
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Gui extends JFrame {

    private Brett brett = new Brett();
    private JPanel squares[][] = new JPanel[8][8];
    boolean isHighlighted = false;
    
    public Gui(String tittel) {
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 500));
        setLayout(new BorderLayout());
        add(new Rutenett(), BorderLayout.CENTER);
        add(new GameInfo(), BorderLayout.EAST);
        add(new SpillerNavn("Spiller2"), BorderLayout.NORTH);
        add(new SpillerNavn("Spiller1"), BorderLayout.SOUTH);
        pack();
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
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //squares[i][j] = new Rute();
                    //add(squares[i][j]);
                    if (i == 1 || i == 6) {
                        JLabel bilde = new JLabel(brett.getIcon(1, 1));
                        squares[i][j] = new GuiRute(bilde);
                        add(squares[i][j]);
                    }
                    else {
                        squares[i][j] = new GuiRute();
                        add(squares[i][j]);
                    }
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(new Color(160, 82, 45));
                    } else {
                        squares[i][j].setBackground(new Color(160, 82, 45, 127));
                    }
                    squares[i][j].addMouseListener(new MuseLytter());
                }
            }
        }
    }
    private class GuiRute extends JPanel {
        private JLabel bilde;
        public GuiRute(JLabel bilde) {
            setPreferredSize(new Dimension(50, 50));
            this.bilde = bilde;
            add(bilde);
        }
        public GuiRute() {
            setPreferredSize(new Dimension(50, 50));
        }
        public void setBilde(JLabel nyBilde) {
            if(bilde == null && nyBilde != null) {
                bilde = nyBilde;
                add(bilde);
            }
        }
        public boolean hasLabel() {
            return bilde != null;
        }
    }
    private class GameInfo extends JPanel{

        ArrayList<String> hvitTrekk;
        ArrayList<String> svartTrekk;

        public GameInfo() {
            setLayout(new FlowLayout());
            hvitTrekk = brett.getHvitMoves();
            svartTrekk = brett.getSvartMoves();
            setPreferredSize(new Dimension(150, 500));
            TextField tekstFelt = new TextField("Siste trekk:");
            tekstFelt.setPreferredSize(new Dimension(150, 500));
            add(tekstFelt);
        }
    }

    private class MuseLytter implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            
            GuiRute denne = (GuiRute) e.getSource();
            Color highlighted = new Color(100, 149, 237);
            if (!isHighlighted && denne.hasLabel()) {
                denne.setBackground(highlighted);
                isHighlighted = true;
            }
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(squares[i][j].getBackground().equals(highlighted)) {
                        int x = j;
                        int y = i;
                        System.out.println("x; " + x + ", y: " + y);
                        ArrayList<Rute> lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                        for(int u = 0; u < lovligeTrekk.size(); u++) {
                            squares[lovligeTrekk.get(u).getX()-1][lovligeTrekk.get(u).getY()-1].setBackground(highlighted);
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

    public static void main(String[] args) {
        Gui b = new Gui("Sjakk");
        b.setVisible(true);
    }
}
