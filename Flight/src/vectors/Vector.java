package vectors;

public class Vector {
	
	public double x;
	public double y;
	
	public double angle;
	
	public Vector(double a, double b) {

		x = a;
		y = b;
		
		angle = Math.atan(a / b);
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
	
	public double theta(Vector b){
		
		double theta = this.angle - b.angle;
		return theta;
	}
	
	public double dot(Vector b){
		
		double dotproduct = Math.cos(this.theta(b)) * this.magnitude() * b.magnitude();
		return dotproduct;
	}
	
	public void cross(Vector b){
		
	}
	
	public Vector minus(Vector b){
		
		Vector temp = new Vector(this.x - b.x, this.y - b.y);
		return temp;
	}
	
	public Vector plus(Vector b){
		
		Vector temp = new Vector(this.x + b.x, this.y + b.y);
		return temp;
	}
	
	public double[] getLocation(){
		
		double[] location = {x, y};
		
		return location;
	}
	
	public double getX(){
		
		return x;
	}
	
	public double getY(){
		
		return y;
	}
}
