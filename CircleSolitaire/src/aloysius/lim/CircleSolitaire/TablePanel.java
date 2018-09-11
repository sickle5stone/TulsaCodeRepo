package aloysius.lim.CircleSolitaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class TablePanel extends JPanel{
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 9-9-18
 */
	
	//Serialization
	private static final long serialVersionUID = 1L;
	
	/**** Constants ****/
	private static final int CARDWIDTH = Deck.getCardWidth();
	private static final int CARDHEIGHT = Deck.getCardHeight();
	
	private static final int SPACING = 15; //space between class
	private static final int MARGIN = 10; //margin around table
	
	//Actual Width & Height taking into account individual card width, spacing and margin 
	private static final int WIDTH = CARDWIDTH * 13 + SPACING * 12 + MARGIN * 2; //Width of table
	private static final int HEIGHT = CARDHEIGHT * 9 + MARGIN * 3; //Height of table
	
	private static final int FOUNDATIONX = WIDTH/2 - (4*CARDWIDTH + 3*SPACING)/5;
	private static final int FOUNDATIONY = MARGIN;
	private static final int BOARDX = MARGIN;
	private static final int BOARDY = CARDHEIGHT + (MARGIN*2);
	private static final int OVERLAP = (int) (CARDHEIGHT * 0.65);
	
	private Deck deck;
	private Card card;
	private CardStack[] foundation = new CardStack[13];
	private CardStack[] column = new CardStack[4];

	//Constructor
	public TablePanel() {
		int x = FOUNDATIONX;
		int y = FOUNDATIONY;
		
		for (int i = 0;i<foundation.length;i++) {
			//13th Pile, set up foundation at middle
			if (i == 12) {
				x = FOUNDATIONX;
				y = CARDHEIGHT * 4;
			}
			foundation[i] = new CardStack(x,y,0);
			//Logic for creating a circle foundation cards
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
		
		x = BOARDX;
		y = BOARDY;
		
		for (int i = 0;i<column.length;i++) {
			//13th Pile, set overlap card at middle pile
			if (i == 12) {
				x = BOARDX;
				y = CARDHEIGHT * 4;
			}
			column[i] = new CardStack(x,y,OVERLAP);
			//Logic for assigning a overlap cards into foundation positions
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
		
		deck = new Deck();
		deal();
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}

	private void deal() {
//		card = deck.deal();
//		card.setXY(10, 10);
		for(int row = 0; row<foundation.length; row++){
			for (int col=0; col< column.length; col++) {
				Card card = deck.deal();
				column[col].add(card);
			}
		}
		repaint();
	}
	
	public void paintComponent(Graphics g) {
//		card.draw(g);
		
		//draw background

		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		//foundation cards
		for (int i=0;i<foundation.length;i++) {
			if (foundation[i].size() > 0) {
//				foundation[i].draw(g2d);
				for (int y=0;y<column.length;y++) {
					column[y].draw(g2d);
				}
			}else {
				int x = foundation[i].getX();
				int y = foundation[i].getY();
				Card.drawOutline(g2d, x, y);
			}
		}
	}
	
}
