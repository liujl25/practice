package com.liujunlong.leetcode.hot100;

/**
 * 11. 盛最多水的容器
 * https://leetcode.cn/problems/container-with-most-water/description/?envType=study-plan-v2&envId=top-100-liked
 */
public class Soluton_11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] height = {1, 1};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        //最大面积，长*宽，长：x轴坐标差，宽: height[i]
        //穷举比较->超时...
        int maxArea = 0;
        int xLeft = 0;
        int xRight = height.length - 1;
        while (xLeft <= xRight) {
            int area = 0;
            if (height[xRight] >= height[xLeft]) {
                area = (xRight - xLeft) * height[xLeft];
                xLeft++;
            } else {
                area = (xRight - xLeft) * height[xRight];
                xRight--;
            }
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}
