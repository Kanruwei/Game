package Background;

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

	}

	public void draw(Graphics g) {

		for (Particle i : gruppe) {
			i.draw(g);
		}
	}
}
