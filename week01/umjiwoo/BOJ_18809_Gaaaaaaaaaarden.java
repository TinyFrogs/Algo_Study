package umjiwoo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18809_Gaaaaaaaaaarden{
    static int[][] map;
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static List<int[]> available;
    static List<List<int[]>> selectedLocation = new ArrayList<>();
    static List<int[]> finalSelectedLocation;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
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
                System.out.println(Arrays.toString(fSelected));
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
            perm[idx] = 1; // G 배양액
            permutation(selected, perm, idx + 1, G - 1, R);
        }
        if (R > 0) {
            perm[idx] = 2; // R 배양액
            permutation(selected, perm, idx + 1, G, R - 1);
        }
    }

    // 2. 뿌린 후 확산, G,R 배양액 동시 확산 시 피는 꽃 개수 계산
    public static void countFlower(){

    }
}
