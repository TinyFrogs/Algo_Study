import java.util.*;


public class PRG_운영체제 {

	class Solution {
		static long time = 0;
		public long[] solution(int[][] tasks) {
			long[] answer = new long[11];

			//대기
			PriorityQueue<int[]> wait = new PriorityQueue<>((a, b) -> {
				if(a[0] == b[0]) return a[1] - b[1];
				return a[0] - b[0];
			});

			//실행전
			PriorityQueue<int[]> ready = new PriorityQueue<>((a, b) -> {
				if(a[1] == b[1]) return a[0] - b[0];
				return a[1] - b[1];
				}
			);


			for(int[] task : tasks) ready.offer(task);
			

			while (true) {
				if(ready.isEmpty() && wait.isEmpty()) break;
				if (!wait.isEmpty()) {
					int[] task = wait.poll();
					int idx = task[0];
					int startTime = task[1];
					int durationTime = task[2];

					long waitTime = time - startTime;
					answer[idx] += waitTime;
					time += durationTime;
				} else {
					int[] task = ready.poll();
					int startTime = task[1];
					int durationTime = task[2];

					time = startTime + durationTime;
				}

				while (!ready.isEmpty() && ready.peek()[1] <= time) {
					wait.offer(ready.poll());
				}
			}

			answer[0] = time;
			return answer;
		}
	}
}
