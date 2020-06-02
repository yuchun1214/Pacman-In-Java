package pacMan;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Image;

public class Pacman extends ElfBase implements KeyListener{
	
	private int direction;
	
	private ArrayList<ArrayList<Image>> Images = new ArrayList<ArrayList<Image>>();
	
	private ArrayList<Image> up_imgs = new ArrayList<Image>();
	private ArrayList<Image> down_imgs = new ArrayList<Image>();
	private ArrayList<Image> left_imgs = new ArrayList<Image>();
	private ArrayList<Image> right_imgs = new ArrayList<Image>();

	public Pacman(int startx, int starty) throws IOException, InterruptedException {
		super(startx, starty, "src/image/pacman/Pacman_D_1.png");
		this.setFocusable(true);
		this.addKeyListener(this);
        this.setFocusable(true);
        String []sufix = "UDLR".split("");
        Image img;
        ArrayList<Image> imgs = new ArrayList<Image>();
        for(int i = 0; i < sufix.length; ++i) {
        	imgs = new ArrayList<Image>();
        	for(int j = 1; j < 4; ++j) {
        		img = ImageIO.read(new File("src/image/pacman/Pacman_" + sufix[i] + "_" + Integer.toString(j) + ".png"));
        		imgs.add(img);
        	}
        	Images.add(imgs);
        }
	}



	@Override
	public void Move(int x, int y, ElfBase pac) throws IOException {
		// TODO Auto-generated method stub
		int currentx = this.getX() / Constant.SCALE;
		int currenty = this.getY() / Constant.SCALE;
		int type = 0;
		Thread thread;
		if(direction == KeyEvent.VK_UP && this.map.avaliable(currentx, currenty - 1)) {
			type = 1;
		}else if(direction == KeyEvent.VK_DOWN && this.map.avaliable(currentx, currenty + 1)) {
			type = 2;
		}else if(direction == KeyEvent.VK_RIGHT && this.map.avaliable(currentx + 1, currenty)) {
			type = 4;
		}else if(direction == KeyEvent.VK_LEFT && this.map.avaliable(currentx - 1, currenty)) {
			type = 3;
		}
		
		if(type == 0) {
			thread = new Thread(new Anime(this, type, Images.get(type)));
		}else {
			thread = new Thread(new Anime(this, type, Images.get(type - 1)));
		}
		
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key pressed!!");
		this.direction = e.getKeyCode();
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
