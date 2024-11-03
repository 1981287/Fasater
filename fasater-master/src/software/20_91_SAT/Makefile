#export ARCH=arm
#export CROSS_COMPILE=arm-linux-gnueabihf-
KERN_DIR=/usr/src/kernels/zynq7100
all:
	make -C $(KERN_DIR) M=`pwd` modules

clean:
	make -C $(KERN_DIR) M=`pwd` modules clean
	rm -rf modules.order

obj-m += cnf_drive.o
