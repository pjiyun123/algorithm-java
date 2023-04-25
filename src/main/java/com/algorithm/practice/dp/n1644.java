package com.algorithm.practice.dp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class n1644 {

    public static void main(String[] args) throws IOException {

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 입력받은 자연수 N
        int count = 0; // 연속된 소수의 합들의 수

        if (N < 2) {
            System.out.println("0");
        } else {
            // 1. 소수 리스트 구하기
            int[] prime = new int[N + 1];

            // 소수가 아니면 1, 소수이면 0
            prime[0] = prime[1] = 1;

            for (int i = 2; i*i <= N; i++) {
                if (prime[i] == 0) {
                    for(int j = i*i; j <= N; j+=i)
                        prime[j] = 1;
                }
            }

            ArrayList<Integer> primeList = new ArrayList<>();
            for (int i = 2; i <= N; i++) {
                if (prime[i] == 0)
                    primeList.add(i);
            }

            //System.out.println(primeList);


            // 2. 2차원 배열을 이용하여 소수들의 합 구하기
            /*
            int pls = primeList.size();
            int[][] dp = new int[pls + 2][pls + 2];

            for (int i = 0; i < pls; i++) {
                dp[0][i] = primeList.get(i);
                dp[i + 1][i] = primeList.get(i);
            }

            for (int i = 1; i <= pls; i++) {
                for (int j = i; j < pls; j++) {
                    dp[i][j] = dp[i][j - 1] + dp[0][j];

                    if (dp[i][j] > N)
                        break;
                    if (dp[i][j] == N)
                        count++;
                }
            }
             */
            int start=0, end=0, sum=0;
            while(true) {
                if(sum >= N) sum -= primeList.get(start++);
                else if(end == primeList.size()) break;
                else sum += primeList.get(end++);
                if(N==sum) count++;
            }

            System.out.println(count);

        }
    }

}
