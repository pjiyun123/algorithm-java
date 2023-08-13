package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1890 {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N+1][N+1];
        dp[1][1] = 1;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int next = map[i][j];
                if(next == 0) break;
                if(j+next <= N)
                    dp[i][j+next] += dp[i][j];
                if(i+next <= N)
                    dp[i+next][j] += dp[i][j];
            }
        }

        System.out.println(dp[N][N]);

    }

}
