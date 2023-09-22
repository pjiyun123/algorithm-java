package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


// 아기상어
public class n16236 {

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    static int[][] map;

    public static class Node{
        int r;
        int c;
        int move;
        public Node(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        int[] cur = null;
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    cur = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }

        int time = 0; // 아기 상어가 이동하는데 걸리는 시간
        int size = 2; // 아기 상어 사이즈
        int eat = 0; // 먹은 물고기 수

        while(true) {
            PriorityQueue<int[]> q = new PriorityQueue<>(
                    (o1, o2) -> o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) : o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));
            boolean[][] visited = new boolean[N][N];

            q.add(new int[]{cur[0], cur[1], 0});
            visited[cur[0]][cur[0]] = true;
            boolean check = false; // 아기 상어가 먹이를 먹었는지 체크

            while(!q.isEmpty()) {
                cur = q.poll();

                if(map[cur[0]][cur[1]] != 0 && map[cur[0]][cur[1]] < size) {
                    map[cur[0]][cur[1]] = 0; // 먹은 물고기 제거
                    eat++;
                    time += cur[2];
                    check = true;
                    break;
                }

                for(int d = 0; d < 4; d++) {
                    int nr = cur[0] + dr[d];
                    int nc = cur[1] + dc[d];

                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] > size)
                        continue;

                    q.add(new int[]{nr, nc, cur[2]+1});
                    visited[nr][nc] = true;
                }
            }

            if(!check)
                break;

            if(size == eat) {
                size++;
                eat = 0;
            }
        }

        System.out.println(time);

    }

}
