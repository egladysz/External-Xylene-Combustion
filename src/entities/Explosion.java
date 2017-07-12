package entities;


public class Explosion extends Entity{
	
	public int frame = 0;

	public Explosion(double ex, double wy) {
		super(ex, wy, "Kaboom.png");
	}

	@Override
	public void act() {
		frame++;
		if (frame > 4) this.die();
		
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

}
