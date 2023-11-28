package com.algorithm.practice.cos_pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class n1 {

    static int num; // 입력되는 매개변수 (자연수)

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());

        // num에 1 더하기
        num += 1;

        // 0 없애기
        int k = 1;
        while((int) Math.pow(10, k) <= num) {
            if(num % (int) Math.pow(10, k) <= (int) Math.pow(10, k-1)) {
                num += (int) Math.pow(10, k-1);
                System.out.println(num);
            }
            k++;
        }

        System.out.println(num);
    }

}
