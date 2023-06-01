package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n16234 {

    static int N;
    static int L;
    static int R;

    static int[][] world;

    static ArrayList<Node> list;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        world = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++)
                world[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(migrate());

    }

    public static int migrate() {

        int days = 0;
        while(true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        int sum = bfs(i, j);
                        if(list.size() > 1) {
                            change(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove)
                return days;
            days++;
        }

    }

    public static int bfs(int x, int y) {

        Queue<Node> q = new LinkedList<>();
        list = new ArrayList<>();

        q.offer(new Node(x, y));
        list.add(new Node(x, y));
        visited[x][y] = true;

        int sum = world[x][y];
        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int gap = Math.abs(world[current.x][current.y] - world[nx][ny]);
                    if(gap >= L && gap <= R) {
                        q.offer(new Node(nx, ny));
                        list.add(new Node(nx, ny));
                        sum += world[nx][ny];
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return sum;

    }

    public static void change(int sum) {

        int avg = sum / list.size();
        for(Node n : list)
            world[n.x][n.y] = avg;
    }

}
