
package SnakePKG;



import java.io.DataInputStream;

import processing.core.PApplet;




public class SnakeMain {
	
	static int[] HighPush = new int[360];
	static int cores = 1;
	static int speed = 50;
	static int Hz = 5;
	static int waitK = 30;
	static int waitL = 200;
	static int tot;
	static int adj = 0;
	static boolean terminate;
	static int savenum = 0;
	static int thissessions = 0; 
	static Thread[] TList = new Thread[cores];
	static SnakeThread[] SList = new SnakeThread[cores];
	static boolean[] GList = new boolean[cores];
	static SnakeThread MST;
	static boolean plsRender;
	static boolean cont1;
	static boolean cont2;
	static double LR = 0.004;
	static boolean sw = false;
	static int mode = 0;
	static final int dim = 8;
	static SnakeDisplay HoldDisplay; //Processing window/input class
	static Snake HoldSnake;
	static SNN HoldNet;
	static char KeyBuffer;
	static int FailOpen = -1; // 1 for open failure
	public static void main(String[] args) {
	
		SnakeDisplay Display = new SnakeDisplay();
		Thread T = new Thread(Display);  
	    T.start();  
	    
		menu();
		
		
		
	}
	static void menu() {
		while(true) {
			wait(1);
			if(mode==0) {
				
			} else if(mode==1) {
				PlaySnake();
			} else if(mode==11) {
				cont1 = false;
				cont2 = false;
				NetworkMode();
			}
		}
	}
	static void NetworkMode() {
		HoldNet = new SNN();
		try {
			HoldNet = FileNetwork.Open();
		} catch (InterruptedException e) {
		//	e.printStackTrace();
		}
		if( HoldNet == null && FailOpen == 1 ) {
			System.out.println("No preexisting network file, press p to create file");
			wait(1);
			HoldNet = new SNN();
			//System.out.println("KEEP");
			while(true) {
				wait(1);
				if(KeyBuffer=='p') {
					break;
				}
			}
			//System.out.println(HoldNet.th);
			//System.out.println("last b4");
			HoldNet = MakeSNN.Make(HoldNet);
			int[] ubar = new int[5];
			for(int u=0;u<5;u++) {
				ubar[u] = HoldNet.FCat[u];
				
			}
			System.out.println("FCAT "+ubar[0]+" "+ubar[1]+" "+ubar[2]+" "+ubar[3]+" "+ubar[4]);
		}
		
		
		//T.start();
		System.out.println("length is "+HoldNet.Nodes.length);
		for(int i=0;i<cores;i++) {
			double u = (double) i;
			int ta = (int) ((u/cores)*HoldNet.Nodes.length);
			int tb = (int) (((u+1)/cores)*HoldNet.Nodes.length);
			System.out.println("ta "+ta+" tb "+tb);
			GList[i] = false;
			SList[i] = new SnakeThread();
		
			SList[i].a = ta;
			SList[i].b = tb;
			SList[i].id = i;
			if(i==0) {
				MST = SList[i];
			} else {
				TList[i] = new Thread(SList[i]);
				TList[i].start();
			}
		}
		
		
		HoldSnake = new Snake();
		cont1 = true;
		System.out.println("Rendering display, press d to continue");
		
		//System.out.println("cont 2 before gate");
		//System.out.println(cont2);
		while(true) {
			//System.out.println("cont 2 during while");
			//System.out.println(cont2);
			wait(1);
			if(cont2) {
				//System.out.println("CONT 2 is true");
				break;
			}
		}
		
		mode = 12;
		//System.out.println("mode 12");
		cont1 = false;
		cont2 = false;
		boolean TsComplete = true;
		terminate = false;
		
		
		
		
		//keep redoing the game
		savenum = 0;
		thissessions = 0;
		double probT;
		int testid;
		int WID; 
		long savelastscore;
		double savelastW;
		while(true) { 
		//individual game loop
			
			savelastscore = HoldNet.score;
			while(true) {
				int randid = (int) (Math.random()*HoldNet.Nodes.length);
				if(HoldNet.Nodes[randid].type == 2) {
					probT = (Math.random()*800);
					if(probT - 400 < HoldNet.Nodes[randid].probPY) {
						testid = randid;
						WID = (int) (Math.random()*8);
						break;
					} 
				}
			}
			//System.out.println("Testing new weight");
			savelastW = HoldNet.Nodes[testid].AxonWeights[WID];
			sw = !sw;
			if(sw) {
				HoldNet.Nodes[testid].AxonWeights[WID] = savelastW + LR;
			} else {
				HoldNet.Nodes[testid].AxonWeights[WID] = savelastW - LR;
			}
		/*	if(!(speed==5000)) {
			System.out.println(savelastW+" // "+HoldNet.Nodes[testid].AxonWeights[WID]);
			} */
			HoldNet.score = 0;
			for(int i=0;i<HoldNet.Nodes.length;i++) {
				HoldNet.Nodes[i].mV = 0;
			}
			
			
			
			while(true) {
				
				if(speed == 333) {
					plsRender = true;
					wait(waitK*2);
				} else if(speed == 111) {
					plsRender = true;
					wait(waitK);
				}
				//In Hz
				for(int i=0;i<Hz;i++) {
					for(int t=0;t<cores;t++) {
						SnakeMain.GList[t] = true;
					}
					//SnakeMain.GList[2] = true;
				
					MST.run();
					
					while(true) {
						for(int t=1;t<cores;t++) {
							if(GList[t]) {
								//System.out.println("List "+t+" is still true");
								TsComplete = false;	
								break;
							}
						}
						if(TsComplete) {
							break;
						} else {
							TsComplete = true;
						//	System.out.println("GLIST 2 ");
							//System.out.println(GList[2]);
							//System.out.println("WAITING");
						}
					}
					//System.out.println("Threads complete");
					//Threads complete
					if(speed == 5000) {
						
					} else if(speed == 500) {
						plsRender = true;
						wait(waitK);
					} else if(speed == 50){ 
						plsRender = true;
						wait(waitL);
					}
				}
				//Game update
				int windex = 0;
				
				
				int win = HighPush[windex];
			/*	if(!(speed == 5000)) {
					System.out.println(HighPush[0]+" "+HighPush[90]+" "+HighPush[180]+" "+HighPush[270]);
				} */
				if(HighPush[90]>win) {
					windex = 90;
					win = HighPush[90];
				}
				if(HighPush[180]>win) {
					windex = 180;
					win = HighPush[180];
				}
				if(HighPush[270]>win) {
					windex = 270;
					win = HighPush[270];
				}
				//System.out.println("WINDEX "+windex);
				HoldSnake.NextOri = windex;
				HighPush[0] = 0;
				HighPush[90] = 0;
				HighPush[180] = 0;
				HighPush[270] = 0;
				
				
				if(Math.abs( (double) (HoldSnake.Ori-HoldSnake.NextOri)) == 180.0) {
					HoldSnake.Ori = HoldSnake.Ori;
				} else {
					HoldSnake.Ori = HoldSnake.NextOri;
				}
				
				if(HoldSnake.HasApple) {
					HoldSnake.Size++;
					HoldSnake.HasApple = false;
					HoldNet.score+=50;
					//HoldNet.steps++;
				}
				if(HoldSnake.Move()) {
					HoldNet.steps++;
					HoldNet.score++;
					if(HoldSnake.CheckIfApple()) {
						HoldSnake.HasApple = true;
						HoldSnake.AddApple();
					}
				} else {
					break;
				}	
			}
			if(HoldNet.score > savelastscore) {
				adj++;
			} else if (HoldNet.score <= savelastscore) {
				HoldNet.Nodes[testid].AxonWeights[WID] = savelastW;
			}
			
			
			
			HoldNet.sessions++;
			thissessions++;
			tot++;
			//tot+=HoldNet.steps;
			if(savenum>=200000) {
				System.out.println("i loop");
				wait(1);
				try {
					FileNetwork.Save(HoldNet);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("WARNING FAILED SAVE");
				}
				System.out.println("Sessions "+HoldNet.sessions+" and thissessions "+thissessions);
				int inhib = 0;
				int exttt = 0;
				for(int i=0;i<HoldNet.Nodes.length;i++) {
					for(int u=0;u<HoldNet.Nodes[i].AxonWeights.length;u++) {
						if(HoldNet.Nodes[i].AxonWeights[u]<0) {
							inhib++;
						} else {
							exttt++;
						}
					}
				}
				System.out.println("AVERAGE STEPS: "+((double)(HoldNet.steps))/((double)(tot)));
				HoldNet.steps = 0;
				System.out.println("ADJUSTMENTS : "+adj);
				adj = 0;
				System.out.println(inhib + " inhibitory connections, "+exttt+" ex connections");
				savenum = 0;
				tot = 0;
				
			}
			if(terminate) {
				System.out.println("Terminating "+thissessions);
				break;
			}
			//System.out.println("Reset "+savenum);
			savenum++;
			HoldSnake.ResetSnake();
		}
		mode = 0;
	}
	
	static void PlaySnake() {
		HoldSnake = new Snake();
		mode = 2;
		
		//while(true) {
		
		while(true) {
			wait(400);
			HoldSnake.Ori = HoldSnake.NextOri;
			if(HoldSnake.HasApple) {
				HoldSnake.Size++;
				HoldSnake.HasApple = false;
			}
			if(HoldSnake.Move()) {
				if(HoldSnake.CheckIfApple()) {
					HoldSnake.HasApple = true;
					HoldSnake.AddApple();
				}
			} else {
				break;
			}
		}
		
		mode = 0;
		/*wait(1000);
		HoldSnake.ResetSnake();
		
		}*/
		
		
	}
	public static void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }	
}
