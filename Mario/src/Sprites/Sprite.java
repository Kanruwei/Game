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
	
	public int refX;
	public int refY;
	
	public int step = 32;
	
	public Image image;
	public String path_image = "res/Sprites/mario.png";
	
	public Sprite(Map map){
		
		this.map = map;
		this.matrix = map.matrix;
		realX = map.bornX;
		realY = map.bornY;
		refX = realX - map.dx;
		refY = realY - map.dy;
		
		
		try {
			image = ImageIO.read(new File(path_image));
		} catch (IOException e) {
			System.out.println("the path of image doesn't exist.");
		}
	}
	
	public void move(String direction){
		
		if (direction.equals("left")){
			refX = refX - step;
		}else if (direction.equals("right")){
			refX = refX + step;
		}
	}
	
	public void update(){

	}
	
	public void draw(Graphics g){
		
		g.drawImage(image, refX, refY, null);
	}

}
