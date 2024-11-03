#include <stdio.h>
#include <stdlib.h>
#include <sys/ioctl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>
#include <string.h>
#define false 0
#define true 1
#define MILLION 1000000
char src[40];
int tar[100];
int cha=0;
int *opera(int *str,int *control,int *slv_reg);
int *read_pro(int *rd,int *control,int *slv_reg);
int binary_Decimal(int *a,int len);
int *conversion(int decimalNumber);
void ToBin(char src[]);
int isZero(char src[]);
int lenof(char tar[]);
 
int main(int argc, char** argv) {
int fd;
fd = open("/dev/sat", O_RDWR);
	if(fd < 0) {
	//	printf("\n");
	}
int control[42]={0};
int count = 0;
int *data_need;
int sat,unsat;
int opera_num0,opera_num1,opera_num2,opera_num3,opera_num4,opera_num5,opera_num6,opera_num7,opera_num8,opera_num9;
 	
int wr[10];
int rd[5];
 
////////////////////////////////////////////////////////////////////////////随机赋值
int slv_reg[67]={0};

int slv_reg0[32] = {0};
int slv_reg8[32] = {0};
int slv_reg9[32] = {0};

int i;
srand((unsigned)time(NULL));//srand()就是给rand()提供种子seed 
for (i = 0; i < 67; i++)
{
    slv_reg[i] = rand() % 2;
   //printf("%d random generate digit is : %d\n",i+1,random_n);
   // printf("slv_reg [%d] = %d\n",29-i,slv_reg[29-i]);
}

for(i=0;i<10;i++){
    slv_reg0[29-i]=slv_reg[i];}

for(i=10;i<40;i++){
    slv_reg8[41-i]=slv_reg[i];}

for(i=40;i<67;i++){
    slv_reg9[71-i]=slv_reg[i];}

slv_reg0[31] = 1;  //start
slv_reg0[19] = 0; slv_reg0[18] = 1; slv_reg0[17] = 0; slv_reg0[16] = 1; slv_reg0[15] = 0; slv_reg0[14] = 1;  	
slv_reg9[4] = 1;   //finish
 	
int number0 = binary_Decimal(slv_reg0,32);
int number8 = binary_Decimal(slv_reg8,32);
int number9 = binary_Decimal(slv_reg9,32);
////////////////////////////////////////////////////////////////////////////
int init[10]={number0,0,0,0,0,0,0,0,number8,number9};
write(fd,init,40);

struct timespec tpstart;
struct timespec tpend;
double timedif;
clock_gettime(CLOCK_MONOTONIC, &tpstart);
 
while(1){
 	
read(fd,rd,20);
 
data_need = read_pro(rd,control,slv_reg); 
 	
control[0] = data_need[12];  control[1] = data_need[13];  control[2] = data_need[14];  control[3] = data_need[15];  control[4] = data_need[16];    
control[5] = data_need[17];  control[6] = data_need[18];  control[7] = data_need[19];  control[8] = data_need[20];  control[9] = data_need[21];    
control[10] = data_need[22];  control[11] = data_need[23];  control[12] = data_need[24];  control[13] = data_need[25];  control[14] = data_need[26];    
control[15] = data_need[27];  control[16] = data_need[28];  control[17] = data_need[29];  control[18] = data_need[30];  control[19] = data_need[31];    
control[20] = data_need[32];  control[21] = data_need[33];  control[22] = data_need[34];  control[23] = data_need[35];  control[24] = data_need[36];    
control[25] = data_need[37];  control[26] = data_need[38];  control[27] = data_need[39];  control[28] = data_need[40];  control[29] = data_need[41];    
control[30] = data_need[42];  control[31] = data_need[43];  control[32] = data_need[44];  control[33] = data_need[45];  control[34] = data_need[46];    
control[35] = data_need[47];  control[36] = data_need[48];  control[37] = data_need[49];  control[38] = data_need[50];  control[39] = data_need[51];    
control[40] = data_need[52];  control[41] = data_need[53];   	
sat = data_need[0];
unsat = data_need[1];
 	
if(sat==1){
	clock_gettime(CLOCK_MONOTONIC, &tpend);
	timedif=MILLION*(tpend.tv_sec-tpstart.tv_sec)+(tpend.tv_nsec-tpstart.tv_nsec)/1000;
	fprintf(stdout, "it took %f us\n", timedif);
 	printf("The problem is SAT\n");
 	break;}
else if(unsat==1){
	clock_gettime(CLOCK_MONOTONIC, &tpend);
	timedif=MILLION*(tpend.tv_sec-tpstart.tv_sec)+(tpend.tv_nsec-tpstart.tv_nsec)/1000;
	fprintf(stdout, "it took %f us\n", timedif);
 	printf("The problem is UNSAT\n");
 	break;}
 	
wr[0] = data_need[2];
wr[1] = data_need[3];
wr[2] = data_need[4];
wr[3] = data_need[5];
wr[4] = data_need[6];
wr[5] = data_need[7];
wr[6] = data_need[8];
wr[7] = data_need[9];
wr[8] = data_need[10];
wr[9] = data_need[11];
 	
write(fd,wr,40); }
    
close(fd);
return 0;
 	
}
 	
 	
int *read_pro(int *rd,int *control,int *slv_reg){
 	
int str[160]={0};
int sum=0;
int i;
 
int *read_bin_0;
int *read_bin_1;
int *read_bin_2;
int *read_bin_3;
int *read_bin_4;
 
read_bin_0 = conversion(rd[0]);//shijinzhi -> erjinzhi
for(i=0;i<32;i++){
    str[sum]=read_bin_0[i];
    sum +=1;}
 
read_bin_1 = conversion(rd[1]);//shijinzhi -> erjinzhi
for(i=0;i<32;i++){
    str[sum]=read_bin_1[i];
    sum +=1;}
 
read_bin_2 = conversion(rd[2]);//shijinzhi -> erjinzhi
for(i=0;i<32;i++){
    str[sum]=read_bin_2[i];
    sum +=1;}
 
read_bin_3 = conversion(rd[3]);//shijinzhi -> erjinzhi
for(i=0;i<32;i++){
    str[sum]=read_bin_3[i];
    sum +=1;}
 
read_bin_4 = conversion(rd[4]);//shijinzhi -> erjinzhi
for(i=0;i<32;i++){
    str[sum]=read_bin_4[i];
    sum +=1;}
 
str[sum]='\0';
 
int *data_need;
data_need = opera(str,control,slv_reg);
 
return data_need;
 
}
 
 	
int *opera(int *str,int *control,int *slv_reg){
 	
int fd;
fd=open("/dev/sat",O_RDWR);
if(fd < 0){
  //  printf("\n");
}
 
int unsat = str[0];
int v_p1 = str[3];
int v_n1 = str[4];
int pro_1 = str[5];
int v_p2 = str[6];
int v_n2 = str[7];
int pro_2 = str[8];
int v_p3 = str[9];
int v_n3 = str[10];
int pro_3 = str[11];
int v_p4 = str[12];
int v_n4 = str[13];
int pro_4 = str[14];
int v_p5 = str[15];
int v_n5 = str[16];
int pro_5 = str[17];
int v_p6 = str[18];
int v_n6 = str[19];
int pro_6 = str[20];
int v_p7 = str[21];
int v_n7 = str[22];
int pro_7 = str[23];
int v_p8 = str[24];
int v_n8 = str[25];
int pro_8 = str[26];
int v_p9 = str[27];
int v_n9 = str[28];
int pro_9 = str[29];
 
int v_p10 = str[32];
int v_n10 = str[33];
int pro_10 = str[34];
int v_p11 = str[35];
int v_n11 = str[36];
int pro_11 = str[37];
int v_p12 = str[38];
int v_n12 = str[39];
int pro_12 = str[40];
int v_p13 = str[41];
int v_n13 = str[42];
int pro_13 = str[43];
int v_p14 = str[44];
int v_n14 = str[45];
int pro_14 = str[46];
int v_p15 = str[47];
int v_n15 = str[48];
int pro_15 = str[49];
int v_p16 = str[50];
int v_n16 = str[51];
int pro_16 = str[52];
int v_p17 = str[53];
int v_n17 = str[54];
int pro_17 = str[55];
int v_p18 = str[56];
int v_n18 = str[57];
int pro_18 = str[58];
int v_p19 = str[59];
int v_n19 = str[60];
int pro_19 = str[61];
 
int v_p20 = str[64];
int v_n20 = str[65];
int pro_20 = str[66];
int v_p21 = str[67];
int v_n21 = str[68];
int pro_21 = str[69];
int v_p22 = str[70];
int v_n22 = str[71];
int pro_22 = str[72];
int v_p23 = str[73];
int v_n23 = str[74];
int pro_23 = str[75];
int v_p24 = str[76];
int v_n24 = str[77];
int pro_24 = str[78];
int v_p25 = str[79];
int v_n25 = str[80];
int pro_25 = str[81];
int v_p26 = str[82];
int v_n26 = str[83];
int pro_26 = str[84];
int v_p27 = str[85];
int v_n27 = str[86];
int pro_27 = str[87];
int v_p28 = str[88];
int v_n28 = str[89];
int pro_28 = str[90];
int v_p29 = str[91];
int v_n29 = str[92];
int pro_29 = str[93];
 
int v_p30 = str[96];
int v_n30 = str[97];
int pro_30 = str[98];
int v_p31 = str[99];
int v_n31 = str[100];
int pro_31 = str[101];
int v_p32 = str[102];
int v_n32 = str[103];
int pro_32 = str[104];
int v_p33 = str[105];
int v_n33 = str[106];
int pro_33 = str[107];
int v_p34 = str[108];
int v_n34 = str[109];
int pro_34 = str[110];
int v_p35 = str[111];
int v_n35 = str[112];
int pro_35 = str[113];
int v_p36 = str[114];
int v_n36 = str[115];
int pro_36 = str[116];
int v_p37 = str[117];
int v_n37 = str[118];
int pro_37 = str[119];
int v_p38 = str[120];
int v_n38 = str[121];
int pro_38 = str[122];
int v_p39 = str[123];
int v_n39 = str[124];
int pro_39 = str[125];
 
int v_p40 = str[128];
int v_n40 = str[129];
int pro_40 = str[130];
int v_p41 = str[131];
int v_n41 = str[132];
int pro_41 = str[133];
int v_p42 = str[134];
int v_n42 = str[135];
int pro_42 = str[136];
int v_p43 = str[137];
int v_n43 = str[138];
int pro_43 = str[139];
int sat = v_p43 || v_n43 || pro_43;
 	
//reasoner
/////////////////////////
int O_N1_0 = 0; O_N1_0 = v_p29;
int O_N1_1 = 0; O_N1_1 = v_p33;
int O_N1_2 = 0; O_N1_2 = v_p14;
int O_N1_3 = 0; O_N1_3 = v_p7;
int O_N1_4 = 0; O_N1_4 = v_p6;
int O_N1_5 = 0; O_N1_5 = v_p8;
int O_P1_0 = 0; O_P1_0 = v_n21 && v_n40 && v_n25 && v_n20 && v_n11;

int O_P1 = O_P1_0;
int O_N1 = O_N1_0 || O_N1_1 || O_N1_2 || O_N1_3 || O_N1_4 || O_N1_5;

int O_N2_0 = 0; O_N2_0 = v_p9;
int O_N2_1 = 0; O_N2_1 = v_p20;
int O_N2_2 = 0; O_N2_2 = v_p18;
int O_N2_3 = 0; O_N2_3 = v_p35;
int O_N2_4 = 0; O_N2_4 = v_p42;
int O_N2_5 = 0; O_N2_5 = v_p5;
int O_P2_0 = 0; O_P2_0 = v_n22 && v_n30 && v_n6 && v_n26 && v_n17;

int O_P2 = O_P2_0;
int O_N2 = O_N2_0 || O_N2_1 || O_N2_2 || O_N2_3 || O_N2_4 || O_N2_5;

int O_N3_0 = 0; O_N3_0 = v_p41;
int O_N3_1 = 0; O_N3_1 = v_p21;
int O_N3_2 = 0; O_N3_2 = v_p16;
int O_N3_3 = 0; O_N3_3 = v_p15;
int O_N3_4 = 0; O_N3_4 = v_p22;
int O_N3_5 = 0; O_N3_5 = v_p27;
int O_P3_0 = 0; O_P3_0 = v_n34 && v_n7 && v_n32 && v_n42 && v_n12;

int O_P3 = O_P3_0;
int O_N3 = O_N3_0 || O_N3_1 || O_N3_2 || O_N3_3 || O_N3_4 || O_N3_5;

int O_N4_0 = 0; O_N4_0 = v_p10;
int O_N4_1 = 0; O_N4_1 = v_p40;
int O_N4_2 = 0; O_N4_2 = v_p28;
int O_N4_3 = 0; O_N4_3 = v_p38;
int O_N4_4 = 0; O_N4_4 = v_p34;
int O_N4_5 = 0; O_N4_5 = v_p30;
int O_P4_0 = 0; O_P4_0 = v_n27 && v_n8 && v_n39 && v_n5 && v_n19;

int O_P4 = O_P4_0;
int O_N4 = O_N4_0 || O_N4_1 || O_N4_2 || O_N4_3 || O_N4_4 || O_N4_5;

int O_N5_0 = 0; O_N5_0 = v_p9;
int O_N5_1 = 0; O_N5_1 = v_p20;
int O_N5_2 = 0; O_N5_2 = v_p18;
int O_N5_3 = 0; O_N5_3 = v_p35;
int O_N5_4 = 0; O_N5_4 = v_p42;
int O_N5_5 = 0; O_N5_5 = v_p2;
int O_P5_0 = 0; O_P5_0 = v_n27 && v_n4 && v_n8 && v_n39 && v_n19;

int O_P5 = O_P5_0;
int O_N5 = O_N5_0 || O_N5_1 || O_N5_2 || O_N5_3 || O_N5_4 || O_N5_5;

int O_N6_0 = 0; O_N6_0 = v_p29;
int O_N6_1 = 0; O_N6_1 = v_p1;
int O_N6_2 = 0; O_N6_2 = v_p33;
int O_N6_3 = 0; O_N6_3 = v_p14;
int O_N6_4 = 0; O_N6_4 = v_p7;
int O_N6_5 = 0; O_N6_5 = v_p8;
int O_P6_0 = 0; O_P6_0 = v_n22 && v_n30 && v_n26 && v_n2 && v_n17;

int O_P6 = O_P6_0;
int O_N6 = O_N6_0 || O_N6_1 || O_N6_2 || O_N6_3 || O_N6_4 || O_N6_5;

int O_N7_0 = 0; O_N7_0 = v_p29;
int O_N7_1 = 0; O_N7_1 = v_p1;
int O_N7_2 = 0; O_N7_2 = v_p33;
int O_N7_3 = 0; O_N7_3 = v_p14;
int O_N7_4 = 0; O_N7_4 = v_p6;
int O_N7_5 = 0; O_N7_5 = v_p8;
int O_P7_0 = 0; O_P7_0 = v_n3 && v_n34 && v_n32 && v_n42 && v_n12;

int O_P7 = O_P7_0;
int O_N7 = O_N7_0 || O_N7_1 || O_N7_2 || O_N7_3 || O_N7_4 || O_N7_5;

int O_N8_0 = 0; O_N8_0 = v_p29;
int O_N8_1 = 0; O_N8_1 = v_p1;
int O_N8_2 = 0; O_N8_2 = v_p33;
int O_N8_3 = 0; O_N8_3 = v_p14;
int O_N8_4 = 0; O_N8_4 = v_p7;
int O_N8_5 = 0; O_N8_5 = v_p6;
int O_P8_0 = 0; O_P8_0 = v_n27 && v_n4 && v_n39 && v_n5 && v_n19;

int O_P8 = O_P8_0;
int O_N8 = O_N8_0 || O_N8_1 || O_N8_2 || O_N8_3 || O_N8_4 || O_N8_5;

int O_N9_0 = 0; O_N9_0 = v_p20;
int O_N9_1 = 0; O_N9_1 = v_p18;
int O_N9_2 = 0; O_N9_2 = v_p35;
int O_N9_3 = 0; O_N9_3 = v_p42;
int O_N9_4 = 0; O_N9_4 = v_p2;
int O_N9_5 = 0; O_N9_5 = v_p5;
int O_P9_0 = 0; O_P9_0 = v_n41 && v_n10 && v_n29 && v_n36 && v_n23;

int O_P9 = O_P9_0;
int O_N9 = O_N9_0 || O_N9_1 || O_N9_2 || O_N9_3 || O_N9_4 || O_N9_5;

int O_N10_0 = 0; O_N10_0 = v_p40;
int O_N10_1 = 0; O_N10_1 = v_p28;
int O_N10_2 = 0; O_N10_2 = v_p38;
int O_N10_3 = 0; O_N10_3 = v_p34;
int O_N10_4 = 0; O_N10_4 = v_p30;
int O_N10_5 = 0; O_N10_5 = v_p4;
int O_P10_0 = 0; O_P10_0 = v_n41 && v_n29 && v_n36 && v_n9 && v_n23;

int O_P10 = O_P10_0;
int O_N10 = O_N10_0 || O_N10_1 || O_N10_2 || O_N10_3 || O_N10_4 || O_N10_5;

int O_N11_0 = 0; O_N11_0 = v_p23;
int O_N11_1 = 0; O_N11_1 = v_p24;
int O_N11_2 = 0; O_N11_2 = v_p37;
int O_N11_3 = 0; O_N11_3 = v_p12;
int O_N11_4 = 0; O_N11_4 = v_p17;
int O_N11_5 = 0; O_N11_5 = v_p19;
int O_P11_0 = 0; O_P11_0 = v_n21 && v_n40 && v_n1 && v_n25 && v_n20;

int O_P11 = O_P11_0;
int O_N11 = O_N11_0 || O_N11_1 || O_N11_2 || O_N11_3 || O_N11_4 || O_N11_5;

int O_N12_0 = 0; O_N12_0 = v_p23;
int O_N12_1 = 0; O_N12_1 = v_p11;
int O_N12_2 = 0; O_N12_2 = v_p24;
int O_N12_3 = 0; O_N12_3 = v_p37;
int O_N12_4 = 0; O_N12_4 = v_p17;
int O_N12_5 = 0; O_N12_5 = v_p19;
int O_P12_0 = 0; O_P12_0 = v_n3 && v_n34 && v_n7 && v_n32 && v_n42;

int O_P12 = O_P12_0;
int O_N12 = O_N12_0 || O_N12_1 || O_N12_2 || O_N12_3 || O_N12_4 || O_N12_5;

int O_N13_0 = 0; O_N13_0 = v_p36;
int O_N13_1 = 0; O_N13_1 = v_p25;
int O_N13_2 = 0; O_N13_2 = v_p31;
int O_N13_3 = 0; O_N13_3 = v_p32;
int O_N13_4 = 0; O_N13_4 = v_p26;
int O_N13_5 = 0; O_N13_5 = v_p39;
int O_P13_0 = 0; O_P13_0 = v_n16 && v_n28 && v_n33 && v_n18 && v_n24;

int O_P13 = O_P13_0;
int O_N13 = O_N13_0 || O_N13_1 || O_N13_2 || O_N13_3 || O_N13_4 || O_N13_5;

int O_N14_0 = 0; O_N14_0 = v_p29;
int O_N14_1 = 0; O_N14_1 = v_p1;
int O_N14_2 = 0; O_N14_2 = v_p33;
int O_N14_3 = 0; O_N14_3 = v_p7;
int O_N14_4 = 0; O_N14_4 = v_p6;
int O_N14_5 = 0; O_N14_5 = v_p8;
int O_P14_0 = 0; O_P14_0 = v_n15 && v_n38 && v_n31 && v_n35 && v_n37;

int O_P14 = O_P14_0;
int O_N14 = O_N14_0 || O_N14_1 || O_N14_2 || O_N14_3 || O_N14_4 || O_N14_5;

int O_N15_0 = 0; O_N15_0 = v_p41;
int O_N15_1 = 0; O_N15_1 = v_p21;
int O_N15_2 = 0; O_N15_2 = v_p16;
int O_N15_3 = 0; O_N15_3 = v_p3;
int O_N15_4 = 0; O_N15_4 = v_p22;
int O_N15_5 = 0; O_N15_5 = v_p27;
int O_P15_0 = 0; O_P15_0 = v_n38 && v_n14 && v_n31 && v_n35 && v_n37;

int O_P15 = O_P15_0;
int O_N15 = O_N15_0 || O_N15_1 || O_N15_2 || O_N15_3 || O_N15_4 || O_N15_5;

int O_N16_0 = 0; O_N16_0 = v_p41;
int O_N16_1 = 0; O_N16_1 = v_p21;
int O_N16_2 = 0; O_N16_2 = v_p15;
int O_N16_3 = 0; O_N16_3 = v_p3;
int O_N16_4 = 0; O_N16_4 = v_p22;
int O_N16_5 = 0; O_N16_5 = v_p27;
int O_P16_0 = 0; O_P16_0 = v_n28 && v_n33 && v_n13 && v_n18 && v_n24;

int O_P16 = O_P16_0;
int O_N16 = O_N16_0 || O_N16_1 || O_N16_2 || O_N16_3 || O_N16_4 || O_N16_5;

int O_N17_0 = 0; O_N17_0 = v_p23;
int O_N17_1 = 0; O_N17_1 = v_p11;
int O_N17_2 = 0; O_N17_2 = v_p24;
int O_N17_3 = 0; O_N17_3 = v_p37;
int O_N17_4 = 0; O_N17_4 = v_p12;
int O_N17_5 = 0; O_N17_5 = v_p19;
int O_P17_0 = 0; O_P17_0 = v_n22 && v_n30 && v_n6 && v_n26 && v_n2;

int O_P17 = O_P17_0;
int O_N17 = O_N17_0 || O_N17_1 || O_N17_2 || O_N17_3 || O_N17_4 || O_N17_5;

int O_N18_0 = 0; O_N18_0 = v_p9;
int O_N18_1 = 0; O_N18_1 = v_p20;
int O_N18_2 = 0; O_N18_2 = v_p35;
int O_N18_3 = 0; O_N18_3 = v_p42;
int O_N18_4 = 0; O_N18_4 = v_p2;
int O_N18_5 = 0; O_N18_5 = v_p5;
int O_P18_0 = 0; O_P18_0 = v_n16 && v_n28 && v_n33 && v_n13 && v_n24;

int O_P18 = O_P18_0;
int O_N18 = O_N18_0 || O_N18_1 || O_N18_2 || O_N18_3 || O_N18_4 || O_N18_5;

int O_N19_0 = 0; O_N19_0 = v_p23;
int O_N19_1 = 0; O_N19_1 = v_p11;
int O_N19_2 = 0; O_N19_2 = v_p24;
int O_N19_3 = 0; O_N19_3 = v_p37;
int O_N19_4 = 0; O_N19_4 = v_p12;
int O_N19_5 = 0; O_N19_5 = v_p17;
int O_P19_0 = 0; O_P19_0 = v_n27 && v_n4 && v_n8 && v_n39 && v_n5;

int O_P19 = O_P19_0;
int O_N19 = O_N19_0 || O_N19_1 || O_N19_2 || O_N19_3 || O_N19_4 || O_N19_5;

int O_N20_0 = 0; O_N20_0 = v_p9;
int O_N20_1 = 0; O_N20_1 = v_p18;
int O_N20_2 = 0; O_N20_2 = v_p35;
int O_N20_3 = 0; O_N20_3 = v_p42;
int O_N20_4 = 0; O_N20_4 = v_p2;
int O_N20_5 = 0; O_N20_5 = v_p5;
int O_P20_0 = 0; O_P20_0 = v_n21 && v_n40 && v_n1 && v_n25 && v_n11;

int O_P20 = O_P20_0;
int O_N20 = O_N20_0 || O_N20_1 || O_N20_2 || O_N20_3 || O_N20_4 || O_N20_5;

int O_N21_0 = 0; O_N21_0 = v_p41;
int O_N21_1 = 0; O_N21_1 = v_p16;
int O_N21_2 = 0; O_N21_2 = v_p15;
int O_N21_3 = 0; O_N21_3 = v_p3;
int O_N21_4 = 0; O_N21_4 = v_p22;
int O_N21_5 = 0; O_N21_5 = v_p27;
int O_P21_0 = 0; O_P21_0 = v_n40 && v_n1 && v_n25 && v_n20 && v_n11;

int O_P21 = O_P21_0;
int O_N21 = O_N21_0 || O_N21_1 || O_N21_2 || O_N21_3 || O_N21_4 || O_N21_5;

int O_N22_0 = 0; O_N22_0 = v_p41;
int O_N22_1 = 0; O_N22_1 = v_p21;
int O_N22_2 = 0; O_N22_2 = v_p16;
int O_N22_3 = 0; O_N22_3 = v_p15;
int O_N22_4 = 0; O_N22_4 = v_p3;
int O_N22_5 = 0; O_N22_5 = v_p27;
int O_P22_0 = 0; O_P22_0 = v_n30 && v_n6 && v_n26 && v_n2 && v_n17;

int O_P22 = O_P22_0;
int O_N22 = O_N22_0 || O_N22_1 || O_N22_2 || O_N22_3 || O_N22_4 || O_N22_5;

int O_N23_0 = 0; O_N23_0 = v_p11;
int O_N23_1 = 0; O_N23_1 = v_p24;
int O_N23_2 = 0; O_N23_2 = v_p37;
int O_N23_3 = 0; O_N23_3 = v_p12;
int O_N23_4 = 0; O_N23_4 = v_p17;
int O_N23_5 = 0; O_N23_5 = v_p19;
int O_P23_0 = 0; O_P23_0 = v_n41 && v_n10 && v_n29 && v_n36 && v_n9;

int O_P23 = O_P23_0;
int O_N23 = O_N23_0 || O_N23_1 || O_N23_2 || O_N23_3 || O_N23_4 || O_N23_5;

int O_N24_0 = 0; O_N24_0 = v_p23;
int O_N24_1 = 0; O_N24_1 = v_p11;
int O_N24_2 = 0; O_N24_2 = v_p37;
int O_N24_3 = 0; O_N24_3 = v_p12;
int O_N24_4 = 0; O_N24_4 = v_p17;
int O_N24_5 = 0; O_N24_5 = v_p19;
int O_P24_0 = 0; O_P24_0 = v_n16 && v_n28 && v_n33 && v_n13 && v_n18;

int O_P24 = O_P24_0;
int O_N24 = O_N24_0 || O_N24_1 || O_N24_2 || O_N24_3 || O_N24_4 || O_N24_5;

int O_N25_0 = 0; O_N25_0 = v_p36;
int O_N25_1 = 0; O_N25_1 = v_p13;
int O_N25_2 = 0; O_N25_2 = v_p31;
int O_N25_3 = 0; O_N25_3 = v_p32;
int O_N25_4 = 0; O_N25_4 = v_p26;
int O_N25_5 = 0; O_N25_5 = v_p39;
int O_P25_0 = 0; O_P25_0 = v_n21 && v_n40 && v_n1 && v_n20 && v_n11;

int O_P25 = O_P25_0;
int O_N25 = O_N25_0 || O_N25_1 || O_N25_2 || O_N25_3 || O_N25_4 || O_N25_5;

int O_N26_0 = 0; O_N26_0 = v_p36;
int O_N26_1 = 0; O_N26_1 = v_p25;
int O_N26_2 = 0; O_N26_2 = v_p13;
int O_N26_3 = 0; O_N26_3 = v_p31;
int O_N26_4 = 0; O_N26_4 = v_p32;
int O_N26_5 = 0; O_N26_5 = v_p39;
int O_P26_0 = 0; O_P26_0 = v_n22 && v_n30 && v_n6 && v_n2 && v_n17;

int O_P26 = O_P26_0;
int O_N26 = O_N26_0 || O_N26_1 || O_N26_2 || O_N26_3 || O_N26_4 || O_N26_5;

int O_N27_0 = 0; O_N27_0 = v_p41;
int O_N27_1 = 0; O_N27_1 = v_p21;
int O_N27_2 = 0; O_N27_2 = v_p16;
int O_N27_3 = 0; O_N27_3 = v_p15;
int O_N27_4 = 0; O_N27_4 = v_p3;
int O_N27_5 = 0; O_N27_5 = v_p22;
int O_P27_0 = 0; O_P27_0 = v_n4 && v_n8 && v_n39 && v_n5 && v_n19;

int O_P27 = O_P27_0;
int O_N27 = O_N27_0 || O_N27_1 || O_N27_2 || O_N27_3 || O_N27_4 || O_N27_5;

int O_N28_0 = 0; O_N28_0 = v_p10;
int O_N28_1 = 0; O_N28_1 = v_p40;
int O_N28_2 = 0; O_N28_2 = v_p38;
int O_N28_3 = 0; O_N28_3 = v_p34;
int O_N28_4 = 0; O_N28_4 = v_p30;
int O_N28_5 = 0; O_N28_5 = v_p4;
int O_P28_0 = 0; O_P28_0 = v_n16 && v_n33 && v_n13 && v_n18 && v_n24;

int O_P28 = O_P28_0;
int O_N28 = O_N28_0 || O_N28_1 || O_N28_2 || O_N28_3 || O_N28_4 || O_N28_5;

int O_N29_0 = 0; O_N29_0 = v_p1;
int O_N29_1 = 0; O_N29_1 = v_p33;
int O_N29_2 = 0; O_N29_2 = v_p14;
int O_N29_3 = 0; O_N29_3 = v_p7;
int O_N29_4 = 0; O_N29_4 = v_p6;
int O_N29_5 = 0; O_N29_5 = v_p8;
int O_P29_0 = 0; O_P29_0 = v_n41 && v_n10 && v_n36 && v_n9 && v_n23;

int O_P29 = O_P29_0;
int O_N29 = O_N29_0 || O_N29_1 || O_N29_2 || O_N29_3 || O_N29_4 || O_N29_5;

int O_N30_0 = 0; O_N30_0 = v_p10;
int O_N30_1 = 0; O_N30_1 = v_p40;
int O_N30_2 = 0; O_N30_2 = v_p28;
int O_N30_3 = 0; O_N30_3 = v_p38;
int O_N30_4 = 0; O_N30_4 = v_p34;
int O_N30_5 = 0; O_N30_5 = v_p4;
int O_P30_0 = 0; O_P30_0 = v_n22 && v_n6 && v_n26 && v_n2 && v_n17;

int O_P30 = O_P30_0;
int O_N30 = O_N30_0 || O_N30_1 || O_N30_2 || O_N30_3 || O_N30_4 || O_N30_5;

int O_N31_0 = 0; O_N31_0 = v_p36;
int O_N31_1 = 0; O_N31_1 = v_p25;
int O_N31_2 = 0; O_N31_2 = v_p13;
int O_N31_3 = 0; O_N31_3 = v_p32;
int O_N31_4 = 0; O_N31_4 = v_p26;
int O_N31_5 = 0; O_N31_5 = v_p39;
int O_P31_0 = 0; O_P31_0 = v_n15 && v_n38 && v_n14 && v_n35 && v_n37;

int O_P31 = O_P31_0;
int O_N31 = O_N31_0 || O_N31_1 || O_N31_2 || O_N31_3 || O_N31_4 || O_N31_5;

int O_N32_0 = 0; O_N32_0 = v_p36;
int O_N32_1 = 0; O_N32_1 = v_p25;
int O_N32_2 = 0; O_N32_2 = v_p13;
int O_N32_3 = 0; O_N32_3 = v_p31;
int O_N32_4 = 0; O_N32_4 = v_p26;
int O_N32_5 = 0; O_N32_5 = v_p39;
int O_P32_0 = 0; O_P32_0 = v_n3 && v_n34 && v_n7 && v_n42 && v_n12;

int O_P32 = O_P32_0;
int O_N32 = O_N32_0 || O_N32_1 || O_N32_2 || O_N32_3 || O_N32_4 || O_N32_5;

int O_N33_0 = 0; O_N33_0 = v_p29;
int O_N33_1 = 0; O_N33_1 = v_p1;
int O_N33_2 = 0; O_N33_2 = v_p14;
int O_N33_3 = 0; O_N33_3 = v_p7;
int O_N33_4 = 0; O_N33_4 = v_p6;
int O_N33_5 = 0; O_N33_5 = v_p8;
int O_P33_0 = 0; O_P33_0 = v_n16 && v_n28 && v_n13 && v_n18 && v_n24;

int O_P33 = O_P33_0;
int O_N33 = O_N33_0 || O_N33_1 || O_N33_2 || O_N33_3 || O_N33_4 || O_N33_5;

int O_N34_0 = 0; O_N34_0 = v_p10;
int O_N34_1 = 0; O_N34_1 = v_p40;
int O_N34_2 = 0; O_N34_2 = v_p28;
int O_N34_3 = 0; O_N34_3 = v_p38;
int O_N34_4 = 0; O_N34_4 = v_p30;
int O_N34_5 = 0; O_N34_5 = v_p4;
int O_P34_0 = 0; O_P34_0 = v_n3 && v_n7 && v_n32 && v_n42 && v_n12;

int O_P34 = O_P34_0;
int O_N34 = O_N34_0 || O_N34_1 || O_N34_2 || O_N34_3 || O_N34_4 || O_N34_5;

int O_N35_0 = 0; O_N35_0 = v_p9;
int O_N35_1 = 0; O_N35_1 = v_p20;
int O_N35_2 = 0; O_N35_2 = v_p18;
int O_N35_3 = 0; O_N35_3 = v_p42;
int O_N35_4 = 0; O_N35_4 = v_p2;
int O_N35_5 = 0; O_N35_5 = v_p5;
int O_P35_0 = 0; O_P35_0 = v_n15 && v_n38 && v_n14 && v_n31 && v_n37;

int O_P35 = O_P35_0;
int O_N35 = O_N35_0 || O_N35_1 || O_N35_2 || O_N35_3 || O_N35_4 || O_N35_5;

int O_N36_0 = 0; O_N36_0 = v_p25;
int O_N36_1 = 0; O_N36_1 = v_p13;
int O_N36_2 = 0; O_N36_2 = v_p31;
int O_N36_3 = 0; O_N36_3 = v_p32;
int O_N36_4 = 0; O_N36_4 = v_p26;
int O_N36_5 = 0; O_N36_5 = v_p39;
int O_P36_0 = 0; O_P36_0 = v_n41 && v_n10 && v_n29 && v_n9 && v_n23;

int O_P36 = O_P36_0;
int O_N36 = O_N36_0 || O_N36_1 || O_N36_2 || O_N36_3 || O_N36_4 || O_N36_5;

int O_N37_0 = 0; O_N37_0 = v_p23;
int O_N37_1 = 0; O_N37_1 = v_p11;
int O_N37_2 = 0; O_N37_2 = v_p24;
int O_N37_3 = 0; O_N37_3 = v_p12;
int O_N37_4 = 0; O_N37_4 = v_p17;
int O_N37_5 = 0; O_N37_5 = v_p19;
int O_P37_0 = 0; O_P37_0 = v_n15 && v_n38 && v_n14 && v_n31 && v_n35;

int O_P37 = O_P37_0;
int O_N37 = O_N37_0 || O_N37_1 || O_N37_2 || O_N37_3 || O_N37_4 || O_N37_5;

int O_N38_0 = 0; O_N38_0 = v_p10;
int O_N38_1 = 0; O_N38_1 = v_p40;
int O_N38_2 = 0; O_N38_2 = v_p28;
int O_N38_3 = 0; O_N38_3 = v_p34;
int O_N38_4 = 0; O_N38_4 = v_p30;
int O_N38_5 = 0; O_N38_5 = v_p4;
int O_P38_0 = 0; O_P38_0 = v_n15 && v_n14 && v_n31 && v_n35 && v_n37;

int O_P38 = O_P38_0;
int O_N38 = O_N38_0 || O_N38_1 || O_N38_2 || O_N38_3 || O_N38_4 || O_N38_5;

int O_N39_0 = 0; O_N39_0 = v_p36;
int O_N39_1 = 0; O_N39_1 = v_p25;
int O_N39_2 = 0; O_N39_2 = v_p13;
int O_N39_3 = 0; O_N39_3 = v_p31;
int O_N39_4 = 0; O_N39_4 = v_p32;
int O_N39_5 = 0; O_N39_5 = v_p26;
int O_P39_0 = 0; O_P39_0 = v_n27 && v_n4 && v_n8 && v_n5 && v_n19;

int O_P39 = O_P39_0;
int O_N39 = O_N39_0 || O_N39_1 || O_N39_2 || O_N39_3 || O_N39_4 || O_N39_5;

int O_N40_0 = 0; O_N40_0 = v_p10;
int O_N40_1 = 0; O_N40_1 = v_p28;
int O_N40_2 = 0; O_N40_2 = v_p38;
int O_N40_3 = 0; O_N40_3 = v_p34;
int O_N40_4 = 0; O_N40_4 = v_p30;
int O_N40_5 = 0; O_N40_5 = v_p4;
int O_P40_0 = 0; O_P40_0 = v_n21 && v_n1 && v_n25 && v_n20 && v_n11;

int O_P40 = O_P40_0;
int O_N40 = O_N40_0 || O_N40_1 || O_N40_2 || O_N40_3 || O_N40_4 || O_N40_5;

int O_N41_0 = 0; O_N41_0 = v_p21;
int O_N41_1 = 0; O_N41_1 = v_p16;
int O_N41_2 = 0; O_N41_2 = v_p15;
int O_N41_3 = 0; O_N41_3 = v_p3;
int O_N41_4 = 0; O_N41_4 = v_p22;
int O_N41_5 = 0; O_N41_5 = v_p27;
int O_P41_0 = 0; O_P41_0 = v_n10 && v_n29 && v_n36 && v_n9 && v_n23;

int O_P41 = O_P41_0;
int O_N41 = O_N41_0 || O_N41_1 || O_N41_2 || O_N41_3 || O_N41_4 || O_N41_5;

int O_N42_0 = 0; O_N42_0 = v_p9;
int O_N42_1 = 0; O_N42_1 = v_p20;
int O_N42_2 = 0; O_N42_2 = v_p18;
int O_N42_3 = 0; O_N42_3 = v_p35;
int O_N42_4 = 0; O_N42_4 = v_p2;
int O_N42_5 = 0; O_N42_5 = v_p5;
int O_P42_0 = 0; O_P42_0 = v_n3 && v_n34 && v_n7 && v_n32 && v_n12;

int O_P42 = O_P42_0;
int O_N42 = O_N42_0 || O_N42_1 || O_N42_2 || O_N42_3 || O_N42_4 || O_N42_5;

int conflict = 0;
conflict = (O_P1 && O_N1) || (O_P2 && O_N2) || (O_P3 && O_N3) || (O_P4 && O_N4) || (O_P5 && O_N5) || (O_P6 && O_N6) || (O_P7 && O_N7) || (O_P8 && O_N8) || (O_P9 && O_N9) || (O_P10 && O_N10) || (O_P11 && O_N11) || (O_P12 && O_N12) || (O_P13 && O_N13) || (O_P14 && O_N14) || (O_P15 && O_N15) || (O_P16 && O_N16) || (O_P17 && O_N17) || (O_P18 && O_N18) || (O_P19 && O_N19) || (O_P20 && O_N20) || (O_P21 && O_N21) || (O_P22 && O_N22) || (O_P23 && O_N23) || (O_P24 && O_N24) || (O_P25 && O_N25) || (O_P26 && O_N26) || (O_P27 && O_N27) || (O_P28 && O_N28) || (O_P29 && O_N29) || (O_P30 && O_N30) || (O_P31 && O_N31) || (O_P32 && O_N32) || (O_P33 && O_N33) || (O_P34 && O_N34) || (O_P35 && O_N35) || (O_P36 && O_N36) || (O_P37 && O_N37) || (O_P38 && O_N38) || (O_P39 && O_N39) || (O_P40 && O_N40) || (O_P41 && O_N41) || (O_P42 && O_N42);  
/////////////////////////
 	
int control_1 = control[0];  int control_2 = control[1];  int control_3 = control[2];  int control_4 = control[3];  int control_5 = control[4];    
int control_6 = control[5];  int control_7 = control[6];  int control_8 = control[7];  int control_9 = control[8];  int control_10 = control[9];    
int control_11 = control[10];  int control_12 = control[11];  int control_13 = control[12];  int control_14 = control[13];  int control_15 = control[14];    
int control_16 = control[15];  int control_17 = control[16];  int control_18 = control[17];  int control_19 = control[18];  int control_20 = control[19];    
int control_21 = control[20];  int control_22 = control[21];  int control_23 = control[22];  int control_24 = control[23];  int control_25 = control[24];    
int control_26 = control[25];  int control_27 = control[26];  int control_28 = control[27];  int control_29 = control[28];  int control_30 = control[29];    
int control_31 = control[30];  int control_32 = control[31];  int control_33 = control[32];  int control_34 = control[33];  int control_35 = control[34];    
int control_36 = control[35];  int control_37 = control[36];  int control_38 = control[37];  int control_39 = control[38];  int control_40 = control[39];    
int control_41 = control[40];  int control_42 = control[41];   	
if (pro_1 == 1)
    control_1 = 1 - control_1;
if (pro_2 == 1)
    control_2 = 1 - control_2;
if (pro_3 == 1)
    control_3 = 1 - control_3;
if (pro_4 == 1)
    control_4 = 1 - control_4;
if (pro_5 == 1)
    control_5 = 1 - control_5;
if (pro_6 == 1)
    control_6 = 1 - control_6;
if (pro_7 == 1)
    control_7 = 1 - control_7;
if (pro_8 == 1)
    control_8 = 1 - control_8;
if (pro_9 == 1)
    control_9 = 1 - control_9;
if (pro_10 == 1)
    control_10 = 1 - control_10;
if (pro_11 == 1)
    control_11 = 1 - control_11;
if (pro_12 == 1)
    control_12 = 1 - control_12;
if (pro_13 == 1)
    control_13 = 1 - control_13;
if (pro_14 == 1)
    control_14 = 1 - control_14;
if (pro_15 == 1)
    control_15 = 1 - control_15;
if (pro_16 == 1)
    control_16 = 1 - control_16;
if (pro_17 == 1)
    control_17 = 1 - control_17;
if (pro_18 == 1)
    control_18 = 1 - control_18;
if (pro_19 == 1)
    control_19 = 1 - control_19;
if (pro_20 == 1)
    control_20 = 1 - control_20;
if (pro_21 == 1)
    control_21 = 1 - control_21;
if (pro_22 == 1)
    control_22 = 1 - control_22;
if (pro_23 == 1)
    control_23 = 1 - control_23;
if (pro_24 == 1)
    control_24 = 1 - control_24;
if (pro_25 == 1)
    control_25 = 1 - control_25;
if (pro_26 == 1)
    control_26 = 1 - control_26;
if (pro_27 == 1)
    control_27 = 1 - control_27;
if (pro_28 == 1)
    control_28 = 1 - control_28;
if (pro_29 == 1)
    control_29 = 1 - control_29;
if (pro_30 == 1)
    control_30 = 1 - control_30;
if (pro_31 == 1)
    control_31 = 1 - control_31;
if (pro_32 == 1)
    control_32 = 1 - control_32;
if (pro_33 == 1)
    control_33 = 1 - control_33;
if (pro_34 == 1)
    control_34 = 1 - control_34;
if (pro_35 == 1)
    control_35 = 1 - control_35;
if (pro_36 == 1)
    control_36 = 1 - control_36;
if (pro_37 == 1)
    control_37 = 1 - control_37;
if (pro_38 == 1)
    control_38 = 1 - control_38;
if (pro_39 == 1)
    control_39 = 1 - control_39;
if (pro_40 == 1)
    control_40 = 1 - control_40;
if (pro_41 == 1)
    control_41 = 1 - control_41;
if (pro_42 == 1)
    control_42 = 1 - control_42;
 	
///////////////////////////////////////
int b0[32] = {0};
int b1[32] = {0};
int b2[32] = {0};
int b3[32] = {0};
int b4[32] = {0};
int b5[32] = {0};
int b6[32] = {0};
int b7[32] = {0};
int b8[32] = {0};
int b9[32] = {0};
 	
b0[31] = 1;  //start
b0[30] = conflict;
b0[29] = slv_reg[0]; 
b0[28] = slv_reg[1]; 
b0[27] = slv_reg[2]; 
b0[26] = slv_reg[3]; 
b0[25] = slv_reg[4];  
b0[24] = slv_reg[5]; 
b0[23] = slv_reg[6]; 
b0[22] = slv_reg[7];  
b0[21] = slv_reg[8]; 
b0[20] = slv_reg[9]; 
b0[19] = 0; b0[18] = 1; b0[17] = 0; b0[16] = 1; b0[15] = 0; b0[14] = 1;  	
 	
b1[31] = O_P1;  b1[30] = O_N1; b1[29] = control_1; 
b1[28] = O_P2;  b1[27] = O_N2; b1[26] = control_2; 
b1[25] = O_P3;  b1[24] = O_N3; b1[23] = control_3; 
b1[22] = O_P4;  b1[21] = O_N4; b1[20] = control_4; 
b1[19] = O_P5;  b1[18] = O_N5; b1[17] = control_5; 
b1[16] = O_P6;  b1[15] = O_N6; b1[14] = control_6; 
b1[13] = O_P7;  b1[12] = O_N7; b1[11] = control_7; 
b1[10] = O_P8;  b1[9] = O_N8; b1[8] = control_8; 
b1[7] = O_P9;  b1[6] = O_N9; b1[5] = control_9; 
b1[4] = O_P10;  b1[3] = O_N10; b1[2] = control_10; 
 	
b2[31] = O_P11;  b2[30] = O_N11; b2[29] = control_11; 
b2[28] = O_P12;  b2[27] = O_N12; b2[26] = control_12; 
b2[25] = O_P13;  b2[24] = O_N13; b2[23] = control_13; 
b2[22] = O_P14;  b2[21] = O_N14; b2[20] = control_14; 
b2[19] = O_P15;  b2[18] = O_N15; b2[17] = control_15; 
b2[16] = O_P16;  b2[15] = O_N16; b2[14] = control_16; 
b2[13] = O_P17;  b2[12] = O_N17; b2[11] = control_17; 
b2[10] = O_P18;  b2[9] = O_N18; b2[8] = control_18; 
b2[7] = O_P19;  b2[6] = O_N19; b2[5] = control_19; 
b2[4] = O_P20;  b2[3] = O_N20; b2[2] = control_20; 
 	
b3[31] = O_P21;  b3[30] = O_N21; b3[29] = control_21; 
b3[28] = O_P22;  b3[27] = O_N22; b3[26] = control_22; 
b3[25] = O_P23;  b3[24] = O_N23; b3[23] = control_23; 
b3[22] = O_P24;  b3[21] = O_N24; b3[20] = control_24; 
b3[19] = O_P25;  b3[18] = O_N25; b3[17] = control_25; 
b3[16] = O_P26;  b3[15] = O_N26; b3[14] = control_26; 
b3[13] = O_P27;  b3[12] = O_N27; b3[11] = control_27; 
b3[10] = O_P28;  b3[9] = O_N28; b3[8] = control_28; 
b3[7] = O_P29;  b3[6] = O_N29; b3[5] = control_29; 
b3[4] = O_P30;  b3[3] = O_N30; b3[2] = control_30; 
 	
b4[31] = O_P31;  b4[30] = O_N31; b4[29] = control_31; 
b4[28] = O_P32;  b4[27] = O_N32; b4[26] = control_32; 
b4[25] = O_P33;  b4[24] = O_N33; b4[23] = control_33; 
b4[22] = O_P34;  b4[21] = O_N34; b4[20] = control_34; 
b4[19] = O_P35;  b4[18] = O_N35; b4[17] = control_35; 
b4[16] = O_P36;  b4[15] = O_N36; b4[14] = control_36; 
b4[13] = O_P37;  b4[12] = O_N37; b4[11] = control_37; 
b4[10] = O_P38;  b4[9] = O_N38; b4[8] = control_38; 
b4[7] = O_P39;  b4[6] = O_N39; b4[5] = control_39; 
b4[4] = O_P40;  b4[3] = O_N40; b4[2] = control_40; 
 	
b5[31] = O_P41;  b5[30] = O_N41; b5[29] = control_41; 
b5[28] = O_P42;  b5[27] = O_N42; b5[26] = control_42; 
 	
 	
 	
 	
b8[31] = slv_reg[10]; b8[30] = slv_reg[11];b8[29] = slv_reg[12]; 
b8[28] = slv_reg[13]; b8[27] = slv_reg[14]; b8[26] = slv_reg[15]; 
b8[25] = slv_reg[16]; b8[24] = slv_reg[17]; b8[23] = slv_reg[18]; 
b8[22] = slv_reg[19]; b8[21] = slv_reg[20]; b8[20] = slv_reg[21];  
b8[19] = slv_reg[22]; b8[18] = slv_reg[23]; b8[17] = slv_reg[24]; 
b8[16] = slv_reg[25]; b8[15] = slv_reg[26]; b8[14] = slv_reg[27]; 
b8[13] = slv_reg[28]; b8[12] = slv_reg[29]; b8[11] = slv_reg[30]; 
b8[10] = slv_reg[31]; b8[9] = slv_reg[32]; b8[8] = slv_reg[33]; 
b8[7] = slv_reg[34]; b8[6] = slv_reg[35]; b8[5] = slv_reg[36];  
b8[4] = slv_reg[37]; b8[3] = slv_reg[38]; b8[2] = slv_reg[39]; 

b9[31] = slv_reg[40]; b9[30] = slv_reg[41];b9[29] = slv_reg[42]; 
b9[28] = slv_reg[43]; b9[27] = slv_reg[44]; b9[26] = slv_reg[45]; 
b9[25] = slv_reg[46]; b9[24] = slv_reg[47]; b9[23] = slv_reg[48]; 
b9[22] = slv_reg[49]; b9[21] = slv_reg[50]; b9[20] = slv_reg[51];  
b9[19] = slv_reg[52]; b9[18] = slv_reg[53]; b9[17] = slv_reg[54]; 
b9[16] = slv_reg[55]; b9[15] = slv_reg[56]; b9[14] = slv_reg[57]; 
b9[13] = slv_reg[58]; b9[12] = slv_reg[59]; b9[11] = slv_reg[60]; 
b9[10] = slv_reg[61]; b9[9] = slv_reg[62]; b9[8] = slv_reg[63]; 
b9[7] = slv_reg[64]; b9[6] = slv_reg[65]; b9[5] = slv_reg[66]; 
b9[4] = 1;
 	
int data_need[54]={0};
 
data_need[0] = sat;
data_need[1] = unsat;
data_need[2] = binary_Decimal(b0,32);
data_need[3] = binary_Decimal(b1,32);
data_need[4] = binary_Decimal(b2,32);
data_need[5] = binary_Decimal(b3,32);
data_need[6] = binary_Decimal(b4,32);
data_need[7] = binary_Decimal(b5,32);
data_need[8] = binary_Decimal(b6,32);
data_need[9] = binary_Decimal(b7,32);
data_need[10] = binary_Decimal(b8,32);
data_need[11] = binary_Decimal(b9,32);
 	
data_need[12] = control_1; data_need[13] = control_2; data_need[14] = control_3; data_need[15] = control_4; data_need[16] = control_5;   
data_need[17] = control_6; data_need[18] = control_7; data_need[19] = control_8; data_need[20] = control_9; data_need[21] = control_10;   
data_need[22] = control_11; data_need[23] = control_12; data_need[24] = control_13; data_need[25] = control_14; data_need[26] = control_15;   
data_need[27] = control_16; data_need[28] = control_17; data_need[29] = control_18; data_need[30] = control_19; data_need[31] = control_20;   
data_need[32] = control_21; data_need[33] = control_22; data_need[34] = control_23; data_need[35] = control_24; data_need[36] = control_25;   
data_need[37] = control_26; data_need[38] = control_27; data_need[39] = control_28; data_need[40] = control_29; data_need[41] = control_30;   
data_need[42] = control_31; data_need[43] = control_32; data_need[44] = control_33; data_need[45] = control_34; data_need[46] = control_35;   
data_need[47] = control_36; data_need[48] = control_37; data_need[49] = control_38; data_need[50] = control_39; data_need[51] = control_40;   
data_need[52] = control_41; data_need[53] = control_42;  	
return data_need;

}
 	
///////////////////////////////////////////////////
int binary_Decimal(int *a,int len)
{
    int i,sum=0,m,j;

   	if(len<=32)
    {

        for(i=0;i<len;i++)
        {
            m=1;

            if(a[i]==1)
            {
                for(j=1;j<=len-i-1;j++)
                m*=2;
                sum+=m;
            }
        }

    return sum;

    }
}

int *conversion(int decimalNumber)//shijinzhi -> erjinzhi
{
    int i=0;
	memset(tar,0,sizeof(tar));
	sprintf(src,"%d",decimalNumber);
	//if(isZero(src)) //printf("\n");
	//	else {
			ToBin(src);
	//	}
	//	//printf("");
		cha=0;
		return tar;
}

int lenof(char tar[])
{
	int i=0;
	int len=0;
	while(tar[i]!='\0') {
		i++;
		len++;
	}
	return len;
}

int isZero(char src[])
{
	int i;
	for ( i=lenof(src)-1; i>=0; i--) {
		if (src[i] != '0') {
			return false;
		}
	}
	return true;
}

void ToBin(char src[])
{
	int i=0;
	int j=0;
	int len=lenof(src);
	while(!isZero(src)) {
		int temp=0;
		int yu=0;
		for(i=0; i<len; i++) {
			if(i==len-1) {
				temp = (yu*10 + (src[i]-'0'))/2 ;
				yu=(yu*10 + (src[i]-'0'))%2;
				src[i]= '0'+temp;
				tar[j]=yu;
				j++;
			} else {
				temp = (yu*10 + (src[i]-'0'))/2 ;
				yu=(yu*10 + (src[i]-'0'))%2;
				src[i]='0'+temp;
			}
		}
	}
	cha=j;
}


