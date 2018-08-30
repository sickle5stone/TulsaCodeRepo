package Aloysius.Lim.BakersDozen;

public class Deck {
/**
 * Author: Aloysius Lim
 * Date Created: 8-28-18
 * Last Updated: 8-28-18
 */
	
	/**** SETTING CONSTANT ****/
	private static final String[] RANKS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
	private static final String[] SUITSYMBOLS = {"\u2665","\u2666","\u2660","\u2663"};
	private static final int CARDWIDTH = 30;
	private static final int CARDHEIGHT = 50;
	
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
}//end class

