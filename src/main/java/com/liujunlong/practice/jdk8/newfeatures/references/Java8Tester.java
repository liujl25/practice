package com.liujunlong.practice.jdk8.newfeatures.references;

import java.util.List;
import java.util.ArrayList;

/**
 * https://www.runoob.com/java/java8-method-references.html
 */
public class Java8Tester {
   public static void main(String args[]){
      List<String> names = new ArrayList();
        
      names.add("Google");
      names.add("Runoob");
      names.add("Taobao");
      names.add("Baidu");
      names.add("Sina");
        
      names.forEach(System.out::println);
   }
}