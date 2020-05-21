package SnakePKG;

import java.io.Serializable;

public class Node implements Serializable {
	SNN Parent;
	
	int ID;
	int type;
	
	int PX;
	int PY;
	
	int probPX;
	int probPY;

	int[] AxonIDList;
	double[] AxonWeights;
	double mV;	
	int MP; 
	
	boolean AX = true;
	
	
	public Node(SNN p) {
		this.Parent = p;
	}
	public void Spike() {
		for(int i=0;i<AxonIDList.length;i++) {
			int ID = this.AxonIDList[i];
			Parent.Nodes[ID].mV += AxonWeights[i];
		}	
	}
}
