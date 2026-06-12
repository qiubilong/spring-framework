package cn.tulingxueyuan.base.CompletableFuture;

import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 类说明：CompletableFuture使用范例
 */
public class CompletableFutureDemo {

    static class GetResult extends Thread {
        CompletableFuture<Integer> f;

        GetResult(String threadName, CompletableFuture<Integer> f) {
            super(threadName);
            this.f = f;
        }
        @Override
        public void run() {
            try {
                System.out.println("waiting result.....");
                System.out.println(this.getName() + ": " + f.get());//挂起等待CompletableFuture结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = new CompletableFuture<>();

        new GetResult("Client1", f).start();
        new GetResult("Client2", f).start();

		//模拟CompletableFuture异步执行时长
        System.out.println("sleeping");
        SleepTools.second(2);

        f.complete(100);/* 设置 CompletableFuture 结果 --> f.get()阻塞的线程返回 */

        f.completeExceptionally(new Exception("异步执行异常"));/* 设置CompletableFuture异常结果*/


    }
}
