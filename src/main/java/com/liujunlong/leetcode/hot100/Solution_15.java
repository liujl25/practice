package com.liujunlong.leetcode.hot100;

import java.util.*;

/**
 * 15. 三数之和
 * https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4, 2};
        //-4,-1,-1,0,1,2,2
        System.out.println(threeSum(nums).toString());
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > 0) {
                return result;
            }
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // 去重
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;// 去重
                    while (left < right && nums[right] == nums[right - 1]) right--;// 去重
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

}
