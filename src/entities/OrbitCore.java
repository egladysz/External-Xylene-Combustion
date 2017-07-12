package entities;

import game.*;

public class OrbitCore extends Enemy{

	public Structure mom;
	public double distance;
	
	public OrbitCore(Structure e, double dst, double spd) {
		super(e.getX(),e.getY() - dst, "Enemy1.png");
		mom = e;
		speed = spd*((double)(15)/Game.FPS);
		distance = dst;
	}

	@Override
	public void act() {
		if (Screen.structureList.indexOf(mom) > -1){
			move();
			if (Math.random()*100 > 80){
				distance--;
				if(isColliding(mom)){die();}
			}
		}else {
			die();
		}
	}

	

	@Override
	public void shoot() {
		
	}

	@Override
	public void move() {
		setX(mom.getX() + ( Math.sin(mom.angle)*mom.speed) + (Math.sin(angle)*distance));
		setY(mom.getY() - ( Math.cos(mom.angle)*mom.speed) - (Math.cos(angle)*distance));
		angle = angle + speed/(2*Math.PI);
	}
	
	public void targetLocation(double ex, double wy) {
		targetX = ex;
		targetY = wy;
		angle = Math.toDegrees(Math.atan2(targetY - getY(), targetX - getX()));
		if(angle < 0){
	    	angle += 360;
	    }
		if(angle > 360){
	    	angle -= 360;
	    }
		System.out.print(" " + angle + " ");
	}

}
