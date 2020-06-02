package ui;

import java.awt.image.BufferedImage;

/*
 * Main Plane
 */
public class MainPlane extends Plane{
	int hp;
	
	public MainPlane() {
		img = App.getImg("/img/Plane4.png");
		x = 580;
		y = 500;
		w = img.getWidth();
		h = img.getHeight();
		hp = 4;
	}
	
	public void moveToMouse(int mx, int my) {
		x = mx-w/2;
		y = my-h/2;
	}
	
	public void moveUp() {
		y -= 10;
	}
	
	public void moveDown() {
		y += 10;
	}
	
	public void moveLeft() {
		x -= 10;
	}
	
	public void moveRight() {
		x += 10;
	}
}
