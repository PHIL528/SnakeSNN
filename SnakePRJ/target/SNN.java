package SnakePKG;

import java.io.Serializable;

public class SNN implements Serializable {
	Node[] Nodes;
	int[] FCat = {0,0,0,0,0};
	int sessions = 0;
	double th = 1.85;
	double dc = 0.15;
	long score;
	int steps;
	public SNN() {
		
	}
}	
