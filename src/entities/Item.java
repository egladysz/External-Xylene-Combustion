package entities;

import game.Screen;

public abstract class Item extends Entity{
	
	public double speed = -2;

	public Item(double ex, double wy, String ing) {
		super(ex, wy, ing);
	}

	@Override
	public void act() {
		move();
		if (this.isColliding()){
			giveStat();
			this.die();
		}
	}
	
	public abstract void giveStat();

	public boolean isColliding(){
		return Math.sqrt(Math.pow(this.getX()-Screen.you.getX(),2) + Math.pow(this.getY()-Screen.you.getY(),2))<(this.getDepth()+Screen.you.getWidth());
	}
	
	@Override
	public void move() {
		setY(getY()+speed);
		if (speed < 2) speed += .25;
	}
	
}
