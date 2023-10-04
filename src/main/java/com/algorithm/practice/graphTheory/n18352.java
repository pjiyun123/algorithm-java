package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 특정 거리의 도시 찾기
public class n18352 {

    static int N; // 도시 개수
    static int M; // 간선 개수
    static int K; // 최단 거리
    static int X; // 출발 도시
    static ArrayList<Node>[] adjList; // 간선 연결 정보
    static int[] dist; // 출발지에서 각 지점까지의 최단 거리 정보
    static boolean[] visited; // 각 지점 방문 정보

    public static class Node implements Comparable<Node>{
        int destination;
        int distance;
        public Node(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        for(int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(new Node(b, 1));
        }

        dijkstra(X);
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            //System.out.println(dist[i]);
            if(dist[i] == K) {
                cnt++;
                System.out.println(i);
            }
        }

        if(cnt == 0)
            System.out.println("-1");

    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        visited = new boolean[N+1];

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(visited[now.destination])
                continue;
            else
                visited[now.destination] = true;

            for(Node next : adjList[now.destination]) {
                if(dist[next.destination] >= dist[now.destination] + next.distance) {
                    dist[next.destination] = dist[now.destination] + next.distance;
                    pq.add(new Node(next.destination, dist[next.destination]));
                }
            }
        }

    }

}
