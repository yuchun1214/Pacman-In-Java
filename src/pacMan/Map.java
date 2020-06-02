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
import org.json.JSONObject;


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
	private Timer collisionTimer1;
	private Timer collisionTimer2;
	private Timer collisionTimer3;
	private Timer collisionTimer4;
	private Timer pacmanTimer;
	private Timer chasingTimer;
	private ElfMovingFire EMF1;
	private ElfMovingFire EMF2;
	private ElfMovingFire EMF3;
	private ElfMovingFire EMF4;
	private ElfMovingFire pacmanEMF;
	private CollisionDetector CDEf1;
	private CollisionDetector CDEf2;
	private CollisionDetector CDEf3;
	private CollisionDetector CDEf4;
	private int score;
	public Map() throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		// variable initialization
		super();
		this.timer1 = new Timer();
		this.timer2 = new Timer();
		this.timer3 = new Timer();
		this.timer4 = new Timer();
		this.pacmanTimer = new Timer();
		this.collisionTimer1 = new Timer();
		this.collisionTimer2 = new Timer();
		this.collisionTimer3 = new Timer();
		this.collisionTimer4 = new Timer();
		this.chasingTimer = new Timer();
		
		this.score = 0;
		File gameconfig = new File("src/pacman/gameconfig.json");
		FileInputStream gcf = new FileInputStream(gameconfig);
		byte[] data = new byte[(int) gameconfig.length()];
		gcf.read(data);
		gcf.close();
		String gConfig = new String(data, "UTF-8");
		// System.out.println(gConfig);
		JSONObject gconfig = new JSONObject(gConfig);
		JSONArray pacman_start_pos = new JSONArray(gconfig.get("pacman_start_pos").toString());
		JSONArray big_pellet_pos = new JSONArray(gconfig.get("big_pellet_pos").toString());
		JSONArray elves_pos = new JSONArray(gconfig.get("elf_start_pos").toString());
		JSONArray elfpos;
		// Frame Initialize
		this.setSize(585, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setContentPane(new MapBackground());
		
		
		// setup elf
		elfpos = new JSONArray(elves_pos.get(0).toString());
		this.elf1 = new Blinky(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(this.elf1);
		this.elf1.x=9;
		this.elf1.y=9;
		this.elf1.state=true;
		
		elfpos = new JSONArray(elves_pos.get(1).toString());
		this.elf2 = new Pinky(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf2);
		elf2.x=18;
		elf2.y=16;
		elf2.state=true;
		
		elfpos = new JSONArray(elves_pos.get(2).toString());
		this.elf3 = new BlueElf(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf3);
		elf3.state = true;
		
		elfpos = new JSONArray(elves_pos.get(3).toString());
		this.elf4= new YellowElf(elfpos.getInt(0)*Constant.SCALE,elfpos.getInt(1)*Constant.SCALE);
		add(elf4);
		elf4.state = true;
		
		pacman = new Pacman(pacman_start_pos.getInt(0)*Constant.SCALE, pacman_start_pos.getInt(1)*Constant.SCALE);
		add(pacman);
		pacman.state=true;
		
		EMF1 = new ElfMovingFire(pacman);
		EMF2 = new ElfMovingFire(pacman);
		EMF3 = new ElfMovingFire(pacman);
		EMF4 = new ElfMovingFire(pacman);
		CDEf1 = new CollisionDetector(pacman, elf1, this);
		CDEf2 = new CollisionDetector(pacman, elf2, this);
		CDEf3 = new CollisionDetector(pacman, elf3, this);
		CDEf4 = new CollisionDetector(pacman, elf4, this);
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
		
		File mazeconfigfile = new File("src/pacMan/mazeconfig.json");
		FileInputStream mzcff = new FileInputStream(mazeconfigfile);
		data = new byte[(int) mazeconfigfile.length()];
		mzcff.read(data);
		mzcff.close();
		String config = new String(data, "UTF-8");
		
		JSONArray jar = new JSONArray(config);
		JSONArray temp1;
		for(int i = 0; i < jar.length(); ++i) {
			temp1 = new JSONArray(jar.get(i).toString());
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setWall();
			Dots.get(25 - temp1.getInt(0)).get(temp1.getInt(1)).setWall();
		}
	
		for(int i = 0; i < big_pellet_pos.length(); ++i) {
			temp1 = new JSONArray(big_pellet_pos.get(i).toString());
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setToSugar();
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
		try {
			return Dots.get(x).get(y).isOk();		
		}catch(java.lang.IndexOutOfBoundsException e) {
			return false;
		}
	}

	
	public void pacmanWalk(int x, int y) {
		Dot d = this.Dots.get(x).get(y);
		if(!d.isEaten()) {
			if(d.isSugar()) {
				this.elf1.state = false;
				this.elf2.state = false;
				this.elf3.state = false;
				this.elf4.state = false;
				this.chasingTimer.schedule(new ChaseTimerTask(this.elf1, this.elf2, this.elf3, this.elf4), 2500);		
			}
			this.Dots.get(x).get(y).eaten();
			// add the score
		}
	}
	

	
	public void pause() {
		timer1.cancel();
		timer2.cancel();
		timer3.cancel();
		timer4.cancel();
		pacmanTimer.cancel();
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
		collisionTimer1.schedule(CDEf1, 0, 10);
		collisionTimer2.schedule(CDEf2, 0, 10);
		collisionTimer3.schedule(CDEf3, 0, 10);
		collisionTimer4.schedule(CDEf4, 0, 10);
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
