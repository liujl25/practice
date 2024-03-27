package com.liujunlong.practice.jdk8.newfeatures.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *
 * @author liujunlong
 * @date 2022/1/21 00:30
 */
public class Test {

    public static void main(String[] args) {
        String b ="𝄞";
        System.out.println(b.length());
        System.out.println(b.codePointCount(0,b.length()));
    }

    private static void testLimit() {
        Stream<String> a = Stream.of("a", "b", "c", "d");
//        System.out.println(a.limit(4));
        Stream<String> b = a.limit(3);
         b.peek(System.out::println).collect(Collectors.toList());
    }

    private static void testFilter() {
        List<Student> s1 = new ArrayList<>();
        Student student1 = new Student("aa", "1");
        Student student2 = new Student("bb", "2");
        Student student3 = new Student("cc", "3");
        s1.add(student1);
        s1.add(student2);
        s1.add(student3);
        List<Student> s2 = new ArrayList<>();
        Student student4 = new Student("aa", "");
        Student student5 = new Student("bb", "");
        Student student6 = new Student("cc", "");
        s2.add(student4);
        s2.add(student5);
        s2.add(student6);
        List<String> collect = s1.stream()
                .flatMap(x -> s2.stream()
                        .filter(y -> y.name.equals(x.name))
                        .map(y -> y.age = x.age))
                .collect(Collectors.toList());
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(collect);
        Objects.isNull(s1);
        return;
    }

    static class Student {
        String name;
        String age;
        int sex;

        public Student(String name, String age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return this.name + ": " + this.age;
        }

    }


}
