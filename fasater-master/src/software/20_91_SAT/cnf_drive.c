#include <linux/types.h>
#include <linux/kernel.h>
#include <linux/delay.h>
#include <linux/init.h>
#include <linux/ide.h>
#include <linux/module.h>
#include <linux/errno.h>
#include <linux/gpio.h>
#include <linux/cdev.h>
#include <linux/device.h>
#include <linux/of_gpio.h>
#include <linux/semaphore.h>
#include <linux/timer.h>
#include <linux/irq.h>
#include <linux/wait.h>
#include <linux/poll.h>
#include <linux/fs.h>
#include <linux/fcntl.h>
#include <linux/platform_device.h>
#include <linux/miscdevice.h>
#include <asm/mach/map.h>
#include <asm/uaccess.h>
#include <asm/io.h>

#define SAT_T unsigned int
#define DEVICE_NAME "sat" 
#define SAT_BASE_ADDR   (0x43C00000)


typedef struct
{
    
  volatile unsigned int Data;

}SAT_DATA;

SAT_DATA* reg0;
SAT_DATA* reg1;
SAT_DATA* reg2;
SAT_DATA* reg3;
SAT_DATA* reg4;
SAT_DATA* reg5;
SAT_DATA* reg6;
SAT_DATA* reg7;
SAT_DATA* reg8;
SAT_DATA* reg9;
SAT_DATA* reg23;
SAT_DATA* reg24;
SAT_DATA* reg25;

static int sat_drv_open(struct inode *Inode, struct file *File)
{
reg0->Data = 0x00000000;
reg1->Data = 0x00000000;
reg2->Data = 0x00000000;
reg3->Data = 0x00000000;
reg4->Data = 0x00000000;
reg5->Data = 0x00000000;
reg6->Data = 0x00000000;
reg7->Data = 0x00000000;
reg8->Data = 0x00000000;
reg9->Data = 0x00000000;
reg23->Data = 0x00000000;
reg24->Data = 0x00000000;
reg25->Data = 0x00000000;
 
return 0;
}

static ssize_t sat_drv_read(struct file *file, char __user *buf, size_t count, loff_t *ppos)
{
unsigned int ret = 0;
   
int a[3]={0};
 
a[0]= reg23->Data;
a[1]= reg24->Data;
a[2]= reg25->Data;
 
u32 pos = ppos;
ret = copy_to_user(buf,a,count); 

/*
   printk("read \n");
   int i; 
   for (i=0;i<3;i++){
		printk("read_out[%d]=%d\n",i,a[i]);
   }
*/
return ret;
} 

static ssize_t sat_drv_write(struct file *file, const char __user *buf, size_t count, loff_t *ppos)
{
unsigned int ret = 0; 
int b[10]={0};
 
u32 pos = ppos;
ret = copy_from_user(b,buf,count);

/*	
	 printk("write \n");
     int i;
     for (i=0;i<10;i++){
		  printk("write_in[%d]=%d\n",i,b[i]);
	} */ 
 
reg0->Data = b[0];
reg1->Data = b[1];
reg2->Data = b[2];
reg3->Data = b[3];
reg4->Data = b[4];
reg5->Data = b[5];
reg6->Data = b[6];
reg7->Data = b[7];
reg8->Data = b[8];
reg9->Data = b[9];

return ret;
} 

static struct file_operations dev_fops =
{ 
    .owner = THIS_MODULE, 
    .open = sat_drv_open,
    .read = sat_drv_read, 
    .write = sat_drv_write,
}; 

static struct miscdevice misc =
{ 
    .minor = MISC_DYNAMIC_MINOR, 
    .name = DEVICE_NAME, 
    .fops = &dev_fops 
}; 

static int __init sat_drv_init(void)
{
int ret; 

reg0 = ioremap(SAT_BASE_ADDR, sizeof(SAT_T)); 
reg1 = ioremap(SAT_BASE_ADDR+4, sizeof(SAT_T)); 
reg2 = ioremap(SAT_BASE_ADDR+8, sizeof(SAT_T)); 
reg3 = ioremap(SAT_BASE_ADDR+12, sizeof(SAT_T)); 
reg4 = ioremap(SAT_BASE_ADDR+16, sizeof(SAT_T)); 
reg5 = ioremap(SAT_BASE_ADDR+20, sizeof(SAT_T)); 
reg6 = ioremap(SAT_BASE_ADDR+24, sizeof(SAT_T)); 
reg7 = ioremap(SAT_BASE_ADDR+28, sizeof(SAT_T)); 
reg8 = ioremap(SAT_BASE_ADDR+32, sizeof(SAT_T)); 
reg9 = ioremap(SAT_BASE_ADDR+36, sizeof(SAT_T)); 
reg23 = ioremap(SAT_BASE_ADDR+92, sizeof(SAT_T)); 
reg24 = ioremap(SAT_BASE_ADDR+96, sizeof(SAT_T)); 
reg25 = ioremap(SAT_BASE_ADDR+100, sizeof(SAT_T)); 
 
    ret = misc_register(&misc);
    if(ret)
    {
        printk("sat_drv_init faiitrt!\n");
    }
    else
    {
        printk("sat_drv_init success!\n");
    } 
    return ret;
}

static void __exit sat_drv_exit(void)
{
iounmap(reg0);
iounmap(reg1);
iounmap(reg2);
iounmap(reg3);
iounmap(reg4);
iounmap(reg5);
iounmap(reg6);
iounmap(reg7);
iounmap(reg8);
iounmap(reg9);
iounmap(reg23);
iounmap(reg24);
iounmap(reg25);

misc_deregister(&misc); 
printk("sat_drv_exit success!\n");
}  

module_init( sat_drv_init);

module_exit( sat_drv_exit);

MODULE_LICENSE("Dual BSD/GPL");


