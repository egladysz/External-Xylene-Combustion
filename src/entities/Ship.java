package entities;



public abstract class Ship extends Structure {
	
	public double targetX, targetY;
	
	public Ship(double ex, double wy, String ing) {
		super(ex, wy, ing);
	}
	
	
	public abstract void shoot();
	
	
	public void targetLocation(double ex, double wy){
		targetX = ex;
		targetY = wy;
		angle = Math.atan2(targetX - getX(), targetY - getY());
		//angle = Math.toRadians(Math.toDegrees(angle) + 90);
	}
}
