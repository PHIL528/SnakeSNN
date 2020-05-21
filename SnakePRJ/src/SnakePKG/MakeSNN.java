package SnakePKG;

public class MakeSNN {
	public static SNN Make(SNN SO) {
		
		int inc = 256;
		//int dinc = 64+inc+4;
		//based on 320 x length
		SO.Nodes = new Node[64+inc+4];
		System.out.println(SO.th);
		System.out.println("length "+SO.Nodes.length);
		for(int i=0;i<64;i++) {
			SO.Nodes[i] = new Node(SO);
			SO.Nodes[i].type = 1;
			SO.Nodes[i].ID = i;
			SO.Nodes[i].probPX = 320/2;
			SO.Nodes[i].probPY = 0;
			SO.Nodes[i].PX = i*5;
			SO.Nodes[i].PY = -5;
			SO.Nodes[i].mV = 0.0;
			SO.Nodes[i].AxonIDList = new int[8];
			SO.Nodes[i].AxonWeights = new double[8];
		}
		System.out.println("mv "+SO.Nodes[0].mV);
		SNN N = SO;
		for(int i=64;i<inc+64;i++) {
			N.Nodes[i] = new Node(N);
			N.Nodes[i].type = 2;
			N.Nodes[i].ID = i;
			int px = (int) (Math.random()*320);
			int py = (int) (Math.random()*800);
			SO.Nodes[i].probPX = px;
			SO.Nodes[i].probPY = py;
			SO.Nodes[i].PX = px;
			SO.Nodes[i].PY = py;
			SO.Nodes[i].mV = 0.0;
			SO.Nodes[i].AxonIDList = new int[8];
			SO.Nodes[i].AxonWeights = new double[8];
		}
		int u = 0;
		int du = 0;
		for(int i=inc+64;i<4+inc+64;i++) {
			N.Nodes[i] = new Node(N);
			N.Nodes[i].type = 3;
			N.Nodes[i].ID = i;
			int px = u;
			int py = 805;
			N.Nodes[i].MP = du;
			du += 90;
			u += 320/3;
			SO.Nodes[i].probPX = px;
			SO.Nodes[i].probPY = py;
			SO.Nodes[i].PX = px;
			SO.Nodes[i].PY = py;
			SO.Nodes[i].mV = 0.0;
			SO.Nodes[i].AxonIDList = new int[16];
			SO.Nodes[i].AxonWeights = new double[16];
		}
		//Now that nodes are added, make connections;
		System.out.println("BEFORE MAKE CONNECTIONS");
		for(int i=0;i<inc+64;i++) {
			for(int a=0;a<8;a++) {
				//System.out.println("FIND R");
				int rID = FindReceptor(N,N.Nodes[i]);
				//System.out.println("i="+i+"   a = "+a+" and rID "+rID);
				N.Nodes[i].AxonIDList[a] = rID;
				if(i<64) {
					N.Nodes[i].AxonWeights[a] = 1.0;
				} else {
					
					if(Math.random() < 0.5) {
						N.Nodes[i].AxonWeights[a] = Math.random()*1;
					} else {
						N.Nodes[i].AxonWeights[a] = -1*Math.random()*1;
					}
					if (rID>=64+256) {
						N.Nodes[i].AxonWeights[a] = 1;
					}
				}
			}
		}
		System.out.println("POST MAKE CONNECTIONS");
		return SO;
	}
	
	public static int FindReceptor(SNN SO, Node Node1) {
		while(true) {
			int gID = (int) (Math.random()*SO.Nodes.length);
		//	System.out.println(gID);
			if(gID == Node1.ID || gID<64) {
			} else {
				Node Node2 = SO.Nodes[gID];
				double dy = (double) (Node2.probPY - Node1.probPY);
				double dx = (double) (Node2.probPX - Node1.probPX);
				double mr1 = Math.random();
				double mr2 = Math.random();
				if(100 < dy && dy <= 250) {
					if(mr1 < 1) {
						if(mr2 > dx/100) {
							SO.FCat[0]++;
							return gID;
						}
					}
				} else if(250 < dy && dy <= 400 ) {
					if(mr1 < 0.222) {
						if(mr2 > dx/100) {
							SO.FCat[1]++;
							return gID;
						}
					}
				} else if(-250 <= dy && dy < 100) {
					if(mr1 < dx/100) {
						if(mr2 < 0.222) {
							SO.FCat[2]++;
							return gID;
						}
					}
				} else if(-400 <= dy && dy < -250) {
					if(mr1 < dx/100) {
						if(mr2 < 0.111) {
							SO.FCat[3]++;
							return gID;
						}
					}
				} else if(mr1 < 0.055) {
					SO.FCat[4]++;
					return gID;
				}
			}
		}
		
	}
}