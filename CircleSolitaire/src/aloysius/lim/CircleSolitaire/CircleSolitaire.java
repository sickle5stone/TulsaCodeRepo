package aloysius.lim.CircleSolitaire;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import aloysius.lim.mycommonmethods.FileIO;
import aloysius.lim.mycomponents.TitleLabel;

public class CircleSolitaire extends JFrame {
/**
 * Author: Aloysius Lim
 * Date Created: 9-9-18
 * Last Updated: 10-16-18
 */
	
	//Serialization of recreation
	private static final long serialVersionUID = 1L;

	//Create table panel
	private TablePanel tablePanel = new TablePanel();
	private Font font = new Font(Font.DIALOG, Font.BOLD, 24);
	private TimerPanel timerPanel = new TimerPanel(0, new Font(Font.DIALOG, Font.BOLD, 48));
	private static final String BG_MUSIC = "Clippity-Clop.wav";
	
	//Constructor
	public CircleSolitaire() {
		Clip clip = FileIO.playClip(this,BG_MUSIC,true);
		showInstruction();
		initGUI();
		setTitle("CircleSolitaire");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Instructional Screen: Displays instruction for the Circle Solitaire game
	private void showInstruction() {
		
		JPanel myPanel = new JPanel(new BorderLayout());
		JOptionPane jop = new JOptionPane();
		JDialog dialog = jop.createDialog("Welcome to Circle Solitaire!");
		JDialog.setDefaultLookAndFeelDecorated(true);

		//Title of the game
		TitleLabel gameTitle = new TitleLabel("CircleSolitaire");
		myPanel.add(gameTitle,BorderLayout.PAGE_START);

		//Instructional Text
		JTextArea textArea = new JTextArea(10,1);
		String instruction = "    To play Circle Solitaire, start from the center pile and drag card to \n appropriate pile position. "
		+ "\n\n    Piles are arranged according to numbers on a clock"
		+ "\n    12PM/12AM : Queen"
		+ "\n    1PM : Ace"
		+ "\n    2PM : 2"
		+ "\n    ..."
		+ "\n    11PM : Jack"
		+ "\n    Middle: King"
		+ "\n\n    Press Start below to proceed.";
		textArea.setBackground(new Color(22,103,75));
		textArea.setForeground(Color.WHITE);
		textArea.setFont(font);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText(instruction);
		myPanel.add(textArea,BorderLayout.CENTER);
		
		//Adding the start button to the bottom
		JButton button = new JButton("Start Game");
		button.setFont(font);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.setVisible(false);
			}
		});
		//Button Panel
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.BLACK);
		btnPanel.add(button,BorderLayout.PAGE_END);
		myPanel.add(btnPanel,BorderLayout.PAGE_END);
		
		dialog.setSize(1000, 600);
		dialog.setContentPane(myPanel);
		dialog.setVisible(true);
	
	}
	
	//Initialize GUI 
	private void initGUI() {

		
		TitleLabel gameTitle = new TitleLabel("CircleSolitaire");
		add(gameTitle,BorderLayout.PAGE_START);
		add(tablePanel,BorderLayout.CENTER);
		
		//Button Panel
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.BLACK);
		add(btnPanel,BorderLayout.PAGE_END);
		
		//start btn
		JButton newBTN= new JButton ("New Game");
		newBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablePanel.newGame();
				timerPanel.reset();
			}//actionPerformed
		});
		btnPanel.add(newBTN);
		btnPanel.add(timerPanel);
		timerPanel.reset();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {			
			String className = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(className);
		}catch(Exception e){}
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new CircleSolitaire();
			}
		});
	}
	
	
	
}
