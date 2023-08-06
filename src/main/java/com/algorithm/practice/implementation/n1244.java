package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.Scanner;

public class n1244 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 스위치 개수
        int[] lights = new int[N+2];
        for(int i = 1; i <= N; i++)
            lights[i] = sc.nextInt();
        lights[N+1] = -1;

        int M = sc.nextInt(); // 학생 수
        for(int i = 0; i < M; i++) {
            int sex = sc.nextInt(); // 성별
            int num = sc.nextInt(); // 받은 스위치 번호
            // 남자
            if(sex == 1) {
                int k = 1;
                while(num*k <= N) {
                    if(lights[num*k] == 0) lights[num*k] = 1;
                    else lights[num*k] = 0;
                    k++;
                }
            }
            //여자
            else {
                if(num == 1 || num == N) {
                    if(lights[num] == 0) lights[num] = 1;
                    else lights[num] = 0;
                }
                else {
                    int lidx = num-1;
                    int ridx = num+1;
                    while(true) {
                        if(lidx <= 0 || ridx > N)
                            break;
                        if(lights[lidx] == lights[ridx]) {
                            lidx--;
                            ridx++;
                        }
                        else
                            break;
                    }
                    for(int idx = lidx+1; idx <= ridx-1; idx++) {
                        if(lights[idx] == 0) lights[idx] = 1;
                        else lights[idx] = 0;
                    }
                }
            }
        }

        if(N <= 20) {
            for(int i = 1; i <= N; i++) {
                System.out.print(lights[i] + " ");
            }
        }
        else {
            loop: for(int i = 0; i <= N / 20; i++) {
                for(int j = 1; j <= 20; j++) {
                    if(lights[20*i + j] == -1)
                        break loop;
                    System.out.print(lights[20*i + j] + " ");
                }
                System.out.println();
            }
        }



    }

}
