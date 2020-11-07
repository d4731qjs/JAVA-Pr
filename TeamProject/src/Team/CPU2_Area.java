package Team;

import java.awt.*;
import javax.swing.*;

import Team.CPU1_Area.CardLabel;
import Team.CPU1_Area.MyMouse;

import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

public class CPU2_Area extends JPanel implements Area_Master{

	JLabel[] Cards = new JLabel[6];
	int CardsIndex = 5;
	int wonIndex = 5;
	int CPU2_index;
	Card_Dispenser CD;
	public void getLabel(String label, int sum) {}
	
	public CPU2_Area() {

		this.setBackground(new Color(1, 124, 1));
		this.setLayout(new GridLayout(1, 6, 6, 5));
		for (int i = 0; i < 6; i++) {
			Cards[i] = new CardLabel("",SwingConstants.CENTER);
			this.add(Cards[i]);
		}

	}
	
	public void getCD(Card_Dispenser CD) {
		this.CD = CD;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D ghp = (Graphics2D) g;
		ghp.setStroke(new BasicStroke(10));
		g.setColor(new Color(0,255,255));
		g.drawRect(0, 0, getWidth(), getHeight());

	}

	public void getLabel(String label) {
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(70, 110, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[CardsIndex].setIcon(icon);
		CardsIndex--;
	}
	
	public void delete_label() {
		for (int i = 0; i < Cards.length; i++) {
			Cards[i].setIcon(null);
		}
	}
	
	public void won_label(String label) {
		
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(70, 110, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[wonIndex].setIcon(icon);
		wonIndex--;
	}
	
	public void allMix(String label) {
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(70, 110, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[5].setIcon(icon);
	}
	
	class CardLabel extends JLabel{
		MyMouse mymouse = null;
		boolean getMouse = false;
		public CardLabel(String text, int swing) {
			super(text,swing);
		}
		
		public void setMouseListener(boolean bol) {
			if(bol) {
				mymouse = new MyMouse(this);
				this.addMouseListener(mymouse);
			}else {
				this.removeMouseListener(mymouse);
			}
		}
		
		public void setMouse(boolean mouse) {
			getMouse = mouse;
			repaint();
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(getMouse) {
				Graphics2D ghp = (Graphics2D) g;
				ghp.setStroke(new BasicStroke(5));
				g.setColor(new Color(0,255,255));
				g.drawRoundRect(2, 18, 73, 113, 12, 12);
			}
		}
	}

	public void back1() {
		Cards[++CardsIndex].setIcon(null);
	}
	
	public void swap_Interface() {
		for(int i=0; i < Cards.length; i++) {
			if(Cards[i].getIcon() != null) {
				((CardLabel) Cards[i]).setMouseListener(true);
				Cards[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		}
	}
	
	public void swap_end(String label) {
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(70, 110, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[CPU2_index].setIcon(icon);
	}
	
	class MyMouse extends MouseAdapter{
		CardLabel cardLabel = null;
		public MyMouse(CardLabel cardLabel) {
			this.cardLabel = cardLabel;
		}
		
		public void mousePressed(MouseEvent e) {
			for(int i = 1; i < Cards.length; i++) {
				if(Cards[i] == cardLabel) {
					CPU2_index = i;
					break;
				}
			}
		
			for(int i=Cards.length-1; i > 0 ; i--) {
				if(Cards[i].getIcon() != null) {
					((CardLabel)Cards[i]).setMouseListener(false);
					((CardLabel)Cards[i]).setMouse(false);
					Cards[i].setCursor(null);
				}
			}
		
			CD.swap_end("CPU2");
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			cardLabel.setMouse(true);
		}@Override
		public void mouseExited(MouseEvent e) {
			cardLabel.setMouse(false);
		}
	}
	public void restart() {
		for (int i = 0; i < Cards.length; i++) {
			Cards[i].setIcon(null);
		}
		CardsIndex = 5;
		wonIndex = 5;
	}
}
