import javax.swing.*;
import java.awt.*;

/**
 * Created by Skyler on 4/17/14.
 */
public class CheckersGame extends JFrame {

    private static final int WINDOW_WIDTH = 750;
    private static final int WINDOW_HEIGHT = 700;

    public CheckersGame() {
        super("Checkers!");

        Board panel = new Board();
        JLabel player1 = new JLabel("Player 1");

        Container pane = getContentPane();
        pane.setLayout(null);

        //panel.setSize(640, 640);
        panel.setSize(720, 720);
        player1.setSize(60, 25);

        panel.setLocation(0,0);
        player1.setLocation(640, 80);

        pane.add(player1);
        pane.add(panel);




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
    }


    public static void main(String[] args) {
        new CheckersGame();
    }
}
