
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Gui extends JFrame {
    private Brett brett;    
    public Gui(String tittel) { 
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        setLayout(new BorderLayout());
        add(new Gui.Rutenett(),BorderLayout.CENTER);
        pack();
    }
    private class Rutenett extends JPanel{
        private JPanel squares[][] = new JPanel[8][8];
        public Rutenett(){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    squares[i][j] = new JPanel();
                    squares[i][j].setPreferredSize(new Dimension(50,50));
                    add(squares[i][j]);
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(new Color(160,82,45));
                    } else {
                        squares[i][j].setBackground(new Color(160,82,45,127));
                    }
                }        
            }
        }
    }

    public static void main(String[] args) {
        Gui b = new Gui("hore");
        b.setVisible(true);        
    }
}
