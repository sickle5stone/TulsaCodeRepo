package aloysius.lim.CircleSolitaire;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
 * Last Updated: 9-11-18
 */

	public static void main(String[] args) {

		//Get Deck Values
		String[] suits = Deck.getSuitSymbols();
		String[] ranks = Deck.getRanks();
		int cardWidth = Deck.getCardWidth();
		int cardHeight = Deck.getCardHeight();
		
		int imageWidth = 13 * cardWidth;
		int imageHeight = 4 * cardHeight;
		
		BufferedImage img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		
		//Load custom clock image from file-system and create Graphic2D object from it
		BufferedImage clockImg;
		String clockFileName = "clock.png";
		try {
			clockImg = ImageIO.read(DrawDeckImages.class.getResourceAsStream(clockFileName));
		} catch (IOException e1) {
			String message = "Could not find" + clockFileName;
			clockImg = null;
			JOptionPane.showMessageDialog(null, message);
		}
		
		//Created clock graphic2D object, exposed to other portions of the code
		Graphics2D clockGraphic = null;
		if (clockImg != null) {
			clockGraphic = img.createGraphics();
		}
		
		g.setColor(new Color(0,0,0,0));
		g.fill3DRect(0, 0, imageWidth, imageHeight, true);
		
		//Referencing the filename of the desired output filename
		String fileName = "cards.png";
		File file = new File(fileName);
		
		//Font Style
		Font font = new Font(Font.DIALOG, Font.BOLD, 24);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);

		//Randomize color scheme of cards
		Random randomizerColors = new Random();
		
		//List of available colors
		ArrayList<Color> colorList = new ArrayList<>();
		colorList.add(new Color(57,87,183)); //green
		colorList.add(Color.RED);
		colorList.add(new Color(191,155,48)); //gold
		colorList.add(new Color(104,74,56)); //brown

		
		//Draw Cards
		for (int row = 0, y = 0; row < 4; row++ , y+=cardHeight ) {
			//Roll random for random color scheme
			int randColorIndex = randomizerColors.nextInt(colorList.size());
			
			for (int col = 0, x = 0 ; col < 13; col++ , x+=cardWidth) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(x, y, cardWidth-1, cardHeight-1, 8, 8);

				//Randomized coloring scheme
				g.setColor(colorList.get(randColorIndex));
								
				//Draw top left hand side rank onto cards
				String topRank = ranks[col];
				int topRankWidth = fm.stringWidth(topRank);
				int fromLeftTopRank = x + 15 - topRankWidth/2;
				int fromTopTopRank = y + 25;
				g.drawString(topRank, fromLeftTopRank , fromTopTopRank);
				
				//Draw top left hand side suit onto cards 
				String topSuit = suits[row];
				int topSuitWidth = fm.stringWidth(topSuit);
				int fromLeftTopSuit = x + 15 - topSuitWidth/2;
				int fromTopTopSuit = y + 50;
				g.drawString(topSuit, fromLeftTopSuit, fromTopTopSuit);
				
				//Draw bottom right hand side suit onto cards
				String botRank = ranks[col];
				int botRankWidth = fm.stringWidth(botRank);
				int fromLeftBotRank = x + 65 - botRankWidth/2;
				int fromTopBotRank = y + 135;
				g.drawString(botRank, fromLeftBotRank , fromTopBotRank);
				
				//Draw bottom right hand side suit onto cards
				String botSuit = suits[row];
				int botSuitWidth = fm.stringWidth(botSuit);
				int fromLeftBotSuit = x + 65 - botSuitWidth/2;
				int fromTopBotSuit = y + 110;
				g.drawString(botSuit, fromLeftBotSuit, fromTopBotSuit);
				
				//Draw the custom clock graphic onto the card background
				if (clockGraphic != null) {					
					//setting opacity of the clock graphic2d object to 62.5% opacity
					clockGraphic.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.625f));
					clockGraphic.drawImage(clockImg, x+20, y+50, cardWidth/2, cardHeight/3, null);
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
