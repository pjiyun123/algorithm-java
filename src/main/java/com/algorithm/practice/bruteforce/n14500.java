package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 테트로미노
public class n14500 {

    static int N; // 세로
    static int M; // 가로
    static int[][] map; // 종이에 쓰인 수 저장
    static boolean[][] visited; // 방문 여부 확인
    static int[] dr = {-1, 1, 0, 0}; // 방향 조절 (행)
    static int[] dc = {0, 0, -1, 1}; // 방향 조절 (열)
    static int maxTotal = Integer.MIN_VALUE; // 합의 최대 (결과값)

    public static class Node {
        int r;
        int c;
        int total;
        int cnt;
        public Node(int r, int c, int total, int cnt) {
            this.r = r;
            this.c = c;
            this.total = total;
            this.cnt = cnt;
        }
        /*
        @Override
        public int compareTo(Node o) {
            return this.total - o.total;
        }
         */
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visited[i][j] = true;
                findMax(new Node(i, j, map[i][j], 1));
                visited[i][j] = false;
            }
        }

        System.out.println(maxTotal);

    }

    public static void findMax(Node n) {

        if(n.cnt == 4) {
            maxTotal = Math.max(maxTotal, n.total);
            return;
        }


        for(int d = 0; d < 4; d++) {
            int nextR = n.r + dr[d];
            int nextC = n.c + dc[d];
            if(nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && !visited[nextR][nextC]) {
                // ㅗ 모양 테트로미노를 만들기 위해 2번째 칸에서 탐색 한 번 더 진행
                if(n.cnt == 2) {
                    visited[nextR][nextC] = true;
                    findMax(new Node(n.r, n.c, n.total + map[nextR][nextC], n.cnt+1));
                    visited[nextR][nextC] = false;
                }

                visited[nextR][nextC] = true;
                findMax(new Node(nextR, nextC, n.total + map[nextR][nextC], n.cnt+1));
                visited[nextR][nextC] = false;
            }
        }

    }

}
