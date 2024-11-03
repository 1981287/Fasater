 //////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: DEL4
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The DEL4 is a delay unit.
// Revision: V2.0
////////////////////////////////////////////////////////////////////////////////// 
 module DEL4(in_R, out_R, fire);      
       
     input in_R;     
     output fire, out_R;  
       
     wire delay1,delay2,delay3;  
     wire fire1,fire2,fire3;  
       
   telescope telescope1(in_R,    delay1, fire1);    
   telescope telescope2(delay1,  delay2, fire2);    
   telescope telescope3(delay2,  delay3, fire3);    
   telescope telescope4(delay3,  out_R,  fire);    
  endmodule  
       
       
