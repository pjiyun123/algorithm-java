package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.Scanner;

public class n1722 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long k; // 순열의 순서

        long[] facArr = new long[21]; // 팩토리얼을 담는 배열
        int[] check = new int[21]; // 순열 내에서 중복된 수를 사용하지 않기 위함
        int[] proArr = new int[N+1]; // 2번 문제의 순열을 담는 배열
        int[] resultArr = new int[N+1]; // 1번 문제의 결과 순열을 담는 배열

        // 팩토리얼 배열 채우기
        facArr[0] = 1;
        for(int i = 1; i <= 20; i++) {
            facArr[i] = i * facArr[i-1];
            check[i] = 0;
        }

        int pro = sc.nextInt(); // 문제 번호 입력받기

        // 1번 문제일 경우 (k번째 순열을 출력)
        if(pro == 1) {
            // 순열 순서 입력받기
            k = sc.nextLong();

            for(int i = 0; i < N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(check[j] == 1)
                        continue;

                    if(facArr[N-i-1] < k) {
                        k -= facArr[N-i-1];
                    }
                    else {
                        resultArr[i] = j;
                        check[j] = 1;
                        break;
                    }
                }
            }

            // 해당 순열 출력
            for(int i = 0; i < N; i++) {
                System.out.print(resultArr[i] + " ");
            }

        }

        // 2번 문제일 경우 (몇번째 순열인지 출력)
        else {
            // 순열 입력받기
            for(int i = 0; i < N; i++) {
                proArr[i] = sc.nextInt();
            }

            // 순열은 1부터 시작
            long ans = 1;
            for(int i = 0; i < N; i++) {
                for(int j = 1; j < proArr[i]; j++) {
                    if(check[j] == 0) {
                        ans += facArr[N-i-1];
                    }
                }
                check[proArr[i]] = 1;
            }

            // 해당 순열이 몇 번째인지 출력
            System.out.println(ans);

        }

    }

}
