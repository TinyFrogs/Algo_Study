package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13023_ABCDE {

    static int N;
    static int M;
    static int answer;

    static boolean[] visited;

    static class Person{
        int name;
        Person friend;

        public Person(int name, Person friend) {
            this.name = name;
            this.friend = friend;
        }
    }

    static Person[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        people = new Person[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            people[a] = new Person(b, people[a]);
            people[b] = new Person(a, people[b]);
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 1);
        }

        System.out.println(answer);
    }

    static void dfs(int cur, int depth) {
        if (depth == 5) {
            answer = 1;
            return;
        }

        if(answer == 1) return;

        visited[cur] = true;

        for (Person next = people[cur]; next != null; next = next.friend) {
            if(visited[next.name]) continue;
            dfs(next.name, depth + 1);
        }

        visited[cur] = false;
    }
}
