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

    }

    public Checker(int inX, int inY, int size, Color colorIn) {
        xPos = inX;
        yPos = inY;
        radius = (size - 20) / 2;
        color = colorIn;
        present = true;
    }

    public Checker(Point choice, int SQRSIZE, Color colorIn) {
        xPos = choice.x;
        yPos = choice.y;
        radius = (SQRSIZE - 20) / 2;
        color = colorIn;
        present = true;


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

    public void drawChecker(Graphics g) {
        //if (!present) {
            g.setColor(color);
            g.fillOval(80 * xPos + 10, 80 * yPos + 10, 60, 60);
            if (selected) {
                g.setColor(Color.ORANGE);
                g.drawOval(80 * xPos + 10, 80 * yPos + 10, 60, 60);
                g.drawOval(80 * xPos + 11, 80 * yPos + 11, 58, 58);
                g.drawOval(80 * xPos + 12, 80 * yPos + 12, 56, 56);
                //king
                //g.fillOval(80 * xPos + 25, 80 * yPos + 25, 30, 30);
            //}
        }
    }
}

