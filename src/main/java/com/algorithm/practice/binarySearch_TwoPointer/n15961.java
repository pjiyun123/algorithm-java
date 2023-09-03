package com.algorithm.practice.binarySearch_TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 회전초밥
// 슬라이딩 윈도우
public class n15961 {

    static int N; // 회전초밥 벨트에 놓인 접시의 수
    static int d; // 초밥의 가짓수
    static int k; // 연속해서 먹는 접시의 수
    static int c; // 쿠폰 번호

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] sushiBelt = new int[N];
        int[] sushiCnt = new int[d+1];
        for(int i = 0; i < N; i++) {
            sushiBelt[i] = Integer.parseInt(br.readLine());
            sushiCnt[sushiBelt[i]]++;
        }

        int sushi = 0; // 회전 벨트 위의 초밥 종류 개수
        for(int i = 0; i < d+1; i++) {
            if(sushiCnt[i] >= 1)
                sushi++;
        }

        int ans = 0;
        Map<Integer, Integer> check = new HashMap<>();
        for(int i = 0; i < k; i++) {
            if(check.containsKey(sushiBelt[i]))
                check.put(sushiBelt[i], check.get(sushiBelt[i])+1);
            else
                check.put(sushiBelt[i], 1);
        }
        int end = k;
        for(int start = 0; start < N; start++) {
            int cnt = check.size();
            if(!check.containsKey(c))
                cnt++;

            if(ans < cnt)
                ans = cnt;

            if(ans >= k+1)
                break;
            if(k > sushi && ans >= sushi+1)
                break;

            // 첫번째 스시 빼고
            check.put(sushiBelt[start], check.get(sushiBelt[start])-1);
            if(check.get(sushiBelt[start]) == 0)
                check.remove(sushiBelt[start]);
            // 윈도우 범위 바로 다음 스시 넣기
            if(start >= N - k) {
                if(check.containsKey(sushiBelt[k-(N-start)]))
                    check.put(sushiBelt[k-(N-start)], check.get(sushiBelt[k-(N-start)])+1);
                else
                    check.put(sushiBelt[k-(N-start)], 1);
            }
            else {
                if(check.containsKey(sushiBelt[end]))
                    check.put(sushiBelt[end], check.get(sushiBelt[end])+1);
                else
                    check.put(sushiBelt[end], 1);
                end++;
            }
        }

        System.out.println(ans);

    }

}
