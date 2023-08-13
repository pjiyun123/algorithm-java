package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.*;

public class n1713 {

    public static class rec implements Comparable<rec>{
        int index;
        int num;
        public rec(int index, int num) {
            this.index = index;
            this.num = num;
        }

        @Override
        public int compareTo(rec o) {
            if(this.num > o.num)
                return 1;
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 사진틀 수
        int M = sc.nextInt(); // 추천 수

        ArrayList<Integer> pic = new ArrayList<>();
        int[] student = new int[101]; // 학생의 추천 수
        int[] order = new int[101]; // 추천 순서
        for(int i = 0; i < M; i++) {
            int rec = sc.nextInt();
            // 이미 사진틀에 있는 학생일 경우
            if(pic.contains(rec)) {
                student[rec] += 1;
            }

            // 새로 추천 받은 학생일 경우
            else {
                // 사진틀 자리가 있을 때
                if(pic.size() < N) {
                    pic.add(rec);
                    student[rec] = 1;
                    order[rec] = i;
                }
                // 사진틀 자리가 없을 때
                else {
                    // 추천 수 비교
                    ArrayList<rec> comRec= new ArrayList<>();
                    for(int j = 0; j < pic.size(); j++) {
                        comRec.add(new rec(pic.get(j), student[pic.get(j)]));
                    }

                    // 가장 작은 추천 수가 두 개 이상인 경우
                    comRec.sort(Comparator.naturalOrder());
                    int k = 0;
                    int minRec = comRec.get(0).num;
                    for(int j = 1; j < pic.size(); j++) {
                        if(minRec >= comRec.get(j).num) {
                            minRec = comRec.get(j).num;
                            k = j;
                        }
                    }

                    int minOrd = 10000;
                    int target = -1;
                    for(int j = 0; j <= k; j++) {
                        if(minOrd > order[comRec.get(j).index]) {
                            minOrd = order[comRec.get(j).index];
                            target = comRec.get(j).index;
                        }
                    }

                    student[target] = 0;
                    pic.remove(pic.indexOf(target));
                    pic.add(rec);
                    student[rec] = 1;
                    order[rec] = i;
                }
            }
        }

        for(int i = 1; i <= 100; i++) {
            if(student[i] != 0) {
                System.out.print(i + " ");
            }
        }

    }

}
