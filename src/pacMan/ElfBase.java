package pacMan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class ElfBase extends JComponent {
	public Image img;
	public int x,y,dir,nextdir;//x,y:0~25,dir:1~4 上下左右 0:沒有
	public Map map;
	public boolean state;//pacman:true:無敵，鬼:true=追
	private int diameter;
	
	public ElfBase(int startx, int starty, String filepath,Map map1) throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		super();
		this.img = ImageIO.read(new File(filepath));
		this.diameter = Constant.DIAMETER;
		this.setSize(this.diameter, this.diameter);
		this.setLocation(startx, starty);
		this.setVisible(true);
		x = startx/22;
		y=starty/22;
		map = map1;
		dir=0;
		nextdir=0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.diameter, this.diameter, null);
		
	}
	
	public abstract void Move(int x, int y)throws IOException;
}
