
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
class GUI extends JFrame{

    private Brett brett;
    //dmd

    
    public JFrame frame;
    public JPanel squares[][] = new JPanel[8][8];

    public GUI() {
        frame = new JFrame("Gr.2 GUItest");
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(8, 8));
        

        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JPanel();

                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.white);
                } else {
                    squares[i][j].setBackground(Color.black);
                }
                frame.add(squares[i][j]);
            }
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        
        GUI b = new GUI();
        
    }
}