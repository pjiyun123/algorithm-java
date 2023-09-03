package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.IOException;
import java.util.Scanner;

public class n2467 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 용액 수
        int[] liq = new int[N];

        for(int i = 0; i < N; i++)
            liq[i] = sc.nextInt();

        int left = 0;
        int right = N-1;
        int ans = Integer.MAX_VALUE;
        int ansLeft = 0;
        int ansRight = 0;

        while(left < right) {
            int res = liq[left] + liq[right];

            if(res == 0) {
                ansLeft = left;
                ansRight = right;
                break;
            }

            // 0에 더 가깝다면 갱신
            if(Math.abs(res) < ans) {
                ans = Math.abs(res);
                ansLeft = left;
                ansRight = right;
            }

            if(res > 0) right--;
            else left++;

        }

        // 오름차순으로 출력
        System.out.println(liq[ansLeft] + " " + liq[ansRight]);

    }

}
