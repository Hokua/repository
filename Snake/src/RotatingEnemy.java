import java.awt.Color;
import java.awt.Graphics2D;

public class RotatingEnemy extends Enemies {
    
    // Extra instance variables
    private Direction d;
    private int turn = 0;
    private final int canvasWidth;
    private final int canvasHeight;
    private final int distance = 30;
    
    /**
     * RotatingEnemy constructor! This guy rotates.
     * 
     * @param life How long the enemy should live.
     * @param p Where the enemy should start.
     */
    public RotatingEnemy(int life, Position p, int canvasWidth, int canvasHeight) {
        super(life, p);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        setWidth(30);
        setHeight(30);
        setColor(Color.yellow);
        d = randDirection();
    }

    /**
     * Overrides the move method! Moves randomly (but always a different direction)...
     * It sort of rotates.
     */
    @Override
    void move(Snake snake) {
        
        // Increment turn
        turn++;
        
        // Decide what to do based on the current direction
        if (d == Direction.RIGHT) {
            step(snake, 1, 0);
        } else if (d == Direction.UP) {
            step(snake, 0, -1);
        } else if (d == Direction.LEFT) {
            step(snake, -1, 0);
        } else {
            step(snake, 0, 1);
        }
    }
    
    /**
     * Picks a random direction.
     * 
     * @return A randomly chosen direction.
     */
    public Direction randDirection() {
        double x = Math.random();
        Direction dir = null;
        if (x < 0.25) {
            dir = Direction.RIGHT;
        } else if (x < 0.5) {
            dir = Direction.UP;
        } else if (x < 0.75) {
            dir = Direction.LEFT;
        } else {
            dir = Direction.DOWN;
        }
        return dir;
    }
    
    /**
     * Finds a direction different from the current one. Resets the turn counter. Re-call the
     * move() function.
     * 
     * @param d The direction that we are currently going
     * @param snake The snake instance.
     */
    public void changeDirection(Snake snake) {
        
        // Get a new direction that is different than old one
        Direction dir = d;
        while (dir == d) {
            dir = randDirection();
        }
        
        // Make the global direction equal to this new direction
        d = dir;
        
        //Reset the turn variable
        turn = 0;
        
        //Re-call the move function
        move(snake);
    }
    
    /**
     * Move the snake in the desired direction.
     * 
     * @param snake The snake instance
     * @param xChange The change in the x direction
     * @param yChange The change in the y direction
     */
    public void step(Snake snake, int xChange, int yChange) {
        
        // Get x and y position
        int x = getPosition().getX();
        int y = getPosition().getY();
        
        // If only been a couple turns in this direction, continue.
        if (turn < 30) {
            x += xChange;
            y += yChange;
            setPosition(new Position(x, y));
            
        // If it has been that specific amount of turns, change the direction and try again
        } else {
            
            // If the enemy is too close to the edge of the screen, move them away:
            if (x < distance) {
                d = Direction.RIGHT;
                System.out.print("Too close to the left: moving " + d);
                turn = 0;
                move(snake);
            } else if (x > canvasWidth - distance) {
                d = Direction.LEFT;
                System.out.print("Too close to the right: moving " + d);
                turn = 0;
                move(snake);
            } else if (y < distance) {
                d = Direction.DOWN;
                System.out.print("Too close to the top: moving " + d);
                turn = 0;
                move(snake);
            } else if (y > canvasHeight - distance) {
                d = Direction.UP;
                System.out.print("Too close to the bottom: moving " + d);
                turn = 0;
                move(snake);
            } else {
                // If the enemy is enough in bounds then randomly change the direction
                changeDirection(snake);
            }
        }
    }
}