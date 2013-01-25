package tictactoe.brain;

/**
 * The Class Board. It contais  a static matrix of integers, representing the
 * state of the board.
 */
public class Board {

	/** The board. */
	public static int[][] board = new int[Constants.boardSize][Constants.boardSize];

	/**
	 * Inits the empty board.
	 */
	public static void initBoard() {
		for (int i = 0; i < Constants.boardSize; i++)
			for (int j = 0; j < Constants.boardSize; j++)
				board[i][j] = Constants.free;

	}

	/**
	 * Prints the board. Used for debugging purposes.
	 */
	public static void printBoard() {
		for (int i = 0; i < Constants.boardSize; i++) {
			for (int j = 0; j < Constants.boardSize; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();

	}

}
