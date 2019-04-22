package source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Piece {
	protected BufferedImage block;
	protected int coords[][];

	protected Board board;
	protected int color;

	protected int dX = 0;
	protected int cX, cY;

	public Piece() {

	}

	public Piece(BufferedImage block, int[][] coords, Board board, int color) {
		super();
		this.block = block;
		this.coords = coords;
		this.board = board;
		this.color = color;

	}

	protected void render(Graphics g) {

	}

	public int getColor() {
		return color;
	}

	public BufferedImage getBlock() {
		return block;
	}

	public int[][] getCoords() {
		return coords;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getcX() {
		return cX;
	}

	public int getcY() {
		return cY;
	}

	public void setcY(int cY) {
		this.cY = cY;
	}

}
