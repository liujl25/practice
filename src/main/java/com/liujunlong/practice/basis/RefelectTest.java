package com.liujunlong.practice.basis;

import java.lang.reflect.Field;

/**
 * 反射修改final值
 */
class RefelectTest {

    private static final String str = "aaa";

    public static void main(String[] args) throws Exception {

        Class c = str.getClass();
        Field field = c.getDeclaredField("value");
        field.setAccessible(true);
        char[] b = new char[]{'b', 'b', 'b'};
        field.set(str, b);
        System.out.println(RefelectTest.str);

    }
}