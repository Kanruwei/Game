package Background;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Frame.GamePanel;


public class Map {
	
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
	public Image hero;
	
	//location of sprite
	public int bornX = 4 * 32;
	public int bornY = 23 * 32;
	
	public int realX = bornX;
	public int realY = bornY;
	
	public BufferedImage origin;
	public BufferedImage originMix;
	public BufferedImage map;
	
	public Graphics g_origin;
	public Graphics g_originMix;
	
	public void init(){
		
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
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
		//draw origin
		g_originMix.drawImage(origin, 0, 0, null);
		
		//draw hero
		g_originMix.drawImage(hero, realX, realY, null);
		
		//draw map
		if(realX < WIDTH / 4){
			
			dx = 0;
			map = originMix.getSubimage(dx, dy, WIDTH_MAP, HEIGHT_MAP);
		}else if(realX > WIDTH - WIDTH_MAP / 2){
			
			dx = WIDTH - WIDTH_MAP;
			map = originMix.getSubimage(dx, dy, WIDTH_MAP, HEIGHT_MAP);
		}else{
			
			dx = realX - WIDTH_MAP / 2;
			System.out.println("dx: " + dx + " realX: " + realX + " WIDTH_MAP: " + WIDTH_MAP);
			map = originMix.getSubimage(dx, dy, WIDTH_MAP, HEIGHT_MAP);
		}
		
		g.drawImage(map, 0, 0, null);
	}
}
