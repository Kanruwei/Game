package vectors;

public class Vector {

	public double x;
	public double y;

	public Vector(double a, double b) {

		x = a;
		y = b;
	}

	public double magnitude() {

		double value = 0.0;

		value = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return value;
	}

	public Vector normalize() {

		Vector value = new Vector(x / this.magnitude(), y / this.magnitude());

		return value;
	}

	public double angle() {

		double temp = Math.atan(y / x);
		if (temp == -0.0) {
			temp = Math.toRadians(180);
		}
		return temp;
	}

	public double theta(Vector b) {

		double theta = this.angle() - b.angle();
		return theta;
	}

	public double dot(Vector b) {

		double dotproduct = Math.cos(this.theta(b)) * this.magnitude()
				* b.magnitude();
		return dotproduct;
	}

	public void cross(Vector b) {

	}

	public Vector minus(Vector b) {

		Vector temp = new Vector(this.x - b.x, this.y - b.y);
		return temp;
	}

	public Vector plus(Vector b) {

		Vector temp = new Vector(this.x + b.x, this.y + b.y);
		return temp;
	}

	public Vector divide(double mass) {

		Vector temp = new Vector(this.getX() / mass, this.getY() / mass);
		return temp;
	}

	public Vector time(double value) {

		Vector temp = new Vector(this.getX() * value, this.getY() * value);
		return temp;
	}

	public double[] getLocation() {

		double[] location = { x, y };

		return location;
	}

	public void setX(double a) {

		x = a;
	}

	public void setY(double b) {

		y = b;
	}

	public double getX() {

		return x;
	}

	public double getY() {

		return y;
	}

	public void clear() {

		this.setX(0.0);
		this.setY(0.0);
	}

	public void show() {

		System.out.println("X: " + this.getX() + " Y: " + this.getY());
	}
}
