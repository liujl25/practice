package com.liujunlong.leetcode.hot100;

import java.util.Arrays;

/**
 * 239. 滑动窗口最大值
 * https://leetcode.cn/problems/sliding-window-maximum/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_239 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {7,2,4};
        int k = 2;
        Arrays.stream(maxSlidingWindow(nums, k)).forEach(System.out::println);
    }



    /**
     * 暴力破解
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        int maxNum = nums[0];
        int index = 0;
        //求前k个最大值,第一个滑动窗口
        for (int i = 0; i < k; i++) {
            if (nums[i] >= maxNum) {
                maxNum = nums[i];
                index = i;
            }
        }
        result[0] = maxNum;
        for (int i = k; i < nums.length; i++) {
            if (index == i - k) {
                maxNum = nums[i - k + 1];
                for (int j = i - k + 1; j <= i; j++) {
                    if (nums[j] >= maxNum) {
                        maxNum = nums[j];
                        index = j;
                    }
                }
            } else if (nums[i] >= maxNum) {
                maxNum = nums[i];
            }
            result[i - k + 1] = maxNum;
        }
        return result;
    }

}
