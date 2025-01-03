package cn.tulingxueyuan.base.CompletableFuture.flow;

import java.util.concurrent.CompletableFuture;

/**
 * 类说明：变换类
 */
public class ThenApply {
    public static void main(String[] args) {
        int result = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(s -> (s + " world").length()).join();
        System.out.println(result);
    }
}
