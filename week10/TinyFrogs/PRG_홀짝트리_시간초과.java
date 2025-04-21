import java.util.*;

public class PRG_홀짝트리_시간초과 {
	class Solution {

		static int[] answer = new int[2];
		static int answerHol = 0;
		static int answerReHol = 0;

		static ArrayDeque<Integer> q = new ArrayDeque<>();
		static List<Integer>[] nodeList = new ArrayList[1000001];
		static boolean[] visited = new boolean[1000001];

		public int[] solution(int[] nodes, int[][] edges) {
			init(nodes, edges);
			solve(nodes);

			return answer;
		}


		static void init(int[] nodes, int[][] edges){
			for(int n = 0 ; n < nodes.length; n++){
				int node = nodes[n];
				nodeList[node] = new ArrayList<>();
			}

			for(int i = 0; i<edges.length; i++){
				int from = edges[i][0];
				int to = edges[i][1];

				nodeList[from].add(to);
				nodeList[to].add(from);
			}
		}

		static void solve(int[] nodes){
			for(int n = 0 ; n < nodes.length; n++){
				int node = nodes[n];
				Arrays.fill(visited, false);

				if(checkHol(node)) {
					if(findHol(node)) {
						answerHol++;
					}
				}
				else {
					if(findReHol(node)){
						answerReHol++;
					}
				}
			}

			answer[0] = answerHol;
			answer[1] = answerReHol;
		}

		static boolean findHol(int root) {
			q.clear();
			q.offer(root);
			visited[root] = true;

			while(!q.isEmpty()){
				int size = q.size();

				for(int i = 0 ; i < size; i++){
					int currNode = q.poll();

					if(!checkHol(currNode)) return false;

					for(int next : nodeList[currNode]){
						if(visited[next]) continue;
						visited[next] = true;
						q.offer(next);
					}

				}
			}

			return true;
		}

		static boolean findReHol(int root) {
			q.clear();
			q.offer(root);
			visited[root] = true;

			while(!q.isEmpty()){
				int size = q.size();

				for(int i = 0 ; i < size; i++){
					int currNode = q.poll();

					if(!checkReHol(currNode)) return false;

					for(int next : nodeList[currNode]){
						if(visited[next]) continue;
						visited[next] = true;
						q.offer(next);
					}

				}
			}

			return true;
		}

		static boolean checkHol(int node){
			int childSize = findChildSize(node);
			if(checkOdd(node) == checkOdd(childSize)) {
				return true;
			}
			return false;
		}

		static boolean checkReHol(int node){
			int childSize = findChildSize(node);
			if(checkOdd(node) != checkOdd(childSize)) return true;
			return false;
		}



		static boolean checkOdd(int n){
			return (n % 2 == 1);
		}

		static int findChildSize(int node) {
			int count = 0;
			for(int n : nodeList[node]){
				if(visited[n]) continue;
				count++;
			}

			return count;
		}

	}
}
