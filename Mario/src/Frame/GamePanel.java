package Frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Background.*;

public class GamePanel extends JPanel implements KeyListener, Runnable{
	
	//Dimension
	public static int WIDTH = 800;
	public static int HEIGHT = 640;
	
	//Display
	public int FPS = 50;
	public int seconds = 1000 / FPS;
	
	//Thread
	public Thread thread;
	public boolean running = false;
	
	//MapManager
	public MapManager manager;
	
	//BufferedImage
	public BufferedImage image;
	public Graphics g;
	
	public GamePanel(){
		
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void init(){
		  
		manager = new MapManager();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.createGraphics();
	}
	
	public void addNotify(){
		
		super.addNotify();
		if(!running){
			addKeyListener(this);
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void render(){
		
		Graphics g_panel = this.getGraphics();
		g_panel.drawImage(image, 0, 0, this);
		g_panel.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		manager.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		manager.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		manager.keyTyped(e);
	}

	@Override
	public void run() {
		
		init();
		
		long startTime = 0;
		long restTime = 0;
		
		while(running){
			
			startTime = System.nanoTime();
			
			manager.update();
			manager.draw(g);

			restTime = System.nanoTime() - startTime;
			if(restTime / 1000000 < seconds){
				render();
			}else{
				
			}
		}
	}
}









