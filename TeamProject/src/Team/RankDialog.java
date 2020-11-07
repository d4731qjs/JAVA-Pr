package Team;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
@SuppressWarnings("serial")
public class RankDialog extends JDialog{
	
	HashMap<String,Integer> player;
	JTable table;
	Vector<String> colData = new Vector<String>();
	Vector<Vector<String>> rowData = new Vector<Vector<String>>();
	public RankDialog(JFrame frame, String title, HashMap<String,Integer> player) {
		super(frame, title, true);
		setLayout(new BorderLayout());
		this.player = player;
		this.colData.add("이름");
		this.colData.add("점수");
		
		Set<String> keys = player.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String name = it.next();
			int value = player.get(name);
			Vector<String> vector = new Vector<String>();
			vector.add(name);
			vector.add(Integer.toString(value));
			rowData.add(vector);
		}
		table = new JTable(rowData,colData);
		JScrollPane js = new JScrollPane(table);
		this.add(js,BorderLayout.NORTH);
		setSize(500,500);
	}
	
	public void tableUpdate(String name, int value, int index) {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
	     m.removeRow(index);
	     String[] str = {name, Integer.toString(value)};
	     m.insertRow(index,str);
	   
	     //m.insertRow(jt.getRowCount()+1, str);  명령으로 수정하여 실행도 해 보시오.
	}
	
}
