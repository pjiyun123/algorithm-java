package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class n19236 {

    static fish[][] map = new fish[4][4];
    static PriorityQueue<fish> pq = new PriorityQueue<>();

    static fish shark;
    static int nr;
    static int nc;
    static int ans = 0;

    public static class fish implements Comparable<fish> {
        int idx;
        int dir;
        int row;
        int col;

        public fish(int idx, int dir, int row, int col) {
            this.idx = idx;
            this.dir = dir;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(fish o) {
            if(this.idx > o.idx)
                return 1;
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                int idx = sc.nextInt();
                int dir = sc.nextInt();
                // 상어 초기 위치 (상어의 인덱스는 0)
                if(i == 0 && j == 0) {
                    shark = new fish(0, dir, 0, 0);
                    map[0][0] = shark;
                    continue;
                }
                fish newFish = new fish(idx, dir, i, j);
                map[i][j] = newFish;
                pq.add(newFish);
            }
        }

        while(true) {
            // 물고기 이동 시키기
            moveFish();
            // 상어 이동시키기
            // 이동할 수 없다면 종료 or 갈 수 있는 방향에 있는 물고기 중 최대 인덱스로 이동
            int res = moveShark();
            if(res != 0) {
                ans += res;
                mapToQueue();
            }
            else
                break;
        }

        System.out.println(ans);

    }

    // 물고기 이동시키기
    public static void moveFish() {

        while(!pq.isEmpty()) {
            // 번호 낮은 순으로 몰고기 꺼내기
            fish fs = pq.poll();

            // 물고기가 가진 방향으로 갈 수 있는지 확인하기
            int cnt = 0;
            while(true) {
                // 방향이 제자리로 돌아왔을 때 멈추고 물고기 위치 바꾸지 않기
                if(cnt == 8) break;

                // 갈 수 있다면 그 방향의 물고기와 위치 바꾸기
                if(checkedFishDir(fs)) {
                    fish changeFish = map[nr][nc]; // 바꿀 위치에 있는 물고기
                    changeFish.row = fs.row;
                    changeFish.col = fs.col;
                    map[fs.row][fs.row] = changeFish;
                    fs.row = nr;
                    fs.col = nc;
                    map[fs.row][fs.col] = fs;
                    break;
                }
                // 갈 수 없다면 반시계 방향으로 회전하기
                else {
                    cnt++;
                    fs.dir = fs.dir + 1;
                    if(fs.dir == 9) fs.dir = 1;
                }
            }

        }

    }

    // 물고기 방향 유효 확인하기
    public static boolean checkedFishDir(fish fs) {

        // 북
        if(fs.dir == 1) {
            nr = fs.row - 1;
            nc = fs.col;
            if(nr < 0 || nr >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 북서
        if(fs.dir == 2) {
            nr = fs.row - 1;
            nc = fs.col - 1;
            if(nr < 0 || nr >= 4) return false;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 서
        if(fs.dir == 3) {
            nr = fs.row;
            nc = fs.col - 1;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 남서
        if(fs.dir == 4) {
            nr = fs.row + 1;
            nc = fs.col - 1;
            if(nr < 0 || nr >= 4) return false;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 남
        if(fs.dir == 5) {
            nr = fs.row + 1;
            nc = fs.col;
            if(nr < 0 || nr >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 남동
        if(fs.dir == 6) {
            nr = fs.row + 1;
            nc = fs.col + 1;
            if(nr < 0 || nr >= 4) return false;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 동
        if(fs.dir == 7) {
            nr = fs.row;
            nc = fs.col + 1;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        // 북동
        if(fs.dir == 8) {
            nr = fs.row - 1;
            nc = fs.col + 1;
            if(nr < 0 || nr >= 4) return false;
            if(nc < 0 || nc >= 4) return false;
            if(nr == shark.row && nc == shark.col) return false;
        }

        return true;

    }

    // 맵에 담긴 물고기를 다시 큐에 넣기
    public static void mapToQueue() {
        pq.clear();
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(map[i][j].idx == 0 || map[i][j].idx == -1) continue; // 상어와 빈칸은 넣지 않기
                pq.add(map[i][j]);
            }
        }
    }

    public static int moveShark() {

        int maxIdx = 0;
        int maxRow = shark.row;
        int maxCol = shark.col;
        int sharkRow = shark.row; // 상어의 원래 위치의 행
        int sharkCol = shark.col; // 상어의 원래 위치의 열

        while(true) {
            // 상어가 움직일 수 있는 방향으로 이동하면서 가장 큰 인덱스 구하기
            if(checkedFishDir(shark)) {
                shark.row = nr;
                shark.col = nc;
                if(map[nr][nc].idx > maxIdx) {
                    maxIdx = map[nr][nc].idx;
                    maxRow = map[nr][nc].row;
                    maxCol = map[nr][nc].col;
                }
            }
            else break;
        }

        // 상어 이동하기
        shark.row = maxRow;
        shark.col = maxCol;
        map[maxRow][maxCol] = shark;
        map[sharkRow][sharkCol] = new fish(-1, -1, shark.row, shark.col); // 상어의 원래 자리를 빈칸으로
        return maxIdx;
    }

}
