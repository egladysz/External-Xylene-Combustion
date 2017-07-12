package entities;


import game.Screen;
import game.Window;


public abstract class Enemy extends Ship{
	
	public String bulletType = "RedEnemyBulletSmall.bmp";
	
	public Enemy(double ex, double wy, String ing) {
		super(ex, wy, ing);
		//targetLocation(getScreen().getPlayer().getX(),getScreen().getPlayer().getY());
	}
	
	public Screen getScreen() {
		return Window.display;
	}
	
	public abstract void act();
}
