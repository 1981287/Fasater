package sat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Reasoner {

	public static PrintWriter pwn;
	public static int time1=1;
	//PrintWriter pw2=new PrintWriter(new OutputStreamWriter(new FileOutputStream("fsm0.v", true), "utf-8"), true);
	 
	public static void writeStart1(int time1) throws UnsupportedEncodingException, FileNotFoundException{
		pwn=new PrintWriter(new OutputStreamWriter(new FileOutputStream("reasoner.v", true), "utf-8"), true);
		System.out.println("generating reasoner...");
//		pwn.print("module reasoner(");   
//		
//		pwn.print("v_p1,v_n1");
//		for (int i=2;i<=Parser.var_num;i++)
//		{
//		pwn.print(",v_p"+i); 
//		pwn.print(",v_n"+i);
//		}		
//		pwn.print(",O_P1,O_N1");
//		for (int i=2;i<=Parser.var_num;i++)
//		{
//		pwn.print(",O_P"+i); 
//		pwn.print(",O_N"+i);
//		}
		
//		pwn.print(",l_co1");
//		for (int i=2;i<=Parser.var_num;i++)
//		{
//		pwn.print(",l_co"+i); 
//		}	
//		
//		pwn.println(",conflict);");	
////		pwn.println(");");
//		pwn.println("");
//		pwn.print("input v_p1,v_n1");		
//		for (int i=2;i<=Parser.var_num;i++)
//		{
//		pwn.print(",v_p"+i);
//		pwn.print(",v_n"+i);
//		}			
//		pwn.println(";");		
//		pwn.println("");
//		pwn.print("output O_P1,O_N1");      
//		for (int i=2;i<=Parser.var_num;i++)
//		{
//		pwn.print(",O_P"+i); 
//		pwn.print(",O_N"+i);
//		}		
//		pwn.println(",conflict;");
//		pwn.println("");
		/*
		 * pwn.println(";");
		 * 
		 * pwn.print("output l_co1"); for (int i=2;i<=Parser.var_num;i++) {
		 * pwn.print(",l_co"+i); } pwn.println(";");
		 */
	}
	 
	  public static void writeEnd1()throws UnsupportedEncodingException, FileNotFoundException{	

//			for (int i=1;i<=Parser.var_num;i++)
//			{
//			pwn.println("wire l_co"+i+";");
//			pwn.println("and AND"+i+"(l_co"+i+",O_P"+i+",O_N"+i+");");
//			}				  	  
//		    pwn.println("wire conflict;");
//		    pwn.print("or OR_CO(conflict");
//		    for(int i=1;i<=Parser.var_num;i++)
//			{
//			pwn.print(",l_co"+i);
//			}
//			pwn.println(");");
//			pwn.println("endmodule");
			System.out.println("reasoner generated");
			System.out.println("   ");
		}
	
}
