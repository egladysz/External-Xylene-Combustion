package entities;

import game.*;

import java.awt.Image;

public abstract class Entity {
	private double x, y;
	private int width, height, depth;
	private Image image;
	public Entity(double x, double y, String ing) {
		setImage(ing);
		width = image.getWidth(null);
		height = image.getHeight(null);
		this.x = x;
		this.y = y;
		depth = width/2;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int dep){
		depth = dep;
	}
	
	public boolean isColliding(Entity e){
		return Math.sqrt(Math.pow(this.getX()-e.getX(),2) + Math.pow(this.getY()-e.getY(),2))<(this.getDepth()+e.getDepth());
	}

	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double ex) {
		x = ex;
	}
	
	public void setY(double wy) {
		y = wy;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int wid) {
		width = wid;
	}
	
	public void setHeight(int hei) {
		height = hei;
	}

	public void setImage(String s) {
		image = Screen.data.getImage(s);
	}

	public abstract void act();
	
	public abstract void move();
	
	public boolean isOffField(){
		return (getX() + width/2 < 0)||(getX() - width/2 > Screen.width)||(getY() - height/2 > Screen.height);
	}
	
	public void die() {
		Screen.kill(this);
		
		if (this instanceof Structure){
			Screen.you.score+= 50;
			Screen.explosionList.add(new Explosion(this.getX(),this.getY()));
			if (Screen.you.power < 5)
				Screen.itemList.add(new PowerItem(getX(),getY()));
			else 
				Screen.itemList.add(new PointItem(getX(),getY()));
		}
	}
	public double getAngleTo(double ex, double wy){
		return Math.atan2(ex - getX(), wy - getY());
		//angle = Math.toRadians(Math.toDegrees(angle) + 90);
	}
	public double getAngleTo(Entity e){
		return getAngleTo(e.getX(),e.getY());
	}
}
