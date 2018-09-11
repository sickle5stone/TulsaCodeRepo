package aloysius.lim.CircleSolitaire;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DrawDeckImages {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 9-9-18
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
//		Graphics g = img.getGraphics();
		Graphics2D g = img.createGraphics();
		BufferedImage clockImg;
		try {
			clockImg = ImageIO.read(DrawDeckImages.class.getResourceAsStream("clock.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			clockImg = null;
		}
		Graphics2D clockGraphic = null;
		if (clockImg != null) {
			clockGraphic = img.createGraphics();
		}
		
		
		g.setColor(new Color(0,0,0,0));
//		g.fillRect(0, 0, imageWidth, imageHeight);
		g.fill3DRect(0, 0, imageWidth, imageHeight, true);
		
		String fileName = "cards.png";
		File file = new File(fileName);
		
		//Font Style
		Font font = new Font(Font.DIALOG, Font.BOLD, 14);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);

		//Randomize color scheme of cards
		Random randomizerColors = new Random();
		
		//List of available colors
		ArrayList<Color> colorList = new ArrayList<>();
		colorList.add(Color.BLUE);
		colorList.add(Color.RED);
		colorList.add(Color.ORANGE);
		colorList.add(Color.GREEN);

		
		//Draw Cards
		for (int row = 0, y = 0; row < 4; row++ , y+=cardHeight ) {
			//Roll random for random color scheme
			int randColorIndex = randomizerColors.nextInt(colorList.size());
			
			for (int col = 0, x = 0 ; col < 13; col++ , x+=cardWidth) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(x, y, cardWidth-1, cardHeight-1, 8, 8);
//				g.fill3DRect(x, y, cardWidth-1, cardHeight-1, true);
//				g.fill
				
				//Randomized coloring scheme
				g.setColor(colorList.get(randColorIndex));
								
//				//Draw rank onto cards
//				String rank = ranks[col];
//				int rankWidth = fm.stringWidth(rank);
//				int fromLeftRank = x + cardWidth/2 - rankWidth/2;
//				int fromTopRank = y + 20;
//				g.drawString(rank, fromLeftRank , fromTopRank);

				//Draw rank onto cards
				String topRank = ranks[col];
				int topRankWidth = fm.stringWidth(topRank);
				int fromLeftTopRank = x + 5;
				int fromTopTopRank = y + 15;
				g.drawString(topRank, fromLeftTopRank , fromTopTopRank);
				
				//Draw suit onto cards 
				String topSuit = suits[row];
				int topSuitWidth = fm.stringWidth(topSuit);
				int fromLeftTopSuit = x + 4;
				int fromTopTopSuit = y + 30;
				g.drawString(topSuit, fromLeftTopSuit, fromTopTopSuit);
				
				String botRank = ranks[col];
				int botRankWidth = fm.stringWidth(botRank);
				int fromLeftBotRank = x + 33;
				int fromTopBotRank = y + 65;
				g.drawString(botRank, fromLeftBotRank , fromTopBotRank);
				
				String botSuit = suits[row];
				int botSuitWidth = fm.stringWidth(botSuit);
				int fromLeftBotSuit = x + 31;
				int fromTopBotSuit = y + 50;
				g.drawString(botSuit, fromLeftBotSuit, fromTopBotSuit);
				
				
				if (clockGraphic != null) {					
					clockGraphic.drawImage(clockImg, x+30, y+5, cardWidth/3, cardHeight/4, null);
					clockGraphic.drawImage(clockImg, x+4, y+45, cardWidth/3, cardHeight/4, null);
				}

			}
			//Remove current color to ensure even distribution of colors
			colorList.remove(randColorIndex);
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
