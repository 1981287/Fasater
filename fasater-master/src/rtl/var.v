//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: var
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The var includes the biChainNode module and the assignUnit module.
// Revision: V2.0
////////////////////////////////////////////////////////////////////////////////// 
     module var(vimp_p,vimp_n,conflict,in_top,in_bottom,control,randomDigit,out_top,out_bottom,vout_p,vout_n,process);     
     input vimp_p,vimp_n,conflict,in_top,in_bottom,control,randomDigit;
     output out_top,out_bottom,vout_p,vout_n,process; 
         
     wire evaluateFire,complementFire,assignFire,resetFire,back;   
         
     (*DONT_TOUCH="yes"*)   biChainNode biChainNode(.in_top(in_top),.in_bottom(in_bottom),.conflict(conflict),.back(back),.evaluateFire(evaluateFire),.complementFire(complementFire),.assignFire(assignFire),.resetFire(resetFire),.out_top(out_top),.out_bottom(out_bottom),.control(control),.process(process));    
     (*DONT_TOUCH="yes"*)   assignUnit assignUnit(.evaluateFire(evaluateFire),.complementFire(complementFire),.assignFire(assignFire),.vimp_p(vimp_p),.vimp_n(vimp_n),.resetFire(resetFire),.vout_p(vout_p),.vout_n(vout_n),.back(back),.randomDigit(randomDigit));  
         
     endmodule    
         
         
