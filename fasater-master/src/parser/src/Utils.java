package sat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
 
public class Utils {
	public static List<Integer> rand = new ArrayList();
	public static List<Integer> rand1 = new ArrayList();
	public static int[] a = new int[Parser.var_num];

	public static void get_rand() {
		rand.clear();     //±‰¡ø≈≈–Ú

		System.out.println("getting random numbers:");
		for (int i = 0; i < Parser.var_num; i++) {
			int c;

			while (true) {
				Random i1 = new Random();
				c = i1.nextInt(Parser.var_num) + 1;

				boolean b1 = false;
				for (Integer integer : rand) {
					if ((int) integer == c)
						b1 = true;
				}

				if (b1)
					continue;
				else 
					break;
			}
			rand.add(new Integer(c));
 
		}

		//System.out.println(rand.toString());
		
		System.out.println("The assignment order is " + rand.toString()); 
	
		
		
		// edit by chengwx
		List data = Parser.data;
//		System.out.println("before:" + data.toString());
		boolean[] changed = new boolean[data.size()];
		for(int i = 1; i <= rand.size(); i++) {
			int temp = rand.get(i - 1);
			if(temp != i) {//need to change num
				for(int j = 0; j < data.size(); j++) {
					int temp1 = Integer.parseInt(data.get(j) + "");
					if(temp1 == i && changed[j] == false) {
						data.set(j, temp);
						changed[j] = true;
					}
					if(temp1 == -i && changed[j] == false) {
						data.set(j, -temp);
						changed[j] = true;
					}
				}
			}
		}
//		System.out.println("after:" + data.toString());
		Parser.data = data;
//		System.out.println("after:" + Parser.data.toString());
		
		
	}

	public static void get_rand1() {
		rand1.clear();                //∏≥÷µ≈≈–Ú
		for (int i = 0; i < Parser.var_num; i++) {
			Random i2 = new Random();
			rand1.add(new Integer(i2.nextInt(2)));
		}
		//System.out.println(rand1.toString());
		System.out.println("The variable assignment is " + rand1.toString());

	}

	public static void main(String[] args) throws IOException {
		Parser.getData("top.v");
		Utils.get_rand();
		Utils.get_rand1();
	}
}
