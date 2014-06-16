package Sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Background.Map;

public class Sprite {
	
	public boolean alive = true;
	
	public Map map;
	public ArrayList<String[]> matrix;
	
	public int realX;
	public int realY;
	
	public int TempX;
	public int TempY;
	
	public boolean falling = false;
	public boolean jumping = false;
	public boolean lefting = false;
	public boolean righting = false;
	public boolean standing = false;
	
	public int move_speed = 6;
	public int jump_speed = 8;
	public int fall_speed = 8;
	
	public Image image;
	public String path_image = "res/Sprites/mario.png";
	
	public Sprite(Map map) {

		this.map = map;
		this.matrix = map.matrix;
		this.realX = map.realX;
		this.realY = map.realY;

		try {
			image = ImageIO.read(new File(path_image));
			map.hero_image = image;
		} catch (IOException e) {
			System.out.println("the path of image doesn't exist.");
		}
	}
	
	public void toLeft() {
		
		righting = false;
		
		TempX = realX - move_speed;

		if (TempX > 0) {

			if (matrix.get(realY / 32)[TempX / 32].equals("1")) {

				realX = realX / 32 * 32;
			} else {

				realX = TempX;
				lefting = true;
			}
		}
	}
	
	public void toRight() {
		
		lefting = false;
		
		TempX = realX + move_speed;

		if (TempX < map.WIDTH - 32) {

			if (matrix.get(realY / 32)[(TempX + 32) / 32].equals("1")) {

				if (realX % 32 != 0) {
					realX = (realX + 32) / 32 * 32;
				} else {
					
				}
			} else {
				realX = TempX;
				
				righting = true;
			}
		}
	}

	int steps = 20;
	int num = 0;
	public void isjump(){
		
		if(num >= steps){
			
			jumping = false;
			num = 0;
		}else{
			
			jumping = true;
			
			TempY = realY - jump_speed;
			
			if(TempY > 0){
				
				if(matrix.get(TempY / 32)[realX / 32].equals("1")){
					
					realY = realY / 32 * 32;
					jumping = false;
				}else{
					realY = TempY;
				}
			num++;
			}
			
		}
	}
	
	public void isfall() {

		TempY = realY + fall_speed;
		
		if ((TempY + 32) < map.HEIGHT) {
			
			if (matrix.get((TempY + 32) / 32)[realX / 32].equals("1")) {
				if(realY % 32 == 0){
					
				}else{
					realY = realY / 32 * 32 + 32;
				}
				falling = false;
			}else{
				realY = TempY;
				falling = true;
			}
		}else{
			System.out.println("Game Over!");
			alive = false;
		}
	}
	
	public void keyPressed(int key){
		
//		if(key == KeyEvent.VK_A){
//			toLeft();
//		}else if(key == KeyEvent.VK_D){
//			toRight();
//		}else if(key == KeyEvent.VK_W){
//			isjump();
//		}
		
		if(key == KeyEvent.VK_A){
			System.out.println("pressed: " + "A");
		}
	}
	
	public void keyReleased(int key) {
		
//		if(key == KeyEvent.VK_A){
			System.out.println("released: " + "A");
//		}
	}
	
	public void KeyTyped(int key){
		
//		if(key == KeyEvent.VK_A){
			System.out.println("typed: " + "A");
//		}
	}
	
	public void update() {
		
		if(!jumping){
			isfall();
		}

		if (jumping) {
			if (!falling) {
				isjump();
			}
		}

		map.realX = realX;
		map.realY = realY;
	}
	
	public void draw(Graphics g){
		
	}

}
