package com.algorithm.practice.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 숫자 야구
public class n2503 {

    static ArrayList<Integer> arr = new ArrayList<>(); // 가능한 수 리스트

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 질문 개수

        // 가능한 수 세팅
        boolean[] visited = new boolean[10];
        int[] nums = new int[3];
        setArr(visited, nums, 0);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int askNum = Integer.parseInt(st.nextToken()); // 민혁이 물은 숫자
            int strike = Integer.parseInt(st.nextToken()); // 스트라이크
            int ball = Integer.parseInt(st.nextToken()); // 볼

            ArrayList<Integer> newArr = new ArrayList<>();
            for(int j = 0; j < arr.size(); j++) {
                int curNum = arr.get(j);
                if(checkStrike(askNum, curNum, strike) && checkBall(askNum, curNum, ball))
                    newArr.add(curNum);
            }
            arr = newArr;
        }

        System.out.println(arr.size());

    }

    public static void setArr(boolean[] visited, int[] nums, int idx) {

        if(idx == 3) {
            int n = nums[0] * 100 + nums[1] * 10 + nums[2];
            arr.add(n);
            return;
        }

        for(int i = 1; i <= 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                nums[idx] = i;
                setArr(visited, nums, idx+1);
                visited[i] = false;
            }
        }

    }

    public static boolean checkStrike(int askNum, int curNum, int strike) {

        int cnt = 0;
        // 첫번째 수 스트라이크 판정
        if (askNum / 100 == curNum / 100)
            cnt++;
        if (cnt > strike)
            return false;

        // 두번째 수 스트라이크 판정
        if ((askNum % 100) / 10 == (curNum % 100) / 10)
            cnt++;
        if (cnt > strike)
            return false;

        // 세번째 수 스트라이크 판정
        if (askNum % 10 == curNum % 10)
            cnt++;
        if (cnt > strike)
            return false;

        return cnt == strike;
    }



    public static boolean checkBall(int askNum, int curNum, int ball) {

        int[] askNumCheck = new int[10];
        askNumCheck[askNum/100] = 1;
        askNumCheck[(askNum%100)/10] = 2;
        askNumCheck[askNum%10] = 3;

        int[] curNumCheck = new int[10];
        curNumCheck[curNum/100] = 1;
        curNumCheck[(curNum%100)/10] = 2;
        curNumCheck[curNum%10] = 3;

        int cnt = 0;
        for(int j = 1; j <= 9; j++) {
            if(askNumCheck[j] != 0 && curNumCheck[j] != 0 && askNumCheck[j] != curNumCheck[j])
                cnt++;
        }

        return cnt == ball;

    }

}
