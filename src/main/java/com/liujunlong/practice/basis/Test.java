package com.liujunlong.practice.basis;

import java.io.IOException;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2023/4/16 22:08
 */
public class Test {

    public Test() {
        super();
    }
    static int arr[] = new int[5];
    public static void main(String[] args) throws IOException {
        testMath();
//        testNumer();
//        test1();
//        System.out.println(arr[0]);
    }

    static  void testNumer(){
        int  x = 1;
        double y = 2;
        System.out.println(x/y);
    }
    static void testMath() {
          //小于x的最小long
        System.out.println(Math.floor(4.2));
        System.out.println(Math.floor(-4.2));
        System.out.println(Math.floor(4.6));
//
          //大于x的最小double
//        System.out.println(Math.ceil(5.5));
//        System.out.println(Math.ceil(5.4));
//        System.out.println(Math.ceil(-5.6));
//
//        //Math.floor(x+0.5d)
        System.out.println(Math.round(4.4));
        System.out.println(Math.round(4.5));
        System.out.println(Math.round(4.6));
        System.out.println(Math.round(-4.6));
//        System.out.println(Math.round(-4.5));
//        System.out.println(Math.round(-4.4));
    }
}
