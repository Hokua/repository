abstract class EnemyAbstract extends GameObj {
	
	private int age = 0;
	private int life;
	
	/*
	 * Enemy Abstract Constructor: Before an enemy is created, positions will be checked that
	 * they are not within the body of the snake or anything else. This initializes the original
	 * position to something that takes that into account.
	 */
	EnemyAbstract(int vx, int vy, int px, int py, int width, int height, int courtWidth,
	        int courtHeight) {
		super(vx, vy, px, py, width, height, courtWidth, courtHeight);
	}
	
	/**
	 * Move method: moves the enemy as desired.
	 * 
	 */
	public void move() {
		
	}
	
	/**
	 * Repaint method: Repaints the enemy as desired.
	 */
	public void draw() {
		
	}
	
	/** 
	 * Interact method: defines how the enemy will interact with the player.
	 * (Examples: no interaction, slows the snake down, speeds the snake up,
	 * removes half of snake's length, ends the game, etc.)
	 */
	public void interact() {
		
	}
	
	/**
	 * Die method: this method determines what removes an enemy from the arena.
	 */
	public void die() {
		age++;
		if (age == life) {
			
		}
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
}