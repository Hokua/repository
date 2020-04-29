import java.awt.Color;
import java.awt.Graphics2D;

abstract class Enemies {

    private int age = 0;
    private int life;
    private Position p;
    private int width = 10;
    private int height = 10;
    private Color color = Color.black;

    /*
     * Enemy Abstract Constructor: Before an enemy is created, positions will be
     * checked that they are not within the body of the snake or anything else. This
     * initializes the original position to something that takes that into account.
     */
    Enemies(int life, Position p) {
        this.life = life;
        this.p = p;
    }

    /**
     * Move method: moves the enemy as desired. Unimplemented.
     * Multiple enemies may move in relation to the snake. So:
     * 
     * @param snake An instance of the snake class.
     */
    abstract void move(Snake snake);

    /**
     * Repaint method: Repaints the enemy as desired. Unimplemented.
     * 
     * @param g A graphics 2d that allows the enemy to be drawn in the current 
     * graphics context.
     */
    public void repaint(Graphics2D g) {
        
        // Draw the enemy
        g.setColor(color);
        g.drawRect(p.getX(), p.getY(), width, height);
    }

    /**
     * Interact method: defines how the enemy will interact with the player.
     * (Examples: no interaction, slows the snake down, speeds the snake up, removes
     * half of snake's length, ends the game, etc.)
     * 
     * @return A boolean indicating whether this enemy has interacted with a given
     * instance of the snake.
     */
    public boolean interact(Snake snake) {
        
        // Can alter the snake (should be mutable by reference).
        // Go-to action will be to return true if touching the snake:
        return snake.intersect(p.getX(), p.getY(), getWidth(), getHeight());
    }

    /**
     * Die method: this method determines what removes an enemy from the arena.
     * 
     * @return A boolean of whether the enemy has aged sufficiently to die.
     */
    public boolean die(Snake snake) {
        age++;
        if (age >= life) {
            return true;
        }
        return false;
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
        
        // Prepare x and y values:
        int xPos = p.getX();
        int yPos = p.getY();

        // If there is an intersection return true:
        if ((x + width) > (xPos) && (x) < (xPos + this.width) &&
                (y + height) > (yPos) && (y) < (yPos + this.height)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the width of the implemented enemy.
     * 
     * @return The width of this instance.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Sets the width of this instance.
     * 
     * @param width The new width.
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Returns the position of the implemented enemy.
     * 
     * @return The height of this instance.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets the height of this instance.
     * 
     * @param height The new height.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Returns the position of the implemented enemy.
     * 
     * @return The position of this instance.
     */
    public Position getPosition() {
        return p;
    }
    
    /**
     * Sets the position of this instance.
     * 
     * @param p The new position.
     */
    public void setPosition(Position p) {
        this.p = p;
    }
    
    /**
     * Returns the color of the implemented enemy.
     * 
     * @return The color of this instance.
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Sets the color of this instance.
     * 
     * @param color The new Color.
     */
    public void setColor(Color color) {
        this.color = color;
    }
}