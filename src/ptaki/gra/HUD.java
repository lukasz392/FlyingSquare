package ptaki.gra;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {


	private int score = 0;
	private int ticks = 0;
	
	public void tick() {
		ticks++;
	}
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 10, 40);
		g.drawString("Ticks: " + ticks, 10, 60);
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public int getTicks() {
		return ticks;
	}
	
}
