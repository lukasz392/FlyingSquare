package ptaki.gra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class Player extends GameObject{

	private int gravity = 12;
	private int jumpPower = -20;
	private Spawn spawn;
	
	Handler handler;
	
	
	public Player(int x, int y, ID id, Handler handler, Spawn spawn) {
		super(x, y, id);
		this.handler = handler;
		this.spawn = spawn;
		velX = 0;
		velY = gravity;
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		velY++;
		velY = Game.clamp(velY, jumpPower, gravity);
		collision();
	}
	public void jump(int speed) {
		velY = velY - Game.clamp(speed,(-1*jumpPower), gravity);
	}
	public Area getBounds() {
		return new Area(new Rectangle(x, y, 30, 30)); 
		
	}
	public static boolean testIntersection(Area a, Area b) {
		a.intersect(b);
		return !a.isEmpty();
	}
	public void reset() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Tube) {
				handler.removeObject(tempObject);
				x = 100;
				y = 100;
			}
		}
	}
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Tube && testIntersection(tempObject.getBounds(), this.getBounds())) {
				//spawn.setScoreKeep(0);
				handler.removeObject(tempObject);
				reset();
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 30, 30);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.red);
		g2d.draw(getBounds());
		
	}

}
