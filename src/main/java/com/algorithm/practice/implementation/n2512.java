package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2512 {

    static int N; // 지방 수
    static long M; // 총 예산
    static int[] arr; // 각 지방의 요청 예산

   public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int total = 0;
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }
        M = Long.parseLong(br.readLine());

       long maxBudgetLine = 0;

       // 주어진 예산으로 모든 지역에 배정 가능한 경우
       if(total <= M) {
            Arrays.sort(arr);
            maxBudgetLine = arr[N-1];
       }

       // 예산이 모자란 경우
       else {
           boolean[] completed = new boolean[N];
           long totalBudget = M;
           int cnt = N;
           while (true) {
               long res = totalBudget / cnt;
               long tmp = 0;
               if (res == 0)
                   break;

               for (int i = 0; i < N; i++) {
                   if (completed[i])
                       continue;

                   if (res < arr[i]) {
                       arr[i] -= res;
                       tmp += res;
                   } else {
                       tmp += arr[i];
                       cnt--;
                       completed[i] = true;
                   }
               }
               totalBudget -= tmp;
               maxBudgetLine += res;
           }

       }

       System.out.println(maxBudgetLine);

    }

}
