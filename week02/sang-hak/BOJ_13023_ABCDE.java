import java.util.*;
import java.io.*;

public class BOJ_13023_ABCDE {
    static boolean isExisted;
    static void dfs(int depth, int cur, boolean[] isCheck, List<Integer>[] friends) {
        if (isExisted) return; // ABCDE 존재 여부 찾았을 경우 백트래킹
        if (depth == 4) {
            // ABCDE 존재 찾았을 때 (depth가 4일 때)
            isExisted = true;
            return;
        }

        // 친구 관계 dfs
        for (int i : friends[cur]) {
            if (!isCheck[i]) {
                isCheck[i] = true;
                dfs(depth+1, i, isCheck, friends);
                isCheck[i] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] friends = new ArrayList[N];

        // N과 M의 개수를 고려했을 때 인접행렬보다 인접 리스트가 빠를 것 같아서
        for (int i = 0; i < N; i++) {
            friends[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }

        // ABCDE 찾으면 break하고
        // 아니면 친구 관계 가지고 있는 사람 모두 dfs + 백트래킹으로 탐색
        for (int i = 0; i < N; i++) {
            if (isExisted) break;
            if (!friends[i].isEmpty()) {
                boolean[] isCheck = new boolean[N];
                isCheck[i] = true;
                dfs(0, i, isCheck, friends);
            }
        }

        System.out.println(isExisted ? 1 : 0);
    }
}