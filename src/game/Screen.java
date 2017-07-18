package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


import javax.swing.JPanel;

import entities.*;


@SuppressWarnings("serial")
public class Screen extends JPanel implements KeyListener{
	
	public static PictureHolder data;
	
	public static ArrayList<Structure> structureList;
	public static ArrayList<Bullet> bulletList;
	public static Player you;
	public static long frameNum;
	public static int width;

	public static int height;
	public static ArrayList<Bullet> playerList;
	public static ArrayList<Item> itemList;
	public static ArrayList<Explosion> explosionList;
	public static boolean gameOver;
	
	public static ArrayList<Star> starList;
	
	
	//startup
	public Screen(){
		data = new PictureHolder();
		
		setFocusable(true);
		Dimension d = new Dimension(data.getImage("SpaceBackground.png").getWidth(null),data.getImage("SpaceBackground.png").getHeight(null));
		
		this.setMaximumSize(d);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		
		width = getPreferredSize().width;
		height = getPreferredSize().height;
		
		frameNum = 0;
		structureList = new ArrayList<Structure>();
		bulletList = new ArrayList<Bullet>();
		playerList = new ArrayList<Bullet>();
		itemList = new ArrayList<Item>();
		starList = new ArrayList<Star>();
		explosionList = new ArrayList<Explosion>();
		this.setBackground(Color.RED);
		you = new Player(this.getWidth()/2,this.getHeight()/8*7);
	}
	
	//Master "LET'S DRAW THE GAME" method. Does nothing to game data.
	public void paint(Graphics g) {
		this.setBackground(Color.RED);
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (gameOver) {
			drawEnd(g);
		} else {
		
		drawBack(g);
		drawStructures(g);
		drawPlayer(g);
		drawBullets(g);
		drawHud(g);
		drawTitle(g);
		}
	}
	
	private void drawEnd(Graphics g) {
		if (you.win)
			g.drawImage(data.getImage("Title.png"), 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(data.getFont());
		g.setFont(g.getFont().deriveFont(30.0f));
		g.drawString("Score: " + you.score, this.getWidth()/4, this.getHeight()/2);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(60.0f));
		g.drawString((you.win?"VICTORY":"THE END"), this.getWidth()/4, this.getHeight()/3);
	}

	private void drawTitle(Graphics g) {
		if (frameNum < 120)
		g.drawImage(data.getImage("Title.png"), 0, 0, null);
	}

	//Draws that scrolling space background.
	private void drawBack(Graphics g) {
		
		
		g.setColor(Color.WHITE);
		ArrayList<Star> temp = new ArrayList<Star>();
		temp.addAll(starList);
		for (Star d : temp){
			if(d != null)
				g.fillRect((int)(d.x), (int)(d.y), 1, 1);
		}
		//Image back = data.getImage("SpaceBackground.png");
		//g.drawImage(back,0, (int) (frameNum % back.getHeight(null)), null);
		//g.drawImage(back,0, (int) ((frameNum % back.getHeight(null)) - back.getHeight(null)), null);
	}
	
	//Draws all structure items except the player.
	private void drawStructures(Graphics g) {
		for (Entity d : structureList){
			g.drawImage(d.getImage(),(int) (d.getX()-d.getWidth()/2),(int) (d.getY()-d.getHeight()/2), null);
		}
		
		for (Entity d : explosionList){
			g.drawImage(d.getImage(),(int) (d.getX()-d.getWidth()/2),(int) (d.getY()-d.getHeight()/2), null);
		}
	}
	
	//Draws the player and the player hitbox circle
	private void drawPlayer(Graphics g) {
		if (you.deadFrame % 10 <= 5)
			g.drawImage(you.getImage(),(int) (you.getX()-(you.getWidth()/2)),(int) (you.getY()-(you.getHeight()/2)), null);
		Image d = data.getImage("BlueEnemyBulletSmall.png");
		if (you.shiftPressed)
			g.drawImage(d,(int) (you.getX()-(d.getWidth(null)/2)),(int) (you.getY()-(d.getHeight(null)/2)), null);
	}
	//draws bullets. separate ArrayList things differentiate player Bullets and Enemy Bullets
	private void drawBullets(Graphics g) {
		for (Bullet d : bulletList){
			if(d != null)
				g.drawImage(d.getImage(),(int) (d.getX()-d.getWidth()/2),(int) (d.getY()-d.getHeight()/2), null);
		}
		for (Bullet d : playerList){
			if (d != null)
				g.drawImage(d.getImage(),(int) (d.getX()-d.getWidth()/2),(int) (d.getY()-d.getHeight()/2), null);
		}
		for (Item d : itemList){
			g.drawImage(d.getImage(),(int) (d.getX()-d.getWidth()/2),(int) (d.getY()-d.getHeight()/2), null);
		}
	}
	//Draws Hud. Might be removed in place of an entire separate JFrame.
	private void drawHud(Graphics g) {
		for (int i = 1; i <= you.lives; i++){
			g.drawImage(data.getImage("Life.png"),Screen.width - 8*i, Screen.height - 8,null);
		}
		g.setColor(Color.WHITE);
		g.setFont(data.getFont());
		g.setFont(g.getFont().deriveFont(30.0f));
		if (you.power < 5)
			g.drawString(String.format("Power: %.1f",you.power), 0, this.getHeight());
		else 
			g.drawString("Power: MAX", 0, this.getHeight());
		g.drawString("Score: " + you.score, 0, this.getHeight()-28);
	}
	//Master "LET'S RUN THE GAME" method. All Entity data is modified here.
	public void process(){
		frameNum++;
		if (!gameOver) {
			spawn();//Exception to previous comment. Checks progress of timer to add enemies.
			if (frameNum %1 == 0) starList.add(new Star());
			processStructures();
			processBullets();
			you.act();
			processHud();
		}
	}

	//Runs through all Structure act() method.
	public void processStructures() {
		
		ArrayList<Entity> temp = new ArrayList<Entity>();
		temp.addAll(structureList);
		for (Entity d : temp){
			d.act();
		}
		
		ArrayList<Explosion> temp2 = new ArrayList<Explosion>();
		temp2.addAll(explosionList);
		for (Entity d : temp2){
			d.act();
		}
	}
	//Runs through
	public void processBullets(){
		ArrayList<Bullet> temp = new ArrayList<Bullet>();
		temp.addAll(bulletList);
		for (Entity d : temp){
			d.act();
		}
		ArrayList<Bullet> temp2 = new ArrayList<Bullet>();
		temp2.addAll(playerList);
		for (Bullet d : temp2){
			d.act();
		}
		ArrayList<Item> temp3 = new ArrayList<Item>();
		temp3.addAll(itemList);
		for (Item d : temp3){
			d.act();
		}
		ArrayList<Star> temp0 = new ArrayList<Star>();
		temp0.addAll(starList);
		for (Star d : temp0){
			d.act();
		}
	}
	
	private void processHud() {
		
		
	}

	public Player getPlayer() {
		return you;
	}

	//destroys the specified thing if it exists in any ArrayList.
	public static void kill(Entity thing) {
		for (Entity d : structureList){
			if (d.equals(thing)) {
				structureList.remove(d);
				return;
			}
		}
		for (Entity d : bulletList){
			if (d.equals(thing)) {
				bulletList.remove(d);
				return;
			}
		}
		for (Entity d : playerList){
			if (d.equals(thing)) {
				playerList.remove(d);
				return;
			}
		}
		for (Entity d : itemList){
			if (d.equals(thing)) {
				itemList.remove(d);
				return;
			}
		}
		for (Entity d : explosionList){
			if (d.equals(thing)) {
				explosionList.remove(d);
				return;
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {you.keyPressed(e);}
	public void keyReleased(KeyEvent e){you.keyReleased(e);}
	public void keyTyped(KeyEvent arg0){}
	
	
	//Checks frame progress to add additional enemies.
	private void spawn() {
		// TODO Make an ACTUAL LEVEL!
		
		if (frameNum == 120)structureList.add(new ChaseBot(Screen.width/2,0));
		
		if (frameNum % 15 == 0 && ((frameNum > 240 && frameNum < 360)||(frameNum > 480 && frameNum < 720))) structureList.add(new ChaseBot(Screen.width/2,0));
		
		if (frameNum == 1000 || frameNum == 1400) {
			for (int i = 0; i <= 4; i++){
				structureList.add(new ChaseBot(Screen.width/4*i,0));
			}
		}
		if (frameNum == 1200 || frameNum == 1600) {
			for (int i = 0; i <= 4; i++){
				structureList.add(new Meteor(Screen.width/4*i, 2));
			}
		}
		
		if (frameNum == 2000){
			structureList.add(new MiniBoss());
		}
		
		
		
		
		/*
		if (frameNum % (Game.FPS*2) == 0){
			Meteor heroCore = new Meteor(Math.random()*this.getWidth(), 2);
			OrbitCore orbitCoreOne = new OrbitCore(heroCore,100,1);
			OrbitCore orbitCoreTwo = new OrbitCore(orbitCoreOne,75,1);
			heroCore.setHealth(10);
			orbitCoreOne.setHealth(5);
			orbitCoreTwo.setHealth(5);structureList.add(heroCore);
			structureList.add(orbitCoreOne);
			structureList.add(orbitCoreTwo);
			structureList.add(new Enemy2(0,0));
		}
		*/
	}

	public static void removeStar(Star star) {
		for (int i = 0; i < starList.size(); i++){
			if (starList.get(i).equals(star)) {
				starList.remove(star);
				return;
			}
		}
	}
}
