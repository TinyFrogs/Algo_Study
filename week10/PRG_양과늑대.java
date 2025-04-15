import java.util.*;
public class PRG_양과늑대 {

	class Solution {
		static List<Integer>[] nodes;
		static int answer = 0;
		static int[] info;

		public int solution(int[] info, int[][] edges) {
			this.info = info;
			init(edges);
			findSheep(new ArrayList<>(), 0, 0, 0);
			return answer;
		}

		static void init(int[][] edges) {
			nodes = new ArrayList[info.length];

			for(int i = 0 ; i < info.length; i++){
				nodes[i] = new ArrayList<>();
			}

			for(int[] edge : edges) {
				int parent = edge[0];
				int child = edge[1];
				nodes[parent].add(child);
			}
		}

		static void findSheep(List<Integer> nearNodes, int node, int sheep, int wolf) {
			if(info[node] == 0) sheep++;
			else wolf++;

			if(wolf >= sheep) return;

			answer = Math.max(answer, sheep);

			List<Integer> copyNearNodes = new ArrayList<>();
			copyNearNodes.addAll(nearNodes);

			if(!nodes[node].isEmpty()) copyNearNodes.addAll(nodes[node]);

			copyNearNodes.remove((Integer) node);

			for(int nearNode : copyNearNodes){
				findSheep(copyNearNodes, nearNode, sheep, wolf);
			}
		}
	}
}
