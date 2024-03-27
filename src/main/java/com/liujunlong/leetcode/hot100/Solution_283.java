package com.liujunlong.leetcode.hot100;

/**
 * 283. 移动零
 * 思路：两个指针 i, j ，把非0元素往前移动
 * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes2(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //两个指针，i和j，i用于遍历元素，j指向非0元素
        int j  = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }

    }
    public static void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        //两个指针，i和j，i用于遍历元素，j指向非0元素
        int j  = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }

    }

    public static void moveZeroes(int[] nums) {
        int zeroSum = 0;
        int n = 0;

        for (int i = 0; i < nums.length - zeroSum; i++) {
            if (nums[i] == 0) {
                n = 1;
                //统计有几个连续的0
                for (int k = i + 1; k < nums.length; k++) {
                    if (nums[k] == 0) {
                        n++;
                    } else {
                        break;
                    }
                }
                zeroSum++;
                //数组元素前移
                for (int j = i; j < nums.length - n; j++) {
                    nums[j] = nums[j + n];
                }
                //将n个0移到最后
                for (int k = 1; k <= n; k++) {
                    nums[nums.length - k] = 0;
                }
                n = 0;
            }
        }
    }
}
