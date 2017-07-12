package game;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PictureHolder {
	
	ArrayList<BufferedImage> imageList;
	ArrayList<String> nameList;
	Font f;
	
	public PictureHolder() {
		imageList = new ArrayList<BufferedImage>();
		nameList = new ArrayList<String>();
		this.fill();
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, PictureHolder.class.getResourceAsStream("/Cirno.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void add(String i){
		try {
			imageList.add(ImageIO.read(PictureHolder.class.getResourceAsStream("/" +i)));
			nameList.add(i);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(i);
		}
	}
	
	public Image getImage(String i){
		int target = 0;
		for (int j = 0; j < nameList.size(); j++){
			if (nameList.get(j).equals(i)){
				target = j;
				break;
			}
		}
		return imageList.get(target);
	}

	public void fill() {
		add("Player.png");
		add("SpaceBackground.png");
		add("Enemy1.png");
		add("Enemy2.png");
		add("PlayerBullet.png");
		add("RedEnemyBulletSmall.png");
		add("RedEnemyBullet.png");
		add("BlueEnemyBulletSmall.png");
		add("BlueEnemyBullet.png");
		add("Kaboom.png");
		add("Title.png");
		add("PowerItem.png");
		add("PointItem.png");
		add("Life.png");
		add("MiniBoss.png");
	}
	public Font getFont(){
		return f;
	}

	
	
	
}
