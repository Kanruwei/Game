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
		
		for(Particle object: Gruppe){
			object.checkCollision();
		}
	}
	
	public void caluForce(){
		
		for(Particle object: Gruppe){
			object.caluForce();;
		}
	}
	
	public void caluDistance(){
		
		for(Particle object: Gruppe){
			object.caluDistance();;
		}
	}
	
	public void update(){
		
		checkCollision();
		caluForce();
		caluDistance();
	}
	
	public void draw(Graphics g){
		System.out.println("working...");
		
		for(Particle object: Gruppe){
			object.draw(g);
		}
	}
}
