package Sjakk;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Gui extends JFrame {

    private Brett brett = new Brett();
    private GuiRute squares[][] = new GuiRute[8][8];
    private ArrayList<Rute> lovligeTrekk;
    private final Color brown = new Color(160, 82, 45);
    private final Color lightBrown = new Color(244, 164, 96);
    private final Color highlighted = new Color(195, 205, 205);
    private final Color highlightedTrekk = new Color(191,239,255);
    private boolean whiteTurn = true;
    private boolean isHighlighted = false;
    
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
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    
                    if(i==0||i==7) {
                        JLabel bilde = new JLabel(brett.getIcon(j,i));
                        squares[i][j] = new GuiRute(bilde,i,j);
                        add(squares[i][j]);
                    }
                    else { 
                        squares[i][j] = new GuiRute(i,j);
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
        public GuiRute(JLabel bilde,int x, int y) {
            setPreferredSize(new Dimension(50, 50));
            this.bilde = bilde;
            this.x=x;
            this.y=y;
            add(bilde);
        }
        public GuiRute(int x,int y) {
            setPreferredSize(new Dimension(50, 50));
            this.x=x;
            this.y=y;
        }
        public void setBilde(JLabel nyBilde) {
            bilde = nyBilde;
            if (bilde!=null) {
                add(bilde);
            }
            this.repaint();
        }
        public JLabel getBilde(){
            return bilde;
        }
        public void removeBilde(int x,int y){
            for(int i = 0; i< 8; i++){
                for(int u = 0; u < 8; u++){
                    if(i==x && y==u){
                        this.bilde = null;
                        this.repaint();
                    }
                }
            }
        }
        public boolean hasLabel() {
            return bilde != null;
        }
        public int getXen(){
            return x;
        }
        public int getYen(){
            return y;
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
            if (!isHighlighted && denne.hasLabel()) {
                if(whiteTurn){
                    Rute sjekk = brett.getRute(denne.getYen(),denne.getXen());
                    if(sjekk.getBrikke().isHvit()){
                        denne.setBackground(highlighted);
                        isHighlighted = true;
                        whiteTurn = true;
                    }
                }
                else{
                    Rute sjekk = brett.getRute(denne.getYen(),denne.getXen());
                    if(!sjekk.getBrikke().isHvit()){
                        denne.setBackground(highlighted);
                        isHighlighted = true;
                        whiteTurn = true;
                    }
                }
            }
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(squares[i][j].getBackground().equals(highlighted)&&squares[i][j] != null) {
                        int x = j;
                        int y = i;                      
                        lovligeTrekk = brett.sjekkLovligeTrekk((brett.getRute(x, y)));
                        for(int u = 0; u < lovligeTrekk.size(); u++) {
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
            GuiRute denne = (GuiRute)e.getSource();
            Rute r = new Rute(0,0);
            JLabel l = new JLabel();
            GuiRute f = null;
            for(int i = 0; i < 8; i++){
                for(int u = 0; u < 8; u++){
                    if(squares[i][u].getBackground().equals(highlighted)){
                        f = squares[i][u];
                        r = new Rute(i,u);
                        l = squares[i][u].getBilde();
                    }
                }
            }
            if(denne.getBackground().equals(highlightedTrekk)){
                brett.flyttBrikke(new Rute(denne.getXen(),denne.getYen()),lovligeTrekk,r);
                denne.setBilde(l);
                f.removeBilde(f.getXen(), f.getYen());
                for(int i = 0; i < 8; i++){
                    for(int u = 0; u < 8; u++){
                        if(squares[i][u].getBackground().equals(highlighted)||squares[i][u].getBackground().equals(highlightedTrekk)){
                            if((i+u)%2==0){
                                squares[i][u].setBackground(brown);
                            }
                            else{
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