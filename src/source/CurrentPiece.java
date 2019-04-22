package source;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CurrentPiece extends Piece {

	private int normalSpeed = 1000, speedDown = 100, currentSpeed;
	private long time, lastTime;

	private boolean collisionY = false, collisionX = false, spacePressed = false;

	public CurrentPiece(BufferedImage block, int[][] coords, Board board, int color) {
		super(block, coords, board, color);
		currentSpeed = normalSpeed;
		time = 0;
		lastTime = System.currentTimeMillis();

		cX = board.getIndentX() + (board.getGRIDWIDTH() / 2 - (coords[0].length / 2)) * Board.BLOCKSIZE;
		cY = board.getIndentY() + 4 * Board.BLOCKSIZE - coords.length * Board.BLOCKSIZE;
	}

	@Override
	public void render(Graphics g) {
		for (int y = 0; y < coords.length; y++) {
			for (int x = 0; x < coords[y].length; x++) {
				if (coords[y][x] != 0)
					g.drawImage(block, x * Board.BLOCKSIZE + cX, y * Board.BLOCKSIZE + cY, null);

			}
		}
	}

	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		// System.out.println(board.getBoard()[((cY - 70) / Board.BLOCKSIZE()) +
		// 1][((cX - 20) / Board.BLOCKSIZE())]);
		if (board.isShiftPressed()) {
			board.getPiece();
		}
		if (collisionY) {
			if (time > currentSpeed) {
				time = 0;
			}
			for (int row = 0; row < coords.length; row++)
				for (int col = 0; col < coords[row].length; col++)
					if (coords[row][col] != 0)
						board.getBoard()[((cY - board.getIndentY()) / Board.BLOCKSIZE)
								+ row][((cX - board.getIndentX()) / Board.BLOCKSIZE) + col] = color;
			checkLine();
			board.getPiece();
		}

		if (cX + dX >= board.getIndentX() && cX + dX + coords[0].length * Board.BLOCKSIZE <= board.getBorderX()) {
			for (int row = 0; row < coords.length; row++)
				for (int col = 0; col < coords[row].length; col++)
					if (coords[row][col] != 0) {
						if (board.getBoard()[((cY - board.getIndentY()) / Board.BLOCKSIZE)
								+ row][((cX - board.getIndentX()) / Board.BLOCKSIZE) + col
										+ (dX / Board.BLOCKSIZE)] != 0)
							collisionX = true;
					}
			if (!collisionX)
				cX += dX;
		}
		if (cY + Board.BLOCKSIZE + (coords.length * Board.BLOCKSIZE) <= board.getBorderY()) {
			for (int row = 0; row < coords.length; row++)
				for (int col = 0; col < coords[row].length; col++)
					if (coords[row][col] != 0) {
						if (board.getBoard()[((cY - board.getIndentY()) / Board.BLOCKSIZE) + row
								+ 1][((cX - board.getIndentX()) / Board.BLOCKSIZE) + col] != 0) {
							collisionY = true;
							if (spacePressed)
								cY -= 1;
						}
					}
			if (spacePressed)
				currentSpeed = 1;
			if (time > currentSpeed) {
				cY += Board.BLOCKSIZE;
				time = 0;
			}
		} else {
			collisionY = true;
		}
		dX = 0;
		collisionX = false;
	}

	// System.out.println(cX + " " + cY);

	public void rotate() {
		int[][] rotatedMatrix = null;

		rotatedMatrix = getTranspose(coords);
		rotatedMatrix = getReverseMatrix(rotatedMatrix);

		if (cX < board.getIndentX() || cX + (rotatedMatrix[0].length * Board.BLOCKSIZE) > board.getBorderX()
				|| cY + (rotatedMatrix.length * Board.BLOCKSIZE) < board.getIndentY()
				|| cY + (rotatedMatrix.length * Board.BLOCKSIZE) > board.getBorderY()) {
			while (cX + (rotatedMatrix[0].length * Board.BLOCKSIZE) > board.getBorderX())
				cX -= Board.BLOCKSIZE;

		}

		for (int row = 0; row < rotatedMatrix.length; row++)
			for (int col = 0; col < rotatedMatrix[row].length; col++)
				if (rotatedMatrix[row][col] != 0) {
					if (board.getBoard()[((cY - board.getIndentY()) / Board.BLOCKSIZE) + row
							+ 1][((cX - board.getIndentX()) / Board.BLOCKSIZE) + col] != 0) {
						return;
					}
				}
		// System.out.println(rotatedMatrix.length + " " + rotatedMatrix[0].length);
		// System.out.println(cX + " " + cY);

		coords = rotatedMatrix;
		// System.out.println(coords[0].length * Board.BLOCKSIZE() + cX + " " + cY +
		// coords.length *
		// Board.BLOCKSIZE());

	}

	private int[][] getTranspose(int[][] matrix) {
		int[][] newMatrix = new int[matrix[0].length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				newMatrix[j][i] = matrix[i][j];
			}
		}
		return newMatrix;

	}

	private int[][] getReverseMatrix(int[][] matrix) {
		int middle = matrix.length / 2;

		for (int i = 0; i < middle; i++) {
			int[] m = matrix[i];
			matrix[i] = matrix[matrix.length - i - 1];
			matrix[matrix.length - i - 1] = m;
		}
		return matrix;

	}

	private void checkLine() {
		int temp = 0;
		int height = board.getBoard().length - 1;
		for (int i = height; i > 0; i--) {
			int count = 0;
			for (int j = 0; j < board.getBoard()[0].length; j++) {
				if (board.getBoard()[i][j] != 0) {
					count++;
				}

				board.getBoard()[height][j] = board.getBoard()[i][j];
			}
			if (count < board.getBoard()[0].length) {
				height--;
				temp++;
			}
		}
		board.setScore((23 - temp));
		System.out.println("Score : " + board.getScore() + " " + temp);
	}

	public void normalSpeed() {
		currentSpeed = normalSpeed;
	}

	public void speedDown() {
		currentSpeed = speedDown;
	}

	public void placeDown() {
		spacePressed = true;
	}

	public boolean isCollisionY() {
		return collisionY;
	}

	public boolean isSpacePressed() {
		return spacePressed;
	}

}
