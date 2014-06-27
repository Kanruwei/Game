package Background;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import vectors.Particle;
import vectors.ParticleGruppe;

public class Map {

	public ParticleGruppe Gruppe = Particle.Gruppe;

	public Particle a = new Particle(320, 320, 6.4, 0.0);
//	public Particle b = new Particle(480, 160, -6.4, 0.0);

	public Map() {

	}

	public void update() {

		Gruppe.update();
	}

	public void draw(Graphics g) {
		
		//draw black background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 640);
		
		g.setColor(Color.WHITE);
		Gruppe.draw(g);
	}
}
