import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;

class Gui extends JFrame {

    private Brett brett;
    JPanel squares[][] = new JPanel[8][8];
    public Gui(String tittel) {

        setTitle(tittel);
        setPreferredSize(new Dimension(500, 500));
        setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Rutenett r = new Rutenett();
        add(r, BorderLayout.CENTER);
        pack();
    }
    private class Rutenett extends JPanel {
        private JPanel squares[][] = new JPanel[8][8];
        public Rutenett(){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    squares[i][j] = new JPanel();
                    squares[i][j].setPreferredSize(new Dimension(50,50));
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(new Color(160,82,45));
                    } else {
                        squares[i][j].setBackground(new Color(160,82,45,127));
                    }
                    add(squares[i][j]);
                }
            }        
        
            squares[0][0].add(new JLabel(new ImageIcon("C:/Users/Martin/Desktop/taarn.png")));//Tårn
            squares[0][2].add(new JLabel(new ImageIcon("")));//løper

            squares[0][4].add(new JLabel(new ImageIcon("")));//hest
            squares[0][5].add(new JLabel(new ImageIcon("")));//løper
            squares[0][7].add(new JLabel(new ImageIcon("")));//Taarn

            squares[7][0].add(new JLabel(new ImageIcon("")));
            squares[7][2].add(new JLabel(new ImageIcon("")));
            squares[7][4].add(new JLabel(new ImageIcon("")));
            squares[7][5].add(new JLabel(new ImageIcon("")));
            squares[7][7].add(new JLabel(new ImageIcon("")));

            for (int i = 0; i < 8; i++) {
                squares[1][i].add(new JLabel(new ImageIcon("")));
                squares[6][i].add(new JLabel(new ImageIcon("")));
            }
        }
    }
    public static void main(String[] args) {
        Gui sjakk = new Gui("Sjakk");
        sjakk.setVisible(true);
    }
}
