import java.awt.Graphics2D;

class StationaryEnemy extends Enemies {

    StationaryEnemy(int life, Position p) {
        super(life, p);
        setWidth(91);
        setHeight(91);
    }

    /**
     * The necessary override of the abstract function 
     */
    @Override
    void move(Snake snake) {
        // Stationary Enemy does not move.
    }

    /**
     * Draws the enemy as a TODO
     * 
     * @param g The Graphics2D context used to draw the enemy.
     */
    @Override
    public void repaint(Graphics2D g) {
        
        // Get the position
        Position p = getPosition();
        
        // Draw the enemy TODO: remove this function if I don't add to it.
        g.setColor(getColor());
        g.drawRect(p.getX(), p.getY(), getWidth(), getHeight());
        
    }
    
    /**
     * If this enemy dies (which happens if the snake interacts with the
     * enemy), the snake should lose 3 pieces of its tail 
     * 
     * @param snake An instance of the snake class.
     */
    @Override
    public boolean die(Snake snake) {
        return interact(snake);
    }
}