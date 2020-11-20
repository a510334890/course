package com.course.system;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if (next.equals("退出")) {
                break;
            } else {
                try {
                    int anInt = test01.getInt(next);
                    System.out.println(anInt);
                } catch (Exception e) {
                    System.out.println("转换失败");
                    break;
                }
            }
        }
    }
}
