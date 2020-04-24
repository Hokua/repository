/**
 * 
 * @author hokuatarnas
 * TODO
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Arena
 * 
 * The main game logic file. All of the interactions between objects will be done here.
 */
@SuppressWarnings("serial")
public class Arena extends JPanel {
	
	//The actual arena
	private Tile[][] field;
	
	//Create instances of game objects
	private Snake snake;
	private Food food;
	
	
	/**
	 * Arena Constructor: sets up the 2D array of tiles, the snakes and any starter enemies (TODO).
	 * 
	 * @param size The height and width of the field
	 */
	Arena(int size) {
		
		//Initialize an array of empty tiles:
		field = new Tile[size][size];
		for(int cols = 0; cols < size; ++cols) {
			for (int rows = 0; rows < size; ++rows) {
				field[cols][rows] = Tile.EMPTY;
			}
		}
		
		
		snake= new Snake(size / 2, size / 2);
		
		
	}
}