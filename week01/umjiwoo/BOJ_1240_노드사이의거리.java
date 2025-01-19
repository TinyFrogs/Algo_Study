package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1240_노드사이의거리 {
    static int[][] adj;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;
        StringBuilder sb=new StringBuilder();

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st. nextToken());

        adj=new int[N+1][N+1];

        for(int n=1;n<N;n++){
            st=new StringTokenizer(br.readLine());
            int node1=Integer.parseInt(st.nextToken());
            int node2=Integer.parseInt(st.nextToken());
            int d=Integer.parseInt(st.nextToken());

            adj[node1][node2]=d;
            adj[node2][node1]=d;
        }

        for(int m=1;m<=M;m++){
            st=new StringTokenizer(br.readLine());
            int startNode=Integer.parseInt(st.nextToken());
            int endNode=Integer.parseInt(st.nextToken());

            sb.append(bfs(startNode,endNode)).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(int startNode, int endNode){
        Queue<int[]> queue=new ArrayDeque<>();
        queue.offer(new int[]{startNode, 0});

        boolean[] visited=new boolean[adj[0].length];
        visited[startNode]=true;

        while(!queue.isEmpty()){
            int[] cur=queue.poll();
            int node=cur[0];
            int d=cur[1];

            if(node==endNode){
                return d;
            }

            for(int i=1;i<adj.length;i++){
                if(!visited[i] && adj[node][i]!=0){
                    visited[i]=true;
                    queue.offer(new int[]{i,d+adj[node][i]});
                }
            }
        }
        return -1;
    }
}
