package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n13458 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int room = Integer.parseInt(br.readLine()); // 시험장 개수

        int tester[] = new int[room]; // 시험장 당 응시자 수
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < room; i++)
            tester[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int mainMan = Integer.parseInt(st.nextToken()); // 총감독관이 시험장 당 관리 가능한 인원
        double subMan = Double.parseDouble(st.nextToken()); // 부감독관이 시험장 당 관리 가능한 인원

        long result = 0;
        for(int i = 0; i < room; i++) {
            // 시험장에 아무도 없을 경우를 제외
            if(tester[i] == 0) continue;

            // 시험장에서 총감독관이 관리 가능한 인원 빼기
            tester[i] -= mainMan;
            result += 1; // 총감독관

            // 시험장에 필요한 부감독관 수 구하기
            if(tester[i] > 0)
                result += Math.ceil(tester[i] / subMan);

        }

        System.out.println(result);

    }

}
