
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import javax.swing.*;

class Gui extends JFrame {
    private Brett brett = new Brett();
    public Gui(String tittel) { 
        setTitle(tittel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        setLayout(new BorderLayout());
        add(new Rutenett(),BorderLayout.CENTER);
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
                    //squares[i][j].add(new MuseLytter());
                    JLabel u = new JLabel(new ImageIcon("src/images/icon.gif"));
                    u.setPreferredSize(new Dimension(40, 40));
                    squares[i][j].add(u);
                    if ((i + j) % 2 == 0) {
                        squares[i][j].setBackground(new Color(160,82,45));
                    } else {
                        squares[i][j].setBackground(new Color(160,82,45,127));
                    }
                }        
            }
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
