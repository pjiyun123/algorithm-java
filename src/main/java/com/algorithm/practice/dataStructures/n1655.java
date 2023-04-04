package com.algorithm.practice.dataStructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class n1655 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        // 최대힙과 최소힙 만들기
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            // 최대힙과 최소힙의 크기가 같은 결우 최대힙에 값 넣기
            if(maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);

                // 최대힙의 peek < 최소힙의 peek 조건 유지하기
                if(!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(minHeap.poll());
                }
            }

            // 최대힙과 최소힙의 크기가 같지 않은 경우 크기가 작은 힙에 값 넣기 / 크기가 같지 않다면 무조건 최소힙의 크기가 작을 것이다.
            else {
                minHeap.add(num);

                // 최대힙의 peek < 최소힙의 peek 조건 유지하기
                if(maxHeap.peek() > minHeap.peek()) {
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(minHeap.poll());
                }
            }

            // 최대합의 피크값 담기
            sb.append(maxHeap.peek()).append("\n");
        }

        // 최대합의 피크값 모음 출력하기
        System.out.println(sb);

    }

}
