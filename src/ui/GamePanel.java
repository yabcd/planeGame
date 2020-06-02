package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

/**
 * GamePanel
 */
public class GamePanel extends JPanel{
	BufferedImage bg;//background paper
	MainPlane plane = new MainPlane();
	List<Enemy> ens = new ArrayList<Enemy>();//enemy
	List<Fire> fs = new ArrayList<Fire>();//fire
	Boss boss;
	List<BossFire> bfs = new ArrayList<BossFire>();//boss fire
	int wave;//boss appear
	int score;//score
	int score1;//right after eating good prop
	int score2;//right after eating bad prop
	boolean Switch = true;//game start
	boolean Resume = true;//game resume
	int power = 1;//change plane power
	int model = 1;//change panel
	/**
	 * game act
	 */
	public void action() {
		new Thread() {
			public void run() {
				while(true) {
					if(Switch && Resume) {
						//add enemy
						EnemyEnter();
						//make enemy move
						EnemyMove();
						//launch fire
						FireLaunch();
						//make fire move
						FireMove();
						//is super time end
						IsSuperEnd();
						//boss appears
						BossAppear();
						//boss launch fire
						BossFireLaunch();
					}
					//is hit or not
					HitTarget();
					//is the main plane hit or not
					HitMainPlane();
					//boss hit main plane
					BossHitMain();
					try {
						if(score <= 1000)
							Thread.sleep(20);
						else if(score <= 2500)
							Thread.sleep(18);
						else if(score <= 5000)
							Thread.sleep(16);
						else if(score <= 8000)
							Thread.sleep(13);
						else
							Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
			}
		}.start();
	}
	/**
	 * boss launches fire
	 */
	int bfindex = 0;
	protected void BossFireLaunch() {
		// TODO Auto-generated method stub
		if(boss==null) return ;
		bfindex++;
		bfindex %= 10;
		if(bfindex == 0) {
			if(power == 1) {
				BossFire f1 = new BossFire(boss, 1);  //middle fire
				bfs.add(f1);
			}
			else if(power == 2) {
				BossFire f2 = new BossFire(boss, 2);  //right side fire
				bfs.add(f2);
				BossFire f3 = new BossFire(boss, 0);  //left side fire
				bfs.add(f3);
			}
			else {
				BossFire f1 = new BossFire(boss, 1);  //middle fire
				bfs.add(f1);
				BossFire f2 = new BossFire(boss, 2);  //right side fire
				bfs.add(f2);
				BossFire f3 = new BossFire(boss, 0);  //left side fire
				bfs.add(f3);
			}
		}
	}
	/**
	 * check whether boss hit the main plane
	 */
	protected void BossHitMain() {
		// TODO Auto-generated method stub
		if(boss==null) return;
		if(boss.HitBy(plane)) {
			plane.hp = 0;
			Switch = false;
		}
	}
	/**
	 * boss appear
	 */
	protected void BossAppear() {
		// TODO Auto-generated method stub
		if(score / 1500 != wave) {
			wave = score/1500;
			Boss b = new Boss(wave);
			boss = b;
		}
	}
	/**
	 * check if the super time is off
	 */
	protected void IsSuperEnd() {
		// TODO Auto-generated method stub
		if((score - score1) >= 100 && score1 != 0) {
			power = 1;
		}
		if((score - score2) >= 100 && score2 != 0) {
			power = 1;
			for(int i = 0; i < ens.size(); i++) {
				Enemy e = ens.get(i);
				e.speedy -= 10;
			}
		}
	}
	/**
	 * check for hit main plane
	 */
	protected void HitMainPlane() {
		// TODO Auto-generated method stub
		for(int i = 0; i < ens.size(); i++) {
			Enemy e = ens.get(i);
			if(e.HitBy(plane)) {
				ens.remove(e);
				plane.hp--;
				power = 1;
				score += 10;
				if(plane.hp == 0) {
					Switch = false;
				}
			}
		}
	}
	/**
	 * check for hit target
	 */
	protected void HitTarget() {
		// TODO Auto-generated method stub
		for(int i = 0; i < fs.size(); i++) {
			Fire f = fs.get(i);
			Bang(f);
			HitBoss(f);
		}
	}
	/**
	 * hit boss plane
	 * @param f
	 */
	private void HitBoss(Fire f) {
		// TODO Auto-generated method stub
		if(boss==null) return;
		if(boss.HitBy(f)) {
			boss.hp--;
			if(boss.hp <= 0) {
				score += boss.score;
				score1 = score;
			}
			fs.remove(f);
		}
	}
	/**
	 * hit target
	 * @param f
	 */
	private void Bang(Fire f) {
		// TODO Auto-generated method stub
		for(int i = 0; i < ens.size(); i++) {
			Enemy e = ens.get(i);
			if(e.HitBy(f)) {
				if(e.type != 15) {
					e.hp--;
					if(e.hp <= 0) {
						//add hp
						if(e.type == 3) {
							plane.hp++;
							if(plane.hp > 4)
								plane.hp = 4;
						}
						//good prop
						if(e.type == 9) {
							score1 = score;
							power ++;
							if(power > 3) {
								power = 3;
							}
						}
						//bad prop
						if(e.type == 10) {
							score2 = score;
							if(power == 1)
								power = 1;
							else
								power--;
							for(int j = 0; j < ens.size(); j++) {
								Enemy ee = ens.get(j);
								ee.speedy += 10;
							}
						}
						ens.remove(e);
						score += 10;
					}
				}
				fs.remove(f);
			}
		}
	}
	/**
	 * make fire move
	 */
	protected void FireMove() {
		// TODO Auto-generated method stub
		for(int i = 0; i < fs.size(); i++) {
			Fire f = fs.get(i);
			f.move();
		}
	}
	/**
	 * set mark
	 */
	int findex = 0;
	protected void FireLaunch() {
		// TODO Auto-generated method stub
		findex++;
		findex %= 10;
		if(findex == 0) {
			if(power == 1) {
				Fire f1 = new Fire(plane.x, plane.y, 1);  //middle fire
				fs.add(f1);
			}
			else if(power == 2) {
				Fire f2 = new Fire(plane.x+40, plane.y+40, 2);  //right side fire
				fs.add(f2);
				Fire f3 = new Fire(plane.x-40, plane.y+40, 0);  //left side fire
				fs.add(f3);
			}
			else {
				Fire f1 = new Fire(plane.x, plane.y, 1);  //middle fire
				fs.add(f1);
				Fire f2 = new Fire(plane.x+40, plane.y+40, 2);  //right side fire
				fs.add(f2);
				Fire f3 = new Fire(plane.x-40, plane.y+40, 0);  //left side fire
				fs.add(f3);
			}
		}
	}
	/**
	 * set mark
	 */
	int index = 0;
	/**
	 * create enemy plane
	 */
	protected void EnemyEnter() {
		index++;
		index %= 15;
		if(index == 0) {
			Enemy e = new Enemy();
			ens.add(e);
		}
	}
	/**
	 * make enemy move
	 */
	protected void EnemyMove() {
		for(int i = 0; i < ens.size(); i++) {
			Enemy e = ens.get(i);
			e.move();
		}
	}
	
	public GamePanel(GameFrame frame) {
		setBackground(Color.pink);
		bg = App.getImg("/img/background1.jpg");
		//mouse listener
		MouseAdapter adapter = new MouseAdapter() {
			/**
			 * restart game
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!Switch) {
					plane= new MainPlane();
					Switch = true;
					score = 0;
					ens.clear();
					fs.clear();
					Random r = new Random();
					int index = r.nextInt(3)+1;
					bg = App.getImg("/img/background" + index + ".jpg");
					repaint();
				}
			}
			/**
			 * control the main plane
			 */
			@Override
			public void mouseMoved(MouseEvent e) {
				int x = e.getX(), y = e.getY();
				if(Switch == true && Resume == true)
					plane.moveToMouse(x, y);
				repaint();
			}
			
		};
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		
		KeyAdapter kd = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				//game start or over
				if(Switch == false)
					return;
				//main plane move
				if(keycode == KeyEvent.VK_UP) {
					plane.moveUp();
				}
				if(keycode == KeyEvent.VK_DOWN) {
					plane.moveDown();
				}
				if(keycode == KeyEvent.VK_LEFT) {
					plane.moveLeft();
				}
				if(keycode == KeyEvent.VK_RIGHT) {
					plane.moveRight();
				}
				//game resume
				if(keycode == KeyEvent.VK_SPACE) {
					if(Resume == false)
						Resume = true;
					else
						Resume = false;
				}
				//open or close game store
				if(keycode == KeyEvent.VK_S) {
					model = 2;
				}
				repaint();
			}
		};
		frame.addKeyListener(kd);
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bg, 0, 0, null);
		
		g.setColor(Color.white);//font color
		g.setFont(new Font("����",Font.BOLD,25));//font,strengthen,size
		g.drawString("Blood:", 50, 50);
		g.drawString("Score: " + score, 50, 80);
		g.drawRect(150, 33, 200, 20);//x, y, width, height/empty
		g.fillRect(150, 33, 200*plane.hp/4, 20);//substantial
		
		g.drawImage(plane.img, plane.x, plane.y, plane.w, plane.h, null);
		
		for(int i = 0; i < ens.size(); i++) {
			Enemy en = ens.get(i);
			g.drawImage(en.img, en.x, en.y, en.w, en.h, null);
		}
		
		for(int i = 0; i < fs.size(); i++) {
			Fire f = fs.get(i);
			g.drawImage(f.img, f.x, f.y, f.w, f.h, null);
		}
		/**
		 * game over
		 */
		if(Switch == false) {
			g.setColor(Color.red);
			g.setFont(new Font("����",Font.BOLD,100));
			g.drawString("GAMEOVER!", 400, 400);
			g.setColor(Color.white);
			g.setFont(new Font("����", Font.BOLD, 20));
			g.drawString("Click the screen to restart the game.", 420, 450);
		}
		/**
		 * resume
		 */
		if(Resume == false) {
			g.setColor(Color.green);
			g.setFont(new Font("����",Font.BOLD,100));
			g.drawString("RESUME", 450, 375);
			g.setColor(Color.white);
			g.setFont(new Font("����", Font.BOLD, 20));
			g.drawString("Click the space key to continue.", 450, 425);
		}
	}
}
