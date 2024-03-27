package com.liujunlong.practice.basis;

import java.util.ArrayList;
import java.util.List;

class Fruit {
    void show() {
    }
}

class Apple extends Fruit {
    void show() {
        System.out.println("apple");
    }
}

class Jonathan extends Apple {
    void show() {
        System.out.println("Jonathan");
    }
}

class Orange extends Fruit {
    void show() {
        System.out.println("Orange");
    }
}

public class CovariantArrays {
    public static void main(String[] args) {
        //上界
        List<? extends Fruit> flistTop = new ArrayList<>();
        flistTop.add(null);
        //add Fruit对象会报错
//    flistTop.add(new Orange());
        Fruit apple = flistTop.get(0);


        //只能用不能改
        List<Fruit> bb = (List<Fruit>) flistTop;
        bb.add(new Apple());


        //下界
        List<? super Fruit> flistBottem = new ArrayList<>();
        flistBottem.add(new Apple());
        flistBottem.add(new Jonathan());
        flistBottem.add(new Fruit());
        //get Apple对象会报错
//    Apple apple = flistBottem.get(0);
        //只能改不能用

        List<Fruit> aa = (List<Fruit>) flistBottem;
        aa.get(0).show();
        aa.get(1).show();
    }


}