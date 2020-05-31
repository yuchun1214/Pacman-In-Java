package pacMan;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Anime implements Runnable{
	private ElfBase elf;
	private int type;
	public Image img1;
	public Image img2;
	
	public void run()
	{
		switch(type) 
		{
		case 1:
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE, elf.y*Constant.SCALE-i);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			elf.y--;
			elf.nextdir=2;
			break;
		case 2:
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE, elf.y*Constant.SCALE+i);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			elf.y++;
			elf.nextdir=1;
			break;
		case 3:
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE-i, elf.y*Constant.SCALE);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			elf.x--;
			elf.nextdir=4;
			break;
		case 4:
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE+i, elf.y*Constant.SCALE);
					if(i%2==1) {elf.img=img2;}
					else {elf.img=img1;}
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
			elf.x++;
			elf.nextdir=3;
			break;
		}
	}
	public Anime(ElfBase n,int type2,Image i1,Image i2) {
		elf=n;
		type=type2;
		img1=i1;
		img2=i2;
	}

}
