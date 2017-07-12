package entities;

import java.util.ArrayList;

import game.Screen;

public class MiniBoss extends Structure {
	
	public long internalFrame = 1;

	public MiniBoss() {
		super(Screen.width/2, 0, "MiniBoss.png");
		setHealth(2000);
	}

	@Override
	public void act() {
		internalFrame++;
		move();
	}
	public void move() {
		shoot();
		if (getY() < 100){
			setY(getY()+1);
		} else {
			
			
		}
	}
	public void die(){
		super.die();
		Screen.bulletList = new ArrayList<Bullet>();
		Screen.you.victory();
	}

	private void shoot() {
		if (getHealth() > 1500){
			if (internalFrame % 5 == 0){
				for (int i = 1; i <= 8; i++){
					Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians((internalFrame%360)-(360/8*i)),1));
				}
			}
		}
		if (getHealth() <= 1500 && getHealth() > 1000){
				Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians(internalFrame%360),1));
		}
		if (getHealth() <= 1000 && getHealth() > 500 && internalFrame % 4 == 0){
			for (int i = 1; i <= 8; i++){
				Screen.bulletList.add(new StraightBullet(getX(),getY(),Math.toRadians((internalFrame%360)-(360/8*i)),2));
			}
		}
		if (getHealth() <= 500){
			Screen.bulletList.add(new StraightBullet(Math.random()*Screen.width,0,Math.toRadians(0),2));
		}
	}

}
