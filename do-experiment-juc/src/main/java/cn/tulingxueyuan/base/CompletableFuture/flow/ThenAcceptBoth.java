package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：结合消费类
 */
public class ThenAcceptBoth {   /* Accept 相关函数不返回结果 */
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
			System.out.println("hello start");
			SleepTools.second(1);
			System.out.println("hello end");
			return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
			System.out.println("world start");
            SleepTools.second(2);
			System.out.println("world end");
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2)); /* 两个异步操作都完成后，执行回调，不关心返回结果 */
        SleepTools.second(3);
    }
}
