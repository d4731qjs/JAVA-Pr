package Team;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.JPanel;

public class Card_Dispenser {
	CPU1_Area CPU1;
	CPU2_Area CPU2;
	CPU3_Area CPU3;
	Player_Area PA;
	Center center;
	Base base;
	RankDialog rank;
	SkillExDialog skillex;
	int getx = 210, gety = 190, rotation_Count, random, player_sum, 
			CPU3_sum, CPU2_sum, CPU1_sum, round = 1, die_Count;
	
	File file;
	BufferedReader br;
	FileWriter fw;
	FileReader fr;
	
	Vector<Integer> CPU1_Nums = new Vector<Integer>();
	Vector<Integer> CPU2_Nums = new Vector<Integer>();
	Vector<Integer> CPU3_Nums = new Vector<Integer>();
	Vector<Integer> player_Nums = new Vector<Integer>();
	Vector<Integer> draw = new Vector<Integer>();
	HashMap<String,Integer> playersRank;
	
	Point p = new Point(210, 190);
	boolean player_alive = true;
	boolean CPU1_alive = true;
	boolean CPU2_alive = true;
	boolean CPU3_alive = true;
	
	File[] PlayerCards;// 카드배열
	File[] CPU1Cards;
	File[] CPU3Cards;
	static int pwc=0;
	int[] num = { 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 1, 1, 2, 2, 3, 3};
	int[] sum= {player_sum, CPU3_sum, CPU2_sum, CPU1_sum};
	String[] name= {"player","CPU1","CPU2","CPU3"};
	
	String now_Player;
	ThreadClass thread;

	public Card_Dispenser(Base base, CPU1_Area CPU1, CPU2_Area CPU2, CPU3_Area CPU3, Player_Area PA, Center center,	String now_Player,SkillExDialog skillex, RankDialog rank) {
		this.CPU1 = CPU1;
		this.CPU2 = CPU2;
		this.CPU3 = CPU3;
		this.PA = PA;
		this.center = center;
		this.base = base;
		this.skillex = skillex;
		File Player_Dir = new File("PNG//PLAYER_CO2");
		PlayerCards = Player_Dir.listFiles();
		File CPU1_Dir = new File("PNG//CO1");
		CPU1Cards = CPU1_Dir.listFiles();
		File CPU3_Dir = new File("PNG//CO3");
		CPU3Cards = CPU3_Dir.listFiles();
		this.now_Player = now_Player;
		playersRank = base.getHM();
		this.rank = rank;
	}

	public void shuffleCard() {

		String label;

		if (player_alive && player_Nums.size() < 6) {
			getNums(getRandom(), rotation_Count % 4);

			for (int i = 0; i < 190; i += 2) {
				setPlayerCard();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

			label = "PNG//PLAYER_CO2//" + PlayerCards[random].getName();
			PA.getLabel(label, player_sum);
		}

		getx = 210;
		gety = 190;
		rotation_Count++;

		if (CPU1_alive) {
			getNums(getRandom(), rotation_Count % 4);

			for (int i = 0; i < 210; i += 2) {
				setCPU1Card();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

			if (this.rotation_Count < 4) {
				label = "PNG//CO1//" + CPU1Cards[random].getName();
			} else {
				label = "PNG//gray_back1.png";
			}
			CPU1.getLabel(label);
		}

		getx = 210;
		gety = 190;
		rotation_Count++;

		if (CPU2_alive) {
			getNums(getRandom(), rotation_Count % 4);

			for (int i = 0; i < 210; i += 2) {
				setCPU2Card();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}

			if (this.rotation_Count < 4) {
				label = "PNG//PLAYER_CO2//" + PlayerCards[random].getName();
			} else {
				label = "PNG//gray_back.png";
			}
			CPU2.getLabel(label);
		}

		getx = 210;
		gety = 190;
		rotation_Count++;

		if (CPU3_alive) {
			getNums(getRandom(), rotation_Count % 4);

			for (int i = 0; i < 210; i += 2) {
				setCPU3Card();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}

			}

			if (this.rotation_Count < 4) {
				label = "PNG//CO3//" + CPU3Cards[random].getName();
			} else {
				label = "PNG//gray_back1.png";
			}
			CPU3.getLabel(label);
		}

		getx = 210;
		gety = 190;
		
		center.getPoint(new Point(210, 190));
		center.repaint();
		rotation_Count++;
	}

	// -------------------------------------------------------------------------------

	public void setPlayerCard() {
		gety++;
		p.setLocation(getx, gety);
		center.getPoint(p);
		center.repaint();
	}

	public void setCPU1Card() {
		getx--;
		p.setLocation(getx, gety);
		center.getPoint(p);
		center.repaint();
	}

	public void setCPU2Card() {
		gety--;
		p.setLocation(getx, gety);
		center.getPoint(p);
		center.repaint();
	}

	public void setCPU3Card() {
		getx++;
		p.setLocation(getx, gety);
		center.getPoint(p);
		center.repaint();
	}

	// -------------------------------------------------------------------------------

	public int getRandom() {
		while (true) {
			boolean same_Exited = false;
			random = (int) (Math.random() * 26);
			for (int i = 0; i < draw.size(); i++) {
				int tmp = draw.get(i);// 중복있는지 없는지 체크
				if (tmp == random) {
					same_Exited = true;
					break;
				}
			}
			if (!same_Exited) {
				draw.add(random);
				return random;
			}
		}
	}

	// -------------------------------------------------------------------------------

	public void getNums(int random, int rotation_Count) {
		if (rotation_Count == 0) {
			if (player_sum <= 21) {
				player_Nums.add(random);
				
				player_sum += num[player_Nums.get(round - 1)];
			}

		} else if (rotation_Count == 1) {
			if (CPU1_sum <= 21) {
				CPU1_Nums.add(random);
				CPU1_sum += num[CPU1_Nums.get(round - 1)];
			}
		} else if (rotation_Count == 2) {
			if (CPU2_sum <= 21) {
				CPU2_Nums.add(random);
				CPU2_sum += num[CPU2_Nums.get(round - 1)];
			}
		} else {
			if (CPU3_sum <= 21) {
				CPU3_Nums.add(random);
				CPU3_sum += num[CPU3_Nums.get(round - 1)];
			}
		}
	}

	// -------------------------------------------------------------------------------

	public void deathReading() {

		String label  = "";
		Vector<String> ST = new Vector<String>();
		Vector<Integer> DS = new Vector<Integer>();
		boolean died = false;
		
		if (player_sum > 21 && player_alive) {
			PA.delete_label();
			ST.add(now_Player);
			DS.add(player_sum);
			return_Num(player_Nums);
			base.setSkillButton(false);
			base.setButtonEnable(true);
			base.setButtonText("다시하기");
			died = true;
			die_Count++;
			player_alive = false;
		}if (CPU1_sum > 21 && CPU1_alive) {
			CPU1.delete_label();
			ST.add("CPU1");
			DS.add(CPU1_sum);
			return_Num(CPU1_Nums);
			died = true;
			die_Count++;
			CPU1_alive = false;
		}if (CPU2_sum > 21 && CPU2_alive) {
			CPU2.delete_label();
			ST.add("CPU2");
			DS.add(CPU2_sum);
			return_Num(CPU2_Nums);
			died = true;
			die_Count++;
			CPU2_alive = false;
		}if (CPU3_sum > 21 && CPU3_alive) {
			CPU3.delete_label();
			ST.add("CPU3");
			DS.add(CPU3_sum);
			return_Num(CPU3_Nums);
			died = true;
			die_Count++;
			CPU3_alive = false;
		}
		
		if(died) {
			for(int i = 0; i < ST.size(); i++) {
				label += ST.get(i) + ", ";
				//rank.setValue(label, ST.get(i));//진 사람의 이름, 벡터의 인덱스
			}
			
			int index = label.lastIndexOf(',');
			label = label.substring(0, index);
			center.getAlert("Dead", label + " Scored over 21");
		}
	}

	
	public boolean winReading() {
		int winCount = 0;
		Vector<String> winName = new Vector<String>();
		
		if(player_sum == 21){
			winName.add(now_Player);
			winCount++;
			for(int i = 0; i < player_Nums.size(); i++) {
				PA.won_label("PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(i)].getName());
			}
		}if(CPU1_sum == 21){
			winName.add("CPU1");
			winCount++;
			for(int i = 0; i < CPU1_Nums.size(); i++) {
				CPU1.won_label("PNG//CO1//" + CPU1Cards[CPU1_Nums.get(i)].getName());
			}
		}if(CPU2_sum == 21){
			winName.add("CPU2");
			winCount++;
			for(int i = 0; i < CPU2_Nums.size(); i++) {
				CPU2.won_label("PNG//PLAYER_CO2//" + PlayerCards[CPU2_Nums.get(i)].getName());
			}
		}if(CPU3_sum == 21){
			winName.add("CPU3");
			winCount++;
			for(int i = 0; i < CPU3_Nums.size(); i++) {
				CPU3.won_label("PNG//CO3//" + CPU3Cards[CPU3_Nums.get(i)].getName());
			}
		}
		
		if(winCount != 0) {
			String text = "";
			if(winCount >= 2) {
				for(int i =0; i < winName.size(); i++) {
					text += winName.get(i) + ", ";
				}
				
				int index = text.lastIndexOf(',');
				text = text.substring(0, index);
				center.getAlert("DRAW",text + " Get 21 points at the same time");
				//rank.setDtext("winner : "+text);
				
			}else {
				text = winName.get(0);
				center.getAlert("VICTORY!!",text + " Achieves 21 points");
				if(text.equals(now_Player)) refactorHashMap();
					
				
			}
			
			base.setButtonEnable(true);
			base.setButtonText("다시하기");
			center.setCardButton(false);
			base.setSkillButton(false);
			return true;
		}
		
		return false;
	}
	
	
	public boolean aliveCount() {
		if(die_Count == 4) {
			center.getAlert("전멸했습니다.","ALL DEAD");
			base.setButtonEnable(true);
			base.setButtonText("다시하기");
			base.setSkillButton(false);
			return true;
		}else if(die_Count == 3) {
			String text = "";
			base.setSkillButton(false);
			
			if(player_alive){
				text = now_Player;
				for(int i = 0; i < player_Nums.size(); i++) {
					PA.won_label("PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(i)].getName());
				}
			}if(CPU1_alive){
				text = "CPU1";
				for(int i = 0; i < CPU1_Nums.size(); i++) {
					CPU1.won_label("PNG//CO1//" + CPU1Cards[CPU1_Nums.get(i)].getName());
				}
			}if(CPU2_alive){
				text = "CPU2";
				for(int i = 0; i < CPU2_Nums.size(); i++) {
					CPU2.won_label("PNG//PLAYER_CO2//" + PlayerCards[CPU2_Nums.get(i)].getName());
				}
			}if(CPU3_alive){
				text = "CPU3";
				for(int i = 0; i < CPU3_Nums.size(); i++) {
					CPU3.won_label("PNG//CO3//" + CPU3Cards[CPU3_Nums.get(i)].getName());
				}
			}
			for(int i=0;i<sum.length;i++) {
	            if(sum[i]==21) {
	               center.getAlert("VICTORY!!",text + "가 21점을 달성해 승리하였습니다!");
	               base.setButtonEnable(true);
	               base.setButtonText("다시하기");
	               if(text.equals(now_Player)) refactorHashMap();
	               
	            }else {
	            	center.getAlert("VICTORY!!",text + " Survived until all dead");
	            	base.setButtonEnable(true);
	            	base.setButtonText("다시하기");
	            	if(text.equals(now_Player)) refactorHashMap();
	            }
	            return true;
	         }
		}
		return false;
	}
	
	
	// -------------------------------------------------------------------------------

	public void return_Num(Vector<Integer> vector) {
		for (int i = 0; i < vector.size(); i++) {
			for (int j = 0; j < draw.size(); j++) {
				if (draw.get(j) == vector.get(i)) {
					draw.remove(j);
				}
			}
		}
	}

	// -------------------------------------------------------------------------------

	public void compareScore() {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		if (player_alive) {
			int sum = 0;
			for (int i = 0; i < player_Nums.size(); i++) {
				sum += num[player_Nums.get(i)];
			}
			hm.put(now_Player, sum);
		}

		if (CPU1_alive) {
			int sum = 0;
			for (int i = 0; i < CPU1_Nums.size(); i++) {
				sum += num[CPU1_Nums.get(i)];
				String label = "PNG//CO1//" + CPU1Cards[CPU1_Nums.get(i)].getName();
				CPU1.won_label(label);
			}

			hm.put("CPU1", sum);
		}

		if (CPU2_alive) {
			int sum = 0;
			for (int i = 0; i < CPU2_Nums.size(); i++) {
				sum += num[CPU2_Nums.get(i)];
				String label = "PNG//PLAYER_CO2//" + PlayerCards[CPU2_Nums.get(i)].getName();
				CPU2.won_label(label);
			}
			hm.put("CPU2", sum);
		}

		if (CPU3_alive) {
			int sum = 0;
			for (int i = 0; i < CPU3_Nums.size(); i++) {
				sum += num[CPU3_Nums.get(i)];
				String label = "PNG//CO3//" + CPU3Cards[CPU3_Nums.get(i)].getName();
				CPU3.won_label(label);
			}
			hm.put("CPU3", sum);
		}

		Set<String> keys = hm.keySet();
		Iterator<String> it = keys.iterator();
		int highNum = 0;
		String highPlayer = "";
		int lastNum = 0;

		int sameCount = 0;
		while (it.hasNext()) {
			String key = it.next();
			lastNum = hm.get(key);
			if (lastNum > highNum) {
				highNum = lastNum;
				highPlayer = key;
			} else if (lastNum == highNum) {
				sameCount++;
			}
		}

		if (sameCount >= 1)
			center.getAlert("DRAW","무승부입니다.");
		else {
			center.getAlert("VICTORY!!",highPlayer + " has a higher score");
			if(highPlayer.equals(now_Player)) refactorHashMap();
		}
		base.setButtonEnable(true);
		base.setButtonText("다시하기");
		
	}

	// -------------------------------------------------------------------------------

	public void restart() {
		getx = 210;
		gety = 190;
		rotation_Count = 0;
		player_sum = 0;
		CPU3_sum = 0;
		CPU2_sum = 0;
		CPU1_sum = 0;
		round = 1;
		die_Count = 0;
		CPU1_Nums.clear();
		CPU2_Nums.clear();
		CPU3_Nums.clear();
		player_Nums.clear();
		draw.clear();
		player_alive = true;
		CPU1_alive = true;
		CPU2_alive = true;
		CPU3_alive = true;

	}

	// -------------------------------------------------------------------------------
	public void allMix(boolean getOver) {
		
		int length = draw.size();
		Vector<Integer> randoms = new Vector<Integer>();
		Vector<Integer> random_Nums = new Vector<Integer>();
		for(int i = 0; i < length; i++) {
			while(true) {
				boolean hasSameExited = false;
				int random = (int)(Math.random()*length);
				for(int j = 0; j < random_Nums.size(); j++) {
					if(random == random_Nums.get(j)) {
						hasSameExited = true;
						break;
					}
				}
				if(!hasSameExited) {
					randoms.add(draw.get(random));
					random_Nums.add(random);
					break;
				}
			}	
		}
		
		System.out.println(length);
		for(int i = 0; i < length; i++) {
			if(i%4 == 0 && player_alive) {
				player_Nums.set(i/(4-die_Count), randoms.get(i));
			}else if(i%4 == 1 && CPU1_alive) {
				CPU1_Nums.set(i/(4-die_Count), randoms.get(i));
			}else if(i%4 == 2 && CPU2_alive) {
				CPU2_Nums.set(i/(4-die_Count), randoms.get(i));
			}else if(i%4 == 3 && CPU3_alive) {
				CPU3_Nums.set(i/(4-die_Count), randoms.get(i));
			}
		}
		
		PA.restart();
		player_sum = 0;
		CPU1_sum = 0;
		CPU2_sum = 0;
		CPU3_sum = 0;
		String label;
		for(int i = 0; i < player_Nums.size(); i++) {
			label = "PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(i)].getName();
			PA.getLabel(label);
			if(player_alive)
			player_sum += num[player_Nums.get(i)];
		}
		
		for(int i = 0; i < CPU1_Nums.size(); i++) {
			if(CPU1_alive)
			CPU1_sum += num[CPU1_Nums.get(i)];
			if(CPU2_alive)
			CPU2_sum += num[CPU2_Nums.get(i)];
			if(CPU3_alive)
			CPU3_sum += num[CPU3_Nums.get(i)];
		}
		
		PA.getLabel(player_sum);
		if(CPU1_alive) 
		CPU1.allMix("PNG//CO1//" + CPU1Cards[CPU1_Nums.get(0)].getName());
		if(CPU2_alive) 
		CPU2.allMix("PNG//PLAYER_CO2//" + PlayerCards[CPU2_Nums.get(0)].getName());
		if(CPU3_alive) 
		CPU3.allMix("PNG//CO3//" + CPU3Cards[CPU3_Nums.get(0)].getName());
		base.setSkillButton(getOver);
		deathReading();
		
		if(aliveCount()) return;
		if(winReading()) return;
	}
	
	public void back_one(boolean getOver) {
		round--;
		player_sum -= num[player_Nums.get(player_Nums.size()-1)];
		PA.getLabel(player_sum);
		CPU1_sum -= num[CPU1_Nums.get(CPU1_Nums.size()-1)];
		CPU2_sum -= num[CPU2_Nums.get(CPU2_Nums.size()-1)];
		CPU3_sum -= num[CPU3_Nums.get(CPU3_Nums.size()-1)];
		System.out.println(player_sum);
		System.out.println(CPU1_sum);
		System.out.println(CPU2_sum);
		System.out.println(CPU3_sum);
		
		player_Nums.remove(player_Nums.size()-1);
		CPU1_Nums.remove(CPU1_Nums.size()-1);
		CPU2_Nums.remove(CPU2_Nums.size()-1);
		CPU3_Nums.remove(CPU3_Nums.size()-1);
		
		int size = draw.size();
		for(int i = size-1; i >=size-4; i--) {
			draw.remove(i);
		}
		
		PA.back1();
		CPU1.back1();
		CPU2.back1();
		CPU3.back1();
		rotation_Count-=4;
		base.setSkillButton(getOver);
	}
	
	public void swap_start() {
		PA.swap_Interface();
	}
	
	public void swap_end(String text) {
		
		center.setCardButton(true);
		int player_index = PA.player_index-1;
		int player_getNums = player_Nums.get(player_index);
		player_sum -= num[player_getNums];
		int CPU_index;
		switch(text) {
		case "CPU1" :
			CPU_index = CPU1.CPU1_index;
			CPU1_sum -= num[CPU1_Nums.get(CPU_index)];
			player_Nums.set(player_index, CPU1_Nums.get(CPU_index));
			player_sum += num[player_Nums.get(player_index)];
			
			CPU1_Nums.set(CPU_index,player_getNums);
			CPU1_sum += num[CPU1_Nums.get(CPU_index)];
			PA.swap_end("PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(player_index)].getName(),player_sum);
			if(CPU_index == 0) 
				CPU1.swap_end("PNG//CO1//" + CPU1Cards[CPU1_Nums.get(CPU_index)].getName());
			break;
		case "CPU2" :
			CPU_index = 5-CPU2.CPU2_index;
			CPU2_sum -= num[CPU2_Nums.get(CPU_index)];
			player_Nums.set(player_index, CPU2_Nums.get(CPU_index));
			player_sum += num[player_Nums.get(player_index)];
			
			CPU2_Nums.set(CPU_index,player_getNums);
			CPU2_sum += num[CPU2_Nums.get(CPU_index)];
			PA.swap_end("PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(player_index)].getName(),player_sum);
			if(CPU_index == 0) 
				CPU2.swap_end("PNG//PLAYER_CO2//" + PlayerCards[CPU2_Nums.get(CPU_index)].getName());
			
			break;
		case "CPU3" :
			CPU_index = 5-CPU3.CPU3_index;
			CPU3_sum -= num[CPU3_Nums.get(CPU_index)];
			player_Nums.set(player_index, CPU3_Nums.get(CPU_index));
			player_sum += num[player_Nums.get(player_index)];
			
			CPU3_Nums.set(CPU_index,player_getNums);
			CPU3_sum += num[CPU3_Nums.get(CPU_index)];
			PA.swap_end("PNG//PLAYER_CO2//" + PlayerCards[player_Nums.get(player_index)].getName(),player_sum);
			if(CPU_index == 0) 
				CPU3.swap_end("PNG//CO3//" + CPU3Cards[CPU3_Nums.get(CPU_index)].getName());
			
			break;
		}
		
		if(skillex.getCount() == 2) base.setSkillButton(false);
		else base.setSkillButton(true);
		deathReading();
		
		if(aliveCount()) return;
		if(winReading()) return;
	}
	
	public void get_Onemore(boolean getOver) {
		getNums(getRandom(), 0);

		for (int i = 0; i < 190; i += 2) {
			setPlayerCard();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		
		center.getPoint(new Point(210, 190));
		center.repaint();

		String label = "PNG//PLAYER_CO2//" + PlayerCards[random].getName();
		PA.getLabel(label, player_sum);
		
		base.setSkillButton(getOver);
		deathReading();
		
		if(aliveCount()) return;
		if(winReading()) return;
		
	}
	
	public void refactorHashMap() {
		
		int value = playersRank.get(now_Player);
		
		value++;
		
		Set<String> keys = playersRank.keySet();
		Iterator<String> it = keys.iterator();
		Vector<String> keyV  = new Vector<String>();
		while(it.hasNext()) {
			keyV.add(it.next());
		}
		int index = keyV.indexOf(now_Player);
		rank.tableUpdate(now_Player, value, index);
		
		//playersRank.remove(now_Player);
		playersRank.put(now_Player,value);
		
		file = new File("Rank.txt");
		try {
			fw = new FileWriter(file,false);
			keys = playersRank.keySet();
			it = keys.iterator();
			while(it.hasNext()) {
				String key = it.next();
				fw.write(key + " " + playersRank.get(key) + "\n");
				fw.flush();
			}
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}	
	// -------------------------------------------------------------------------------
	
	public void makeThread() {
		thread = new ThreadClass();
		thread.start();
	}

	// -------------------------------------------------------------------------------

	class ThreadClass extends Thread {
		@Override
		public void run() {

			base.setButtonText("Round : " + round);
			shuffleCard();
			
			System.out.println("player : " + player_sum);
			System.out.println("CPU1 : " + CPU1_sum);
			System.out.println("CPU2 : " + CPU2_sum);
			System.out.println("CPU3 : " + CPU3_sum);
			System.out.println(round);
			
			deathReading();
			
			if(aliveCount()) return;
			if(winReading()) return;
			
			
			if (round == 6) {
				center.setCardButton(false);
				base.setSkillButton(false);
				compareScore();
				return;
			}
			if(round == 2) {
				base.setSkillButton(true);
			}
			
			if(!player_alive) {
				center.setCardButton(false);
			}else {
				center.setCardButton(true);
			}
			round++;
			
			
			try {}catch(Exception e) {
				return;
			}
		}

	}
}
