package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11049 {

    static int N;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 행렬 개수

        // 행렬 입력
        matrix = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            matrix[i][0] = Integer.parseInt(st.nextToken());
            matrix[i][1] = Integer.parseInt(st.nextToken());
        }

        // 결과 출력
        int result = matrixSolution();
        System.out.println(result);

    }

    public static int matrixSolution() {

        int[][] dp = new int[N][N];

        // 인접한 두 행렬의 곱을 계산해서 dp에 저장
        for(int i = 0; i < N - 1; i++) {
            dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
        }

        // 인접하지 않은 두 행렬의 곱을 계산해서 dp에 저장
        for(int gap = 2; gap < N; gap++) {
            for(int i = 0; gap + i < N; i++) {
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]);
                }
            }
        }

        return dp[0][N-1];

    }

}
