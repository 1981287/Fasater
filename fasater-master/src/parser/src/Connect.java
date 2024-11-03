package sat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Connect {
	
	public static PrintWriter pw;
	public static int times=1;
	//public static int number = 104;
	public static int number_wr = 23;
	public static int number_rd = 21;
	
	public static int number_wr_use = 10;
	public static int number_rd_use = 3;
	
	public static void writeStart(int time) throws UnsupportedEncodingException, FileNotFoundException{
/*		pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("SAT_1024_node.v", true), "utf-8"), true);
		System.out.println("starting write connectors... "+time+" time");
		System.out.println("   ");
		pw.println("module SAT_1024_node(");    //模块定义
				
		
		pw.print("input slv_reg0");
		for (int i=1;i<=number_wr;i++)
		{
		pw.print(",slv_reg"+i);
		}
		pw.println(",");		

		pw.println("input clk, ");
		
		pw.print("output rd_data0");
		for (int i=1;i<=number_rd;i++)
		{
		pw.print(",rd_data"+i);
		}
		pw.println("  ");
		pw.println(");");	
		pw.println("  ");

		
		pw.print("wire v_p1,v_n1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",v_p"+i);
		pw.print(",v_n"+i);
		}
		pw.println(";");
		
		pw.print("reg O_P1,O_N1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",O_P"+i);
		pw.print(",O_N"+i);
		}
		pw.println(";");
		pw.println(" 	");
		//pw.println(" 	");
		
		pw.print("wire t1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",t"+i);
		}
		pw.println(";");
		
		
		pw.println("wire b1;");
		pw.print("reg b2");
		for (int i=3;i<=Parser.var_num+1;i++)
		{
		pw.print(",b"+i);
		}
		pw.println(";");

		
		pw.print("wire c2");
		for (int i=3;i<=Parser.var_num;i++)
		{
		pw.print(",c"+i);
		}
		pw.println(";");
		
		pw.print("reg x1");
		for (int i=2;i<=(Parser.var_num-1);i++)
		{
		pw.print(",x"+i);
		}
		pw.println(";");
		pw.println(" 	");
		
		
		pw.print("wire a1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",a"+i);
		}
		pw.println(";");
		
		pw.print("wire back,back1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",back"+i);
		}
		pw.println(";");
		pw.println(" 	");
		
		
		pw.print("reg control_1 = 0");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(",control_"+i);
		pw.print(" = 0");
		}
		pw.println(";");
		pw.println(" 	");


		pw.println("reg conflict = 1'b0 ;"); 
		pw.println("reg start = 1'b0 ;");
		pw.println("reg sat = 1'b0;");		
		pw.println("reg unsat = 1'b0;");	
		pw.println("reg [9:0] sign;");
		pw.println("reg [9:0] sign_b;");
		pw.println("");
		
		pw.print("assign back = back1");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(" | back"+i);
		}
		pw.println(";");
		pw.println(" 	");	
		
		pw.println("always@(posedge t"+Parser.var_num+")");			
		pw.println(" begin	");	
		pw.println("    if(t"+Parser.var_num+" == 1'b1)");
		pw.println(" 	  sat = 1'b1;");	
		pw.println(" end	");			
		pw.println(" 	");		
		pw.println("always@(posedge b1)");			
		pw.println(" begin	");	
		pw.println("     if(b1 == 1'b1)");
		pw.println(" 	   unsat = 1'b1;");			
		pw.println(" end	");	
		pw.println(" 	");	

		
		
	    pw.println("(*DONT_TOUCH=\"yes\"*) var_"+ Utils.rand1.get(0) +" variable"+0+"(.a(a1),.control(control_1),.backtrack(back1),.in_top(start),.in_bottom(b2"+"),.out_top(t1)"+",.out_bottom(b1),.conflict(conflict),.vimp_p(O_P"+1+"),.vimp_n(O_N"+1+"),.vout_p(v_p"+1+"),.vout_n(v_n"+1+"));");
		for (int i=2;i<=Parser.var_num-1;i++)
		{
			int i1=i-1;
			int i2=i+1;
		pw.println("(*DONT_TOUCH=\"yes\"*) var_"+Utils.rand1.get(i-1)+" variable"+(i-1)+"(.a(a"+i+"),.control(control_"+i+"),.backtrack(back"+i+"),.in_top(x"+i1+"),.in_bottom(b"+i2+"),.out_top(t"+i+"),.out_bottom(c"+i+"),.conflict(conflict),.vimp_p(O_P"+(i)+"),.vimp_n(O_N"+(i)+"),.vout_p(v_p"+(i)+"),.vout_n(v_n"+(i)+"));");

		}
		int i5=Parser.var_num-1;
		int i4=Parser.var_num;
		int i6=Parser.var_num+1;
		pw.println("(*DONT_TOUCH=\"yes\"*) var_"+Utils.rand1.get(Parser.var_num-1)+" variable"+(Parser.var_num-1)+"(.a(a"+i4+"),.control(control_"+i4+"),.backtrack(back"+i4+"),.in_top(x"+i5+"),.in_bottom(b"+i6+"),.out_top(t"+i4+"),.out_bottom(c"+i4+"),.conflict(conflict),.vimp_p(O_P"+(Parser.var_num)+"),.vimp_n(O_N"+(Parser.var_num)+"),.vout_p(v_p"+(Parser.var_num)+"),.vout_n(v_n"+(Parser.var_num)+"));");

		
		pw.println(" 	");
		pw.println("always@ (*)\r\n" + 
				"begin\r\n" + 
				"  case (sign)");
		for (int q=1;q<=Parser.var_num-1;q++) {  
		String sign = Integer.toBinaryString(q);	
		pw.print("    10'b"+sign);
		pw.print(" : x"+q);
		pw.print(" = t"+q);
		pw.println(";");
		}

		pw.println("    default:;\r\n" + 
				"  endcase\r\n" + 
				"end ");
		pw.println(" 	");
		
		/////////////////////////////////////////////////////////
		pw.println("always@ (*)\r\n" + 
				"begin\r\n" + 
				"  case (sign_b)");
		int number_c;
		for (int q=1;q<=Parser.var_num;q++) {  
		String sign_b = Integer.toBinaryString(q);	
		pw.print("    10'b"+sign_b);
		pw.print(": ");
		pw.print("begin ");
		pw.print("b"+(q+1));
		pw.print(" = 0;");
		number_c = q;
		for (int p=2;p<=number_c;p++) {
			pw.print("b"+p);
			pw.print(" = c"+p);	
			pw.print(";");
		}
		pw.println(" end");
		}
        /////////////////////////////////////////////////////////
		
		
		pw.print("    default:;\r\n" + 
				"  endcase\r\n" + 
				"end \r\n" + 
				"\r\n" + 
				"always@(posedge clk)\r\n" + 
				"begin\r\n" + 
				"  if(slv_reg0[0] == 1 && slv_reg6[20] == 1) begin\r\n" + 
				"		start <= slv_reg0[0];\r\n" + 
				"		conflict <= slv_reg0[1];\r\n" + 
				"		sign <= {slv_reg0[11],slv_reg0[10],slv_reg0[9],slv_reg0[8],slv_reg0[7],slv_reg0[6],slv_reg0[5],slv_reg0[4],slv_reg0[3],slv_reg0[2]};\r\n" + 
				"");	
		
		
		pw.println("		sign_b <= {slv_reg0[21],slv_reg0[20],slv_reg0[19],slv_reg0[18],slv_reg0[17],slv_reg0[16],slv_reg0[15],slv_reg0[14],slv_reg0[13],slv_reg0[12]};");
		
		
		int f;
		int g=1;
		for (int i=1;i<=number_wr-1;i++)
		{
			pw.println(" 	");
			for (f=0,g=g;f<=29 && g<=Parser.var_num;f=f+3,g++) 
			{
			pw.print("		O_P"+g);
			pw.print("<=");
			pw.print("slv_reg"+(i));
			pw.print("["+f);
			pw.print("];");
			
			pw.print("O_N"+g);
			pw.print("<=");
			pw.print("slv_reg"+(i));
			pw.print("["+(f+1));
			pw.print("];");
			
			pw.print("control_"+g);
			pw.print("<=");
			pw.print("slv_reg"+(i));
			pw.print("["+(f+2));
			pw.print("];");
			pw.println(" 	");
			}
		}
		
		pw.println("	end\r\n" + 
				"      \r\n" + 
				"	else begin\r\n" + 
				"		start <= start;\r\n" + 
				"		conflict <= conflict;\r\n" + 
				"		sign_b <= sign_b;\r\n" + 
				"		sign <= sign;");
		
		pw.println(" 	");
		for (g=1;g<=Parser.var_num;g++)
		{
			pw.print("		O_P"+g);
			pw.print("<=");
			pw.print("O_P"+g);
			pw.print(";");
			
			pw.print("O_N"+g);
			pw.print("<=");
			pw.print("O_N"+g);
			pw.print(";");
			
			pw.print("control_"+g);
			pw.print("<=");
			pw.print("control_"+g);
			pw.println(";");
		}
		pw.println("	end");
		pw.println("end");
		pw.println("endmodule");
		
*/		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////驱动程序
		pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("cnf_drive.c", true), "utf-8"), true);
		
		//pw.println("////////////////////////////////////////drive");
		pw.println("#include <linux/types.h>\r\n" + 
				"#include <linux/kernel.h>\r\n" + 
				"#include <linux/delay.h>\r\n" + 
				"#include <linux/init.h>\r\n" + 
				"#include <linux/ide.h>\r\n" + 
				"#include <linux/module.h>\r\n" + 
				"#include <linux/errno.h>\r\n" + 
				"#include <linux/gpio.h>\r\n" + 
				"#include <linux/cdev.h>\r\n" + 
				"#include <linux/device.h>\r\n" + 
				"#include <linux/of_gpio.h>\r\n" + 
				"#include <linux/semaphore.h>\r\n" + 
				"#include <linux/timer.h>\r\n" + 
				"#include <linux/irq.h>\r\n" + 
				"#include <linux/wait.h>\r\n" + 
				"#include <linux/poll.h>\r\n" + 
				"#include <linux/fs.h>\r\n" + 
				"#include <linux/fcntl.h>\r\n" + 
				"#include <linux/platform_device.h>\r\n" + 
				"#include <linux/miscdevice.h>\r\n" + 
				"#include <asm/mach/map.h>\r\n" + 
				"#include <asm/uaccess.h>\r\n" + 
				"#include <asm/io.h>\r\n" + 
				"\r\n" + 
				"#define SAT_T unsigned int\r\n" + 
				"\r\n" + 
				"#define DEVICE_NAME \"sat\" \r\n" + 
				"\r\n" + 
				"#define SAT_BASE_ADDR   (0x43C00000)\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"typedef struct\r\n" + 
				"{\r\n" + 
				"    \r\n" + 
				"  volatile unsigned int Data;\r\n" + 
				"\r\n" + 
				"}SAT_DATA;");	
		
		pw.println("");
		for(int i=0;i<(number_wr_use);i++)
		{
		pw.print("SAT_DATA*");
		pw.print(" reg"+i);
		pw.println(";");
		}
		for(int i=number_wr;i<(number_wr+number_rd_use);i++)
		{
		pw.print("SAT_DATA*");
		pw.print(" reg"+i);
		pw.println(";");
		}		
		pw.println("");
		
		
		pw.println("static int sat_drv_open(struct inode *Inode, struct file *File)\r\n" + 
				"{");
		
	
		for(int i=0;i<(number_wr_use);i++)
		{
		pw.print("reg"+i);
		pw.println("->Data = 0x00000000;");
		}
		for(int i=number_wr;i<(number_wr+number_rd_use);i++)
		{
		pw.print("reg"+i);
		pw.println("->Data = 0x00000000;");
		}
		pw.println(" ");
		
		
		
		pw.println("return 0;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"static ssize_t sat_drv_read(struct file *file, char __user *buf, size_t count, loff_t *ppos)\r\n" + 
				"{\r\n" + 
				"unsigned int ret = 0;\r\n" + 
				"   ");
		
//		for(int i=0;i<number_rd_use;i++) //////////////read
//		{
//		pw.print("unsigned int in"+i);
//		pw.println(" = 0;");
//		}
//		pw.println(" ");	
//
//		for(int i=0;i<number_rd_use;i++) 
//		{
//		pw.print("in"+i);
//		pw.print("= reg"+(i+number_wr));
//		pw.println("->Data;");
//		}
//		pw.println(" ");
		
		///////////////////////////////////////简化read
		pw.print("int a["+number_rd_use);  
		pw.println("]={0};");
		pw.println(" ");
				
		for(int i=0;i<number_rd_use;i++) 
		{
		pw.print("a["+i);
		pw.print("]= reg"+(i+number_wr));
		pw.println("->Data;");
		}
		pw.println(" ");
		
		
//		pw.println("   u32 pos = ppos;\r\n" + 
//				"   ret = copy_to_user(buf,a,count); \r\n" + 
//				"   \r\n" + 
//				"   printk(\"read \\n\");\r\n" + 
//				"   int i;\r\n" + 
//				"   for (i=0;i<3;i++){\r\n" + 
//				"	printk(\"read_out[%d]=%d\\n\",i,a[i]);\r\n" + 
//				"   }\r\n" + 
//				"	\r\n" + 
//				"   return ret;\r\n" + 
//				"} \r\n" + 
//				"\r\n" + 
//				"static ssize_t sat_drv_write(struct file *file, const char __user *buf, size_t count, loff_t *ppos)\r\n" + 
//				"{\r\n" + 
//				"unsigned int ret = 0;");
		
		pw.println("u32 pos = ppos;\r\n" + 
				"ret = copy_to_user(buf,a,count); \r\n");
		
		pw.println("/*");
		pw.println("   printk(\"read \\n\");\r\n" + 
				"   int i; ");
		pw.print("   for (i=0;i<"+number_rd_use);  //////////////write
		pw.println(";i++){\r\n" + 
				"		printk(\"read_out[%d]=%d\\n\",i,a[i]);\r\n" + 
				"   }");
		pw.println("*/");
		
		
		pw.println("return ret;\r\n" + 
				"} \r\n" + 
				"\r\n" + 
				"static ssize_t sat_drv_write(struct file *file, const char __user *buf, size_t count, loff_t *ppos)\r\n" + 
				"{\r\n" + 
				"unsigned int ret = 0; ");
		
		pw.print("int b["+number_wr_use);  //////////////write
		pw.println("]={0};");
		pw.println(" ");
		
		pw.println("u32 pos = ppos;\r\n" + 
				"ret = copy_from_user(b,buf,count);\r\n"); 
		
		pw.println(		"/*	\r\n" + 
				"	 printk(\"write \\n\");\r\n" + 
				"     int i;\r\n" + 
				"     for (i=0;i<10;i++){\r\n" + 
				"		  printk(\"write_in[%d]=%d\\n\",i,b[i]);\r\n" + 
				"	} */ ");
		
		
		pw.println(" ");
		for(int i=0;i<number_wr_use;i++)
		{
		pw.print("reg"+i);
		pw.print("->Data = b["+i);
		pw.println("];");
		}	
		
		pw.println("");
		pw.println("return ret;\r\n" + 
				"} \r\n" + 
				"\r\n" +
				"static struct file_operations dev_fops =\r\n" + 
				"{ \r\n" + 
				"    .owner = THIS_MODULE, \r\n" + 
				"    .open = sat_drv_open,\r\n" + 
				"    .read = sat_drv_read, \r\n" + 
				"    .write = sat_drv_write,\r\n" + 
				"}; \r\n" + 
				"\r\n" +
				"static struct miscdevice misc =\r\n" + 
				"{ \r\n" + 
				"    .minor = MISC_DYNAMIC_MINOR, \r\n" + 
				"    .name = DEVICE_NAME, \r\n" + 
				"    .fops = &dev_fops \r\n" + 
				"}; \r\n" + 
				"\r\n" +
				"static int __init sat_drv_init(void)\r\n" + 
				"{\r\n" + 
				"int ret; ");

		pw.println("");
		pw.println("reg0 = ioremap(SAT_BASE_ADDR, sizeof(SAT_T)); ");
		
		
		for(int i=1;i<number_wr_use;i++)
		{
		pw.print("reg"+i);
		pw.print(" = ioremap(SAT_BASE_ADDR+"+i*4);
		pw.println(", sizeof(SAT_T)); ");
		}
		for(int i=number_wr;i<number_rd_use+number_wr;i++)
		{
		pw.print("reg"+i);
		pw.print(" = ioremap(SAT_BASE_ADDR+"+i*4);
		pw.println(", sizeof(SAT_T)); ");
		}		
		
		pw.println(" ");
		
		pw.println("    ret = misc_register(&misc);\r\n" + 
				"    if(ret)\r\n" + 
				"    {\r\n" + 
				"        printk(\"sat_drv_init faiitrt!\\n\");\r\n" + 
				"    }\r\n" + 
				"    else\r\n" + 
				"    {\r\n" + 
				"        printk(\"sat_drv_init success!\\n\");\r\n" + 
				"    } \r\n" + 
				"    return ret;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"static void __exit sat_drv_exit(void)\r\n" + 
				"{");
		
		for(int i=0;i<number_wr_use;i++)
		{
		pw.print("iounmap(");
		pw.print("reg"+i);
		pw.println(");");
		}
		for(int i=number_wr;i<number_rd_use+number_wr;i++)
		{
		pw.print("iounmap(");
		pw.print("reg"+i);
		pw.println(");");
		}		
		
		pw.println("");
		pw.println("misc_deregister(&misc); \r\n" + 
				"printk(\"sat_drv_exit success!\\n\");\r\n" + 
				"}  \r\n" + 
				"\r\n" + 
				"module_init( sat_drv_init);\r\n" + 
				"\r\n" + 
				"module_exit( sat_drv_exit);\r\n" + 
				"\r\n" + 
				"MODULE_LICENSE(\"Dual BSD/GPL\");");
		pw.println("");
		pw.println("");
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////应用程序
		pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream("cnf_main.c", true), "utf-8"), true);
		
		//pw.println("////////////////////////////////////////main");
		pw.println("#include <stdio.h>");
		pw.println("#include <stdlib.h>");
		pw.println("#include <sys/ioctl.h>");
		pw.println("#include <sys/types.h>");
		pw.println("#include <sys/stat.h>");
		pw.println("#include <fcntl.h>");
		pw.println("#include <unistd.h>");
		pw.println("#include <time.h>");
		pw.println("#include <string.h>");
		pw.println("#define false 0");
		pw.println("#define true 1");
		pw.println("#define MILLION 1000000");
		pw.println("char src[40];");
		pw.println("int tar[100];");
		pw.println("int cha=0;");
		pw.println("int *opera(int *str,int *control,int *slv_reg);");
		pw.println("int *read_pro(int *rd,int *control,int *slv_reg);");
		pw.println("int binary_Decimal(int *a,int len);");
		pw.println("int *conversion(int decimalNumber);");
		pw.println("void ToBin(char src[]);");
		pw.println("int isZero(char src[]);");
		pw.println("int lenof(char tar[]);");
		pw.println(" ");
		pw.println(" ");
		
		
		pw.println("int main(int argc, char** argv) {");
		pw.println("int fd;");
		pw.println("fd = open(\"/dev/sat\", O_RDWR);");
		pw.println("	if(fd < 0) {");
		pw.println("	//	printf(\"\\n\");");
		pw.println("	}");
		pw.println(" ");
		
//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("int control_"+i);
//		pw.print(" = 0");
//		pw.print(";  ");
//		if(i==10*j) {
//		j = j + 1;	
//		pw.println("  ");}
//		}
//		pw.println(" 	");
//		pw.println(" 	");
//		
//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("control_["+(i-1));
//		pw.print("]");
//		pw.print(" = control_"+i);
//		pw.print(";  ");
//		if(i==10*j) {
//		j = j + 1;	
//		pw.println("  ");}
//		}
//		pw.println(" 	");
//		pw.println(" 	");
		pw.print("int control["+Parser.var_num);  
		pw.println("]={0};");
		
		//pw.println("int sign = 0;");
		pw.println("int count = 0;");
		pw.println("int *data_need;");
		pw.println("int sat,unsat;");
		pw.print("int opera_num0");
		for (int i=1;i<number_wr_use;i++)
		{
		pw.print(",opera_num"+i);
//		    if(i%5==0) {
//			    pw.println("  ");
//			}
		}
		pw.println(";");
		pw.println(" 	");
		
		pw.print("int wr["+(number_wr_use));  
		pw.println("];");
		pw.print("int rd["+number_rd_use);  
		pw.println("];");
		pw.println(" ");
		
		
		pw.println("////////////////////////////////////////////////////////////////////////////随机赋值");
		pw.println("int slv_reg[67]={0};\r\n" + 
				"\r\n" + 
				"int slv_reg0[32] = {0};\r\n" + 
				"int slv_reg8[32] = {0};\r\n" + 
				"int slv_reg9[32] = {0};\r\n" + 
				"\r\n" + 
				"int i;\r\n" + 
				"srand((unsigned)time(NULL));//srand()就是给rand()提供种子seed \r\n" + 
				"for (i = 0; i < 67; i++)\r\n" + 
				"{\r\n" + 
				"    slv_reg[i] = rand() % 2;\r\n" + 
				"   //printf(\"%d random generate digit is : %d\\n\",i+1,random_n);\r\n" + 
				"   // printf(\"slv_reg [%d] = %d\\n\",29-i,slv_reg[29-i]);\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"for(i=0;i<10;i++){\r\n" + 
				"    slv_reg0[29-i]=slv_reg[i];}\r\n" + 
				"\r\n" + 
				"for(i=10;i<40;i++){\r\n" + 
				"    slv_reg8[41-i]=slv_reg[i];}\r\n" + 
				"\r\n" + 
				"for(i=40;i<67;i++){\r\n" + 
				"    slv_reg9[71-i]=slv_reg[i];}\r\n" + 
				"");
		
		pw.println("slv_reg0[31] = 1;  //start");

        //////////////////输出length
		String result_1 = Integer.toBinaryString(Parser.var_num);
		//int length_1 = result_1.length();
        int[] intArr_1 = new int[result_1.length()];     //字符串转为数组
        char[] ch_1 = result_1.toCharArray();
        for (int b = 0; b < result_1.length(); b++) {
            intArr_1[b] = (int) ch_1[b] - 48;
        }
		for (int j=19,s=intArr_1.length-1;j>=10 && s>=0;j--,s--) {  
		pw.print("slv_reg0["+j);
		pw.print("] = "+intArr_1[s]);
		pw.print("; ");}
		
		pw.println(" 	");
		pw.println("slv_reg9[4] = 1;   //finish");
		
		pw.println(" 	");
		pw.println("int number0 = binary_Decimal(slv_reg0,32);");
		pw.println("int number8 = binary_Decimal(slv_reg8,32);");
		pw.println("int number9 = binary_Decimal(slv_reg9,32);");
		
		
		pw.println("////////////////////////////////////////////////////////////////////////////");
		pw.print("int init["+(number_wr_use));
		pw.println("]={number0,0,0,0,0,0,0,0,number8,number9};");  
		
		pw.print("write(fd,init,"+(number_wr_use)*4);
		pw.println(");");	
		pw.println("\r\n" + 
				"struct timespec tpstart;\r\n" + 
				"struct timespec tpend;\r\n" + 
				"double timedif;\r\n" + 
				"clock_gettime(CLOCK_MONOTONIC, &tpstart);" + 
				"");
		pw.println(" ");
		
		pw.println("while(1){");
		pw.println(" 	");
		pw.print("read(fd,rd,"+number_rd_use*4);
		pw.println(");");	
		pw.println(" ");
//		pw.println("/////////////////1");
		pw.println("data_need = read_pro(rd,control,slv_reg); ");	         //只操作一次

		pw.println(" 	");		
		for (int i=1,j=1;i<=Parser.var_num;i++)
		{
		pw.print("control["+(i-1));
		pw.print("]");
		pw.print(" = data_need["+(i+1+number_wr_use));
		pw.print("];  ");
		if(i==5*j) {
		j = j + 1;	
		pw.println("  ");}
		}
		pw.println(" 	");
		
//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("control_"+i);
//		pw.print(" = data_need["+(i+3+number_wr_use));
//		pw.print("]; ");
//		if(i==10*j) {
//		j = j + 1;	
//		pw.println("  ");}
//		}
//		pw.println("  ");
//		pw.println("  ");
//		
//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("control_["+(i-1));
//		pw.print("]");
//		pw.print(" = control_"+i);
//		pw.print(";  ");
//		if(i==10*j) {
//		j = j + 1;	
//		pw.println("  ");}
//		}
//		pw.println(" 	");
//		pw.println(" 	");
		
		pw.println("sat = data_need[0];");
		pw.println("unsat = data_need[1];");
		pw.println(" 	");	
		pw.print("if(sat==1");
		pw.println("){");
		pw.println("	clock_gettime(CLOCK_MONOTONIC, &tpend);\r\n" + 
				"	timedif=MILLION*(tpend.tv_sec-tpstart.tv_sec)+(tpend.tv_nsec-tpstart.tv_nsec)/1000;\r\n" + 
				"	fprintf(stdout, \"it took %f us\\n\", timedif);");
		pw.println(" 	printf(\"The problem is SAT\\n\");");	
		pw.println(" 	break;}");
		pw.println("else if(unsat==1){");	
		pw.println("	clock_gettime(CLOCK_MONOTONIC, &tpend);\r\n" + 
				"	timedif=MILLION*(tpend.tv_sec-tpstart.tv_sec)+(tpend.tv_nsec-tpstart.tv_nsec)/1000;\r\n" + 
				"	fprintf(stdout, \"it took %f us\\n\", timedif);");
		pw.println(" 	printf(\"The problem is UNSAT\\n\");");
		pw.println(" 	break;}");	
		pw.println(" 	");
		
//		pw.println("count = count + 1;");
//		pw.println("if(count == 1000000 || count == 1400000 || count == 1800000 || count == 2000000)");
//		pw.println("    printf(\"count=%d\\n\",count);");
//		pw.println(" 	");	
		
		
		for (int i=0;i<number_wr_use;i++)
		{
		pw.print("wr["+i);
		pw.print("] = data_need["+(i+2));
		pw.println("];");
		}
		pw.println(" 	");
		
//		for (int i=0;i<number_wr_use;i++)
//		{
//		pw.print("opera_num"+i);
//		pw.print(" = data_need["+(i+2));
//		pw.println("];");
//		}
//		pw.println(" 	");
//
//		for (int i=0;i<number_wr_use;i++)
//		{
//		pw.print("wr["+i);
//		pw.print("] = opera_num"+i);
//		pw.println(";");
//		}
//		pw.println(" 	");
				
		
		
		pw.print("write(fd,wr,"+number_wr_use*4);
		pw.println("); }");	
		pw.println("    ");
		
		pw.println("close(fd);");
		pw.println("return 0;");	
		pw.println(" 	");
		pw.println("}");
		pw.println(" 	");
		pw.println(" 	");
		
		pw.println("int *read_pro(int *rd,int *control,int *slv_reg){");
		pw.println(" 	");
		pw.print("int str["+(number_rd_use*32));  
		pw.println("]={0};");
		pw.println("int sum=0;");
		pw.println("int i;");
		pw.println(" ");
		
		for(int i=0;i<number_rd_use;i++) 
		{
		pw.print("int *read_bin_"+i);
		pw.println(";");
		}
		pw.println(" ");
		
		
		for(int i=0;i<number_rd_use;i++) 
		{
		pw.print("read_bin_"+i);
		pw.print(" = conversion(rd["+i);
		pw.println("]);//shijinzhi -> erjinzhi");
		
		
		pw.println("for(i=0;i<32;i++){");
		pw.print("    str[sum]=read_bin_"+i);
		pw.println("[i];");
		pw.println("    sum +=1;}");
		pw.println(" ");
		}
		pw.println("str[sum]='\\0';");
		pw.println(" ");
		pw.println("int *data_need;");
		pw.println("data_need = opera(str,control,slv_reg);");
		pw.println(" ");
		pw.println("return data_need;");
		pw.println(" ");
		pw.println("}");
		pw.println(" ");
		pw.println(" 	");
		
		pw.println("int *opera(int *str,int *control,int *slv_reg){");
		pw.println(" 	");
		pw.println("int fd;");
		pw.println("fd=open(\"/dev/sat\",O_RDWR);");
		pw.println("if(fd < 0){");
		pw.println("  //  printf(\"\\n\");");
		pw.println("}");
		pw.println(" ");
		
		pw.println("int unsat = str[0];");
//		pw.println("int unsat = str[1];");
//		pw.println("int back = str[2];");
		

		for(int i=1,k=3,m=0;i<=Parser.var_num+1;i++,k=k+3) 
		{
		pw.print("int v_p"+i);  
		pw.print(" = str["+k);
		pw.println("];");
		
		pw.print("int v_n"+i);  
		pw.print(" = str["+(k+1));
		pw.println("];");
		
		pw.print("int pro_"+i);  
		pw.print(" = str["+(k+2));
		pw.println("];");
		
		if(k==27+32*m) {
		    k = k + 2;	
		    m = m + 1;
		    pw.println(" ");
		    }
		
		}
		
		pw.print("int sat = v_p"+(Parser.var_num+1));
		pw.print(" || ");
		pw.print("v_n"+(Parser.var_num+1));
		pw.print(" || ");
		pw.print("pro_"+(Parser.var_num+1));
		pw.println(";");
		
		
		pw.println(" 	");
		pw.println("//reasoner");
		pw.println("/////////////////////////");
		
		
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
								pw.print(" = 0; ");
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
									
									
									//
//									else if (mid < 0 && leng1 < 3)
//										pw.print(" 0 ");
//									else if (mid > 0 && leng1 < 3)
//										pw.print(" 1 ");
									//
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
								pw.print(" = 0; ");
								pw.print("O_N" + i + "_" + k);
								pw.print(" = ");
								k++;
								int count2=0;
								int leng9 = clause.size();
								if (leng9 == 3)
									pw.print("1");
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
		//System.out.println("reasoning finished");
		

		pw.println("int conflict = 0;");
		pw.print("conflict = (O_P1 && O_N1)");
		for (int i=2;i<=Parser.var_num;i++)
		{
		pw.print(" || ");
		pw.print("(");
		pw.print("O_P"+i);
		pw.print(" && ");
		pw.print("O_N"+i);
		pw.print(")");
		}
		pw.println(";  ");
		pw.println("/////////////////////////");
		
//		pw.println("if (back == 1)");
//		pw.println(" 	sign = sign - 1;");
//		pw.println("else if (conflict==1 && back == 0)");
//		pw.println(" 	sign = sign;");
//		pw.println("else if (conflict==0 && back == 0)");
//		pw.println(" 	sign = sign + 1;");	
//		pw.println(" 	");		

//		pw.println("printf(\"conflict=%d\\n\",conflict);");
//		pw.println("printf(\"back=%d\\n\",back);");
//		pw.println("printf(\"sign=%d\\n\",sign);");	

//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("int control_"+i);
//		pw.print(" = 0");
//		pw.print(";  ");
//			if(i==10*j) {
//			j = j + 1;	
//			pw.println("  ");}
//		}
//		pw.println(" 	");
//		pw.println(" 	");
//		
//		for (int i=1,j=1;i<=Parser.var_num;i++)
//		{
//		pw.print("control_"+i);
//		pw.print(" = control_["+(i-1));
//		pw.print("]");
//		pw.print(";  ");
//			if(i==10*j) {
//			j = j + 1;	
//			pw.println("  ");}
//		}
//		pw.println(" 	");
		
		pw.println(" 	");
		for (int i=1,j=1;i<=Parser.var_num;i++)                   //简化数组取数
		{
		pw.print("int control_"+i);
		pw.print(" = control["+(i-1));
		pw.print("]");
		pw.print(";  ");
			if(i==5*j) {
			j = j + 1;	
			pw.println("  ");}
		}
		pw.println(" 	");	
		
		for (int i=1;i<=Parser.var_num;i++)
		{
		pw.print("if (pro_"+i);
		pw.println(" == 1)");
		pw.print("    control_"+i);
		pw.print(" =");
		pw.print(" 1 - control_"+i);
		pw.println(";");
		}
		pw.println(" 	");
		
		pw.println("///////////////////////////////////////");
		for (int i=0;i<number_wr_use;i++)
		{
		pw.print("int b"+i);
		pw.println("[32] = {0};");
		}
		pw.println(" 	");
		
		pw.println("b0[31] = 1;  //start");
		pw.println("b0[30] = conflict;");
		pw.println("b0[29] = slv_reg[0]; \r\n" + 
				"b0[28] = slv_reg[1]; \r\n" + 
				"b0[27] = slv_reg[2]; \r\n" + 
				"b0[26] = slv_reg[3]; \r\n" + 
				"b0[25] = slv_reg[4];  \r\n" + 
				"b0[24] = slv_reg[5]; \r\n" + 
				"b0[23] = slv_reg[6]; \r\n" + 
				"b0[22] = slv_reg[7];  \r\n" + 
				"b0[21] = slv_reg[8]; \r\n" + 
				"b0[20] = slv_reg[9]; ");
		
		
		///////////////////////////////////////////////////////////////////
//		for (int i=1;i<=Parser.var_num-1;i++)          //输出sign
//		{
//		pw.print("if(sign=="+i);
//		pw.println("){");
//		pw.print("    ");
//		
//		//System.out.println(Integer.toBinaryString(i));   //转为二进制
//		String result = Integer.toBinaryString(i);
//		//pw.println(result);
//		int length = result.length();
//		//pw.println("length="+length);
//		
//
////	               将String字符串转换为int数组（数字范围0-9）
////	     param str 字符串（内只能包含数字0-9）
////	     return 字符串内产生的数组
//
//		
//        int[] intArr = new int[result.length()];     //字符串转为数组
//        char[] ch = result.toCharArray();
//        for (int b = 0; b < result.length(); b++) {
//            intArr[b] = (int) ch[b] - 48;
//        }
//
//      // 测试输出数组
////        for (int b = 0; b < intArr.length; b++) {
////            System.out.println(intArr[b]);
////        }
//		
//				
//		//for (int j=29,s=0;j>=20 && s<intArr.length;j--,s++) { 
//		for (int j=29,s=intArr.length-1;j>=20 && s>=0;j--,s--) {  
//		pw.print(" b0["+j);
//		pw.print("] = "+intArr[s]);
//		pw.print(";");}
//		pw.println("}");
//		
//		pw.println(" 	");
//	
//		
//		
//	}
//		pw.println(" 	");	
//		
		
		///////////////////////////////////////////////////////////////////	
		//输出sign_b
	    //System.out.println(Integer.toBinaryString(i));   //转为二进制
		String result = Integer.toBinaryString(Parser.var_num);
		//pw.println(result);
		int length = result.length();
		//pw.println("length="+length);
		
        int[] intArr = new int[result.length()];     //字符串转为数组
        char[] ch = result.toCharArray();
        for (int b = 0; b < result.length(); b++) {
            intArr[b] = (int) ch[b] - 48;
        }

		for (int j=19,s=intArr.length-1;j>=10 && s>=0;j--,s--) {  
		pw.print("b0["+j);
		pw.print("] = "+intArr[s]);
		pw.print("; ");}
		
		pw.println(" 	");
		
		//////////////////////////////////////////////////////////////////////
		
		int m=31,j=1;
		//for (int i=1,m=31,j=1;i<=(number-1)/2 && m>=4 && j<=Parser.var_num;i++,m--,j++)
		for (int i=1;i<number_wr_use;i++)
		{
				
			pw.println(" 	");
			for (m=31,j=j;m>=4 && j<=Parser.var_num;m=m-3,j++) {
			//for (int m=31;m>=4;m--) {
			pw.print("b"+i);
			pw.print("["+m);
			pw.print("]");
				//for (int ;j<=Parser.var_num;j++) {
			pw.print(" = O_P"+j);
			pw.print(";  ");
			
			pw.print("b"+i);
			pw.print("["+(m-1));
			pw.print("]");
			pw.print(" = O_N"+j);
			pw.print("; ");
			
			
			pw.print("b"+i);
			pw.print("["+(m-2));
			pw.print("]");
			pw.print(" = control_"+j);
			pw.println("; ");}
		}
		
		pw.println("b8[31] = slv_reg[10]; b8[30] = slv_reg[11];b8[29] = slv_reg[12]; \r\n" + 
				"b8[28] = slv_reg[13]; b8[27] = slv_reg[14]; b8[26] = slv_reg[15]; \r\n" + 
				"b8[25] = slv_reg[16]; b8[24] = slv_reg[17]; b8[23] = slv_reg[18]; \r\n" + 
				"b8[22] = slv_reg[19]; b8[21] = slv_reg[20]; b8[20] = slv_reg[21];  \r\n" + 
				"b8[19] = slv_reg[22]; b8[18] = slv_reg[23]; b8[17] = slv_reg[24]; \r\n" + 
				"b8[16] = slv_reg[25]; b8[15] = slv_reg[26]; b8[14] = slv_reg[27]; \r\n" + 
				"b8[13] = slv_reg[28]; b8[12] = slv_reg[29]; b8[11] = slv_reg[30]; \r\n" + 
				"b8[10] = slv_reg[31]; b8[9] = slv_reg[32]; b8[8] = slv_reg[33]; \r\n" + 
				"b8[7] = slv_reg[34]; b8[6] = slv_reg[35]; b8[5] = slv_reg[36];  \r\n" + 
				"b8[4] = slv_reg[37]; b8[3] = slv_reg[38]; b8[2] = slv_reg[39]; \r\n" + 
				"\r\n" + 
				"b9[31] = slv_reg[40]; b9[30] = slv_reg[41];b9[29] = slv_reg[42]; \r\n" + 
				"b9[28] = slv_reg[43]; b9[27] = slv_reg[44]; b9[26] = slv_reg[45]; \r\n" + 
				"b9[25] = slv_reg[46]; b9[24] = slv_reg[47]; b9[23] = slv_reg[48]; \r\n" + 
				"b9[22] = slv_reg[49]; b9[21] = slv_reg[50]; b9[20] = slv_reg[51];  \r\n" + 
				"b9[19] = slv_reg[52]; b9[18] = slv_reg[53]; b9[17] = slv_reg[54]; \r\n" + 
				"b9[16] = slv_reg[55]; b9[15] = slv_reg[56]; b9[14] = slv_reg[57]; \r\n" + 
				"b9[13] = slv_reg[58]; b9[12] = slv_reg[59]; b9[11] = slv_reg[60]; \r\n" + 
				"b9[10] = slv_reg[61]; b9[9] = slv_reg[62]; b9[8] = slv_reg[63]; \r\n" + 
				"b9[7] = slv_reg[64]; b9[6] = slv_reg[65]; b9[5] = slv_reg[66]; \r\n" + 
				"b9[4] = 1;");	
		//pw.println(" 	");	
		//pw.println("b9[4] = 1;");			
		pw.println(" 	");	
		
		
//		for (int i=0;i<number_wr_use;i++)
//		{
//		pw.print("int opera_num"+i);
//		pw.print(" = binary_Decimal(b"+i);
//		pw.println(",32);");
//		}
//		pw.println(" 	");
		
		
		pw.print("int data_need["+(number_wr_use+2+Parser.var_num)); 
		pw.println("]={0};");
		pw.println(" ");

		
		pw.println("data_need[0] = sat;");
		pw.println("data_need[1] = unsat;");
	
		
		for (int i=0;i<number_wr_use;i++)
		{
		pw.print("data_need["+(i+2));
		pw.print("] = binary_Decimal(b"+i);
		pw.println(",32);");
		}
		pw.println(" 	");		
		
		
		
		for (int i=1,b=1;i<=Parser.var_num;i++)
		{
		pw.print("data_need["+(i+1+number_wr_use));
		pw.print("] = control_"+i);
		pw.print("; ");
		    if(i==5*b){
		    b = b + 1;	
		    pw.println("  ");}
		}
		pw.println(" 	");
		pw.println("return data_need;");
		pw.println();
		pw.println("}");	
		
		pw.println(" 	");
		pw.println("///////////////////////////////////////////////////");
		
		pw.println("int binary_Decimal(int *a,int len)\r\n" + 
				"{\r\n" + 
				"    int i,sum=0,m,j;\r\n" + 
				"\r\n" + 
				"   	if(len<=32)\r\n" + 
				"    {\r\n" + 
				"\r\n" + 
				"        for(i=0;i<len;i++)\r\n" + 
				"        {\r\n" + 
				"            m=1;\r\n" + 
				"\r\n" + 
				"            if(a[i]==1)\r\n" + 
				"            {\r\n" + 
				"                for(j=1;j<=len-i-1;j++)\r\n" + 
				"                m*=2;\r\n" + 
				"                sum+=m;\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"    return sum;\r\n" + 
				"\r\n" + 
				"    }\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int *conversion(int decimalNumber)//shijinzhi -> erjinzhi\r\n" + 
				"{\r\n" + 
				"    int i=0;\r\n" + 
				"	memset(tar,0,sizeof(tar));\r\n" + 
				"	sprintf(src,\"%d\",decimalNumber);\r\n" + 
				"	//if(isZero(src)) //printf(\"\\n\");\r\n" + 
				"	//	else {\r\n" + 
				"			ToBin(src);\r\n" + 
				"	//	}\r\n" + 
				"	//	//printf(\"\");\r\n" + 
				"		cha=0;\r\n" + 
				"		return tar;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int lenof(char tar[])\r\n" + 
				"{\r\n" + 
				"	int i=0;\r\n" + 
				"	int len=0;\r\n" + 
				"	while(tar[i]!='\\0') {\r\n" + 
				"		i++;\r\n" + 
				"		len++;\r\n" + 
				"	}\r\n" + 
				"	return len;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"int isZero(char src[])\r\n" + 
				"{\r\n" + 
				"	int i;\r\n" + 
				"	for ( i=lenof(src)-1; i>=0; i--) {\r\n" + 
				"		if (src[i] != '0') {\r\n" + 
				"			return false;\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	return true;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"void ToBin(char src[])\r\n" + 
				"{\r\n" + 
				"	int i=0;\r\n" + 
				"	int j=0;\r\n" + 
				"	int len=lenof(src);\r\n" + 
				"	while(!isZero(src)) {\r\n" + 
				"		int temp=0;\r\n" + 
				"		int yu=0;\r\n" + 
				"		for(i=0; i<len; i++) {\r\n" + 
				"			if(i==len-1) {\r\n" + 
				"				temp = (yu*10 + (src[i]-'0'))/2 ;\r\n" + 
				"				yu=(yu*10 + (src[i]-'0'))%2;\r\n" + 
				"				src[i]= '0'+temp;\r\n" + 
				"				tar[j]=yu;\r\n" + 
				"				j++;\r\n" + 
				"			} else {\r\n" + 
				"				temp = (yu*10 + (src[i]-'0'))/2 ;\r\n" + 
				"				yu=(yu*10 + (src[i]-'0'))%2;\r\n" + 
				"				src[i]='0'+temp;\r\n" + 
				"			}\r\n" + 
				"		}\r\n" + 
				"	}\r\n" + 
				"	cha=j;\r\n" + 
				"}\r\n" + 
				"");
		
	}
		
	  public static void writeEnd()throws UnsupportedEncodingException, FileNotFoundException{	

//			
//		pw.print("or OR_CO(conflict");
//		for(int i=1;i<=Parser.var_num;i++)
//		{
//		pw.print(",l_co"+i);
//		}
//		pw.println(");");
		
//		pw.println("(*DONT_TOUCH=\"yes\"*) var_"+Utils.rand1.get(0)+" variable"+1+"(.in_top(start),.in_bottom(b2"+"),.out_top(t1)"+",.out_bottom(b1),.conflict(conflict),.vimp_p(O_P"+Utils.rand.get(0)+"),.vimp_n(O_N"+Utils.rand.get(0)+"),.vout_p(v_p"+Utils.rand.get(0)+"),.vout_n(v_n"+Utils.rand.get(0)+"));");
//		for (int i=2;i<=Parser.var_num-1;i++)
//		{
//			int i1=i-1;
//			int i2=i+1;
//		pw.println("(*DONT_TOUCH=\"yes\"*) var_"+Utils.rand1.get(i-1)+" variable"+i+"(.in_top(t"+i1+"),.in_bottom(b"+i2+"),.out_top(t"+i+"),.out_bottom(b"+i+"),.conflict(conflict),.vimp_p(O_P"+Utils.rand.get(i1)+"),.vimp_n(O_N"+Utils.rand.get(i1)+"),.vout_p(v_p"+Utils.rand.get(i1)+"),.vout_n(v_n"+Utils.rand.get(i1)+"));");
//
//		}
//		int i3=Parser.var_num-1;
//		int i4=Parser.var_num;
//		pw.println("(*DONT_TOUCH=\"yes\"*) var_"+Utils.rand1.get(Parser.var_num-1)+" variable"+Parser.var_num+"(.in_top(t"+i3+"),.in_bottom(1'b0),.out_top(t"+i4+"),.out_bottom(b"+i4+"),.conflict(conflict),.vimp_p(O_P"+Utils.rand.get(Parser.var_num-1)+"),.vimp_n(O_N"+Utils.rand.get(Parser.var_num-1)+"),.vout_p(v_p"+Utils.rand.get(Parser.var_num-1)+"),.vout_n(v_n"+Utils.rand.get(Parser.var_num-1)+"));");
//
//		pw.println(); 
//		pw.print("(*DONT_TOUCH=\"yes\"*) reasoner reasoner(.v_p1(v_p1), .v_n1(v_n1),.O_P1(O_P1),.O_N1(O_N1)");
//		for(int i=2;i<=Parser.var_num;i++)
//		{
//		pw.print(",.v_p"+i+"(v_p"+i+"), .v_n"+i+"(v_n"+i+"),.O_P"+i+"(O_P"+i+"),.O_N"+i+"(O_N"+i+")");
//		}
//		pw.println(",.conflict(conflict));");
		
		/*
		 * for(int i=1;i<=Parser.var_num;i++) { pw.print(",.l_co"+i+"(l_co"+i+")"); }
		 * pw.println(");"); pw.println(); pw.
		 * print("(*DONT_TOUCH=\"yes\"*) conflict_detector conflict_detector(.conflict(conflict)"
		 * ); for(int i=1;i<=Parser.var_num;i++) { pw.print(",.l_co"+i+"(l_co"+i+")"); }
		 * pw.println(");");
		 */
		 
		
		//pw.println("endmodule");
//		pw.println(); 
//		pw.println();
		pw.println();
		}
	
	
	public static void writeTop() throws UnsupportedEncodingException, FileNotFoundException{
		System.out.println("statring write TOP of all the parrllels");
		System.out.println("   ");
//		pw.println("module Multip_solver"+times+"(start_key,sat,unsat);");
//		pw.println("input start_key;");
//		pw.println("output sat,unsat;");
//		pw.println();
//		for(int i=0;i<times;i++)
//		{
//			pw.println("wire sat"+i+";");
//			pw.println("wire unsat"+i+";");			
//		}	
//		pw.println();
//		pw.println("reg start=0;");		
//		pw.println("  always@(negedge start_key)	");			
//		pw.println("     begin	");	
//		pw.println("       start=1;	");			
//		pw.println("      end	");		
//		pw.println();
//		pw.print("or OR_SAT(sat");
//		for(int i=0;i<times;i++){
//		pw.print(",sat"+i);
//		}
//		pw.println(");");		
//		
//		pw.print("or OR_UNSAT(unsat");
//		for(int i=0;i<times;i++){
//		pw.print(",unsat"+i);
//		}
//		pw.println(");");		
//
//		
//		for(int i=0;i<times;i++)
//		{
//			pw.println("(*DONT_TOUCH=\"yes\"*) solve_core"+i+" solve_core"+i+"(.start(start),.sat(sat"+i+"),.unsat(unsat"+i+"));");
//		}
//		
//		pw.println("endmodule");
		
//		for (int i=1;i<=1024;i++)
//		{
//		pw.print("x"+i);
//		pw.print(" = ");
//		pw.print("t"+i);
//		pw.println(";");
//		}
		
//		pw.println("O_P1 <= ");
//		pw.println("O_N1 <= ");
		
		
		
//		for(int i=1;i<=1024;i++)
//		{
//		pw.print("O_P"+i     );
//		pw.println("      <= ");
//		//pw.print("O_P"+i);
//		//pw.println(";");
//		pw.print("O_N"+i     );
//		pw.println("      <= ");
//		pw.print("control_"+i);
//		pw.println(" <= ");
//		//pw.print(" <= ");
//		//pw.print("O_N"+i);
//		//pw.println(";");
//		}

		
//		for(int i=1024;1<=i && i<=1024;i--)
//		{
//		pw.print("a"+i);
//		pw.print(",");
//		pw.print("v_n"+i     );
//		pw.print(",");
//		pw.print("v_p"+i     );
//		//pw.println("      <= ");
//		//pw.print("O_P"+i);
//		pw.print(",");
//
//		//pw.println("      <= ");
//
//		//pw.println(" <= ");
//		//pw.print(" <= ");
//		//pw.print("O_N"+i);
//		//pw.println(";");
//		}
		
		
//		for(int i=1;i<=104;i++)
//		{
//		pw.print("control_"+i);
//		pw.print(" <= ");
//		pw.print("control_"+i);
//		pw.println(";");
//
//		}
//		
//		for(int i=103;i>=1 && i<=103;i--)
//		{
//		pw.print(" assign ");
//		pw.print("rd_data"+i);
//		pw.print(" = ");
//		pw.println("{ };");
//		}
//		pw.println(); 
//		for(int i=0;i<103;i++)
//		{
//		pw.print(".");
//		pw.print("rd_data"+i);
//		pw.print("(");
//		pw.print("rd_data"+i);
//		pw.print("),");
//		//pw.println("{ };");
//		}
//		pw.println(); 		
//
//		pw.println(); 
//		for(int i=0;i<=103;i++)
//		{
//		pw.print(".");
//		pw.print("slv_reg"+i);
//		pw.print("(");
//		pw.print("slv_reg"+i);
//		pw.print("),");
//		//pw.println("{ };");
//		}
//		pw.println(); 	
		
		
//		for(int i=1,j=0;i<=1024 && j<=102;i++,j++)
//		{
//		pw.print("O_P"+i);
//		pw.println(" <= ");
//		pw.print("slv_reg"+j);
//		
//		
//		pw.print("O_N"+i);
//		pw.println(" <= ");
//		}
	}
	
		
		
	}

