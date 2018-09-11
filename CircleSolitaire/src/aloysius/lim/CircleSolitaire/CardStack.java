package aloysius.lim.CircleSolitaire;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CardStack {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 9-9-18
 */
	
	/**** Variables ****/
	
	ArrayList<Card> cards = new ArrayList<>();
	int stackX = 0;
	int stackY = 0;
	int overlap = 0;
	
	//Constructor
	public CardStack(int stackX, int stackY, int overlap) {
		this.stackX = stackX;
		this.stackY = stackY;
		this.overlap = overlap;
	}
	
	public void add(Card card) {
		int cardX = stackX;
		int cardY = stackY + overlap * cards.size();
		card.setXY(cardX, cardY);
		cards.add(card);
	}
	
	public void addToBeginning(Card card) {
		card.setXY(stackX, stackY);
		cards.add(0,card);
		
		for (int i=1; i<cards.size(); i++) {
			Card nextCard = cards.get(i);
			nextCard.addToXY(0, overlap);
		}
	}
	
	public void draw(Graphics2D g) {
		if (cards.size() > 0 && overlap == 0) {
			int lastIndex = cards.size()-1;
			Card card = cards.get(lastIndex);
			card.draw(g);
		}else {
			for (int i=0; i<cards.size(); i++) {
				Card card = cards.get(i);
				card.draw(g);
			}
		}
	}
	
	public int size() {
		return cards.size();
	}
	
	public int getX() {
		return stackX;
	}
	
	public int getY() {
		return stackY;
	}
	
	public void clear() {
		cards.clear();
	}
}
