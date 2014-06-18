package Background;

import java.awt.Point;

import Enemy.Npg;

public class Stage_1 extends Map{
	
	public Point location_1 = new Point(448, 608);

	public Npg npg_1;
	
	public Stage_1() {
		super.path_image = "res/Backgrounds/Stage_1.png";
		super.path_txt = "res/Backgrounds/Stage_1.txt";
		super.path_bricks = "res/Backgrounds/Brick.png";
		
		super.Location_Npg.add(location_1);
		
		npg_1 = new Npg(this, super.gruppe);
	}	
}
