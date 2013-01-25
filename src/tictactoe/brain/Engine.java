package tictactoe.brain;

// TODO: Auto-generated Javadoc
/**
 * The Engine Interface.
 */
public interface Engine {

	/**
	 * Checks to see if the clicked cell is empty. If so, the static board matrix is 
	 * updated and we check to see if a player won the game. Then we change the turn.
	 *
	 * @param i the i
	 * @param j the j
	 * @return 	0 if cell is occupied, 
	 * 			1 if cell is empty  
	 * 			2 if smbdy won, 
	 * 			3 if nobody won
	 */
	int clickedCell(int i, int j);
	
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	int [][] getBoard();
	
	
	/**
	 * Gets whose player turn it is.
	 *
	 * @return the turn
	 */
	int getTurn();
	
	

}
