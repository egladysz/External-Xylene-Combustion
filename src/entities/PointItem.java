package entities;

import game.Screen;

public class PointItem extends Item {

	public PointItem(double ex, double wy) {
		super(ex, wy, "PointItem.png");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void giveStat() {
		Screen.you.score += 100;

	}

}
