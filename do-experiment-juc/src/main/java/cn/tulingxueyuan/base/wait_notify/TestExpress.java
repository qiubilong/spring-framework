package cn.tulingxueyuan.base.wait_notify;

import cn.tulingxueyuan.tools.SleepTools;

/**
 *类说明：测试wait/notify/notifyAll
 */
public class TestExpress {
    private static final Express express = new Express(0,"WUHAN");

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread{

		public CheckKm(String name) {
			super(name);
		}

		@Override
        public void run() {
        	express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread{

		public CheckSite(String name) {
			super(name);
		}

		@Override
        public void run() {
        	express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1;i++){
            new CheckSite("快递目的地检查").start();
        }
        for(int i=0;i<1;i++){
            new CheckKm("快递公里数记录").start();
        }
        SleepTools.ms(500);

        for(int i=0; i<5; i++){
            synchronized (express){
                express.change();

				/* notify只能唤醒一个线程，如果存在处理业务不同的多个线程等待条件，将会导致唤醒丢失，线程永远挂起无法结束。因此总是建议使用notifyAll() */
                //express.notify();
				express.notifyAll();
            }
            SleepTools.second(3);
			System.out.println("-------------------------------");
		}
    }
}
