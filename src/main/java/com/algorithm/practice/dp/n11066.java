package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11066 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        int total = 0; // 결과값

        for(int t = 0; t < T; t++) {

            // 파일 개수
            int K = Integer.parseInt(br.readLine());

            // 파일 담을 배열 및 파일 크기의 합을 담을 배열과 dp 배열
            int[] fileSize = new int[K+1];
            int[] sum = new int[K+1];
            int[][] dp = new int[K+1][K+1];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int k = 1; k <= K; k++) {
                fileSize[k] = Integer.parseInt(st.nextToken());
                sum[k] = sum[k-1] + fileSize[k];
            }

            // dp 연산 과정
            for(int n = 1; n <= K; n++) {
                for(int from = 1; from + n <= K; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for(int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to] + sum[to] - sum[from-1]);
                    }
                }
            }

            // 첫번째 파일부터 K번째 파일까지의 합
            System.out.println(dp[1][K]);

        }

    }

}
