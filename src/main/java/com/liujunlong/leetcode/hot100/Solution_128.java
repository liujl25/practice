package com.liujunlong.leetcode.hot100;

import java.util.*;

/**
 * 128. 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_128 {
    public static void main(String[] args) {
//        int[] nums = {100, 4, 200, 1, 3, 2};
//        int[] nums = {1,2,0,1};
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxQueue = 0;
        int tempMaxQueue = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                tempMaxQueue++;
            } else if (nums[i] == nums[i + 1]){
                continue;
            } else{
                maxQueue = Math.max(tempMaxQueue, maxQueue);
                tempMaxQueue = 1;
            }
        }
        maxQueue = Math.max(tempMaxQueue, maxQueue);
        return maxQueue;
    }
}
