package aloysius.lim.BakersDozen;

import java.awt.Dimension;

import javax.swing.JPanel;

public class TablePanel extends JPanel{
/**
 * Author: Aloysius Lim
 * Date Created: 8-30-18
 * Last Updated: 8-30-18
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
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		return size;
	}
	
}
