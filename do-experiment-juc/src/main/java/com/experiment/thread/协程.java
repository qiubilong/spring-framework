package com.experiment.thread;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author chenxuegui
 * @since 2024/12/3
 */
public class 协程 {

	public static void main(String[] args) throws  Exception{

		int threadNums = 10000;
		CountDownLatch countDownLatch = new CountDownLatch(threadNums);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();


		IntStream.range(0,threadNums).forEach(i->{

			new Fiber(){
				@Override
				protected Object run() throws InterruptedException, SuspendExecution {
					//Quasar中Thread和Fiber都被称为Strand,Fiber不能调用Thread.sleep休眠
					Strand.sleep(1000 );
					countDownLatch.countDown();

					return 1;
				}
			}.start();

		});



		countDownLatch.await(); //等待计时为0
		stopWatch.stop();
		System.out.println("结束了: "+ stopWatch.getTotalTimeMillis()+ " - "+ stopWatch.prettyPrint());
	}
}
