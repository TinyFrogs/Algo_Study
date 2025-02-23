package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18809_Gaaaaaaaaaarden{
    static int N,M;
    static int[][] map;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static List<int[]> available;
    static List<List<int[]>> selectedLocation = new ArrayList<>();
    static List<int[]> finalSelectedLocation;

    static class Fertilizer{
        int r,c,fType, t;

        public Fertilizer(int row, int col, int fertilizer_type, int time){
            this.r=row;
            this.c=col;
            this.fType=fertilizer_type;
            this.t=time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        available = new ArrayList<>();

        // map 정보 받기 - 0:호수, 1:배양액 불가, 2:배양액 가능
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                int map_info = Integer.parseInt(st.nextToken());
                map[n][m] = map_info;

                if (map_info == 2)
                    available.add(new int[]{n, m});
            }
        }

        selectLocation(G, R);

    }

    // 1. 배양액 뿌릴 위치 뽑기(조합+순열)
    // 배양액을 뿌릴 수 있는 위치들 중 배양액 개수 만큼의 위치 선정(조합)
    // 선정된 배양액 개수 만큼의 위치에 G,R을 어떤 순서로 뿌릴건지 선정(순열)
    public static void selectLocation(int G, int R){
        combination(new ArrayList<>(), 0, G+R);
        for(List<int[]> selected : selectedLocation){
            finalSelectedLocation=new ArrayList<>();
            permutation(selected,new int[selected.size()], 0, G, R);
            for(int[] fSelected : finalSelectedLocation){
                countFlower(fSelected);
            }
        }
    }

    public static void combination(List<int[]> selected, int start, int count) {
        if (selected.size() == count) {
            selectedLocation.add(new ArrayList<>(selected));
            return;
        }

        for (int i = start; i < available.size(); i++) {
            selected.add(available.get(i));
            combination(selected, i + 1, count);
            selected.removeLast();
        }
    }

    public static void permutation(List<int[]> selected, int[] perm, int idx, int G, int R) {
        if (idx == selected.size()) {
            finalSelectedLocation.add(perm.clone());
            return;
        }

        if (G > 0) {
            perm[idx] = 3; // G 배양액
            permutation(selected, perm, idx + 1, G - 1, R);
        }
        if (R > 0) {
            perm[idx] = 4; // R 배양액
            permutation(selected, perm, idx + 1, G, R - 1);
        }
    }

    // 2. 뿌린 후 확산, G,R 배양액 동시 확산 시 피는 꽃 개수 계산
    public static void countFlower(int[] selected){
        int[][][] copiedMap=copyMap(map);

        Queue<Fertilizer> fQueue=new ArrayDeque<>();
        for(List<int[]> sLocation:selectedLocation){
            for(int i=0;i<sLocation.size();i++) {
                Fertilizer fertilizer = new Fertilizer(sLocation.get(i)[0], sLocation.get(i)[1], selected[i], 0);
                fQueue.offer(fertilizer);

                copiedMap[sLocation.get(i)[0]][sLocation.get(i)[1]][0]=selected[i];
                copiedMap[sLocation.get(i)[0]][sLocation.get(i)[1]][1]=0;
            }
        }

        while(!fQueue.isEmpty()){
            Fertilizer f=fQueue.poll();
            int r=f.r;
            int c=f.c;
            int type=f.fType;
            int t=f.t;

            for(int d=0;d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // map 영역 밖
                if(copiedMap[nr][nc][0]==0 || copiedMap[nr][nc][0]==5) continue; // 호수이거나 꽃인 영역
                if(copiedMap[nr][nc][0]==type) continue; // 현재 배양액 상태와 퍼지려는 곳의 상태가 같음

                if(copiedMap[nr][nc][0]!=type && copiedMap[nr][nc][1]==t){
                    copiedMap[nr][nc][0]=type;
                }
            }
        }
        for(int[][] row: copiedMap){
            for(int[] r:row){
                r[1]=r[1]+1;
            }
        }
    }

    public static int[][][] copyMap(int[][] map){
        int[][][] returnMap=new int[N][M][];
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                returnMap[i][j]=new int[]{map[i][j],0};
            }
        }
        return returnMap;
    }
}
