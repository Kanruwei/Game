package vectors;

import java.awt.Graphics;

//320 pixels equal 1 meter, 50 time equals 1 s.

public class Particle {

	public static double _g = 1.2544;
	public static double _e = 0.9;
	public static double _p = 1.23 / Math.pow(320, 3);
	public static double _cd = 0.6;

	public double mass = 10;

	public Vector vPosition = new Vector(0.0, 0.0);;
	public Vector vVelocity = new Vector(0.0, 0.0);
	public double speed;
	public double radius = 16;

	public Vector vForce = new Vector(0.0, 0.0);
	public Vector vGravity;

	public boolean beCollision = false;
	public boolean beSupport = false;
	public Vector impactForce = new Vector(0.0, 0.0);

	public static ParticleGruppe Gruppe = new ParticleGruppe();

	public Particle(double x, double y, double vx, double vy) {

		vPosition.setX(x);
		vPosition.setY(y);

		vVelocity.setX(vx);
		vVelocity.setY(vy);

		speed = vVelocity.magnitude();
		vGravity = new Vector(0.0, mass * _g);

		Gruppe.add(this);
	}

	public void caluForce() {

		// Gravity
		vForce = vForce.plus(vGravity);

		// air Drag
		Vector vDrag;
		double dDrag;

		vDrag = new Vector(-vVelocity.getX(), -vVelocity.getY());
		vDrag.normalize();

		dDrag = 0.5 * _p * speed * speed * (radius * radius * 3.14159) * _cd;
		vDrag = vDrag.time(dDrag);

		this.vForce = this.vForce.plus(vDrag);

	}

	public void caluDistance() {

		Vector a;
		Vector dv;
		Vector ds;

		a = this.vForce.divide(mass);
		dv = a;
		this.vVelocity = this.vVelocity.plus(dv);

		ds = this.vVelocity.time(1);
		this.vPosition = this.vPosition.plus(ds);

		// speed set
		this.speed = this.vVelocity.magnitude();

		// clear all force
		vForce.clear();
	}

	public void checkCollision() {

		Vector n = new Vector(0.0, 0.0);
		Vector vr = null;
		double vrn = 0.0;
		double J;
		Vector F;

		// check the region
		if (vPosition.getY() - radius <= 0) {
			beCollision = true;
			vPosition.setY(radius);
			n.setY(1.0);
		} else if (vPosition.getY() + radius >= 640) {
			beCollision = true;
			vPosition.setY(640 - radius);
			n.setY(-1.0);
		}

		if (vPosition.getX() - radius <= 0) {
			beCollision = true;
			vPosition.setX(radius);
			n.setX(1.0);
		} else if (vPosition.getX() + radius >= 800) {
			beCollision = true;
			vPosition.setX(800 - radius);
			n.setX(-1.0);
		}

		// check collision with particle
		int index = 1;
		double dis = 0.0;
		double ddis = 0.0;

		if (Gruppe.Gruppe.size() > 1) {
			for (int i = 0; i < Gruppe.Gruppe.size(); i++) {
				if (this.equals(Gruppe.Gruppe.get(i))) {
					
				} else {
					if (distance(Gruppe.Gruppe.get(i)) <= (this.radius + Gruppe.Gruppe.get(i).radius)) {
						dis = distance(Gruppe.Gruppe.get(i));
						ddis = this.radius + Gruppe.Gruppe.get(i).radius - dis;
						index = i;
						
						Particle object = Gruppe.Gruppe.get(i);
						Vector n2 = new Vector(0.0, 0.0);
						Vector vr2 = null;
						double vrn2 = 0.0;
						double J2;
						Vector F2;
						
						n2.setX(this.vPosition.getX() - object.vPosition.getX());
						n2.setY(this.vPosition.getY() - object.vPosition.getY());
						n2 = n2.normalize();
						
						this.vPosition.setX(this.vPosition.getX() + n2.time(ddis).getX());
						this.vPosition.setY(this.vPosition.getY() + n2.time(ddis).getY());
						
						vr2 = this.vVelocity.minus(object.vVelocity);
						vrn2 = vr2.dot(n2);

						if (vrn2 < 0) {
							vrn2 = -vrn2;
							J2 = vrn2 * (_e + 1) / (1/mass + 1/object.mass);
							F2 = n2;
							F2 = F2.time(J2);
							vForce = vForce.plus(F2);
						}
					}
				}
			}
		}

		if (beCollision) {

			vr = this.vVelocity;
			vrn = vr.dot(n);

			if (vrn < 0) {
				vrn = -vrn;
				J = vrn * (_e + 1) * mass;
				F = n;
				F = F.time(J);

				if (Math.abs(F.getY() + mass * _g) <= 0.5
						|| (this.vVelocity.getY() == 0 && this.vPosition.getY() != 0)) {
					F.setY(-mass * _g);
					vVelocity.setY(0.0);
				}
				vForce = vForce.plus(F);
			}
			beCollision = false;
		}
	}

	public double distance(Particle object) {

		double distance = Math.sqrt(Math.pow(
				(vPosition.getX() - object.vPosition.getX()), 2)
				+ Math.pow((vPosition.getY() - object.vPosition.getY()), 2));
		
		return distance;
	}

	public void update() {

		caluForce();
		caluDistance();
		checkCollision();
	}

	public void draw(Graphics g) {

		g.fillOval((int) (this.vPosition.getX() - this.radius),
				(int) (this.vPosition.getY() - this.radius), 32, 32);
	}

}
