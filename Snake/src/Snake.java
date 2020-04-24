import java.awt.Graphics;
import java.util.*;

public class Snake {
	
	//A list of the snake body parts:
	Map<int[], Direction> snake = new TreeMap<int[], Direction>();
	
	/*
	 * Snake constructor: Constructs the snake object:
	 */
	Snake(int xPos, int yPos) {
		int[] x = {xPos, yPos};
		snake.put(x, Direction.RIGHT);
		//TODO
	}

	/*
	 * Repaint method:
	 */
	public void repaint(Graphics g) {
	}
	
}