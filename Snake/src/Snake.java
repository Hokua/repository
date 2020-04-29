import java.awt.Color;
import java.awt.Graphics2D;
import java.util.*;

public class Snake {

    // All snake variable essentials
    private LinkedList<Position> snake = new LinkedList<Position>();
    private int headX;
    private int headY;
    private final int size = 15;
    private Color color;
    private int speedCounter = 0; // a counter. At 'speed' (below), the move function will move.
    private int speed = 3; // the speed of the snake. Higher number = slower.
    private boolean eating = false;
    private int length = 0;

    /**
     * Snake constructor: Constructs the snake object at the position it should
     * start TODO
     * 
     * @param x
     * @param y
     */
    Snake(int x, int y) {

        // Initialize the instance variables:
        headX = x;
        headY = y;
        length = 3;

        // Create and add three positions to the list
        Position pos = new Position(x - size * 2, y);
        Position pos1 = new Position(x - size, y);
        Position pos2 = new Position(x, y);
        snake.add(pos);
        snake.add(pos1);
        snake.add(pos2);

        // Use a random number to select a color (red, green or blue):
        double rand = Math.random();
        if (rand <= 0.33) {
            color = Color.blue;
        } else if (rand <= 0.66) {
            color = Color.red;
        } else {
            color = Color.green;
        }
    }

    /**
     * TODO
     * 
     * @param g
     */
    public void repaint(Graphics2D g) {

        // Set the color
        g.setColor(color);

        // Create and use an iterator to draw each chunk of the snakes body
        Iterator<Position> iter = snake.iterator();
        while (iter.hasNext()) {
            Position p = iter.next();
            int x = p.getX();
            int y = p.getY();
            g.fillRoundRect(x, y, size - 1, size - 1, 5, 5); //TODO
        }
    }

    /**
     * TODO
     * 
     * @param d
     * @param hasEaten
     * @return whether or not the snake can move there without running into itself
     */
    public boolean move(Direction d, boolean hasEaten) {

        // If the snake has eaten then set the instance variable eating to true
        if (hasEaten) {
            eating = true;
        }

        // SpeedCounter functionality + if the snake would move back into itself, just skip
        speedCounter++;
        if (speedCounter >= speed) {
            speedCounter = 0;

            // Create the next position depending on the direction indicated:
            Position p = null;
            if (d == Direction.UP) {
                p = new Position(headX, headY - size);
            } else if (d == Direction.DOWN) {
                p = new Position(headX, headY + size);
            } else if (d == Direction.LEFT) {
                p = new Position(headX - size, headY);
            } else if (d == Direction.RIGHT) {
                p = new Position(headX + size, headY);
            }
            
            // Update state variables:
            snake.add(p);
            headX = p.getX();
            headY = p.getY();

            // Remove the last thing in the set if the snake has not just eaten:
            if (!eating) {
                Iterator<Position> iter = snake.iterator();
                iter.next();
                iter.remove();
            } else {
                // If the snake has eaten the return eating to false and increment length
                eating = false;
                length++;
            }
        }
        
        return true;
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

        // Create and use an iterator
        Iterator<Position> iter = snake.iterator();
        while (iter.hasNext()) {

            // Prepare x and y values:
            Position p = iter.next();
            int xPos = p.getX();
            int yPos = p.getY();

            // If there is an intersection return true:
            if ((x + width) > (xPos) && (x) < (xPos + size) &&
                    (y + height) > (yPos) && (y) < (yPos + size)) {
                return true;
            }
        }

        // If there was no intersection return false
        return false;
    }

    /**
     * TODO
     * 
     * @param width
     * @param height
     * @return
     */
    public boolean hitWall(int width, int height) {

        // Create and use a descending iterator
        Iterator<Position> x = snake.descendingIterator();
        Position p = x.next();

        // Prepare x and y values:
        int xPos = p.getX();
        int yPos = p.getY();

        if ((xPos) > 0 && (xPos + size) < width && (yPos) > 0 && (yPos + size) < height) {
            return false;
        }

        // If the head left the boundary, return true
        return true;
    }
    
    public boolean gotClubbed() {
        if (length > 2) {
            snake.remove();
            snake.remove();
            length -= 2;
            return true;
        } else {
            length -= 2;
            return false;
        }
    }
    
    /**
     * TODO
     */
    public int getLength() {

        // Return the stored length value
        return length;
    }

    /**
     * getElements returns all the current elements in the snake LinkedList. TODO
     * 
     * @return An array list representing the snake.
     */
    public List<Position> getElements() {
        return snake; // TODO must make this function return an immutable version of snake.
    }
    
    /**
     * TODO
     */
    public void increSpeed() {
        speed--; //actually subtracting from speed because less = faster
    }
    
    /**
     * TODO
     */
    public void decreSpeed() {
        speed++; //adds to speed because more = slower
    }
    
    /**
     * Gets the current x position of the snake's head.
     * 
     * @return The x position of the snake's head.
     */
    public int getHeadX() {
        return headX;
    }
    
    /**
     * Gets the current y position of the snake's head.
     * 
     * @return The y position of the snake's head.
     */
    public int getHeadY() {
        return headY;
    }
    
    /**
     * Gets the size of the snake.
     * 
     * @return the size of the snake.
     */
    public int getSize() {
        return size;
    }
}