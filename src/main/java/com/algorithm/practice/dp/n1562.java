package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n1562 {

    static int MOD = 1000000000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][][] dp = new long[N+1][10][1<<10]; // i번째 자리에 j라는 수를 기록. k는 비트마스크 정보를 기록.


        // 1번째 자리에 1~9까지의 수를 기록하고 사용한 수를 비트마스크로 표시
        for(int n = 1; n <= 9; n++)
            dp[1][n][1<<n] = 1;

        for(int i = 2; i <= N; i++) {
            for(int j = 0; j <= 9; j++) {
                for(int visit = 0; visit < (1 << 10); visit++) {
                    // newVisit은 기존의 visit에 j 수 방문 추가
                    int newVisit = visit | (1<<j);
                    // 0을 사용할 땐 +1 연산만 가능
                    if(j == 0)
                        dp[i][j][newVisit] += dp[i-1][j+1][visit] % MOD;
                    // 9를 사용할 땐 -1 연산만 가능
                    else if(j == 9)
                        dp[i][j][newVisit] += dp[i-1][j-1][visit] % MOD;
                    else
                        dp[i][j][newVisit] += dp[i-1][j+1][visit] % MOD + dp[i-1][j-1][visit] % MOD;

                    dp[i][j][newVisit] %= MOD;
                }
            }
        }

        // 마지막 N번째 자리까지 계단 수를 완성했을 때
        // 모든 수를 다 사용한 경우(visit이 1111111111d)를 카운트
        // dp[N][0][1111111111]부터 dp[N][9][1111111111]까지 더하기
        long sum = 0;
        for(int n = 0; n <= 9; n++) {
            sum += dp[N][n][(1<<10)-1] % MOD;
            sum %= MOD;
        }

        System.out.println(sum);

    }
}
