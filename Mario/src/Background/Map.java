package Background;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Enemy.Gruppe;
import Enemy.Npg;
import Frame.GamePanel;
import Sprites.Sprite;


public class Map {
	
	public Sprite hero;
	
	//path of image and text file.
	public String path_image = null;
	public String path_txt = null;
	public String path_bricks = null;
	
	//String[] of Map
	public ArrayList<String[]> matrix = new ArrayList<String[]>();
	
	public int WIDTH = GamePanel.WIDTH * 2;
	public int HEIGHT = GamePanel.HEIGHT * 2;
	
	public int WIDTH_MAP = WIDTH / 2;
	public int HEIGHT_MAP = HEIGHT / 2;
	
	public int dx = 0;
	public int dy = HEIGHT / 4;
	
	public Image brick;
	public Image hero_image;
	
	//location of sprite
	public int bornX = 5 * 32;
	public int bornY = 19 * 32;
	
	public int realX = bornX;
	public int realY = bornY;
	
	//gruppe of npgs
	public Gruppe gruppe = new Gruppe();
	
	//location of npgs
	public ArrayList<Point> Location_Npg = new ArrayList<Point>();
	
	public BufferedImage origin;
	public BufferedImage originMix;
	public BufferedImage map;
	
	public Graphics g_origin;
	public Graphics g_originMix;
	public Graphics g_map;
	
	public void init(Sprite hero){
		
		this.hero = hero;
		
		Image image = null;
		
		//create origin image
		origin = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g_origin = origin.createGraphics();
		
		try {
			image = ImageIO.read(new File(path_image));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image != null){
			g_origin.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		}else{
			System.out.println("original image is null...");
		}
		
		BufferedReader text = null;
		String line = null;
		
		//draw bricks
		try {
			brick = ImageIO.read(new File(path_bricks));
			
			text = new BufferedReader(new FileReader(path_txt));
			
			int y = 0;
			while ((line = text.readLine()) != null) {

				String[] StringLine = line.split(" ");
				matrix.add(StringLine);
				for (int x = 0; x < StringLine.length; x++) {
					if (Integer.parseInt(StringLine[x]) == 1) {
						g_origin.drawImage(brick, x * 32, y * 32, null);
					}
				}
				y++;
			}
		} catch (IOException e1) {
			System.out.println("image of Brick, path_txt don't exist. BufferedReader text is null.");
		}
		
		//create origin_hero.
		originMix = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g_originMix = originMix.createGraphics();
		
		//create map, which will be rendered on the screen.
		map = new BufferedImage(WIDTH_MAP, HEIGHT_MAP, BufferedImage.TYPE_INT_RGB);
		g_map = map.createGraphics();
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
		//draw origin
		g_originMix.drawImage(origin, 0, 0, null);
		
		//draw hero
		g_originMix.drawImage(hero_image, realX, realY, null);
		
		//draw ngps
		gruppe.draw(g_originMix);
		
		//define dx.
		if(realX < WIDTH / 4){
			dx = 0;
		}else if(realX > WIDTH - WIDTH_MAP / 2){
			dx = WIDTH - WIDTH_MAP;
		}else{
			dx = realX - WIDTH_MAP / 2;
		}
		
		//define dy.
		if(realY >= HEIGHT - (32 + (HEIGHT_MAP - 32) / 2)){
			dy = HEIGHT - HEIGHT_MAP;
		}else if(realY <= (HEIGHT_MAP - 32) / 2){
			dy = 0;
		}else{
			dy = realY - 304;
		}
		
        //map = originMix.getSubimage(dx, dy, WIDTH_MAP, HEIGHT_MAP);
		g_map.drawImage(originMix.getSubimage(dx, dy, WIDTH_MAP, HEIGHT_MAP), 0, 0, null);
		
		//FPS
		g_map.drawString(GamePanel.los + "/" + GamePanel.FPS, 100, 50);
		
		g.drawImage(map, 0, 0, null);
	}
}
