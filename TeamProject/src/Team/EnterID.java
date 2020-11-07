package Team;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class EnterID extends JFrame{
	File file;
	BufferedReader br;
	FileWriter fw;
	FileReader fr;
	
	HashMap<String,Integer> player= new HashMap<String,Integer>();
	private JLabel login=new JLabel("Enter ID",SwingConstants.CENTER);
	private JLabel id=new JLabel("ID",SwingConstants.CENTER);
	private JTextField idT=new JTextField(10);
	private ConfirmButton jb = new ConfirmButton("입장");
	private JLabel warning = new JLabel("",SwingConstants.CENTER);
	private ImageIcon icon= new ImageIcon("images/블랙잭 로그인 이미지.jpg");
	private Image img=icon.getImage();
	private myPanel panel=new myPanel();
	public EnterID() {
		setTitle("BlackJack EnterID");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		login.setFont(new Font("HY견고딕",Font.BOLD,30));
		login.setForeground(Color.YELLOW);
		id.setForeground(Color.YELLOW);
		
		try {
			file = new File("Rank.txt");
			fr = new FileReader(file);//파일을 읽어 옴
			br = new BufferedReader(fr);
			
			String Line;
			while((Line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(Line," ");
				int i = 0;
				String[] token = new String[2];
				while(st.hasMoreTokens()) {
					token[i] = st.nextToken();
					i++;
				}
				if(token[0]!=null&&token[1]!=null)
					player.put(token[0],Integer.parseInt(token[1]));
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		setSize(300,300);
		setVisible(true);
	}
	
	class myPanel extends JPanel{
		
		public myPanel() {
			
			add(login);
			JPanel jpanel = new JPanel();
			jpanel.setBackground(new Color(255,0,0,0));
			jpanel.setLayout(null);
			id.setSize(50, 50);
			id.setLocation(10, 10);
			jpanel.add(id);
			idT.setSize(150, 30);
			idT.setLocation(50, 20);
			jpanel.add(idT);
			jb.setSize(100, 30);
			jb.setLocation(50,70 );
			jpanel.add(jb);
			warning.setSize(150,50);
			warning.setLocation(30, 90);
			jpanel.add(warning);
			jpanel.setPreferredSize(new Dimension(200, 200));
			this.add(jpanel);
			
			
		}public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0,getWidth(), getHeight(), this);
		}
	}
	
	class ConfirmButton extends JButton{
		boolean already_Has_Name = false;
		String name;
		String ID_Input;
		public ConfirmButton(String text) {
			super(text);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ID_Input= idT.getText();
					
					if(ID_Input.equals("")) {
						warning.setForeground(Color.RED);
						warning.setText("이름을 입력해주세요");
					}else {
						Set<String> keys = player.keySet();
						Iterator<String> it = keys.iterator();
						while(it.hasNext()) {
							name = it.next();
							System.out.println("EnterID 해시맵에 있는 이름: "+name);
							if(name.equals(ID_Input)) {
								already_Has_Name = true;
								break;
							}
						}
						if(already_Has_Name) {
							new Base(name,player);
						}else {
							player.put(ID_Input,0);
							try {
								fw = new FileWriter(file,true);
								fw.write(ID_Input + " 0" + "\n");
								fw.flush();
								
							}catch(IOException ex) {
								System.out.println(ex.getMessage());
							}
							new Base(ID_Input,player);
						}
						
						dispose();
					}
				}
			});
		}
	}
	
	public static void main(String[] args) {
		new EnterID();
	}
}
