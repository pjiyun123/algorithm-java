package com.algorithm.practice.bitmasking;

import java.io.IOException;
import java.util.Scanner;

public class n9527 {

    static long A;
    static long B;
    static long[] DP = new long[55]; // 1의 개수 누적합 저장 배열

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        A = sc.nextLong();
        B = sc.nextLong();

        setDp();
        long result = cal(B) - cal(A-1);
        System.out.println(result);

    }

    // DP[n] = DP[n-1] x 2 + 2^n
    public static void setDp() {

        DP[0] = 1;
        for(int i = 1; i < 55; i++) {
            DP[i] = (DP[i-1] << 1) + (1L << i);
        }

    }

    public static Long cal(Long n) {

        long count = n & 1;
        int size = (int) (Math.log(n) / Math.log(2));
        for(int i = size; i > 0; i--) {
            if((n & (1L << i)) != 0L) {
                count += DP[i-1] + (n - (1L<<i) + 1);
                n -= (1L << i);
            }
        }

        return count;

    }

}
