package vectors;

import java.awt.Graphics;

public class Particle {
	
	public double mass;
	
	public Vector vPosition = new Vector(100, 100);
	public Vector vVelocity = new Vector(64, 0);
	public double speed;
	public Vector vForce;
	public double radius = 16;
	public Vector vGravity;
	
	public Particle(){
		
		speed = this.vVelocity.magnitude();
	}
	
	public void Collide(){
		
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
		g.fillOval((int)(this.vPosition.getX() - this.radius), (int)(this.vPosition.getY() - this.radius), 32, 32);
	}

}
