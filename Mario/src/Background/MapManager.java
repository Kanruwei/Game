package Background;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Sprites.Sprite;

public class MapManager {
	
	public Map currentMap;
	public Sprite hero;
	
	public MapManager(){
		
		currentMap = new Stage_1();
		currentMap.init();
		
		hero = new Sprite(currentMap);
	}
	
	public void update(){
		
		currentMap.update();
		hero.update();
	}
	
	public void draw(Graphics g){
		
		currentMap.draw(g);
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_A){
			hero.toLeft();
		}else if(key == KeyEvent.VK_D){
			hero.toRight();
		}
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
