package com.experiment.thread.线程通讯;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;

@Slf4j
public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        
        // Create and start threads
        for (int i = 1; i <= 3; i++) {
            new Thread(new Task(phaser, "Task " + i)).start();
        }
    }

	private static class Task implements Runnable {
		private final Phaser phaser;
		private final String name;

		public Task(Phaser phaser, String name) {
			this.phaser = phaser;
			this.name = name;
			phaser.register(); // Register the task with the phaser
		}

		@Override
		public void run() {
			// Phase 1
			log.info(name + " is performing Phase 1.");
			try {
				Thread.sleep((int) (Math.random() * 1000)); // Simulate work
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			phaser.arriveAndAwaitAdvance(); // Wait for others to reach this phase

			// Phase 2
			System.out.println("------------------------");
			log.info(name + " is performing Phase 2.");
			try {
				Thread.sleep((int) (Math.random() * 1000)); // Simulate work
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			phaser.arriveAndAwaitAdvance(); // Wait for others to reach this phase

			System.out.println("------------------------");
			// Phase 3
			log.info(name + " is performing Phase 3.");
			try {
				Thread.sleep((int) (Math.random() * 1000)); // Simulate work
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			System.out.println("------------------------");
			phaser.arriveAndDeregister(); // Deregister this task
			log.info(name + " has finished.");
		}
	}
}