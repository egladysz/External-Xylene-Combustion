package entities;

import game.*;

public class Meteor extends Structure{
	
	public long demoInternalClock;
	
	public Meteor(double ex) {
		super(ex, -32, "Enemy1.png");
		demoInternalClock = 0;
		speed = (Math.random()*4 + 1)*60/Game.FPS;
		angle = Math.toRadians(145 + Math.random()*90);
	}
	public Meteor(double ex, double spd) {
		super(ex, -32, "Enemy1.png");
		speed = spd*30/Game.FPS;
		//angle = Math.toRadians(145 + Math.random()*90);
		angle = Math.toRadians(0);
	}
	
	public void act() {
		move();
		demoInternalClock++;
		if (demoInternalClock == 80){
			demoInternalClock = 0;
			shoot();
		}
		if (getY() > Window.display.getHeight()*2 + this.getHeight()*2) die();
	}

	private void shoot() {
		Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians(Math.toDegrees(getAngleTo(Screen.you))),2));
		Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians(Math.toDegrees(getAngleTo(Screen.you))-15),2));
		Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians(Math.toDegrees(getAngleTo(Screen.you))+15),2));
	}
	@Override
	public void move() {
		setX(getX()+ Math.sin(angle)*speed);
		setY(getY()+ Math.cos(angle)*speed);
		if(isOffField())this.die();
		//targetLocation(targetX, targetY);
	}
}
