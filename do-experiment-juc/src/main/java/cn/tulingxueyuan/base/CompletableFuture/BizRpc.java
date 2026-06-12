package cn.tulingxueyuan.base.CompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author chenxuegui
 * @since 2024/12/9
 */
public class BizRpc {

	public static void main(String[] args) {  /* CompletableFuture调用接口模拟 */

		Long uid = new Random().nextLong();

		CompletableFuture<ServiceResult> future = CompletableFuture.supplyAsync(() -> {
			if(uid %2  == 0){
				throw new IllegalArgumentException();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return new ServiceResult(200,"rpc结果");
		}).exceptionally(throwable -> {  /* 异常补偿 */
			return new ServiceResult(500,"rpc异常,"+throwable.getMessage());
		});

		ServiceResult serviceResult = ServiceResult.getCompletableFuture(future);/* JDK9后可以直接使用completeOnTimeout设置异步超时时间 */

		System.out.println(serviceResult);
	}

	private static class ServiceResult{
		public int code;
		public Object data;

		public ServiceResult(int code, Object data) {
			this.code = code;
			this.data = data;
		}

		public static ServiceResult getCompletableFuture(CompletableFuture<ServiceResult> future){

			ServiceResult serviceResult = null;
			try {
				serviceResult = future.get(1, TimeUnit.SECONDS);
			} catch (InterruptedException | TimeoutException e) {
				return new ServiceResult(500,"rpc超时");  /* 超时补偿 */
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			return serviceResult;
		}

		@Override
		public String toString() {
			return "ServiceResult{" +
					"code=" + code +
					", data=" + data +
					'}';
		}
	}
}
