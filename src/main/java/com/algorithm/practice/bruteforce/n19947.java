package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투자의 귀재 배주형
public class n19947 {

    static int H; // 초기비용
    static int Y; // 투자기간

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        long[] dp = new long[Y+1];
        dp[0] = H; // 원금 세팅

        for(int i = 1; i <= Y; i++) {
            dp[i] = (int) (dp[i-1] * 1.05);

            if(i >= 3)
                dp[i] = (int) Math.max(dp[i-3] * 1.2, dp[i]);
            if(i >= 5)
                dp[i] = (int) Math.max(dp[i-5] * 1.35, dp[i]);
        }

        System.out.println(dp[Y]);

    }

}
