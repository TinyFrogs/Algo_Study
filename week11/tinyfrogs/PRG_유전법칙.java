import java.util.*;


public class PRG_유전법칙 {
	class Solution {
		public String[] solution(int[][] queries) {
			String[] answer = new String[queries.length];


			for(int i = 0 ; i < queries.length ; i++) {
				int depth = queries[i][0];
				int sequence = queries[i][1];

				answer[i] = solve(depth, sequence - 1);
			}

			return answer;
		}

		static String solve(int depth, int sequence) {
			if(depth == 1) return "Rr";
			int slice = (int) Math.pow(4, depth - 2);
			int sector = sequence / slice;

			//첫 번째 구역(0)
			if(sector == 0) return "RR";

			//마지막 구역(4)
			if(sector == 3) return "rr";

			return solve(depth - 1, sequence % slice);
		}
	}
}
