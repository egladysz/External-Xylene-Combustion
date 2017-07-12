package entities;

import game.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;




public class Player extends Ship {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public boolean shiftPressed;
	public int score;
	boolean zPressed;
	boolean xPressed;
	boolean isHittable,isAlive, isKillingAll;
	public double power = 1.0;
	long shootFrame = 1;
	long killFrame = 0;
	long killAllFrame = 0;
	public int lives = 5;
	public long deadFrame = 0;
	public boolean win = false;
	
	
	
	
	public Player(double ex, double wy) {
		super(ex, wy, "Player.png");
		speed = 4;
		setDepth(2);
		isHittable = true;
		isAlive = true;
		isKillingAll = false;
	}
	
	public void act() {
		if (win){
			setY(getY()-1);
			if (getY() <= -20){
				Screen.gameOver = true;
			}
		} else {
		move();
		if (!(deadFrame > 90)){
		if (zPressed && shootFrame == 0){
			shootFrame = Game.FPS/8;
			shoot();
			
		}
		if (isKillingAll){
			killAll();
		}
		
		if (shootFrame > 0){
			shootFrame--;
		}
		if (isHittable) {
			getHits();
		}
		}
		}
	}
	public void getHits(){
		ArrayList<Structure> temp = new ArrayList<Structure>();
		temp.addAll(Screen.structureList);
		for (Structure s :temp) {
			if (isColliding(s)){
				//s.takeDamage();
				//this.die();
				beHurt();
			}
		}
		ArrayList<Bullet> temp2 = new ArrayList<Bullet>();
		temp2.addAll(Screen.bulletList);
		for (Bullet s :temp2) {
			if (isColliding(s)){
				//s.takeDamage();
				//this.die();
				beHurt();
			}
		}
	}
	
	private void beHurt() {
		isHittable = false;
		isAlive = false;
		Screen.explosionList.add(new Explosion(this.getX(),this.getY()));
		setX(Screen.width/2);
		setY(Screen.height);
		lives--;
		if (lives == 0) Screen.gameOver = true;
		deadFrame = 120;
	}

	public void move() {
		if (deadFrame < 90){
			double dummySpeed = speed;
			if (shiftPressed){
				dummySpeed /= 2;
			}
			if(upPressed && !downPressed && getY() > 0){
				setY(getY()-dummySpeed);
			}
			if(!upPressed && downPressed && getY() < Screen.height){
				setY(getY()+dummySpeed);
			}
			if(leftPressed && !rightPressed && getX() > 0){
				setX(getX()-dummySpeed);
			}
			if(!leftPressed && rightPressed && getX() < Screen.width){
				setX(getX()+dummySpeed);
			}
		}
		else {setY(getY()-1);}
		if (!isAlive){
			if (deadFrame < 1){
				isAlive = true;
				isHittable = true;
			}
		}
		if (deadFrame > 0) deadFrame--;
		
	}
	public void shoot() {
		if (!shiftPressed){
		if ((int)(power%2) == 1){
			Screen.playerList.add(new PlayerBullet((int)(getX()),(int)(getY()-16)));
		}if ((int)(power) >= 2){
			Screen.playerList.add(new PlayerBullet((int)(getX()-(getWidth()/3)),(int)(getY())));
			Screen.playerList.add(new PlayerBullet((int)(getX()+(getWidth()/3)),(int)(getY())));
		}if ((int)(power) >= 4){
			Screen.playerList.add(new PlayerBullet((int)(getX()-(getWidth()/6)),(int)(getY()-8)));
			Screen.playerList.add(new PlayerBullet((int)(getX()+(getWidth()/6)),(int)(getY()-8)));
		}
		}
		else {
			Screen.playerList.add(new PlayerBullet((int)(getX()),(int)(getY()-16)));
			shootFrame /= (int)(power);
		}
		
	}


	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = true;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		case KeyEvent.VK_SHIFT:
			shiftPressed = true;
			break;
		case KeyEvent.VK_Z:
			zPressed = true;
			break;
		case KeyEvent.VK_X:
			if (isAlive && !isKillingAll && power >= 2){
				power--;
				xPressed = true;
				isKillingAll = true;
				isHittable = false;
			}
			break;
		}
	}
	
	private void killAll() {
		killAllFrame++;
		Screen.bulletList = new ArrayList<Bullet>();
		Screen.playerList.add(new PlayerKillAll(this.getX(),this.getY()));
		Screen.playerList.add(new PlayerKillAll(this.getX(),this.getY()));
		Screen.playerList.add(new PlayerKillAll(this.getX(),this.getY()));
		if(killAllFrame > 240){
			isKillingAll = false;
			isHittable = true;
			killAllFrame = 0;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		case KeyEvent.VK_DOWN:
			downPressed = false;
			break;
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		case KeyEvent.VK_SHIFT:
			shiftPressed = false;
			break;
		case KeyEvent.VK_Z:
			zPressed = false;
			break;
		case KeyEvent.VK_X:
			xPressed = false;
			break;
		}
	}

	public void victory() {
		win = true;
		isHittable = false;
	}
}