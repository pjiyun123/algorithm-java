package com.algorithm.practice.dataStructures;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class n12789 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 학생 수

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < N; i++)
            q.add(sc.nextInt());

        int ticket = 1;
        int check = 0;
        Stack<Integer> s = new Stack<>();
        while(true) {
            if(ticket == N+1)
                break;

            check++;
            if(check > 2 * N)
                break;

            while(!s.isEmpty()) {
                int mm = s.pop();
                if(ticket == mm)
                    ticket++;
                else {
                    s.push(mm);
                    break;
                }
            }

            if(!q.isEmpty()) {
                int now = q.poll();
                if(ticket == now)
                    ticket++;
                else
                    s.push(now);
            }
        }

        if(ticket == N+1)
            System.out.println("Nice");
        else
            System.out.println("Sad");

    }

}
