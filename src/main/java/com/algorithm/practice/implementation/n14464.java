package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 소가 길을 건너간 이유 4
public class n14464 {

    static int C;
    static int N;

    static int[] T; // 닭이 도와줄 수 있는 초
    static int[][] AB; // 소가 닭의 도움을 받을 수 있는 초 범위

    public static class Node implements Comparable<Node> {
        int A;
        int B;

        public Node(int A, int B) {
            this.A = A;
            this.B = B;
        }

        @Override
        public int compareTo(Node o) {
            return this.B - o.B;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        T = new int[C];
        for(int i = 0; i < C; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }

        AB = new int[N][2];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b));
        }

        Arrays.sort(T);

        int res = 0;
        while(!pq.isEmpty()) {
            boolean isHelp = false;

            for(int i = 0; i < C; i++) {
                if (T[i] >= pq.peek().A && T[i] <= pq.peek().B && T[i] > 0) {
                    res++;
                    pq.poll();
                    isHelp = true;
                    T[i] = -1;
                    break;
                }
            }

            if(!isHelp)
                pq.poll();
        }

        System.out.println(res);

    }
}
