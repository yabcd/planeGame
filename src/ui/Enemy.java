package ui;

import java.awt.image.BufferedImage;
import java.util.Random;

/*
 * Enemy Plane
 */
public class Enemy extends Plane{
	
	int hp;
	int id;
	int speedy;
	int speedx;
	int score;
	int type;
	
	public Enemy() {
		
		Random r = new Random();
		int index = r.nextInt(3)+1;
		type = index;
		img = App.getImg("/img/Enemy" + index + ".png");
		w = img.getWidth();
		h = img.getHeight();
		x = r.nextInt(1280-w);
		y = -h;
		score = index+10;
		
		speedx = 5;
		speedy = 10-index;
	}
	/**
	 * make enemy move
	 */
	public void move() {
		// TODO Auto-generated method stub
		if(type == 3)
			x -= speedx;
		if(type == 15)
			speedy += 10;
		y += speedy;
	}
	/**
	 * is the enemy being shot
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
	 * is the main plane being hit by enemy
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
