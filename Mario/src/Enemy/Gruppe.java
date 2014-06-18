package Enemy;

import java.awt.Graphics;
import java.util.ArrayList;


public class Gruppe{

	public boolean Empty = false;
	
	public ArrayList<Npg> npgs = new ArrayList<Npg>();
	
	public void add(Npg npg){
		
		npgs.add(npg);
	}
	
	public void move(){
		
	}
	
	public void CollideSprite(){
		
	}
	
	public boolean Empty(){
		
		return Empty;
	}
	
	public void updata() {

		for (Npg npg : npgs) {

			npg.update();
		}
	}
	
	public void draw(Graphics g){
		
		for(Npg npg: npgs){
			
			g.drawImage(npg.image, (int)npg.rect.getX(), (int)npg.rect.getY(), null);
		}
	}
}
