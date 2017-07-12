package entities;



public abstract class Structure extends Entity {
	
	private int health;
	
	public double speed, angle;
	
	public Structure(double ex, double wy, String ing) {
		super(ex, wy, ing);
		speed = 0;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int heal) {
		health = heal;
	}
	
	public void takeDamage(){
		health--;
		if (checkLife()) {
			
			die();
		}
	}
	
	private boolean checkLife() {
		return (health < 1);
	}
	public void move(){
		setX(getX()+ Math.sin(angle)*speed);
		setY(getY()+ Math.cos(angle)*speed);
	}
	
}
