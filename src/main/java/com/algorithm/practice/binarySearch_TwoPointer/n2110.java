package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class n2110 {

    static int N; // 집 개수
    static int C; // 공유기 개수
    static int[] house; // 집 좌표

   public static void main(String[] args) throws IOException {

       Scanner sc = new Scanner(System.in);
       N = sc.nextInt();
       C = sc.nextInt();

       house = new int[N];
       for(int i = 0; i < N; i++)
           house[i] = sc.nextInt();

       Arrays.sort(house);

       int lo = 1; // 최소 거리가 가질 수 있는 최솟값
       int hi = house[N-1] - house[0] + 1; // 최소 거리가 가질 수 있는 최댓값
       while(lo < hi) {
           int mid = (hi + lo) / 2;
           // 거리를 좁혀야 할 경우, hi를 줄인다
           if(canInstall(mid) < C) {
               hi = mid;
           }
           // 거리를 벌리면서 최소거리가 가질 수 있는 최대 거리를 찾는다
           else {
               lo = mid + 1;
           }
       }

       System.out.println(lo-1);

    }

    public static int canInstall(int dist) {

       // 첫번째 집은 무조건 설치한다고 가정
       int count = 1;
       int lastLocate = house[0];

       for(int i = 1; i < house.length; i++) {
           int locate = house[i];
           if(locate - lastLocate >= dist) {
               count++;
               lastLocate = locate;
           }
       }

       return count;

    }

}
