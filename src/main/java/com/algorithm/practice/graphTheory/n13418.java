package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n13418 {

    static int N; // 건물의 개수
    static int M; // 도로의 개수
    static Edge[] edges;
    static int[] parents;

    public static class Edge implements Comparable<Edge> {

        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
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

        edges = new Edge[M+1];
        for(int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        // 내리막 간선부터 선택하면서 트리를 증가
        parentsInitiate();
        Arrays.sort(edges, 1, M+1, Collections.reverseOrder());
        int cnt1 = 0;
        int zeroCnt1 = 0;
        for(Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                if(edge.weight == 0)
                    zeroCnt1++;
                if(++cnt1 == N) break;
            }
        }

        // 오르막 간선부터 선택하면서 트리를 증가
        parentsInitiate();
        Arrays.sort(edges, 1, M+1);
        int cnt2 = 0;
        int zeroCnt2 = 0;
        for(Edge edge : edges) {
            if(union(edge.from, edge.to)) {
                if(edge.weight == 0)
                    zeroCnt2++;
                if(++cnt2 == N) break;
            }
        }

        System.out.println(zeroCnt2*zeroCnt2 - zeroCnt1*zeroCnt1);

    }

    public static void parentsInitiate() {
        parents = new int[N+1];
        // 정점 초기화 (자신을 대표자로)
        for(int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        // 사이클 존재시 false
        if(aRoot == bRoot) return false;
        // 사이클이 생성되지 않는다면 union 수행
        parents[bRoot] = aRoot;
        return true;
    }

}
