//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: conflictProcessor
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The conflictProcessor determine the direction of circuit operation based on conflict signal and back signal.
// Revision: V2.0
//////////////////////////////////////////////////////////////////////////////////
module conflictProcessor(       
 	 back,conflict,in_bottom,processFire,control,out_bottom,out_top,reassign,process
     );   
        
 	input  back,conflict,in_bottom,processFire,control;
    output  out_bottom,out_top,reassign,process;    

    reg  out_top = 0;
    reg  bottom = 0;   
    reg  out_bottom = 0;  
    reg  process = 0;
    
    wire outBottomFire,bottomFire,outTopFire,delay1Fire,delay2Fire,delay3Fire,delay4Fire,controlFire,flipFire;
    wire outR_1,outR_2,outR_3,outR_4,outR_5,outR_6,outR_7,outR_8;

    assign flipFire = processFire | outBottomFire | bottomFire | outTopFire;
 
    always@ ( posedge flipFire)
     begin     
        process = ~process; 
     end

    (*DONT_TOUCH="yes"*)  ClickJoin controller1(.in_R(out_bottom), .out_R(outR_1),	  .fire(outBottomFire));
    (*DONT_TOUCH="yes"*)  ClickJoin controller2(.in_R(bottom),     .out_R(outR_2),	  .fire(bottomFire));
    (*DONT_TOUCH="yes"*)  ClickJoin controller3(.in_R(out_top),    .out_R(outR_3),	  .fire(outTopFire));
    
    (*DONT_TOUCH="yes"*)  ClickJoin delay1(.in_R(control),     .out_R(outR_4),		  .fire(delay1Fire));
    (*DONT_TOUCH="yes"*)  ClickJoin delay2(.in_R(outR_4),      .out_R(outR_5),		  .fire(delay2Fire));
    (*DONT_TOUCH="yes"*)  ClickJoin delay3(.in_R(outR_5),      .out_R(outR_6),		  .fire(delay3Fire));
    (*DONT_TOUCH="yes"*)  ClickJoin delay4(.in_R(outR_6),      .out_R(outR_7),		  .fire(delay4Fire));
    (*DONT_TOUCH="yes"*)  ClickJoin controller4(.in_R(outR_7), .out_R(outR_8),		  .fire(controlFire));

    always@ (posedge controlFire) 
       begin 
          if(back == 1) 
                 begin
                    out_bottom = ~out_bottom;
                 end 
          else if(conflict == 1)         
                 begin  
                    bottom = ~bottom; 
                 end 
          else if((back == 0) && (conflict == 0))  
                 begin 
                    out_top = ~out_top;    
                 end  
       end 
                  
    assign	reassign = bottom ^ in_bottom;    
    
endmodule       