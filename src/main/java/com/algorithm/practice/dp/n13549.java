package com.algorithm.practice.dp;

import java.io.IOException;
import java.util.*;

public class n13549 {

    public static class Node implements Comparable<Node>{
        int n;
        int t;

        public Node(int n, int t) {
            this.n = n;
            this.t = t;
        }

        @Override
        public int compareTo(Node o) {
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int limit = Math.max(N, K) * 2;
        int[] time = new int[limit+1];
        Arrays.fill(time, Integer.MAX_VALUE);

        int minTime = Integer.MAX_VALUE;
        time[N] = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(N, 0));
        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.n == K) {
                minTime = Math.min(now.t, time[now.n]);
            }

            if(now.t > 100000) continue;

            if(now.n+1 <= limit && now.t+1 < time[now.n+1]) {
                q.add(new Node(now.n+1, now.t+1));
                time[now.n+1] = now.t+1;
            }
            if(now.n-1 >= 0 && now.t+1 < time[now.n-1]) {
                q.add(new Node(now.n-1, now.t+1));
                time[now.n-1] = now.t+1;
            }
            if(now.n*2 <= limit && now.t < time[now.n*2]) {
                q.add(new Node(now.n*2, now.t));
                time[now.n*2] = now.t;
            }
        }

        System.out.println(minTime);

    }
}
