package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.IOException;
import java.util.*;

public class n1300 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 배열의 크기
        int K = sc.nextInt(); // 찾는 수의 순서

        long lo = 1;
        long hi = K;

        while(lo < hi) {
            long mid = (lo + hi) / 2;
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid/i, N);
            }

            if(K <= count) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);

    }

}
