 //////////////////////////////////////////////////////////////////////////////////
//////////////////////////////Asyncsys Lab////////////////////////////////////////
// Author: xirui, WangTianLi
// Email: xir19@lzu.edu.cn, wangtl21@lzu.edu.cn
// Create Date: 2022/05/31 
// Module Name: ClickJoin
// Project Name: FaSATer
// Target Devices: xc7z020clg400-2
// Tool Versions: Vivado 2017.4
// Description: The ClickJoin is a asynchronous controller.
// Revision: V2.0
//////////////////////////////////////////////////////////////////////////////////
module ClickJoin(in_R, out_R, fire);       
 	input	in_R;      
	output	fire, out_R;       
       
	wire	in_R_nor, out_R_delayed, out_R_tmp;       
	assign	fire = in_R_nor;       
 	    
  LUT2 #(.INIT(4'b0110)) nor_in_R    
	(       
	   .O(in_R_nor),        
	   .I0(out_R_delayed),        
	   .I1(in_R)        
 	);      
       
	FDPE #(.INIT(1'b0)) ff_in_R (       
 		  .Q(out_R),         
		  .C(in_R_nor),        
		  .CE(1'b1),         
		  .PRE(1'b0),       
		  .D(out_R_tmp)          
	   );       
       
 LUT1 #(.INIT(2'b01)) in_R_inv        
	(       
	   .O(out_R_tmp),         
	   .I0(out_R)         
   );    
       
	LUT1 #(.INIT(2'b10)) in_R_delay        
 	(       
    .O(out_R_delayed),        
    .I0(out_R)       
  );     
       
 endmodule      
       
       
       
