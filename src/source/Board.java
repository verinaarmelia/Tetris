package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener {
	private BufferedImage blocks;
	private BufferedImage bg;
	private BufferedImage frame2;
	// gameover new
	private BufferedImage gO;
	private int score = 0;
	private String scoreString = "0";
	private final int indentY = 25 * 2;
	private final int indentX = 25;
	private final int borderY = 25 * 24 + indentY;

	private final int borderX = 25 * 10 + indentX;
	public static final int BLOCKSIZE = 25;
	private final int GRIDHEIGHT = 24, GRIDWIDTH = 10;

	private int[][] board = new int[GRIDHEIGHT][GRIDWIDTH];
	private int curIDX;
	private int holdIDX = 7;
	private int nextIDX;

	private final Piece piece[] = new Piece[7];
	private CurrentPiece currentPiece; // current piece being positioned/played
	private NextPiece nextPiece;
	private HoldPiece holdPiece = null;
	private Timer timer;

	private final int FPS = 60;
	private final int delay = 1000 / 60;
	private boolean gameOver = false;
	private boolean shiftPressed = false; // apakah shift di tekan
	private boolean shiftPieceAvail = false; // cek apakah ada untuk diganti, ini baru ada setelah pertama shift
	private boolean playerShifted = false; // ck apakah selama main, shift udah pernah dipencet
	private boolean shifted = false; // cuman boleh sekali shift per piece

	private float h = (float) 0.53358333;
	private float s = (float) 0.5697;
	private float b = (float) 0.9569;

	public Board() {
		// setLayout(null);
		// JLabel gameTitle = new JLabel("TETRIS 2019");
		// add(gameTitle);
		// gameTitle.setSize(getMaximumSize());
		setBackground(Color.getHSBColor(h, s, b));
		try {
			bg = ImageIO.read(Board.class.getResource("/totoro.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// new gameOver
		try {
			gO = ImageIO.read(Board.class.getResource("/game_over.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			frame2 = ImageIO.read(Board.class.getResource("/totoroFrames.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = 0;
			}
		}
		try {
			blocks = ImageIO.read(Board.class.getResource("/tetris_blocks_21.png")); // masukkin gambar
		} catch (IOException e) {
			e.printStackTrace();
		}

		timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});

		timer.start();

		// shapes of tetris block piece , crop subImage
		piece[0] = new Piece(blocks.getSubimage(0, 0, BLOCKSIZE, BLOCKSIZE), new int[][] { { 1, 1, 1, 1 } }, this, 1); // the
																														// stick

		piece[1] = new Piece(blocks.getSubimage(1 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 1, 1, 0 }, { 0, 1, 1 } }, this, 2); // the dog (left)

		piece[2] = new Piece(blocks.getSubimage(2 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 0, 1, 1 }, { 1, 1, 0 } }, this, 3); // the dog (right)

		piece[3] = new Piece(blocks.getSubimage(3 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 1, 0, 0 }, { 1, 1, 1 } }, this, 4); // the L (left)

		piece[4] = new Piece(blocks.getSubimage(4 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 0, 0, 1 }, { 1, 1, 1 } }, this, 5); // the L (right)

		piece[5] = new Piece(blocks.getSubimage(5 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 0, 1, 0 }, { 1, 1, 1 } }, this, 6); // the T

		piece[6] = new Piece(blocks.getSubimage(6 * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
				new int[][] { { 1, 1 }, { 1, 1 } }, this, 7); // the Square

		curIDX = Helper.randomNum(0, 6);
		currentPiece = new CurrentPiece(piece[curIDX].getBlock(), piece[curIDX].getCoords().clone(), this,
				piece[curIDX].getColor()); // coba

		getNextPiece();
		// getPiece();
	}

	public void update() {
		if (gameOver) {
			timer.stop();

		}
		currentPiece.update();
	}

	public void paint(Graphics g) {
		super.paint(g);
		// g.drawImage(blocks, 0, 0, null); //ini tes doang
		// bg image
		g.drawImage(bg, 40, 320, null);
		g.setColor(Color.getHSBColor((float) 0.472, (float) 0.5, (float) 0.76));
		// g.fillRect(405, 70, 125, 188);
		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(indentX, indentY + 4 * BLOCKSIZE, GRIDWIDTH * BLOCKSIZE, (GRIDHEIGHT - 4) * BLOCKSIZE);
		// masukkin piece
		currentPiece.render(g);
		// buat gambar piece kalau udah di placed
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[0].length; col++)
				if (board[row][col] != 0)
					g.drawImage(blocks.getSubimage((board[row][col] - 1) * BLOCKSIZE, 0, BLOCKSIZE, BLOCKSIZE),
							col * BLOCKSIZE + indentX, row * BLOCKSIZE + indentY, null);

		// gambar nextPiece
		nextPiece.render(g);
		// gambar holdPiece
		if (holdPiece != null && (shiftPieceAvail || playerShifted))
			holdPiece.render(g);

		// piece frame
		g.drawImage(frame2, 390, 65, null);

		// score
		g.setFont(new Font("Agent Red", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString("LINES CLEARED: " + scoreString, 50, 40);

		// bikin grid blocks
		// horizontal
		g.setColor(Color.DARK_GRAY);
		for (int i = 0; i <= GRIDHEIGHT - 4; i++) {
			g.drawLine(indentX, i * BLOCKSIZE + (indentY + (BLOCKSIZE * 4)), GRIDWIDTH * BLOCKSIZE + BLOCKSIZE,
					i * BLOCKSIZE + (indentY + (BLOCKSIZE * 4)));
		}
		// vertical
		for (int i = 0; i <= GRIDWIDTH; i++) {
			g.drawLine(i * BLOCKSIZE + BLOCKSIZE, (indentY + (BLOCKSIZE * 4)), i * BLOCKSIZE + BLOCKSIZE,
					(GRIDHEIGHT - 4) * BLOCKSIZE + (indentY + (BLOCKSIZE * 4)));
		}

		// new gameover image
		if (gameOver) {
			// x,y,lebar,tinggi

			g.drawImage(gO, 10, 150, 280, 500, null);

		}
	}

	public void getNextPiece() {
		do {
			nextIDX = Helper.randomNum(0, 6);
		} while (nextIDX == curIDX);
		// System.out.println("rand " + idx);
		// if (!shiftPieceAvail) {
		nextPiece = new NextPiece(piece[nextIDX].getBlock(), piece[nextIDX].getCoords(), this,
				piece[nextIDX].getColor());
		System.out.println("2. " + holdIDX + " " + curIDX + "\n");
		// }
		// System.out.println("cur: " + (currentPiece.getColor() - 1) + " next: " +
		// (nextPiece.getColor() - 1));

	}

	public void getPiece() {
		// System.out.println("nextpiece: " + nextPiece.getColor());
		// int idx = Helper.randomNum(0, 6); //coba
		// int idx = 0;
		// nextPiece = new Piece(piece[idx].getBlock(), piece[idx].getCoords(), this,
		// piece[idx].getColor()); // coba
		if (shiftPressed && !shifted) {
			shifted = true;
			System.out.println(shifted);
			curIDX = currentPiece.getColor() - 1;
			if (curIDX == holdIDX)
				currentPiece.setcY(indentY + 4 * BLOCKSIZE - currentPiece.getCoords().length * BLOCKSIZE);
			else {
				int temp = holdIDX;
				holdIDX = curIDX;
				holdPiece = new HoldPiece(piece[holdIDX].getBlock(), piece[holdIDX].getCoords(), this,
						piece[holdIDX].getColor());
				System.out.println("hold" + (holdPiece.getColor() - 1));
				System.out.println("STat:" + shiftPieceAvail);
				if (shiftPieceAvail) {
					System.out.println("1. " + holdIDX + " " + curIDX);
					curIDX = temp;
					currentPiece = new CurrentPiece(piece[curIDX].getBlock(), piece[curIDX].getCoords(), this,
							piece[curIDX].getColor());
					// System.out.println("shifted to " + (currentPiece.getColor() - 1));
					holdIDX = holdPiece.getColor() - 1;
				} else if (!shiftPieceAvail) {
					curIDX = nextPiece.getColor() - 1;
					currentPiece = new CurrentPiece(piece[nextIDX].getBlock(), piece[nextIDX].getCoords(), this,
							piece[nextIDX].getColor());

					getNextPiece();
					// System.out.println("error if shiftPressed"); // TODO: fix bug
				}
				holdPiece.render(getGraphics());
			}
			shiftPieceAvail = true;
			shiftPressed = false;
		} else if (!shiftPressed) {
			shifted = false;
			currentPiece = new CurrentPiece(piece[nextIDX].getBlock(), piece[nextIDX].getCoords(), this,
					piece[nextIDX].getColor());
			getNextPiece();
			// System.out.println("error else");
		}

		for (int i = 0; i < GRIDWIDTH; i++)
			if (board[3][i] != 0) {
				gameOver = true;
				break;
			}
	}

	public int[][] getBoard() {
		return board;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			currentPiece.rotate();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentPiece.speedDown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			currentPiece.setdX(-BLOCKSIZE);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			currentPiece.setdX(BLOCKSIZE);
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			if (!shifted) {
				shiftPressed = true;
			} else {
				playerShifted = true;
				shifted = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (gameOver) {
				new MainMenuFrame();
			} else
				currentPiece.placeDown();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			currentPiece.normalSpeed();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public int getGRIDHEIGHT() {
		return GRIDHEIGHT;
	}

	public int getGRIDWIDTH() {
		return GRIDWIDTH;
	}

	public int getIndentY() {
		return indentY;
	}

	public int getIndentX() {
		return indentX;
	}

	public int getBorderY() {
		return borderY;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public int getBorderX() {
		return borderX;
	}

	public boolean isShiftPressed() {
		return shiftPressed;
	}

	public Piece getHoldPiece() {
		return holdPiece;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
		scoreString = "" + this.score;
		System.out.println(scoreString);
	}

}
