package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n15683 {

    static int N, M;
    static int[][] map;
    static PriorityQueue<cctv> pq = new PriorityQueue<>(new Comparator<cctv>() {
        @Override
        public int compare(cctv o1, cctv o2) {
            int o1Result = o1.num;
            int o2Result = o2.num;
            return o2Result-o1Result;
        }
    });



    public static class cctv {
        int i, j, num;

        public cctv(int num, int i, int j) {
            this.num = num;
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // cctv인 경우 우선순위 큐에 넣기
                if(map[i][j] != 0 && map[i][j] != 6) {
                    pq.offer(new cctv(map[i][j], i, j));
                }
            }
        }

        for(int i = 0; i < pq.size(); i++) {
            cctv tmp = pq.poll();
            System.out.println(tmp.num + " " + tmp.i + " " + tmp.j);
        }

    }
}
