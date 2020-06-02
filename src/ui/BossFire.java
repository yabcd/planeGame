package ui;

public class BossFire extends Plane{

	int direction;
	int speedx = 5;
	int speedy = 10;
	
	public BossFire(Boss b, int direction) {
		img = App.getImg("/img/Bullet.png");
		w = img.getWidth();
		h = img.getHeight();
		x = b.x + b.w/2;
		y = b.y - 40;
		this.direction = direction;
	}

	public void move() {
		// TODO Auto-generated method stub
		int xs = speedx;
		int ys = speedy;
		if(direction == 0) {
			x -= speedx;
			y += speedy;
		}
		else if(direction == 1){
			y += speedy;
		}
		else if(direction == 2) {
			x += speedx;
			y += speedy;
		}
	}
}
