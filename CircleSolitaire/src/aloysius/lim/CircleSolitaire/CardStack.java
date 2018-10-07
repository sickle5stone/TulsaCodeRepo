package aloysius.lim.CircleSolitaire;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class CardStack {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 10-02-18
 */
	
	/**** Variables ****/
	
	ArrayList<Card> cards = new ArrayList<>();
	int stackX = 0;
	int stackY = 0;
	int overlap = 0;
	private int completedCards = 0;
	
	//Constructor
	public CardStack(int stackX, int stackY, int overlap) {
		this.stackX = stackX;
		this.stackY = stackY;
		this.overlap = overlap;
	}
	
	//check whether there are cards left inside the stack (there should only be 4 completed cards in a foundation stack)
	public boolean cardsLeft() {
		if (cards.size() - completedCards > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public void add(Card card) {
		int cardX = stackX;
		int cardY = stackY + overlap * cards.size();
		card.setXY(cardX, cardY);
		cards.add(card);
	}
	
	//Logic to add cards to the bottom
	//Additionally, set the card sent to the bottom to flipped so it is rendered faceup
	public void addToBottom(Card card) {
		int cardX = stackX;
		int cardY = stackY - overlap * cards.size();
		card.setXY(cardX, cardY);
		for (Card c : cards) {
			c.addToXY(0, overlap);
		}
		card.setCardFlip(true);
		cards.add(0,card);
		//keep track of number of completed cards (Target: 4 by default base game)
		completedCards++;
	}
		
	public void draw(Graphics2D g) {
		if (cards.size() > 0 && overlap == 0) {
			int lastIndex = cards.size()-1;
			Card card = cards.get(lastIndex);
			card.drawBack(g);
		}else {
			for (int i=0; i<cards.size(); i++) {
					Card card = cards.get(i);
					//Render the card based on the variable returned that shows whether card is flipped or not flipped
					if (card.getCardFlip()) {
						card.drawFront(g);
					}else {						
						card.drawBack(g);
					}
				
			}
		}
	}
	
	//flip the bottom most card face-up
	public void flipLastCard(Graphics2D g) {
		if (cards.size() > 0) {
			int lastIndex = cards.size()-1;
			Card card = cards.get(lastIndex);
			card.drawFront(g);
		}
	}

	public int completedCards() {
		return completedCards;
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
	
	public Card getLast() {
		int index = cards.size() - 1;
		return cards.get(index);
	}
	
	public void removeLast() {
		int index = cards.size() - 1;
		cards.remove(index);
	}
}
