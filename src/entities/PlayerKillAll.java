package entities;

import game.Screen;

public class PlayerKillAll extends PlayerBullet{
	
	public int internalClock1 = 0;
	
	public PlayerKillAll(double x, double y) {
		super(x,y,"BlueEnemyBullet.png");
		angle = getAngleTo(Math.random()*Screen.width,Math.random()*Screen.height);
		speed = 8;
		this.setDepth(getWidth()/2);
		setX(x);
		setY(y);
	}
	
	public void move(){
		internalClock1++;
		setX(getX()+ Math.sin(angle)*speed);
		setY(getY()+ Math.cos(angle)*speed);
		if(internalClock1 > 120) this.die();
	}
}
