import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skyler on 4/16/14.
 */
public class Board extends JPanel implements MouseListener{
    private final int SQRSIZE = 80;
    private Checker [] [] board = new Checker [8] [8];
    private List <List<Checker>> pieces = new ArrayList<List<Checker>>();
    private Point choice1 = new Point(-1, -1);
    private Point choice2 = new Point(-1, -1);

    public Board(){
        setPreferredSize(new Dimension(640, 640));
        addMouseListener(this);
        //initalizing 2d array
        for(int i = 0; i<8;i++){
            List<Checker> newList = new ArrayList<Checker>();
            pieces.add(i, newList);
            for(int j = 0; j<8; j++){
                Checker newCheck = new Checker(false);
                pieces.get(i).add(j, newCheck);
            }
        }

        //checkers
        for(int row = 0; row < 1; row++)
            for(int col = 0; col < 8; col+=2){
                //board[row][col].setColor(Color.BLUE);
                //board[row][col].drawChecker(col, row, g);
                Checker newCheck = new Checker(col, row, SQRSIZE, Color.BLUE);
                pieces.get(col).add(row, newCheck);
            }

        for(int row = 1; row < 2; row++)
            for(int col = 1; col < 8; col+=2){
                //board[row][col].setColor(Color.BLUE);
                //board[row][col].drawChecker(col, row, g);
                Checker newCheck = new Checker(col, row, SQRSIZE, Color.BLUE);
                pieces.get(col).add(row, newCheck);
            }

        //checkers
        for(int row = 6; row < 7; row++)
            for(int col = 0; col < 8; col+=2){
                //board[row][col].setColor(Color.GREEN);
                //board[row][col].drawChecker(col, row, g);
                Checker newCheck = new Checker(col, row, SQRSIZE, Color.GREEN);
                pieces.get(col).add(row, newCheck);
            }
        for(int row = 7; row < 8; row++)
            for(int col = 1; col < 8; col+=2){
                //board[row][col].setColor(Color.GREEN);
                //board[row][col].drawChecker(col, row, g);

                Checker newCheck = new Checker(col, row, SQRSIZE, Color.GREEN);
                pieces.get(col).add(row, newCheck);
            }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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

        //for(int i = 0; i < 8; i++)
            //for(int j = 0; j <8; j++)
                //board[i][j] = new Checker();

        /////////////////////////

        //System.out.println("test");

        for(int i = 0; i<8;i++){
            //List<Checker> newList = new ArrayList<Checker>();
            //pieces.get(i);
            //System.out.println("test2");
            for(int j = 0; j<8; j++){
                //System.out.println("test3");
                pieces.get(i).get(j).drawChecker(g);
            }
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
            System.out.println("testpoint3");
            choice1 = new Point(xPos, yPos);
            //choice1.x = xPos;
            //choice1.y = yPos;

        } else if(choice1.x == xPos && choice1.y == yPos){
            System.out.println("testpoint2");
            choice1 = new Point(-1, -1);
            //choice1.x = -1;
            //choice1.y = -1;
        } else{
            choice2 = new Point(xPos, yPos);
            //choice2.x = xPos;
            //choice2.y = yPos;


//            Checker temp1 = pieces.get(choice1.x).get(choice1.y);
//            Checker temp2 = pieces.get(choice2.x).get(choice2.y);
//
//            pieces.get(choice1.x).add(choice1.y, temp2);
//            pieces.get(choice2.x).add(choice2.y, temp1);


            //Checker newCheck = pieces.get(choice1.x).remove(choice1.y);

            //pieces.get(choice2.x).add(choice2.y, newCheck);
            Checker newCheck = new Checker(choice2, SQRSIZE, pieces.get(choice1.x).get(choice1.y).getColor());
            //pieces.get(choice2.x).set(choice2.y, pieces.get(choice1.x).remove(choice1.y));
            pieces.get(choice2.x).set(choice2.y, newCheck);
            Checker newCheck2 = new Checker(false);
            pieces.get(choice1.x).set(choice1.y, newCheck2);

            System.out.println("testpoint1");
            choice1.x = -1;
            choice1.y = -1;
            choice2.x = -1;
            choice2.y = -1;

        }
        repaint();

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

