package com.algorithm.practice.dp;

import java.io.IOException;
import java.util.Scanner;

public class n9084 {

    public static void main(String[] args) throws IOException {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        int[] coin;
        int[][] dp;
        int t = sc.nextInt();
        for (int k = 0; k < t; k++) {
            int N = sc.nextInt();
            coin = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                coin[i] = sc.nextInt();
            }
            int goal = sc.nextInt();

            dp = new int[N+1][goal+1];
            for(int i = 1; i <= N; i++) {
                if(coin[i] > goal)
                    continue;
                dp[i][coin[i]] += 1;
            }

            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= goal; j++) {
                    dp[i][j] += dp[i-1][j];
                    if(j-coin[i] >= 0) {
                        dp[i][j] += dp[i][j - coin[i]];
                    }
                }
            }

            sb.append(dp[N][goal]);
            sb.append("\n");

        }

        System.out.println(sb);

    }

}
