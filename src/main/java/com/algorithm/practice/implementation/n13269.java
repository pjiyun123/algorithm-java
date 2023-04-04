package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n13269 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 세로
        int M = Integer.parseInt(st.nextToken()); // 가로

        int[][] up = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < M; j++) {
                up[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] front = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i++) {
            front[i] = Integer.parseInt(st.nextToken());
        }

        int[] rightSide = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            rightSide[i] = Integer.parseInt(st.nextToken());
        }

        int[][] ans = new int[N][M];
        boolean[][] fix = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(up[i][j] == 0) {
                    fix[i][j] = true;
                }
            }
        }

        // 모순 확인하기
        boolean error= false;
        int frontMax = 0;
        int sideMax = 0;
        for(int i = 0; i < M; i++){
            if(frontMax < front[i])
                frontMax = front[i];
        }
        for(int i = 0; i < N; i++){
            if(sideMax < rightSide[i])
                sideMax = rightSide[i];
        }
        if(frontMax != sideMax) {
            error = true;
        }

        for(int i = 0; i < N; i++) {
            int sideTrueCount = 0;
            for(int j = 0; j < M; j++) {
                if(fix[i][j] == true)
                    sideTrueCount++;
            }
            if(sideTrueCount == M)
                if(rightSide[N-i-1] != 0){
                    error = true;
                }
        }

        for(int i = 0; i < M; i++) {
            int frontTrueCount = 0;
            for(int j = 0; j < N; j++) {
                if(fix[j][i] == true)
                    frontTrueCount++;
            }
            if(frontTrueCount == N)
                if(front[i] != 0){
                    error = true;
                }
        }

        if(error == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (fix[i][j] != true) {
                        ans[i][j] = Math.min(front[j], rightSide[N - i - 1]);
                    }
                }
            }

            // 내가 만든 쌓기나무를 앞과 옆에서 보고, 문제에서 제시한 것과 같은지 체크
            int[] sideCheck = new int[N];
            for (int i = 0; i < N; i++) {
                int sideCheckMax = 0;
                for (int j = 0; j < M; j++) {
                    if (sideCheckMax < ans[i][j]) {
                        sideCheckMax = ans[i][j];
                    }
                }
                sideCheck[N-i-1] = sideCheckMax;
            }
            for(int i = 0; i < N; i++) {
                if(sideCheck[i] != rightSide[i])
                    error = true;
            }

            int[] frontCheck = new int[M];
            for(int i = 0; i < M; i++) {
                int frontCheckMax = 0;
                for(int j = 0; j < N; j++) {
                    if(frontCheckMax < ans[j][i]) {
                        frontCheckMax = ans[j][i];
                    }
                }
                frontCheck[i] = frontCheckMax;
            }
            for(int i = 0; i < M; i++) {
                if(frontCheck[i] != front[i])
                    error = true;
            }

        }

        // 결과 출력
        if(error == false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(ans[i][j] + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println(-1);
        }

    }

}
