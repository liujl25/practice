package com.liujunlong.leetcode.hot100;

/**
 * 42. 接雨水
 * https://leetcode.cn/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Solution_42 {
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {4, 2, 3};
//        int[] height = {4, 2, 0, 3, 2, 5};
        System.out.println(trap1(height));
    }

    /**
     * my solution
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int left = 0, right = 1, n = height.length - 1;
        int area = 0;
        while (right < n) {
            //寻找凹槽右侧最大高度, 比左侧高则停止寻找，并计算雨水面积
            int maxHeight = 0;
            for (int i = right; i <= n && maxHeight < height[left]; i++) {
                if (height[i] >= maxHeight) {
                    maxHeight = height[i];
                    right = i;
                }
            }
            //雨水的深度
            int depth = Math.min(height[left], height[right]);
            //计算凹槽面积
            for (int i = left; i < right; i++) {
                if (height[i] < depth) {
                    area += depth - height[i];
                }
            }
            left = right;
            right++;
        }
        return area;
    }

    /**
     * 题解中最快的
     * https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/?envType=study-plan-v2&envId=top-100-liked
     * 动态规划，优化：双指针代替数组
     *
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        int left = 0, right = height.length - 1,
                maxLeft = 0, maxRight = 0,
                sum = 0;
        while (left < right) {
            //从柱子低的一遍开始遍历，雨水由低的一边决定
            if (height[left] < height[right]) {
                maxLeft = Math.max(maxLeft, height[left]);
                if (maxLeft > height[left]) {
                    sum += (maxLeft - height[left]);
                }
                left++;
            } else {
                maxRight = Math.max(maxRight, height[right]);
                if (maxRight > height[right]) {
                    sum += (maxRight - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

}
