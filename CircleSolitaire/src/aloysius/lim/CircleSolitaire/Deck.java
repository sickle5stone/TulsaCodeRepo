package aloysius.lim.CircleSolitaire;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Deck {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 9-9-18
 */
	
	/**** SETTING CONSTANT ****/
	private static final String[] RANKS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static final String[] SUITSYMBOLS = {"\u2665","\u2666","\u2660","\u2663"};
	private static final int CARDWIDTH = 50;
	private static final int CARDHEIGHT = 70;
	
	private static final int[] VALUES = {0,1,2,3,4,5,6,7,8,9,10,11,12};
	private static final String FILENAME = "cards.png";
	private static final ArrayList<Card> CARDS = new ArrayList<>();
	
	//Constructor
	public Deck() {
		
		Random rand = new Random();
		
		try {
			InputStream input = getClass().getResourceAsStream(FILENAME);
			BufferedImage cardsImg = ImageIO.read(input);
			
			for (int suit = 0; suit < SUITSYMBOLS.length; suit++) {
				for (int rank = 0; rank < RANKS.length; rank++) {
					int pos = 0;
					if (CARDS.size()>0) {
						rand.nextInt(CARDS.size()+1);
					}
					int x = rank*CARDWIDTH;
					int y = suit*CARDHEIGHT;
					Image img = cardsImg.getSubimage(x, y, CARDWIDTH, CARDHEIGHT);
					Card card = new Card(RANKS[rank],suit,VALUES[rank],img);
					CARDS.add(pos,card); //pos being index of new card
				}
			}
		} catch (IOException e) {
			String message = "Could not open image file " + FILENAME;
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	/**** GET METHODS ****/
	
	public static String[] getRanks() {
		return RANKS;
	}
	
	public static String[] getSuitSymbols() {
		return SUITSYMBOLS;
	}
	
	public static int getCardWidth() {
		return CARDWIDTH;
	}
	
	public static int getCardHeight() {
		return CARDHEIGHT;
	}
	
	public Card deal() {
		Card card = CARDS.remove(0);
		return card;
	}
	
	public int size() {
		return CARDS.size();
	}
	
}//end class

