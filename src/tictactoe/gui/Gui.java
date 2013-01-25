package tictactoe.gui;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

import tictactoe.brain.Constants;
import tictactoe.brain.Engine;
import tictactoe.brain.EngineImpl;

/**
 * The Class GuiImpl, which implements the Mouse Listener.
 */
public class Gui extends Applet implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	BufferedImage bi;
	Graphics2D big;
	Graphics2D gg;

	/** The board. */
	Image board;

	/** The x image. */
	Image x;

	/** The o image. */
	Image o;

	/** The game status. */
	int gameStatus = Constants.WelcomeMessage;

	/** The width of the window. */
	int width;

	/** The height of the window. */
	int height;

	/** The error (exception raised). */
	boolean error = false;

	/** The engine. */
	Engine engine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init() {
		// get size
		width = getSize().width;
		height = getSize().height;

		// instantiate engine
		engine = new EngineImpl();

		bi = (BufferedImage) createImage(getSize().width, getSize().height);
		big = (Graphics2D) bi.getGraphics();

		// load images
		try {
			board = ImageIO.read(new URL(getCodeBase(),
					"../graphics/game_board_16.png"));
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}

		try {
			x = ImageIO.read(new URL(getCodeBase(), "../graphics/cross.png"));
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}

		try {
			o = ImageIO.read(new URL(getCodeBase(), "../graphics/nought.png"));
		} catch (Exception e) {
			error = true;
			e.printStackTrace();
		}

		// add mouse listener
		addMouseListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		gg = (Graphics2D) g;

		// if exception has been raised, display message
		if (error) {
			big.setFont(new Font("Arial", Font.BOLD, 20));
			big.setColor(new Color(222, 20, 20));
			big.drawString("Error. Graphic resource not found.",
					width / 2 - 140, height / 2);
		}

		else {
			// draw board image
			big.drawImage(board, 0, 0, null);
			// draw board with x and 0
			for (int i = 0; i < Constants.boardSize; i++) {
				for (int j = 0; j < Constants.boardSize; j++) {
					if (engine.getBoard()[i][j] == Constants.x) {
						// draw x
						big.drawImage(x, j * Constants.cellWidth, i
								* Constants.cellWidth, null);
					} else if (engine.getBoard()[i][j] == Constants.o) {
						// draw 0
						big.drawImage(o, j * Constants.cellWidth, i
								* Constants.cellWidth, null);
					} else if (engine.getBoard()[i][j] == Constants.winningX) {
						// draw x and mark winning line with yellow
						big.drawImage(x, j * Constants.cellWidth, i
								* Constants.cellWidth, null);
						big.setColor(new Color(255, 200, 80, 90));
						Rectangle rect;
						rect = new Rectangle(j * Constants.cellWidth, i
								* Constants.cellWidth, Constants.cellWidth,
								Constants.cellWidth);
						big.fill(rect);
						big.draw(rect);
					} else if (engine.getBoard()[i][j] == Constants.winningO) {
						// draw x and mark winning line with yellow
						big.drawImage(o, j * Constants.cellWidth, i
								* Constants.cellWidth, null);
						big.setColor(new Color(255, 200, 80, 90));
						Rectangle rect;
						rect = new Rectangle(j * Constants.cellWidth, i
								* Constants.cellWidth, Constants.cellWidth,
								Constants.cellWidth);
						big.fill(rect);
						big.draw(rect);
					}
				}
			}

			if (gameStatus == Constants.WelcomeMessage) {
				// if the game has just started, display welcome and
				// instructions
				big.setFont(new Font("Arial", Font.BOLD, 30));
				big.setColor(new Color(220, 220, 180));
				big.drawString("Welcome to TicTacToe!", width / 2 - 170,
						height / 2 - 150);
				big.drawString("The board is 4 x 4. ", width / 2 - 130,
						height / 2 - 75);
				big.drawString(
						"Win with 3 in a row (line, column or diagonal).",
						width / 2 - 320, height / 2);
				big.drawString("X starts.", width / 2 - 60, height / 2 + 75);
				big.drawString("Click to play", width / 2 - 80,
						height / 2 + 150);

			}

			if (gameStatus == Constants.noWinner)
			// nobody won
			{
				big.setFont(new Font("Arial", Font.BOLD, 40));
				big.setColor(new Color(220, 220, 180));
				big.drawString("Tie", width / 2 - 20, height / 2);
			} else if (gameStatus == Constants.o)
			// 0 won
			{
				big.setFont(new Font("Arial", Font.BOLD, 40));
				big.setColor(new Color(220, 220, 180));
				big.drawString("O won. Click to play again", width / 2 - 240,
						height / 2);

			} else if (gameStatus == Constants.x)
			// X won
			{
				big.setFont(new Font("Arial", Font.BOLD, 40));
				big.setColor(new Color(220, 220, 180));
				big.drawString("X won. Click to play again", width / 2 - 240,
						height / 2);
			}
		}
		gg.drawImage(bi, 0, 0, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#update(java.awt.Graphics)
	 */
	public void update(Graphics g) {
		paint(g);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		// i and j are the matrix coordinates of the clicked cell
		int i = me.getY() / 200;
		int j = me.getX() / 200;

		// if the game was just started, click to clear the screen and then
		// start the game
		if (gameStatus == Constants.WelcomeMessage) {
			gameStatus = Constants.gameInProgress;
			repaint();
		} else if (gameStatus == Constants.gameInProgress) {
			// tell the engine that a player clicked a cell
			int status = engine.clickedCell(i, j);
			if (status == 2)
				// somebody won the game. get the turn (previous to the current one)
				gameStatus = (engine.getTurn() + 1) % 2;
			else if (status == 3)
				gameStatus = Constants.noWinner;

			repaint();
		} else {
			engine = new EngineImpl();
			gameStatus = Constants.gameInProgress;
			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
