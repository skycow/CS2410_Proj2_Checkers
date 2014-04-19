import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skyler on 4/16/14.
 */
public class Board extends JPanel implements MouseListener {
    private final int SQRSIZE = 80;
    //private Checker[][] board = new Checker[8][8];
    private List<List<Checker>> pieces = new ArrayList<List<Checker>>();
    private Point choice1 = new Point(-1, -1);
    private Point choice2 = new Point(-1, -1);
    private Color player1 = Color.BLUE, player2 = Color.GREEN, turn = player1;
    private JLabel jla = new JLabel("hello");
    private int p1Check=8, p2Check=8;
    //private int turn = 1;

    public Board() {

        //setPreferredSize(new Dimension(640, 640));
        addMouseListener(this);
        //initalizing 2d array
        for (int i = 0; i < 8; i++) {
            List<Checker> newList = new ArrayList<Checker>();
            pieces.add(i, newList);
            for (int j = 0; j < 8; j++) {
                Checker newCheck = new Checker(j, i, false);
                pieces.get(i).add(j, newCheck);
            }
        }

        //checkers
        for (int row = 0; row < 1; row++)
            for (int col = 0; col < 8; col += 2) {
                Checker newCheck = new Checker(col, row, SQRSIZE, player1);
                pieces.get(col).set(row, newCheck);
            }

        for (int row = 1; row < 2; row++)
            for (int col = 1; col < 8; col += 2) {
                Checker newCheck = new Checker(col, row, SQRSIZE, player1);
                pieces.get(col).set(row, newCheck);
            }

        //checkers
        for (int row = 6; row < 7; row++)
            for (int col = 0; col < 8; col += 2) {
                Checker newCheck = new Checker(col, row, SQRSIZE, player2);
                pieces.get(col).set(row, newCheck);
            }
        for (int row = 7; row < 8; row++)
            for (int col = 1; col < 8; col += 2) {
                Checker newCheck = new Checker(col, row, SQRSIZE, player2);
                pieces.get(col).set(row, newCheck);
            }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //black components
        g.setColor(Color.BLACK);
        //squares
        for (int row = 0; row < 8; row += 2)
            for (int col = 0; col < 8; col += 2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);
        for (int row = 1; row < 8; row += 2)
            for (int col = 1; col < 8; col += 2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);

        g.setColor(Color.RED);
        for (int row = 1; row < 8; row += 2)
            for (int col = 0; col < 8; col += 2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);

        for (int row = 0; row < 8; row += 2)
            for (int col = 1; col < 8; col += 2)
                g.fillRect(SQRSIZE * col, SQRSIZE * row, SQRSIZE, SQRSIZE);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces.get(i).get(j).getPresence())
                    pieces.get(i).get(j).drawChecker(g, SQRSIZE);
            }
        }

        g.setColor(turn);
        g.fillOval(SQRSIZE * 8 + 10, 0, SQRSIZE - 20, SQRSIZE - 20);
    }

    public void move() {
        Checker newCheck = new Checker(choice2, SQRSIZE, pieces.get(choice1.x).get(choice1.y).getColor(), pieces.get(choice1.x).get(choice1.y).getKing());
        if (turn == player1 && choice2.y == 7 || turn == player2 && choice2.y == 0)
            newCheck.setKing(true);
        pieces.get(choice2.x).set(choice2.y, newCheck);
        Checker newCheck2 = new Checker(choice1.x, choice1.y, false);
        pieces.get(choice1.x).set(choice1.y, newCheck2);
        turn = (turn == player1) ? player2 : player1;

        choice2 = new Point(-1, -1);
    }

    /*public static void main(String[] args) {

        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Board());
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }*/

    @Override
    public void mouseClicked(MouseEvent e) {

        int xPos = (e.getX() / SQRSIZE);
        int yPos = (e.getY() / SQRSIZE);

        if (xPos < 8 && yPos < 8) {

            System.out.println(xPos + " " + yPos);

            if (choice1.x == -1) {
                if (pieces.get(xPos).get(yPos).getPresence()) {
                    if (pieces.get(xPos).get(yPos).getColor() == turn) {
                        choice1 = new Point(xPos, yPos);
                        pieces.get(choice1.x).get(choice1.y).setSelected(true);
                    }
                }
            } else if (choice1.x == xPos && choice1.y == yPos) {
                pieces.get(choice1.x).get(choice1.y).setSelected(false);
                choice1 = new Point(-1, -1);
            } else {
                if (!pieces.get(xPos).get(yPos).getPresence()) {
                    //special cases//
                    //top going down
                    if (turn == player1) {
                        if ((xPos == (choice1.x + 1) || xPos == (choice1.x - 1)) && (yPos == (choice1.y + 1) || (pieces.get(choice1.x).get(choice1.y).getKing() && yPos == (choice1.y - 1)))) {
                            choice2 = new Point(xPos, yPos);
                            move();
                            choice1 = new Point(-1, -1);
                        }
                        //check left
                        else if (xPos == (choice1.x - 2) && yPos == (choice1.y + 2)) {
                            if (pieces.get(choice1.x - 1).get(choice1.y + 1).getPresence() && pieces.get(choice1.x - 1).get(choice1.y + 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x - 1, choice1.y + 1, false);
                                pieces.get(choice1.x - 1).set(choice1.y + 1, newCheck2);
                                p1Check -= 1;
                                if (p1Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 1 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }

                                choice1 = new Point(-1, -1);
                            }
                        }
                        //check right
                        else if (xPos == (choice1.x + 2) && yPos == (choice1.y + 2)) {
                            if (pieces.get(choice1.x + 1).get(choice1.y + 1).getPresence() && pieces.get(choice1.x + 1).get(choice1.y + 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x + 1, choice1.y + 1, false);
                                pieces.get(choice1.x + 1).set(choice1.y + 1, newCheck2);
                                p1Check -= 1;
                                if (p1Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 1 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                        //king jumps backward
                        //check left
                        else if (xPos == (choice1.x - 2) && yPos == (choice1.y - 2) && pieces.get(choice1.x).get(choice1.y).getKing()) {
                            if (pieces.get(choice1.x - 1).get(choice1.y - 1).getPresence() && pieces.get(choice1.x - 1).get(choice1.y - 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x - 1, choice1.y - 1, false);
                                pieces.get(choice1.x - 1).set(choice1.y - 1, newCheck2);
                                choice1 = new Point(-1, -1);
                            }
                        }
                        //check right
                        else if (xPos == (choice1.x + 2) && yPos == (choice1.y - 2) && pieces.get(choice1.x).get(choice1.y).getKing()) {
                            if (pieces.get(choice1.x + 1).get(choice1.y - 1).getPresence() && pieces.get(choice1.x + 1).get(choice1.y - 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x + 1, choice1.y - 1, false);
                                pieces.get(choice1.x + 1).set(choice1.y - 1, newCheck2);
                                p1Check -= 1;
                                if (p1Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 1 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                    }
                    //bottom going up
                    else if (turn == player2) {
                        if ((xPos == (choice1.x + 1) || xPos == (choice1.x - 1)) && (yPos == (choice1.y - 1) || (pieces.get(choice1.x).get(choice1.y).getKing() && yPos == (choice1.y + 1)))) {
                            choice2 = new Point(xPos, yPos);
                            move();
                            choice1 = new Point(-1, -1);
                        }
                        //check left
                        else if (xPos == (choice1.x - 2) && yPos == (choice1.y - 2)) {
                            if (pieces.get(choice1.x - 1).get(choice1.y - 1).getPresence() && pieces.get(choice1.x - 1).get(choice1.y - 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x - 1, choice1.y - 1, false);
                                pieces.get(choice1.x - 1).set(choice1.y - 1, newCheck2);
                                p2Check -= 1;
                                if (p2Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 2 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                        //check right
                        else if (xPos == (choice1.x + 2) && yPos == (choice1.y - 2)) {
                            if (pieces.get(choice1.x + 1).get(choice1.y - 1).getPresence() && pieces.get(choice1.x + 1).get(choice1.y - 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x + 1, choice1.y - 1, false);
                                pieces.get(choice1.x + 1).set(choice1.y - 1, newCheck2);
                                p2Check -= 1;
                                if (p2Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 2 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                        //backwards king jumps
                        //check left
                        else if (xPos == (choice1.x - 2) && yPos == (choice1.y + 2) && pieces.get(choice1.x).get(choice1.y).getKing()) {
                            if (pieces.get(choice1.x - 1).get(choice1.y + 1).getPresence() && pieces.get(choice1.x - 1).get(choice1.y + 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x - 1, choice1.y + 1, false);
                                pieces.get(choice1.x - 1).set(choice1.y + 1, newCheck2);
                                p2Check -= 1;
                                if (p2Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 2 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                        //check right
                        else if (xPos == (choice1.x + 2) && yPos == (choice1.y + 2) && pieces.get(choice1.x).get(choice1.y).getKing()) {
                            if (pieces.get(choice1.x + 1).get(choice1.y + 1).getPresence() && pieces.get(choice1.x + 1).get(choice1.y + 1).getColor() != turn) {
                                choice2 = new Point(xPos, yPos);
                                move();
                                Checker newCheck2 = new Checker(choice1.x + 1, choice1.y + 1, false);
                                pieces.get(choice1.x + 1).set(choice1.y + 1, newCheck2);
                                p2Check -= 1;
                                if (p2Check == 0){
                                    JOptionPane.showMessageDialog(null, "Player 2 wins!", "Winner", JOptionPane.OK_OPTION);
                                    System.exit(0);
                                }
                                choice1 = new Point(-1, -1);
                            }
                        }
                    }
                    //end special cases
                }
            }
            repaint();
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

