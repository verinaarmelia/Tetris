package source;

import javax.swing.JFrame;

public class WindowFrame extends JFrame {

	public static final int WIDTH = 640, HEIGHT = 720;

	public WindowFrame() {
		setTitle("Game Legendaris Klasik Susun Balok Lucu Warna-Warni Hanya Menyerupai tapi Bukan TETRIS 2019");

		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Board board = new Board();
		add(board);

		setGlassPane(new GlassPane());
		getGlassPane().setVisible(true);
		addKeyListener(board);

		setVisible(true);
	}

}
