package pacMan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Condition extends JComponent {
	Image loseIconImage;
	Image winIconImage;
	Image heart1;
	Image heart2;
	Image heart3;
	
	public void Condition() throws IOException {
		this.loadImage();
	}
	
	public void loadImage() throws IOException {
		this.loseIconImage = ImageIO.read(new File("src/image/win_fail/fail.png"));
		this.winIconImage = ImageIO.read(new File("src/image/win_fail/win.png"));
		this.heart1 = ImageIO.read(new File("src/image/win_fail/heart1.png"));
		this.heart2 = ImageIO.read(new File("src/image/win_fail/heart2.png"));
		this.heart3 = ImageIO.read(new File("src/image/win_fail/heart3.png"));
	}
	public void paint(Graphics g) {
		super.paint(g);
		paintLose(g);
	}
	public void paintLose(Graphics g) {
		g.drawImage(loseIconImage,10,10,  500,  500,  null);
	}
	
}
