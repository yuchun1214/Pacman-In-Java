package pacMan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public abstract class ElfBase extends JComponent {
	private Image img;
	private int diameter;
	
	public ElfBase(int startx, int starty, String filepath) throws IOException, InterruptedException {
		// TODO Auto-generated constructor stub
		super();
		this.img = ImageIO.read(new File(filepath));
		this.diameter = Constant.DIAMETER;
		this.setSize(this.diameter, this.diameter);
		this.setLocation(startx, starty);
		this.setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.diameter, this.diameter, null);
	}
	
	public abstract void Move(int x, int y);
}
