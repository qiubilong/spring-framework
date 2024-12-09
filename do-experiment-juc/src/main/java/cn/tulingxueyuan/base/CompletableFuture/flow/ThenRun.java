package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：执行操作类
 */
public class ThenRun {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
			System.out.println("hello start");
            SleepTools.second(2);
			System.out.println("hello end");
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));
        SleepTools.second(2);
    }
}
