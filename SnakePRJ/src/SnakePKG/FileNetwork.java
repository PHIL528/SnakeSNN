
package SnakePKG;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * DataInputStream in = new DataInputStream(System.in);
        int intnumber=0;
        float floatnumber=0.0f;
        try {
            System.out.println("enter an integer: ");
            intnumber = Integer.parseInt(in.readLine());
            System.out.println("enter a float number: ");
            floatnumber = Float.valueOf(in.readLine()).floatValue();
        } catch(Exception e){
        	System.out.println("DIDNT WORK");
        }
 */

/*
 * SNN SU = new SNN();
		System.out.println("P1 ");
		SU.Nodes[0].Spike();
		try {
			SU = FileNetwork.Open();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("P2 ");
		SU.Nodes[0].Spike();
		
		/*
		SNN S = new SNN();
		S.Nodes[1].ID = 250;
		try {
			FileNetwork.Save(S);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		SnakeDisplay Display = new SnakeDisplay();
		HoldDisplay = Display;
		Thread DisplayThread = new Thread(Display);
		DisplayThread.start();
		
		
SNN SU = new SNN();
System.out.println(SU.u);
try {
	SU = FileNetwork.Open();
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
System.out.println(SU.u);
*/

//menu();
/*
SNN S = new SNN();
S.u = 4;
try {
	FileNetwork.Save(S);
} catch (InterruptedException e) {
	e.printStackTrace();
}
*/

public class FileNetwork {
	static String fn = "SaveSNN.txt";
	
	public static void Save(SNN sNSO) throws InterruptedException {
		boolean ERR = false;
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fn));
			os.writeObject(sNSO);
			os.close();
			System.out.println("SAVED");
			return;
		} catch (FileNotFoundException e) {
			System.out.println("FILE NOT FOUND EX");
			e.printStackTrace();
			ERR = true;
		} catch (IOException e) {
			System.out.println("IO ERROR");
			e.printStackTrace();
			ERR = true;
		}
		if(ERR == true) {
			//while(true) {
				System.out.println("FAILED SAVE");
			//}
		}
		return;
	}
	public static SNN Open() throws InterruptedException {
		boolean ERR = false;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(fn));
			SNN nNSO = (SNN) is.readObject();
			is.close();
			System.out.println("OPENED");
			return(nNSO);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
			ERR = true;
			System.out.println("FILE NOT FOUND EX");
		} catch (IOException e) {
			e.printStackTrace();
			ERR = true;
			System.out.println("IO ERROR");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			ERR = true;
			System.out.println("CLASS NOT FOUND");
		}
		if(ERR == true) {
			SnakeMain.FailOpen = 1;
			//while(true) {
			//	System.out.println("FAILED OPEN");
			//}
		}
		return null;
	}
}
