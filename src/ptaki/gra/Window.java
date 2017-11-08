package ptaki.gra;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4035095594286785400L;

	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame();
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
