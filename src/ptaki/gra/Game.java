package ptaki.gra;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3069193237234333982L;

	public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;
	public boolean running = false;
	
	private HUD hud;
	private Thread thread;
	private Spawn spawn;
	
	private Handler handler;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Ptaki", this);
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		handler.addObject(new Player(100, 100, ID.Player, handler, spawn));
		
		//handler.addObject(new Tube(WIDTH, 0, ID.Tube));
		
		
		int j = 0;
		//for(int i = 0; i < 50; i++) {
		//	handler.addObject(new Tube(WIDTH + j, 0, ID.Tube));
		//	j += 500;
		//}
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static int clamp(int var, int min, int max) {
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames);
				frames = 0;
			}
		}
		stop();
	}
	public void tick() {
		handler.tick();
		hud.tick();
		spawn.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		hud.render(g);
		g.dispose();
		bs.show();
	}
	public static void main(String[] args) {
		new Game();
	}

	
}
