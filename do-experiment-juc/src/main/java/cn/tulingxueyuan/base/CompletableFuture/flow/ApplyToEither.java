package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快转换类
 */
public class ApplyToEither {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> {
            SleepTools.second(3);
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "hello world";
        }), s -> s).join();//最快异步操作


        System.out.println(result);
    }
}
