package com.experiment.cpu;

public  class 内存可见性VisibilityTest {

    private boolean flag = true;
	private int count = 0;

    public void refresh() {
        // 希望结束数据加载工作
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:"+flag);
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        while (flag) { /* 禁止jit编译 -XX:-UseCompiler --> 利用CPU缓存一致性协议也能读到线程B修改后的值   */
            //TODO  业务逻辑：加载数据
			count ++;
        }
        System.out.println(Thread.currentThread().getName() + "数据加载完成，跳出循环,count="+count);
    }


    public static void main(String[] args) throws InterruptedException {
        内存可见性VisibilityTest test = new 内存可见性VisibilityTest();
		// 线程threadB通过修改flag控制threadA的执行时间，数据加载可以结束了
		Thread threadB = new Thread(() -> test.refresh(), "threadB");

        // 线程threadA模拟数据加载场景
        Thread threadA = new Thread(() -> test.load(), "threadA");
        threadA.start();

        // 让threadA先执行一会儿后再启动线程B
        Thread.sleep(1000);/* 休眠时间短Thread.sleep(1)，load()中的循环没有形成热点代码，未JIT编译优化 -->利用CPU缓存一致性协议 -->正常读到线程B修改后的值跳出循环 */
        threadB.start();

    }

}