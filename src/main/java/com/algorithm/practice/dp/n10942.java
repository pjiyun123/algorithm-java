package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10942 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 자연수 개수
        int[] arr = new int[N+1]; // 자연수 담을 배열
        boolean[][] dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 한 개만 있는 수는 무조건 true
        for(int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 두 개 있는 수열에 대해 true/false 지정
        for(int i = 1; i < N; i++) {
            if(arr[i] == arr[i+1])
                dp[i][i+1] = true;
        }

        // 그 이상의 수의 수열에 대해서 true/false 지정
        for(int i = 2; i < N; i++) {
            for(int j = 1; j <= N-i; j++) {
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1])
                    dp[j][j+i] = true;
            }
        }

        int M = Integer.parseInt(br.readLine()); // 질문 개수
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(dp[S][E])
                sb.append("1\n");
            else
                sb.append("0\n");
        }

        System.out.println(sb);

    }

}
