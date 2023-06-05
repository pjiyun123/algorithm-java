package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n5557 {

    static int N;
    static int[] num;
    static long[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        num = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[N][21];
        dp[0][num[0]] = 1;
        cal();
        System.out.println(dp[N-2][num[N-1]]);

    }

    public static void cal() {

        int plus;
        int minus;

        for(int i = 1; i < N-1; i++) {
            for(int j = 0; j <= 20; j++) {
                if(dp[i-1][j] != 0) {
                    plus = j + num[i];
                    minus = j - num[i];
                    if(plus >= 0 && plus <= 20)
                        dp[i][plus] += dp[i-1][j];
                    if(minus >= 0 && minus <= 20)
                        dp[i][minus] += dp[i-1][j];
                }
            }
        }

    }

}
