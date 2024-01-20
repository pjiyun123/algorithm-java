package com.algorithm.practice.implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

// Interpreter
public class n4385 {

    static int[] instructions = new int[100001];
    static int[] registers = new int[10];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        String str = "";
        int lines = 0;
        while(!(str = br.readLine()).isEmpty()) {
            instructions[lines++] = Integer.valueOf(str);
        }

        //System.out.println("hello");

        int idx = 0;
        int ans = 0;
        boolean finish = false;
        while(!finish) {
            int opt = instructions[idx] / 100; // 명령어 분류
            int arg1 = (instructions[idx] / 10) % 10;
            int arg2 = instructions[idx] % 10;

            idx++;
            ans++;

            switch(opt) {
                case 1:
                    finish = true;
                    break;

                case 2:
                    registers[arg1] = arg2;
                    break;

                case 3:
                    registers[arg1] = (registers[arg1] + arg2) % 1000;
                    break;

                case 4:
                    registers[arg1] = (registers[arg1] * arg2) % 1000;
                    break;

                case 5:
                    registers[arg1] = registers[arg2];
                    break;

                case 6:
                    registers[arg1] = (registers[arg1] + registers[arg2]) % 1000;
                    break;

                case 7:
                    registers[arg1] = (registers[arg1] * registers[arg2]) % 1000;
                    break;

                case 8:
                    registers[arg1] = instructions[registers[arg2]];
                    break;

                case 9:
                    instructions[registers[arg2]] = registers[arg1];
                    break;

                case 0:
                    if(registers[arg2] != 0) {
                        idx = registers[arg1];
                    }
                    break;
            }
        }

        System.out.println(ans);

    }
}
