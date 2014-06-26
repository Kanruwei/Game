package vectors;

import java.awt.Graphics;
import java.util.ArrayList;

//320 pixels equal 1 meter, 50 timestep equals 1 s.

public class Particle {

	// public static ParticleGruppe gruppe = new ParticleGruppe();

	public static double _g = 1.2544;
	public static double _e = 0.9;
	public static double _p = 30.3 / Math.pow(320, 3);
	public static double _cd = 0.6;

	public double mass = 10;

	public Vector vPosition = new Vector(32, 300);
	public Vector vVelocity = new Vector(6.4, 0);
	public double speed;
	public double radius = 16;

	public Vector vForce = new Vector(0.0, 0.0);
	public Vector vGravity;

	public boolean beCollision = false;
	public boolean fixY = false;
	public String[] artCollision = { "a", "a", "a", "a", "a" };
	public Vector impactForce = new Vector(0.0, 0.0);

	public Particle() {

		speed = this.vVelocity.magnitude();
		vGravity = new Vector(0.0, this.mass * _g);
	}

	public void collision() {

		Vector n = new Vector(0.0, 0.0);
		Vector vr = null;
		double vrn = 0.0;
		double J;
		Vector F;

		impactForce.clear();

		// identify n
		if (artCollision[1].equals("bottom")) {
			n.setY(-1.0);
			artCollision[1] = "a";
		} else if (artCollision[0].equals("top")) {
			n.setY(1.0);
			artCollision[0] = "a";
		}

		if (artCollision[3].equals("right")) {
			n.setX(-1.0);
			artCollision[3] = "a";
		} else if (artCollision[2].equals("left")) {
			n.setX(1.0);
			artCollision[2] = "a";
		}

		vr = this.vVelocity;
		vrn = vr.dot(n);

		if (vrn < 0) {
			vrn = -vrn;
		}

		J = vrn * (_e + 1) * mass;
		F = n;
		F = F.time(J);

		// approximate of F
		if (Math.abs(F.getY() + this.mass * _g) <= 1) {
			F.setY(-12.544);
			vVelocity.setY(0.0);
		}
		
		if(fixY){
			F.setY(-12.544);
		}

		this.vForce = this.vForce.plus(F);

		beCollision = false;
	}

	public void caluloads() {
		
		//check, whether the particle is loaded on the ground.
		if(vForce.getY() == 0 && vVelocity.getY() == 0){
			fixY = true;
		}else{
			fixY = false;
		}
		
		this.vForce.clear();

		if (beCollision) {
			collision();
		}
		// Gravity
		this.vForce = this.vForce.plus(this.vGravity);

		// air Drag
		Vector vDrag;
		double dDrag;

		vDrag = new Vector(-vVelocity.getX(), -vVelocity.getY());
		vDrag.normalize();

		dDrag = 0.5 * _p * speed * speed * (radius * radius * 3.14159) * _cd;
		vDrag = vDrag.time(dDrag);

		this.vForce = this.vForce.plus(vDrag);

	}

	public void updatebodyeuler() {

		Vector a;
		Vector dv;
		Vector ds;

		a = this.vForce.divide(mass);
		dv = a;
		this.vVelocity = this.vVelocity.plus(dv);

		ds = this.vVelocity.time(1);
		this.vPosition = this.vPosition.plus(ds);

		// check the region
		if (vPosition.getY() - radius <= 0) {
			beCollision = true;
			artCollision[0] = "top";
			vPosition.setY(radius);
		} else if (vPosition.getY() + radius >= 640) {
			beCollision = true;
			artCollision[1] = "bottom";
			vPosition.setY(640 - radius);
		}

		if (vPosition.getX() - radius <= 0) {
			beCollision = true;
			artCollision[2] = "left";
			vPosition.setX(radius);
		} else if (vPosition.getX() + radius >= 800) {
			beCollision = true;
			artCollision[3] = "right";
			vPosition.setX(800 - radius);
		}

		// speed set
		this.speed = this.vVelocity.magnitude();
	}

	public void update() {

		caluloads();
		updatebodyeuler();
	}

	public void draw(Graphics g) {

		g.fillOval((int) (this.vPosition.getX() - this.radius),
				(int) (this.vPosition.getY() - this.radius), 32, 32);
	}

}
