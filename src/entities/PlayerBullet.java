package entities;

import java.util.ArrayList;

import game.Screen;

public class PlayerBullet extends Bullet {

	public PlayerBullet(double ex, double wy) {
		super(ex, wy, "PlayerBullet.png");
	}
	public PlayerBullet(double ex, double wy, String ing) {
		super(ex, wy, ing);
	}

	@Override
	public void act() {
		move();
		if(isOnField()){
			this.die();
		}
		ArrayList<Structure> temp = new ArrayList<Structure>();
		temp.addAll(Screen.structureList);
		for (Structure s :temp) {
			if (isColliding(s)){
				s.takeDamage();
				this.die();
			}
		}
	}
	
	@Override
	public void move() {
		setY(getY()-8);
	}

}
