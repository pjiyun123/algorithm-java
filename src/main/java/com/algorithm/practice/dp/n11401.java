package com.algorithm.practice.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11401 {

    final static long P = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        long up = factorial(N);
        long down = factorial(K) * factorial(N-K) % P;

        System.out.println(up * pow(down, P-2) % P);

    }

    public static long factorial(long N) {

        long fac = 1L;

        while(N > 1) {
            fac = (fac * N) % P;
            N--;
        }
        return fac;
    }

    public static long pow(long base, long expo) {

        //지수가 1일 경우
        if(expo == 1)
            return base % P;

        long temp = pow(base, expo/2);

        // 지수가 홀수라면
        // base^(expo/2) * base^(expo/2) * base 이다.
        if(expo % 2 == 1)
            return (temp * temp % P) * base % P;

        return temp * temp % P;

    }

}
