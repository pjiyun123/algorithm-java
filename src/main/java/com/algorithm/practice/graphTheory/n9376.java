package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n9376 {

    static int h;
    static int w;

    public static class Loc {
        int i;
        int j;

        public Loc(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            h = Integer.parseInt(st.nextToken()); // 평면도의 높이
            w = Integer.parseInt(st.nextToken()); // 평면도의 너비

            // 두 명의 죄수의 위치
            Loc[] prisoners = new Loc[2];
            int prisonerIdx = 0;

            // 평면도 입력받기
            char[][] map = new char[h+2][w+2];

            // 가장자리 .으로 채우기
            for(int j = 0; j < h+2; j++) {
                for(int k = 0; k < w+2; k++) {
                    map[j][k] = '.';
                }
            }

            String line = null;
            for(int j = 1; j <= h; j++) {
                line = br.readLine();
                for(int k = 1; k <= w; k++) {
                    // map 채우기
                    map[j][k] = line.charAt(k-1);
                    // 죄수의 위치 기억하기
                    if(line.charAt(k-1) == '$') {
                        prisoners[prisonerIdx++] = new Loc(j, k);
                    }
                }
            }


            /*
            for(int j = 0; j < h + 2; j++) {
                for(int k = 0; k < w + 2; k++) {
                    System.out.print(map[j][k]);
                }
                System.out.println();
            }
             */


            // bfs로 탐색하기
            int[][] prisonerOne = bfs(map, prisoners[0]);
            int[][] prisonerTwo = bfs(map, prisoners[1]);
            int[][] sanggeun = bfs(map, new Loc(0, 0));

            // 문의 개수 구하기
            int[][] fin_dist = new int[h+2][w+2];
            for(int j = 0; j < h+2; j++) {
                for(int k = 0; k < w+2; k++) {
                    fin_dist[j][k] = prisonerOne[j][k] + prisonerTwo[j][k] + sanggeun[j][k];
                }
            }

            int door = Integer.MAX_VALUE; // 열어야 하는 문의 개수
            for(int j = 0; j < h+2; j++) {
                for(int k = 0; k < w+2; k++) {
                    // 벽일 경우 못가니까 고려 x
                    if(map[j][k] == '*')
                        continue;
                    // 문일 경우 동시에 가면 한명만 열면 되니까 -2
                    if(map[j][k] == '#')
                        fin_dist[j][k] -= 2;
                    door = Math.min(door, fin_dist[j][k]);
                }
            }

            sb.append(door);
            sb.append("\n");

        }

        System.out.println(sb);

    }

    public static int[][] bfs(char map[][], Loc loc) {

        int[] di = {0, 0, -1, 1};
        int[] dj = {1, -1, 0, 0};

        Deque<Loc> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h+2][w+2];
        int[][] dist = new int[h+2][w+2]; // 열어온 문의 개수 저장

        q.add(loc);
        while(!q.isEmpty()) {
            Loc temp = q.poll();
            visited[temp.i][temp.j] = true;

            for(int d = 0; d < 4; d++) {
                int ni = temp.i + di[d];
                int nj = temp.j + dj[d];

                if(ni >= 0 && ni < h+2 && nj >=0 && nj < w+2 && !visited[ni][nj] && map[ni][nj] != '*') {
                    if(!visited[ni][nj]){
                        // 문일 경우 이동하므로, dist+1
                        if(map[ni][nj] == '#') {
                            dist[ni][nj] = dist[temp.i][temp.j] + 1;
                            visited[ni][nj] = true;
                            q.addLast(new Loc(ni, nj));
                        }
                        // 문이 아닐 경우 이동하지 않으므로, 그대로
                        else {
                            dist[ni][nj] = dist[temp.i][temp.j];
                            visited[ni][nj] = true;
                            q.addFirst(new Loc(ni, nj));
                        }
                    }
                }
            }
        }

        return dist;

    }

}
