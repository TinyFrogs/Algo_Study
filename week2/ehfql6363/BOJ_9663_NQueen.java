package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9663_NQueen {

    static int N;
    static int answer;
    static boolean[] chess;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        chess = new boolean[N * N];

        nQueen(0);

        sb.append(answer);
        System.out.println(sb);
    }

    static void nQueen(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int index = depth * N; index < depth * N + N; index++) {
            if(!isValidColForPiece(index)) continue;
            if(!isValidLeftDialogForPiece(index)) continue;
            if(!isValidRightDialogForPiece(index)) continue;

            chess[index] = true;

            nQueen(depth + 1);
            chess[index] = false;
        }
    }

    static boolean isValidColForPiece(int index) {
        for (int next = index % N; next < N * N; next += N) {
            if(chess[next]) return false;
        }

        return true;
    }

    static boolean isValidRightDialogForPiece(int index) {
        int next = index;

        while (next - N - 1 >= 0) { // left up
            if(next % N == 0) return true;
            next -= N + 1;
            if(chess[next]) return false;
        }

        return true;
    }

    static boolean isValidLeftDialogForPiece(int index) {
        int next = index;

        while (next - N + 1 >= 0) { // right up
            if(next % N == N - 1) return true;
            next -= N - 1;
            if(chess[next]) return false;
        }

        return true;
    }
}
