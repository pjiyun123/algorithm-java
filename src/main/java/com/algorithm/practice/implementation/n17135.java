package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 캐슬디펜스
public class n17135 {

    static int N; // 행
    static int M; // 열
    static int D; // 궁수 공격 거리 제한

    static int[][] mapOri;
    static int[][] map;
    static int enemyCnt = 0; // 적의 수
    static int[] arr;
    static boolean[] visited;
    static ArrayList<int[]> gungsu;
    static int maxCnt = 0; // 궁수가 최대로 제거할 수 있는 적의 수

    static int[][][] moveDist = {
            {{-1, 0}},
            {{-1, -1}, {-2, 0}, {-1, 1}},
            {{-1, -2}, {-2, -1}, {-3, 0}, {-2, 1}, {-1, 2}},
            {{-1, -3}, {-2, -2}, {-3, -1}, {-4, 0}, {-3, 1}, {-2, 2}, {-1, 3}},
            {{-1, -4}, {-2, -3}, {-3, -2}, {-4, -1}, {-5, 0}, {-4, 1}, {-3, 2}, {-2, 3}, {-1, 4}},
            {{-1, -5}, {-2, -4}, {-3, -3}, {-4, -2}, {-5, -1}, {-6, 0}, {-5, 1}, {-4, 2}, {-3, 3}, {-2, 4}, {-1, 5}},
            {{-1, -6}, {-2, -5}, {-3, -4}, {-4, -3}, {-5, -2}, {-6, -1}, {-7, 0}, {-6, 1}, {-5, 2}, {-4, 3}, {-3, 4}, {-2, 5}, {-1, 6}},
            {{-1, -7}, {-2, -6}, {-3, -5}, {-4, -4}, {-5, -3}, {-6, -2}, {-7, -1}, {-8, 0}, {-7, 1}, {-6, 2}, {-5, 3}, {-4, 4}, {-3, 5}, {-2, 6}, {-1, 7}},
            {{-1, -8}, {-2, -7}, {-3, -6}, {-4, -5}, {-5, -4}, {-6, -3}, {-7, -2}, {-8, -1}, {-9, 0}, {-8, 1}, {-7, 2}, {-6, 3}, {-5, 4}, {-4, 5}, {-3, 6}, {-2, 7}, {-1, 8}},
            {{-1, -9}, {-2, -8}, {-3, -7}, {-4, -6}, {-5, -5}, {-6, -4}, {-7, -3}, {-8, -2}, {-9, -1}, {-10, 0}, {-9, 1}, {-8, 2}, {-7, 3}, {-6, 4}, {-5, 5}, {-4, 6}, {-3, 7}, {-2, 8}, {-1, 9}}
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        mapOri = new int[N+1][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                mapOri[i][j] = Integer.parseInt(st.nextToken());
                if(mapOri[i][j] == 1) enemyCnt++;
            }
        }

        // 궁수 자리 구하기 - 조합
        arr = new int[M];
        visited = new boolean[M];
        for(int i = 0; i < M; i++)
            arr[i] = i;
        gungsu = new ArrayList<>();
        comb(arr, visited, 0, M, 3);

        // 궁수 배치하여 공격하기
        map = new int[N+1][M];
        for(int i = 0; i < gungsu.size(); i++) {
            // 적 배치
            for(int r = 0; r < N; r++) {
                map[r] = Arrays.copyOf(mapOri[r], M);
            }

            // 궁수 배치
            for(int j = 0; j < M; j++) map[N][j] = 0;
            for(int j = 0; j < 3; j++) map[N][gungsu.get(i)[j]] = 2;


            // 공격
            int total = 0; // 궁수의 공격으로 제거 가능한 적의 수
            int moveCnt = 0; // 아래쪽 이동으로 제외된 적의 수
            while(total + moveCnt != enemyCnt) {
                int cnt = 0;
                // 궁수 당 (동시) 공격
                for(int j = 0; j < 3; j++)
                    attack(N, gungsu.get(i)[j]);
                for(int r = 0; r < N; r++) {
                    for(int c = 0; c < M; c++) {
                        if(map[r][c] == -1) {
                            map[r][c] = 0;
                            cnt++;
                        }
                    }
                }
                total += cnt;

                // 적 아래로 한 칸 이동
                moveCnt += move();
            }

            // 최대로 제거할 수 있는 적의 수 갱신
            if(total > maxCnt)
                maxCnt = total;
        }

        System.out.println(maxCnt);

    }

    public static void comb(int[] arr, boolean[] visited, int depth, int n, int r) {
        if (r == 0) {
            int[] a = new int[3];
            int k = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i])
                    a[k++] = arr[i];
            }
            gungsu.add(a);
            return;
        }

        if (depth == n)
            return;

        visited[depth] = true;
        comb(arr, visited, depth + 1, n, r - 1);

        visited[depth] = false;
        comb(arr, visited, depth + 1, n, r);
    }

    public static void attack(int gungsuR, int gungsuC) {

        int nowD = 0;
        loop: while(nowD < D) {
            int idx = 0;
            while(idx < 2*nowD+1) {
                int rr = gungsuR + moveDist[nowD][idx][0];
                int cc = gungsuC + moveDist[nowD][idx][1];
                if (rr >= 0 && cc >= 0 && cc < M && map[rr][cc] != 0) {
                    map[rr][cc] = -1;
                    break loop;
                }
                idx++;
            }
            nowD++;
        }

    }

    public static int move() {

        int delEnemyCnt = 0;

        for(int i = N-1; i >= 0; i--) {
            for(int j = 0; j < M; j++) {
                if(i == N-1) {
                    if(map[i][j] == 1)
                        delEnemyCnt++;
                }
                else if(i == 0) {
                    map[i+1][j] = map[i][j];
                    map[i][j] = 0;
                }
                else
                    map[i+1][j] = map[i][j];
            }
        }

        return delEnemyCnt;

    }

}
