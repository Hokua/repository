
/**
 * 
 * @author hokuatarnas
 *
 */
public class Food {
	
	//Instance variables:
	int xPos;
	int yPos;
	int number = 0; //the number of food chunks that have been eaten
	
	Food(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void eaten(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		number++;
	}
	
	public void repaint() {
		
	}
}