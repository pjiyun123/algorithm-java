package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파이프 옮기기 1
public class n17070 {

    static int N; // 집 크기
    static int[][] map; // 파이프 지도
    static int cnt = 0; // (N,N)으로 이동시키는 방법 수

    public static class pipe {
        int leftR;
        int leftC;
        int rightR;
        int rightC;
        int dir; // 0은 가로, 1은 세로, 2는 대각선
        public pipe(int leftR, int leftC, int rightR, int rightC, int dir) {
            this.leftR = leftR;
            this.leftC = leftC;
            this.rightR = rightR;
            this.rightC = rightC;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+2][N+2];
        for(int i = 0; i < N+2; i++)
            Arrays.fill(map[i], 1);

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(new pipe(1, 1, 1, 2, 0));
        System.out.println(cnt);


    }

    public static void dfs(pipe p1) {

        if(p1.rightR == N && p1.rightC == N) {
            cnt++;
            return;
        }

        // 가로
        if(p1.dir == 0) {
            // 1. 가로
            if(map[p1.rightR][p1.rightC+1] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR, p1.rightC+1, 0));

            // 2. 대각선
            if(map[p1.rightR+1][p1.rightC+1] != 1 && map[p1.rightR+1][p1.rightC] != 1 && map[p1.rightR][p1.rightC+1] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR+1, p1.rightC+1, 2));
        }

        // 세로
        else if(p1.dir == 1) {
            // 1. 세로
            if(map[p1.rightR+1][p1.rightC] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR+1, p1.rightC, 1));

            // 2. 대각선
            if(map[p1.rightR+1][p1.rightC+1] != 1 && map[p1.rightR+1][p1.rightC] != 1 && map[p1.rightR][p1.rightC+1] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR+1, p1.rightC+1, 2));

        }

        // 대각선
        else if(p1.dir == 2) {
            // 1. 가로
            if(map[p1.rightR][p1.rightC+1] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR, p1.rightC+1, 0));

            // 2. 세로
            if(map[p1.rightR+1][p1.rightC] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR+1, p1.rightC, 1));

            // 3. 대각선
            if(map[p1.rightR+1][p1.rightC+1] != 1 && map[p1.rightR+1][p1.rightC] != 1 && map[p1.rightR][p1.rightC+1] != 1)
                dfs(new pipe(p1.rightR, p1.rightC, p1.rightR+1, p1.rightC+1, 2));
        }

    }

}
