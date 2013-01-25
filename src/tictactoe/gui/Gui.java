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
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import tictactoe.brain.Constants;
import tictactoe.brain.Engine;
import tictactoe.brain.EngineImpl;


/**
 * The Class GuiImpl, which implements the Mouse Listener
 */
public class Gui extends Applet implements MouseListener {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	BufferedImage bi;
	Graphics2D big;
	Graphics2D gg;
	Image board;
	Image x;
	Image o;
	int winner = Constants.gameInProgress;
	int width;
	int height;
	
	/** The engine. */
	Engine engine;


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init() {

		width = getSize().width;
		height = getSize().height;
		engine = new EngineImpl();
		bi = (BufferedImage) createImage(getSize().width, getSize().height);
		big = (Graphics2D) bi.getGraphics();

		try {
			board = ImageIO.read(new URL(getCodeBase(),
					"../graphics/game_board_16.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			x = ImageIO.read(new URL(getCodeBase(),
					"../graphics/cross.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			o = ImageIO.read(new URL(getCodeBase(),
					"../graphics/nought.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		addMouseListener(this);
		//repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {

		gg = (Graphics2D) g;
		big.drawImage(board, 0, 0, null);
		

		
		for (int i = 0; i < Constants.boardSize; i++){
			for (int j = 0; j < Constants.boardSize; j++){
				if (engine.getBoard()[i][j] == Constants.x){
					big.drawImage(x, j*Constants.cellWidth, i*Constants.cellWidth, null);
				}
				else if (engine.getBoard()[i][j] == Constants.o){
					big.drawImage(o, j*Constants.cellWidth, i*Constants.cellWidth, null);
				}
				else if (engine.getBoard()[i][j] == Constants.winningX){
					big.drawImage(x, j*Constants.cellWidth, i*Constants.cellWidth, null);
					big.setColor(new Color(255, 200,80, 90));
					Rectangle rect;
					rect = new Rectangle(j*Constants.cellWidth, i*Constants.cellWidth, Constants.cellWidth, Constants.cellWidth);
					big.fill(rect);
					big.draw(rect);
				}
				else if (engine.getBoard()[i][j] == Constants.winningO){
					big.drawImage(o, j*Constants.cellWidth, i*Constants.cellWidth, null);
					big.setColor(new Color(255, 200,80, 90));
					Rectangle rect;
					rect = new Rectangle(j*Constants.cellWidth, i*Constants.cellWidth, Constants.cellWidth, Constants.cellWidth);
					big.fill(rect);
					big.draw(rect);
				}
			}
		}
		System.out.println("winner = " + winner);
		if (winner == Constants.noWinner)
			//nobody won
		{
			big.setFont(new Font ("Arial", Font.BOLD, 15));
			big.setColor(new Color(175, 238, 238));
			big.drawString("Tie", 288, 410);
		}
		else if (winner == Constants.o)
			// 0 won
		{
			big.setFont(new Font ("Arial", Font.BOLD, 15));
			big.setColor(new Color(175, 238, 238));
			big.drawString("O won. Click to play again", 288, 410);
			
		}
		else if (winner == Constants.x)
			// X won
		{
			big.setFont(new Font ("Arial", Font.BOLD, 40));
			big.setColor(new Color(220, 220, 180));
			big.drawString("X won. Click to play again", width/2-240, height/2);
		}



		gg.drawImage(bi, 0, 0, this);
	}
	
	public void update(Graphics g)
	{
		paint(g);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		/**
		 * i and j are matrix coordinates of the clicked cell
		 */
		int i = me.getY() / 200;
		int j = me.getX() / 200;

		// tell the brain that a player clicked a cell
		if (winner == Constants.gameInProgress){
			int status = engine.clickedCell(i,j);
			if (status == 2)
				winner = (engine.getTurn()+1) % 2;
			else if (status == 3)
				winner = -1;
			
			repaint();
		}
		else
		{
			engine = new EngineImpl();
			winner = Constants.gameInProgress;
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
