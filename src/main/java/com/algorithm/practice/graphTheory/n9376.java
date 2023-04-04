package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n9376 {

    public static class Loc implements Comparable<Loc> {
        int i;
        int j;
        int door;

        public Loc(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public Loc(int i, int j, int door) {
            this.i = i;
            this.j = j;
            this.door = door;
        }

        @Override
        public int compareTo(Loc o) {
            return Integer.compare(this.door, o.door);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for(int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken()); // 평면도의 높이
            int w = Integer.parseInt(st.nextToken()); // 평면도의 너비
            int door = 0; // 열어야 하는 문의 개수

            // 두 명의 죄수의 위치
            Loc[] prisoners = new Loc[2];
            int prisonerIdx = 0;

            // 평면도 입력받기
            char[][] map = new char[h+2][w+2]; // 가장자리 0으로 채우기
            String line = null;
            for(int j = 0; j < h; j++) {
                line = br.readLine();
                for(int k = 0; k < w; k++) {
                    // map 채우기
                    map[j+1][k+1] = line.charAt(k);
                    // 죄수의 위치 기억하기
                    if(line.charAt(k) == '$') {
                        prisoners[prisonerIdx++] = new Loc(j+1, k+1);
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
            int[][] prisonerOne, prisonerTwo, sanggeun;

            prisonerOne = bfs(map, prisoners[0], h, w);
            prisonerTwo = bfs(map, prisoners[1], h, w);
            sanggeun = bfs(map, new Loc(0, 0), h, w);

            //
            door = getMinSum(prisonerOne, prisonerTwo, sanggeun, map);
            System.out.println(door);

        }

    }

    public static int[][] bfs(char map[][], Loc prisoner, int h, int w) {

        int[] di = {0, 0, -1, 1};
        int[] dj = {1, -1, 0, 0};

        // 우선순위큐로 문을 가장 적게 연 사람을 우선적으로 탐색시켜야 함
        PriorityQueue<Loc> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[h+2][w+2];
        int[][] doorHistory = new int[h+2][w+2]; // 열어온 문의 개수 저장

        q.add(prisoner);
        visited[prisoner.i][prisoner.j] = true;

        while(!q.isEmpty()) {
            Loc temp = q.poll();
            doorHistory[temp.i][temp.j] = temp.door;

            for(int d = 0; d < 4; d++) {
                int ni = temp.i + di[d];
                int nj = temp.j + dj[d];

                if(ni >= 0 && ni < h+2 && nj >=0 && nj < w+2 && !visited[ni][nj] && map[ni][nj] != '*') {
                    visited[ni][nj] = true;
                    //문이면 열어서 이동이니까 +1 해줌
                    q.add(new Loc(ni, nj, map[ni][nj] == '#' ? temp.door + 1 : temp.door));
                }
            }
        }

        return doorHistory;

    }

    public static int getMinSum(int[][] prisonerOne, int[][] prisonerTwo, int[][] sanggeun, char[][] map) {

        int minSum = Integer.MAX_VALUE;

        for(int i = 0; i < prisonerOne.length; i++) {
            for(int j = 0; j < prisonerOne[i].length; j++) {
                // 벽이라면 고려 X
                if(map[i][j] == '*')
                    continue;

                // 문이라면 죄수 2명과 상근이가 연 문의 수 합을 계산하여 -2 해줌
                // 합산하는 위치에 문이 있다면 3명 중에 1명만 열면 되기 때문
                int sum = prisonerOne[i][j] + prisonerTwo[i][j] + sanggeun[i][j];
                if(map[i][j] == '#')
                    sum -= 2;
                if(minSum > sum)
                    minSum = sum;
            }
        }

        return minSum;

    }

}
