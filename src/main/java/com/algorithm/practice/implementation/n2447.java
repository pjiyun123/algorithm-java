package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.Scanner;

public class n2447 {

    static int N;
    static char[][] star;

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        star = new char[N][N];

        make(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(star[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }

    public static void make(int x, int y, int N, boolean blank) {

        // 공백일 경우
        if(blank) {
            for(int i = x; i < x + N; i++) {
                for(int j = y; j < y + N; j++) {
                    star[i][j] = ' ';
                }
            }
            return;
        }

        // 더이상 쪼갤 수 없는 블록일 때
        if(N == 1) {
            star[x][y] = '*';
            return;
        }

        // N=27일 경우 한 블록 사이즈는 9이듯, size는 해당 블록의 한 칸을 담을 변수를 의미
        // count는 별 출력 누적을 의미

        int size = N / 3;
        int count = 0;
        for (int i = x; i < x + N; i += size) {
            for (int j = y; j < y + N; j += size) {
                count++;
                if (count == 5) { // 공백 칸일 경우
                    make(i, j, size, true);
                } else {
                    make(i, j, size, false);
                }
            }
        }

    }

}
