package aloysius.lim.CircleSolitaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aloysius.lim.mycommonmethods.FileIO;

/**
 * Author: Aloysius Lim
 * Date Created: 10-02-18
 * Last Updated: 10-16-18
 */

public class TimerPanel extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 1L;

	/**** Variables ****/
	private int width = 150;
	private int height = 48;
	private String timeString = "00:00:00";
	private static long time = 10; //Set timer default
	private Thread timerThread;
	private static final String ALARM_FILE = "alarm.wav"; 
	
	public TimerPanel(long time, Font font) {
		setTimer(time);
		setFont(font);
		height = font.getSize();
		FontMetrics fm = getFontMetrics(font);
		width = fm.stringWidth(timeString);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString(timeString, 0, height);
	}
	
	public Dimension getPreferredSize() {
		Dimension size = new Dimension(width,height);
		return size;
	}
	
	public void setTimer(long time) {
		this.time = time;
		long h = time / 3600;
		long m = (time/60) % 60;
		long s = time % 60;
		timeString = String.format("%02d:%02d:%02d",h,m,s);
		repaint();
	}
	
//	protected void timesUp() {
////		
//		Clip clip = FileIO.playClip(this, ALARM_FILE);
//		System.out.println(clip);
//		String message = "Times Up";
//		JOptionPane.showMessageDialog(this, message);
////		clip.stop();
//	}
	
	// Start a new thread
	public void start() {
		this.stop();
		timerThread = new Thread(this);
		timerThread.start();
	};
	
	public void run() {
		try {
			while (true) {
				time+=1;
				setTimer(time);
				Thread.sleep(1000);
			}
//			timesUp();
		}catch(InterruptedException e) {
			return;
		}
	}
	
	public void stop() {
		if (timerThread != null) {			
			timerThread.interrupt();
			timerThread = null;
		}
	}
	
	public static String getTime() {
		long h = time / 3600;
		long m = (time/60) % 60;
		long s = time % 60;
		return String.format("%02d:%02d:%02d",h,m,s);
	}

	public void reset() {
		// TODO Auto-generated method stub
		time = -1;
		start();
	}
	
	public static void resetTime() {
		time = -1;
	}
	
}

