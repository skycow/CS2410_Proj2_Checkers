import java.awt.*;

/**
 * Created by scowley on 4/16/14.
 */
public class Checker {

    private Color color;
    private int xPos, yPos;


    public Checker(){

    }

    public Checker(Color colorIn){
        color = colorIn;

    }

    //public void setX(int xIn) { xPos = xIn; }
    //public void setY(int yIn) { yPos = yIn; }
    public void setColor(Color colorIn) { color = colorIn; }

    public void drawChecker(int col, int row, Graphics g){
        g.setColor(color);
        g.fillOval(80 * col +10, 80 * row +10, 60, 60);

    }
}

