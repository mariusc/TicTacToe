package tictactoe.brain;

import tictactoe.gui.Gui;
import tictactoe.gui.Gui;

// TODO: Auto-generated Javadoc
/**
 * The Class EngineImpl, which implements the Engine Interface.
 */
public class EngineImpl implements Engine {

	/** The turn. */
	int turn = Constants.x; // x starts the game
	
	/** The gui. */
	Gui gui;
	
	/** The clicks. */
	int clicks = 1;

	/**
	 * Instantiates a new engine impl.
	 */
	public EngineImpl() {

		Board.initBoard();
		gui = new Gui();
	}
	
	/**
	 * Count occurences of x or 0 (depending on "player") on the same row, line or 
	 * diagonal. The choice between couting to the left, right, up, down, up left diag, 
	 * up right diag, down left diag, down right diag is given by z and t combinations 
	 * z = -1, we go left
	 * z = 1, we ro right
	 * z = 0, we stay on the same column
	 * t = -1, we go up
	 * t = 1, we go down
	 * t = 0, we stay on the same line 
	 * @param x the x
	 * @param y the y
	 * @param player the player
	 * @param z the z
	 * @param t the t
	 * @return the number of occurences of the element which was just placed
	 */
	private int countOccurences(int x, int y, int player, int z, int t) {
		int k = 1;
		//check to be on the board
		while (k * z + y >= 0 && k * z + y < Constants.boardSize
				&& k * t + x >= 0 && k * t + x < Constants.boardSize) {
			if (Board.board[k*t+x][k*z +y] != player){
				break;
			}
			k++;
		}
	return k-1;

	}
	

	/**
	 * Check if the current player has won the game, using the countOccurences function.
	 * We count how many times the current player placed his sign on each semi-line,
	 * column or diagonal and we add one for the current cell.
	 *
	 * @param x the line
	 * @param y the column
	 * @param player the current player
	 * @return true, if won
	 */
	private boolean checkWin(int x, int y, int player){
		int c1, c2;
		//check line
		c1 = countOccurences(x, y, player, -1, 0);
		c2 = countOccurences(x, y, player, 1, 0);
		if (c1 + c2 + 1 >= Constants.winningSize){
			Board.board[x][y] += 10;
			for (int k = 1; k <= c1; k++)
				Board.board[x][y-k] += 10;
			for (int k = 1; k <= c2; k++)
				Board.board[x][y+k] += 10;
			
			return true;
		}
		// check column
		c1 = countOccurences(x, y, player, 0, -1);
		c2 = countOccurences(x, y, player, 0, 1);
		if (c1 + c2 + 1 >= Constants.winningSize){
			Board.board[x][y] += 10;
			for (int k = 1; k <= c1; k++)
				Board.board[x-k][y] += 10;
			for (int k = 1; k <= c2; k++)
				Board.board[x+k][y] += 10;
			
			return true;
		}
		// check 1st digonal or parallels
		c1 = countOccurences(x, y, player, -1, -1);
		c2 = countOccurences(x, y, player, 1, 1);
		if (c1 + c2+ 1 >= Constants.winningSize){
			Board.board[x][y] += 10;
			for (int k = 1; k <= c1; k++)
				Board.board[x-k][y-k] += 10;
			for (int k = 1; k <= c2; k++)
				Board.board[x+k][y+k] += 10;
			
			return true;
		}
		// check 2nd digonal or parallels
		c1 = countOccurences(x, y, player, -1, 1);
		c2 = countOccurences(x, y, player, 1, -1);
		if (c1 + c2 + 1 >= Constants.winningSize){
			Board.board[x][y] += 10;
			for (int k = 1; k <= c1; k++)
				Board.board[x+k][y-k] += 10;
			for (int k = 1; k <= c2; k++)
				Board.board[x-k][y+k] += 10;
			
			return true;
		}
		return false;
	}

	/**
	 * Checks if cell at i, j is empty.
	 *
	 * @param i the line
	 * @param j the column
	 * @return true, if empty
	 */
	private boolean cellIsEmpty(int i, int j) {
		if (Board.board[i][j] == Constants.free)
			return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see tictactoe.brain.Engine#clickedCell(int, int)
	 */
	public int clickedCell(int i, int j) {
		int toReturn = 0;
		if (cellIsEmpty(i, j)) {
			toReturn = 1;
			clicks ++;
			if (clicks >= Constants.boardSize*Constants.boardSize)
				toReturn = 3;
			// mark on gui the new move
			Board.board[i][j] = turn;
			if (checkWin(i, j, turn)){
				System.out.println(turn + " won");
				toReturn = 2;
			}
	
			Board.printBoard();
			// change turn
			if (turn == Constants.x) {
				turn = Constants.o;
			} else {
				turn = Constants.x;
			}
			
		}
		return toReturn;

	}

	/* (non-Javadoc)
	 * @see tictactoe.brain.Engine#getBoard()
	 */
	@Override
	public int[][] getBoard() {
		return Board.board;
	}

	@Override
	public int getTurn() {
		return turn;
	}

}
