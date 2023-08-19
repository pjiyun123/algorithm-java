package com.algorithm.practice.dp;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class n2565 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 전깃줄 개수

        int[][] wire = new int[N][2];
        int[] dp = new int[N];

        for(int i = 0; i < N; i++) {
            wire[i][0] = sc.nextInt();
            wire[i][1] = sc.nextInt();
        }

        // A전봇대 기준으로 오름차순 정렬 (2차원 배열 정렬)
        Arrays.sort(wire, new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < dp.length; i++) {
            dp[i] = 1; // 최소 개수인 1로 초기화
            for(int j = 0; j < i; j++) {
                if(wire[i][1] > wire[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);

    }

}
