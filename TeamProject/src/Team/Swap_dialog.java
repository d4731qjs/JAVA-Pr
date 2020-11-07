package Team;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Swap_dialog extends JDialog{
	
	JButton[] swap = new JButton[3];
	String[] buttonString = {"CPU1", "CPU2", "CPU3"};
	Base base;
	CPU1_Area CPU1;
	CPU2_Area CPU2;
	CPU3_Area CPU3;
	Card_Dispenser CD;
	public Swap_dialog(Base base, CPU1_Area CPU1, CPU2_Area CPU2, CPU3_Area CPU3,Card_Dispenser CD) {
		super(base,"교환 상대 선택",true);
		setLayout(null);
		this.base = base;
		this.CPU1 = CPU1;
		this.CPU2 = CPU2;
		this.CPU3 = CPU3;
		this.CD = CD;
		for(int i=0; i < swap.length; i++) {
			swap[i] = new JButton(buttonString[i]);
			swap[i].setSize(80,30);
			ButtonListener bl = new ButtonListener();
			swap[i].addActionListener(bl);
			add(swap[i]);
			
		}
		swap[0].setLocation(50,50);
		swap[1].setLocation(150,50);
		swap[2].setLocation(250,50);
		setSize(400,170);
		setVisible(false);
	}
	
	public void set_Button() {
		swap[0].setEnabled(CD.CPU1_alive);
		swap[1].setEnabled(CD.CPU2_alive);
		swap[2].setEnabled(CD.CPU3_alive);
	}
	class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			
			JButton jb = (JButton)e.getSource();
			String text = jb.getText();
			
			switch(text) {
			case "CPU1" : 
				CPU1.swap_Interface();
				break;
			case "CPU2" : 
				CPU2.swap_Interface();
				break;
			case "CPU3" : 
				CPU3.swap_Interface();
				break;
			}
			
		}
	}
}
