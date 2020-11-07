package Team;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;


public class Base extends JFrame{
	Card_Dispenser CD;
	JButton jb = new JButton("다시하기");
	JButton showR=new JButton("showR");
	JButton skb = new JButton("기술");
	CPU1_Area CPU1;
	CPU2_Area CPU2;
	CPU3_Area CPU3;
	Player_Area PA;
	Center center ;
	SkillExDialog skillex;
	Swap_dialog sd;
	private RankDialog rankDialog;
	String now_Player;
	HashMap<String,Integer> player;
	
	
	public Base(String now_Player, HashMap<String,Integer> player) {
		setTitle("NEW BLACKJACK");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(new Color(1,124,1));
		c.setLayout(null);
		this.player = player; 
		
		//---------------------------------------------------------------------------------
		
		PA = new Player_Area(this);
		PA.setSize(800,150);
		PA.setLocation(0, 650);
		center = new Center();
		center.setSize(500,500);
		center.setLocation(150,150);
		CPU1 = new CPU1_Area();
		CPU1.setSize(150,500);
		CPU1.setLocation(0, 150);
		CPU2 = new CPU2_Area();
		CPU2.setSize(500,150);
		CPU2.setLocation(150, 0);
		CPU3 = new CPU3_Area();
		CPU3.setSize(150,500);
		CPU3.setLocation(650, 150);
		jb.setSize(100,50);
		jb.setLocation(25, 50);
		jb.setEnabled(false);
		jb.addActionListener(new replay());
		skillex = new SkillExDialog(this);
		skillex.setVisible(false);
		skillex.setLocation(230,290);
		//---------------------------------------------------------------------------------
		
		rankDialog=new RankDialog(this,"rank", player);
		CD = new Card_Dispenser(this,CPU1,CPU2,CPU3,PA,center,now_Player,skillex,rankDialog);
		CPU1.getCD(CD);
		CPU2.getCD(CD);
		CPU3.getCD(CD);
		skillex.getCD(CD);
		//---------------------------------------------------------------------------------
		
		showR.setSize(100,30);
		showR.setLocation(680,100);
		showR.setEnabled(false);
		showR.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rankDialog.setVisible(true);// TODO Auto-generated method stub
				
			}
		});
		//---------------------------------------------------------------------------------
		
		skb.setSize(100,30);
		skb.setLocation(680, 30);
		
		sd = new Swap_dialog(this, CPU1, CPU2, CPU3,CD);
		sd.setVisible(false);
		
		skb.setEnabled(false);
		skb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				skillex.setVisible(true);
			}
		});
		
		
		//---------------------------------------------------------------------------------
		
		c.add(PA);
		c.add(CPU1);
		c.add(CPU2);
		c.add(CPU3);
		c.add(center);
		center.getDispenser(CD);
		c.add(jb);
		c.add(showR);
		c.add(skb);
		setVisible(true);
		setSize(815,840);
	}
	
	public void setButtonEnable(boolean bol) {
		jb.setEnabled(bol);
		showR.setEnabled(bol);
	}
	
	public void setButtonText(String round) {
		jb.setText(round);
	}
	
	public void setSkillButton(boolean bol) {
		skb.setEnabled(bol);
	}
	
	public void swap_Visible(boolean bol) {
		sd.setVisible(bol);
	}
	
	public void swap_Set_Button() {
		sd.set_Button();
	}
	
	public HashMap<String,Integer> getHM(){
		return player;
	}
	
	class replay implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			jb.setEnabled(false);
			CD.restart();
			center.restart();
			CPU1.restart();
			CPU2.restart();
			CPU3.restart();
			PA.restart();
			showR.setEnabled(false);
			skillex.restart();
			skb.setEnabled(false);
			skillex.count = 0;
		}
	}
}
