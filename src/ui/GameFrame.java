package ui;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * Windows
 */
public class GameFrame extends JFrame{
	
	private static JButton but = new JButton("Store");
	
	public GameFrame() {
		setTitle("Plane Game");
		setSize(1280,720);//width, height
		setLocationRelativeTo(null);//centered
		setResizable(false);//unable to change size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//shut down the procedure and window at the same time
	}
	
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		HomePanel hPanel = new HomePanel(frame);
		frame.add(hPanel);
		hPanel.action();
		
		frame.setVisible(true);//show window
	}
	
}

