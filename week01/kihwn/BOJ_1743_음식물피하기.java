import java.io.*;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class Main {
    static int[][]map;
    static boolean[][]visited;
    static int[]dx = {-1,1,0,0};
    static int[]dy = {0,0,-1,1};
    static int count = 0;
    static int sum = 0;

    static Queue<Pair>queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        String[]sarr = s.split(" ");
        int n = Integer.parseInt(sarr[0])+1;
        int m = Integer.parseInt(sarr[1])+1;
        int dump = Integer.parseInt(sarr[2]);
        map = new int[n][m];
        visited=new boolean[n][m];

        for (int i=0; i<dump; i++){
            String k = br.readLine();
            String[]karr = k.split(" ");
            int x = Integer.parseInt(karr[0]);
            int y = Integer.parseInt(karr[1]);
            map[x][y]=1;
        }

        for(int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (map[i][j]==1 && !visited[i][j]){
                    count = 0;
                    bfs(i,j,n,m);
                    sum = Math.max(count,sum);
                }
            }
        }


        System.out.println(sum);
    }

    public static void bfs(int x, int y, int n, int m){
        queue.add(new Pair(x,y));
        if (!visited[x][y]){
            visited[x][y]=true;
            count++;}

        while(!queue.isEmpty()){
            Pair pair = queue.poll();



            for (int i=0; i<4; i++){
                int tempX = pair.x + dx[i];
                int tempY = pair.y+ dy[i];
                if (tempX>=0 && tempX<n && tempY>=0 && tempY<m){
                    if (!visited[tempX][tempY]&& map[tempX][tempY]==1){
                        visited[tempX][tempY]=true;
                        count++;
                        bfs(tempX,tempY,n,m);
                    }
                }
            }
        }
    }


}


class Pair{
    int x;
    int y;

    Pair(int x , int y){
        this.x = x;
        this.y=y;
    }
}


