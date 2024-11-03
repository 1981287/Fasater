 //////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: biChainNode
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The biChainNode includes micropipeline and conflict processor.
// Revision: V2.0
//////////////////////////////////////////////////////////////////////////////////
 module biChainNode(
     in_top,in_bottom,conflict,back,control,evaluateFire,complementFire,assignFire,resetFire,out_top,out_bottom,process
     ); 
      
 input in_top,in_bottom,conflict,back,control;    
 output evaluateFire,complementFire,assignFire,resetFire,out_top,out_bottom,process;                                                        
 
 wire outR_0,outR_1,outR_2,outR_3,outR_4,delay0_outR,delay1_outR,delay2_outR,inR;       
 wire resetFire,assignFire,processFire,delay0Fire,delay1Fire,delay2Fire;      
 wire reassign,process;
 
 assign	inR = outR_0 ^ outR_1;      
       
(*DONT_TOUCH="yes"*)  ClickJoin controller0(.in_R(in_top),      .out_R(outR_0),      .fire(evaluateFire));
(*DONT_TOUCH="yes"*)  ClickJoin controller1(.in_R(reassign),    .out_R(outR_1),      .fire(complementFire));
(*DONT_TOUCH="yes"*)  DEL1      delay0(.in_R(inR),              .out_R(delay0_outR), .fire(delay0Fire));
(*DONT_TOUCH="yes"*)  ClickJoin controller2(.in_R(delay0_outR), .out_R(outR_2),      .fire(assignFire));
(*DONT_TOUCH="yes"*)  DEL4  	   delay1(.in_R(outR_2), 	     .out_R(delay1_outR), .fire(delay1Fire));
(*DONT_TOUCH="yes"*)  ClickJoin controller3(.in_R(delay1_outR), .out_R(outR_3),      .fire(resetFire));
(*DONT_TOUCH="yes"*)  DEL4  	   delay2(.in_R(outR_3), 	     .out_R(delay2_outR), .fire(delay2Fire));
(*DONT_TOUCH="yes"*)  ClickJoin controller4(.in_R(delay2_outR), .out_R(outR_4),		  .fire(processFire));
             
(*DONT_TOUCH="yes"*)  conflictProcessor conflictProcessor(.control(control),.processFire(processFire),.back(back),.conflict(conflict),.in_bottom(in_bottom),.out_bottom(out_bottom),.out_top(out_top),.reassign(reassign),.process(process));        
       
endmodule       
       
       
       
       
