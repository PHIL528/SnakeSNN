package SnakePKG;

import java.io.Serializable;

import processing.core.PApplet;

//ProcessingPDE is responsible for painting the display after each draw() and getting user input


public class SnakeDisplay extends PApplet implements Runnable {
	int ix = 480;
	int iy = 20;
	boolean wait = false;

	public void run() {
		PApplet.main(concat(new String[] {"SnakePKG.SnakeDisplay"}, new String[] {}));
	}
	public void settings(){
		size(1200,900);
	}
	public void setup() {
		background(50); 
		rendersnakespace();
	}
	public void rendernet() {
		
		clear();
		background(50);
		rendersnakespace();
		rendersnake();
		strokeWeight(1);
		int px1;
		int py1;
		for(int i=0;i<SnakeMain.HoldNet.Nodes.length-4;i++) {
			if(SnakeMain.HoldNet.Nodes[i].AX) {
				px1 = SnakeMain.HoldNet.Nodes[i].PX;
				py1 = SnakeMain.HoldNet.Nodes[i].PY;
				for(int b=0;b<SnakeMain.HoldNet.Nodes[i].AxonIDList.length;b++) {
					stroke(139,69,19);
					int px2 = SnakeMain.HoldNet.Nodes[SnakeMain.HoldNet.Nodes[i].AxonIDList[b]].PX;
					int py2 = SnakeMain.HoldNet.Nodes[SnakeMain.HoldNet.Nodes[i].AxonIDList[b]].PY;
					line(px1+ix,py1+iy,px2+ix,py2+iy);
				}
			}
		}
		for(int i=0;i<SnakeMain.HoldNet.Nodes.length;i++) {
			if(SnakeMain.HoldNet.Nodes[i].AX) {
				px1 = SnakeMain.HoldNet.Nodes[i].PX;
				py1 = SnakeMain.HoldNet.Nodes[i].PY;
				fill(255,200,200);
				rect(ix+px1,iy+py1,4,4);
				
			}
		}
	}
	public void renderallnet() {
		clear();
		background(50);
		rendersnakespace();
		rendersnake();
		strokeWeight(1);
		int px1;
		int py1;
		for(int i=0;i<SnakeMain.HoldNet.Nodes.length;i++) {
			px1 = SnakeMain.HoldNet.Nodes[i].PX;
			py1 = SnakeMain.HoldNet.Nodes[i].PY;
		
			for(int b=0;b<SnakeMain.HoldNet.Nodes[i].AxonIDList.length;b++) {
				stroke(139,69,19);
				int px2 = SnakeMain.HoldNet.Nodes[SnakeMain.HoldNet.Nodes[i].AxonIDList[b]].PX;
				int py2 = SnakeMain.HoldNet.Nodes[SnakeMain.HoldNet.Nodes[i].AxonIDList[b]].PY;
				line(px1+ix,py1+iy,px2+ix,py2+iy);
			}
		}
		for(int i=0;i<SnakeMain.HoldNet.Nodes.length;i++) {
	
				px1 = SnakeMain.HoldNet.Nodes[i].PX;
				py1 = SnakeMain.HoldNet.Nodes[i].PY;
				fill(255,200,200);
				rect(ix+px1,iy+py1,4,4);

		}
		
	}
	public void draw() {
		
		if(SnakeMain.speed == 5000) {
			wait(1000);
		} else if(SnakeMain.mode == 2) {
			rendersnakespace();
			rendersnake();
		} else if(SnakeMain.mode == 11) {
			if(SnakeMain.cont1) {
				renderallnet();
			}
		} else if(SnakeMain.mode == 12) {
			rendernet();
			SnakeMain.plsRender = false;
		}
		
		

	}
	
	public void rendersnakespace() {
		fill(153);
		for(int a=0;a<SnakeMain.dim;a++) {
			for(int b=0;b<SnakeMain.dim;b++) {
				rect(20+20*a,20+20*b,20,20);
			}
		}
	}
	public void rendersnake() {
		fill(204, 102, 0);
		//System.out.println("Size is "+this.HoldSnake.Size);
		//System.out.println("XP/YP 0 is "+this.HoldSnake.XPos[0]+" "+this.HoldSnake.YPos[0]);
		//System.out.println("XP/YP 0 is "+this.HoldSnake.XPos[1]+" "+this.HoldSnake.YPos[1]);
		for(int i=0;i<SnakeMain.HoldSnake.Size;i++) {
			rect(20+20*(SnakeMain.HoldSnake.XPos[i]),20+20*(SnakeMain.HoldSnake.YPos[i]),20,20);
		}
		
		fill(255,0,0);
		rect(20+20*(SnakeMain.HoldSnake.AppleX),20+20*(SnakeMain.HoldSnake.AppleY),20,20);
	}
	public static void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }	
	public void keyPressed() {
		//key is given by ProcessingPDE
		if(SnakeMain.mode==0) {
			if(key==CODED) {
				if(keyCode==CONTROL) {
					System.out.println("KEY SET MODE 1");
					SnakeMain.mode = 1;
				}
			} else if(key == 's') {
				SnakeMain.cont1 = false;
				SnakeMain.cont2 = false;
				SnakeMain.mode = 11;
			}
		} else if(SnakeMain.mode==2) {
			if(key==CODED) {
				//System.out.println("Reg U/D/L/R");
				if(keyCode == UP && SnakeMain.HoldSnake.Ori!=270) {
					SnakeMain.HoldSnake.NextOri = 90;
				} else if(keyCode == DOWN && SnakeMain.HoldSnake.Ori!=90) {
					SnakeMain.HoldSnake.NextOri = 270;
				} else if(keyCode == RIGHT && SnakeMain.HoldSnake.Ori!=180) {
					SnakeMain.HoldSnake.NextOri = 0;
				} else if(keyCode == LEFT && SnakeMain.HoldSnake.Ori!=0) {
					SnakeMain.HoldSnake.NextOri = 180;
				}
			}
		} else if(SnakeMain.mode==11) {
			if(key=='p') {
				SnakeMain.KeyBuffer = 'p';
			} else if(key == 'd') {
				SnakeMain.cont1 = false;
				SnakeMain.cont2 = true;
			}
		} else if(SnakeMain.mode==12) {
			if(key=='j') {
				SnakeMain.speed = 5000;
			} else if(key=='k') {
				SnakeMain.speed = 500;
			} else if(key=='l') {
				SnakeMain.speed = 50;
			} else if(key=='h') {
				SnakeMain.speed = 333;
			} else if(key=='g') {
				SnakeMain.speed = 111;
			} else if(key=='t') {
				SnakeMain.savenum = 1000000000;
				SnakeMain.terminate = true;
			} else if(key=='i') {
				SnakeMain.HoldNet.th -= 0.01;
				System.out.println("Thssdreshold now at "+SnakeMain.HoldNet.th);
			} else if(key=='o') {
				SnakeMain.HoldNet.th += 0.01;
				System.out.println("Threshold now at "+SnakeMain.HoldNet.th);
			} else if(key=='z') {
				SnakeMain.HoldNet.dc -= 0.002;
				System.out.println("Decay now at "+SnakeMain.HoldNet.dc);
			} else if(key=='x') {
				SnakeMain.HoldNet.dc += 0.002;
				System.out.println("Decay now at "+SnakeMain.HoldNet.dc);
			}
		}
		
	}
}
