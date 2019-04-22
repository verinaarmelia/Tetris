package source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class NextPiece extends Piece {

	public NextPiece(BufferedImage block, int[][] coords, Board board, int color) {
		super(block, coords, board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g) {
		for (int y = 0; y < coords.length; y++) {
			for (int x = 0; x < coords[y].length; x++) {
				if (coords[y][x] != 0 && coords[0].length != 4 && coords[0].length != 2)
					g.drawImage(block, x * Board.BLOCKSIZE + 425, y * Board.BLOCKSIZE + 2 * board.getIndentY() - 5,
							null);
				else if (coords[y][x] != 0 && coords[0].length == 4)
					g.drawImage(block, x * Board.BLOCKSIZE + 415, y * Board.BLOCKSIZE + 2 * board.getIndentY(), null);
				else if (coords[y][x] != 0)
					g.drawImage(block, x * Board.BLOCKSIZE + 440, y * Board.BLOCKSIZE + 2 * board.getIndentY() - 5,
							null);
			}
		}
	}

}
