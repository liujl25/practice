package com.liujunlong.leetcode.hot100;

import java.util.Arrays;

class Solution_1921 {
    public static int eliminateMaximum(int[] dist, int[] speed) {
        int sum = 1;
        //java, 排序
        int n = dist.length;
        //记录每只怪兽到达需要的时间
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = dist[i] % speed[i] == 0 ? dist[i] / speed[i] - 1: dist[i] / speed[i];//求余不为0，说明有小数，无需减一
        }
        //排序
        Arrays.sort(arr);
        //每分钟我们都可以杀一只怪兽，如果怪兽在我们击杀前到达，GG
        for (int i = 1; i < n; i++) {
            if (arr[i] < sum)
                return i;
            sum++;
        }
        return n;
    }

    public static void main(String[] args) {
        int[] l1 = new int[]{3, 5, 7, 4, 5};
        int[] l2 = new int[]{2, 3, 6, 3, 2};

        System.out.println(eliminateMaximum(l1, l2));
        ;
    }
}