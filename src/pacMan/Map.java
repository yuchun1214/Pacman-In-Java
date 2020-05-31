package pacMan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import org.json.JSONArray;


import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Map extends JFrame{
	
	ArrayList<ArrayList<Dot> >Dots;
	ElfBase elftesting;
	public ElfBase elf1,elf2,pac;
	
	public Map() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		
		// Frame Initialize
		super();
		setSize(585, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setContentPane(new MapBackground());
		// setup elf
		elf1 = new Blinky(9*Constant.SCALE,9*Constant.SCALE);
		add(elf1);
		elf1.x=9;
		elf1.y=9;
		elf1.state=true;
		elf2 = new Pinky(18*Constant.SCALE,16*Constant.SCALE);
		add(elf2);
		elf2.x=18;
		elf2.y=16;
		elf2.state=true;
		
		// Dots initialize
		Dots = new ArrayList<ArrayList<Dot> >();
		ArrayList<Dot> row;
		Dot temp;
		for(int i = 0; i < 570; i += Constant.SCALE) {
			row = new ArrayList<Dot>();
			for(int j = 0; j < 570; j += Constant.SCALE) {
				temp = new Dot(i, j,Constant.DIAMETER);
				row.add(temp);
				add(temp);
			}
			Dots.add(row);
		}
		
		File file = new File("src/pacMan/mazeconfig.json");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String config = new String(data, "UTF-8");
		
		JSONArray jar = new JSONArray(config);
		JSONArray temp1;
		for(int i = 0; i < jar.length(); ++i) {
			temp1 = new JSONArray(jar.get(i).toString());
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setWall();
			Dots.get(25 - temp1.getInt(0)).get(temp1.getInt(1)).setWall();
		}
		
		setVisible(true);
		// System.out.print(this.Dots);
	}
	
	public boolean avaliable(int x, int y) {
		return Dots.get(x).get(y).isOk();		
	}
	public void play(Map map1) throws IOException, InterruptedException
	{
		elf1.mapIn(map1);
		elf2.mapIn(map1);

		while(elf2.x!=16||elf1.y!=14)
		{

			elf1.Move(16,14,pac);//需要pac的資訊
			elf2.Move(1,1,pac);
			Thread.sleep(220);
			//吃人 吃點數
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Map map = new Map();
		map.play(map);

	}

}
