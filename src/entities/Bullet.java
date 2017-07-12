package entities;
import game.*;
public abstract class Bullet extends Entity {
	
	public double speed;
	public double angle;
	
	public Bullet(double d, double e, String ing) {
		super(d, e, ing);
	}
	public boolean isOnField(){
		return (getX() + getDepth() < 0)||(getX() - getDepth() > Screen.width)||(getY() - getDepth() > Screen.height);
	}
}
