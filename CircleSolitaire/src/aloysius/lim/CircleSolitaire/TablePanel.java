package aloysius.lim.CircleSolitaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TablePanel extends JPanel{
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 10-02-18
 */
	
	//Serialization
	private static final long serialVersionUID = 1L;
	
	/**** Constants ****/
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	
	private static final int SPACING = 25; //space between class
	private static final int MARGIN = 15; //margin around table
	
	//Actual Width & Height taking into account individual card width, spacing and margin 
	private static final int WIDTH = CARDWIDTH * 13 + MARGIN * 3; //Width of table
	private static final int HEIGHT = CARDHEIGHT * 9 + MARGIN * 3; //Height of table
	
	private static final int PILEX = WIDTH/2 - (4*CARDWIDTH + 3*SPACING)/7;
	private static final int PILEY = MARGIN * 6;
	private static final int OVERLAP = (int) (CARDHEIGHT * 0.05);
	
	//Declare the number of cards inside a pile
	private static final int CARDPERPILESTACK = 4;
	
	private Deck deck;
	private CardStack[] pile = new CardStack[13];
	//Nested array to remember location of the pile cards

	//moving card
	private Card movingCard;
	private int mouseX;
	private int mouseY;
	private int fromCol = 0;
	
	//The playable pile, start the game from the middle stack
	private int focusPile = 12;
	
	//Constructor
	public TablePanel() {
		int x = PILEX;
		int y = PILEY;
		
		for (int i = 0;i<pile.length;i++) {
			//13th Pile, set up pile at middle
			if (i == 12) {
				x = PILEX;
				y = CARDHEIGHT * 4;
			}
			pile[i] = new CardStack(x,y,0);
			//Logic for creating a circle of pile cards
			if (i > 2) {
				if (i > 5) {
					y -= CARDHEIGHT + SPACING;
					if (i > 8) {
						x += (CARDWIDTH + SPACING * 2)*2;
					}
				}else {
					y += CARDHEIGHT + SPACING;
				}
				x -= CARDWIDTH + SPACING * 2;
			}else {
				y += CARDHEIGHT + SPACING;
				x += CARDWIDTH + SPACING * 2;				
			}
		}
		
		//assign the pile into the different pile X,Y positions
		for (int r = 0;r<pile.length;r++) {			
			pile[r] = new CardStack(pile[r].getX(),pile[r].getY(),OVERLAP);
		}
		
		newGame();
		
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

	public void newGame() {
		focusPile = 12;
		movingCard = null;
		deck = new Deck();
		deal();
		repaint();
	}//end newGame()
	
	//Assign cards into each pile slots
	private void deal() {
		
		for(int row = 0; row<pile.length; row++){
			for (int col=0; col< 4; col++) {
				pile[row].clear();
			}
		}
		
		for(int row = 0; row<pile.length; row++){
			for (int col=0; col< 4; col++) {
				Card card = deck.deal();
				pile[row].add(card);
			}
		}
		
		//restart game and flip first card on center pile
		pile[pile.length-1].getLast().setCardFlip(true);
		repaint();
	}
	
	//Draw the background and cards into graphic
	public void paintComponent(Graphics g) {

		//draw background
		Graphics2D g2d = (Graphics2D) g;
		
		//Set optimization of rendering images, text, anti-aliasing and quality of render
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    
	    //color background
	    g2d.setColor(new Color(22,103,75));
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		
		//pile cards
		for (int i=0;i<pile.length;i++) {
			if (pile[i].size() > 0) {
				pile[i].draw(g2d);
			}else {
				int x = pile[i].getX();
				int y = pile[i].getY();
				Card.drawOutline(g2d, x, y);
			}
		}
		
		//draw board
		for (int r=0;r<pile.length;r++) {			
				pile[r].draw(g2d);
				if (r == focusPile && movingCard == null) {
					pile[r].flipLastCard(g2d);
				}
		}
		
		//draw moving card
		if (movingCard != null) {
			movingCard.drawFront(g2d);
		}
	}
	
	//mouse clicked
	private void clicked(int x,int y) {
		movingCard = null;
		for(int col = 0;col<13 && movingCard == null;col++) {
			if (pile[col].size()>0 && focusPile == col) {
				Card card = pile[col].getLast();
				if (card.contains(x, y)) {
					movingCard = card;
					mouseX = x;
					mouseY = y;
					pile[col].removeLast();
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
			for (int j = 0; j < 13 && !validMove; j++) {					
					int foundationX = pile[j].getX();
					int foundationY = pile[j].getY();
					if (movingCard.isNear(foundationX,foundationY)) {
						if (pile[j].size() == 0) {
							if (movingCard.getValue() == 0) {
								validMove = true;
								pile[j].add(movingCard);
								movingCard = null;
							}//if movingCard value == 0
						}//if foundation
						else {
							if (movingCard.getValue() == j) {
								validMove = true;
								//add the completed card to the bottom of the pile 
								pile[j].addToBottom(movingCard);
								movingCard = null;
								//check for completed stacks
								int findNext = 0;
								if (pile[j].completedCards() == CARDPERPILESTACK) {
									isGameOver();
									//offset which to jump to
									//check whether the next following stacks are completed
									//only focus on the next available non-completed stack
									if (pile[j].cardsLeft()) {
										focusPile = j;
									}else {
//										if (j == 12) { j = 0; }; // force game to continue. USED TO TEST WIN CONDITION
										while(j+findNext < pile.length && pile[j+findNext].completedCards() == CARDPERPILESTACK) {
											findNext += 1;
										}
										focusPile = j+findNext;
										
									}
								}else {
									focusPile = j;
								}
							}
						}
					};//if isNear
//				}//end for
			}
			
			//check the abyss
			if(!validMove) {
				pile[fromCol].add(movingCard);
				movingCard = null;
			}
			
			repaint();
		}
	}//end released
	
	private void isGameOver() {
		boolean gameWon = true;
		boolean gameLost = false;
		for (int i=0;i<pile.length;i++) {
			if (pile[i].completedCards() != CARDPERPILESTACK) {
				gameWon = false;
			}
			if (pile[pile.length-1].completedCards() == CARDPERPILESTACK && !gameWon) {
				gameLost = true;
			}
		}
		if (gameLost) {
			String message = "You Lose! Do you want to play again?";
			
			//dialog for play again
			int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
			
			//check user response
			if(option==JOptionPane.YES_OPTION) {
				focusPile = 12;
				newGame();
			}else {
				System.exit(0);
			}
		}
		if (gameWon) {
			String message = "You Win! Do you want to play again?";
			
			//dialog for play again
			int option = JOptionPane.showConfirmDialog(this, message, "Play again?", JOptionPane.YES_NO_OPTION);
			
			//check user response
			if(option==JOptionPane.YES_OPTION) {
				focusPile = 12;
				newGame();
			}else {
				System.exit(0);
			}
		}
	}
	
}
