package com.algorithm.practice.scpc;

import java.io.IOException;
import java.util.Scanner;

public class n1 {

    static int Answer;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int test_case = 0; test_case < T; test_case++) {

            int N = sc.nextInt(); // 사과 개수
            int A = sc.nextInt();
            int B = sc.nextInt();

            Answer = 0;

            int box1 = Math.min(A, B);
            int box2 = Math.max(A, B);
            while(N != 0) {
                if(N % box1 != 0) {
                    N -= box2;
                    Answer++;
                }
                else {
                    Answer += N / box1;
                    break;
                }
            }

            System.out.println("Case #"+(test_case+1));
            System.out.println(Answer);
        }
    }

}
