package SnakePKG;

public class SnakeThread implements Runnable {

	int a;
	int b;
	int id;

	int C = 5;
	public void run() {
		
	//System.out.println(" a="+this.a+" b="+this.b+" id="+this.id);
		//System.out.println(SnakeMain.GList[this.id]);
		int u;
		while(true) {
			/*if(this.id==C) {
			System.out.println("Task "+this.id+" is starting");
			System.out.println(SnakeMain.GList[this.id]);
			}*/
		//	System.out.println(SnakeMain.GList[2]);
		//	System.out.println(thisss.id);
		//	System.out.println(" a="+this.a+" b="+this.b+" id="+this.id);
			//System.out.println(SnakeMain.GList[this.id]);
		//	System.out.println(SnakeMain.GList[this.id]);
			for(int i=0;i<1;i++) {
				u = (int) Math.random();
			}
			if(SnakeMain.GList[this.id]) {
				//System.out.println("Task "+this.id+" is ACTUALLY starting");
				for(int i=this.a;i<this.b;i++) {
				//	if(this.id == C) {
					//System.out.println(i);
					//}
					if(SnakeMain.HoldNet.Nodes[i].type == 1) {
						if(SnakeMain.HoldSnake.iPos[i]) {
							SnakeMain.HoldNet.Nodes[i].AX = true;
							if(i==SnakeMain.HoldSnake.Applei) {
								SnakeMain.HoldNet.Nodes[i].Spike();
								//SnakeMain.HoldNet.Nodes[i].Spike();
							}
						} else {
							SnakeMain.HoldNet.Nodes[i].AX = false;
						}	
						//fliping the logic order to save cpu
					} else if(SnakeMain.HoldNet.Nodes[i].mV > SnakeMain.HoldNet.th) {
						SnakeMain.HoldNet.Nodes[i].AX = true;
						SnakeMain.HoldNet.Nodes[i].mV = 0;
						if(SnakeMain.HoldNet.Nodes[i].type == 2) {
							SnakeMain.HoldNet.Nodes[i].Spike();
						} else {	
							SnakeMain.HighPush[SnakeMain.HoldNet.Nodes[i].MP]++;
							//System.out.println(" PUSH ORI "+SnakeMain.HoldNet.Nodes[i].MP);
						}
					} else {	
						SnakeMain.HoldNet.Nodes[i].AX = false;
						SnakeMain.HoldNet.Nodes[i].mV = SnakeMain.HoldNet.Nodes[i].mV/1.2;
					}
				}
		//		if(this.id==C) {
			//		System.out.println("WE ARE OUT");
				//}
				if(this.id == 0) {
				//	System.out.println("TASK 0 IS RETURNING");
					SnakeMain.GList[this.id] = false;
					return;
				} else {
					//System.out.println("Task "+this.id+" is complete");
					SnakeMain.GList[this.id] = false;
				}
			}
		
		}
	}
}