package vectors;

import java.awt.Graphics;

public class Particle {

	public static double _g = 1.2544;
	public static double _e = 0.9;

	public double mass = 10;

	public Vector vPosition = new Vector(32, 32);
	public Vector vVelocity = new Vector(6.4, 0);
	public double speed;
	public double radius = 16;

	public Vector vForce = new Vector(0.0, 0.0);
	public Vector vGravity;

	public boolean beCollision = false;
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

		boolean hasCollision = false;

		this.impactForce.clear();

		n.setY(-1.0);
		vr = this.vVelocity;

		vrn = vr.dot(n);

		if (vrn < 0) {
			vrn = -vrn;
		}

		J = vrn * (_e + 1) * mass;
		F = n;
		F = F.time(J);

		this.vForce = this.vForce.plus(F);

		beCollision = false;
	}

	public void caluloads() {

		this.vForce.clear();
		this.vForce = this.vForce.plus(this.vGravity);

		if (beCollision) {
			collision();
		}
	}

	public void updatebodyeuler() {

		Vector a;
		Vector dv;
		Vector ds;

		a = this.vForce.divide(mass);
		dv = a.time(1);
		this.vVelocity = this.vVelocity.plus(dv);

		ds = this.vVelocity.time(1);
		this.vPosition = this.vPosition.plus(ds);

		// check the region
		if (vPosition.getY() - radius <= 0) {
			beCollision = true;
			vPosition.setY(radius);
		} else if (vPosition.getY() + radius >= 640) {
			beCollision = true;
			vPosition.setY(640 - radius);
		} else if (vPosition.getX() - radius <= 0) {
			beCollision = true;
			vPosition.setX(radius);
		} else if (vPosition.getX() >= 800) {
			beCollision = true;
			vPosition.setX(800 - radius);
		}
		
		//speed set
		this.speed = this.vVelocity.magnitude();
	}

	public void update() {

		caluloads();
		updatebodyeuler();
	}

	public void draw(Graphics g) {

		g.fillOval((int) (this.vPosition.getX() - this.radius),
				(int) (this.vPosition.getY() - this.radius), 32, 32);
		
		g.fillOval(100, (int) (640 - 2 * radius), 32, 32);
	}

}
