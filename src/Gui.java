
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Gui extends JFrame {

    private Brett brett = new Brett();

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

        private JPanel squares[][] = new JPanel[8][8];

        public Rutenett() {
            setLayout(new GridLayout(8, 8));
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    squares[i][j] = new JPanel();
                    squares[i][j].setPreferredSize(new Dimension(50, 50));
                    add(squares[i][j]);
                    //squares[i][j].add(new MuseLytter());
                    if (i == 1 || i == 6) {
                        squares[i][j].add(new JLabel(brett.getIcon(1, 1)));

                    }
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(new Color(160, 82, 45));
                    } else {
                        squares[i][j].setBackground(new Color(160, 82, 45, 127));
                    }
                }
            }
        }
    }

    private class GameInfo extends JPanel {

        ArrayList<String> hvitTrekk;
        ArrayList<String> svartTrekk;

        public GameInfo() {

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
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    public static void main(String[] args) {
        Gui b = new Gui("hore");
        b.setVisible(true);
    }
}
