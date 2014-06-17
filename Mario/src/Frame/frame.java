package Frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class frame {

	// Dimension
	public static int WIDTH = 800;
	public static int HEIGHT = 640;

	public static void main(String args[]) {

		// initialization
		JFrame frame = new JFrame("Mario V1.0");
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		GamePanel panel = new GamePanel();
		frame.add(panel);
	}

}
