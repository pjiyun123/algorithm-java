package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 트리의 지름
public class n1167 {

    static int N; // 정점의 개수
    static ArrayList<Edge>[] adjList;
    static int maxLen = Integer.MIN_VALUE; // 트리의 지름

    public static class Edge implements Comparable<Edge>{
        int dest;
        int val;

        public Edge(int dest, int val) {
            this.dest = dest;
            this.val = val;
        }
        @Override
        public int compareTo(Edge o) {
            return this.val - o.val;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            String[] tmp = br.readLine().split(" ");
            int s = Integer.parseInt(tmp[0]);
            adjList[s] = new ArrayList<>();
            for(int j = 1; j < tmp.length - 1; j+=2) {
                int n = Integer.parseInt(tmp[j]);
                int v = Integer.parseInt(tmp[j+1]);
                adjList[s].add(new Edge(n, v));
            }
        }

        findMaxLen(findMaxLen(1));
        System.out.println(maxLen);

    }

    public static int findMaxLen(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        boolean[] checked = new boolean[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);


        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int dest = edge.dest;

            if(checked[dest])
                continue;
            else
                checked[dest] = true;

            for(Edge next : adjList[dest]) {
                if(dist[next.dest] >= dist[dest] + next.val) {
                    dist[next.dest] = dist[dest] + next.val;
                    pq.add(new Edge(next.dest, dist[next.dest]));
                }
            }
        }

        int maxNode = 0;
        int maxCnt = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            if(dist[i] != Integer.MAX_VALUE) {
                if(maxCnt < dist[i]) {
                    maxCnt = dist[i];
                    maxNode= i;
                }
            }
        }

        maxLen = Math.max(maxLen, maxCnt);

        return maxNode;
    }

}
