import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

/**
 * @author hokuatarnas
 *
 */
public class Food {

    // Instance variables:
    private int x;
    private int y;
    private int eaten = 0;
    private int width = 15;

    /**
     * TODO
     * 
     * @param p
     */
    Food(Position p) {
        x = p.getX();
        y = p.getY();
    }

    /**
     * TODO
     * 
     * @param p
     */
    public void eaten(Position p) {
        x = p.getX();
        y = p.getY();
        eaten++;
    }

    /**
     * TODO
     * 
     * @param g
     */
    public void repaint(Graphics g) {
        g.setColor(Color.pink);
        g.fillOval(x, y, width, width);
    }

    /**
     * TODO
     * 
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * TODO
     */
    public void shrink() {

        // If the snake has eaten 10 pieces of food since the last increase
        if (eaten >= 10) {
            eaten = 0;

            // Decrease the food size
            if ( width >= 5) {
                width -= 1;
            }
        }
    }
    
    /**
     * TODO
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public boolean intersect(int x, int y, int width, int height) {

        // If there is an intersection return true:
        if ((x + width) > (this.x) && (x) < (this.x + this.width) &&
                (y + height) > (this.y) && (y) < (this.y + this.width)) {
            return true;
        }
        
        return false;
    }
}