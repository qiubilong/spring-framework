package com.experiment.thread.并发容器;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author chenxuegui
 * @since 2024/12/13
 */
public class ConcurrentSkipHashMap_跳表 {
	// 分数 -> 用户ID
	private ConcurrentSkipListMap<Integer, String> scoresMap = new ConcurrentSkipListMap<>((a, b) -> b - a); // 按分数降序排序;


	public static void main(String[] args) {
		ConcurrentSkipHashMap_跳表 leaderboard = new ConcurrentSkipHashMap_跳表();
		leaderboard.putScore("user1", 100);
		leaderboard.putScore("user2", 200);
		leaderboard.putScore("user3", 150);

		System.out.println("Top 2 users:");
		leaderboard.getTopN(2);


	}

	public  void putScore(String userId, int score) {
		scoresMap.put(score, userId);
	}

	// 获取前 N 名用户
	public void getTopN(int n) {
		int count = 0;
		for (Map.Entry<Integer, String> entry : scoresMap.entrySet()) {
			if (count < n) {
				System.out.println("User: " + entry.getValue() + ", Score: " + entry.getKey());
				count++;
			} else {
				break;
			}
		}
	}
}
