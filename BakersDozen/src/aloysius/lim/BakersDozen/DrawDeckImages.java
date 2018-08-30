package aloysius.lim.BakersDozen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DrawDeckImages {
/**
 * Author: Aloysius Lim
 * Date Created: 8-28-18
 * Last Updated: 8-30-18
 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Get Deck Values
		String[] suits = Deck.getSuitSymbols();
		String[] ranks = Deck.getRanks();
		int cardWidth = Deck.getCardWidth();
		int cardHeight = Deck.getCardHeight();
		
		int imageWidth = 13 * cardWidth;
		int imageHeight = 4 * cardHeight;
		
		BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics(); 
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, imageWidth, imageHeight);
		
		String fileName = "cards.png";
		File file = new File(fileName);
		
		//Font Style
		Font font = new Font(Font.DIALOG, Font.BOLD, 24);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		
		//Draw Cards
		for (int row = 0, y = 0; row < 4; row++ , y+=cardHeight ) {
			for (int col = 0, x = 0 ; col < 13; col++ , x+=cardWidth) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(x, y, cardWidth-1, cardHeight-1, 8, 8);
				
				//Color the row based on row value
				if ( row < 2 ) {
					g.setColor(Color.RED);
				}else {					
					g.setColor(Color.BLACK);
				}
				
				//Draw rank onto cards
				String rank = ranks[col];
				int rankWidth = fm.stringWidth(rank);
				int fromLeftRank = x + cardWidth/2 - rankWidth/2;
				int fromTopRank = y + 20;
				g.drawString(rank, fromLeftRank , fromTopRank);
				
				//Draw suit onto cards 
				String suit = suits[row];
				int suitWidth = fm.stringWidth(suit);
				int fromLeftSuit = x + cardWidth/2 - suitWidth/2;
				int fromTopSuit = y + 45;
				g.drawString(suit, fromLeftSuit, fromTopSuit);

			}
		}
		
		//Drawing and Writing of Cards into file
		try {
			ImageIO.write(img, "png", file);
		}catch (IOException e){
			String message = "Could not save" + fileName;
			JOptionPane.showMessageDialog(null, message);
		}
		
		
		
	}
	
	

}
