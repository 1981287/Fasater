//package sat;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Iterator; 
//import java.util.List;
//
//public class Parser {
//	public static int var_num;
//	public static int clause_num;
//	public static int max_length;
//	public static int lines;
//	public static List data = new ArrayList(1);
//
//	public static void getData(String s) throws IOException {
//		System.out.println("starting parser...");
//		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
//		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
//
//		String x;
//		while ((x = br1.readLine()) != null) {      //���ж�ȡ
//			if (x.contains("c "))
//				continue;        //C��ͷ��Ϊע��
//			if (!x.contains("p") && !x.contains("P") || !x.contains("cnf") && !x.contains("CNF"))
//				continue;    //P CNF ���������Ӿ���
//			String[] strArray = x.split("\\s+");
//			var_num = Integer.parseInt(strArray[2]);
//			clause_num = Integer.parseInt(strArray[3]);
//			//var_num = 10;
//			System.out.println("The var_num is " + var_num);      //������������Ӿ���
//			System.out.println("The clause_num is " + clause_num);
//			break;
//		}
//		br1.close();
// 
//		data.add(new Integer(0));
//		while ((x = br2.readLine()) != null) {
//
//			if (x.contains("c "))
//				continue;
//			if (!x.contains("p") && !x.contains("P") || !x.contains("cnf") && !x.contains("CNF"))
//				continue;
//			while ((x = br2.readLine()) != null) {
//
//				String[] strArray = x.split("\\s+");
//
//				for (int i = 0; i < strArray.length; i++) {
//					if (!strArray[i].equals(""))
//						data.add(new Integer(strArray[i]));
//				}
// 
//			}
//
//		}
//		br2.close();
//		System.out.println("The clause is " + data);
//		System.out.println("parsing finished");
//		System.out.println("   ");
//	}
//
//	public static void test() throws IOException {
//		Parser.getData("test");
//	}
//
//	public static void main(String[] args) throws IOException {
//
//		Parser.getData(args[0]);
//	}
//}

package sat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator; 
import java.util.List;

public class Parser {
	public static int var_num;
	public static int clause_num;
	public static int max_length;
	public static int lines;
	public static List data = new ArrayList(1);

	public static void getData(String s) throws IOException {
		System.out.println("starting parser...");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(s)));

		String x;
		while ((x = br1.readLine()) != null) {      //���ж�ȡ
			if (x.contains("c "))
				continue;        //C��ͷ��Ϊע��
			if (!x.contains("p") && !x.contains("P") || !x.contains("cnf") && !x.contains("CNF"))
				continue;    //P CNF ���������Ӿ���
			String[] strArray = x.split("\\s+");
			var_num = Integer.parseInt(strArray[2]);
			clause_num = Integer.parseInt(strArray[3]);
			//var_num = 200;
			//clause_num = Integer.parseInt(strArray[3]);
			System.out.println("The var_num is " + var_num);      //��������������Ӿ���� ����Ӿ�����
			System.out.println("The clause_num is " + clause_num);
			break;
		}
		br1.close();
 
		data.add(new Integer(0));
		while ((x = br2.readLine()) != null) {

			if (x.contains("c "))
				continue;
			if (!x.contains("p") && !x.contains("P") || !x.contains("cnf") && !x.contains("CNF"))
				continue;
			while ((x = br2.readLine()) != null) {

				String[] strArray = x.split("\\s+");

				for (int i = 0; i < strArray.length; i++) {
					if (!strArray[i].equals(""))
						data.add(new Integer(strArray[i]));
				}
 
			}

		}
		br2.close();
		System.out.println("The primary clause is " + data);      //����Ӿ�����
		
		
		System.out.println("parsing finished");
		System.out.println("   ");
	}

	public static void test() throws IOException {
		Parser.getData("test");
	}

	public static void main(String[] args) throws IOException {

		Parser.getData(args[0]);
	}
}

