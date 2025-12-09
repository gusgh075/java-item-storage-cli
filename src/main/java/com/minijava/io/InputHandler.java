package com.minijava.io;

import java.util.Scanner;

public class InputHandler {

    private final Scanner sc = new Scanner(System.in);

    // 숫자 입력
    public int inputInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("숫자만 입력하세요.");
                sc.nextLine();
            }
        }
    }

    // 문자열 입력
    public String inputString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    // Enter 대기
    public void waitForEnter() {
        System.out.println("계속하려면 Enter");
        sc.nextLine();
    }

}
