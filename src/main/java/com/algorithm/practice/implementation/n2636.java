package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2636 {

    static int row; // 가로
    static int col; // 세로

    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int totalTime; // 치즈가 모두 녹는데 걸리는 시간
    static int cheeseCnt; // 시간마다 녹은 치즈

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for(int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[row][col];

        for(totalTime = 0; isCheese(); totalTime++) {
            for (boolean[] arr : visited) {
                Arrays.fill(arr, false);
            }
            visited[0][0] = true;
            cheeseCnt = 0;
            dfs(0, 0);
        }

        System.out.println(totalTime);
        System.out.println(cheeseCnt);

    }

    public static boolean isCheese() {

        // 공기와 맞닿은 치즈를 공기로 바꿔주기
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] == 2)
                    map[i][j] = 0;
            }
        }

        // 판 위에 치즈가 존재하는지 체크
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(map[i][j] == 1)
                    return true;
            }
        }

        return false;

    }

    public static void dfs(int r, int c) {

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nr >= row || nc < 0 || nc >= col)
                continue;

            if(!visited[nr][nc]) {
                visited[nr][nc] = true;
                // 공기가 치즈와 맞닿는다면 녹이기
                if(map[nr][nc] == 1) {
                    map[nr][nc] = 2;
                    cheeseCnt++;
                }
                else {
                    dfs(nr, nc);
                }
            }
        }

    }

}
