package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n4386 {

    static int N; // 별의 개수
    static Loc[] locs; // 별의 위치

    static double[][] dist; // 별 사이의 거리

    static PriorityQueue<Loc> pq = new PriorityQueue<>();
    static int[] parents;

    public static class Loc implements Comparable<Loc> {
        double x, y;
        int i, j;
        double dist;

        public Loc(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Loc(int i, int j, double dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }

        @Override
        public int compareTo(Loc o) {
            if(this.dist > o.dist) return 1;
            return -1;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        double res = 0.0;

        StringTokenizer st;
        locs = new Loc[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            locs[i] = new Loc(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        dist = new double[N][N];
        for(int i = 0; i < N-1; i++) {
            for(int j = i; j < N; j++) {
                if(i == j)
                    continue;
                double tmp = Math.sqrt((locs[i].x - locs[j].x) * (locs[i].x - locs[j].x) + (locs[i].y - locs[j].y) * (locs[i].y - locs[j].y));
                pq.add(new Loc(i, j, tmp));
            }
        }

        parents = new int[N];
        for(int i = 0; i < N; i++)
            parents[i] = i;

        int cnt = 0;
        while(true) {
            Loc now = pq.poll();
            // 사이클 체크
            if(union(now)) {
                res += now.dist;
                if(++cnt == N-1)
                    break;
            }
        }

        System.out.printf("%.2f\n", res);
        //System.out.println(Math.round(res*100)/100.0);

    }

    public static boolean union(Loc now) {

        int aRoot = find(now.i);
        int bRoot = find(now.j);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;

    }

    public static int find(int n) {

        if(n == parents[n]) return n;
        return parents[n] = find(parents[n]);
    }

}
