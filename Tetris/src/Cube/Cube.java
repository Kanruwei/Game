package Cube;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


import java.util.Iterator;

import Background.Background;

public class Cube {
	
	//state
	public boolean alive = false;
	
	//size
	public static int SIZE = 24;
	public static int gap = 2;
	
	//Arraylist
	public Point Point_0;
	public ArrayList<Point> Sharp = new ArrayList<Point>();
	
	//Color
	public static Color line = new Color(240, 248, 255);
	public static Color kern = new Color(0, 0, 255);
	
	//direction
	public String direction;
	
	//temp
	public ArrayList<Point> Temp;
	
	public Cube(){
		alive = true;
		Point_0 = Background.CubeLocation;
		Sharp.add(Point_0);
	}
	
	public void rotate(String direction){
		
		Temp = new ArrayList<Point>();
		
		if(direction.equals("left")){
			toLeft();
		}else if(direction.equals("right")){
			toRight();
		}
	}
	
	public void toLeft(){
		Iterator<Point> next = Sharp.iterator();
		Point center = next.next();
		Temp.add(center);
		
		int dx = 0;
		int dy = 0;
		
		while(next.hasNext()){
			Point element = next.next();
			dx = element.x - center.x;
			dy = element.y - center.y;
			
			Temp.add(new Point(center.x + dy, center.y - dx));
		}
		
		check(Temp);
		
	}
	
	public void toRight(){
		Iterator<Point> next = Sharp.iterator();
		Point center = next.next();
		Temp.add(center);
		
		int dx = 0;
		int dy = 0;
		
		while(next.hasNext()){
			Point element = next.next();
			dx = element.x - center.x;
			dy = element.y - center.y;
			
			Temp.add(new Point(center.x - dy, center.y + dx));
		}
		
		check(Temp);
		
	}
	
	public void moving(String moving){
		
		Temp = new ArrayList<Point>();
		Iterator<Point> next = Sharp.iterator();
		
		if(moving.equals("a")){
			while(next.hasNext()){
				Point element = next.next();
				Temp.add(new Point(element.x - SIZE, element.y));
			}
		}else if(moving.equals("d")){
			while(next.hasNext()){
				Point element = next.next();
				Temp.add(new Point(element.x + SIZE, element.y));
			}
		}else if(moving.equals("down")){
			while(next.hasNext()){
				Point element = next.next();
				Temp.add(new Point(element.x, element.y + SIZE));
			}
		}
		check(Temp);
	}
	
	public void check(ArrayList<Point> Temp){
		
		Iterator<Point> next = Temp.iterator();
		boolean in = true;
		
		Label_while:
		while(next.hasNext()){
			Point element = next.next();
			
			if(element.x < Background.FrameX || element.x >= (Background.FrameX + Background.FrameWidth)){
				in = false;
			}
			
			if(element.y == (Background.FrameY + Background.FrameHeight) || Background.Stack.contains(element)){
				in = false;
				alive = false;
				Background.fillStack(Sharp);
				break Label_while;
			}
		}
		
		if(in){
			Sharp = Temp;
		}
		
	}
	
	public void downing(){
		
		Temp = new ArrayList<Point>();
		Iterator<Point> next = Sharp.iterator();
		
		while(next.hasNext()){
			Point element = next.next();
			Temp.add(new Point(element.x, element.y + SIZE));
		}
		
		check(Temp);
	}
	
	public void draw(Graphics g){
		
		Iterator<Point> next = Sharp.iterator();
		
		while(next.hasNext()){
			Point element = next.next();
			g.setColor(kern);
			g.fillRect(element.x, element.y, SIZE, SIZE);
			
			g.setColor(line);
			g.drawRect(element.x, element.y, SIZE, SIZE);
			
		}
	}
	
	public void draw2(Graphics g) {
		Iterator<Point> next = Sharp.iterator();

		while (next.hasNext()) {
			Point element = next.next();
			g.setColor(kern);
			g.fillRect(element.x, element.y - Background.FrameY / 2 - 2 * SIZE, SIZE, SIZE);

			g.setColor(line);
			g.drawRect(element.x, element.y - Background.FrameY / 2 - 2 * SIZE, SIZE, SIZE);

		}
	}
	

}
