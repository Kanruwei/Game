package vectors;

import java.awt.Graphics;
import java.util.ArrayList;

public class ParticleGruppe {
	
	public ArrayList<Particle> Gruppe = new ArrayList<Particle>();
	
	public void add(Particle object){
		
		Gruppe.add(object);
	}
	
	public void remove(Particle object){
		
		Gruppe.remove(object);
	}
	
	public void checkCollision(){
		
	}
	
	public void caluForce(){
		
	}
	
	public void caluDistance(){
		
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		System.out.println("working...");
		
		for(Particle object: Gruppe){
			object.draw(g);
		}
	}
}
