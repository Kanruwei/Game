package Background;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import vectors.Particle;

public class Map {

	public ArrayList<Particle> gruppe = new ArrayList<Particle>();

	public Particle a = new Particle();

	public Map() {

		gruppe.add(a);
	}

	public void update() {

		for (Particle i : gruppe) {
			i.update();
		}
	}

	public void draw(Graphics g) {
		
		//draw black background
//		g.setColor(Color.BLACK);
//		g.fillRect(0, 0, 800, 640);
		
		g.setColor(Color.WHITE);
		for (Particle i : gruppe) {
			i.draw(g);
		}
	}
}
