package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：结合转化类
 */
public class ThenCombine {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
			System.out.println("hello start");
            SleepTools.second(2);
			System.out.println("hello end");
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {/* 将两个异步操作结果合并后返回 */
			System.out.println("world start");
			SleepTools.second(1);
			System.out.println("world end");
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join(); /* 自定义合并结果 */

        System.out.println(result);
    }
}
