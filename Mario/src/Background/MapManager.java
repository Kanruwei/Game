package Background;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Sprites.Sprite;

public class MapManager {
	
	public Map currentMap;
	public Sprite hero;
	
	public MapManager(){
		
		currentMap = new Stage_1();
		
		hero = new Sprite(currentMap);
		currentMap.init(hero);
	}
		
		
	
	
	public void update() {

		currentMap.update();
		hero.update();

	}
	
	public void draw(Graphics g){
		
		currentMap.draw(g);
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		hero.keyPressed(key);
		
	}

	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		hero.keyReleased(key);
	}

	public void keyTyped(KeyEvent e) {
		
		int key = e.getKeyCode();

		hero.KeyTyped(key);
	}
}
