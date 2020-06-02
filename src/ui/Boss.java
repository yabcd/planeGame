package ui;

import java.util.Random;

public class Boss extends Plane{

	int hp;
	int id;
	int speedy;
	int speedx;
	int score;
	int type;
	
	public Boss() {
		
	}
	
	public Boss(int wave) {
		
		Random r = new Random();
		int index = r.nextInt(3)+1;
		hp = wave*100;
		type = index;
		img = App.getImg("/img/Boss" + index + ".png");
		w = img.getWidth();
		h = img.getHeight();
		x = 640-w/2;
		y = -h;
		score = 100;
		
		speedx = 5;
		speedy = 10-index;
	}
	/**
	 * make boss move
	 */
	public void move() {
		// TODO Auto-generated method stub
		if(y >= h)
			return;
		else
			y += speedy;
	}
	/**
	 * is the boss being shot
	 * @param f
	 * @return
	 */
	public boolean HitBy(Fire f) {
		// TODO Auto-generated method stub
		boolean hit = x <= f.x+f.w &&
					x >= f.x - w &&
					y <= f.y+f.h &&
					y >= f.y - h;
		return hit;
	}
	/**
	 * is the main plane being hit by boss
	 * @param plane
	 * @return
	 */
	public boolean HitBy(MainPlane plane) {
		// TODO Auto-generated method stub
		boolean hit = x <= plane.x+plane.w &&
				x >= plane.x - w &&
				y <= plane.y+plane.h &&
				y >= plane.y - h;
		return hit;
	}
}
