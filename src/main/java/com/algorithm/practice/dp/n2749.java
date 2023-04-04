package com.algorithm.practice.dp;

import java.io.IOException;
import java.util.Scanner;

public class n2749 {

    // 1,000,000(백만)으로 나눈 나머지를 출력
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int pisano = 1500000;
        long[] fibo = new long[pisano+1];
        long n = sc.nextLong() % pisano;


        fibo[0] = 0;
        fibo[1] = 1;
        for(int i = 2; i <= pisano; i++) {
            fibo[i] = (fibo[i-1] + fibo[i-2]) % 1000000;
        }

        System.out.println(fibo[(int)n]);

    }

}
