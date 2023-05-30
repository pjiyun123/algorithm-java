package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11570 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] height = new int[2001];
        for(int i = 1; i <= N; i++)
            height[i] = Integer.parseInt(st.nextToken());

        int[][] dp = new int[2001][2001];
        for(int i = 0; i <= N; i++)
            for(int j = 0; j <= N; j++)
                dp[i][j] = Integer.MAX_VALUE;

        // 상덕이가 마지막으로 부른 음이 1, 희원이가 마지막으로 부른 음이 0 >> 상덕 시작
        dp[1][0] = 0;
        // 상덕이가 마지막으로 부른 음이 0, 희원이가 마지막으로 부른 음이 1 >> 희원 시작
        dp[0][1] = 0;

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(i == j) continue;
                int next = Math.max(i, j) + 1;
                if(j == 0 || i == 0) height[0] = height[next];
                dp[next][j] = Math.min(dp[next][j], dp[i][j]) + Math.abs(height[next]-height[i]);
                dp[i][next] = Math.min(dp[i][next], dp[i][j]) + Math.abs(height[next]-height[j]);
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            ans = Math.min(ans, dp[N][i]);
            ans = Math.min(ans, dp[i][N]);
        }

        System.out.println(ans);

    }

}
