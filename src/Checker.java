import java.awt.*;

/**
 * Created by scowley on 4/16/14.
 */
public class Checker {

    private Color color;


    public Checker(){

        color = Color.WHITE;
    }

    public Checker(Color colorIn){
        color = colorIn;

    }

    public void setColor(Color value) {
        color = value;
    }

    public void drawChecker(int xPos, int yPos, Color colorIn, Graphics g){
        g.setColor(colorIn);
        g.fillOval(80 * xPos +10, 80 * yPos +10, 60, 60);

    }
}

