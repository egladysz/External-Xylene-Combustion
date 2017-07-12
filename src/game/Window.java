package game;


import java.awt.Graphics;
import javax.swing.JFrame;

import entities.Player;

//import entities.Player;


@SuppressWarnings("serial")
public class Window extends JFrame{
	
	public static final int WIDTH = 384; // TODO: get a real value;
	public static final int HEIGHT = 512;
	public long frameNum = 0;
	
	public static Screen display;
	
	public Window(){
		super("External Xylene Combustion | Version 1.1");
		setFocusable(false);
		setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display = new Screen();
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().add(display);
		this.pack();
		this.addKeyListener(display);
		
		
		Screen.you = new Player(display.getWidth()/2,display.getHeight()/8*7);
		setVisible(true);
	}
	public void update(Graphics screen){
		paint(screen);
	}
	public void process() {
		frameNum++;
		if (frameNum > 120)
			display.process();
			
		
	}
	public void applyDrawing() {
		display.repaint();
	}


}
