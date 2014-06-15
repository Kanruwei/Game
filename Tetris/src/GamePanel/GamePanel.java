package GamePanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Background.Background;
import Cube.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	//Dimension
	public int WIDTH = Game.WIDTH;
	public int HEIGHT = Game.HEIGHT;
	
	//FPS
	public int FPS = 50;
	public int seconds = 1000 / FPS;
	
	//Thread
	public Thread thread;
	public boolean running = false;
	
	//BufferedImage
	public BufferedImage image;
	public Graphics g;
	
	//initialization
	public Background background;
	public Cube currentCube;
	public Cube nextCube;
	
	
	public GamePanel(){
		
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setFocusable(true);
		this.requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
	}
	
	public void init(){
		
		background = new Background();
		
		currentCube = CubeState.createCube();
		nextCube = CubeState.createCube();
	}
	
	public void addNotify(){
		
		super.addNotify();
		if(!running){
			running = true;
			this.addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}

	@Override
	public void run() {
		
		init();
		int i = 0;
		
		while(running){
			
			if(i == 18){
				downing();
				i = 0;
			}
			
			update();
			draw(g);
			render();
			
			try {
				Thread.sleep(seconds);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i ++;
		}
	}
	
	public void downing(){
		currentCube.downing();
	}
	
	public void update(){
		 
		if(!currentCube.alive){
			currentCube = nextCube;
			nextCube = CubeState.createCube();
		}
	}
	
	public void draw(Graphics g){
	
		background.draw(g);
		currentCube.draw(g);
		nextCube.draw2(g);
	}
	
	public void render(){
		
		Graphics g_panel = this.getGraphics();
		g_panel.drawImage(image, 0, 0, this);
		g_panel.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		String direction = null;
		String moving = null;
		
		if(key == KeyEvent.VK_LEFT){
			direction = "left";
			currentCube.rotate(direction);
		}else if(key == KeyEvent.VK_RIGHT){
			direction = "right";
			currentCube.rotate(direction);
		}else if(key == KeyEvent.VK_S){
			moving = "down";
			currentCube.moving(moving);
		}else if(key == KeyEvent.VK_A){
			moving = "a";
			currentCube.moving(moving);
		}else if(key == KeyEvent.VK_D){
			moving = "d";
			currentCube.moving(moving);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
