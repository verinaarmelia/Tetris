package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
	JLabel title, upTitle, upUpTitle;
	JLabel play, exit, howToPlay;
	BufferedImage cube;
	BufferedImage img;
	private String highscore = "0";
	private int currHighscore = 0;
	private int newHighscore = 0;

	private float h = (float) 0.53358333;
	private float s = (float) 0.5697;
	private float b = (float) 0.9569;

	@Override
	public void paint(Graphics g) {
		setBackground(Color.getHSBColor(h, s, b));
		super.paint(g);
		g.drawImage(img, 40, 320, null);
		g.drawImage(cube, 250, 30, 130, 130, null);
	}

	public MainMenuPanel() {
		setLayout(null);

		try {
			img = ImageIO.read(Board.class.getResource("/totoro.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			cube = ImageIO.read(Board.class.getResource("/cube.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// title = new JLabel("TETRIS 2019", JLabel.CENTER);
		// title.setBounds(50, 0, 500, 50);
		// title.setFont(new Font("Helvetica", Font.BOLD, 36));
		// title.setForeground(Color.BLUE);
		// add(title);

		play = new JLabel("Play", JLabel.CENTER);
		play.setBounds(60, 170, 500, 50);
		play.setFont(new Font("Orange Kid", Font.BOLD, 36));
		play.setForeground(Color.BLACK);
		play.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				play.setForeground(Color.BLACK);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				play.setForeground(Color.YELLOW);

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainMenuFrame.window.dispose();
				new WindowFrame();
			}
		});
		add(play);

		howToPlay = new JLabel("How To Play", JLabel.CENTER);
		howToPlay.setBounds(60, 213, 500, 50);
		howToPlay.setFont(new Font("Orange Kid", Font.BOLD, 36));
		howToPlay.setForeground(Color.BLACK);
		howToPlay.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				howToPlay.setForeground(Color.BLACK);

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				howToPlay.setForeground(Color.YELLOW);

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainMenuFrame.window.dispose();
				new HowToPlayFrame();
			}
		});
		add(howToPlay);

		exit = new JLabel("Exit", JLabel.CENTER);
		exit.setBounds(60, 250, 500, 50);
		exit.setFont(new Font("Orange Kid", Font.BOLD, 36));
		exit.setForeground(Color.BLACK);
		exit.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.BLACK);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.YELLOW);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);

			}
		});
		add(exit);

	}

	public void setHighscore(int score) {
		newHighscore = score;
		if (newHighscore > currHighscore) {
			currHighscore = newHighscore;
			highscore = "" + currHighscore;
		}
	}
}