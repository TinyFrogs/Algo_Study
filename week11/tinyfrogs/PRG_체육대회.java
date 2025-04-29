import java.util.*;

public class PRG_체육대회 {
	class Solution {

		static int peopleCount;
		static int subjectCount;
		static int answer = 0;
		static boolean[] visited;
		static int[] sequence;


		public int solution(int[][] ability) {
			peopleCount = ability.length;
			subjectCount = ability[0].length;
			visited = new boolean[peopleCount];
			sequence = new int[subjectCount];

			solve(0, ability);

			return answer;
		}

		static void solve(int count, int[][] ability){
			if(count == subjectCount){
				int sum = sumSubject(ability);
				answer = Math.max(answer, sum);

				return;
			}


			for(int i = 0; i < peopleCount; i++){
				if(visited[i]) continue;
				visited[i] = true;
				sequence[count] = i;
				solve(count+1, ability);
				visited[i] = false;
			}
		}

		static int sumSubject(int[][] ability){
			int sum = 0;
			for(int i = 0 ; i < sequence.length; i++){
				int selectPeople = sequence[i];
				sum += ability[selectPeople][i];
			}

			return sum;
		}
	}
}
