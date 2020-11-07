package Team;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;

//80, 120 80,120 210,190
@SuppressWarnings("serial")
class Center extends JPanel {
	JLabel jackLabel = new JLabel("NEW BLACKJACK",SwingConstants.CENTER);
	JLabel playerLabel = new JLabel("",SwingConstants.CENTER);
	ImageIcon icon = new ImageIcon("PNG//gray_back.png");
	Image img = icon.getImage();
	Point p = new Point(210,190);
	Base base;
	int playerNum;
	int getPlayerNum = 0;
	Card_Dispenser CD = null;
	JButton jb = new Button();
	public Center() {
		
		this.setLayout(null);
		JPanel jackPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		jackPanel.setSize(500,80);
		jackPanel.setLocation(0, 0);
		jackPanel.setBackground(new Color(1, 124, 1));
		jackPanel.add(jackLabel);
		playerPanel.setSize(500,80);
		playerPanel.setLocation(0, 420);
		playerPanel.setBackground(new Color(1, 124, 1));
		playerPanel.add(playerLabel);
		this.add(jackPanel);
		this.add(playerPanel);
		
		jackLabel.setFont(new Font("HY°ß°íµñ",Font.BOLD,40));
		jackLabel.setForeground(Color.RED);
		playerLabel.setFont(new Font("HY°ß°íµñ",Font.BOLD,18));
		
		jb.setSize(80,120);
		jb.setLocation(210,190);
		
		jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(jb);
		this.setBackground(new Color(1, 124, 1));
		
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CD.makeThread();
				jb.setEnabled(false);
			}
		});
		
	}
	
	public void setCardButton(boolean bol) {
		jb.setEnabled(bol);
	}
	
	class Button extends JButton{
		ImageIcon icon = new ImageIcon("PNG//gray_back.png");
		Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			g.drawImage(img,0,0,this.getWidth(),this.getHeight(),this);
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, (int) p.getX(), (int) p.getY(), 80, 120, this);
	}


	public void getPoint(Point p) {
		this.p.setLocation(p);
	}
	
	public void getDispenser(Card_Dispenser CD) {
		this.CD = CD;
	}
	
	public void getAlert(String text1, String text2) {
		jackLabel.setText(text1);
		playerLabel.setText(text2);
	}
	
	public void restart() {
		jb.setEnabled(true);
		jb.setText("Round : 1");
		jackLabel.setText("NEW BLACKJACK");
		playerLabel.setText("");
	}
}
