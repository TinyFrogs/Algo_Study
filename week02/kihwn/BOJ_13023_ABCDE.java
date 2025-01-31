import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
	static int n;
	static int m;
	static List<Integer>[]graph;
	static boolean[]visited;
	static boolean flag;
	public static void main(String[]args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[]sarr = s.split(" ");
		n = Integer.parseInt(sarr[0]);
		m = Integer.parseInt(sarr[1]);
		graph = new ArrayList[n];
		visited = new boolean[n];
		flag= false;

		for (int i=0; i<n; i++){
			graph[i]=new ArrayList<>();
		}
		for (int i=0; i<m; i++){
			s = br.readLine();
			sarr = s.split(" ");
			int a = Integer.parseInt(sarr[0]);
			int b = Integer.parseInt(sarr[1]);
			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i=0; i<n; i++){
			if (!flag){
				combination(i,0);
			}
		}
		if (!flag){
			System.out.println(0);
		}

	}

	static void combination(int node, int depth) {
		if (flag) return;
		if (depth==4) {
			System.out.println(1);
			flag = true;
			return;
		}
		visited[node]=true;

		for (int i=0; i<graph[node].size(); i++){
			if (!visited[graph[node].get(i)]){
				combination(graph[node].get(i),depth+1);
			}
		}
		visited[node]=false;

	}


}