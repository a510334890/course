package com.course.generator.ftl;

import java.util.ArrayList;
import java.util.List;

public class test01 {
    public static void main(String[] args) {
        int [] a = {33,22,11,55,66,77};
        int [] b ;
        int temp;
        List<Integer> c = new ArrayList<>();
        for (int i = 0; i < a.length-1; i++) {
            if(a[i]>a[i+1]){
                temp = a[i];
                a[i+1]=temp;
                a[i] = a[i+1];
                c.add(a[i]);
            }

        }
        System.out.println(c);
        System.out.println(c.toArray());
    }
}
