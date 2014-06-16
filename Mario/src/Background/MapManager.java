package Background;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Sprites.Sprite;

public class MapManager {
	
	public ArrayList<Map> mapList = new ArrayList<Map>();
	public int index = 0;
	public boolean trigger = false;
	
	public Map currentMap;
	public Sprite hero;
	
	public MapManager(){
		
		mapList.add(new StartMenu(this));
		mapList.add(new Stage_1(this));
		mapList.add(new EndMenu(this));
		
		currentMap = mapList.get(index);
	}
	
	public void changeStage(){
		
		trigger = true;
		index++;
	}
	
	public void update(){
		
		if(index != 0){
			
			if(trigger){
				if(index < mapList.size()){
					
					currentMap = mapList.get(index);
					
					hero = new Sprite(currentMap);
					currentMap.init(hero);
					
					trigger = false;
					System.out.println("inti stage_1");
				}
			}
			
			currentMap.update();
			hero.update();
		}
	}
	
	public void draw(Graphics g){
		
		currentMap.draw(g);
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		currentMap.keyPressed(key);
		
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}
