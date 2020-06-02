package pacMan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import java.util.Timer;

import org.json.JSONArray;


import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Map extends JFrame{
	
	ArrayList<ArrayList<Dot> >Dots;
	ElfBase elftesting;
	public ElfBase elf1,elf2,pac, elf3, elf4;
	private Pacman pacman;
	private Timer timer1;
	private Timer timer2;
	private Timer timer3;
	private Timer timer4;
	private Timer pacmanTimer;
	private ElfMovingFire EMF1;
	private ElfMovingFire EMF2;
	private ElfMovingFire EMF3;
	private ElfMovingFire EMF4;
	private ElfMovingFire pacmanEMF;
	
	public Map() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		// Frame Initialize
		super();
		timer1 = new Timer();
		timer2 = new Timer();
		timer3 = new Timer();
		timer4 = new Timer();
		pacmanTimer = new Timer();
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
		
		elf3 = new YellowElf(1*Constant.SCALE, 1*Constant.SCALE);
		add(elf3);
		elf3.state = true;
		
		elf4 = new BlueElf(5 * Constant.SCALE, 4 * Constant.SCALE);
		add(elf4);
		elf4.state = true;
		
		pacman = new Pacman(1*Constant.SCALE, 2*Constant.SCALE);
		add(pacman);
		pacman.state=false;
		
		EMF1 = new ElfMovingFire(pacman);
		EMF2 = new ElfMovingFire(pacman);
		EMF3 = new ElfMovingFire(pacman);
		EMF4 = new ElfMovingFire(pacman);
		pacmanEMF = new ElfMovingFire(pacman);
		// Dots initialize
		Dots = new ArrayList<ArrayList<Dot> >();
		ArrayList<Dot> row;
		Dot temp;
		for(int i = 0; i < 570; i += Constant.SCALE) {
			row = new ArrayList<Dot>();
			for(int j = 0; j < 570; j += Constant.SCALE) {
				temp = new Dot(i, j,Constant.DOTDIAMETER);
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
		EMF1.addAnElf(elf1);
		EMF2.addAnElf(elf2);
		EMF3.addAnElf(elf3);
		EMF4.addAnElf(elf4);
		pacmanEMF.addAnElf(pacman);
		// System.out.print(this.Dots);
	}
	
	public boolean avaliable(int x, int y) {
		return Dots.get(x).get(y).isOk();		
	}
	public void play() throws IOException, InterruptedException
	{
		elf1.mapIn(this);
		elf2.mapIn(this);
		elf3.mapIn(this);
		elf4.mapIn(this);
		pacman.mapIn(this);
		timer1.schedule(EMF1, 0, 100);
		timer2.schedule(EMF2, 0, 100);
		timer3.schedule(EMF3, 0, 100);
		timer4.schedule(EMF4, 0, 100);
		pacmanTimer.schedule(pacmanEMF, 0, 100);
		/*
		while(elf2.x!=16||elf1.y!=14)
		{

			elf1.Move(16,14,pac);//需要pac的資訊
			elf2.Move(1,1,pac);
			elf3.Move(24, 24, pac);
			Thread.sleep(50);
			//吃人 吃點數
		}
		*/
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		Map map = new Map();
		map.play();
		// map.elf3.mapIn(map);
		// map.elf3.Move(5, 4, map.pac);
	}

}
