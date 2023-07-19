package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n5373 {

    static char[][] up;
    static char[][] down;
    static char[][] left = new char[3][3];
    static char[][] right = new char[3][3];
    static char[][] front = new char[3][3];
    static char[][] back = new char[3][3];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int i = 0; i < T; i++) {
            intiate();
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                String dir = st.nextToken();
                rotate(dir);
            }
            printUp();
        }

    }

    public static void intiate() {
        up = new char[][]{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}};
        down = new char[][]{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}};
        left = new char[][]{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}};
        right = new char[][]{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}};
        front = new char[][]{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}};
        back = new char[][]{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
    }

    public static void rotate(String dir) {
        // 위 (U)
        if(dir.charAt(0) == 'U') {
            // 시계 방향
            if(dir.charAt(1) == '+') {

            }
            // 반시계 방향
            else {

            }
        }

        // 왼 (L)
        if(dir.charAt(0) == 'L') {
            // 시계 방향
            if(dir.charAt(1) == '+') {

            }
            // 반시계 방향
            else {
                // 위 변경 -> 위의 왼쪽줄이 앞의 왼쪽줄로
                up[0][0] = front[0][0];
                up[1][0] = front[1][0];
                up[2][0] = front[2][0];
                // 앞 변경 -> 앞의 왼쪽줄이 아래의 왼쪽줄로
                front[0][0] = down[0][0];
                front[1][0] = down[1][0];
                front[2][0] = down[2][0];
                // 아래 변경 -> 아래의 왼쪽줄이 뒤의 왼쪽줄로
                down[0][0] = back[0][0];
                down[1][0] = back[1][0];
                down[2][0] = back[2][0];
                // 뒤 변경 -> 뒤의 왼쪽줄이 위의 왼쪽줄로
                back[0][0] = up[0][0];
                back[1][0] = up[1][0];
                back[2][0] = up[2][0];
            }
        }

        // 앞 (F)
        if(dir.charAt(0) == 'F') {
            // 시계 방향
            if(dir.charAt(1) == '+') {
                // 위 변경 -> 위의 아래줄이 왼의 아래줄로
                up[2][0] = left[2][0];
                up[2][1] = left[2][1];
                up[2][2] = left[2][2];
                // 오 변경 -> 오의 아래줄이 위의 아래줄로
                right[2][0] = up[2][0];
                right[2][1] = up[2][1];
                right[2][2] = up[2][2];
                // 아래 변경 -> 아래의 아래줄이 오의 아래줄로
                down[2][0] = right[2][0];
                down[2][1] = right[2][1];
                down[2][2] = right[2][2];
                // 왼 변경 -> 왼의 아래줄이 아래의 아래줄로
                left[2][0] = down[2][0];
                left[2][1] = down[2][1];
                left[2][2] = down[2][2];
            }
            // 반시계 방향
            else {

            }
        }

        // 뒤 (B)
        if(dir.charAt(0) == 'B') {
            // 시계 방향
            if(dir.charAt(1) == '+') {
                // 위 변경 -> 위의 위줄이 오른의 오른줄로
                up[0][0] = right[2][0];
                up[0][1] = right[2][1];
                up[0][2] = right[2][2];
                // 오 변경 -> 오의 위줄이 앞의 위줄로

                // 아래 변경

                // 왼 변경 -> 왼의
            }
            // 반시계 방향
            else {

            }
        }

    }

    public static void printUp() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.print(up[i][j]);
            }
            System.out.println();
        }
    }

}
