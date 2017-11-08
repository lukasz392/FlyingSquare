package ptaki.gra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.Random;

public class Tube extends GameObject{

	private int gapHeight;
	private int tubeWight = 60;
	private int gap = 120;
	
	public Tube(int x, int y, ID id) {
		super(x, y, id);
		Random rand = new Random();
		this.gapHeight = rand.nextInt(Game.HEIGHT);
		if((Game.HEIGHT - gapHeight) < gap)
			gapHeight = gap;
		velX = -5;
		velY = 0;
	}
	public Area getBounds() {
		Area a = new Area(new Rectangle(x, y, tubeWight, gapHeight));
		Area b = new Area(new Rectangle(x, gapHeight + gap, tubeWight, Game.HEIGHT - (gapHeight + gap)));
		a.add(b);
		return a;
	}
		
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	public void jump(int a) {
		
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, tubeWight, gapHeight);
		g.fillRect(x, gapHeight + gap, tubeWight, Game.HEIGHT - (gapHeight + gap));

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.draw(getBounds());
		
	}
	public int getTubeWight() {
		return tubeWight;
	}

}
