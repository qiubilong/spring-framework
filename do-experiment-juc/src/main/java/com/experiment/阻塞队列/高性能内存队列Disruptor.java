package com.experiment.阻塞队列;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;

import java.util.concurrent.Executors;

/**
 * @author chenxuegui
 * @since 2024/12/31
 */
public class 高性能内存队列Disruptor {

	public static void main(String[] args) {
		//创建disruptor
		Disruptor<OrderEvent> disruptor = new Disruptor<>(new OrderEventFactory(), 1024 * 1024,
				Executors.defaultThreadFactory(), ProducerType.MULTI, new YieldingWaitStrategy()  //等待策略
		);

		//设置多消费者,消费者要实现WorkHandler接口，一条消息只会被一个消费者消费
		disruptor.handleEventsWithWorkerPool(new OrderEventHandler(), new OrderEventHandler());/* 消费者 */
		disruptor.start();

		//创建ringbuffer容器
		RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
		OrderEventProducer eventProducer = new OrderEventProducer(ringBuffer);/* 生产者 */
		//发送消息
		for(int i=0;i<100;i++){
			eventProducer.onData(i,"Fox"+i);
		}

		disruptor.shutdown();
	}

	/* 消费者 */
	public static class OrderEventHandler implements EventHandler<OrderEvent> , WorkHandler<OrderEvent> {

		@Override
		public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
			// TODO 消费逻辑
			System.out.println("消费者获取数据value:"+ event.getValue()+",name:"+event.getName());
		}

		@Override /* 多消费者 */
		public void onEvent(OrderEvent event) throws Exception {
			// TODO 消费逻辑
			System.out.println("消费者"+ Thread.currentThread().getName()
					+"获取数据value:"+ event.getValue()+",name:"+event.getName());
		}
	}
    /* 生产者 */
	public static class OrderEventProducer {
		//事件队列
		private RingBuffer<OrderEvent> ringBuffer;

		public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
			this.ringBuffer = ringBuffer;
		}

		public void onData(long value,String name) {
			// 获取事件队列 的下一个槽
			long sequence = ringBuffer.next();
			try {
				//获取消息（事件）
				OrderEvent orderEvent = ringBuffer.get(sequence);
				// 写入消息数据
				orderEvent.setValue(value);
				orderEvent.setName(name);
			} catch (Exception e) {
				// TODO  异常处理
				e.printStackTrace();
			} finally {
				System.out.println("生产者发送数据value:"+value+",name:"+name);
				//发布事件
				ringBuffer.publish(sequence);
			}
		}
	}

	@Data
	public static class OrderEvent {
		private long value;
		private String name;
	}

	public static class OrderEventFactory implements EventFactory<OrderEvent> {

		@Override
		public OrderEvent newInstance() {
			return new OrderEvent();
		}
	}
}
