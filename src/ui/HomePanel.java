package ui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class HomePanel extends JPanel{
	Button startGame;
	Button store;
	GameFrame gf;
	
	public HomePanel(GameFrame gf) {
		startGame = new Button("start game");
		store = new Button("store");
		this.gf=gf;
	}
	
	public void action() {
		this.add(startGame);
		startGame.setLocation(200, 300);
		this.add(store);
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GamePanel gp = new GamePanel(gf);
				gf.add(gp);
				gf.setVisible(true);
				gp.action();
			}
		});
		store.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StorePanel sp = new StorePanel(gf);
				gf.add(sp);
				gf.setVisible(true);
				sp.action();
			}
		});
	}
}