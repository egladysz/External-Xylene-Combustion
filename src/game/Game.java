package game;

public class Game {
	
	public static final int FPS = 60;
	public static final int FRAME_SKIP = 1000/FPS;
	public long nextTick;
	public long sleepTime;
	public boolean isRunning;
	public Window window;
	
	public Game () {
		
		sleepTime = 0;
		isRunning = false;
		window = new Window();
	}
	
	public void begin() {
		isRunning = true;
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		//int ticks = 0;
		//int frames = 0;
		//long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			while(delta>= 1){
				//ticks++;
				this.update();
				delta--;
				shouldRender=true;
			}			
			if(shouldRender){
				//frames++;
				this.display();
			}
			
		}
		/*
		nextTick = System.currentTimeMillis();
		while (isRunning){
			this.update();
			this.display();
			
			nextTick += FRAME_SKIP;
			sleepTime = nextTick - System.currentTimeMillis();
			if (sleepTime >= 0){
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		*/
	}
	
	
	
	public void update() {
		window.process();
	}
	public void display() {
		window.applyDrawing();
	}
}
