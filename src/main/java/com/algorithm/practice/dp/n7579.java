package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n7579 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N]; // 메모리 크기
        int[] C = new int[N]; // 메모리당 비용
        int[] dp = new int[10001]; // dp 배열에서 인덱스는 비용, 값은 메모리 크기의 합

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        // dp 배열 -1로 초기화
        Arrays.fill(dp, -1);

        for(int i = 0; i < N; i++) {
            int cost = C[i];
            for(int j = 10000; j >= cost; j--) {
                if(dp[j-cost] != -1) {
                    if(dp[j-cost] + A[i] > dp[j])
                        dp[j] = dp[j-cost] + A[i];
                }
            }

            if(dp[cost] < A[i])
                dp[cost] = A[i];
        }

        for(int i = 0; i < 10001; i++) {
            if(dp[i] >= M) {
                System.out.println(i);
                break;
            }
        }

    }

}
