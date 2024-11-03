 //////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: init
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The init is used to reset the evaluate and complement signal.
// Revision: V2.0
////////////////////////////////////////////////////////////////////////////////// 
 module init(evaluateFire,complementFire,resetFire,evaluate,complement );      
       
     input evaluateFire,complementFire,resetFire;  
     output evaluate,complement;      
       
     reg  evaluate = 0;       
     reg  complement = 0;       
       
   always @(posedge evaluateFire or posedge resetFire)       
      begin      
         if (evaluateFire) 
          begin 
            evaluate = 1; 
          end 
        else if(resetFire) 
          begin        
            evaluate = 0;
          end    
       end  
       
   always@(posedge complementFire or posedge resetFire)    
      begin
          if (complementFire) 
           begin   
             complement = 1;   
           end 
         else if(resetFire)   
           begin   
             complement = 0;  
           end    
        end  
       
  endmodule  
       
       
       
