package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17951_흩날리는시험지속에서내평점이느껴진거야 {

    static int N;
    static int K;
    static int answer;
    static int[] scores;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 0; i < N; i++){
            scores[i] = Integer.parseInt(st.nextToken());
            sum += scores[i];
        }

        parametricSearch(sum);

        System.out.println(answer);
    }

    private static void parametricSearch(int sum) {
        answer = 0;
        int left = 0;
        int right = sum;

        while(left <= right){
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                answer = mid;
                left = mid + 1;
            } else
                right = mid - 1;
        }
    }

    private static boolean isValid(int target) {
        int group = 0;
        int count = 0;

        for (int score : scores) {
            count += score;

            if (count >= target) {
                group++;
                count = 0;
            }
        }

        return group >= K;
    }
}
