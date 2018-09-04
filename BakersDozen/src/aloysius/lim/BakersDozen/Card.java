package aloysius.lim.BakersDozen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Card {
/**
 * Author: Aloysius Lim
 * Date Created: 9-4-18
 * Last Updated: 9-4-18
 */
	
	/**** Variables ****/
	private String rank = "";
	private int suit = -1;
	private int value = 0;
	private Image img = null;
	
	private static int width = 0;
	private static int height = 0;
	private int x = 0;
	private int y = 0;
	
	//Constructor
	public Card(String rank, int suit, int value, Image img) {
		this.rank = rank;
		this.suit = suit;
		this.value = value;
		this.img = img;
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
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void addToXY(int changeX, int changeY) {
		x += changeX;
		y += changeY;
	}
	
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	public static void drawOutline(Graphics g, int x, int y) {
		g.setColor(Color.BLACK);
		g.drawRoundRect(x, y, width, height, 8, 8);
	}
	
}
