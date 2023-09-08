package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n1600 {

    static int K; // 말처럼 움직일 수 있는 횟수
    static int W; // 가로 길이
    static int H; // 세로 길이
    static int[][] map; // 주어진 격자판
    static int[] horseR = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] horseC = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] monkeyR = {0, 1, 0, -1};
    static int[] monkeyC = {1, 0, -1, 0};
    static boolean[][][] visited;

    public static class Node {
        int r;
        int c;
        int cnt;
        int k;
        public Node(int r, int c, int cnt, int k) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[H][W][K+1];
        int ans = move(0, 0);


        if(W == 1 && H == 1)
            System.out.println(0);
        else
            System.out.println(ans);

    }

    public static int move(int r, int c) {

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r, c, 0, K));
        visited[r][c][K] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == H - 1 && cur.c == W - 1)
                return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + monkeyR[i];
                int nc = cur.c + monkeyC[i];
                if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc][cur.k] && map[nr][nc] == 0) {
                    visited[nr][nc][cur.k] = true;
                    q.offer(new Node(nr, nc, cur.cnt + 1, cur.k));
                }
            }

            if (cur.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nr = cur.r + horseR[i];
                    int nc = cur.c + horseC[i];
                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc][cur.k - 1] && map[nr][nc] == 0) {
                        visited[nr][nc][cur.k - 1] = true;
                        q.offer(new Node(nr, nc, cur.cnt + 1, cur.k - 1));
                    }
                }
            }
        }
        return -1;
    }

}
