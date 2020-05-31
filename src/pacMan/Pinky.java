package pacMan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import org.json.JSONArray;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
public class Pinky extends ElfBase{
	public void Move(int tx, int ty) throws IOException {
		tx+=2;
		ty+=2;
		int i=0,j=0,k=0;
		if(state)
		{
			if(x==tx) {
				if(y<ty) {
					for(i=y;i<ty;i++){if(map.avaliable(x,i)){break;}}
					if(i==ty){dir=2;}
					else if(map.avaliable(x,y+1)&&nextdir!=2){dir=2;}
					else {
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y+1)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0&&k==0){dir=1;}
						else if(j==0){dir=3;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==3){dir=4;}else{dir=3;}}
						else if(j<k){if(nextdir==4){dir=3;}else{dir=4;}}
						else if(j==k){if(nextdir==3){dir=4;}else{dir=3;}}
					}
				}
				else {
					for(i=ty;i<y;i++){if(map.avaliable(x,i)){break;}}
					if(i==y){dir=1;}
					else if(map.avaliable(x,y-1)&&nextdir!=1){dir=1;}
					else {
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=x-1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0&&k==0){dir=2;}
						else if(j==0){dir=3;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==3){dir=4;}else{dir=3;}}
						else if(j<k){if(nextdir==4){dir=3;}else{dir=4;}}
						else if(j==k){if(nextdir==4){dir=3;}else{dir=4;}}
					}
				}
			}
			else if(y==ty) {
				if(tx>x)
				{
					for(i=x;i<tx;++i){if(map.avaliable(i,y)){break;}}
					if(i==tx){dir=4;}
					else if(map.avaliable(x+1,y)&&nextdir!=4){dir=4;}
					else {
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=y-1;map.avaliable(x,i);i--){++k;if(map.avaliable(x+1,i)){break;}}
						if(j==0&&k==0){dir=3;}
						else if(j==0){dir=1;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==1){dir=2;}else{dir=1;}}
						else if(j<k){if(nextdir==2){dir=1;}else{dir=2;}}
						else if(j==k){if(nextdir==2){dir=1;}else{dir=2;}}
					}
				}
				else
				{
					for(i=tx;i<x;++i){if(map.avaliable(i,y)){break;}}
					if(i==x){dir=3;}
					else if(map.avaliable(x-1,y)&&nextdir!=3){dir=3;}
					else {
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x-1,i)){break;}}
						for(i=y-1;map.avaliable(x,i);i--){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0&&k==0){dir=4;}
						else if(j==0){dir=1;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==1){dir=2;}else{dir=1;}}
						else if(j<k){if(nextdir==2){dir=1;}else{dir=2;}}
						else if(j==k){if(nextdir==1){dir=2;}else{dir=1;}}
					}
				}
			}
			else if(x<tx&&y<ty) {
				if(tx-x >= ty-y)
				{
					if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==3){dir=1;}else{dir=3;}}
						else if(j<k){if(nextdir==1){dir=3;}else{dir=1;}}
						else if(j==k){if(nextdir==3){dir=1;}else{dir=3;}}
					}
				}
				else {
					if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==3){dir=1;}else{dir=3;}}
						else if(j<k){if(nextdir==1){dir=3;}else{dir=1;}}
						else if(j==k){if(nextdir==1){dir=3;}else{dir=1;}}
					}
				}
			}//人在鬼右下
			else if(x<tx&&y>ty) {
				if(tx-x >= y-ty)
				{
					if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else
					{
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==3){dir=2;}else{dir=3;}}
						else if(j<k){if(nextdir==2){dir=3;}else{dir=2;}}
						else if(j==k){if(nextdir==2){dir=3;}else{dir=2;}}
					}
				}
				else {
					if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else if(nextdir!=4&&map.avaliable(x+1,y)){dir=4;}
					else
					{
						for(i=y+1;map.avaliable(x,i);i++){++j;if(map.avaliable(x+1,i)){break;}}
						for(i=x-1;map.avaliable(i,y);i--){++k;if(map.avaliable(i,y-1)){break;}}
						if(j==0){dir=3;}
						else if(k==0){dir=2;}
						else if(j>k){if(nextdir==3){dir=2;}else{dir=3;}}
						else if(j<k){if(nextdir==2){dir=3;}else{dir=2;}}
						else if(j==k){if(nextdir==3){dir=2;}else{dir=3;}}
					}
				}
			}//人在鬼右上
			else if(x>tx&&y>ty) {
				if(x-tx >= y-ty)
				{
					if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else
					{
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=y+1;map.avaliable(x,i);i++){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0){dir=2;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==2){dir=4;}else{dir=2;}}
						else if(j<k){if(nextdir==4){dir=2;}else{dir=4;}}
						else if(j==k){if(nextdir==2){dir=4;}else{dir=2;}}
					}
				}
				else {
					if(nextdir!=1&&map.avaliable(x,y-1)){dir=1;}
					else if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else
					{
						for(i=x+1;map.avaliable(i,y);i++){++j;if(map.avaliable(i,y-1)){break;}}
						for(i=y+1;map.avaliable(x,i);i++){++k;if(map.avaliable(x-1,i)){break;}}
						if(j==0){dir=2;}
						else if(k==0){dir=4;}
						else if(j>k){if(nextdir==2){dir=4;}else{dir=2;}}
						else if(j<k){if(nextdir==4){dir=2;}else{dir=4;}}
						else if(j==k){if(nextdir==4){dir=2;}else{dir=4;}}
					}
				}
			}//人在鬼左上
			else if(x>tx&&y<ty) {
				if(x-tx >= ty-y)
				{
					if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x-1,y)){break;}}
						for(i=x+1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=4;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==4){dir=1;}else{dir=4;}}
						else if(j<k){if(nextdir==1){dir=4;}else{dir=1;}}
						else if(j==k){if(nextdir==4){dir=1;}else{dir=4;}}
					}
				}
				else {
					if(nextdir!=2&&map.avaliable(x,y+1)){dir=2;}
					else if(nextdir!=3&&map.avaliable(x-1,y)){dir=3;}
					else
					{
						for(i=y-1;map.avaliable(x,i);i--){++j;if(map.avaliable(x-1,y)){break;}}
						for(i=x+1;map.avaliable(i,y);i++){++k;if(map.avaliable(i,y+1)){break;}}
						if(j==0){dir=4;}
						else if(k==0){dir=1;}
						else if(j>k){if(nextdir==4){dir=1;}else{dir=4;}}
						else if(j<k){if(nextdir==1){dir=4;}else{dir=1;}}
						else if(j==k){if(nextdir==4){dir=1;}else{dir=4;}}
					}
				}
			}//人在鬼左下
			switch(dir) {
				case 1:
					if(map.avaliable(x,y-1))
					{
						y--;
						setLocation(x*Constant.SCALE, y*Constant.SCALE);
						img = ImageIO.read(new File("src/image/ghost/YELLOW_D.png"));
						nextdir=2;
					}
					break;
				case 2:
					if(map.avaliable(x,y+1))
					{
						y++;
						setLocation(x*Constant.SCALE, y*Constant.SCALE);
						img = ImageIO.read(new File("src/image/ghost/YELLOW_D.png"));
						nextdir=1;
					}
					break;
				case 3:
					if(map.avaliable(x-1,y))
					{
						x--;
						setLocation(x*Constant.SCALE, y*Constant.SCALE);
						img = ImageIO.read(new File("src/image/ghost/YELLOW_D.png"));
						nextdir=4;
					}
					break;
				case 4:
					if(map.avaliable(x+1,y))
					{
						x++;
						setLocation(x*Constant.SCALE, y*Constant.SCALE);
						img = ImageIO.read(new File("src/image/ghost/YELLOW_D.png"));
						nextdir=3;
					}
					break;
			}
		}
		else {
			/*
	        switch(base->direction){
	        case 1:if(M[base2->situation-15]==1){base2->direction=1;}else if(M[base2->situation-1]==1){base2->direction=3;}else if(M[base2->situation+1]==1){base2->direction=4;} break;
	        case 2:if(M[base2->situation+15]==1){base2->direction=2;}else if(M[base2->situation-1]==1){base2->direction=3;}else if(M[base2->situation+1]==1){base2->direction=4;} break;
	        case 3:if(M[base2->situation-1]==1){base2->direction=3;}else if(M[base2->situation-15]==1){base2->direction=1;}else if(M[base2->situation+15]==1){base2->direction=2;} break;
	        case 4:if(M[base2->situation+1]==1){base2->direction=4;}else if(M[base2->situation+15]==1){base2->direction=2;}else if(M[base2->situation-15]==1){base2->direction=1;} break;
	        }
	        switch(base2->direction){
	        case 1: base2->situation-=15;(base2->y)-=50;base2->setGeometry(base2->x,base2->y,50,50);base2->nextdir=0;break;
	        case 2: base2->situation+=15;(base2->y)+=50;base2->setGeometry(base2->x,base2->y,50,50);base2->nextdir=0;break;
	        case 3: base2->situation--;(base2->x)-=50;base2->setGeometry(base2->x,base2->y,50,50);base2->nextdir=0;break;
	        case 4: base2->situation++;(base2->x)+=50;base2->setGeometry(base2->x,base2->y,50,50);base2->nextdir=0;break;
	        }*/
			//跑
		}
	}
	public Pinky(int x , int y,Map map)throws IOException, InterruptedException
	{
		super(x,y,"src/image/ghost/Red_D.png",map);
	}

}
