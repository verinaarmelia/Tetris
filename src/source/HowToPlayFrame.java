package source;

import javax.swing.JFrame;

public class HowToPlayFrame {
	public static JFrame window;
	public static final int HEIGHT = 720, WIDTH = 640;

	public HowToPlayFrame() {
		window = new JFrame(
				"Game Legendaris Klasik Balok Susun Lucu Warna-warni Hanya menyerupai Tapi Bukan TETRIS 2019");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);

		window.add(new HowToPlayPanel());
		window.setVisible(true);
	}

}
