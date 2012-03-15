
import javax.swing.*;
import java.awt.*;
import java.net.*;
import javax.swing.*;
/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author Martin
 */
class GUI extends JFrame {

    private Brett brett;
    JFrame frame;
    JPanel squares[][] = new JPanel[8][8];
    //dmdxf

    public GUI(String tittel) {

        frame = new JFrame(tittel);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JPanel();

                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.black);
                } else {
                    squares[i][j].setBackground(Color.white);
                }
                frame.add(squares[i][j]);
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        GUI sjakk = new GUI("Sjakk");
        sjakk.setVisible(true);
    }
}
