package aloysius.lim.BakersDozen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class TablePanel extends JPanel{
/**
 * Author: Aloysius Lim
 * Date Created: 8-30-18
 * Last Updated: 9-13-18
 */
	
	//Serialization
	private static final long serialVersionUID = 1L;
	
	/**** Constants ****/
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	
	private static final int SPACING = 4; //space between class
	private static final int MARGIN = 10; //margin around table
	
	//Actual Width & Height taking into account individual card width, spacing and margin 
	private static final int WIDTH = CARDWIDTH * 13 + SPACING * 12 + MARGIN * 2; //Width of table
	private static final int HEIGHT = CARDHEIGHT * 9 + MARGIN * 3; //Height of table
	
	private static final int FOUNDATIONX = WIDTH/2 - (4*CARDWIDTH + 3*SPACING)/2;
	private static final int FOUNDATIONY = MARGIN;
	private static final int BOARDX = MARGIN;
	private static final int BOARDY = CARDHEIGHT + (MARGIN*2);
	private static final int OVERLAP = (int) (CARDHEIGHT * 0.65);
	
	private Deck deck;
	private Card card;
	private CardStack[] foundation = new CardStack[4];
	private CardStack[] column = new CardStack[13];

	//moving card
	private Card movingCard;
	private int mouseX;
	private int mouseY;
	private int fromCol = 0;
	
	//Constructor
	public TablePanel() {
		int x = FOUNDATIONX;
		int y = FOUNDATIONY;
		
		for (int i = 0;i<foundation.length;i++) {
			foundation[i] = new CardStack(x,y,0);
			x += CARDWIDTH + SPACING;
		}
		
		x = BOARDX;
		y = BOARDY;
		
		for (int i = 0;i<column.length;i++) {
			column[i] = new CardStack(x,y,OVERLAP);
			x += CARDWIDTH + SPACING;
		}
		
		deck = new Deck();
		deal();
		
		//mouseListener
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				clicked(x,y);
			}
			
			public void mouseReleased(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				released(x,y);
			}
		});//end listener
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				dragged(x,y);
			}//end mouseDragged
		});//end listener
		
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}

	private void deal() {
//		card = deck.deal();
//		card.setXY(10, 10);
		for(int row = 0; row<4; row++){
			for (int col=0; col< 13; col++) {
				Card card = deck.deal();
				if (card.getValue()==12) {
					column[col].addToBeginning(card);
				}else {					
					column[col].add(card);
				}
			}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
//		card.draw(g);
		
		//draw background
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//foundation cards
		for (int i=0;i<foundation.length;i++) {
			if (foundation[i].size() > 0) {
				foundation[i].draw(g);
			}else {
				int x = foundation[i].getX();
				int y = foundation[i].getY();
				Card.drawOutline(g, x, y);
			}
		}
		
		//draw board
		for (int i=0;i<column.length;i++) {
			column[i].draw(g);
		}
		
		//draw moving card
		if (movingCard != null) {
			movingCard.draw(g);
		}
	}
	
	//mouse clicked
	private void clicked(int x,int y) {
		movingCard = null;
		for(int col = 0;col<13 && movingCard == null;col++) {
			if (column[col].size()>0) {
				Card card = column[col].getLast();
				if (card.contains(x, y)) {
					movingCard = card;
					mouseX = x;
					mouseY = y;
					column[col].removeLast();
					fromCol = col;
				}
			}
		}
	}
	
	//mouse dragged
	private void dragged(int x, int y) {
		if(movingCard != null) {
			int changeX = x - mouseX;
			int changeY = y - mouseY;
			movingCard.addToXY(changeX, changeY);
			mouseX = x;
			mouseY = y;
			repaint();
		}
	}
	
	//mouse released
	private void released(int x, int y) {
		if (movingCard != null) {
			boolean validMove = false;
			
			//play on a foundation
			for (int i = 0; i < 4 && !validMove; i++) {
				int foundationX = foundation[i].getX();
				int foundationY = foundation[i].getY();
				if (movingCard.isNear(foundationX,foundationY)) {
					if (foundation[i].size() == 0) {
						if (movingCard.getValue() == 0) {
							validMove = true;
							foundation[i].add(movingCard);
							movingCard = null;
						}//if movingCard value == 0
					}//if foundation
					else {
						Card topCard = foundation[i].getLast();
						if (movingCard.getSuit() == topCard.getSuit() &&
								movingCard.getValue() == topCard.getValue()+1) {
							validMove = true;
							foundation[i].add(movingCard);
							movingCard = null;
						}
					}
				};//if isNear
			}//end for
			
			//check other stacks
			for (int i = 0; i<13 && !validMove ; i++) {
				if (column[i].size()>0) {
					Card card = column[i].getLast();
					if (movingCard.isNear(card)
							&& movingCard.getValue() == card.getValue()-1) {
						validMove = true;
						column[i].add(movingCard);
						movingCard = null;
					}
				}//if column
			}//for other stacks
			
			//check the abyss
			if(!validMove) {
				column[fromCol].add(movingCard);
				movingCard = null;
			}
			
			repaint();
		}
		
	}
	
	private void isGameOver() {
		boolean gameOver = true;
		for (int i=0; i<4 && gameOver;i++) {
			if (foundation[i].size()!=13) {
				gameOver=false;
			}
		}
		if(gameOver) {
			String message = "You Win!";
			System.out.println(message);
		}
	}
	
}
