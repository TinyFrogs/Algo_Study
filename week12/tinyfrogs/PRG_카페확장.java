import java.util.*;
class PRG_카페확장 {

	public int solution(int[] menu, int[] order, int k) {
		Queue<Integer> waitQ = new LinkedList<>();

		int currentTime = 0;
		int maxCount = 0;

		for (int i = 0; i < order.length; i++) {
			int arrivalTime = i * k;
			int startTime = Math.max(currentTime, arrivalTime);
			int endTime = startTime + menu[order[i]];

			currentTime = endTime;

			while (!waitQ.isEmpty() && waitQ.peek() <= arrivalTime) {
				waitQ.poll();
			}

			waitQ.offer(endTime);

			maxCount = Math.max(maxCount, waitQ.size());
		}

		return maxCount;
	}
}