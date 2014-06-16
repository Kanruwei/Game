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
	public boolean moving = false;
	
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

		if (!falling) {
			TempX = realX - move_speed;

			if (TempX > 0) {

				if (matrix.get(realY / 32)[TempX / 32].equals("1")) {

					realX = realX / 32 * 32;
				} else {

					realX = TempX;
				}
			}
		}
	}
	
	public void toRight() {

		if (!falling) {
			TempX = realX + move_speed;

			if (TempX < map.WIDTH - 32) {

				if (matrix.get(realY / 32)[(TempX + 32) / 32].equals("1")) {

					if (realX % 32 != 0) {
						realX = (realX + 32) / 32 * 32;
					} else {

					}
				} else {
					realX = TempX;
				}
			}
		}
	}
	
	public void isfall() {

		TempY = realY + fall_speed;
		
		System.out.println("realY_row: " + realY / 32+ " TempY_row: " + TempY / 32);

		if ((TempY + 32) < map.HEIGHT) {
			System.out.println("size of matrix: " + matrix.size());
			
			if (matrix.get((TempY + 32) / 32)[realX / 32].equals("1")) {
				System.out.println("next" + ((TempY + 32) / 32));
				if(realY % 32 == 0){
					
				}else{
					realY = realY / 32 * 32 + 32;
				}
				falling = false;
			}else{
				System.out.println("fall!");
				realY = TempY;
				falling = true;
			}
		}else{
			System.out.println("Game Over!");
			alive = false;
		}
	}
	
	public void keyPressed(int key){
		
		if(key == KeyEvent.VK_A){
			toLeft();
		}else if(key == KeyEvent.VK_D){
			toRight();
		}
	}
	
	public void update(){

		isfall();
		
		map.realX = realX;
		map.realY = realY;
	}
	
	public void draw(Graphics g){
		
	}

}
