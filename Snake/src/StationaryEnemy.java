import java.awt.Graphics;

class StationaryEnemy extends EnemyAbstract {
	
	StationaryEnemy(int vx, int vy, int px, int py, int width, int height, int courtWidth,
	        int courtHeight) {
		super(vx, vy, px, py, width, height, courtWidth, courtHeight);
		setLife(500);
	}
	
	/**
	 * Overrides the draw() method in GameObj abstract w/ a given graphics context.
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
}