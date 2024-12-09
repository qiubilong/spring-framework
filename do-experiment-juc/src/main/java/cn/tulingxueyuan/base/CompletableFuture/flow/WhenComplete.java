package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：运行后记录结果类
 */
public class WhenComplete {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(3);
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, throwable) -> {/* 异步完成记录 */
            System.out.println("whenComplete --> " + s);
            System.out.println("whenComplete --> " + throwable);
        }).exceptionally(e -> { /* 异常补偿 */
            System.out.println("exceptionally e = "+e.getMessage());
            return "hello world";
        }).join();/* 最终结果 */
        System.out.println(result);
    }
}
