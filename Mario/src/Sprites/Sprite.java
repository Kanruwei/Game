package Sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Background.Map;
import Background.MapManager;

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
	public boolean isjump = false;
	public boolean lefting = false;
	public boolean righting = false;
	
	public boolean direction_left = false;
	public boolean direction_right = false;
	
	public int move_speed = 2;
	public int jump_speed = 4;
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
		
		TempX = realX - move_speed;

		if (TempX > 0) {

			if (matrix.get(realY / 32)[TempX / 32].equals("1")) {

				realX = realX / 32 * 32;
			} else {

				realX = TempX;
			}
		}
	}
	
	public void toRight() {
		
		TempX = realX + move_speed;

		if (TempX < map.WIDTH - 32) {

			if (matrix.get(realY / 32)[(TempX + 32) / 32].equals("1")) {

				if (realX % 32 != 0) {
					realX = (realX + 32) / 32 * 32;
				} else {
					
				}
				righting = false;
			} else {
				realX = TempX;
			}
		}
	}

	int steps = 32;
	int num = 0;
	public void toJump(){
		
		isjump = true;
		move_speed = 4;
		
		if(num >= steps){
			jumping = false;
			falling = true;
			num = 0;
		}else{
			
			jumping = true;
			
			TempY = realY - jump_speed;
			
			if(TempY > 0){
				
				if(matrix.get(TempY / 32)[realX / 32].equals("1")){
					
					realY = realY / 32 * 32;
					num = 0;
					jumping = false;
					isjump = false;
					falling = true;
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
				isjump = false;
				//move speed change.
				move_speed = 2;
			}else{
				realY = TempY;
				falling = true;
			}
		}else{
			System.out.println("Game Over!");
			alive = false;
		}
	}
	
	public void keyPressed(int key) {

		if (!falling && !isjump) {

			if (key == KeyEvent.VK_A) {
				if (!righting) {
					lefting = true;
					toLeft();
				}
			} else if (key == KeyEvent.VK_D) {
				if (!lefting) {
					righting = true;
					toRight();
				}
			}

			if (key == KeyEvent.VK_W) {
				if (lefting) {
					toJump();
				} else if (righting) {
					toJump();
				} else {
					toJump();
				}
			}
		}
	}
	
	public void keyReleased(int key) {

		if (!isjump) {

			if (key == KeyEvent.VK_A) {
				lefting = false;
			}

			if (key == KeyEvent.VK_D) {
				righting = false;
			}
		}
	}
	
	public void KeyTyped(int key){
		
	}
	
	public void update() {

		if (isjump) {
			if (jumping) {
				toJump();
			} else {
				isfall();
			}

			if (isjump) {
				if (lefting) {
					toLeft();
				} else if (righting) {
					toRight();
				}
			}
		} else {

			if (lefting) {
				toLeft();
			} else if (righting) {
				toRight();
			}
			isfall();
		}

		map.realX = realX;
		map.realY = realY;

	}
	
	public void draw(Graphics g){
		
	}

}
