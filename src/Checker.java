import java.awt.*;

/**
 * Created by scowley on 4/16/14.
 */
public class Checker {

    private Color color;
    private int xPos, yPos;
    private int radius;
    private boolean king, present, selected = false;

    public Checker() {

    }

    public Checker(int inX, int inY, boolean presIn) {
        //xPos = inX;
        //yPos = inY;
        color = null;
        present = presIn;
        king = false;

    }

    public Checker(int inX, int inY, int size, Color colorIn) {
        xPos = inX;
        yPos = inY;
        radius = (size - 20) / 2;
        color = colorIn;
        present = true;
        king = false;
    }

    public Checker(Point choice, int SQRSIZE, Color colorIn, boolean kingIn) {
        xPos = choice.x;
        yPos = choice.y;
        radius = (SQRSIZE - 20) / 2;
        color = colorIn;
        present = true;
        king = kingIn;


    }

    //public void setX(int xIn) { xPos = xIn; }
    //public void setY(int yIn) { yPos = yIn; }
    public void setColor(Color colorIn) {
        color = colorIn;
    }

    public Color getColor() {
        return color;
    }

    public void setSelected(boolean selectedIn) {
        selected = selectedIn;
    }

    public boolean getPresence() {
        return present;
    }

    public void setKing(boolean kingIn) { king = kingIn; }

    public boolean getKing() { return king; }

    public void drawChecker(Graphics g, int sqrsize) {
        //if (!present) {
            g.setColor(color);
            g.fillOval(sqrsize * xPos + 10, sqrsize * yPos + 10, sqrsize-20, sqrsize-20);
            if (selected) {
                g.setColor(Color.ORANGE);
                g.drawOval(sqrsize * xPos + 10, sqrsize * yPos + 10, sqrsize-20, sqrsize-20);
                g.drawOval(sqrsize * xPos + 11, sqrsize * yPos + 11, sqrsize-22, sqrsize-22);
                g.drawOval(sqrsize * xPos + 12, sqrsize * yPos + 12, sqrsize-24, sqrsize-24);
            }
            if(king){
                g.setColor(Color.ORANGE);
                g.fillOval(sqrsize * xPos + 25, sqrsize * yPos + 25, sqrsize/2-10, sqrsize/2-10);
        }
    }
}

