package pacMan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class ElfBase extends JComponent {
	public Image img;
	public Image deadimg;
	public int x,y,dir,nextdir;//x,y:0~25,dir:1~4 上下左右 0:沒有
	public Map map;
	public boolean state;//pacman:true:無敵，鬼:true=追
	private int diameter;
	private int startx, starty;
	public ElfBase(int startx, int starty, String filepath) throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		super();
		this.img = ImageIO.read(new File(filepath));
		this.deadimg = ImageIO.read(new File("src/image/ghost/Dead1.png"));
		this.diameter = Constant.DIAMETER;
		this.setSize(this.diameter, this.diameter);
		this.setLocation(startx, starty);
		this.setVisible(true);
		this.startx = startx;
		this.starty = starty;
		x = startx/22;
		y=starty/22;
		dir=0;
		nextdir=0;
	}
	
	public void backToStartPos() {
		this.setLocation(startx, starty);
		x = startx / 22;
		y = starty / 22;
		this.state = true;
	}
	
	public void beGhost() {
		this.state = false;
		this.repaint();
	}
	
	public void beElf() {
		this.state = true;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.diameter, this.diameter, null);
		
	}
	public void mapIn(Map map1)
	{
		map=map1;
	}
	public abstract void Move(int x, int y,ElfBase pac)throws IOException;
}
