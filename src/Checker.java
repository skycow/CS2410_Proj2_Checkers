import java.awt.*;

/**
 * Created by scowley on 4/16/14.
 */
public class Checker {

    private Color color;
    private int xPos, yPos;
    private int radius;
    private boolean king, present;

    public Checker(){

    }

    public Checker(boolean presIn){

        present = presIn;
    }

    public Checker(int inX, int inY, int size, Color colorIn){
        xPos = inX;
        yPos = inY;
        radius = (size -20)/2;
        color = colorIn;
    }

    public Checker(Color colorIn){
        color = colorIn;

    }

    public Checker(Point choice, int SQRSIZE, Color colorIn){
        xPos = choice.x;
        yPos = choice.y;
        radius = (SQRSIZE - 20)/2;
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

