package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快消费类
 */
public class AcceptEither {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(3);
            return "s1";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "hello world";
        }), (s)-> System.out.println(s));//最快异步结果

        SleepTools.second(3);
    }
}
