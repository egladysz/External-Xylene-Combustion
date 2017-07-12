package entities;

import game.Game;
import game.Screen;


public class Enemy2 extends Enemy{
	
	public long demoInternalClock = 0;
	
	public Enemy2(double ex, double wy) {
		super(ex, wy, "Enemy2.png");
		speed = 60/Game.FPS;
		setHealth(4);
	}
	
	public void act() {
		move();
		demoInternalClock++;
		if (demoInternalClock == 60) shoot();
	}

	@Override
	public void move() {
		setX(getX()+ Math.sin(angle)*speed);
		setY(getY()+ Math.cos(angle)*speed);
		try {
			targetLocation(getScreen().getPlayer().getX(),getScreen().getPlayer().getY());
		} finally{
			
		}
	}

	@Override
	public void shoot() {
		for (int i = 0; i < 8; i++){
			Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians(Math.toDegrees(getAngleTo(Screen.you))+(i*360/8)),2));
		}
		demoInternalClock = 0;
	}
}
