package entities;

import game.Screen;

public class Star {
	
	public double x,y,depth,size;
	public boolean isAlive;

	public Star() {
		x = Math.random()*Screen.width;
		y = -1;
		depth = Math.random()*4+2;
	}
	public void act(){
		y+= depth;
		if (y > Screen.height){
			Screen.removeStar(this);
		}
	}
}
//any suggested modifications?