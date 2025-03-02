package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1743_음식물피하기 {
    static int[][] map;
    static boolean[][] visited;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static int max_size=0;

    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=null;

        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        map=new int[N+1][M+1];
        visited=new boolean[N+1][M+1];
        Queue<int[]> queue=new ArrayDeque<int[]>();

        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        for(int n=1;n<N+1;n++){
            for(int m=1;m<M+1;m++){
                if(map[n][m]==1 && !visited[n][m]){
                    max_size = Math.max(max_size, bfs(n,m));
                }
            }
        }

        System.out.println(max_size);
    }

    public static int bfs(int startR,int startC){
        Queue<int[]> queue=new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC]=true;
        int size=1;

        while(!queue.isEmpty()){
            int[] start=queue.poll();
            for(int d=0;d<4;d++){
                int nr=start[0]+dr[d];
                int nc=start[1]+dc[d];
                if(0<nr && nr<map.length && 0<nc && nc<map[0].length
                        && !visited[nr][nc] && map[nr][nc]==1){
                    queue.offer(new int[]{nr,nc});
                    visited[nr][nc]=true;
                    size++;
                }
            }
        }
        return size;
    }
}
