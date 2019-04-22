package source;

import javax.swing.JFrame;

public class MainMenuFrame {
	public static final int HEIGHT = 720, WIDTH = 640;
	public static JFrame window;
	private Board board;

	public MainMenuFrame() {
		window = new JFrame(
				"Game Legendaris Klasik Balok Susun Lucu Warna-warni Hanya menyerupai Tapi Bukan TETRIS 2019");
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setLocationRelativeTo(null);

		window.add(new MainMenuPanel());
		window.setVisible(true);

	}

	public static void main(String[] args) {
		new MainMenuFrame();
	}

}
