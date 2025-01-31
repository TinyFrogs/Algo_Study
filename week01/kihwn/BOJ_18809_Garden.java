
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;

    static int g;

    static int r;

    static int[][]map,map2;
    static int[]red, green;
    static int[][]all; //배양액 가능한 위치

    static boolean[][]visited;
    static int[]dx = {-1,1,0,0};
    static int[]dy = {0,0,-1,1};
    static int ans = 0;
    static int a=0; //배양액 가능한 칸 수


    public static void main(String[]args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[]sarr = s.split(" ");
        n = Integer.parseInt(sarr[0]);
        m = Integer.parseInt(sarr[1]);
        g = Integer.parseInt(sarr[2]);
        r = Integer.parseInt(sarr[3]);

        map = new int[n][m];
        visited = new boolean[n][m];
        green = new int[g];
        red = new int[r];

        for (int i=0; i<n; i++){
            s=br.readLine();
            sarr = s.split(" ");
            for (int j=0; j<m; j++){
                map[i][j]=Integer.parseInt(sarr[j]);
                if (map[i][j]==2){
                    a++;
                }
            }
        }
        all = new int[a][2];


        int idx = 0;
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (map[i][j]==2){
                    all[idx][0]=i;
                    all[idx][1]=j;
                    idx++;
                }
            }
        }

        dfs(0,0,0);
        System.out.println(ans);

    }

    static void dfs (int greenN, int redN, int depth){
        if (depth==a) {
            if (greenN==g && redN==r){
                bfs();
            }
            return;
        };

        if (greenN<g){
            green[greenN] = depth;
            dfs(greenN+1,redN,depth+1);

        }

        if (redN<r){
            red[redN]=depth;
            dfs(greenN,redN+1, depth+1);

        }
        dfs(greenN,redN,depth+1);


    }

    static void bfs(){
        map2 = new int[n][m];
        Queue<int[]>queue = new LinkedList<>();
        int[][]time = new int[n][m];
        int flower=0;
        makeTmpMap(queue);

        while(!queue.isEmpty()){
            int[]tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int color = tmp[2];
            int t = tmp[3];

            if (map2[x][y]!=5){
                for (int i=0; i<4; i++){
                    int tmpX = x+dx[i];
                    int tmpY = y+dy[i];

                    if (tmpX<n && tmpX>=0 && tmpY<m && tmpY>=0){
                        if (map2[tmpX][tmpY]==1 || map2[tmpX][tmpY]==2){
                            map2[tmpX][tmpY]=color;
                            time[tmpX][tmpY]=t+1;
                            queue.add(new int[]{tmpX,tmpY,color,t+1});
                        }
                        else if (((map2[tmpX][tmpY]==3 && color==4) ||(map2[tmpX][tmpY]==4&&color==3))&& time[tmpX][tmpY]==t+1){
                            map2[tmpX][tmpY]=5;
                            flower++;
                        }
                    }
                }
            }


        }
        ans = Math.max(ans,flower);
    }

    public static void makeTmpMap(Queue<int[]>queue){
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                map2[i][j]=map[i][j];

            }
        }

        for (int i=0; i<g; i++){
            int j = green[i];
            int x = all[j][0];
            int y = all[j][1];
            map2[x][y]=3;
            queue.add(new int[]{x,y,3,0});
        }

        for (int i=0; i<r; i++){
            int j=red[i];
            int x = all[j][0];
            int y= all[j][1];
            map2[x][y]=4;
            queue.add(new int[]{x,y,4,0});
        }
    }


}


