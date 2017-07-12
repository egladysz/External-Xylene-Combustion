package entities;

import game.Window;

public class StraightBullet extends Bullet{

	public StraightBullet(double d, double e) {
		super(d, e, "RedEnemyBulletSmall.png");
	}

	public StraightBullet(double d, double e, double angleTo, int i) {
		super(d, e, "RedEnemyBulletSmall.png");
		angle = angleTo;
		setSpeed(i);
	}

	public void act() {
		move();
		if (getY() > Window.display.getHeight()*2 + this.getHeight()*2) die();
	}

	@Override
	public void move() {
		setX(getX()+ Math.sin(angle)*speed);
		setY(getY()+ Math.cos(angle)*speed);
		if (isOffField()) this.die();
	}
	public void setAngle(double ang){
		angle = ang;
	}
	public double getAngle(){
		return angle;
	}
	
	public void setSpeed(double spd){
		speed = spd;
	}
	public double getSpeed(){
		return speed;
	}

}
