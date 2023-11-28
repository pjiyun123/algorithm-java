package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n2211 {

    static int N; // 컴퓨터 개수
    static int M; // 회선 개수

    static ArrayList<Edge>[] adjList;
    static int[] times;
    static boolean[] visited;
    static int[] pre;

    public static class Edge implements Comparable<Edge> {
        int destination;
        int dist;

        public Edge(int destination, int dist) {
            this.destination = destination;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
        }

        dijkstra(1);
        for(int i = 0; i <= N; i++) {
            System.out.println(times[i]);
        }

    }

    public static void dijkstra(int start) {

        times = new int[N+1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;
        visited = new boolean[N+1];
        pre = new int[N+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(visited[now.destination])
                continue;
            else
                visited[now.destination] = true;

            for(Edge next : adjList[now.destination]) {
                if(times[next.destination] >= times[now.destination] + next.dist) {
                    times[next.destination] = times[now.destination] + next.dist;
                    pq.add(new Edge(next.destination, times[next.destination]));
                }
            }
        }

    }

}
