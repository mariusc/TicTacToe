package tictactoe.brain;
public class Board {
	
	public static int[][] board = new int[Constants.boardSize][Constants.boardSize];
	
	/**
	 * Inits the empty board.
	 */
	public static void initBoard(){
		for (int i = 0; i < Constants.boardSize; i++)
			for (int j = 0; j < Constants.boardSize; j++)
				board[i][j] = Constants.free;
			
	}
	
	/**
	 * Prints the board.
	 */
	public static void printBoard()
	{
		for (int i = 0; i < Constants.boardSize; i++){
			for (int j = 0; j < Constants.boardSize; j++){
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
			
		System.out.println();
		System.out.println();
		
	}

}
