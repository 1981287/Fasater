//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: assignUnit
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The assignUnit module includes the assignment module and the initialization module.
// Revision: V2.0
//////////////////////////////////////////////////////////////////////////////////
module assignUnit(evaluateFire,complementFire,assignFire,vimp_p,vimp_n,resetFire,vout_p,vout_n,back,randomDigit);      
 
    input  evaluateFire,complementFire,assignFire,resetFire,vimp_p,vimp_n,randomDigit;     
    output  vout_p,vout_n,back;  
    wire  evaluate,complement;     
       
    (*DONT_TOUCH="yes"*)  assignment assignment(evaluate,complement,vimp_p,vimp_n,assignFire,vout_p,vout_n,back,randomDigit);   
    (*DONT_TOUCH="yes"*)  init init(evaluateFire,complementFire,resetFire,evaluate,complement);   
       
endmodule      
       
       
