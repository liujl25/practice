package com.liujunlong.practice.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liujunlong
 * @since 2020/10/8 10:41
 */
public class IteratorTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap();
        map.put(1, "Java");
        map.put(2, "C++");
        map.put(3, "PHP");
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + entry.getValue());
        }

//        System.out.println(Integer.MAX_VALUE);
        System.out.println(1<<30);

        //2147483647
    }
}
