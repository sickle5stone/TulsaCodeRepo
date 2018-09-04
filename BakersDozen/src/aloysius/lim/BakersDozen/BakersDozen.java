package aloysius.lim.BakersDozen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import aloysius.lim.mycomponents.TitleLabel;

public class BakersDozen extends JFrame {
/**
 * Author: Aloysius Lim
 * Date Created: 8-30-18
 * Last Updated: 9-4-18
 */
	
	//Serialization of recreation
	private static final long serialVersionUID = 1L;

	//Create table panel
	private TablePanel tablePanel = new TablePanel();
	
	//Constructor
	public BakersDozen() {
		initGUI();
		setTitle("Baker's Dozen");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Initialize GUI 
	private void initGUI() {
		TitleLabel gameTitle = new TitleLabel("Baker's Dozen");
		add(gameTitle,BorderLayout.PAGE_START);
		add(tablePanel,BorderLayout.CENTER);
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
				new BakersDozen();
			}
		});
	}
	
	
	
}
