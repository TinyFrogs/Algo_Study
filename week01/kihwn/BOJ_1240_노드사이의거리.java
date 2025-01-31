import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;

    static List<int[]>[] tree;

    static boolean[]visited;
    static int ans = 0;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[]args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[]sarr = s.split(" ");
        n = Integer.parseInt(sarr[0]);
        m = Integer.parseInt(sarr[1]);
        //visited = new boolean[n+1];

        tree = new ArrayList[n+1];

        for (int i=0; i<n+1; i++){
            tree[i]=new ArrayList<>();
        }

        for (int i=1; i<n; i++){
            String k = br.readLine();
            String[]karr = k.split(" ");
            int n1 = Integer.parseInt(karr[0]);
            int n2 = Integer.parseInt(karr[1]);
            int dis = Integer.parseInt(karr[2]);
            tree[n1].add(new int[]{n2, dis});
            tree[n2].add(new int[]{n1, dis});
        }

        for (int i=0; i<m; i++){
            String k = br.readLine();
            String[]karr = k.split(" ");
            int n1 = Integer.parseInt(karr[0]);
            int n2 = Integer.parseInt(karr[1]);
            visited = new boolean[n+1];
            visited[n1]=true;
            dfs(n1,n2,0);
            sb.append(ans).append("\n");

        }
        System.out.println(sb);


    }

    public static void dfs(int n1, int n2, int depth){

        if (n2==n1){
            ans = depth;
            return;
        }
        for (int[] set :tree[n1]){
            if (!visited[set[0]]){
                visited[n1]=true;
                dfs(set[0],n2,depth+set[1]);
                visited[set[0]]=false;
            }
        }
    }

}



