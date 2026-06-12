package cn.tulingxueyuan.base.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 类说明：get和join方法的区别
 */
public class JoinAndGet {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->{
           int i = 1/0;
           return 100;
        });

		try {
			future.get(); /* 需要显示捕捉异常 */
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		future.join();
	}
}
