package com.algorithm.practice.implementation;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class n1715 {

    static int N; // 카드 묶음 수
    static PriorityQueue<Long> card; // 카드

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        card = new PriorityQueue<>();
        for(int i = 0; i < N; i++)
            card.add(sc.nextLong());

        long result = 0;
        if(N != 1) {
            while(true) {
                if(card.size() < 2) {
                    break;
                }
                long tmp = card.poll() + card.poll();
                result += tmp;
                card.add(tmp);
            }
        }

        System.out.println(result);

    }

}
