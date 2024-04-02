package com.liujunlong.practice;

public class Test {

    @org.junit.Test
    public void test() {
        int n = 2;
        int sum = 0;
        for (int i = 0; i <= n-1; i++)   // (1)
            for (int j = n; j > i; j--) // (2)
                sum++;               // (3)
        System.out.println(sum);
    }
}
