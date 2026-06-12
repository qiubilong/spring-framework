package cn.tulingxueyuan.aqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

public class PhaserBatchProcessorDemo {

    private final List<String> data;//需要处理的数据
    private final int batchSize;  //一次处理多少数据
    private final int threadCount; //处理的线程数
    private final Phaser phaser;
    private final List<String> processedData;

    public PhaserBatchProcessorDemo(List<String> data, int batchSize, int threadCount) {
        this.data = data;
        this.batchSize = batchSize;
        this.threadCount = threadCount;
        this.phaser = new Phaser(1);//初始化任务数
        this.processedData = new ArrayList<>();
    }

    public void process() {
        for (int i = 0; i < threadCount; i++) {

            phaser.register();//增加一个任务数
            new Thread(new BatchProcessor(i)).start();
        }

        phaser.arriveAndDeregister();//减少一个任务数
    }

    private class BatchProcessor implements Runnable {

        private final int threadIndex;

        public BatchProcessor(int threadIndex) {
            this.threadIndex = threadIndex;
        }

        @Override
        public void run() {
            int index = 0;
            while (true) {
                // 所有线程都到达这个点之前会阻塞
                phaser.arriveAndAwaitAdvance();//等待所有的线程到达

                // 从未处理数据中找到一个可以处理的批次
                List<String> batch = new ArrayList<>();
                synchronized (data) {
                    while (index < data.size() && batch.size() < batchSize) {
                        String d = data.get(index);
                        if (!processedData.contains(d)) {
                            batch.add(d);
                            processedData.add(d);
                        }
                        index++;
                    }
                }

                // 处理数据
                for (String d : batch) {
                    System.out.println("线程" + threadIndex + "处理数据" + d);
                }

                // 所有线程都处理完当前批次之前会阻塞
                phaser.arriveAndAwaitAdvance();//等待所有的线程到达

                // 所有线程都处理完当前批次并且未处理数据已经处理完之前会阻塞
                if (batch.isEmpty() || index >= data.size()) {
                    phaser.arriveAndDeregister();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        //数据准备
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            data.add(String.valueOf(i));
        }

        int batchSize = 4;
        int threadCount = 3;
        PhaserBatchProcessorDemo processor = new PhaserBatchProcessorDemo(data, batchSize, threadCount);
        //处理数据
        processor.process();
    }
}
