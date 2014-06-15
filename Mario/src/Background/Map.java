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
	
	//location of sprite
	public int bornX = 2 * 32;
	public int bornY = 23 * 32;
	
	public int WIDTH = GamePanel.WIDTH * 2;
	public int HEIGHT = GamePanel.HEIGHT * 2;
	
	public int dx = 0;
	public int dy = HEIGHT / 4;
	
	public Image brick;
	
	public BufferedImage origin;
	public BufferedImage map;
	
	public Graphics g_origin;
	
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
		
		//create map, which will be rendered on the screen.
		map = new BufferedImage(WIDTH / 2, HEIGHT / 2, BufferedImage.TYPE_INT_RGB);
		map = origin.getSubimage(dx, dy, WIDTH / 2, HEIGHT / 2);
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics g){
		
		g.drawImage(map, 0, 0, null);
	}
}
