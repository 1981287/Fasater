//////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: assignment
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The assignment module assigns values to variables according to their implied values and randomDigit.
// Revision: V2.0
//////////////////////////////////////////////////////////////////////////////////
module assignment(evaluate,complement,vimp_p,vimp_n,assignFire,randomDigit,vout_p,vout_n,back);		
   
    input evaluate,complement,vimp_p,vimp_n,assignFire,randomDigit;		
    output vout_p,vout_n,back;		
   	
    parameter init = 2'b00;		
    parameter s0 = 2'b01; 		
    parameter s1 = 2'b10;   		
   	
    reg [1:0] state = init;		
    reg  vout_p = 0;		
    reg  vout_n = 0;		
    reg  back = 0;		

always @(posedge assignFire)
begin
  if(randomDigit == 0) begin	 
       if (evaluate)
           begin        
             if (vimp_p == 0 && vimp_n == 0)begin        
               state = s0;          
               vout_p = 0;          
               vout_n = 1;         
               back = 0;end        
             else if(vimp_p == 1 && vimp_n == 0)begin        
               state = s1;         
               vout_p = 1;         
               vout_n = 0;         
               back = 0; end        
             else if(vimp_p == 0 && vimp_n == 1)begin        
               state = s0;                 
               vout_p = 0;         
               vout_n = 1;         
               back = 0;end           
          end        
       else if(complement)
             begin        
                case(state)        
                 s1:if(1) begin        
                             state = init;                         
                             vout_p = 0;        
                             vout_n = 0;         
                             back = 1;     
                          end        
                 s0:if(vimp_p == 0 && vimp_n == 1)          
                         begin        
                             state = init;        
                             vout_p = 0;          
                             vout_n = 0;          
                             back = 1;      
                          end        
                     else if(vimp_p == 0 && vimp_n == 0)        
                         begin        
                             state = s1;        
                             vout_p = 1;        
                             vout_n = 0;          
                             back = 0;  
                          end         
                 endcase     
              end    
         end    
  else if(randomDigit == 1)begin		
        if (evaluate)		
           begin		
            if (vimp_p == 0 && vimp_n == 0) begin		
                state = s1;		
                vout_p = 1;		
                vout_n = 0; 		
                back = 0;end		
            else if(vimp_p == 1 && vimp_n == 0)begin		
                state = s1;		
                vout_p = 1;  		
                vout_n = 0; 		
                back = 0;end		
            else if(vimp_p == 0 && vimp_n == 1)begin		
                state = s0;		
                vout_p = 0;   		
                vout_n = 1;		
                back = 0;end 		
         end		
         else if(complement)		
            begin		
               case(state)		
                s0:if(1) begin		
                          state = init;        
                          vout_p = 0;        
                          vout_n = 0;          
                          back = 1;  	
                         end		
                s1:if(vimp_p == 1 && vimp_n == 0)		
                         begin		
                          state = init;        
                          vout_p = 0;        
                          vout_n = 0;          
                          back = 1;                                 		
                         end		
                    else if(vimp_p == 0 && vimp_n == 0)		
                         begin		
                           state = s0;		
                           vout_p = 0; 		
                           vout_n = 1;   		
                           back = 0; 		
                         end 		
               endcase		
            end		
        end		
end

endmodule		
		