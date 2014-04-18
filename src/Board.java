import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Skyler on 4/16/14.
 */
public class Board extends JPanel implements MouseListener{
    private final int SQRSIZE = 80;
    private Checker [] [] board = new Checker [8] [8];
    private Checker [] pieces = new Checker [16];
    private Point choice1 = new Point(-1, -1);
    private Point choice2 = new Point(-1, -1);

    public Board(){
        //setBackground(Color.BLACK);
        setPreferredSize(new Dimension(640, 640));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        addMouseListener(this);

        //black components
        g.setColor(Color.BLACK);

        //squares
        for(int row = 0; row < 8; row+=2)
            for(int col = 0; col < 8; col+=2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);
        for(int row = 1; row < 8; row+=2)
            for(int col = 1; col < 8; col+=2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);

        g.setColor(Color.RED);

        for(int row = 1; row < 8; row+=2)
            for(int col = 0; col < 8; col+=2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);

        for(int row = 0; row < 8; row+=2)
            for(int col = 1; col < 8; col+=2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);



        for(int i = 0; i < 8; i++)
            for(int j = 0; j <8; j++)
                board[i][j] = new Checker();

        //checkers
        for(int row = 0; row < 1; row++)
            for(int col = 0; col < 8; col+=2){
                board[row][col].setColor(Color.BLUE);
                board[row][col].drawChecker(col, row, g);
            }

        for(int row = 1; row < 2; row++)
            for(int col = 1; col < 8; col+=2){
                board[row][col].setColor(Color.BLUE);
                board[row][col].drawChecker(col, row, g);
            }

        //checkers
        for(int row = 6; row < 7; row++)
            for(int col = 0; col < 8; col+=2){
                board[row][col].setColor(Color.GREEN);
                board[row][col].drawChecker(col, row, g);
            }
        for(int row = 7; row < 8; row++)
            for(int col = 1; col < 8; col+=2){
                board[row][col].setColor(Color.GREEN);
                board[row][col].drawChecker(col, row, g);
            }

    }

    public static void main(String [] args) {

        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Board());
        frame.pack();
        frame.setVisible(true);
        //frame.setResizable(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xPos = (e.getX()/SQRSIZE);
        int yPos = (e.getY()/SQRSIZE);

        System.out.println(xPos +" "+ yPos);

        if(choice1.x == -1){
            choice1.x = xPos;
            choice1.y = yPos;
        } else if(choice1.x == xPos && choice1.y == yPos){
            choice1.x = -1;
            choice1.y = -1;
        } else{
            choice2.x = xPos;
            choice2.y = yPos;
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

