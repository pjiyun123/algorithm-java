package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n4811 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= 30; i++) {
            long cnt = 0;
            for(int j = 0; j < i; j++)
                cnt += dp[j] * dp[i-1-j];
            dp[i] = cnt;
        }

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0)
                break;

            sb.append(dp[n]);
            sb.append("\n");
        }

        System.out.println(sb);

    }

}
