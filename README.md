#FaSATer求解器介绍
FaSATer求解器是一个基于软硬件运行时协同计算的SAT求解器,软件和硬件之间通过AXI总线进行数据通信,二者之间协同工作完成SAT问题的求解,其开发过程包括硬件工程开发、软件程序的自动生成、Linux操作系统的制作、求解器测试等几个步骤.  

项目中各个文件夹内容情况说明:
1. rtl：求解器的硬件代码和接口代码（可求解70个变量以内的SAT实例）.
2. cnf：两个简单的SAT实例.
3. petalinux：根据硬件信息生成的BOOT.BIN 和 image.ub文件，可直接用于测试.
4. parser：用于生成软件程序的解析器，需在Eclipse软件中使用.
5. software：根据解析器解析得到的两个SAT实例的软件程序，可直接用于测试.
6. doc：制作Linux操作系统时可以参考的教程.
##1 硬件工程开发
###1.1 自定义IP的封装
创建一个新的IP,命名为SAT_solver（Vivado版本为2017.4）.选择创建一个AXI4外设,选择一个AXI-Lite类型的Slave接口,数据位宽为32位,寄存器数量为44个（读写寄存器各分配21和23个）.

![输入图片说明](https://foruda.gitee.com/images/1670221770645639310/bc0bf3bc_10890357.jpeg "1.jpg")

添加设计代码并修改我们创建的总线接口模块（RTL代码中的SAT_solver_v1_0_S00_AXI.v）,然后进行综合、实现,没有问题之后封装该IP核.

![输入图片说明](https://foruda.gitee.com/images/1670221825088966293/5cbe2aad_10890357.jpeg "2.jpg")

###1.2 创建一个Block Design,添加ZYNQ7 Processing System IP核,然后根据具体需求进行配置.

![输入图片说明](https://foruda.gitee.com/images/1670221849651403109/05fbcdb6_10890357.jpeg "3.jpg")

###1.3 系统集成
将我们创建的自定义IP核添加进去,点击Run Connection Automation,Vivado会自动创建一个AXI互联模块和一个处理器系统复位模块.

![输入图片说明](https://foruda.gitee.com/images/1670221867984342089/07c11c96_10890357.jpeg "4.jpg")

选中我们创建的Block Design,点击Create HDL Wrapper,然后点击Generate Output Products.之后进行综合、实现,生成比特流,选择File,Export->ExportHardware,勾选比特流,即可得到硬件信息（.hdf文件）.
##2 软件程序的自动生成
首先安装Eclipse软件,导入parser文件夹,在Connect.java文件中设置读写寄存器使用个数（number_wr_use和number_rd_use（取决于具体的SAT实例的变量个数,可参考RTL代码中的Solver_top.v））.在运行之前需要输入cnf文件的路径（Run->Run Configuration-> Arguments->Program Arguments）,这样就可以生成不同SAT实例对应的软件程序.软件程序包括应用程序、驱动程序和编译文件,共3个.
##3 Linux操作系统的制作
首先安装虚拟机,在虚拟机中安装Centos操作系统,然后安装Linux版本的Vivado软件和Petalinux工具,其中Linux Vivado和Petalinux的版本均为2017.4,安装步骤可参考教程《course_s4_ZYNQ那些事儿-Linux实验篇V1.06》的前三章.  

环境搭建好之后,使用Petalinux工具对硬件信息（.hdf文件）进行配置和编译,得到BOOT.BIN 和 image.ub文件,具体过程可参考教程《course_s4_ZYNQ那些事儿-Linux实验篇V1.06》的第五章.需要注意的是,在配置 Petalinux工程时需要选择启动方式为从SD卡启动（Image Packaging Configuration-> Root filesystem type ->SD card）.

![输入图片说明](https://foruda.gitee.com/images/1670221891533978926/3cab3cd0_10890357.jpeg "5.jpg")

##4 求解器测试
我们使用的开发板为ZYNQ7020,实物图如下.

![输入图片说明](https://foruda.gitee.com/images/1670221915560607265/c016b4c4_10890357.jpeg "6.jpg")

首先将SD卡分区为FAT分区和EXT4分区,然后将BOOT.BIN 和 image.ub文件放到FAT分区,将根文件系统和软件程序放到EXT4分区,最后将SD卡插入开发板中上电,使用串口调试工具PUTTY即可观察求解结果,其中波特率设置为115200bps.对一个4变量、4子句的可满足SAT实例进行求解,其求解时间和求解结果如下所示.

![输入图片说明](https://foruda.gitee.com/images/1670221936608607324/e27b3fb0_10890357.jpeg "7.jpg")
