package sat;

import java.io.IOException;
 
public class Top {

	public static void main(String[] args) throws IOException {
		Parser.getData(args[0]);
		for (int i = 0; i < Connect.times; i++) {
			Utils.get_rand();
			Utils.get_rand1();
			System.out.println("The changed clause is " + Parser.data.toString());
			Connect.writeStart(i);
		    Connect.writeEnd();
		  }
		for(int i = 0; i < Reasoner.time1; i++) {
			//Reasoner.writeStart1(i);
			//Reason.write();
		    //Reasoner.writeEnd1();	
		   }	
			/*
			 * for(int i = 0; i < Conflict_detector.time1; i++) {
			 * Conflict_detector.writeStart1(i); Conflict_detector.writeEnd1(); }
			 */
		
		Connect.writeTop();
		//Module.write();
	}
 
}
