package com.algorithm.practice.cos_pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소용돌이 수
public class n2 {

    static int n; // 격자 크기 (1 이상 100 이하)
    static int[][] arr; // 소용돌이 수 담긴 배열

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        int num = 1;
        int res = 1;
        // n이 홀수일 경우
        if(n % 2 == 1) {
            int k = 4 * (n/2);
            for(int i = 1; i < n; i+=2) {
                for(int j = 1; j <= 2; j++) {
                    num += k;
                    res += num;
                }
                k -= 4;
            }
        }

        // n이 짝수일 경우
        else {
            int s = (n*2) - 2;
            for(int i = 1; i < n; i+=2) {
                if(s == 2) {
                    num += s;
                    res += num;
                }
                else {
                    for(int j = 1; j <= 2; j++) {
                        num += s;
                        res += num;
                    }
                }
                s -= 4;
            }
        }

        System.out.println(res);

    }

}
