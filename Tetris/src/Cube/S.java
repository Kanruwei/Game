package Cube;

import java.awt.Point;

public class S extends Cube{
	
	public Point Point_1 = new Point(Point_0.x + SIZE, Point_0.y);
	public Point Point_2 = new Point(Point_0.x - SIZE, Point_0.y + SIZE);
	public Point Point_3 = new Point(Point_0.x, Point_0.y + SIZE);
	
	public S( ){
		super();
		Sharp.add(Point_1);
		Sharp.add(Point_2);
		Sharp.add(Point_3);
	}

}

