package aloysius.lim.mycomponents;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class TitleLabel extends JLabel {

/**
 * Author: Aloysius Lim 
 * Date Created: 8-23-18
 * Last Updated: 8-23-18
 */
	
	//Serialization for recreation
	private static final long serialVersionUID = 1L;
	
	//Constructor
	public TitleLabel (String title) {
		//Font.serif Font.san_serif
		Font myFont = new Font("Garamond",Font.BOLD,32); 
		setFont(myFont);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		setText(title);
	}//End of Constructor
	
}
