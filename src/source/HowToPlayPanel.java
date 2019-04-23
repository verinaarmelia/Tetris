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

public class HowToPlayPanel extends JPanel {
	BufferedImage up;
	BufferedImage down;
	BufferedImage right;
	BufferedImage left;
	BufferedImage shift;
	BufferedImage space;
	BufferedImage icon;
	BufferedImage icon1;
	int stat = 0;
	JLabel lbl_title, lbl_up, lbl_down, lbl_right, lbl_left, lbl_shift, lbl_space, lbl_space2, back;

	private float h = (float) 0.53358333;
	private float s = (float) 0.5697;
	private float b = (float) 0.9569;

	@Override
	public void paint(Graphics g) {
		setBackground(Color.getHSBColor(h, s, b));
		super.paint(g);
		g.drawImage(icon, 310, 400, 500, 740, null);
		g.drawImage(icon1, -170, 400, 500, 740, null);
		if (stat == 1)
			g.drawImage(up, 150, 100, 60, 60, null);
		if (stat == 2)
			g.drawImage(down, 150, 160, 60, 60, null);
		if (stat == 3)
			g.drawImage(left, 150, 220, 60, 60, null);
		if (stat == 4) {
			g.drawImage(right, 150, 280, 60, 60, null);
		}
		if (stat == 5) {
			g.drawImage(shift, 150, 400, 60, 60, null);
		}
		if (stat == 6) {
			g.drawImage(space, 150, 340, 60, 60, null);
		}
		if (stat == 7) {
			g.drawImage(space, 150, 450, 60, 60, null);
		}
	}

	public HowToPlayPanel() {
		setLayout(null);
		try {
			icon = ImageIO.read(Board.class.getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			icon1 = ImageIO.read(Board.class.getResource("/icon1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			shift = ImageIO.read(Board.class.getResource("/shift.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			space = ImageIO.read(Board.class.getResource("/space.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			up = ImageIO.read(Board.class.getResource("/up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			down = ImageIO.read(Board.class.getResource("/down.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			left = ImageIO.read(Board.class.getResource("/left.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			right = ImageIO.read(Board.class.getResource("/right.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lbl_title = new JLabel("HOW TO PLAY", JLabel.CENTER);
		lbl_title.setBounds(100, 20, 500, 50);
		lbl_title.setFont(new Font("Orange Kid", Font.BOLD, 26));
		lbl_title.setForeground(Color.BLUE);
		lbl_title.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_title.setForeground(Color.BLUE);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_title.setForeground(Color.RED);
				stat = 1;

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_title);

		lbl_up = new JLabel("Rotate Brick", JLabel.CENTER);
		lbl_up.setBounds(100, 100, 500, 50);
		lbl_up.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_up.setForeground(Color.BLACK);
		lbl_up.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_up.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_up.setForeground(Color.RED);
				stat = 1;

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_up);

		lbl_down = new JLabel("Soft Drop", JLabel.CENTER);
		lbl_down.setBounds(100, 160, 500, 50);
		lbl_down.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_down.setForeground(Color.BLACK);
		lbl_down.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_down.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_down.setForeground(Color.RED);
				stat = 2;

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_down);

		lbl_left = new JLabel("Move To Left", JLabel.CENTER);
		lbl_left.setBounds(100, 220, 500, 50);
		lbl_left.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_left.setForeground(Color.BLACK);
		lbl_left.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_left.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_left.setForeground(Color.RED);
				stat = 3;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_left);

		lbl_right = new JLabel("Move To Right", JLabel.CENTER);
		lbl_right.setBounds(100, 280, 500, 50);
		lbl_right.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_right.setForeground(Color.BLACK);
		lbl_right.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_right.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_right.setForeground(Color.RED);
				stat = 4;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_right);

		lbl_shift = new JLabel("Change Brick", JLabel.CENTER);
		lbl_shift.setBounds(100, 400, 500, 50);
		lbl_shift.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_shift.setForeground(Color.BLACK);
		lbl_shift.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_shift.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_shift.setForeground(Color.RED);
				stat = 5;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_shift);

		lbl_space2 = new JLabel("Go To Main Menu After KO!", JLabel.CENTER);
		lbl_space2.setBounds(100, 450, 500, 50);
		lbl_space2.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_space2.setForeground(Color.BLACK);
		lbl_space2.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_space2.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_space2.setForeground(Color.RED);
				stat = 7;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_space2);

		lbl_space = new JLabel("Speed Down", JLabel.CENTER);
		lbl_space.setBounds(100, 340, 500, 50);
		lbl_space.setFont(new Font("Orange Kid", Font.BOLD, 20));
		lbl_space.setForeground(Color.BLACK);
		lbl_space.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				lbl_space.setForeground(Color.BLACK);
				stat = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_space.setForeground(Color.RED);
				stat = 6;
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		add(lbl_space);

		back = new JLabel("BACK TO MAIN MENU", JLabel.CENTER);
		back.setBounds(70, 570, 500, 50);
		back.setFont(new Font("Orange Kid", Font.BOLD, 20));
		back.setForeground(Color.BLUE);
		back.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				back.setForeground(Color.BLUE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				back.setForeground(Color.YELLOW);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				HowToPlayFrame.window.remove(HowToPlayPanel.this);
				new MainMenuFrame();

			}
		});
		add(back);

	}

}
