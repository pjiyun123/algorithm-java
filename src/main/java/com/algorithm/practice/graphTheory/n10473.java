package com.algorithm.practice.graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class n10473 {

    static class Coordinate {
        float x;
        float y;
        public Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int destination;
        float time;

        public Edge(int destination, float time) {
            this.destination = destination;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            return Float.compare(this.time, o.time);
        }
    }

    static Coordinate[] coordinateList;
    static ArrayList<Edge> adjList[];
    static boolean[] check;
    static float times[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 출발지 좌표
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        float x = Float.parseFloat(st.nextToken());
        float y = Float.parseFloat(st.nextToken());
        Coordinate start = new Coordinate(x, y);

        // 도착지 좌표
        st = new StringTokenizer(br.readLine(), " ");
        x = Float.parseFloat(st.nextToken());
        y = Float.parseFloat(st.nextToken());
        Coordinate end = new Coordinate(x, y);

        int N = Integer.parseInt(br.readLine()); // 대포 개수
        coordinateList = new Coordinate[N+2];
        check = new boolean[N+2];
        times = new float[N+2];
        adjList = new ArrayList[N+2];
        for(int i = 0; i < N+2; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 정점 좌표들(출발지, 대포들, 도착지)
        coordinateList[0] = start;
        coordinateList[N+1] = end;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            x = Float.parseFloat(st.nextToken());
            y = Float.parseFloat(st.nextToken());
            coordinateList[i] = new Coordinate(x, y);
        }

        // 그래프 만들기
        // 출발지는 대포가 아니기 때문에 무조건 달려가야 함
        for(int i = 1; i < N+2; i++) {
            float dist = (float) Math.sqrt(Math.pow(coordinateList[0].x - coordinateList[i].x, 2)
                    + Math.pow(coordinateList[0].y - coordinateList[i].y, 2)); // 출발지에서 정점까지의 거리 계산
            adjList[0].add(new Edge(i, (float) (dist/5.0)));
        }

        // 대포에서는 달려가거나, 대포로 발사되거나 빠른 걸로 택1
        for(int i = 1; i < N+2; i++) {
            for(int j = 0; j < N+2; j++) {
                float dist = (float) Math.sqrt(Math.pow(coordinateList[i].x - coordinateList[j].x, 2)
                        + Math.pow(coordinateList[i].y - coordinateList[j].y, 2));
                adjList[i].add(new Edge(j, (float) Math.min(dist / 5.0, Math.abs(dist - 50) / 5.0 + 2)));
            }
        }

        dijkstra(0);
        System.out.println(times[N+1]);

    }

    public static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(times, Integer.MAX_VALUE);
        pq.add(new Edge(start, 0));
        times[start] = 0;

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int dest = edge.destination;

            if(check[dest]) {
                continue;
            }
            else {
                check[dest] = true;
            }

            for(Edge next : adjList[dest]) {
                if(times[next.destination] >= times[dest] + next.time) {
                    times[next.destination] = times[dest] + next.time;
                    pq.add(new Edge(next.destination, times[next.destination]));
                }
            }
        }
    }

}
