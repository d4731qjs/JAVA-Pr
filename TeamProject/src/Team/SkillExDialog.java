package Team;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class SkillExDialog extends JDialog{
	
	Base base;
	Card_Dispenser CD;
	Vector<Integer> CPU1_Nums;
	Vector<Integer> CPU2_Nums;
	Vector<Integer> CPU3_Nums;
	Vector<Integer> player_Nums;
	Vector<Integer> draw;
	int count;
	JButton[] skills = new JButton[4];
	String[] buttonString = {"다섞기","바꾸기","물리기","얻기"};
	ButtonListener bl = new ButtonListener();
	
	public SkillExDialog(Base base) {
		super(base,"기술 목록",true);
		setLayout(null);
		this.base = base; 
		for(int i=0; i < skills.length; i++) {
			skills[i] = new JButton(buttonString[i]);
			skills[i].setSize(100,30);
			skills[i].addActionListener(bl);
			add(skills[i]);
		}
		
		skills[0].setLocation(50,50);
		skills[1].setLocation(200,50);
		skills[2].setLocation(50,150);
		skills[3].setLocation(200,150);
		
		
		setSize(370,290);
		setVisible(false);
	}
	
	public void setVisble(boolean v) {
		this.setVisible(v);
	}

	public void restart() {
		for(int i=0; i < skills.length; i++) {
			skills[i].setEnabled(true);
		}
	}
	
	public void getCD(Card_Dispenser CD) {
		this.CD = CD;
	}
	
	public int getCount() {
		
		return count;
	}

	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			
			JButton jb = (JButton)e.getSource();
			String text = jb.getText();
			count++;
			
			switch(text) {
				
			case "다섞기" :
				System.out.println("다섞기");
				
				skills[0].setEnabled(false);
				
				if(count == 2) 
					CD.allMix(false);
				else 
					CD.allMix(true);
				
				break;
			case "바꾸기" :
					skills[1].setEnabled(false);
					CD.swap_start();

				break;
			case "물리기" :
				
				skills[2].setEnabled(false);
				if(count == 2) 
					CD.back_one(false);
				else 
					CD.back_one(true);
				break;
			case "얻기" :
				
				skills[3].setEnabled(false);
				if(count == 2) 
					CD.get_Onemore(false);
				else 
					CD.get_Onemore(true);
				break;
				
			}
		}
	}
}
