package entities;

import game.Screen;

public class PowerItem extends Item{

	public PowerItem(double ex, double wy) {
		super(ex, wy, "PowerItem.png");
		
	}

	@Override
	public void giveStat() {
		if (Screen.you.power < 5.0)
		Screen.you.power += 0.1f;
	}

}
