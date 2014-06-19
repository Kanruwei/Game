package Frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Background.MapManager;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	//Dimension
	public int WIDTH = GameFrame.WIDTH;
	public int HEIGHT = GameFrame.HEIGHT;
	
	//Thread
	public boolean running = false;
	public Thread thread;
	
	//BufferedImage
	public BufferedImage image;
	public Graphics g;
	
	//MapManager
	public MapManager manager;
	
	public GamePanel(){
		
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Initialization of BufferedImage
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
		
		//Initialization of MapManager
		manager = new MapManager();
	}
	
	public void addNotify(){
		
		super.addNotify();
		if(!running){
			
			this.addKeyListener(this);
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void update(){
		
		manager.update();
	}
	
	public void draw(Graphics g){
		
		manager.draw(g);
	}
	
	public void render(){
		
		Graphics g_panel = this.getGraphics();
		g_panel.drawImage(image, 0, 0, this);
		g_panel.dispose();
	}

	public void run() {
		
		update();
		draw(g);
		render();
	}
	
	public void keyPressed(KeyEvent arg0) {
		
	}

	public void keyReleased(KeyEvent arg0) {
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}

}
