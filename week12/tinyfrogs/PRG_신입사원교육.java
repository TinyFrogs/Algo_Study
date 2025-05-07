import java.util.*;

public class PRG_신입사원교육 {

	class Solution {

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o));

		public int solution(int[] ability, int number) {

			for(int a : ability) pq.offer(a);

			for(int i = 0 ;  i < number ; i++){
				int num1 = pq.poll();
				int num2 = pq.poll();
				int sum = num1 + num2;
				pq.offer(sum);
				pq.offer(sum);
			}


			return pq.stream().mapToInt(o -> o).sum();
		}
	}
}
