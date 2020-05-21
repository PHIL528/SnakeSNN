
package SnakePKG;

import java.io.Serializable;



//Contains the positions for the snake on the board, moves the positions, and 
public class Snake implements Serializable {
	int[] XPos;
	int[] YPos;
	boolean[] iPos;
	int Size;
	int NextOri;
	int Ori;
	
	int AppleX;
	int AppleY;
	int Applei;
	
	int dim;
	int dim2;
	
	int maxsteps;
	int cursteps;
	
	boolean HasApple;
	boolean GTGt;
	
	boolean FT;
	int use;
	int[] AppleSetX;
	int[] AppleSetY;
	int[] AppleSeti; 
	int nextindex;
	
	
	public Snake() {
		this.dim = SnakeMain.dim;
		this.dim2 = SnakeMain.dim*SnakeMain.dim;
		this.XPos = new int[dim2];
		this.YPos = new int[dim2];
		this.iPos = new boolean[dim2];
		this.HasApple = false;
		
		falseiPos();
	
		this.XPos[0] = 2;
		this.YPos[0] = 3;
		this.iPos[xytoi(2,3)] = true;
		
		this.XPos[1] = 1;
		this.YPos[1] = 3;
		this.iPos[xytoi(1,3)] = true;
		
		this.Size = 2;
		this.NextOri = 0;
		this.Ori = 0;
		
		this.maxsteps = 4*this.dim + 4*this.Size;
		this.cursteps = 0;
	
		this.use = 0;
		this.FT = true;
		this.AppleSetX = new int[2080];
		this.AppleSetY = new int[2080];
		this.AppleSeti = new int[2080];
		this.nextindex = 0;
		
		for(int i=0;i<2080;i++) {
			this.AppleSetX[i] = (int) (Math.random()*SnakeMain.dim);
			this.AppleSetY[i] = (int) (Math.random()*SnakeMain.dim);
			this.AppleSeti[i] = xytoi(this.AppleSetX[i],this.AppleSetY[i]);
		}
		
		AddApple();
	}
	public int xytoi(int a, int b) {
		return b*dim + a;
	}
	/*
	public void falseSet() {
		for(int i=0;i<this.AppleSetX.length;i++) {
			this.AppleSetX[i] = 0;
		}
	}
	*/
	public void falseiPos() {
		for(int i=0;i<dim2;i++) {
			this.iPos[i] = false;
		}
	}
	public boolean Move() {
		int Angle = this.Ori;
		for(int i=this.Size;i>1;i--) {
			this.XPos[i-1] = this.XPos[i-2];
			this.YPos[i-1] = this.YPos[i-2];
		}
		if(Angle == 0) {
			this.XPos[0] = this.XPos[0] + 1;
		} else if(Angle == 90) {
			this.YPos[0] = this.YPos[0] - 1;
		} else if(Angle == 180) {
			this.XPos[0] = this.XPos[0] - 1;
		} else if(Angle == 270) {
			this.YPos[0] = this.YPos[0] + 1;
		} else {
			while(true) {
				System.out.println("ANGLE ERROR");
			}
		}
		if(GoodToGo()) {
		falseiPos();
		for(int i=this.Size;i>0;i--) {
			int ax = this.XPos[i-1];
			int by = this.YPos[i-1];
			int ii = xytoi(ax,by);
			//System.out.println("For "+ax+", "+by+"    i is "+ii);
			this.iPos[ii] = true;
		}
		int ax = this.AppleX;
		int by = this.AppleY;
		int ii = xytoi(ax,by);
		this.iPos[ii] = true;
		return true;
		} else {
			return false;
		}
	}
	public void ResetSnake() {
		this.HasApple = false;
		this.AppleX = 0;
		this.AppleY = 0;
		falseiPos();
		for(int i=0;i<dim2;i++) {
			this.XPos[i] = 0;
			this.YPos[i] = 0;
		}
		
		this.XPos[0] = 2;
		this.YPos[0] = 3;
		this.iPos[xytoi(2,3)] = true;
		
		this.XPos[1] = 1;
		this.YPos[1] = 3;
		this.iPos[xytoi(1,3)] = true;
		
		this.Size = 2;
		this.NextOri = 0;
		this.Ori = 0;
		
		this.maxsteps = 4*this.dim + 4*this.Size;
		this.cursteps = 0;
		
		if(this.use>4999) {
			this.use = 0;
			this.FT = true;
			
			this.AppleSetX = new int[2080];
			this.AppleSetY = new int[2080];
			this.AppleSeti = new int[2080];
			this.nextindex = 0;
			
			for(int i=0;i<2080;i++) {
				this.AppleSetX[i] = (int) (Math.random()*SnakeMain.dim);
				this.AppleSetY[i] = (int) (Math.random()*SnakeMain.dim);
				this.AppleSeti[i] = xytoi(this.AppleSetX[i],this.AppleSetY[i]);
			}
		} else {
			this.use++;
			this.FT = false;
			this.nextindex = 0;
		}
		
		AddApple();
	}
	public void AddApple() {
		
		this.AppleX = this.AppleSetX[this.nextindex];
		this.AppleY = this.AppleSetY[this.nextindex];
		this.Applei = this.AppleSeti[this.nextindex];
		nextindex++;
		/*
		if(this.FT) {
		int AddX;
		int AddY;
		int it = 0;
		while(true) {
			AddX = (int) (Math.random()*SnakeMain.dim);
			AddY = (int) (Math.random()*SnakeMain.dim);
			boolean GTG = true;
			for(int i=0;i<this.Size-1;i++) {
				if(this.XPos[i] == AddX && this.YPos[i] == AddY) {
					GTG = false;
					break;
				}
			}
			if(GTG) {
				break;
			}
			it++;
			if(it==10000) {
				System.out.println("Add apple sat");
			}
		}
		this.AppleX = AddX;
		this.AppleY = AddY;
		this.Applei = xytoi(AddX,AddY);
		
		this.AppleSetX[this.nextindex] = AddX;
		this.AppleSetY[this.nextindex] = AddY;
		this.AppleSeti[this.nextindex] = this.Applei;
		this.nextindex++;
		} else {
			this.AppleX = this.AppleSetX[this.nextindex];
			this.AppleY = this.AppleSetY[this.nextindex];
			this.Applei = this.AppleSeti[this.nextindex];
			nextindex++;
		}
		*/
		
	}
	public boolean CheckIfApple() {
		if(this.XPos[0] == this.AppleX && this.YPos[0] == this.AppleY) {
			this.cursteps = 0;
			this.maxsteps = 4*this.dim + 4*this.Size;
			return true;
		} else { 
			this.cursteps++;
			return false;
		}
	}
	public boolean GoodToGo() {
		boolean GTG = true;
		if(this.XPos[0]<0 || this.XPos[0]>SnakeMain.dim-1) {
			GTG = false;
		}
		if(this.YPos[0]<0 || this.YPos[0]>SnakeMain.dim-1) {
			GTG = false;
		}
		for(int i=1;i<this.Size-1;i++) {
			if (this.XPos[0] == this.XPos[i] && this.YPos[0] == this.YPos[i]) {
				GTG = false;
				break;
			}
		}
	//	if(this.cursteps>this.maxsteps) {
		//	System.out.println("CS "+this.cursteps+" MS "+this.maxsteps);
		//	GTG = false;
		//}
		if(GTG==false) {
			//System.out.println("GTG is false");
		}
		return GTG;
	}
	
}
