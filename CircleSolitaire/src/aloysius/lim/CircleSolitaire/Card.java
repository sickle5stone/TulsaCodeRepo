package aloysius.lim.CircleSolitaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Card {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 10-02-18
 */
	
	/**** Variables ****/
	private String rank = "";
	private int suit = -1;
	private int value = 0;
	private Image imgFront = null;
	private Image imgBack = null;
	private boolean cardFlip = false;

	private static int width = 0;
	private static int height = 0;
	private int x = 0;
	private int y = 0;
	
	//Constructor
	public Card(String rank, int suit, int value, Image img, Image cardBack) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
		this.imgFront = img;
		this.imgBack = cardBack;
		width = img.getWidth(null);
		height = img.getHeight(null);
	}

	public String getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//check whether card should be rendered faceup/facedown
	public boolean getCardFlip() {
		return cardFlip;
	}
	
	//flip the card faceup (true), facedown(false)
	public void setCardFlip(boolean flipped) {
		cardFlip = flipped;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addToXY(int changeX, int changeY) {
		x += changeX;
		y += changeY;
	}
	
	public void drawFront(Graphics2D g) {
		g.drawImage(imgFront, x, y, null);
		drawOutline(g,x,y);
	}
	
	public void drawBack(Graphics2D g) {
		g.drawImage(imgBack, x, y, null);
		drawOutline(g,x,y);
	}
	
	public static void drawOutline(Graphics2D g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height, 8, 8);
	}
	
	public boolean contains(int pointX, int pointY) {
		boolean contains = false;
		if (pointX >= x && pointX <= x+width && pointY >= y && pointY <= y+height) {
			contains = true;
		}
		return contains;
	}
	
	public boolean isNear(int pointX, int pointY) {
		boolean isNear = false;
		int offsetX = width/2;
		int offsetY = height;
		if (pointX > x-offsetX && pointX < x+offsetX && pointY > y-offsetY && pointY < y+offsetY) {
			isNear = true;
		}
		return isNear;
	}
	
	//is my card near another card
	public boolean isNear(Card card) {
		int pointX = card.getX();
		int pointY = card.getY();
		boolean isNear = isNear(pointX,pointY);
		return isNear;
	}
	
}
