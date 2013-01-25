package tictactoe.gui;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import tictactoe.brain.Constants;
import tictactoe.brain.Engine;
import tictactoe.brain.EngineImpl;


/**
 * The Class GuiImpl, which implements the Gui interface
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
	
	/** The engine. */
	Engine engine;


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init() {

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
		repaint();
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
			}
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
		boolean refresh = engine.clickedCell(i,j);
		if (refresh)
			repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}



}
