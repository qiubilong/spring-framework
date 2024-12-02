package cn.tulingxueyuan.base.vola;

import cn.tulingxueyuan.tools.SleepTools;

/**
 * 类说明：演示Volatile的提供的可见性
 */
/* volatile声明的变量存在主存，而非cpu缓存 */
public class VolatileCase {
    private static boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while(!ready){
                System.out.println("lll");
            };//无限循环
            System.out.println("PrintThread end. number = "+number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
