package cn.tulingxueyuan.base.vola;

import cn.tulingxueyuan.tools.SleepTools;

/**
 * 类说明：演示Volatile的提供的可见性
 *
 *
 *               -------可见性 - volatile
 *  synchronized
 *               --------原子性
 *
 *    volatile是最轻量的线程通信机制，只适合一个线程写
 *    volatile能做到的事情，synchronized都能做到
 */
/* volatile声明的变量存在主存，而非cpu缓存 */
public class VolatileCase {
    private volatile static boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while(!ready){
                //System.out.println("lll");
            };//无限循环
            System.out.println("PrintThread end. number = "+number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true; /* volatile写屏障,把目前所有的修改刷到主存 */
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
