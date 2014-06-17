package GamePanel;

import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JFrame;

public class Game extends JFrame{
	
	public static int WIDTH = 600;
	public static int HEIGHT = 700;
	
	public static void main(String args[]){
		
		JFrame Frame = new JFrame("Tetric V1.0");
		Frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		Frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		Frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setVisible(true);
		
		GamePanel Panel = new GamePanel();
		Frame.add(Panel);
	}

}
