package Team;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

public class Player_Area extends JPanel implements Area_Master{
	
	int sum;
	JLabel[] Cards = new JLabel[7];
	int CardsIndex = 1;
	int wonIndex = 1;
	Card_Dispenser CD;
	int player_index ;
	Base base;
	
	public Player_Area(Base base) {
		
		this.base = base;
		this.setBackground(new Color(1, 124, 1));
		this.setLayout(new GridLayout(1, 7, 17, 17));
		Cards[0] = new JLabel("",SwingConstants.RIGHT);
		this.add(Cards[0]);
		for (int i = 1; i < 7; i++) {
			Cards[i] = new CardLabel("",SwingConstants.CENTER);
			this.add(Cards[i]);
		}
		Cards[0].setText(Integer.toString(sum));
		Cards[0].setFont(new Font("Arial",Font.BOLD,60));
		Cards[0].setForeground(new Color(200,50,0));
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D ghp = (Graphics2D) g;
		ghp.setStroke(new BasicStroke(10));
		g.setColor(Color.RED);
		g.drawRect(0, 0, getWidth(), getHeight());
	}
	
	public void getLabel(String label) {
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[CardsIndex].setIcon(icon);
		CardsIndex++;
	}
	
	public void getLabel(int sum) {
		this.sum = sum;
		Cards[0].setText(Integer.toString(sum));
	}
	
	public void getLabel(String label, int sum) {
		this.sum = sum;
		Cards[0].setText(Integer.toString(sum));
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[CardsIndex].setIcon(icon);
		CardsIndex++;
	}
	
	public void delete_label() {
			for (int i = 1; i < Cards.length; i++) {
				Cards[i].setIcon(null);
			}
	}
	
	public void won_label(String label) {
		
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[wonIndex].setIcon(icon);
		wonIndex++;
		
	}
	
	public void restart() {
		for (int i = 1; i < Cards.length; i++) {
			Cards[i].setIcon(null);
		}
		
		sum = 0;
		Cards[0].setText(Integer.toString(sum));
		CardsIndex = 1;
		wonIndex = 1;
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
				g.setColor(new Color(255,0,0));
				g.drawRoundRect(7, 13, 83, 124, 15, 15);
			}
		}
	}
	
	public void back1() {
		Cards[--CardsIndex].setIcon(null);
		
	}
	
	public void swap_Interface() {
		for(int i=0; i < Cards.length; i++) {
			if(Cards[i].getIcon() != null) {
				((CardLabel) Cards[i]).setMouseListener(true);
				Cards[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		}
	}
	
	public void swap_end(String label, int sum) {
		ImageIcon img = new ImageIcon(label);
		Image originImage = img.getImage();
		Image changeImage = originImage.getScaledInstance(80, 120, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(changeImage);
		Cards[player_index].setIcon(icon);
		Cards[0].setText(Integer.toString(sum));
	}
	
	class MyMouse extends MouseAdapter{
		CardLabel cardLabel = null;
		public MyMouse(CardLabel cardLabel) {
			this.cardLabel = cardLabel;
		}
		@Override
		public void mousePressed(MouseEvent e) {
			for(int i = 1; i < Cards.length; i++) {
				if(Cards[i] == cardLabel) {
					player_index = i;
					break;
				}
			}
			
			base.swap_Set_Button();
			base.swap_Visible(true);
		for(int i=0; i < Cards.length; i++) {
			if(Cards[i].getIcon() != null) {
				((CardLabel)Cards[i]).setMouseListener(false);
				((CardLabel)Cards[i]).setMouse(false);
				Cards[i].setCursor(null);
			}
		}
	}
		@Override
		public void mouseEntered(MouseEvent e) {
			cardLabel.setMouse(true);
		}@Override
		public void mouseExited(MouseEvent e) {
			cardLabel.setMouse(false);
		}
	}

}
