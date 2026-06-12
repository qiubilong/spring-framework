package cn.tulingxueyuan.base.CompletableFuture.flow;


import cn.tulingxueyuan.tools.SleepTools;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：取最快运行后执行类
 */
public class RunAfterEither {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            SleepTools.second(2);
            return "s1";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            SleepTools.second(1);
            return "s2";
        }), () -> System.out.println("hello world")).join();



        SleepTools.second(3);

	}
}
