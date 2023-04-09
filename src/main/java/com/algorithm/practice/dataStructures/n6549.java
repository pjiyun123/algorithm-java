package com.algorithm.practice.dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n6549 {

    static int[] histogram;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        int n;
        while(true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());

            if(n == 0)
                break;

            histogram = new int[n];

            for(int i = 0; i < n; i++) {
                histogram[i] = Integer.parseInt(st.nextToken());
            }
            sb.append(getArea(0, n-1)).append('\n');
            histogram = null;
        }

        System.out.println(sb);

    }

    public static long getArea(int lo, int hi) {

        // 막대 폭이 1일 경우
        if(lo == hi)
            return histogram[lo];

        // 가운데 위치 구하기
        int mid = (lo + hi) / 2;

        // 가운데 위치를 기준으로 분할하여 왼쪽 구간 넓이와 오른쪽 구간 넓이 구하기
        long leftArea = getArea(lo, mid);
        long rightArea = getArea(mid+1, hi);

        // 왼쪽과 오른쪽 중 큰 넓이를 변수에 저장하기
        long max = Math.max(leftArea, rightArea);

        // 변수에 저장된 넓이와 두 구간을 합친 구간의 가장 큰 넓이를 구해 두 개 중 가장 큰 넓이를 반환하기
        max = Math.max(max, getMidArea(lo, hi, mid));

        return max;

    }

    public static long getMidArea(int lo, int hi, int mid) {

        int toLeft = mid; // 중간지점으로부터 왼쪽으로 갈 변수
        int toRight = mid; // 중간지점으로부터 오른쪽으로 갈 변수

        long height = histogram[mid]; // 높이

        // 초기 넓이
        long maxArea = height;

        // 양끝 범위를 벗어나기 전까지 반복
        while(lo < toLeft && toRight < hi) {

            // 양쪽 다음칸의 높이 중 높은 값 선택
            if(histogram[toLeft-1] < histogram[toRight+1]) {
                toRight++;
                height = Math.min(height, histogram[toRight]);
            }
            else {
                toLeft--;
                height = Math.min(height, histogram[toLeft]);
            }

            maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
        }

        // 오른쪽 구간을 끝까지 탐색 못했다면 마저 탐색
        while(toRight < hi) {
            toRight++;
            height = Math.min(height, histogram[toRight]);
            maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
        }

        // 왼쪽 구간을 끝까지 탐색 못했따면 마저 탐색
        while(lo < toLeft) {
            toLeft--;
            height = Math.min(height, histogram[toLeft]);
            maxArea = Math.max(maxArea, height*(toRight-toLeft+1));
        }

        return maxArea;

    }

}
