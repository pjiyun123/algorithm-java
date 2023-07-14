package com.algorithm.practice.binarySearch;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class n2805 {

    static int N; // 나무의 수
    static long M; // 가져가려는 나무의 길이
    static long[] tree; // 나무의 높이

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextLong();

        tree = new long[N];
        for(int i = 0; i < N; i++)
            tree[i] = sc.nextLong();

        Arrays.sort(tree);

        long lo = 0;
        long hi = tree[N-1];
        long res = -1;

        while(lo < hi) {
            long mid = (lo + hi) / 2;
            // 높이를 높여야 함
            long h = canCut(mid);
            if(h >= M) {
                res = Math.max(mid, res);
                lo = mid + 1;
            }
            // 높이를 낮춰야 함
            else if(h < M)
                hi = mid;
        }

        System.out.println(res);

    }

    public static long canCut(long height) {

        long cnt = 0;
        for(int i = 0; i < N; i++) {
            if(tree[i] - height > 0) {
                cnt += tree[i] - height;
            }
        }

        return cnt;

    }

}
