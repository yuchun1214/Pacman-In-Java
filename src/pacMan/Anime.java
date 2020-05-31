package pacMan;

public class Anime implements Runnable{
	private ElfBase elf;
	private int type;
	public void run()
	{
		switch(type) 
		{
		case 1:
			for(int i = 1;i<23;i++)
			{
				
				try {
					elf.setLocation(elf.x*Constant.SCALE, elf.y*Constant.SCALE-i);
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
	public Anime(ElfBase n,int type2) {
		elf=n;
		type=type2;
	}

}
