package sat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Reason {

	public static void write() throws UnsupportedEncodingException, FileNotFoundException {
		PrintWriter pw = Reasoner.pwn;
		System.out.println("starting reason...");

		for (int i = 1; i <= Parser.var_num; i++) {

			int j = 0;
			int k = 0;
			int start = 0;
			int end = 0;
			int sum = 0;
			
			for (int l = 0; l < Parser.data.size(); l++) {
				Integer it = (Integer) Parser.data.get(l);
				if (it.intValue() == 0 && sum == 0) {
					start = l;
					sum++;
				} else if (it.intValue() == 0 && sum == 1) {
					end = l;
					sum++;
				}
				if (sum == 2) {

					List clause = Parser.data.subList(start, end + 1);
					sum = 1;
					start = end;

					for (Object object : clause) {
						Integer literal = (Integer) object;
						boolean b1 = false;
						boolean b2 = false;
						boolean b3 = false;
						if (literal.intValue() == i) {
							b1 = true;
							b2 = true;
							for (Object object1 : clause) {
								Integer mid = (Integer) object1;
								if (mid.intValue() == -i)
									b1 = false;
							}
						}

						if (literal.intValue() == -i) {
							b1 = true;
							b3 = true;
							for (Object object2 : clause) {
								Integer mid = (Integer) object2;
								if (mid.intValue() == i)
									b3 = false;
							}
						}
 
						if (b1) {
							if (b2) {
								pw.print("int " + "O_P" + i + "_" + j);
								pw.println(" = 0;");
								pw.print("O_P" + i + "_" + j );
								pw.print(" = ");
								j++;
								int count1=0;
								int leng3 = clause.size();
								//pw.println("clause="+clause);
								if (leng3 == 3)
									pw.print("1");
								for (Object object3 : clause) {
									Integer mid = (Integer) object3;
									int leng1 = clause.size();
									//pw.println("leng1="+leng1);

									if (mid == i || mid == -i || mid == 0)
										
										continue;
										
									else if (mid > 0 && leng1 > 3 )
										pw.print("v_n" + mid);
										
									else if (mid < 0 && leng1 > 3 )
										pw.print("v_p" + (-mid)); 
//									else
//										pw.print(" 1 ");
//									else if (leng1 == 3)
//										pw.print(" 1 ");
									 
									 //pw.println("leng="+leng);
									 
									 if (count1 < (leng1-4)) {
	                                	pw.print(" && ");
									    count1+=1;
									    //pw.println("count="+count);
									    }
									 //pw.println("clause="+clause);
									 //pw.println("mid="+mid);
								}
								

								pw.println(";");
								
//								 int leng = clause.size();
//								 for (int s=0; s<leng ;s++)
//                                 	pw.print("&&");


//						else if (count < (leng-5)) {
//                        	pw.print("&&");
//						    	count = count + 1;
//								pw.println(";");
//								pw.println("clause="+clause);
//								pw.println("clause="+clause.size());
								
								//pw.println("leng="+leng);
								
							
								
							} else if (b3) {
								pw.print("int " + "O_N" + i + "_" + k);
								pw.println(" = 0;");
								pw.print("O_N" + i + "_" + k);
								pw.print(" = ");
								k++;
								int count2=0;
								for (Object object4 : clause) {
									Integer mid = (Integer) object4;
									int leng2 = clause.size();
									//pw.println("leng2="+leng2);
									if (mid == i || mid == -i || mid == 0)
										continue;
									else if (mid > 0 && leng2 > 3) 
										pw.print("v_n" + mid);
									else if (mid < 0 && leng2 > 3)
										pw.print("v_p" + (-mid));
									else if (mid < 0 && leng2 < 3)
										pw.print(" 0 ");
									else if (mid > 0 && leng2 < 3)
										pw.print(" 1 ");
 
								    if (count2 < (leng2-4)) {
								    	pw.print(" && ");
								    	count2+=1;
								    	}
								 
								}
									//pw.print("mid="+mid);	
									//pw.print("&&");	
								
								pw.println(";");
								
							}
						}

					}

				} 

			}
			pw.println("");
			if (j > 1) {
				pw.print("int "+"O_P" + i);
				pw.print(" = ");
				int i2 = 0;
				pw.print("O_P" + i + "_" + i2);
				for (i2 = 0; i2 < j-1; i2++) 
				{ 
				    //pw.print(" || O_P" + i + "_" + (i2+1));
				    pw.print(" || O_P" + i + "_" + (i2+1));
				}
				pw.println(";");
		
			} 
			else if (j == 1) {
				pw.print("int " + "O_P" + i);
				pw.print(" = ");
				for (int i2 = 0; i2 < j; i2++) 
				{
				    pw.print("O_P" + i + "_" + i2);
				}
				pw.println(";");
			}
			else {
				pw.println("int " + "O_P" + i + " = 0;");
			}
			
			if (k > 1) {
				pw.print("int " + "O_N" + i);
				pw.print(" = ");
				int i3 = 0;
				pw.print("O_N" + i + "_" + i3);
				for (i3 = 0; i3 < k-1; i3++) 
				{	
					//pw.print("O_N" + i + "_" + i3);
				    pw.print(" || O_N" + i + "_" + (i3+1));
				}
				pw.println(";");
				
			} 
			else if (k == 1) {
				pw.print("int " + "O_N" + i);
				pw.print(" = ");
				for (int i3 = 0; i3 < k; i3++) 
				{
					pw.print("O_N" + i + "_" + i3);
				}
				pw.println(";");
			}
			else {
				pw.println("int " + "O_N" + i + " = 0;");
			}
			pw.println("");
		}
		
		
		//System.out.println("The clause is " + intValue);
		System.out.println("reasoning finished");
		//System.out.println("   ");
	}

	public static void main(String[] args) throws IOException {
		FileOutputStream o = new FileOutputStream("reasoner.v", true);
		Parser.test();

	}

}
