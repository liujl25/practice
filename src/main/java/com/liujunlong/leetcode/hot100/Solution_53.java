package com.liujunlong.leetcode.hot100;

/**
 * 53. 最大子数组和
 * https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_53 {

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray1(nums));
    }

    /**
     * 负数不作为连续子数组的元素，负数累加只会更小
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int index = 0, res = Integer.MIN_VALUE;
        int sum = 0;
        while (index < nums.length) {
            if (sum < 0) {
                sum = nums[index];//和为负数就重置为下一个元素，重新计算
            } else {
                sum = sum + nums[index];//累加
            }
            res = Math.max(res, sum);
            index++;
        }
        return res;
    }

    /**
     *
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }


}

