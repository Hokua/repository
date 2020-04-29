
/**
 * 
 * @author hokuatarnas
 * TODO
 */

import java.awt.*;
import java.awt.event.*;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

/**
 * Arena
 * 
 * The main game logic file. All of the interactions between objects will be
 * done here.
 */
@SuppressWarnings("serial")
public class Arena extends JPanel {

    // Movement and interaction specific variables:
    public static final int ARENA_WIDTH = 750;
    public static final int ARENA_HEIGHT = 600;
    private Direction d = Direction.RIGHT;
    private Position foodPos;

    // Main game objects:
    private Snake snake;
    private Food food = new Food(new Position(ARENA_WIDTH / 2, ARENA_HEIGHT / 2));
    private ArrayList<Enemies> enemies = new ArrayList<Enemies>();

    // Game play and swing variables:
    private boolean inPlay = false;
    private JLabel score;
    private static int interval = 30; // left mutable so speed can be changed in future adaptations

    /**
     * Arena Constructor: sets up all the necessary game components
     * 
     * @param score The score JLabel
     */
    Arena(JLabel score) {

        // JPanel work
        this.score = score;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Timer timer = new Timer(interval, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();

        // Keyboard functionality
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && d != Direction.RIGHT) {
                    d = Direction.LEFT;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && d != Direction.LEFT) {
                    d = Direction.RIGHT;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && d != Direction.UP) {
                    d = Direction.DOWN;
                } else if (e.getKeyCode() == KeyEvent.VK_UP && d != Direction.DOWN) {
                    d = Direction.UP;
                }
            } //Future adaptations: can add a "press space = shoot laser" option
        });

    }

    /**
     * Reset method: resets all states so the game can be played from the start.
     */
    public void reset() {

        // Reset game variables
        d = Direction.RIGHT;
        inPlay = true;
        snake = new Snake((int) (.5 * ARENA_WIDTH), (int) (.5 * ARENA_HEIGHT));
        foodPos = randomInBoundsPos(food.getWidth(), food.getWidth()); // snake must be initialized
        food = new Food(foodPos); // foodPos must be initialized
        enemies.clear(); //make sure old enemies are no longer in the list
        for (int i = 0; i < 3; i++) {
            enemies.add(nextEnemy()); //Add three enemies
        }

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    /**
     * TODO
     */
    public void tick() {
        if (inPlay) {

            // Food functionality
            boolean ateFood = snake.intersect(foodPos.getX(), foodPos.getY(), 15, 15);
            if (ateFood) {
                foodPos = randomInBoundsPos(food.getWidth(), food.getWidth());
                food.eaten(foodPos);
            }
            food.shrink();
            
            // Snake functionality
            inPlay = !snake.hitWall(ARENA_WIDTH, ARENA_HEIGHT);
            if (inPlay) {
                inPlay = snake.move(d, ateFood);
            }
            
            //Enemies functionality
            int removed = 0;
            Iterator<Enemies> iter = enemies.iterator();
            while (iter.hasNext()) {
                Enemies temp = iter.next();
                temp.move(snake);
                if (temp.die(snake)) {
                    iter.remove();
                    removed++;
                }
                if (temp.interact(snake)) {
                    
                    // If this is the stationary enemy, club the snake
                    if (temp instanceof StationaryEnemy) {
                        if (!snake.gotClubbed()) {
                            inPlay = false;
                        }
                    
                    // If this is any other type of enemy, you die
                    } else {
                        inPlay = false;
                    }
                }
                //TODO
            }
            
            //Add the amount of enemies that were removed
            for (int i = 0; i < removed; i++) {
                enemies.add(nextEnemy());
            }

            // Score
            score.setText("Length = " + snake.getLength());
            
            // Repaint:
            repaint();
            
        } else {
            score.setText("You died... Your score was " +
                snake.getLength() + "! Beat it next time!");
            repaint();
        }
    }

    /**
     * TODO
     */
    @Override
    public void paintComponent(Graphics g) {
        if (inPlay) {
            super.paintComponent(g);
    
            // Create the 2D graphics context:
            Graphics2D newG = (Graphics2D) g;
    
            // Draw the field:
            newG.setColor(Color.darkGray);
            newG.fillRect(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
            newG.setColor(Color.black);
            newG.drawRect(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
    
            // Draw all game components:
            snake.repaint(newG);
            food.repaint(g);
            Iterator<Enemies> iter = enemies.iterator(); //TODO finish this part
            while (iter.hasNext()) {
                iter.next().repaint(newG);
            }
        } else {
            g.setColor(Color.black);
            g.fillRect(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
            g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(g.getFont().getSize() * 5F));
            g.drawString("Game Over", 125, 200);
        }
    }

    /**
     * TODO
     */
    @Override
    public Dimension getPreferredSize() {

        // Return the dimension of this JPanel
        return new Dimension(ARENA_WIDTH, ARENA_HEIGHT);
    }

    /**
     * Creates a random position where something can be placed without intersecting
     * the snake object.
     * 
     * @param width An integer value that indicates the width of a particular item
     * @return a Position (with x and y integers values)
     */
    private Position randomInBoundsPos(int width, int height) {

        // Create two new points that would keep the full object within bounds
        int x = (int) (Math.random() * (ARENA_WIDTH - width));
        int y = (int) (Math.random() * (ARENA_HEIGHT - height));
        
        // Under the assumption that we have 3 enemies in the list
        Iterator<Enemies> iter = enemies.iterator();
        if (iter.hasNext()) { 
            Enemies e1 = iter.next();
            if (iter.hasNext()) {
                Enemies e2 = iter.next();
                if (iter.hasNext()) {
                    Enemies e3 = iter.next();
                    
                    // Save snake variables for ease of use
                    int snakeX = snake.getHeadX();
                    int snakeY = snake.getHeadY();
                    int snakeSize = snake.getSize();
                    
                    // Check if either the snake or food currently occupies this location
                    // or if the location is in front of the snake
                    while (e1.intersect(x, y, width, height) || e2.intersect(x, y, width, height) ||
                            e3.intersect(x, y, width, height) ||
                            snake.intersect(x, y, width, height) ||
                            food.intersect(x, y, width, height) ||
                            (d == Direction.RIGHT && x > snakeX &&
                            y > snakeY - height && y < snakeY + snakeSize) ||
                            (d == Direction.LEFT && x < snakeX &&
                            y > snakeY - height && y < snakeY + snakeSize) ||
                            (d == Direction.UP && y < snakeY) &&
                            x > snakeX + snakeSize && x < snakeX - width ||
                            (d == Direction.DOWN && y > snakeY) &&
                            x > snakeX + snakeSize && x < snakeX - width) {
            
                        // Create two new points that would keep the full object within bounds
                        x = (int) (Math.random() * (ARENA_WIDTH - width));
                        y = (int) (Math.random() * (ARENA_HEIGHT - height));
                    }
                }
            }
        }

        // Return the functioning Position:
        return new Position(x, y);
    }
    
    private Enemies nextEnemy() {
        
        int life = (int) (Math.random() * 50 + 50);
        
        // Randomly choose what enemies will start:
        double dieRoll = Math.random();
        if (dieRoll < 0.33) {
            
            // Create a stationary enemy
            StationaryEnemy enemy = new StationaryEnemy(life, new Position(0, 0));
            Position p = randomInBoundsPos(enemy.getWidth(), enemy.getHeight());
            enemy.setPosition(p);
            return enemy;
            
        } else if (dieRoll < 0.66) {
            
            // Create a rotating enemy
            RotatingEnemy enemy = new RotatingEnemy(6 * life, new Position(0, 0),
                    ARENA_WIDTH, ARENA_HEIGHT);
            Position p = randomInBoundsPos(enemy.getWidth(), enemy.getHeight());
            enemy.setPosition(p);
            return enemy;
            
            
        } else {
            
            // Create a tracking enemy
            TrackingEnemy enemy = new TrackingEnemy(10 * life + 30, new Position(0, 0));
            Position p = randomInBoundsPos(enemy.getWidth(), enemy.getHeight());
            enemy.setPosition(p);
            return enemy;
            
            
        }
    }
}