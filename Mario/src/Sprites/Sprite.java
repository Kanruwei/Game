package Sprites;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Background.Map;

public class Sprite {
	
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
	public int jump_speed = 4;
	
	public int step = 32;
	
	public Image image;
	public String path_image = "res/Sprites/mario.png";
	
	public Sprite(Map map){
		
		this.map = map;
		this.matrix = map.matrix;
		this.realX = map.realX;
		this.realY = map.realY;
		
		try {
			image = ImageIO.read(new File(path_image));
			map.hero = image;
		} catch (IOException e) {
			System.out.println("the path of image doesn't exist.");
		}
	}
	
	public void toLeft(){
		
		if(!falling){
			TempX = realX - move_speed;

			if(TempX > 0){

				if(matrix.get(realY / 32)[TempX / 32].equals("1")){

					realX = realX / 32 * 32;
				}else{
					
					realX = TempX;
				}
			}
		}
	}
	
	public void toRight(){
		
		if(!falling){
			TempX = realX + move_speed;

			if(TempX < map.WIDTH - 32){

				if(matrix.get(realY / 32)[(TempX + 32) / 32].equals("1")){
					
					if(realX % 32 != 0){
						realX = (realX + 32) / 32 * 32;
					}else{
						
					}
				}else{
					realX = TempX;
				}
			}
		}
	}
	
	public void update(){
		
		map.realX = realX;
		map.realY = realY;
	}
	
	public void draw(Graphics g){
		
	}

}
