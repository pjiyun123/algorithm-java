package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n1238 {

    static int N; // 마을 수
    static int M; // 길 수
    static int X; // 모이는 마을 번호
    static List<Edge>[] adj; // 길 정보

    public static class Edge implements Comparable<Edge>{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.weight > o.weight)
                return 1;
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            adj[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 모이는 마을에서 나가는데 소요되는 최단 시간 구하기
        int[] out = cal(X);

        // 모이는 마을로 가는데 소요되는 최단 시간 구하기
        int[] in = new int[N+1];
        for(int i = 1; i <= N; i++) {
            in[i] = cal(i)[X];
        }

        // 오고 가는데 소요되는 최단 시간 구하기
        int[] cnt = new int[N+1];
        for(int i = 1; i <= N; i++) {
            cnt[i] = in[i] + out[i];
        }

        Arrays.sort(cnt);
        System.out.println(cnt[N]);

    }

    public static int[] cal(int goal) {

        int[] dist = new int[N+1];
        boolean[] checked = new boolean[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[goal] = 0; // 시작점 0으로 변경

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(goal, 0));
        Edge cur = null;

        while(!pq.isEmpty()) {
            cur = pq.poll();
            if(checked[cur.to]) continue;

            for(Edge next : adj[cur.to]) {
                if(!checked[next.to] && dist[next.to] > dist[cur.to] + next.weight) {
                    dist[next.to] = dist[cur.to] + next.weight;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }

            checked[cur.to] = true;
        }

        return dist;

    }

}
