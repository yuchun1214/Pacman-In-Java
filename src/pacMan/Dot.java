package pacMan;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Dot extends JComponent {
	private Image img;
	private boolean ok;
	private boolean dot;
	private boolean sugar;
	private int diameter;
	public Dot(int posx, int posy, int diameter) throws IOException {
		// TODO Auto-generated constructor stub
		super();
		this.dot = true;
		this.ok = true;
		this.diameter = diameter;
		this.img = ImageIO.read(new File("src/image/bonus/dot.png")); 
		
		this.setLocation(posx, posy);
		this.setSize(this.diameter, this.diameter);
		this.setVisible(true);
		
		this.addMouseListener(new CustomizeMouseEventListener("Mouse Click", this));
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		if(this.dot == true)
			g.drawImage(img,  0,  0, this.diameter, this.diameter, null);
	}
	
	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	public boolean isOk() {
		return this.ok;
	}
	
	public void setWall() {
		this.dot = false;
		this.ok = false;
		repaint();
	}
}
