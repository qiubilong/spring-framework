package cn.tulingxueyuan.base.CompletableFuture.flow;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：结合转化类
 */
public class ThenCompose {
    public static void main(String[] args) {
        String result =
        CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenCompose(i -> CompletableFuture
                        .supplyAsync(() -> { return i+"";})).join();
        System.out.println(result);
    }
}
