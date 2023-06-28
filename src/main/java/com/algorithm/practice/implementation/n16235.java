package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class n16235 {

    static int N; // 전체 땅의 행과 열
    static int M; // 초기에 주어진 나무의 수
    static int K; // 년 수

    static int[][] lands; // 전체 땅
    static int[][] foods; // 땅마다 추가되는 양분
    static ArrayList<Tree> trees = new ArrayList<>(); // 나무들
    static Deque<Integer> deadTrees = new LinkedList<>(); // 죽은 나무들

    static ArrayList[][] deadTree; // 죽은 나무

    public static class Tree {

        int row; // 나무가 있는 행
        int col; // 나무가 있는 열
        int age; // 나무의 나이
        boolean dead; // 나무 생존 여부

        public Tree(String[] tree) {
            this.row = Integer.parseInt(tree[0]) - 1;
            this.col = Integer.parseInt(tree[1]) - 1;
            this.age = Integer.parseInt(tree[2]);
        }

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }

    }

    // k년이 지난 후 살아남은 나무의 수 구하기
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        lands = new int[N][N];
        foods = new int[N][N];

        // 양분 입력받기
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                foods[i][j] = Integer.parseInt(st.nextToken());
                lands[i][j] = 5;
            }
        }

        // 나무 위치에 따른 나이 초기화
        for(int m = 0; m < M; m++) {
            String[] tree = br.readLine().split(" ");
            trees.add(new Tree(tree));
        }

        // 처음에 주어진 나무들을 어린나이 순으로 정렬
        Collections.sort(trees, (t1, t2) -> t1.age - t2.age);


        // k년 만큼 사계절 반복
        for(int k = 0; k < K; k++) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());

    }

    public static void spring() {

        // 봄에는 자신의 나이만큼 양분 섭취
        for(int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if(lands[tree.row][tree.col] < tree.age) {
                deadTrees.add(i);
                continue;
            }
            lands[tree.row][tree.col] -= tree.age;
            tree.age++;
        }

    }

    public static void summer() {

        // 여름에는 죽은 나무를 양분으로
        while(!deadTrees.isEmpty()) {
            int deadTreeIndex = deadTrees.pollLast();
            Tree deadTree = trees.get(deadTreeIndex);
            int food = deadTree.age / 2;
            lands[deadTree.row][deadTree.col] += food;
            deadTree.dead = true;
        }

    }

    public static void fall() {

        // 가을에는 8방향으로 나무 번식
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}; // 번식 r방향
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1}; // 번식 c방향
        ArrayList<Tree> newTrees = new ArrayList<>(); // 새로 생성될 나무를 저장할 리스트

        for(int p = 0; p < trees.size(); p++) {
            Tree tree = trees.get(p);
            //죽은 나무는 건너뛰기
            if(tree.dead) {
                continue;
            }

            // 살아있는 나무의 나이가 5의 배수라면
            if (tree.age % 5 == 0) {
                for(int d = 0; d < 8; d++) {
                    int nr = tree.row + dr[d];
                    int nc = tree.col + dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N)
                        continue;
                    // 나이가 1인 나무 생성
                    newTrees.add(new Tree(nr, nc, 1));
                }
            }
        }

        // 새로 생성된 나무들이 저장된 리스트에 기존의 살아있는 나무 추가
        for(Tree tree : trees) {
            if(!tree.dead) {
                newTrees.add(tree);
            }
        }

        // 기존 나무 + 새로 생성된 나무
        trees = newTrees;

    }

    public static void winter() {

        // 겨울에는 양분 추가
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                lands[i][j] += foods[i][j];
            }
        }

    }

}
