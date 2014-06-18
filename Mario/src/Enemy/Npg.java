package Enemy;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Background.Map;

public class Npg {
	
	public String path_image = "res/Sprites/frog.png";
	public Image image;
	
	public int speed = 4;
	
	public int X;
	public int Y;
	
	public int tempX;
	public int tempY;
	
	public Rectangle rect;
	
	public Gruppe gruppe;
	public Map map;

	public Npg(Map map, Gruppe gruppe){
		
		this.map = map;
		this.gruppe = gruppe;
		
		//image reading
		try {
			image = ImageIO.read(new File(path_image));
		} catch (IOException e) {
			System.out.println("image of npg isn't be found.");
		}
		
		rect = new Rectangle(map.Location_Npg.get(gruppe.npgs.size()));
		
		this.add(gruppe);
	}
	
	public void add(Gruppe gruppe){
		
		gruppe.add(this);
	}
	
	public void patrol(){
		
	}
	
	public void collideSprite(){
		
	}
	
	public void update(){
		
		patrol();
	}
}
