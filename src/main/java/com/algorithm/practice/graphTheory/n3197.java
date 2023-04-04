package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n3197 {

    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static char[][] map;
    static Point[] swan = new Point[2];
    static boolean[][] visited;
    static int R;
    static int C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static Queue<Point> q = new LinkedList<>();
    static Queue<Point> waterQ = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        // 데이터 입력
        int swanIdx = 0;
        for(int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                map[i][j] = line[j];
                // 백조일 경우
                if(map[i][j] == 'L')
                    swan[swanIdx++] = new Point(i, j);
                // 물일 경우
                if(map[i][j] != 'X')
                    waterQ.add(new Point(i, j));
            }
        }

        // 출발점이 되는 백조1 (큐1에 넣고 방문체크하기)
        q.add(swan[0]);
        visited[swan[0].r][swan[0].c] = true;

        int countDay = 0; // 출력할 일수
        boolean meet = false; // while문 탈출 조건

        // q로 bfs 수행
        while(true) {
            Queue<Point> nextQ = new LinkedList<>();
            while(!q.isEmpty()) {
                Point now = q.poll();

                // 백조2를 만날 경우 종료
                if(now.r == swan[1].r && now.c == swan[1].c) {
                    meet = true;
                    break;
                }

                // 백조2를 아직 안 만난 경우
                for(int i = 0; i < 4; i++) {
                    int nr = now.r + dr[i];
                    int nc = now.c + dc[i];

                    if(nr >= R || nr < 0 || nc >= C || nc < 0 || visited[nr][nc])
                        continue;

                    // 방문 체크 해주기
                    visited[nr][nc] = true;
                    // 물에 인접한 얼음인 경우 다음날 백조가 탐색할 지역이므로 큐2(nextQ)에 넣어주기
                    if(map[nr][nc] == 'X') {
                        nextQ.add(new Point(nr, nc));
                        continue;
                    }
                    // 물일 경우 현재(오늘) 백조가 탐색할 지역이므로 큐1(q)에 넣어주기
                    q.add(new Point(nr, nc));
                }

            }

            // 두 백조가 만났을 경우 종료
            if(meet)
                break;
            // 아니라면 큐2(nextQ)를 큐1(q)로 바꾸기
            q = nextQ;

            // 얼음 녹이기
            int size = waterQ.size();
            for(int i = 0; i < size; i++) {
                Point now = waterQ.poll();

                for(int j = 0; j < 4; j++) {
                    int nr = now.r + dr[j];
                    int nc = now.c + dc[j];

                    if(nr >= R || nr < 0 || nc >= C || nc < 0)
                        continue;

                    // 물에 인접한 얼음을 발견할 경우 녹이고 다시 큐(waterQ)에 넣기
                    if(map[nr][nc] == 'X') {
                        map[nr][nc] = '.';
                        waterQ.add(new Point(nr, nc));
                    }
                }
            }
            // 다음날로 넘기기
            countDay++;
        }

        System.out.println(countDay);

    }

}
