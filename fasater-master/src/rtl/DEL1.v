//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: DEL1
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The DEL1 is a delay unit.
// Revision: V2.0
////////////////////////////////////////////////////////////////////////////////// 
module DEL1(in_R, out_R, fire);      
       
     input in_R;     
     output fire, out_R;  
       
   telescope telescope1(in_R,  out_R, fire);    
  endmodule  
       
       
