import java.awt.Color;

public class TrackingEnemy extends Enemies {
    
    int begin = 0;
    
    public TrackingEnemy(int life, Position p) {
        super(life, p);
        setWidth(15);
        setHeight(15);
        setColor(Color.green);
    }

    @Override
    void move(Snake snake) {
        
        if (begin > 30) {
                
            //Get the snake's head's x and y positions
            int x = snake.getHeadX();
            int y = snake.getHeadY();
            
            // Get the current enemy instance's x and y positions
            int xPos = getPosition().getX();
            int yPos = getPosition().getY();
            
            // Move the x position of the enemy towards that of the snake
            if (x > xPos) {
                xPos++;
            } else if (x < xPos) {
                xPos--;
            }
            
            // Move the y position of the enemy towards that of the snake
            if (y > yPos) {
                yPos += 2;
            } else if (y < yPos) {
                yPos -= 2;
            }
            
            // Set the position to the new position
            setPosition(new Position(xPos, yPos));
            
        } else {
            begin++;
        }
        
    }

    /**
     * Overrides the repaint function of the enemies abstract.
     * 
     * @param g A Graphics2D context to draw the enemy in.
     */
//    @Override
//    public void repaint(Graphics2D g) {
//        //TODO remove if I don't add to this.
//        
//    }
}