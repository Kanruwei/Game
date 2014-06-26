package Frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public static int WIDTH = 860;
	public static int HEIGHT = 700;
	
	public static JFrame frame;
	public static GamePanel panel;
	
	public static void main(String args[]){
		
		frame = new JFrame("Flight");
		
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		panel = new GamePanel();
		frame.add(panel);
	}

}
