# TicTacToe
Tic Tac Toe game. The board is 4x4, a player wins with 3 in a row. The size of the board and of the winning line can be changed.
Turn based game between two players on the same machine.

I used an interface for the Engine, so that any game engine implementing that interface could be associated to this game.
The game is scalable, with different graphics, just a minor change in the Constant class is needed to play the game on a different board.
Currently, if I change in the Constants class the value of winningSize from 3 to 4, the player would have to complete a line of 4 marks to win.
Also, if I were to add an AI to this game (instead of one player), this could be done without much struggle. The engine is independent, 
and it only knows that the game is played by two players. If, for example, in the GUI I would instantiate an AI which would choose the 
next move for a player, the engine would still work just as before.

Possible further developments:
1. AI
2. network module
