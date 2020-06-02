package ui;
/*
 * Bullet
 */
public class Fire extends Plane{
	
	int direction;
	int speedx = 2;
	int speedy = 10;
	
	public Fire(int hx, int hy, int direction) {
		img = App.getImg("/img/Bullet.png");
		w = img.getWidth();
		h = img.getHeight();
		x = hx + 41;
		y = hy - 40;
		this.direction = direction;
	}

	public void move() {
		// TODO Auto-generated method stub
		int xs = speedx;
		int ys = speedy;
		if(direction == 0) {
			x -= speedx;
			y -= speedy;
		}
		else if(direction == 1){
			y -= speedy;
		}
		else if(direction == 2) {
			x += speedx;
			y -= speedy;
		}
	}
}
