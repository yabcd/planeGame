package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class StorePanel extends JPanel{
	BufferedImage bg;//background paper
	public StorePanel(GameFrame frame) {
		setBackground(Color.pink);
		bg = App.getImg("/img/background3.jpg");
	}
	public void action() {
		Label label = new Label("welcome to store");
		this.add(label);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg,0,0,null);
	}
}
