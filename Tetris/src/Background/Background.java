package Background;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Cube.Cube;
import GamePanel.Game;

public class Background {
	
	// Color
	public Color Background = new Color(240, 248, 255);
	public Color Frame = new Color(0, 191, 255);

	// Location of Frame
	public static int FrameWidth = Cube.SIZE * 10;
	public static int FrameHeight = Cube.SIZE * 20;
	public static int FrameX = (Game.WIDTH / 2 - FrameWidth) / 2 + Game.WIDTH / 2;
	public static int FrameY = (Game.HEIGHT - FrameHeight) / 2;
	
	//Cube initial location
	public static Point CubeLocation = new Point(FrameX + FrameWidth / 2, FrameY + Cube.SIZE);
	
	//Stack
	public static ArrayList<Point> Stack = new ArrayList<Point>();
	public static ArrayList<int[]> needDelete = new ArrayList<int[]>();
	
	//scroe
	public static int record = 0;
	
	//text
	public String path = null;
	public ArrayList<String[]> score = new ArrayList<String[]>();
	public Font Font_Title;
	public Font Font_Artikel;
	
	public String title = "Ruwei Kan's Tetris";
	
	public Background(){
		path = "res/score.txt";
		Font_Title = new Font("Arial", Font.BOLD, 20);
		Font_Artikel = new Font("Arial", Font.BOLD, 14);
		
		String lines = null;
		BufferedReader txt = null;
		
		try {
			txt = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while((lines = txt.readLine()) != null){
				String[] line = lines.split(",");
				score.add(line);
				System.out.println(lines);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fillStack(ArrayList Points) {
		Stack.addAll(Points);
		check();
	}

	public static void check() {

		int gap = Cube.SIZE;
		int startX = FrameX;
		int startY = FrameY + FrameHeight - gap;

		Point next = new Point();
		
		for (int y = startY; y > FrameY; y = y - gap) {
			int num = 0;
			LabelY: 
			for (int x = startX; x < startX + FrameWidth; x = x + gap) {
				next.setLocation(x, y);
				if (Stack.contains(next)) {
					num++;
					if (num == 10) {
						int[] need = { y, 0 };
						needDelete.add(need);
					}
				} else {
					break LabelY;
				}
			}
		}

		if (!needDelete.isEmpty()) {
			cubeDown();
			needDelete.clear();
		}
			
	}
	
	public static void cubeDown() {
		record = needDelete.size() * 10 + record;
		ArrayList<Point> Temp_Stack = new ArrayList<Point>();
		
		for (int index = needDelete.size() - 1; index >= 0; index--) {
			int numOfline = needDelete.get(index)[0];
			for (int i = 0; i < Stack.size(); i++) {
				Point element = Stack.get(i);
				if (element.y < numOfline) {
					element.translate(0, Cube.SIZE);
					Temp_Stack.add(element);
				}else if(element.y > numOfline){
					Temp_Stack.add(element);
				}
			}
		}
		Stack = Temp_Stack;
	}
	
	public void draw(Graphics g) {

		g.setColor(Background);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		g.setColor(Frame);
		g.drawRect(FrameX, FrameY, FrameWidth, FrameHeight);

		g.setFont(Font_Title);
		g.drawString(title, 50, 50);
		
		g.setFont(Font_Artikel);
		int y_score = 100;
		for (int i = 0; i < score.size() + 1; i++) {
			
			if (i == score.size()) {
				g.setColor(new Color(255, 106, 106));
				g.drawString("Now", 50, y_score);
				g.drawString(String.valueOf(record), 250, y_score);
			} else {
				String[] strings = score.get(i);
				g.drawString(strings[0], 50, y_score);
				g.drawString(strings[1], 250, y_score);
			}

			y_score = y_score + 50;
		}

		Iterator<Point> next = Stack.iterator();
		while (next.hasNext()) {

			Point element = next.next();
			g.setColor(Frame);
			g.fillRect(element.x, element.y, Cube.SIZE, Cube.SIZE);

			g.setColor(Cube.line);
			g.drawRect(element.x, element.y, Cube.SIZE, Cube.SIZE);
		}
	}
}
